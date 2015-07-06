package com.nsn.test.process;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.nsn.test.bean.BaseBean;
import com.nsn.test.queue.BrandProductQueue;
import com.nsn.test.queue.EmptyBrandProcessQueue;
import com.nsn.test.util.StringUtils;


public class ContentHandlerProcess<T  extends BaseBean> implements ContentHandler, Runnable {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ContentHandlerProcess.class);

	XMLReader reader;
	InputSource source;
	EmptyBrandProcessQueue<T> emptyBrandQueue;
	BrandProductQueue<T> brandProductQueue;
	Class<T> tagetClass;
	
	public ContentHandlerProcess(XMLReader reader,InputSource source,EmptyBrandProcessQueue<T> emptyBrandQueue,
			BrandProductQueue<T> brandProductQueue,Class<T> tagetClass){
		this.reader=reader;
		this.source=source;
		this.emptyBrandQueue=emptyBrandQueue;
		this.brandProductQueue=brandProductQueue;
		this.tagetClass=tagetClass;
	}

	public void setDocumentLocator(Locator locator) {
		if (logger.isDebugEnabled()) {
			logger.debug("setDocumentLocator(Locator) - =====setDocumentLocator====="); //$NON-NLS-1$
		}
		
	}

	public void startDocument() throws SAXException {
		if (logger.isDebugEnabled()) {
			logger.debug("startDocument() - =====startDocument====="); //$NON-NLS-1$
		}
		
	}

	public void endDocument() throws SAXException {
		emptyBrandQueue.endEmptyBrandProcess();
		if (logger.isDebugEnabled()) {
			logger.debug("endDocument() - =====endDocument====="); //$NON-NLS-1$
		}
		
	}

	public void startPrefixMapping(String prefix, String uri)
			throws SAXException {
		if (logger.isDebugEnabled()) {
			logger.debug("startPrefixMapping(String, String) - =====startPrefixMapping====="); //$NON-NLS-1$
		}
		
	}

	
	public void endPrefixMapping(String prefix) throws SAXException {
		if (logger.isDebugEnabled()) {
			logger.debug("endPrefixMapping(String) - =====endPrefixMapping====="); //$NON-NLS-1$
		}
		
	}

	
	public void startElement(String uri, String localName, String qName,
			Attributes atts) throws SAXException {
		if (logger.isDebugEnabled()) {
			logger.debug("startElement(String, String, String, Attributes) - =====startElement====="); //$NON-NLS-1$
		}
		T obj = null;
		try {
			obj = tagetClass.newInstance();
		} catch (Exception e1) {
			if (logger.isDebugEnabled()) {
				logger.debug("startElement(String, String, String, Attributes) - ***crete tagetClass error!***"); //$NON-NLS-1$
			}
			logger.error("startElement(String, String, String, Attributes)", e1); //$NON-NLS-1$
		}

		for (int i = 0; i < atts.getLength(); i++) {
			String methodName = "set"+ StringUtils.getFieldGetMethodName(atts.getQName(i));
			Method method;
			try {
				method = tagetClass.getMethod(methodName, String.class);
				method.invoke(obj, atts.getValue(atts.getQName(i)));
			} catch (Exception e) {
				if (logger.isDebugEnabled()) {
					logger.debug("startElement(String, String, String, Attributes) - *****invoke set method of name :" + methodName + " error!!!****attr nams is: " + atts.getQName(i) + " attr value is :" + atts.getValue(atts.getQName(i))); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				}
			} 
		}
		
		classifyDataToQueue(obj);
	}

	private void classifyDataToQueue(T obj) {
		if (StringUtils.isEmptyStringWithTrim(obj.geProductCategory())) {
			this.emptyBrandQueue.put(obj);
		} else {
			if (brandProductQueue.containsKey(obj.geProductCategory())) {
				brandProductQueue.get(obj.geProductCategory()).add(obj);
			} else {
				List<T> catchList = new ArrayList<T>();
				catchList.add(obj);
				brandProductQueue.put(obj.geProductCategory(), catchList);
			}
		}
	}
	
	
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (logger.isDebugEnabled()) {
			logger.debug("endElement(String, String, String) - =====endElement====="); //$NON-NLS-1$
		}
		
	}

	
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if (logger.isDebugEnabled()) {
			logger.debug("characters(char[], int, int) - =====characters====="); //$NON-NLS-1$
		}
		
	}

	
	public void ignorableWhitespace(char[] ch, int start, int length)
			throws SAXException {
		if (logger.isDebugEnabled()) {
			logger.debug("ignorableWhitespace(char[], int, int) - =====ignorableWhitespace====="); //$NON-NLS-1$
		}
		
	}

	
	public void processingInstruction(String target, String data)
			throws SAXException {
		if (logger.isDebugEnabled()) {
			logger.debug("processingInstruction(String, String) - =====processingInstruction====="); //$NON-NLS-1$
		}
		
	}

	
	public void skippedEntity(String name) throws SAXException {
		if (logger.isDebugEnabled()) {
			logger.debug("skippedEntity(String) - =====skippedEntity====="); //$NON-NLS-1$
		}
		
	}
	
	public void run() {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("run() - **** Process xml file start ****"); //$NON-NLS-1$
			}
			reader.setContentHandler(this);
			reader.parse(source);
			if (logger.isDebugEnabled()) {
				logger.debug("run() - **** Process xml file end ****"); //$NON-NLS-1$
			}
		} catch (IOException e) {
			logger.error("run()", e); //$NON-NLS-1$
		} catch (SAXException e) {
			logger.error("run()", e); //$NON-NLS-1$
		}
		
	}

}
