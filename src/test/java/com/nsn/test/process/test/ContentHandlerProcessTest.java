package com.nsn.test.process.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.nsn.test.bean.Product;
import com.nsn.test.process.ContentHandlerProcess;
import com.nsn.test.queue.BrandProductQueue;
import com.nsn.test.queue.EmptyBrandProcessQueue;
import com.nsn.test.util.FileUtils;
import com.nsn.test.util.NSNKey;
import com.nsn.test.util.PropertiesUtils;

public class ContentHandlerProcessTest {
	BrandProductQueue<Product> brandProductQueue;
	EmptyBrandProcessQueue<Product> emptyBrandProcessQueue;
	XMLReader reader;
	
	public void init() throws Exception, SAXException{
		PropertiesUtils.loadByClassPathName("/config.properties");
		SAXParserFactory factory=SAXParserFactory.newInstance();
		SAXParser parser=factory.newSAXParser();
		reader=parser.getXMLReader();
	}
	@Before
	public void setUp() throws Exception{
		System.out.println("setUp resource");
		init();
		brandProductQueue = new BrandProductQueue<Product>();
		emptyBrandProcessQueue = new EmptyBrandProcessQueue<Product>();
	}
	
	@Test
	public void xmlFileReadTest() throws Exception{
		ExecutorService pool = Executors.newFixedThreadPool(2);

		String filePath = PropertiesUtils.get(NSNKey.FILE_XML_PATH_KEY);
		FileInputStream in = null;
		if (NSNKey.FILE_XML_TYPE_ABSOLUTELY.equals(PropertiesUtils.get(NSNKey.FILE_XML_TYPE_KEY))) {
			File file = FileUtils.getFileByFullName(filePath);
			in = new FileInputStream(file);
		} else {
			File file = FileUtils.getFileByClassPath(filePath);
			in = new FileInputStream(file);
		}
		InputSource source=new InputSource(new InputStreamReader(in,"utf-8"));
		ContentHandlerProcess<Product> contentHandlerProcess = new ContentHandlerProcess<Product>(reader, source, emptyBrandProcessQueue, brandProductQueue,Product.class);
		Thread contentHandlerThread = new Thread(contentHandlerProcess);
		pool.submit(contentHandlerThread);
		pool.shutdown();
		while(!pool.isTerminated()); 
		validateResult();
	}
	public void validateResult(){
		brandProductQueue.displayPutBrands();
		brandProductQueue.displayPutBrandsProduct();
		while(emptyBrandProcessQueue.size()>0){
			emptyBrandProcessQueue.displayEmptyBrandProduct();
		}
	}
	
}
