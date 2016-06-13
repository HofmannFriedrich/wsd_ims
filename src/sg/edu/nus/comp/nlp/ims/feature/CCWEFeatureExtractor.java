/**
 * IMS (It Makes Sense) -- NUS WSD System
 * Copyright (c) 2010 National University of Singapore.
 * All Rights Reserved.
 */
package sg.edu.nus.comp.nlp.ims.feature;

import java.util.ArrayList;
import java.util.HashSet;
//import java.util.*;
// proba
//import java.io.*;

import sg.edu.nus.comp.nlp.ims.corpus.AItem;
import sg.edu.nus.comp.nlp.ims.corpus.ICorpus;
import sg.edu.nus.comp.nlp.ims.corpus.ISentence;
//import sg.edu.nus.comp.nlp.ims.util.CSurroundingWordFilter;
import sg.edu.nus.comp.nlp.ims.util.CEmbeddingExtractor;

/*import java.util.Hashtable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.regex.Pattern;
import java.util.concurrent.atomic.AtomicInteger;*/



/**
 * surrounding word extractor.
 *
 * @author zhongzhi
 *
 */
public class CCWEFeatureExtractor implements IFeatureExtractor {
	// corpus to be extracted
	protected ICorpus m_Corpus = null;

	// index of current instance
	protected int m_Index = -1;

	protected int m_CWEIndex = -1;
	
	// current sentence to process
	protected ISentence m_Sentence = null;

	// item index in current sentence
	protected int m_IndexInSentence;

	protected int m_IndexInEmbedding;
	
	protected String[] m_CurrentEmbedding; 
	
	protected ArrayList<String> m_CWEset = new ArrayList<String>();
	
	// item length
	protected int m_InstanceLength;
	
	protected int m_windowSize = 5;


	// Hash table of the words appeared so far
	//protected Hashtable<String,String> m_embeddingHash = new Hashtable<String,String>();
	
	// sentence before current sentence
	protected int m_Left;

	// sentence after current sentence
	protected int m_Right;

	// current feature
	protected IFeature m_CurrentFeature = null;

	//protected static long line_no;
	//protected static String outOfVocabularyEmbedding = "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0";
	
	protected static CEmbeddingExtractor m_embExtractor = new CEmbeddingExtractor();
	
	
	/**
	 * constructor
	 */
	public CCWEFeatureExtractor() {
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
	public CCWEFeatureExtractor(int p_Left, int p_Right) {
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
	public CCWEFeatureExtractor(HashSet<String> p_StopWords) {
		if (p_StopWords == null) {
			throw new IllegalArgumentException(
					"stop words list should not be null.");
		}
		this.m_Left = Integer.MAX_VALUE;
		this.m_Right = Integer.MAX_VALUE;
	}

	/**
	 * constructor
	 * @param p_Left
	 * 	number of sentences left to current sentence that will be used to extract surrounding words
	 * @param p_Right
	 * 	number of sentences right to current sentence that will be used to extract surrounding words
	 * @param p_StopWords
	 * 	stop word list
	 */
	public CCWEFeatureExtractor(int p_Left, int p_Right,
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
	protected String formCWEName(int p_Index) {
		return "CWE_" + p_Index + "_" + this.m_IndexInEmbedding;
	}
	
	
	/*public String[] getFileNames(String word){
		
		String[] files = new String[2];
	//	char c= word.toLowerCase().charAt(0);
		
		
		if(word.matches("\\d.*")){
			files[0]="voc.dig";
			files[1]="emb.dig";	
		}
		else if(word.matches("[A-Za-z].*")){
			files[0]="voc."+ word.toUpperCase().charAt(0);
			files[1]="emb."+ word.toUpperCase().charAt(0);
		}		
		else{
			files[0]="voc.punc";
			files[1]="emb.punc";
		}
	
		return files;
		
	}*/
	
	
/*	public long getLineNumber(String word, String filePath)
    {

		try {

			//String filepath = "embedding/"+getFileNames(word)[0];
			int line_num = 0;
			String targetWord = word;
			if(!word.matches("\\w.*")){targetWord="\\"+word;}
			
			Process p = Runtime.getRuntime().exec(new String[]{"/bin/sh","-c","grep -wn '^"+ targetWord + "$' "+filePath});
			BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String output = stdoutReader.readLine();	
			if(output!=null){
				String[] parts = output.split(":"); 
				line_num = Integer.parseInt(parts[0]);} 
			stdoutReader.close();
			p.destroy();
			return line_num;
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        return 0;
    }*/

/*	public String getEmbeddingFromLineNumber(long line_number, String filePath){
					
			String embedding = "";
			if (line_number > 0){ // The word is in the bocabulary and therefore an embedding can be retrieved for it
				
				try {
					String com = "sed -n '" + line_number + "{p;q;}' < " + filePath;
					Process p = Runtime.getRuntime().exec(new String[]{"/bin/sh","-c",com});
					BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(p.getInputStream()));
					embedding = stdoutReader.readLine();	
					embedding.replace("\n", ""); //remove the line break
					stdoutReader.close();
					p.destroy();
				
				} catch (IOException e1) {
				// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else{ // out of vocabulary case
				embedding = outOfVocabularyEmbedding;
			}
		
		return embedding;
	}*/
	
	/*private String getEmbedding(String word){
		
		String embedding = this.outOfVocabularyEmbedding;
		
		if(this.m_embeddingHash.containsKey(word)){
			
			embedding = this.m_embeddingHash.get(word);
		
			
			// TODO: Borratu, probarako da
			//System.out.println("[" + this.m_Index +"] " + this.m_IndexInSentence + "/" + this.m_Sentence.size() + ": " + word + " (*)");
			/*if( this.m_IndexInSentence == 0 ){
				System.out.print(this.m_Index + " ");
			}*/
/*
		}
		else{
			
			// Hash taulan ez badago embeddigarentzako sarrerarik gehitu.
			String vocabFilePath ="embedding/"+getFileNames(word)[0];
			String vectorFilePath="embedding/"+getFileNames(word)[1];
	        
			// TODO: Borratu, probarako da
			//System.out.print("[" + this.m_Index +"] " + this.m_IndexInSentence + "/" + this.m_Sentence.size() + ": " + word.toLowerCase());
			
			// Get the line number of the word appearance in the bocabulary file
			long line_number=getLineNumber(word, vocabFilePath);		
			
			// TODO: pintln-ak borratu, probarako dira
			//System.out.print(" (" + line_number+ ")\n");
	    
			
	        embedding = getEmbeddingFromLineNumber(line_number, vectorFilePath);
	        this.m_embeddingHash.put(word, embedding);
	        //this.m_CurrentEmbedding = embeddingStr.split(" ");

	       // System.out.println("Hash: " + this.m_embeddingHash.size());
	        
		}
		
		return embedding;
		
	}*/
	
	private void getCWESet(){
				
		for(int i = (this.m_IndexInSentence - this.m_windowSize); i <= (this.m_IndexInSentence + this.m_windowSize); i++){

			//if(this.m_CWEset.isEmpty()){this.getCWESet();}
			
			
			if(i<0 | i>=this.m_Sentence.size()){
				this.m_CWEset.add(m_embExtractor.getOutOfVocabEmbedding());
			}
			else{
				String word = this.m_Sentence.getItem(i).get(AItem.Features.TOKEN.ordinal());
				String embeddingStr = m_embExtractor.getEmbedding(word);
				this.m_CWEset.add(embeddingStr);		
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
				
		if(this.m_CWEset.isEmpty()){this.getCWESet();}
		
		if (this.m_CWEIndex >=0 && this.m_CWEIndex < this.m_CWEset.size()){

			feature = new CCWEFeature();	
			feature.setKey("CWE_" + this.m_CWEIndex + "_" + this.m_IndexInEmbedding);
				
			if(this.m_CurrentEmbedding==null){this.m_CurrentEmbedding=this.m_CWEset.get(this.m_CWEIndex).split(" ");}
						
			feature.setValue(this.m_CurrentEmbedding[this.m_IndexInEmbedding]);
			this.m_IndexInEmbedding++;
			
			if(this.m_IndexInEmbedding==this.m_CurrentEmbedding.length){
				this.m_IndexInEmbedding = 0;
				this.m_CWEIndex++;
				this.m_CurrentEmbedding=null;

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
	//	this.m_SurroundingWordIndex = 0;
		//this.m_IndexInSentence = 0;
		this.m_IndexInEmbedding=0;
		this.m_CurrentFeature = null;
		this.m_CurrentEmbedding = null;
		this.m_CWEset.clear();
		this.m_CWEIndex=0;
//		this.getCWESet();

		
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
			this.getCWESet();
			
			this.restart();							
			
			return true;
		}
		return false;
	}

		
	
	
	
}
