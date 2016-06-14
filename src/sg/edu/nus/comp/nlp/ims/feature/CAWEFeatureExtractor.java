/**
 * IMS (It Makes Sense) -- NUS WSD System
 * Copyright (c) 2010 National University of Singapore.
 * All Rights Reserved.
 */
package sg.edu.nus.comp.nlp.ims.feature;

import java.util.*;
// proba
import java.io.*;

import sg.edu.nus.comp.nlp.ims.corpus.AItem;
import sg.edu.nus.comp.nlp.ims.corpus.ICorpus;
import sg.edu.nus.comp.nlp.ims.corpus.ISentence;
import sg.edu.nus.comp.nlp.ims.util.CEmbeddingExtractor;
import sg.edu.nus.comp.nlp.ims.util.CSurroundingWordFilter;
import sg.edu.nus.comp.nlp.ims.util.CEmbeddingExtractor;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;
import java.nio.file.Files;
import java.nio.file.Paths;
//import java.util.stream.Stream;
import java.util.regex.Pattern;
//import java.util.concurrent.atomic.AtomicInteger;

/**
 * surrounding word extractor.
 *
 * @author zhongzhi
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
	protected int m_IndexInEmbedding;
	
//	protected String[] m_CurrentEmbedding; 
	protected String[] m_avgEmbedding;
	
	// item length
	protected int m_InstanceLength;

	protected ArrayList<String> m_AWEset = new ArrayList<String>();

	
	// index of surrounding word feature
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

	//protected ArrayList<String> m_avgEmbedding = new ArrayList<String>();
	protected int m_AWEIndex = -1;
		
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
	public CAWEFeatureExtractor(HashSet<String> p_StopWords) {
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
	

	
	
	private void getAWESet(){
		
		for(int i=0; i<this.m_Sentence.size(); i++){
			String word = this.m_Sentence.getItem(i).get(AItem.Features.TOKEN.ordinal());
			String embeddingStr = m_embExtractor.getEmbedding(word);
			this.m_AWEset.add(embeddingStr);
		}
		
	}
	
	private void getAvgEmbedding(){
		
		if(this.m_AWEset.isEmpty()){this.getAWESet();}
		
		if(this.m_avgEmbedding==null){this.m_avgEmbedding=this.m_embExtractor.getOutOfVocabEmbedding().split(" ");}
		
		for(int i=0; i<this.m_AWEset.size(); i++){
			
			for(int j=0; j<this.m_avgEmbedding.length; j++){
				
				float embValue = Float.parseFloat(this.m_avgEmbedding[j]) + Float.parseFloat(this.m_AWEset.get(i).split(" ")[j]); 
				
				if(j==(this.m_avgEmbedding.length-1)){
					this.m_avgEmbedding[j]= Float.toString(embValue/ this.m_avgEmbedding.length);
				}
				else
				{
					this.m_avgEmbedding[j] = Float.toString(embValue);
				}
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
		if(this.m_avgEmbedding==null){getAvgEmbedding();}

		//if (this.m_IndexInSentence >=0 && this.m_IndexInSentence < this.m_Sentence.size()){

		//if (this.m_AWEIndex >=0 && this.m_AWEIndex < this.m_AWEset.size()){

		if(this.m_IndexInEmbedding >= 0 && this.m_IndexInEmbedding < this.m_avgEmbedding.length){
			
			feature = new CCWEFeature();
			feature.setKey(this.formAWEName(this.m_IndexInEmbedding));
			feature.setValue(this.m_avgEmbedding[this.m_IndexInEmbedding]);
			this.m_IndexInEmbedding++;
			
			if(this.m_IndexInEmbedding==this.m_avgEmbedding.length){
				this.m_IndexInEmbedding=-1;
				this.m_avgEmbedding=null;
				this.m_AWEset.clear();
	//			System.out.println("Instance: " + m_Index + ", Size: " + this.m_Sentence.size());
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
		this.m_IndexInSentence = 0;
		this.m_IndexInEmbedding = 0;
		this.m_avgEmbedding = null;
		//this.m_CurrentEmbedding = null;
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
//			this.m_CWESet.clear();
	//		this.getAWESet();
			
			this.restart();							
			
			return true;
		}
		return false;
	}

	
	
	
}
