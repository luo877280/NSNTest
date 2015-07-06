package com.nsn.test.queue;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;

import com.nsn.test.bean.BaseBean;
import com.nsn.test.util.FileWriterHelper;
import com.nsn.test.util.NSNKey;
import com.nsn.test.util.StringUtils;



public class EmptyBrandProcessQueue<T extends BaseBean> {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(EmptyBrandProcessQueue.class);
	
	Set<T> emptyBrandProductSet = new HashSet<T> ();
	boolean finishedReadXML = false;
	
	
	public  synchronized void put(T product) {
		if(emptyBrandProductSet.size()>NSNKey.QUEUE_POOL_MAX_SIZE){
	        try { 
				if (logger.isDebugEnabled()) {
					logger.debug("put(T) - <<<Waiting to deal with data>>>"); //$NON-NLS-1$
				} 
	            wait(); 
	        } 
	        catch (Exception ex) { 
	        } 
		}
		if (logger.isDebugEnabled()) {
			logger.debug("put(T) - <<Add data to empty brand queue>>>" + product); //$NON-NLS-1$
		} 
		emptyBrandProductSet.add(product);
		notifyAll();
	}

	public void displayEmptyBrandProduct() {
		T product = takeOut();
		if (logger.isDebugEnabled()) {
			logger.debug("outPutEmptyBrandProduct() - <<<OutPut Empty Brand Product start >>>" + product); //$NON-NLS-1$
		}
		if(product != null&&!StringUtils.isEmptyStringWithTrim(product.getOutputFiled())){
			if (logger.isDebugEnabled()) {
				logger.debug("displayEmptyBrandProduct() - <<<out Put Empty Brand Product content is >>>" + product.getOutputFiled()); //$NON-NLS-1$
			}
		}
	}
	public void outPutEmptyBrandProduct() {
		T product = takeOut();
		if (logger.isDebugEnabled()) {
			logger.debug("outPutEmptyBrandProduct() - <<<OutPut Empty Brand Product start >>>" + product); //$NON-NLS-1$
		}
		
		if(product != null&&!StringUtils.isEmptyStringWithTrim(product.getOutputFiled())){
			FileWriterHelper.outputEmptyBrandFile(product.getOutputFiled()+"\r\n");
			if (logger.isDebugEnabled()) {
				logger.debug("outPutEmptyBrandProduct() - <<< OutPut Empty Brand Product content end>>>" + product.getOutputFiled()); //$NON-NLS-1$
			}
		}
	}
	
	/**
	 * if the queue have data, the processor could deal with them until the finished read the xml file 
	 * @return
	 */
	public synchronized T takeOut() {
		if (emptyBrandProductSet.size() <= 0) {
			try {
				if (logger.isDebugEnabled()) {
					logger.debug("takeOut() - <<<Waiting to produce data...>>>"); //$NON-NLS-1$
				}
				wait();
			} catch (Exception ex) {
				logger.error("takeOut()", ex); //$NON-NLS-1$
			}
		}
		Iterator<T> productIterator = emptyBrandProductSet.iterator();
		if (productIterator.hasNext()) {
			T p = productIterator.next();
			if (logger.isDebugEnabled()) {
				logger.debug("takeOut() - <<<Deal with empty brand product :>>>" + p); //$NON-NLS-1$
			}
			emptyBrandProductSet.remove(p);
			notifyAll();
			return p;
		}else{
			if (logger.isDebugEnabled()) {
				logger.debug("takeOut() - <<<Woke up because end of document!>>>"); //$NON-NLS-1$
			}
			return null;
		}
	}
	
	public synchronized int size(){
		return emptyBrandProductSet.size();
	}

	public boolean isFinishedReadXML() {
		return finishedReadXML;
	}

	/**
	 * when the xml file read finish,we need let the waiting thread weak up. 
	 * (if the processor is waiting,the xml file finished read,this thread will always on waiting status if not notify them)
	 */
	public synchronized void endEmptyBrandProcess(){
		this.finishedReadXML = true;
		notifyAll();
	}

}
