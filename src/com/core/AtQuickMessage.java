package com.core;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class AtQuickMessage {
	public void atQuickMessagePage() throws InterruptedException, ParserConfigurationException, SAXException, IOException{
		//try{
			WebElement headerElement, activityNameElement, deliveryElement, mobileElement, languageElement, messageElement, scheduleWebElement, dateElement, hrsElement, minElement, proceedElement, sendElement, msgCategoryEle, exclutionListEle;
			String project = AtLaunch.projectName;
			File xmlFile = null, propFile = null;
			if(project.toLowerCase().equals("airtxt")){
				xmlFile = new File("testCases/airtxt/AtQuickMessage.xml");
			}else if(project.toLowerCase().equals("mreach")){
				xmlFile = new File("testCases/mreach/AtQuickMessage.xml");
				propFile = new File("testCases/mreach/properties.xml");
			}
			DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dFactory.newDocumentBuilder();
			org.w3c.dom.Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			String quickMessage = null;
			StringBuffer name = null;
			String domestic = null;
			String global = null;
			String number = null;
			ArrayList<String> mobileNumbers = new ArrayList<String>();
			String language = null;
			String message = null;
			String schedule = null;
			String date = null;
			String hrs = null;
			String min = null;
			String proceed = null;
			String send = null;
			String quickMsgUrl = null;
			String domesticSenderId = null;
			String globalSenderId = null;
			String exclutionList = null;
			String msgCategory = null;
			NodeList list = doc.getElementsByTagName("testcase");
			NodeList domesticList = doc.getElementsByTagName("domestic");
			NodeList globalList = doc.getElementsByTagName("global");
			NodeList mobileList = doc.getElementsByTagName("mobile");
			NodeList scheduleList = doc.getElementsByTagName("schedule");
			NodeList myList = null;
			
			if(project.toLowerCase().equals("mreach")){
				org.w3c.dom.Document propdoc = dBuilder.parse(propFile);
				propdoc.getDocumentElement().normalize();
				NodeList propList = propdoc.getElementsByTagName("properties");
								
				for(int p = 0; p < propList.getLength(); p++){
					Node propNode = propList.item(p);
					Element prpElement = (Element) propNode;
					
					exclutionList = prpElement.getElementsByTagName("exclusionList").item(0).getTextContent();
					msgCategory = prpElement.getElementsByTagName("msgCategory").item(0).getTextContent();
				}
			}
			
			
			
			for(int i = 0; i < list.getLength(); i++){
				Node node = list.item(i);
				Element element = (Element) node;
				quickMsgUrl = element.getAttribute("url");
				quickMessage = element.getElementsByTagName("quickMessage").item(0).getTextContent();
								
				for(int l=0; l < domesticList.getLength(); l++){
					Node domesticchildNode = domesticList.item(l);
					Element domesticChileElement = (Element) domesticchildNode;
					domesticSenderId = domesticChileElement.getAttribute("senderId");
					domestic = domesticChileElement.getElementsByTagName("domesticValue").item(0).getTextContent();
				}
				
				for(int l=0; l < globalList.getLength(); l++){
					Node globalChildNode = globalList.item(l);
					Element globalChileElement = (Element) globalChildNode;
					globalSenderId = globalChileElement.getAttribute("senderId");
					global = globalChileElement.getElementsByTagName("globalValue").item(0).getTextContent();
				}
				
				for(int k = 0; k < mobileList.getLength(); k++){
					Node mobileChildNode = mobileList.item(k);
					Element mobChildElement = (Element) mobileChildNode;
					myList = mobChildElement.getElementsByTagName("number");
					for(int l = 0; l < myList.getLength(); l++){
						number = myList.item(l).getTextContent();
						mobileNumbers.add(number);
					}
				}
				
				language = element.getElementsByTagName("language").item(0).getTextContent();
				message = element.getElementsByTagName("message").item(0).getTextContent();
				
				for(int m = 0; m < scheduleList.getLength(); m++){
					Node scheduleNode = scheduleList.item(m);
					Element scheduleElement = (Element) scheduleNode;
					schedule = scheduleElement.getAttribute("type");
					date = scheduleElement.getElementsByTagName("date").item(0).getTextContent();
					hrs = scheduleElement.getElementsByTagName("hrs").item(0).getTextContent();
					min = scheduleElement.getElementsByTagName("min").item(0).getTextContent();
				}
				
				proceed = element.getElementsByTagName("proceed").item(0).getTextContent();
				send = element.getElementsByTagName("send").item(0).getTextContent();
				
			}
			
			if(!quickMessage.equals(null)){
				AtLogin.driver.get(quickMsgUrl);
				URL	homeUrl = new URL(AtLogin.driver.getCurrentUrl());
				HttpURLConnection connection = (HttpURLConnection)homeUrl.openConnection();
				connection = (HttpURLConnection)homeUrl.openConnection();
				connection.setRequestMethod("GET");
				connection.connect();
				Thread.sleep(2000);
				headerElement = AtLogin.driver.findElement(By.xpath("//*[@id='content']/h2"));
				System.out.println("Status code of "+headerElement.getText()+": "+connection.getResponseCode());
				System.out.println("******************************************************************");
				AtLaunch.bw.write("Status code of "+headerElement.getText()+": "+connection.getResponseCode());
				AtLaunch.bw.newLine();
				AtLaunch.bw.write("******************************************************************");
				AtLaunch.bw.newLine();
			}
			
			if(!quickMessage.equals(null)){
				activityNameElement = AtLogin.driver.findElement(By.xpath("//*[@id='table']/tbody/tr[1]/td[3]/table/tbody/tr[2]/td/input"));
				name = RandomName.getName();
				activityNameElement.sendKeys("QuickMsg "+name);
				System.out.println("File name: QuickMsg "+name);
				
				if(Boolean.valueOf(domestic)){
					AtLaunch.bw.write("delivery: domestic");
					AtLaunch.bw.newLine();
					System.out.println("delivery: domestic");
					deliveryElement = AtLogin.driver.findElement(By.xpath("//*[@id='domesticDelivery']"));
					deliveryElement.click();
					if(domesticSenderId.length() > 0){
						AtLaunch.bw.write("Domestic SenderId: "+domesticSenderId);
						AtLaunch.bw.newLine();
						System.out.println("Domestic SenderId: "+domesticSenderId);
						if(project.toLowerCase().equals("airtxt")){
							deliveryElement = AtLogin.driver.findElement(By.xpath("//*[@id='table']/tbody/tr[12]/td[3]/table/tbody/tr[3]/td[1]/span/select"));
							deliveryElement.sendKeys(domesticSenderId);
						}else if(project.toLowerCase().equals("mreach")){
							deliveryElement = AtLogin.driver.findElement(By.xpath("//*[@id='senderid']"));
							deliveryElement.sendKeys(domesticSenderId);
						}
					}
				}
				
				if(Boolean.valueOf(global)){
					AtLaunch.bw.write("delivery: global");
					AtLaunch.bw.newLine();
					System.out.println("delivery: global");
					deliveryElement = AtLogin.driver.findElement(By.xpath("//*[@id='globalDelivery']"));
					deliveryElement.click();
					if(globalSenderId.length() > 0)
						AtLaunch.bw.write("Global SenderId: "+globalSenderId);
						AtLaunch.bw.newLine();
						System.out.println("Global SenderId: "+globalSenderId);
					deliveryElement = AtLogin.driver.findElement(By.xpath("//*[@id='globalSenderid']"));
					deliveryElement.sendKeys(globalSenderId);
				}
				
				mobileElement = AtLogin.driver.findElement(By.xpath("//*[@id='txtMob']"));
				AtLaunch.bw.write("Mobile Numbers: ");
				AtLaunch.bw.newLine();
				System.out.println("Mobile Numbers: ");
				for(int i = 0; i < mobileNumbers.size(); i++){
					AtLaunch.bw.write(mobileNumbers.get(i));
					AtLaunch.bw.newLine();
					System.out.println(mobileNumbers.get(i));
					mobileElement.sendKeys(mobileNumbers.get(i));
					mobileElement.sendKeys("\n");
				}
				
				languageElement = AtLogin.driver.findElement(By.xpath("//*[@id='contentType']"));
				languageElement.sendKeys(language);
				
				
				if(project.toLowerCase().equals("mreach")){
																								
					exclutionListEle = AtLogin.driver.findElement(By.xpath("//*[@id='table']/tbody/tr[6]/td[3]/table/tbody/tr[2]/td[1]/span/select"));
					exclutionListEle.sendKeys(exclutionList);
					
					
					if(msgCategory.length() > 0){
						msgCategoryEle = AtLogin.driver.findElement(By.xpath("//*[@id='msgCategory']"));
						msgCategoryEle.sendKeys(msgCategory);
					}
				}
				
				messageElement = AtLogin.driver.findElement(By.xpath("//*[@id='message']"));
				messageElement.sendKeys(message);
				
				
				
				
				if(Boolean.valueOf(schedule)){
					scheduleWebElement = AtLogin.driver.findElement(By.xpath("//*[@id='selectSch']"));
					scheduleWebElement.click();
					dateElement = AtLogin.driver.findElement(By.xpath("//*[@id='schDate']"));
					dateElement.sendKeys(date);
					hrsElement = AtLogin.driver.findElement(By.xpath("//*[@id='hour']"));
					hrsElement.sendKeys(hrs);
					minElement = AtLogin.driver.findElement(By.xpath("//*[@id='mins']"));
					minElement.sendKeys(min);
				}
				
				
				
				if(Boolean.valueOf(proceed)){
					proceedElement = AtLogin.driver.findElement(By.xpath("//*[@id='innerwrapper']/p/a"));
					proceedElement.submit();
				}
				
				Thread.sleep(3000);
				if(Boolean.valueOf(send)){
					sendElement = AtLogin.driver.findElement(By.xpath("//*[@id='proceed']"));
					sendElement.submit();
				}
			}
			
			if(!Boolean.valueOf(AtLaunch.groupMsg) && !Boolean.valueOf(AtLaunch.uploadMobile) && !Boolean.valueOf(AtLaunch.uploadMM) && !Boolean.valueOf(AtLaunch.templateMsg) && !Boolean.valueOf(AtLaunch.logout) && !Boolean.valueOf(AtLaunch.navigation)){
				AtLaunch.bw.close();
			}
		/*}catch(Exception e){
			System.err.println(e.getMessage());
		}*/
	}
}
