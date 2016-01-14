package com.nort.symc.perfengg.utils;

public class Constants {
	
	public static final String repoLocation = "C:\\Users\\MANOJKUMAR_k\\Documents\\JMXSources";
	public static final String jenkinsHome = "http://localhost:8080/";
	public static final String jmeterBuildJob = "JMeterExec";
	public static final String jmeterHome = "C:\\Users\\MANOJKUMAR_k\\Documents\\Resources\\apache-jmeter-2.13";
	public static final String jenkinsPhysical = "C:\\Program Files (x86)\\Jenkins";
	public static final String reportLocation = "http://localhost:7070/jmeterResults";
	public static final String defaultJobState = "Running";
	public static final String reportsLink = "//localhost:7070/jmeterResults/";
	
	public static String getRepolocation() {
		return repoLocation;
	}
	public static String getJenkinshome() {
		return jenkinsHome;
	}
	public static String getJmeterbuildjob() {
		return jmeterBuildJob;
	}
	public static String getJmeterhome() {
		return jmeterHome;
	}
	public static String getJenkinsphysical() {
		return jenkinsPhysical;
	}
	public static String getReportlocation() {
		return reportLocation;
	}
	public static String getDefaultjobstate() {
		return defaultJobState;
	}
	

}
