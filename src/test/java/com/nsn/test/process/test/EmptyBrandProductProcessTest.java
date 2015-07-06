package com.nsn.test.process.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Before;
import org.junit.Test;

import com.nsn.test.bean.Product;
import com.nsn.test.process.EmptyBrandProductProcess;
import com.nsn.test.queue.EmptyBrandProcessQueue;

public class EmptyBrandProductProcessTest {
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
	public void brandProductProcessTest() throws Exception{
		//ExecutorService for junit test on thread testing
		ExecutorService pool = Executors.newFixedThreadPool(2); 
		
		EmptyBrandProductProcess<Product> emptyBrandProductProcess = new EmptyBrandProductProcess<Product>(emptyBrandProcessQueue);
		Thread emptProcessThread = new Thread(emptyBrandProductProcess);
		
		pool.submit(emptProcessThread);
		//------------ writing the produce finished end then end this thread---------------------
		Thread.sleep(2000);
		emptyBrandProcessQueue.endEmptyBrandProcess();
		//---------------------------------
		
		pool.shutdown();
		while(!pool.isTerminated()); 
		System.out.println("=== Test end ===");
		
	}
	
}
