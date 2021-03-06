package com.nort.symc.perfengg.controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.nort.symc.perfengg.Jenkins.CallJenkins;
import com.nort.symc.perfengg.actions.FileUpload;
import com.nort.symc.perfengg.dao.JMeterTestRunsDao;
import com.nort.symc.perfengg.models.JMeterTestRuns;
import com.nort.symc.perfengg.utils.Constants;
import com.nort.symc.perfengg.utils.JMXFiles;
import com.nort.symc.perfengg.utils.ReadXML;

@Controller
public class ScriptSubmission {
	
	@Autowired CallJenkins callJenkins;
	@Autowired JMeterTestRunsDao jmeterTestRuns;
	
	@RequestMapping("/runScript")
	public ModelAndView getStarted(@RequestParam("script") String script, @RequestParam("users") int users, @RequestParam("duration") int duration, @RequestParam("mWebHost") String mWebHost, @RequestParam("ssoHost") String ssoHost, @RequestParam("parameters") String parameters, @RequestParam("comment") String comment, @RequestParam(value = "dataFile", required=false) MultipartFile dataFile) {
			
		ModelAndView mv = null; 
		Boolean isBuildQueued = new Boolean(ReadXML.readXmlUrlByXpath("/api/xml", "/freeStyleProject/inQueue",true));
		
		if(isBuildQueued) {
			mv = new ModelAndView("errorPage");
			mv.addObject("error", "queueError");
			return mv;
		}
		
		/* Set all default values just in case something is missed */
		script = script.trim().length()==0 ? "Login" : script;
		mWebHost = mWebHost.trim().length()==0 ? "mweb-int2.norton.com" : mWebHost;
		ssoHost = ssoHost.trim().length()==0 ? "login-int2.norton.com" : ssoHost;
		comment = comment.trim().length()==0 ? "Performance Test" : comment;
		users = users<=0 ? 2 : users;
		duration = duration<=0 ? 10 : duration;
		String paramsToInsert = "mWebHost:"+mWebHost+";ssoHost:"+ssoHost+ (parameters.trim().length()!=0 ? ";otherParams:"+parameters : "");
		
		/*Start File Upload*/
		FileUpload upload = new FileUpload();
		String dataFolder = upload.uploadNewFile(dataFile);
		if(dataFolder.equalsIgnoreCase("Fail")) {
			mv = new ModelAndView("errorPage");
			mv.addObject("error", "uploadError");
			return mv;
		}
		
		/* check if we still have a free slot as concurrent uploads could have been possible*/
		isBuildQueued = new Boolean(ReadXML.readXmlUrlByXpath("/api/xml", "/freeStyleProject/inQueue",true));
		
		if(isBuildQueued) {
			mv = new ModelAndView("errorPage");
			mv.addObject("error", "queueError");
			return mv;
		}
		
		/* If we can still queue the run, get the expected build number*/
		int nextBuild = Integer.parseInt(ReadXML.readXmlUrlByXpath("/api/xml", "/freeStyleProject/nextBuildNumber",true));
		mv = new ModelAndView("results");
		mv.addObject("expectedBuild", nextBuild);
		System.out.println("Making the call");
		
		/* Persist run details and fire the CLI only after the upload completes*/
		JMeterTestRuns jmeterTest = new JMeterTestRuns(nextBuild,comment,users,Constants.defaultJobState,duration,script,paramsToInsert,dataFolder);
		System.out.println(jmeterTest);
		List<JMeterTestRuns> currentRun = new ArrayList<JMeterTestRuns>(); //return as list to reuse same ui that will render the hibernate list query as well.
		currentRun.add(jmeterTest);
		mv.addObject("runDetail", currentRun);
		//JMeterTestRunsKey runKey = new JMeterTestRunsKey(jmeterTest);
		jmeterTestRuns.saveWithDelete(jmeterTest);
		
		/* Fire the CLI */
		callJenkins.runAjob(script,users,duration,mWebHost,ssoHost,parameters,comment,dataFolder);
		System.out.println("returning to view");
		return mv;
		
	}
	
	public static void main(String args[]) {
		String script = "Login";
		String jmxPath = Constants.repoLocation + File.separator +JMXFiles.valueOf(script).getJMXPath();
		System.out.println(jmxPath);
		
	}
	

}
