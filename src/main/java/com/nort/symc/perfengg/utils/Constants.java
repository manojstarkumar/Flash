package com.nort.symc.perfengg.utils;

public class Constants {
	
	public static final String repoLocation = "C:\\JMXSources";
	/* public static final String jenkinsHome = "http://localhost:8080/";
	 * public static final String jmeterHome = "C:\\Users\\MANOJKUMAR_k\\Documents\\Resources\\apache-jmeter-2.13";
	 * public static final String jenkinsPhysical = "C:\\Program Files (x86)\\Jenkins";
	 * public static final String machineRoot = "http://localhost/";
	 * */
	public static final String jenkinsHome = "http://10.223.19.73:8080/jenkins/";
	public static final String machineRoot = "http://10.223.19.73/apps";
	public static final String jmeterHome = "C:\\apache-jmeter-2.13";
	public static final String jenkinsPhysical = "C:\\Program Files (x86)\\Jenkins";
	
	public static final String jmeterBuildJob = "JMeterExec";

	public static final String reportLocation = "/jmeterResults";
	public static final String defaultJobState = "Running";
	public static final String reportsLink = "/jmeterResults/";
	public static final String jobName = "JMeterExec";
	public static final String rsaKey = "C:\\jenkinsRsa\\id_rsa";
	public static final String defaultDataFolder = "data";
	
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
