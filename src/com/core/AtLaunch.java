package com.core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class AtLaunch {
	public static FileWriter fw;
	public static BufferedWriter bw;
	public static String projectName;
	public static String login;
	public static String quickMsg;
	public static String uploadMobile;
	public static String logout;
	public static String uploadMM;
	public static String templateMsg;
	public static String groupMsg;
	public static String navigation;
	public static String makerchecker;
	final static private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss");
	
	public static void main(String args[]) throws ParserConfigurationException, SAXException, IOException, InterruptedException{
		//try{
			String str;
			str = format.format(new java.util.Date());
			File file = new File("Logs/"+str+".txt");
			file.createNewFile();
			fw = new FileWriter(file.getAbsoluteFile());
			bw = new BufferedWriter(fw);
		
									
			File xmlFile = new File("testCases/AtLaunch.xml");
			DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dFactory.newDocumentBuilder();
			org.w3c.dom.Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			NodeList list = doc.getElementsByTagName("testcase");
			projectName = null;
			login = null;
			quickMsg = null;
			uploadMobile = null; 
			logout = null;
			uploadMM = null;
			templateMsg = null;
			groupMsg = null;
			navigation = null;
			makerchecker = null;
			
			
			for(int i = 0; i < list.getLength(); i++){
				Node node = list.item(i);
				Element element = (Element) node;
				projectName = element.getAttribute("project");
				login = element.getElementsByTagName("login").item(0).getTextContent();
				quickMsg = element.getElementsByTagName("quickMsg").item(0).getTextContent();
				groupMsg = element.getElementsByTagName("groupMsg").item(0).getTextContent();
				uploadMobile = element.getElementsByTagName("uploadMobile").item(0).getTextContent();
				uploadMM = element.getElementsByTagName("uploadMM").item(0).getTextContent(); 
				logout = element.getElementsByTagName("logout").item(0).getTextContent();
				templateMsg = element.getElementsByTagName("templateMsg").item(0).getTextContent();
				navigation = element.getElementsByTagName("navigation").item(0).getTextContent();
				makerchecker = element.getElementsByTagName("makerChecker").item(0).getTextContent();
			}
			
				
			if(Boolean.valueOf(login)){
				AtLogin atLogin = new AtLogin();
				atLogin.atLoginPage();
			}
			
			if(Boolean.valueOf(quickMsg)){
				AtQuickMessage atQuickMsg = new AtQuickMessage();
				atQuickMsg.atQuickMessagePage();
			}
			
			if(Boolean.valueOf(groupMsg)){
				AtGroups atGroup = new AtGroups();
				atGroup.atGroupsPage();
			}
			
			if(Boolean.valueOf(uploadMobile)){
				AtUploadMobile upldMobile = new AtUploadMobile();
				upldMobile.atUploadMobilePage();
			}
			
			if(Boolean.valueOf(uploadMM)){
				AtUploadMM upldMM = new AtUploadMM(); 
				upldMM.atUploadMMPage();
			}
			
			if(Boolean.valueOf(templateMsg)){
				AtTemplateMsg tempMsg = new AtTemplateMsg();
				tempMsg.atTemplateMsgPage();
			}
			
			if(Boolean.valueOf(makerchecker)){
				AtMakerChecker makerChecker = new AtMakerChecker();
				makerChecker.atMakerCheckerPage();
			}
			
			if(Boolean.valueOf(navigation)){
				AtNavigation nav = new AtNavigation();
				nav.atNavigationPage();
			}
			
			if(Boolean.valueOf(logout)){
				AtLogout atLogout = new AtLogout();
				atLogout.atLogoutPage();
			}
		/*}catch(Exception e){
			System.err.println(e.getMessage());
		}*/
	}
}
