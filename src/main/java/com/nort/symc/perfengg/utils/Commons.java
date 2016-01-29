package com.nort.symc.perfengg.utils;

import org.apache.commons.lang.RandomStringUtils;

public class Commons {
	
	public static String generateRandomString (int length) {
		
		return RandomStringUtils.randomAlphanumeric(length);
	}

}
