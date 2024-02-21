package com.project.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.project.baseclass.BaseClass;
import com.project.pageclasses.DashboardPage;

public class LogoutTest extends BaseClass{
	public WebDriver driver;
	DashboardPage dp;
	
	public LogoutTest(){
		super();
	}
	
	@BeforeMethod
	public void initialsetup() {
	 driver=inittializebrowserandopenurl(prop.getProperty("browsername"));
	 dp=new DashboardPage(driver);
		
	 }
	
	@Test
	public void checkingLogout() {
		
		dp.logOutFromWebsite();
	 }
	
	@AfterMethod
	public void aftertest() {
		driver.quit();
	}
	
       }