package com.core;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class AtNavigation {
	public void atNavigationPage(){
		try{
			WebElement headerElement;
			String project = AtLaunch.projectName;
			File xmlFile = null;
			if(project.toLowerCase().equals("airtxt")){
				xmlFile = new File("testCases/airtxt/AtNavigation.xml");
			}else if(project.toLowerCase().equals("mreach")){
				xmlFile = new File("testCases/mreach/AtNavigation.xml");
			}
			DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dFactory.newDocumentBuilder();
			org.w3c.dom.Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			String url = null;
			NodeList list = doc.getElementsByTagName("navigation");
			ArrayList<String> urlList = new ArrayList<String>();
			
			for(int i = 0; i < list.getLength(); i++){
				Node node = list.item(i);
				Element element = (Element) node;
				url = element.getTextContent();
				urlList.add(url);
			}
			
			for(int j = 0; j < urlList.size(); j++){
				AtLogin.driver.get(urlList.get(j));
				URL	homeUrl = new URL(AtLogin.driver.getCurrentUrl());
				Thread.sleep(2000);
				HttpURLConnection connection = (HttpURLConnection)homeUrl.openConnection();
				connection = (HttpURLConnection)homeUrl.openConnection();
				connection.setRequestMethod("GET");
				connection.connect();
				if(urlList.get(j).contains("groupEditView")){
					headerElement = AtLogin.driver.findElement(By.xpath("//*[@id='table']/h2"));
				}else{
					headerElement = AtLogin.driver.findElement(By.xpath("//*[@id='content']/h2"));
				}
				System.out.println("Status code of "+headerElement.getText()+": "+connection.getResponseCode());
				System.out.println("******************************************************************");
				AtLaunch.bw.write("Status code of "+headerElement.getText()+": "+connection.getResponseCode());
				AtLaunch.bw.newLine();
				AtLaunch.bw.write("******************************************************************");
				AtLaunch.bw.newLine();
				Thread.sleep(2000);
			}
			
			if(!Boolean.valueOf(AtLaunch.groupMsg) && !Boolean.valueOf(AtLaunch.uploadMobile) && !Boolean.valueOf(AtLaunch.uploadMM) && !Boolean.valueOf(AtLaunch.templateMsg) && !Boolean.valueOf(AtLaunch.logout) && !Boolean.valueOf(AtLaunch.quickMsg) && !Boolean.valueOf(AtLaunch.login) && !Boolean.valueOf(AtLaunch.logout)){
				System.out.println("Success");
				AtLaunch.bw.close();
			}
			
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
}
