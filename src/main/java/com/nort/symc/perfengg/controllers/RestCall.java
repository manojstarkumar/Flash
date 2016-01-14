package com.nort.symc.perfengg.controllers;

import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nort.symc.perfengg.dao.JMeterTestRunsDao;

@Controller
@RequestMapping("/rest")
public class RestCall {

	@Autowired JMeterTestRunsDao jmeterTestRuns;
	
	@RequestMapping("/updateTest")
	@Produces("application/json")
	public ModelAndView updateTest(@RequestParam String buildNumber) throws InterruptedException {
		Thread.sleep(10000);
		ModelAndView model = new ModelAndView("jsonOut");
		StringBuilder json = new StringBuilder();
		json.append("{");
		if(buildNumber.trim().isEmpty()) {
			json.append("\"result\": \"Error\",");
			json.append("\"reason\": \"Null or Empty testId\"");
		}
		else {
			Boolean result = jmeterTestRuns.update(buildNumber);
			if(result) json.append("\"result\": \"Success\"");
			else {
				json.append("\"result\": \"Error\",");
				json.append("\"reason\": \"Update DB Failed\"");
			}
		}
		json.append("}");
		model.addObject("json", json);
		return model;
	}
}
