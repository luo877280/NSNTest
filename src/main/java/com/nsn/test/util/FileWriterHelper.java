package com.nsn.test.util;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterHelper {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(FileWriterHelper.class);

	public static FileWriter emptyBrandFileWriter;
	public static FileWriter brandFileWriter;
	public static FileWriter brandProductFileWriter;
	
	
	public static  void outputbrandProductFile(String data) {
		if(brandProductFileWriter ==null){
			File outputFile = new File(PropertiesUtils.get(NSNKey.FILE_OUT_PATH_BRANDS_PRODUCT));
			if(!outputFile.getParentFile().exists()){
				outputFile.getParentFile().mkdirs();
			}
			try {
				brandProductFileWriter = new FileWriter(outputFile);
				brandProductFileWriter.write(data);
			} catch (IOException e) {
				logger.error("outputbrandProductFile(String)", e); //$NON-NLS-1$
			}
		}else{
			try {
				brandProductFileWriter.write(data);
			} catch (IOException e) {
				logger.error("outputbrandProductFile(String)", e); //$NON-NLS-1$
			}
		}
	}
	
	public static void CloseBrandProductFileWriter() {
		if(brandProductFileWriter!=null){
			try {
				brandProductFileWriter.close();
				logger.info("CloseBrandProductFileWriter() - brand Product FileWriter have been closed!!"); //$NON-NLS-1$
			} catch (IOException e) {
				logger.error("CloseBrandProductFileWriter()", e); //$NON-NLS-1$
			}
		}
	}
	
	public static  void outputBrandFile(String data) {
		if(brandFileWriter ==null){
			File outputFile = new File(PropertiesUtils.get(NSNKey.FILE_OUT_PATH_BRANDS));
			if(!outputFile.getParentFile().exists()){
				outputFile.getParentFile().mkdirs();
			}
			try {
				brandFileWriter = new FileWriter(outputFile);
				brandFileWriter.write(data);
			} catch (IOException e) {
				logger.error("outputBrandFile(String)", e); //$NON-NLS-1$
			}
		}else{
			try {
				brandFileWriter.write(data);
				
			} catch (IOException e) {
				logger.error("outputBrandFile(String)", e); //$NON-NLS-1$
			}
		}
	}
	public static void CloseBrandFileWriter() {
		if(brandFileWriter!=null){
			try {
				brandFileWriter.close();
				logger.info("CloseBrandFileWriter() - brand FileWriter have been closed!!"); //$NON-NLS-1$
			} catch (IOException e) {
				logger.error("CloseBrandFileWriter()", e); //$NON-NLS-1$
			}
		}
	}
	
	public static  void outputEmptyBrandFile(String data) {
		if(emptyBrandFileWriter ==null){
			File outputFile = new File(PropertiesUtils.get(NSNKey.FILE_OUT_PATH_BRANDS_EMPTY));
			if(!outputFile.getParentFile().exists()){
				outputFile.getParentFile().mkdirs();
			}
			try {
				emptyBrandFileWriter = new FileWriter(outputFile);
				emptyBrandFileWriter.write(data);
			} catch (IOException e) {
				logger.error("outputEmptyBrandFile(String)", e); //$NON-NLS-1$
			}
			
		}else{
			try {
				emptyBrandFileWriter.write(data);
			} catch (IOException e) {
				logger.error("outputEmptyBrandFile(String)", e); //$NON-NLS-1$
			}
		}
	}
	
	public   static void CloseEmptyBrandFileWriter() {
		if(emptyBrandFileWriter!=null){
			try {
				emptyBrandFileWriter.close();
				logger.info("CloseEmptyBrandFileWriter() - Empty brand Product FileWriter have been closed!!"); //$NON-NLS-1$
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error("CloseEmptyBrandFileWriter()", e); //$NON-NLS-1$
			}
		}
	}
}
