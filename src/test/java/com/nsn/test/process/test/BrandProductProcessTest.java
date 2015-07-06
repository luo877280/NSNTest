package com.nsn.test.process.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Before;
import org.junit.Test;

import com.nsn.test.bean.Product;
import com.nsn.test.process.BrandProductProcess;
import com.nsn.test.queue.BrandProductQueue;

public class BrandProductProcessTest {

	BrandProductQueue<Product> brandProductQueue;
	@Before
	public void setUp(){
		System.out.println("setUp resource");
		brandProductQueue = new BrandProductQueue<Product>();
		List<Product> productList = new ArrayList<Product>();
		for(int i=0;i<11;i++){
			Product p = new Product();
			p.setBrand("brand");
			p.setCategory("category"+i);
			p.setId("id"+i);
			p.setModel("modle"+i);
			p.setPrice("price"+i);
			p.setProductID("productID"+i);
			p.setSiteName("siteName"+i);
			p.setTitle("title"+i);
			p.setUrl("url"+i);
			productList.add(p);
		}
		brandProductQueue.put("brand", productList);
	}
	
	@Test
	public void brandProductProcessTest(){
		//ExecutorService for junit test on thread testing
		ExecutorService pool = Executors.newFixedThreadPool(2); 
		
		BrandProductProcess<Product> brandProductProcess = new BrandProductProcess<Product>(brandProductQueue);
		Thread brandProductThread = new Thread(brandProductProcess);
		
		pool.submit(brandProductThread); 
		pool.shutdown();
		while(!pool.isTerminated()); 
		System.out.println("=== Test end ===");
		
	}
	
}
