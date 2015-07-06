package com.nsn.test.process;

import org.apache.log4j.Logger;

import com.nsn.test.bean.BaseBean;
import com.nsn.test.queue.BrandProductQueue;
import com.nsn.test.util.FileWriterHelper;


public class BrandProcess<T extends BaseBean> implements Runnable {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BrandProcess.class);

	BrandProductQueue<T> brandProductQueue;
	
	public BrandProcess(BrandProductQueue<T> brandProductQueue) {
		this.brandProductQueue = brandProductQueue;
	}

	public void run() {
		logger.info("run() - ***Deal  the data  only  output  brands thread start ***"); //$NON-NLS-1$
		brandProductQueue.outPutBrands();
		FileWriterHelper.CloseBrandFileWriter();
		logger.info("run() - ***Deal  the data  only  output  brands thread end ***"); //$NON-NLS-1$
	}
	
	

}
