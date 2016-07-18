/**
 * IMS (It Makes Sense) -- NUS WSD System
 * Copyright (c) 2010 National University of Singapore.
 * All Rights Reserved.
 */
package sg.edu.nus.comp.nlp.ims.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

/**
 * Extract clusters from files
 *
 * @author Aratz Puerto
 *
 */
public class CClusterExtractor {

	// Hash table to store the clusters of the words appeared until the moment. 
	// key: word, value: cluster
	protected Hashtable<String,String> m_clusterHash = new Hashtable<String,String>();;

	// Cluster for out of vocabulary words
	protected static String outOfVocabularyCluster = "-1" ; 
	
	/**
	 * Cluster types 
	 */
	public enum clusterTypes {
		BROWN, CLARK, W2V
	}
	
	/**
	 * constructor
	 */
	/*public CEmbeddingExtractor() {
					
	}*/

	
	
	/**
	 * Load the clusters in the memory 
	 *
	 * @param clusterType
	 *            The type of cluster to be loaded
	 * @param filePath
	 *            path of the file to fetch the clusters 			 
	 */
	public void loadClusters(int clusterType, String filePath){
	
		try{
			BufferedReader br = new	BufferedReader(new FileReader(filePath));
			String line;
			
			String cluster = this.outOfVocabularyCluster;
			String word = "";
			
			while((line=br.readLine())!=null){
		
				
				// TODO: Orokortu clusterTypes erabiltzeko
				switch(clusterType){
				
				case 0:
					cluster = line.split("\t")[0];
					word = line.split("\t")[1];
					break;
				case 1:
					cluster = line.split(" ")[1];
					word = line.split(" ")[0];
					break;
				case 2:
					cluster = line.split(" ")[1];
					word = line.split(" ")[0];
					break;
				}
				
				if(!this.m_clusterHash.containsKey(word)){
					this.m_clusterHash.put(word,cluster);
				}
				
			}
			
			br.close();
		
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
			
	/**
	 * Get cluster for a word
	 *
	 * @param word
	 *            word for whom to fetch the cluster
	 * @param clusterType
	 * 			  Type of the cluster to be retrieved           		
	 * @return cluster
	 *            cluster of the word word 
	 */
	public String getCluster(String word){
			
		if(this.m_clusterHash.containsKey(word)){	// fetch the embedding from the hash if it is a hit
			 return this.m_clusterHash.get(word);
		}
		
		return this.outOfVocabularyCluster;		
	}

	/**
	 * Get a cluster for the out of vocabulary case	
	 * @return cluster
	 *            cluster for the out of vocabulary word 
	 */
	public String getOutOfVocabCluster(){
		return this.outOfVocabularyCluster;
	}
	
}
