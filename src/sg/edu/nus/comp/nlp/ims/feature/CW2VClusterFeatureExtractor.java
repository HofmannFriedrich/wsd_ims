/**
 * IMS (It Makes Sense) -- NUS WSD System
 * Copyright (c) 2010 National University of Singapore.
 * All Rights Reserved.
 */
package sg.edu.nus.comp.nlp.ims.feature;

import java.util.ArrayList;
import java.util.HashSet;

import sg.edu.nus.comp.nlp.ims.corpus.AItem;
import sg.edu.nus.comp.nlp.ims.corpus.ICorpus;
import sg.edu.nus.comp.nlp.ims.corpus.ISentence;
import sg.edu.nus.comp.nlp.ims.util.CClusterExtractor;

/**
 * Context Word Embedding extractor.
 *
 * @author Aratz Puerto
 *
 */
public class CW2VClusterFeatureExtractor implements IFeatureExtractor {
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
	
	// embedding for current feature value
	protected String[] m_CurrentCluster; 
	
	// item length
	protected int m_InstanceLength;
	
	// default window size
	protected int m_windowSize = 5;
	
	// Element array string for current feature
	protected ArrayList<String> m_CWCset = new ArrayList<String>();

	// item index in the element array string for current feature
	protected int m_CWCIndex = -1;
	
	// target word index in the element array string
	protected int m_targetCWCIndex = -1;
	
	// sentence before current sentence
	protected int m_Left;

	// sentence after current sentence
	protected int m_Right;

	// current feature
	protected IFeature m_CurrentFeature = null;

	// embedding extractor
	protected static CClusterExtractor m_clusterExtractor = new CClusterExtractor();

	// Cluster types
	protected static CClusterExtractor.clusterTypes m_clusterType = CClusterExtractor.clusterTypes.W2V;
	
	// Cluster file paths
	protected static String m_BrownClusterPath = "cluster/brown.txt";
	protected static String m_ClarkClusterPath = "cluster/clark.txt";
	protected static String m_w2vClusterPath = "cluster/w2v.txt";
	
	/**
	 * constructor
	 */
	public CW2VClusterFeatureExtractor() {
		
		
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
	public CW2VClusterFeatureExtractor(int p_Left, int p_Right) {
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
	/*public CCWEFeatureExtractor(HashSet<String> p_StopWords) {
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
	public CW2VClusterFeatureExtractor(int p_Left, int p_Right,
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
	protected String formCWCName(int p_Index) {
		
		if(p_Index<0){
			return "CWWC_" + -p_Index + "C" + this.m_IndexInEmbedding;
		}
		else
		{
			return "CWWC" + p_Index + "C" + this.m_IndexInEmbedding;
		}
		
	}
	
	
	/**
	 * get item list for current feature in current sentence
	 * if the window exceed current sentence items outside the sentence get 
	 * an out of vocabulary embedding
	 */
	/*private void getBoundedCWESet(){
				
		for(int i = (this.m_IndexInSentence - this.m_windowSize); i <= (this.m_IndexInSentence + this.m_windowSize); i++){			
			
			// If the window exceeds current sentence ignore the words which do so.
			if(i>=0 && i<this.m_Sentence.size()){
				String word = this.m_Sentence.getItem(i).get(AItem.Features.TOKEN.ordinal());
				String embeddingStr = m_embExtractor.getEmbedding(word);
				this.m_CWEset.add(embeddingStr);		
				if(i==this.m_windowSize){this.m_targetCWEIndex=this.m_CWEset.size();}
			}	
			
		}
	}*/
	
	
	/**
	 * get item list for current feature from current, previous and next sentences
	 */
	private void getCWCSet(){

		for(int i = (this.m_IndexInSentence - this.m_windowSize); i <= (this.m_IndexInSentence + this.m_windowSize); i++){
					
			String word = null;
			
			// The window exceeds the sentence and needs to get the word from the previous  one.
			if(i<0 && this.m_Index>0){ 
				
				int prevSentenceId = this.m_Corpus.getSentenceID(this.m_Index-1);
				ISentence previousSentence = this.m_Corpus.getSentence(prevSentenceId);
				
				if(previousSentence != null){
					int sentenceID = previousSentence.size() + i;
				
					//The window might still exceed the previous sentence.
					if(sentenceID>=0){ 
						word = previousSentence.getItem(sentenceID).get(AItem.Features.TOKEN.ordinal());
					}
				}
			}
			// The window exceeds the sentence and needs to get the word from the next.
			else if (i>=this.m_Sentence.size() && this.m_Index<this.m_Corpus.numOfSentences()){ 
				
				int nextSentenceId = this.m_Corpus.getSentenceID(this.m_Index+1);
				ISentence nextSentence = this.m_Corpus.getSentence(nextSentenceId);
				
				if(nextSentence != null){
					int sentenceID = i - this.m_Sentence.size();
				
					// The window might still exceed next sentence.
					if(sentenceID < nextSentence.size()){ 
						word = nextSentence.getItem(sentenceID).get(AItem.Features.TOKEN.ordinal());
					}
				}
			}
			else{
				word = this.m_Sentence.getItem(i).get(AItem.Features.TOKEN.ordinal());
			}
		
			if (word != null){
				String clusterStr = this.m_clusterExtractor.getCluster(word);
				this.m_CWCset.add(clusterStr);
				if(i==this.m_windowSize){this.m_targetCWCIndex=this.m_CWCset.size();}
			}
			
		}
	}
	
	
	
	/**
	 * get next feature
	 *
	 * @return feature
	 */
	private IFeature getNext() {
				
		IFeature feature = null;	
		
		
		// Get the set of word for the current representation
		if(this.m_CWCset.isEmpty()){getCWCSet();}
		
		// Create a feature for each element in the set of words
		if (this.m_CWCIndex >=0 && this.m_CWCIndex < this.m_CWCset.size()){

			feature = new CCWEFeature();	
			// Get the position of the word in relation to the target word
			int clusterInd = this.m_CWCIndex-this.m_targetCWCIndex;
			feature.setKey(this.formCWCName(clusterInd));
			feature.setValue(this.m_CWCset.get(this.m_CWCIndex));
			
			this.m_CWCIndex++;		

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

		this.m_IndexInEmbedding=0;
		this.m_CurrentFeature = null;
		this.m_CurrentCluster = null;
		this.m_CWCset.clear();
		this.m_CWCIndex=0;

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
			this.m_clusterExtractor.loadClusters(this.m_clusterType.ordinal(), this.m_w2vClusterPath);
			
			this.restart();							
			
			return true;
		}
		return false;
	}

		
	
	
	
}
