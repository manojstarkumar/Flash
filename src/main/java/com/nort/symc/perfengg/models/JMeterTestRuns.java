package com.nort.symc.perfengg.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*@Embeddable*/
@Entity
@Table(name="jmetertestRuns")
public class JMeterTestRuns implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6400692564449759872L;
	
	@Id
	public int buildNumber;
	
	public String comment;
	public int users;
	public String status;
	public int duration;
	public String test;
	public String parameters;
	
	public JMeterTestRuns(){
		
	}
	public JMeterTestRuns(int nextBuild, String comment, int users,
			String defaultjobstate, int duration, String script,
			String parameters) {
		this.buildNumber = nextBuild;
		this.comment = comment;
		this.users = users;
		this.status = defaultjobstate;
		this.duration = duration;
		this.test = script;
		this.parameters = parameters;
		
	}
	public int getBuildNumber() {
		return buildNumber;
	}
	public void setBuildNumber(int buildNumber) {
		this.buildNumber = buildNumber;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getUsers() {
		return users;
	}
	public void setUsers(int users) {
		this.users = users;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
	public String getParameters() {
		return parameters;
	}
	public void setParameters(String parameters) {
		this.parameters = parameters;
	}
	
	@Override
	public String toString(){
		return this.comment+"::"+this.test+"::"+this.users+"::"+this.duration;
	}

}
