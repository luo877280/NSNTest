package com.nsn.test.process;

import org.apache.log4j.Logger;

import com.nsn.test.bean.BaseBean;
import com.nsn.test.queue.BrandProductQueue;
import com.nsn.test.util.FileWriterHelper;


public class BrandProductProcess<T   extends BaseBean> implements Runnable {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BrandProductProcess.class);

	BrandProductQueue<T> brandProductQueue;
	
	public BrandProductProcess(BrandProductQueue<T> brandProductQueue) {
		this.brandProductQueue = brandProductQueue;
	}

	public void run() {
		logger.info("run() - ***Deal  the data  output Brands Product detail thread start ***"); //$NON-NLS-1$
		//brandProductQueue.displayPutBrandsProduct();
		brandProductQueue.outPutBrandsProduct();
		FileWriterHelper.CloseBrandProductFileWriter();
		logger.info("run() - ***Deal  the data  output Brands Product detail thread end ***"); //$NON-NLS-1$
	}
}
