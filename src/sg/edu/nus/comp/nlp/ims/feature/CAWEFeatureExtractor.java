/**
 * IMS (It Makes Sense) -- NUS WSD System
 * Copyright (c) 2010 National University of Singapore.
 * All Rights Reserved.
 */
package sg.edu.nus.comp.nlp.ims.feature;
import java.util.*;
import sg.edu.nus.comp.nlp.ims.corpus.AItem;
import sg.edu.nus.comp.nlp.ims.corpus.ICorpus;
import sg.edu.nus.comp.nlp.ims.corpus.IItem;
import sg.edu.nus.comp.nlp.ims.corpus.ISentence;
import sg.edu.nus.comp.nlp.ims.util.CEmbeddingExtractor;

/**
 * Average Context Word Embedding extractor.
 *
 * @author Aratz Puerto
 *
 */
public class CAWEFeatureExtractor implements IFeatureExtractor {
	// corpus to be extracted
	protected ICorpus m_Corpus = null;

	// index of current instance
	protected int m_Index = -1;

	// current sentence to process
	protected ISentence m_Sentence = null;

	// item index in current sentence
	protected int m_IndexInSentence;
	
	// item index in current embedding
	protected int m_IndexInEmbedding;
	
	// average embedding for current feature value
	protected String[] m_avgEmbedding;
	
	// item length
	protected int m_InstanceLength;

	// default window size
	protected int m_windowSize = 5;
	
	// Element array string for current feature
	protected ArrayList<String> m_AWEset = new ArrayList<String>();

	// item index in the element array string for current feature
	protected int m_AWEIndex = -1;

	// target word index in the element array string
	protected int m_targetAWEIndex = -1;
	
	// sentence before current sentence
	protected int m_Left;
	
	// sentence after current sentence
	protected int m_Right;

	// current feature
	protected IFeature m_CurrentFeature = null;

	// embedding extractor
	protected static CEmbeddingExtractor m_embExtractor = new CEmbeddingExtractor();

	
	/**
	 * constructor
	 */
	public CAWEFeatureExtractor() {
		this.m_Left = Integer.MAX_VALUE;
		this.m_Right = Integer.MAX_VALUE;
	}

	/**
	 * constructor
	 * @param p_Left
	 * 	number of sentences left to current sentence that will be used to extract surrounding words
	 * @param p_Right
	 * 	number of sentences right to current sentence that will be used to extract surrounding words
	 */
	public CAWEFeatureExtractor(int p_Left, int p_Right) {
		if (p_Left < 0 || p_Right < 0) {
			throw new IllegalArgumentException(
					"p_Before and p_After should be >= 0");
		}
		this.m_Left = p_Left;
		this.m_Right = p_Right;
	}

	/**
	 * constructor
	 * @param p_StopWords
	 *            stop word list
	 */
	/*public CAWEFeatureExtractor(HashSet<String> p_StopWords) {
		if (p_StopWords == null) {
			throw new IllegalArgumentException(
					"stop words list should not be null.");
		}
		this.m_Left = Integer.MAX_VALUE;
		this.m_Right = Integer.MAX_VALUE;
	}*/

	/**
	 * constructor
	 * @param p_Left
	 * 	number of sentences left to current sentence that will be used to extract surrounding words
	 * @param p_Right
	 * 	number of sentences right to current sentence that will be used to extract surrounding words
	 * @param p_StopWords
	 * 	stop word list
	 */
	public CAWEFeatureExtractor(int p_Left, int p_Right,
			HashSet<String> p_StopWords) {
		if (p_Left < 0 || p_Right < 0) {
			throw new IllegalArgumentException(
					"p_Before and p_After should be >= 0");
		}
		if (p_StopWords == null) {
			throw new IllegalArgumentException(
					"stop words list should not be null.");
		}
		this.m_Left = p_Left;
		this.m_Right = p_Right;
	}

	/*
	 * (non-Javadoc)
	 * @see sg.edu.nus.comp.nlp.ims.feature.IFeatureExtractor#getCurrentInstanceID()
	 */
	@Override
	public String getCurrentInstanceID() {
		if (this.validIndex(this.m_Index)) {
			return this.m_Corpus.getValue(this.m_Index, "id");
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see sg.edu.nus.comp.nlp.ims.feature.IFeatureExtractor#hasNext()
	 */
	@Override
	public boolean hasNext() {
		if (this.m_CurrentFeature != null) {
			return true;
		}
		if (this.validIndex(this.m_Index)) {
			this.m_CurrentFeature = this.getNext();
			if (this.m_CurrentFeature != null) {
				return true;
			}
		}
		return false;
	}


	/**
	 * form CWE feature name
	 *
	 * @param p_Index
	 *            index
	 * @return feature name
	 */
	protected String formAWEName(int p_Index) {
		return "AWE_" + this.m_IndexInEmbedding; 
	}
	
	/**
	 * get item list for current feature in current sentence
	 * if the window exceed current sentence items outside the sentence get 
	 * an out of vocabulary embedding
	 */
	private void getBoundedAWESet(){
		
		for(int i = (this.m_IndexInSentence - this.m_windowSize); i <= (this.m_IndexInSentence + this.m_windowSize); i++){
		
			// If the window exceeds current sentence ignore the words which do so.
			if(i>=0 && i<this.m_Sentence.size()){			
				String word = this.m_Sentence.getItem(i).get(AItem.Features.TOKEN.ordinal());
				String embeddingStr = m_embExtractor.getEmbedding(word);
				this.m_AWEset.add(embeddingStr);
				if(i==this.m_windowSize){this.m_targetAWEIndex=this.m_AWEset.size();}
			}
			
			
		}
		
	}
	
	/**
	 * get item list for current feature from current, previous and next sentences
	 */
	private void getAWESet(){

		for(int i = (this.m_IndexInSentence - this.m_windowSize); i <= (this.m_IndexInSentence + this.m_windowSize); i++){
					
			String word = null;
			
			
			System.out.println("i: "+i+" m_Index: "+this.m_Index + " Sentence size:"+ this.m_Sentence.size() +" Nº Sentences:" + this.m_Corpus.numOfSentences());
			
			// The window exceeds the sentence and needs to get the word from the previous  one.
			if(i<=0 && this.m_Index>0){ 
				
				int prevSentenceId = this.m_Corpus.getSentenceID(this.m_Index-1);
				ISentence previousSentence = this.m_Corpus.getSentence(prevSentenceId);
				
				if(previousSentence != null){
				
					int sentenceID = previousSentence.size() + i;
												
					// The window might still exceed the previous sentence.
					if(sentenceID>previousSentence.size()){
						word = previousSentence.getItem(sentenceID).get(AItem.Features.TOKEN.ordinal());
					}
					//word = previousSentence.getItem(sentenceID).get(AItem.Features.TOKEN.ordinal());

					
				}
				
			}
			// The window exceeds the sentence and needs to get the word from the next.
			else if (i>=this.m_Sentence.size() && this.m_Index+1<this.m_Corpus.numOfSentences()){ 
			
				int nextSentenceId = this.m_Corpus.getSentenceID(this.m_Index+1);
				ISentence nextSentence = this.m_Corpus.getSentence(nextSentenceId);
				
				if (nextSentence != null){

					int sentenceID = i - this.m_Sentence.size();
					
					// The window might still exceed next sentence.
					if(sentenceID<nextSentence.size()){ 
						word = nextSentence.getItem(sentenceID).get(AItem.Features.TOKEN.ordinal());
					}
				}
			}
			// The whole window takes words from current sentence
			else{
				word = this.m_Sentence.getItem(i).get(AItem.Features.TOKEN.ordinal());
			}
		
			if(word != null){
				String embeddingStr = m_embExtractor.getEmbedding(word);
				this.m_AWEset.add(embeddingStr);
				if(i==this.m_windowSize){this.m_targetAWEIndex=this.m_AWEset.size();}
			}
		}
				
	}
	
	/**
	 * get the average of the embedding stored in AWEset 
	 */
	private void getAvgEmbedding(){
		
		// Get the set of words for the current AWE
		if(this.m_AWEset.isEmpty()){this.getAWESet();}
		
		// Get the first element of the set as the first embedding for the avg
		if(this.m_avgEmbedding==null){this.m_avgEmbedding=this.m_AWEset.get(0).split(" ");}
		
		for(int i=0; i<this.m_AWEset.size(); i++){
			
			String[] embStr = this.m_AWEset.get(i).split(" ");
			
			for(int j=0; j<this.m_avgEmbedding.length; j++){
							
				float embValue = Float.parseFloat(this.m_avgEmbedding[j]) + Float.parseFloat(embStr[j]); 
				
				if(j==(this.m_avgEmbedding.length-1)){this.m_avgEmbedding[j]= Float.toString(embValue/ this.m_avgEmbedding.length);}
				else{this.m_avgEmbedding[j] = Float.toString(embValue);}
			}
			
		}
				
	}
	
	/**
	 * get next feature
	 *
	 * @return feature
	 */	
	private IFeature getNext() {
		
		System.out.println("GET"+this.m_Index);
		IFeature feature = null;
		
		// Get the embedding containing the average of all the embeddings in the current AWE set
		if(this.m_avgEmbedding==null){getAvgEmbedding();}

		// Create a feature for each element in the set of words
		if (this.m_AWEIndex >=0 && this.m_AWEIndex < this.m_AWEset.size()){
			
			feature = new CCWEFeature();
			feature.setKey(this.formAWEName(this.m_IndexInEmbedding));
			feature.setValue(this.m_avgEmbedding[this.m_IndexInEmbedding]);
			this.m_IndexInEmbedding++;
		
			// If the last feature for the AWE has been created restart variables for the next target word
			if(this.m_IndexInEmbedding==this.m_avgEmbedding.length){
				this.m_IndexInEmbedding=0;
				this.m_AWEIndex++;
				this.m_avgEmbedding=null;
			}
						
		}

		return feature;
		
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see sg.edu.nus.comp.nlp.ims.feature.IFeatureExtractor#next()
	 */
	@Override
	public IFeature next() {
		IFeature feature = null;
		if (this.hasNext()) {
			feature = this.m_CurrentFeature;
			this.m_CurrentFeature = null;
		}
		return feature;
	}
		
	/*
	 * (non-Javadoc)
	 * @see sg.edu.nus.comp.nlp.ims.feature.IFeatureExtractor#restart()
	 */
	@Override
	public boolean restart() {
		this.m_IndexInEmbedding = 0;
		this.m_avgEmbedding = null;
		this.m_CurrentFeature = null;
		this.m_AWEIndex=0;
		this.m_AWEset.clear();
		return this.validIndex(this.m_Index);
	}

	/*
	 * (non-Javadoc)
	 * @see sg.edu.nus.comp.nlp.ims.feature.IFeatureExtractor#setCorpus(sg.edu.nus.comp.nlp.ims.corpus.ICorpus)
	 */
	@Override
	public boolean setCorpus(ICorpus p_Corpus) {
		if (p_Corpus == null) {
			return false;
		}
		this.m_Corpus = p_Corpus;
		this.m_Index = 0;
		this.restart();
		this.m_Index = -1;
		this.m_IndexInSentence = -1;
		this.m_InstanceLength = -1;
		return true;
	}

	/**
	 * check the validity of index
	 *
	 * @param p_Index
	 *            index
	 * @return valid or not
	 */
	protected boolean validIndex(int p_Index) {
		if (this.m_Corpus != null && this.m_Corpus.size() > p_Index
				&& p_Index >= 0) {
			return true;
		}
		return false;
	}


	/*
	 * (non-Javadoc)
	 * @see sg.edu.nus.comp.nlp.ims.feature.IFeatureExtractor#setCurrentInstance(int)
	 */
	
	@Override
	public boolean setCurrentInstance(int p_Index) {
		if (this.validIndex(p_Index)) {
			this.m_Index = p_Index;
			this.m_IndexInSentence = this.m_Corpus.getIndexInSentence(p_Index);
			this.m_InstanceLength = this.m_Corpus.getLength(p_Index);
			this.m_Sentence = this.m_Corpus.getSentence(this.m_Corpus.getSentenceID(p_Index));
		
			this.restart();							
			
			return true;
		}
		return false;
	}

	
	
	
}
