package com.nsn.test.util;

public class StringUtils {
	
	/**
	 * @param str
	 * @return is empty or not 
	 */
	public static boolean isEmptyString(String str) {
		if (str == null || "".equals(str)) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * @param str
	 * @return is empty  or not
	 */
	public static boolean isEmptyStringWithTrim(String str) {
		if (str == null || "".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}
	public static String getFieldGetMethodName(String fildeName){
        if(fildeName!=null && !"".equals(fildeName.trim())){
        	fildeName  = fildeName.substring(0,1).toUpperCase()+fildeName.substring(1);
        }
        return fildeName;
	}
}
