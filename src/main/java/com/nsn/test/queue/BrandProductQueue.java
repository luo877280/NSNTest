package com.nsn.test.queue;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nsn.test.bean.BaseBean;
import com.nsn.test.util.FileWriterHelper;
import com.nsn.test.util.NSNKey;



public class BrandProductQueue<T  extends BaseBean> {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BrandProductQueue.class);
	/**
	 * the queue of the all product with brand data.
	 */
	Map<String,List<T>> map = new HashMap<String,List<T>>();
	
	
	public void displayPutBrands() {
		for(String dataKey : map.keySet())   { 
			if(dataKey!=null&&!"".equals(dataKey)){
				if (logger.isDebugEnabled()) {
					logger.debug("displayPutBrands() - <<<  out put brand data is  >>>:" + dataKey); //$NON-NLS-1$
				}
			}
		}
	}
	public void displayPutBrandsProduct() {
		Object[] key_arr = map.keySet().toArray();
		Arrays.sort(key_arr);     
		for  (Object key : key_arr) {
			if (logger.isDebugEnabled()) {
				logger.debug("displayPutBrandsProduct() - <<<  out put brands product's brand data is  >>>:" + key); //$NON-NLS-1$
			}
			List<T> productList = map.get(key);
			if(key!=null&&!"".equals(key)){
				for(int i = 0;i<productList.size();i++){
					if (logger.isDebugEnabled()) {
						logger.debug("displayPutBrandsProduct() - <<<  out put brands product data is  >>>:" + productList.get(i)); //$NON-NLS-1$
					}
				}
			}
		}
	}
	public void outPutBrands(){
		if (logger.isDebugEnabled()) {
			logger.debug("outPutBrands() - <<< Start out put brands data to file! >>>"); //$NON-NLS-1$
		}
		for(String dataKey : map.keySet())   {
			if(dataKey!=null&&!"".equals(dataKey)){
				if (logger.isDebugEnabled()) {
					logger.debug("outPutBrands() - <<< Start out put brands data is >>>"+dataKey); //$NON-NLS-1$
				}
				FileWriterHelper.outputBrandFile(dataKey+"\r\n");
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("outPutBrands() - <<< Start out put brands data to file end! >>>"); //$NON-NLS-1$
		}
	}
	public void outPutBrandsProduct(){
		if (logger.isDebugEnabled()) {
			logger.debug("outPutBrandsProduct() - <<<  out put product with brands data to file start! >>>"); //$NON-NLS-1$
		}
		Object[] key_arr = map.keySet().toArray();
		Arrays.sort(key_arr);
		for  (Object key : key_arr) {
			if (logger.isDebugEnabled()) {
				logger.debug("outPutBrandsProduct() - <<< out put product with brands data of bran  >>>" + key); //$NON-NLS-1$
			}
			FileWriterHelper.outputbrandProductFile(key+"\r\n");
			List<T> productList = map.get(key);
			if(key!=null&&!"".equals(key)){
				generateProductWithSite(productList);
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("outPutBrandsProduct() - <<<  out put product with brands data to file end ! >>>"); //$NON-NLS-1$
		}
	}

	private void generateProductWithSite(List<T> productList) {
		Map<String,List<T>> tempmap = new HashMap<String,List<T>>();
		for(int i = 0;i<productList.size();i++){
			String source=productList.get(i).getSource()!=null?productList.get(i).getSource():"";
			if(tempmap.containsKey(source)){
				tempmap.get(source).add(productList.get(i));
			}else{
				List<T> list = new ArrayList<T>();
				list.add(productList.get(i));
				tempmap.put(source, list);
			}
		}
		for(Object site : tempmap.keySet()){
			if (logger.isDebugEnabled()) {
				logger.debug("generateProductWithSite(List<T>) - <<< out put product with brands product data of site name  >>>" + site); //$NON-NLS-1$
			}
			FileWriterHelper.outputbrandProductFile(NSNKey.BRAND_SITE_PLACEHOLDER+site+"\r\n");
			List<T> tempproductList = tempmap.get(site);
			for(int j = 0;j<tempmap.get(site).size();j++){
				if (logger.isDebugEnabled()) {
					logger.debug("generateProductWithSite(List<T>) - <<< out put product with brands product data of product  >>>" + tempproductList.get(j).getOutputFiled()); //$NON-NLS-1$
				}
				FileWriterHelper.outputbrandProductFile(NSNKey.BRAND_PRODUCT_PLACEHOLDER+tempproductList.get(j).getOutputFiled()+"\r\n");
			}
		}
	}
	
	@SuppressWarnings("unused")
	private void generateProduct(Object key, List<T> productList) {
		for(int i = 0;i<productList.size();i++){
			if (logger.isDebugEnabled()) {
				logger.debug("generateProduct(Object, List<T>) - <<< out put product with brands product data of bran >>>" + productList.get(i).getOutputFiled()); //$NON-NLS-1$
			}
			FileWriterHelper.outputbrandProductFile(NSNKey.BRAND_PRODUCT_PLACEHOLDER+productList.get(i).getOutputFiled()+"\r\n");
		}
	}
	
	public boolean containsKey(String key){
		return map.containsKey(key);
	}
	
	public List<T> get(String key){
		return map.get(key);
	}
	
	public void put(String key, List<T> value){
		map.put(key, value);
	}
	
}
