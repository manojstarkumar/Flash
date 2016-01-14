package com.nort.symc.perfengg.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nort.symc.perfengg.dao.JMeterTestRunsDao;
import com.nort.symc.perfengg.models.JMeterTestRuns;

@Controller
public class ListRuns {

	@Autowired JMeterTestRunsDao jmeterTestRuns;
	
	@RequestMapping("/getRuns")
	public ModelAndView getRuns() {
		ModelAndView modal = new ModelAndView("runs");
		List<JMeterTestRuns> jMeterRuns = jmeterTestRuns.list();
		modal.addObject("runs", jMeterRuns);
		return modal;
	}
}
