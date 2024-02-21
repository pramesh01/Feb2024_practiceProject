package com.project.testcases;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.project.baseclass.BaseClass;
import com.project.pageclasses.LoginPage;
import com.utilities.DataUtil;
import com.utilities.TimeStamp;

import pages.HomePage;

import util.MyXLSReader;

public class LoginTest extends BaseClass {

	public WebDriver driver;
	LoginPage lp;
	MyXLSReader xls_reader;
	
	public LoginTest() {
		super();
	 }
	
	@BeforeMethod
	public void initialsetup() {
		driver=inittializebrowserandopenurl(prop.getProperty("browsername"));
		lp=new LoginPage(driver);
	 }
	
	 
	/*@Test(priority=3,dataProvider="validCredentialsSupplier")
	 
		public void loginwithValidCredentials(String email,String password) {
		lp.gotouserandpasswordenterpage();
		//lp.gotoApplicationDashboardPage(email,password);
		lp.gotoApplicationDashboardPage(prop.getProperty("username"),prop.getProperty("password"));
	  } */
	   
	@Test(priority=2)
	public void loginwithInvalidCredentials() {
		lp.gotouserandpasswordenterpage();
		lp.gotoApplicationDashboardPageforwrongcrdentials("bloggerdelhi123"+TimeStamp.timeStamp()+"@gmail.com",prop.getProperty("password"));
	  }
	
	@Test(priority=1)
	public void forgotlinkfunctionality() {
		lp.gotouserandpasswordenterpage();
		lp.forgotlinkfunctioanlity("pramesh.cs@gmail.com");
	}
	
	@AfterMethod
	public void aftertest() {
		driver.quit();
	}
	
	@DataProvider(name="validCredentialsSupplier")
	public Object[][] supplyTestData() {
		
		Object[][] data = DataUtil.getTestDataFromExcel("Example");
		return data;
	}
          }
