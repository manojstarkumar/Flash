package com.nort.symc.perfengg.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nort.symc.perfengg.utils.ReadXML;

@Controller
public class TestMain {
	
	@RequestMapping("/Main")
	public ModelAndView callSomething() {
		ModelAndView mv = new ModelAndView("testMain");
		
		String nextBuild = ReadXML.readXmlUrlByXpath("/lastBuild/api/xml", "/freeStyleBuild/number",true);
		System.out.println("Expecting :: "+nextBuild);
		mv.addObject("expectedBuild", nextBuild);
		//CallJenkins.runAjob("dummy");
		return mv;
	}
	
	public static void main(String args[]) {
		System.out.println(ReadXML.readXmlUrlByXpath("/lastBuild/api/xml", "/freeStyleBuild/result",true));
		System.out.println(ReadXML.readXmlUrlByXpath("http://localhost:8080/job/JMeterExec/lastBuild/api/xml", "/freeStyleBuild/number",false));
	}

}
