package com.project.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.project.baseclass.BaseClass;
import com.project.pageclasses.DashboardPage;
import com.project.pageclasses.LoginPage;

public class SearchTest extends BaseClass {
	public WebDriver driver;
	DashboardPage dp;
	LoginPage lp;;
	
	@BeforeMethod
	public void initialsetup() {
		driver=inittializebrowserandopenurl(prop.getProperty("browsername"));
		lp=new LoginPage(driver);
		dp=new DashboardPage(driver);
	 }
	
	@Test
	public void searchitemwithoutlogin() {
	     dp.searchobjectinapplication(); 
	   }
	
	@Test(priority=1)
	public void searchitemwithlogin() {
	        
		 lp.gotouserandpasswordenterpage();
	     lp.gotoApplicationDashboardPage(prop.getProperty("username"),prop.getProperty("password"));
		 dp.searchobjectinapplication();
		
	  }
	
	@AfterMethod
	public void aftertest() {
		driver.quit();
	}
         }