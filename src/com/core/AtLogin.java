package com.core;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class AtLogin {
	public static WebDriver driver;
	public void atLoginPage(){
		try{
			WebElement linkelement,userElement,passwordElement,submitElement, headerElement;
			String project = AtLaunch.projectName;
			File xmlFile = null;
			if(project.toLowerCase().equals("airtxt")){
				xmlFile = new File("testCases/airtxt/AtLogin.xml");
			}else if(project.toLowerCase().equals("mreach")){
				xmlFile = new File("testCases/mreach/AtLogin.xml");
			}
			DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dFactory.newDocumentBuilder();
			org.w3c.dom.Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			String url = doc.getDocumentElement().getAttribute("url");
			String username = null;
			String password = null;
			String submit = null;
			String logout = null;
			String testCaseType = null;
			driver = new FirefoxDriver();
			URL	connectionUrl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection)connectionUrl.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			System.out.println("Status code of Login page: "+connection.getResponseCode());
			AtLaunch.bw.write("Status code of Login page: "+connection.getResponseCode());
			AtLaunch.bw.newLine();
			AtLaunch.bw.write("******************************************************************");
			AtLaunch.bw.newLine();
			System.out.println("******************************************************************");
			NodeList list = doc.getElementsByTagName("testcase");
			
			System.out.println("url: "+url);
			
			for(int i = 0; i < list.getLength(); i++){
				driver.get(url);
				Node node = list.item(i);
				Element element = (Element) node;
				testCaseType = element.getAttribute("type");
				username = element.getElementsByTagName("username").item(0).getTextContent();
				password = element.getElementsByTagName("password").item(0).getTextContent();
				submit = element.getElementsByTagName("submit").item(0).getTextContent();
				logout = element.getElementsByTagName("logout").item(0).getTextContent();
				
				Thread.sleep(4000);
				userElement = driver.findElement(By.xpath("//*[@id='userid']")); 
				passwordElement = driver.findElement(By.xpath("//*[@id='password']")); 
				submitElement = driver.findElement(By.xpath("//*[@id='login']/tbody/tr[3]/td[2]/a"));
				
				userElement.sendKeys(username);
				passwordElement.sendKeys(password);
				if(Boolean.valueOf(submit)){
					submitElement.submit();
				}
				Thread.sleep(3000);
				if(testCaseType.equals("correct") && submit.equals("true") && logout.equals("true")){
					URL	homeUrl = new URL(driver.getCurrentUrl());
					connection = (HttpURLConnection)homeUrl.openConnection();
					connection.setRequestMethod("GET");
					connection.connect();
					Thread.sleep(2000);
					headerElement = driver.findElement(By.tagName("h2"));
					System.out.println("Status code of "+headerElement.getText()+": "+connection.getResponseCode());
					AtLaunch.bw.write("Status code of "+headerElement.getText()+": "+connection.getResponseCode());
					AtLaunch.bw.newLine();
					AtLaunch.bw.write("******************************************************************");
					AtLaunch.bw.newLine();
					System.out.println("******************************************************************");
						linkelement = driver.findElement(By.xpath("//*[@id='topnav']/a[7]")); 
						linkelement.click();
				}else{
					URL	homeUrl = new URL(driver.getCurrentUrl());
					connection = (HttpURLConnection)homeUrl.openConnection();
					connection.setRequestMethod("GET");
					connection.connect();
					AtLaunch.bw.write("Status code of Login page: "+connection.getResponseCode());
					AtLaunch.bw.newLine();
					AtLaunch.bw.write("******************************************************************");
					AtLaunch.bw.newLine();
					System.out.println("Status code of Login page: "+connection.getResponseCode());
					System.out.println("******************************************************************");
				}
				if(testCaseType.equals("incorrect") && submit.equals("true")){
					System.err.println(driver.findElement(By.xpath("//*[@id='errors']/ul/li")).getText());
				}
				
				if(!Boolean.valueOf(AtLaunch.quickMsg) && !Boolean.valueOf(AtLaunch.uploadMobile) && !Boolean.valueOf(AtLaunch.logout) && !Boolean.valueOf(AtLaunch.uploadMM) && !Boolean.valueOf(AtLaunch.templateMsg) && !Boolean.valueOf(AtLaunch.groupMsg) && !Boolean.valueOf(AtLaunch.navigation)){
					AtLaunch.bw.close();
				}
			}
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
			
	}	
}
