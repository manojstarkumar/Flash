package com.nort.symc.perfengg.Jenkins;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class GenerateCharts {
	
	public static void main(String args[]) throws IOException {
		GenerateCharts jar = new GenerateCharts();
		int exit = 0; /*= jar.anOperation(Constants.jmeterHome+"\\lib\\ext\\CMDRunner.jar",
									Constants.jenkinsPhysical+"\\jobs\\JMeterExec\\builds\\"+buildNumber+"\\archive\\results.jtl",
									"csv","JAgg", "AggregateReport",null);*/
		if(exit!=0) System.exit(-1);
		
		/*List<String> filter = new ArrayList<String>();
		filter.add("HomePage");
		filter.add("Login");*/
		
		exit = jar.cmdRunner("C:\\Users\\MANOJKUMAR_k\\Documents\\Resources\\apache-jmeter-2.13\\lib\\ext\\CMDRunner.jar",
				"C:\\Program Files (x86)\\Jenkins\\jobs\\JMeterExec\\builds\\30\\archive\\results.jtl",
				"png", "JResp", "ResponseTimesOverTime", null);
	}
	
	public int cmdRunner(String jarPath, String jtlPath, String generate, String chartName, String pluginType, List<String> filterList) throws IOException {
		//java -jar ../lib/ext/CMDRunner.jar --tool Reporter --generate-png Hits.png --input-jtl "C:\Program Files (x86)\Jenkins\workspace\Test\results.jtl" --plugin-type HitsPerSecond
		
		List<String> arguments = new ArrayList<String>();
		
		arguments.add("java");
		arguments.add("-jar");
		arguments.add(jarPath);
		arguments.add("--tool");
		arguments.add("Reporter");
		if(generate.equalsIgnoreCase("csv")) {
			arguments.add("--generate-csv");
			arguments.add(chartName+".csv");
		}
		else {
			arguments.add("--generate-png");
			arguments.add(chartName+".png");
		}
		arguments.add("--input-jtl");
		arguments.add(jtlPath);
		arguments.add("--plugin-type");
		arguments.add(pluginType);
		if(filterList!=null) {
			arguments.add("--include-labels");
			arguments.add("\""+customToString(filterList)+"\"");
		}
		
		System.out.println(arguments);
		
		ProcessBuilder pb = new ProcessBuilder(arguments);
		
		
		Process p = pb.start();
		BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line = br.readLine();
		while (line != null) {
            System.out.println(line);
            line = br.readLine();
        }
		br.close();
		
		BufferedReader be = new BufferedReader(new InputStreamReader(p.getErrorStream()));
		line = be.readLine();
		while (line != null) {
            System.out.println(line);
            line = be.readLine();
        }
		be.close();
		
		return p.exitValue();
		
	}

	private String customToString(List<String> orgList) {
		StringBuilder temp = new StringBuilder();
		for(String s : orgList) {
			temp.append(s+",");
		}
		temp.deleteCharAt(temp.length()-1);
		return temp.toString();
	}

}
