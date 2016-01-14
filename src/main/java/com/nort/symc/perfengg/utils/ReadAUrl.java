package com.nort.symc.perfengg.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ReadAUrl {
	
	public static String readFromUrl(String url, boolean isrelativeJenkinsUrl) {
		try {
			if(isrelativeJenkinsUrl) {
				url = Constants.jenkinsHome+"job/"+Constants.jmeterBuildJob+url;
			}
			
			System.out.println("URL is  : "+ url);
			
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	 
			// optional default is GET
			con.setRequestMethod("GET");
	 
			//add request header
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
	 
			int responseCode = con.getResponseCode();
			if(responseCode==200) {
	 
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	 
			while ((inputLine = in.readLine()) != null) {
				response.append("\r\n\r"+inputLine);
			}
			in.close();
	 
			//print result
			return response.toString();

			}
			else throw new Exceptions("Url Not Found");
		}
		catch(Exception e) {
			//notify the front-end
			return null;
		}
		
	}

	public static void main(String args[]) {
		readFromUrl("/"+"21"+"/consoleText",true);
	}
}
