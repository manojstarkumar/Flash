package com.nort.symc.perfengg.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nort.symc.perfengg.dao.JMeterTestRunsDao;
import com.nort.symc.perfengg.models.JMeterTestRuns;
import com.nort.symc.perfengg.utils.ReadXML;

@Repository
public class JMeterTestDaoImpl implements JMeterTestRunsDao {

	@Autowired SessionFactory sessionFactory;
	
	@Override
	public void saveWithDelete(JMeterTestRuns testRun) {
		Session session = this.sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(testRun); //This is bad. Real BAD!!! The nextBuildId that is obtained in the controller will definitely change if the run is triggered.
		session.flush();		// So for the time being if the CLI fails to fire-up, lets just delete and re-create a new job.
		session.persist(testRun);
		transaction.commit();
		session.close();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<JMeterTestRuns> list() {
		Session session = this.sessionFactory.openSession();
		Query query = session.createQuery("from JMeterTestRuns");
		Criteria criteria = session.createCriteria(JMeterTestRuns.class);
		criteria.addOrder(Order.desc("buildNumber"));
		criteria.setMaxResults(100);
		List<JMeterTestRuns> jMeterRuns = (List<JMeterTestRuns>)criteria.list();
		session.close();
		return jMeterRuns;
	}

	@Override
	public boolean update(String buildNumber) {
		
		try {
		Session session = this.sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		JMeterTestRuns testRun = session.load(JMeterTestRuns.class, Integer.parseInt(buildNumber));
		String responseToReturn =  ReadXML.readXmlUrlByXpath("/"+buildNumber+"/api/xml", "/freeStyleBuild/result",true);
		testRun.setStatus(responseToReturn);
		String result = String.valueOf(session.save(testRun)); //have this as a string, just incase something else has to done from here.
		transaction.commit();
		session.close();
		if(result==null || result.trim().isEmpty()) return false;
		else return true;
		}
		catch(Exception obj) {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<JMeterTestRuns> runDetails(String buildNumber) {
		Session session = this.sessionFactory.openSession();
		Query query = session.createQuery("from JMeterTestRuns where buildNumber=:buildNumber");
		query.setString("buildNumber", buildNumber);
		List<JMeterTestRuns> jMeterRuns = (List<JMeterTestRuns>)query.list();
		session.close();
		return jMeterRuns;
	}

}
