package com.nort.symc.perfengg.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nort.symc.perfengg.dao.JMeterTestRunsDao;
import com.nort.symc.perfengg.models.JMeterTestRuns;
import com.nort.symc.perfengg.utils.ReadAUrl;
import com.nort.symc.perfengg.utils.ReadXML;


@Controller
public class UpdateJobDetail {

	@Autowired JMeterTestRunsDao jmeterTests;
	
	@RequestMapping("/updateJobDetail")
	@ResponseBody
	public String readJenkinConsole(@RequestParam("buildNumber") String jobNumber) {
		String responseToReturn =  ReadAUrl.readFromUrl("/"+jobNumber+"/consoleText",true);
		return responseToReturn;
		//return null;
	}
	
	@RequestMapping("/getBuildStatus")
	@ResponseBody
	public String getJenkinsBuildStatus(@RequestParam("buildNumber") String jobNumber) {
		System.out.println("Called");
		String responseToReturn =  ReadXML.readXmlUrlByXpath("/"+jobNumber+"/api/xml", "/freeStyleBuild/result",true);
		if(responseToReturn.equalsIgnoreCase("SUCCESS") || responseToReturn.equalsIgnoreCase("FAILURE") || responseToReturn.equalsIgnoreCase("ABORTED")) return responseToReturn;
		if(responseToReturn.equalsIgnoreCase("Retrieve Fail")) return "queued";
		else return "false";
		//return null;
	}
	
	@RequestMapping(value = {"/testStatus","/testResult"})
	public ModelAndView getStarted(@RequestParam("testId") String testId, HttpServletRequest request) {
		String url = request.getRequestURI();
		ModelAndView mv = null;
		List<JMeterTestRuns> runDetail = jmeterTests.runDetails(testId);
		//System.out.println("rundetail : " + runDetail);
		//System.out.println(runDetail.size());
		if(runDetail==null || runDetail.size()==0 || runDetail.isEmpty()) {
			mv = new ModelAndView("errorPage");
			mv.addObject("error", "Invalid test Id");
			return mv;
		}
		//System.out.println("URLLLL : " + url);
		if(url.contains("testStatus")) mv = new ModelAndView("results");
		else mv = new ModelAndView("viewResult");
		mv.addObject("expectedBuild", testId);
		mv.addObject("runDetail", runDetail);
		return mv;
		
	}
}
