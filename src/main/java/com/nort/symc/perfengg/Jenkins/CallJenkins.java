package com.nort.symc.perfengg.Jenkins;

import hudson.cli.CLI;

import java.io.File;
import java.net.URL;
import java.security.KeyPair;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.nort.symc.perfengg.utils.Constants;
import com.nort.symc.perfengg.utils.JMXFiles;

@Component
public class CallJenkins {
	
	@Async
	public String runAjob(String script, int users, int duration, String mWebHost, String ssoHost, String parameters, String comment) {
		

		try {
			System.out.println("Starting...");
		CLI cli  = new CLI(new URL(Constants.jenkinsHome));
    	File file = new File("C:\\Users\\MANOJKUMAR_k\\Documents\\Luna Workspace\\JellyLab\\src\\main\\resources\\id_rsa"/*classLoader.getResource("id_rsa").getFile()*/);
        KeyPair key = CLI.loadKey(file);
        cli.authenticate(Collections.singleton(key));

        cli.upgrade();
		
		String jMeterScript = Constants.repoLocation + File.separator +JMXFiles.valueOf(script).getJMXPath();

		
		List<String> arguments = new LinkedList<String>();
		arguments.add("build");
		arguments.add("JMeterExec");

		arguments.add("-p");
		arguments.add("Script="+jMeterScript);
		arguments.add("-p");
		arguments.add("Users="+users);
		arguments.add("-p");
		arguments.add("Duration="+duration);
		arguments.add("-p");
		arguments.add("mWebHost="+mWebHost);
		arguments.add("-p");
		arguments.add("ssoHost="+ssoHost);
				
		if(parameters!=null && parameters.length()>0) {
		String[] parameterList = parameters.split("\n");
		StringBuffer jParams = new StringBuffer();
		for(String param : parameterList) {
			jParams.append("-J"+param.split("=")[0]+"="+param.split("=")[1]+" ");
		}
		arguments.add("-p");
		arguments.add("Parameters="+jParams.toString());
		}
		
		arguments.add("-p");
		arguments.add("Comment="+comment);
		arguments.add("-p");
		arguments.add("TestScript="+script);
		
		System.out.println(arguments);
		
		arguments.add("-s");
		arguments.add("-v");
		
		cli.upgrade();
		System.out.println("exec");

		int exit_code = cli.execute(arguments);

		if(exit_code==0) {
			//can be used if any post build has to be triggered from here
		}
		else
			return null;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

}
