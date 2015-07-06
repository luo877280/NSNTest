package com.nsn.test.process;

import org.apache.log4j.Logger;

import com.nsn.test.bean.BaseBean;
import com.nsn.test.queue.EmptyBrandProcessQueue;
import com.nsn.test.util.FileWriterHelper;


public class EmptyBrandProductProcess<T extends BaseBean> implements Runnable {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(EmptyBrandProductProcess.class);

	EmptyBrandProcessQueue<T> emptyBrandQueue;
	
	public EmptyBrandProductProcess(EmptyBrandProcessQueue<T> emptyBrandQueue) {
		this.emptyBrandQueue = emptyBrandQueue;
	}

	
	public void run() {
			logger.info("run() - ***deal with the empty brand data thread start***"); //$NON-NLS-1$
		while((!emptyBrandQueue.isFinishedReadXML())||emptyBrandQueue.isFinishedReadXML()&&emptyBrandQueue.size() > 0){
				
			emptyBrandQueue.outPutEmptyBrandProduct();
				
		}
		FileWriterHelper.CloseEmptyBrandFileWriter();
			logger.info("run() - ***deal with the empty brand data thread end***"); //$NON-NLS-1$
		
	}
	
	

}
