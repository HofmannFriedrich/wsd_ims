/**
 * IMS (It Makes Sense) -- NUS WSD System
 * Copyright (c) 2010 National University of Singapore.
 * All Rights Reserved.
 */
package sg.edu.nus.comp.nlp.ims.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;

/**
 * Extract embedding from parallel files
 *
 * @author Aratz Puerto
 *
 */
public class CEmbeddingExtractor {

	// Hash table to store the embeddings of the words appeared until the moment. 
	// key: word, value: embedding
	protected Hashtable<String,String> m_embeddingHash = new Hashtable<String,String>();

	// Embedding for out of vocabulary words
	protected static String outOfVocabularyEmbedding = "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0";

	/**
	 * constructor
	 */
	/*protected CEmbeddingExtractor() {
				
	}*/

	
	/**
	 * Get the file names of the vocabulary and the embedding files containing the information of the word
	 *
	 * @param word
	 *            word to be fetched
	 * @return files[0]
	 *            path of the vocabulary file 				
	 * @return files[1]
	 *            path of the embedding file 
	 */
	private String[] getFileNames(String word){
		
		String[] files = new String[2];	
		
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
		
	}

	/**
	 * Get the line number of the appearance of a word in the file
	 *
	 * @param word
	 *            word to extract the line number
	 * @param filePath
	 *            path of the file to extract the line number 				
	 * @return line_num
	 *            line number of the appearance of the word word in the file in filePath 
	 */
	private long getLineNumber(String word, String filePath)
    {

		try {
			int line_num = -1;
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
			e1.printStackTrace();
		}

        return -1;
    }
	
	
	/**
	 * Get embedding in a specific line number
	 *
	 * @param line_number
	 *            line number to get the embedding from in the file
	 * @param filePath
	 *            file path of the file to get the embedding		
	 * @return embedding
	 *            embedding in the line number line_number in the file at filePath 
	 */
	private String getEmbeddingFromLineNumber(long line_number, String filePath){
		
		String embedding =  outOfVocabularyEmbedding;
		
		if (line_number >= 0){
			
			try {
				String com = "sed -n '" + line_number + "{p;q;}' < " + filePath;
				Process p = Runtime.getRuntime().exec(new String[]{"/bin/sh","-c",com});
				BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(p.getInputStream()));
				embedding = stdoutReader.readLine();	
				embedding.replace("\n", ""); //remove the line break
				stdoutReader.close();
				p.destroy();
			
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

	
		return embedding;
	}
	
	
	/**
	 * Get embedding for a word
	 *
	 * @param word
	 *            word for whom to fetch the embedding		
	 * @return embedding
	 *            embedding of the word word 
	 */
	public String getEmbedding(String word){
		
		String embedding = this.outOfVocabularyEmbedding;
		
		if(this.m_embeddingHash.containsKey(word)){	// fetch the embedding from the hash if it is a hit
			embedding = this.m_embeddingHash.get(word);
		}
		else{ // if the word is not in the hash table fetch it from file
			
			String vocabFilePath ="embedding/"+getFileNames(word)[0];
			String vectorFilePath="embedding/"+getFileNames(word)[1];

			long line_number=getLineNumber(word, vocabFilePath);		

	        embedding = getEmbeddingFromLineNumber(line_number, vectorFilePath);
	        this.m_embeddingHash.put(word, embedding);
	        
		}
		
		return embedding;
		
	}

	/**
	 * Get an embedding for the out of vocabulary case	
	 * @return embedding
	 *            embedding for the out of vocabulary word 
	 */
	public String getOutOfVocabEmbedding(){
		return this.outOfVocabularyEmbedding;
	}
	
}
