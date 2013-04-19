package com.core;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class AtMakerChecker {
	public static WebDriver mcdriver;
	public void atMakerCheckerPage(){
		try{
			WebElement userElement,passwordElement,submitElement,makercheckerElement,selectAllElement,commentElement,approveElement,confirmElement, fromdateElement;
			String project = AtLaunch.projectName;
			if(project.toLowerCase().equals("mreach")){
				File xmlFile = null;
				xmlFile = new File("testCases/mreach/AtMakerChecker.xml");
				DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dFactory.newDocumentBuilder();
				org.w3c.dom.Document doc = dBuilder.parse(xmlFile);
				doc.getDocumentElement().normalize();
				
				String mcUrl = null;
				String userName = null;
				String password = null;
				String comments = null;
				String task = null;
				String fromdate = null;
				NodeList list = doc.getElementsByTagName("testcase");
				
				for(int i = 0; i < list.getLength(); i++){
					Node node = list.item(i);	
					Element element = (Element) node;
					mcUrl = element.getAttribute("url");
					userName = element.getElementsByTagName("username").item(0).getTextContent();
					password = element.getElementsByTagName("password").item(0).getTextContent();
					comments = element.getElementsByTagName("comments").item(0).getTextContent();
					task = element.getElementsByTagName("task").item(0).getTextContent();
					fromdate = element.getElementsByTagName("fromdate").item(0).getTextContent();
				}
				
				if(!mcUrl.equals(null)){
					mcdriver = new FirefoxDriver();
					mcdriver.get(mcUrl);
					
					Thread.sleep(4000);
					userElement = mcdriver.findElement(By.xpath("//*[@id='userid']")); 
					passwordElement = mcdriver.findElement(By.xpath("//*[@id='password']")); 
					submitElement = mcdriver.findElement(By.xpath("//*[@id='login']/tbody/tr[3]/td[2]/a"));
					
					userElement.sendKeys(userName);
					passwordElement.sendKeys(password);
					submitElement.submit();
					
					System.out.println("******************************************************************");
					AtLaunch.bw.newLine();
					AtLaunch.bw.write("******************************************************************");
					AtLaunch.bw.newLine();
					System.out.println("Makerchecker Account Login with");
					AtLaunch.bw.write("Makerchecker Account Login with");
					System.out.println("******************************************************************");
					AtLaunch.bw.newLine();
					AtLaunch.bw.write("******************************************************************");
					AtLaunch.bw.newLine();
					System.out.println("Username: "+userName);
					System.out.println("Password: "+password);
					AtLaunch.bw.write("Username: "+userName);
					AtLaunch.bw.newLine();
					AtLaunch.bw.write("Password: "+password);
					AtLaunch.bw.newLine();
					
					
					Thread.sleep(4000);
					makercheckerElement = mcdriver.findElement(By.xpath("//*[@id='sidebarright']/div/ul/li[10]/a"));
					makercheckerElement.click();
					
					Thread.sleep(4000);
					fromdateElement = mcdriver.findElement(By.xpath("//*[@id='fromDate']"));
					fromdateElement.sendKeys(fromdate);
					
					
					System.out.println("Selecting All");
					AtLaunch.bw.write("Selecting All");
					AtLaunch.bw.newLine();
										
					/*Thread.sleep(4000);
					selectAllElement = mcdriver.findElement(By.xpath("//*[@id='content']/table[1]/tbody/tr[6]/td/input"));
					selectAllElement.click();
					
					
					commentElement = mcdriver.findElement(By.xpath("//*[@id='comments']"));
					commentElement.sendKeys(comments);
					if(task.toLowerCase().equals("approve")){
						System.out.println(task);
						AtLaunch.bw.write(task);
						AtLaunch.bw.newLine();
						approveElement = mcdriver.findElement(By.xpath("//*[@id='btnTable']/tbody/tr[3]/td[1]/a"));
						approveElement.click();
					}else if(task.toLowerCase().equals("reject")){
						System.out.println(task);
						AtLaunch.bw.write(task);
						AtLaunch.bw.newLine();
						approveElement = mcdriver.findElement(By.xpath("//*[@id='btnTable']/tbody/tr[3]/td[2]/a"));
						approveElement.click();
					}
												
					Thread.sleep(3000);
					confirmElement = mcdriver.findElement(By.partialLinkText("Confirm"));
					confirmElement.click();
					System.out.println("Confirm to "+task);
					AtLaunch.bw.write("Confirm to "+task);
					System.out.println("******************************************************************");
					AtLaunch.bw.newLine();
					AtLaunch.bw.write("******************************************************************");
					AtLaunch.bw.newLine();*/
				} 
				
			}
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
}
