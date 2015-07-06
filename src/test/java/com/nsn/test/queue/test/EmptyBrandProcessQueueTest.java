package com.nsn.test.queue.test;

import org.junit.Before;
import org.junit.Test;

import com.nsn.test.bean.Product;
import com.nsn.test.queue.EmptyBrandProcessQueue;
import com.nsn.test.util.FileWriterHelper;

public class EmptyBrandProcessQueueTest {
	EmptyBrandProcessQueue<Product> emptyBrandProcessQueue;
	@Before
	public void setUp(){
		System.out.println("setUp resource");
		emptyBrandProcessQueue = new EmptyBrandProcessQueue<Product>();
		for(int i=0;i<11;i++){
			Product p = new Product();
			p.setBrand("");
			p.setCategory("category"+i);
			p.setId("id"+i);
			p.setModel("modle"+i);
			p.setPrice("price"+i);
			p.setProductID("productID"+i);
			p.setSiteName("siteName"+i);
			p.setTitle("title"+i);
			p.setUrl("url"+i);
			emptyBrandProcessQueue.put( p);
		}
		
	}
	
	@Test
	public void TestOutPutEmptyBrandProduct(){
		while(emptyBrandProcessQueue.size()>0){
			emptyBrandProcessQueue.outPutEmptyBrandProduct();
		}
		FileWriterHelper.CloseEmptyBrandFileWriter();
	}
	
}
