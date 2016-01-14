package com.nort.symc.perfengg.utils;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class ReadXML {
	
	public static String readXmlUrlByXpath(String url, String xpath, boolean isrelativeJenkinsUrl) {
		
		try {
			if(isrelativeJenkinsUrl) {
				url = Constants.jenkinsHome+"job/"+Constants.jmeterBuildJob+url;
			}
			//System.out.println("URL is " + url);
	         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder;

	         dBuilder = dbFactory.newDocumentBuilder();

	         Document doc = dBuilder.parse(new InputSource(new URL(url).openStream()));
	         doc.getDocumentElement().normalize();

	         XPath xPath =  XPathFactory.newInstance().newXPath();	
	         NodeList nodeList = (NodeList) xPath.compile(xpath).evaluate(doc, XPathConstants.NODESET);

        	return nodeList.item(0).getTextContent();
	         
	      }
		
		catch(NullPointerException np) {
			return "Not Present";
		}
		
			catch (Exception e) {
	         e.printStackTrace();
	         return "Retrieve Fail";
	      } 
		
		//return null;
	}
	
	public static void main(String args[]) {
		String nextBuild = ReadXML.readXmlUrlByXpath("/apsi/xml", "/freeStyleProject/inQueue",true);
		System.out.println(nextBuild);
	}

}
