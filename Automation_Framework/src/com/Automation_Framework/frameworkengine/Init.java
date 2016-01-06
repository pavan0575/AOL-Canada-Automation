package com.Automation_Framework.frameworkengine;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.Automation_Framework.frameworkengine.TestExecutor;

public class Init {

	TestExecutor exe = new TestExecutor();
	
	@Parameters("browser")
	
	@BeforeTest
	public void browserSelect(String browserName){
		exe.openBrowser(browserName);
	}

	@Test
	public void allLinks() {
		exe.get("http://pavan.sandbox.advertising.aol.ca/");
	}

	@Test
	public void screenShot() throws IOException {
		exe.getscreenShot("http://pavan.sandbox.advertising.aol.ca/about-us/");
	}
	
	@AfterTest
	public void closeBrowser(){
		
	}
	
	
}
