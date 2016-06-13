/**
 * IMS (It Makes Sense) -- NUS WSD System
 * Copyright (c) 2010 National University of Singapore.
 * All Rights Reserved.
 */
package sg.edu.nus.comp.nlp.ims.feature;
import java.util.ArrayList;
import java.util.ListIterator;

/**
 * feature type with list value.
 * @author zhongzhi
 *
 */
public abstract class AEmbeddingFeature implements IFeature {
	// feature key
	protected String m_Key;
	// feature value
	//protected String m_embeddings;
	
	protected ArrayList<String> m_Values = new ArrayList<String>();

	/*
	 * (non-Javadoc)
	 * @see sg.edu.nus.comp.nlp.ims.feature.IFeature#getKey()
	 */
	public String getKey() {
		return this.m_Key;
	}

	/*
	 * (non-Javadoc)
	 * @see sg.edu.nus.comp.nlp.ims.feature.IFeature#getValue()
	 */
	public String getValue() {
		
		String embedding = "";
		
		for(int i=0; i<m_Values.size(); i++){
			
			if( embedding == "" ){ embedding = this.m_Values.get(i);}
			else { embedding = embedding + " " + this.m_Values.get(i);};		
			
		}
		
		return embedding;
	}

	public ArrayList<String> getValues() {
		return this.m_Values;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see sg.edu.nus.comp.nlp.ims.feature.IFeature#setKey(java.lang.String)
	 */
	public boolean setKey(String p_Key) {
		if (p_Key != null) {
			p_Key = p_Key.trim();
			if (!p_Key.isEmpty()) {
				this.m_Key = p_Key;
				return true;
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see sg.edu.nus.comp.nlp.ims.feature.IFeature#setValue(java.lang.String)
	 */
	public boolean setValue(String p_Value) {
		
		if (p_Value != null) {
			p_Value = p_Value.trim();
			if (!p_Value.isEmpty()) {
								
				String[] embeddings = p_Value.split(" "); 				
				
				for (int ind=0; ind < embeddings.length; ind++){
					m_Values.add(embeddings[ind]);					
				}
								
				return true;
			}
		}
		return false;
	}

	
	public boolean setAddedValue(String p_Value) {
		
		ListIterator<String> it = m_Values.listIterator();
		
		if (p_Value != null) {
			p_Value = p_Value.trim();
			if (!p_Value.isEmpty()) {
								
				String[] embeddings = p_Value.split(" "); 				
				
				for (int ind=0; ind < embeddings.length; ind++){
				
					Float embeddingsValue = Float.parseFloat(it.next());
					Float embeddingsCurrent = Float.parseFloat(embeddings[ind]);

					it.set(Float.toString(embeddingsValue + embeddingsCurrent));
										
					m_Values.add(embeddings[ind]);					
				}
								
				return true;
			}
		}
		return false;
	}

	public boolean setAvgValue() {
		
		ListIterator<String> it = m_Values.listIterator();
					
		while(it.hasNext()){

			Float embedding = Float.parseFloat(it.next());				
			it.set(Float.toString(embedding/this.m_Values.size()));
		
		}
								
		return true;
		
	}

	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	public abstract Object clone();
}
