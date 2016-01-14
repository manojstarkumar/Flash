package com.nort.symc.perfengg.utils;

public enum JMXFiles {
		
	Login("Login.jmx"),
	BATCases("PageHits.jmx"),
	Devices("Devices.jmx"),
	CreateAccount("Register.jmx"),
	RemoveDevice("RemoveDevice.jmx"),
	Download("Download.jmx");
	
	public String jxmFile;
	
	JMXFiles(String script) {
		this.jxmFile = script;
	}
	
	public String getJMXPath() {
		return jxmFile;
	}
	
		
}
