package com.nort.symc.perfengg.utils;

import java.util.Arrays;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		List<JMXFiles> files = Arrays.asList(JMXFiles.values());
		for(JMXFiles f : files)
		System.out.println(f);

	}

}
