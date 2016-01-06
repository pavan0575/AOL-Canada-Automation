package com.Automation_Framework.testcases;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Automation_Framework.frameworkengine.TestExecutor;



public class ContactForm {
	
	//creating TestExecutor object to access all methods and member functions of TestExecutor class
	TestExecutor exe = new TestExecutor();
	
	@Parameters("browser")
	@BeforeTest
	public void browserSelect(String browserName){
		exe.openBrowser(browserName);
	}
	
	@Test
	public void test() throws EncryptedDocumentException, InvalidFormatException, IOException{
		System.out.println("Automating Contact Us page");
		exe.executeTest("ContactForm");
		System.out.println("End of Executing Contact us page");
	}
	
	@AfterTest
	public void closeBrowser(){
		
	}

}
