package com.nsn.test.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * function:Get properties file attributes value author：luolei date：2014/8/31
 */
public class PropertiesUtils {

	private static Properties pros = null;

	/**
	 * Loading files to Properties
	 * @param fullFileName
	 */
	public static void loadByFullFileName(String fullFileName) {

		File pFile = new File(fullFileName);
		FileInputStream pInStream = null;
		try {
			pInStream = new FileInputStream(pFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		pros = new Properties();
		try {
			pros.load(pInStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Loading files to Properties
	 * @param fullFileName
	 */
	public static void loadByClassPathName(String flieName) {
		
		File file = FileUtils.getFileByClassPath(flieName);
		FileInputStream pInStream = null;
		try {
			pInStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		pros = new Properties();
		try {
			pros.load(pInStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Through the attribute name to get attribute values
	 * @param key
	 * @return
	 */
	public static String get(String key) {
		return pros.getProperty(key);
	}
}