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

public class AtUploadMobile {
	public void atUploadMobilePage(){
		try{
			WebElement headerElement, activityNameElement, deliveryElement, fileElement, languageElement, messageElement, scheduleWebElement, dateElement, hrsElement, minElement, proceedElement, sendElement, msgCategoryEle, exclutionListEle, throtSizeEle, throtTimeEle;
			String project = AtLaunch.projectName;
			File xmlFile = null, propFile = null;
			if(project.toLowerCase().equals("airtxt")){
				xmlFile = new File("testCases/airtxt/AtUploadMobile.xml");
			}else if(project.toLowerCase().equals("mreach")){
				xmlFile = new File("testCases/mreach/AtUploadMobile.xml");
				propFile = new File("testCases/mreach/properties.xml");
			}
			DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dFactory.newDocumentBuilder();
			org.w3c.dom.Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			String uploadFileMobile = null;
			String uploadFileMobUrl = null;
			StringBuffer name = null;
			String domestic = null;
			String global = null;
			String file = null;
			ArrayList<String> uploadFiles = new ArrayList<String>();
			String language = null;
			String message = null;
			String schedule = null;
			String date = null;
			String hrs = null;
			String min = null;
			String proceed = null;
			String send = null;
			String domesticSenderId = null;
			String globalSenderId = null;
			String exclutionList = null;
			String msgCategory = null;
			String throtSize = null;
			String throtTime = null;
			String throttlingType = null;
			NodeList list = doc.getElementsByTagName("testcase");
			NodeList domesticList = doc.getElementsByTagName("domestic");
			NodeList globalList = doc.getElementsByTagName("global");
			NodeList uploadFileList = doc.getElementsByTagName("files");
			NodeList scheduleList = doc.getElementsByTagName("schedule");
			NodeList myList = null;
			
			if(project.toLowerCase().equals("mreach")){
				org.w3c.dom.Document propdoc = dBuilder.parse(propFile);
				propdoc.getDocumentElement().normalize();
				NodeList propList = propdoc.getElementsByTagName("properties");
				NodeList trotList = propdoc.getElementsByTagName("throttling");
				
				for(int p = 0; p < propList.getLength(); p++){
					Node propNode = propList.item(p);
					Element prpElement = (Element) propNode;
					
					exclutionList = prpElement.getElementsByTagName("exclusionList").item(0).getTextContent();
					msgCategory = prpElement.getElementsByTagName("msgCategory").item(0).getTextContent();
					
					for(int t=0; t < trotList.getLength(); t++){
						Node trotNode = trotList.item(t);
						Element troElement = (Element) trotNode;
						throttlingType = troElement.getAttribute("type");
						throtSize = troElement.getElementsByTagName("size").item(0).getTextContent();
						throtTime = troElement.getElementsByTagName("time").item(0).getTextContent(); 
					}
				}
			}
			
			for(int i = 0; i < list.getLength(); i++){
				Node node = list.item(i);
				Element element = (Element) node;
				uploadFileMobUrl = element.getAttribute("url");
				uploadFileMobile = element.getElementsByTagName("uploadFileMobile").item(0).getTextContent();
							
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
				
				
				for(int k = 0; k < uploadFileList.getLength(); k++){
					Node fileChildNode = uploadFileList.item(k);
					Element fileChildElement = (Element) fileChildNode;
					myList = fileChildElement.getElementsByTagName("file");
					for(int l = 0; l < myList.getLength(); l++){
						file = myList.item(l).getTextContent();
						uploadFiles.add(file);
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
			
			String activityText,activityText1;
			for(int k = 0; k < uploadFiles.size(); k++){
				if(!uploadFileMobile.equals(null)){
					AtLogin.driver.get(uploadFileMobUrl);
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
					activityNameElement = AtLogin.driver.findElement(By.xpath("//*[@id='table']/tbody/tr[1]/td[3]/table/tbody/tr[2]/td/input"));
					name = RandomName.getName();
					
					activityText = uploadFiles.get(k).substring(uploadFiles.get(k).lastIndexOf("\\")+1, uploadFiles.get(k).lastIndexOf("."));
					activityText1 = uploadFiles.get(k).substring(uploadFiles.get(k).indexOf(".")+1, uploadFiles.get(k).length());
					
					activityText.replace(" ", "");
					activityText1.replace(" ", "");
					
					
					System.out.println("Activity Name: UpldM-"+activityText+"-"+activityText1+"-"+name);
					System.out.println("File: "+uploadFiles.get(k));
					activityNameElement.sendKeys("UpldM-"+activityText+"-"+activityText1+"-"+name);
					
					AtLaunch.bw.write("Activity Name: UpldM-"+activityText+"-"+activityText1+"-"+name);
					AtLaunch.bw.newLine();
					AtLaunch.bw.write("File: "+uploadFiles.get(k));
					AtLaunch.bw.newLine();
					
					
					if(Boolean.valueOf(domestic)){
						System.out.println("delivery: domestic");
						AtLaunch.bw.write("delivery: domestic");
						AtLaunch.bw.newLine();
						deliveryElement = AtLogin.driver.findElement(By.xpath("//*[@id='domesticDelivery']"));
						deliveryElement.click();
						if(domesticSenderId.length() > 0){
							System.out.println("Domestic SenderId: "+domesticSenderId);
							AtLaunch.bw.write("Domestic SenderId: "+domesticSenderId);
							AtLaunch.bw.newLine();
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
						System.out.println("delivery: global");
						AtLaunch.bw.write("delivery: global");
						AtLaunch.bw.newLine();
						deliveryElement = AtLogin.driver.findElement(By.xpath("//*[@id='globalDelivery']"));
						deliveryElement.click();
						if(!globalSenderId.equals(null))
							System.out.println("Global SenderId: "+globalSenderId);
						AtLaunch.bw.write("Global SenderId: "+globalSenderId);
						AtLaunch.bw.newLine();
						deliveryElement = AtLogin.driver.findElement(By.xpath("//*[@id='globalSenderid']"));
						deliveryElement.sendKeys(globalSenderId);
					}
					
					fileElement = AtLogin.driver.findElement(By.xpath("//*[@id='table']/tbody/tr[4]/td[3]/table/tbody/tr[3]/td/input"));
					fileElement.sendKeys(uploadFiles.get(k));
					
					languageElement = AtLogin.driver.findElement(By.xpath("//*[@id='contentType']"));
					languageElement.sendKeys(language);
					
					
					if(project.toLowerCase().equals("mreach")){
						if(!Boolean.valueOf(throttlingType)){
							exclutionListEle = AtLogin.driver.findElement(By.xpath("//*[@id='table']/tbody/tr[6]/td[3]/table/tbody/tr[2]/td[1]/span/select"));
							exclutionListEle.sendKeys(exclutionList);
						}else{
							exclutionListEle = AtLogin.driver.findElement(By.xpath("//*[@id='table']/tbody/tr[8]/td[3]/table/tbody/tr[2]/td[1]/span/select"));
							exclutionListEle.sendKeys(exclutionList);
						}
						
						if(msgCategory.length() > 0){
							msgCategoryEle = AtLogin.driver.findElement(By.xpath("//*[@id='msgCategory']"));
							msgCategoryEle.sendKeys(msgCategory);
						}
						
						if(Boolean.valueOf(throttlingType)){
							throtSizeEle = AtLogin.driver.findElement(By.xpath("//*[@id='throttlesize']"));
							throtSizeEle.sendKeys(throtSize);
							System.out.println("Throttling Size: "+throtSize);
							AtLaunch.bw.write("Throttling Size: "+throtSize);
							AtLaunch.bw.newLine();
							
							
							throtTimeEle = AtLogin.driver.findElement(By.xpath("//*[@id='throttleTime']"));
							throtTimeEle.sendKeys(throtTime);
							System.out.println("Throttling Time: "+throtTime);
							AtLaunch.bw.write("Throttling Time: "+throtTime);
							AtLaunch.bw.newLine();
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
						proceedElement.click();
					}
					
					Thread.sleep(3000);
					if(Boolean.valueOf(send)){
						sendElement = AtLogin.driver.findElement(By.xpath("//*[@id='proceed']"));
						sendElement.click();
					}
				}
			}
			
			if(!Boolean.valueOf(AtLaunch.groupMsg) && !Boolean.valueOf(AtLaunch.uploadMM) && !Boolean.valueOf(AtLaunch.templateMsg) && !Boolean.valueOf(AtLaunch.quickMsg) && !Boolean.valueOf(AtLaunch.logout) && !Boolean.valueOf(AtLaunch.navigation)){
				AtLaunch.bw.close();
			}
			
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
	
}
