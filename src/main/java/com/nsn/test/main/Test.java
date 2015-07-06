package com.nsn.test.main;

import org.xml.sax.SAXException;

public class Test {
	
	/**
	 * @param args
	 * @throws Exception 
	 * @throws SAXException 
	 */
	public static void main(String[] args) throws SAXException, Exception {
		BrandProductManager t= new BrandProductManager();
		t.init();
		t.process();
	}

}
