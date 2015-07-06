package com.nsn.test.queue.test;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.nsn.test.bean.Product;
import com.nsn.test.queue.BrandProductQueue;
import com.nsn.test.util.FileWriterHelper;



public class BrandProductQueueTest  {
	
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
	public void testOutPutBrands() {
		System.out.println("  ---start OutPut Brands---  ");
		brandProductQueue.outPutBrands();
		FileWriterHelper.CloseBrandFileWriter();
		System.out.println("  ---end OutPut Brands---  ");
	}
	@Test
	public void testOutPutBrandsProduct() {
		System.out.println("  ---start OutPut Brands Product---  ");
		brandProductQueue.outPutBrandsProduct();
		FileWriterHelper.CloseBrandProductFileWriter();
		System.out.println("  ---start OutPut Brands Product---  ");
		
	}

}
