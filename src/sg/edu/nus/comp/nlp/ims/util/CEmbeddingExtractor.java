/**
 * IMS (It Makes Sense) -- NUS WSD System
 * Copyright (c) 2010 National University of Singapore.
 * All Rights Reserved.
 */
package sg.edu.nus.comp.nlp.ims.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.regex.Pattern;

/**
 * filter stop words and words that contains no alphabet.
 *
 * @author zhongzhi
 *
 */
public class CEmbeddingExtractor {

	protected Hashtable<String,String> m_embeddingHash = new Hashtable<String,String>();

	
	protected static String outOfVocabularyEmbedding = "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0";



	/**
	 * constructor
	 */
/*	protected CEmbeddingExtractor() {
				
	}*/

	private String[] getFileNames(String word){
		
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
		
	}


	private long getLineNumber(String word, String filePath)
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
    }
	
	private String getEmbeddingFromLineNumber(long line_number, String filePath){
		
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
	}
	
	
	public String getEmbedding(String word){
		
		String embedding = this.outOfVocabularyEmbedding;
		
		if(this.m_embeddingHash.containsKey(word)){
			
			embedding = this.m_embeddingHash.get(word);
		
			
			// TODO: Borratu, probarako da
			//System.out.println("[" + this.m_Index +"] " + this.m_IndexInSentence + "/" + this.m_Sentence.size() + ": " + word + " (*)");
			/*if( this.m_IndexInSentence == 0 ){
				System.out.print(this.m_Index + " ");
			}*/

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
		
	}

	public String getOutOfVocabEmbedding(){
		return this.outOfVocabularyEmbedding;
	}
	
}
