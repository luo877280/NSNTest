package com.nsn.test.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.nsn.test.bean.Product;
import com.nsn.test.process.BrandProcess;
import com.nsn.test.process.BrandProductProcess;
import com.nsn.test.process.ContentHandlerProcess;
import com.nsn.test.process.EmptyBrandProductProcess;
import com.nsn.test.queue.BrandProductQueue;
import com.nsn.test.queue.EmptyBrandProcessQueue;
import com.nsn.test.util.FileUtils;
import com.nsn.test.util.NSNKey;
import com.nsn.test.util.PropertiesUtils;

public class BrandProductManager {
	XMLReader reader;
	
	public void init() throws Exception, SAXException{
		PropertiesUtils.loadByClassPathName("/config.properties");
		SAXParserFactory factory=SAXParserFactory.newInstance();
		SAXParser parser=factory.newSAXParser();
		reader=parser.getXMLReader();
	}
	
	public  void process() throws ParserConfigurationException, Exception {
		String filePath = PropertiesUtils.get(NSNKey.FILE_XML_PATH_KEY);
		FileInputStream in = null;
		if(NSNKey.FILE_XML_TYPE_ABSOLUTELY.equals(PropertiesUtils.get(NSNKey.FILE_XML_TYPE_KEY))){
			File  file = FileUtils.getFileByFullName(filePath);
			in=new FileInputStream(file);
		}else {
			File  file = FileUtils.getFileByClassPath(filePath);
			in=new FileInputStream(file);
		}
	
		
		System.out.println("==filePath==="+filePath);
		BrandProductQueue<Product> brandProductQueue = new BrandProductQueue<Product>();
		EmptyBrandProcessQueue<Product> emptyBrandProcessQueue = new EmptyBrandProcessQueue<Product>();
		
		Thread contentHandlerThread = fileAndEmptyBrandProcess(in,brandProductQueue, emptyBrandProcessQueue);
		contentHandlerThread.join();//waiting finished deal with the xml file
		//after finished deal with all xml file date,  we can start to deal with all information 
		brandAndProductProcess(brandProductQueue);
		
		
		System.out.println(" ... main process end ...");
		
	}

	private Thread fileAndEmptyBrandProcess(FileInputStream in,
			BrandProductQueue<Product> brandProductQueue,
			EmptyBrandProcessQueue<Product> emptyBrandProcessQueue)
			throws FileNotFoundException, UnsupportedEncodingException {
		InputSource source=new InputSource(new InputStreamReader(in,"utf-8"));
		
		ContentHandlerProcess<Product> contentHandlerProcess = new ContentHandlerProcess<Product>(reader, source, emptyBrandProcessQueue, brandProductQueue,Product.class);
		Thread contentHandlerThread = new Thread(contentHandlerProcess);
		
		EmptyBrandProductProcess<Product> emptyBrandProductProcess = new EmptyBrandProductProcess<Product>(emptyBrandProcessQueue);
		Thread emptProcessThread = new Thread(emptyBrandProductProcess);
		
		contentHandlerThread.start();
		emptProcessThread.start();
		return contentHandlerThread;
	}

	private void brandAndProductProcess(BrandProductQueue<Product> brandProductQueue) {
		BrandProductProcess<Product> brandProductProcess = new BrandProductProcess<Product>(brandProductQueue);
		Thread brandProductThread = new Thread(brandProductProcess);
		
		BrandProcess<Product> brandProcess = new BrandProcess<Product>(brandProductQueue);
		Thread brandThread = new Thread(brandProcess);
		
		brandProductThread.start();
		brandThread.start();
	}
}
