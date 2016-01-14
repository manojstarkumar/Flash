package com.nort.symc.perfengg.Jenkins;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



import com.google.common.io.Files;
import com.nort.symc.perfengg.utils.Constants;

@Controller
public class JenkinsPostProcess {
	
	@Autowired GenerateCharts charts;
	
@RequestMapping("/generateReports")
@ResponseBody
public String generateReports(String buildNumber) {
	
	try {
		String cmdRunnerJar = Constants.jmeterHome+"\\lib\\ext\\CMDRunner.jar";
		String jtlFile = Constants.jenkinsPhysical+"\\jobs\\JMeterExec\\builds\\"+buildNumber+"\\archive\\results.jtl";
		String saveLocation = Constants.jenkinsPhysical+"\\jobs\\JMeterExec\\builds\\"+buildNumber+"\\archive\\";
		File sourceIndex = new File(Constants.jenkinsPhysical+"\\index.html");
		File destinatinIndex = new File(saveLocation+"index.html");
		
		charts.cmdRunner(cmdRunnerJar,jtlFile,"csv",saveLocation+"JAgg", "AggregateReport",null);
		charts.cmdRunner(cmdRunnerJar,jtlFile,"png",saveLocation+"JBytes", "BytesThroughputOverTime",null);
		charts.cmdRunner(cmdRunnerJar,jtlFile,"png",saveLocation+"JHits", "HitsPerSecond",null);
		charts.cmdRunner(cmdRunnerJar,jtlFile,"png",saveLocation+"JResponse", "ResponseTimesOverTime",null);
		charts.cmdRunner(cmdRunnerJar,jtlFile,"png",saveLocation+"JThroughput", "ThroughputVsThreads",null);
		charts.cmdRunner(cmdRunnerJar,jtlFile,"png",saveLocation+"JTimes", "TimesVsThreads",null);
		charts.cmdRunner(cmdRunnerJar,jtlFile,"png",saveLocation+"JTransactions", "TransactionsPerSecond",null);
		
		System.out.println("File copy initiated...");
		System.out.println("SRC : "+sourceIndex.toString());
		System.out.println("Dest : "+destinatinIndex.toString());
		Files.copy(sourceIndex, destinatinIndex);
		
		return Constants.reportLocation+"/"+buildNumber+"/archive";
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("OH... NO. AN Exception");
		return "-1";
	}
}

public static void main(String args[]) {
	String saveLocation = Constants.jenkinsPhysical+"\\jobs\\JMeterExec\\builds\\22\\archive\\";
	File sourceIndex = new File(Constants.jenkinsPhysical+"\\index.html");
	File destinatinIndex = new File(saveLocation+"index.html");
	
	try {
		Files.copy(sourceIndex, destinatinIndex);
		System.out.println("copied");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

}
