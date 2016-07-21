/**
 * IMS (It Makes Sense) -- NUS WSD System
 * Copyright (c) 2010 National University of Singapore.
 * All Rights Reserved.
 */
package sg.edu.nus.comp.nlp.ims.feature;

/**
 * collocation feature.
 *
 * @author zhongzhi
 *
 */
public class CCWCFeature extends AListFeature {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/*public CCWEFeature() {
	}*/

	/*
	 * (non-Javadoc)
	 * @see sg.edu.nus.comp.nlp.ims.feature.AListFeature#clone()
	 */
	public Object clone() {
		CCWCFeature clone = new CCWCFeature();
		clone.m_Key = this.m_Key;
		clone.m_Value = this.m_Value;
		return clone;
	}
}
