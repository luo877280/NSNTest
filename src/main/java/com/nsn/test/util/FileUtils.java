package com.nsn.test.util;

import java.io.File;

public class FileUtils {
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(getFileByClassPath("config.properties"));

	}

	/**
	 * @param fullFileName
	 * @return File
	 */
	public static File getFileByFullName(String fullFileName) {
		File file = new File(fullFileName);
		return file;
	}
	/** start with'/' will be root of the classpath; else will be the same position of FileUtils.java(same package)
	 * @param fileName
	 * @return
	 */
	public static File getFileByClassPath(String fileName) {
		String fullFileName = FileUtils.class.getResource(fileName).getFile();
		File file = new File(fullFileName);
		return file;
	}
	

}
