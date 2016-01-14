package com.nort.symc.perfengg.dao;

import java.util.List;

import com.nort.symc.perfengg.models.JMeterTestRuns;

public interface JMeterTestRunsDao {
	
	public void saveWithDelete(JMeterTestRuns jmeterTest);
	public List<JMeterTestRuns> list();
	public boolean update(String buildNumber);
	public List<JMeterTestRuns> runDetails(String buildNumber);

}
