package com.core;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class AtLogout {
	public void atLogoutPage(){
		try{
			WebElement statusElement = null;
			String project = AtLaunch.projectName;
			File xmlFile = null;
			if(project.toLowerCase().equals("airtxt")){
				xmlFile = new File("testCases/airtxt/AtLogout.xml");
			}else if(project.toLowerCase().equals("mreach")){
				xmlFile = new File("testCases/mreach/AtLogout.xml");
			}
			DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dFactory.newDocumentBuilder();
			org.w3c.dom.Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			String logout = null;
			NodeList list = doc.getElementsByTagName("testcase");
			WebElement linkelement;
						
			for(int i=0; i<list.getLength();i++){
				Node node = list.item(i);
				AtLogin.driver.getCurrentUrl();
				Thread.sleep(2000);
				Element elememt = (Element) node;
				logout = elememt.getElementsByTagName("logout").item(0).getTextContent();
				if(Boolean.valueOf(logout)){
					if(project.toLowerCase().equals("airtxt")){
						linkelement = AtLogin.driver.findElement(By.xpath("//*[@id='topnav']/a[7]")); 
						linkelement.click();
						Thread.sleep(2000);
						statusElement  = AtLogin.driver.findElement(By.xpath("//*[@id='info']/ul/li"));
					}else if(project.toLowerCase().equals("mreach")){
						Thread.sleep(2000);
						linkelement = AtLogin.driver.findElement(By.xpath("//*[@id='topnav']/a[6]")); 
						linkelement.click();
						Thread.sleep(2000);
						statusElement  = AtLogin.driver.findElement(By.xpath("//*[@id='info']/ul/li"));
					}
					Thread.sleep(2000);
					AtLaunch.bw.write("Status: "+statusElement.getText());
					AtLaunch.bw.newLine();
					System.out.println("Status: "+statusElement.getText());	
				}
				Thread.sleep(2000);
			}
			AtLaunch.bw.close();
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
}
