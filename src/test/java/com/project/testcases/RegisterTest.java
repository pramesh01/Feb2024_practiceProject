package com.project.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.project.baseclass.BaseClass;
import com.project.pageclasses.RegisterPage;
import com.utilities.TimeStamp;

public class RegisterTest extends BaseClass {
	
  public WebDriver driver;
  RegisterPage rp;
  
  public RegisterTest() {
	  super();
  }
   
  @BeforeMethod
	public void initialsetup() {
	  driver=inittializebrowserandopenurl(prop.getProperty("browsername"));
	  rp=new RegisterPage(driver);
	 }
  
	@Test(priority=1)
	public void registerwithallmandatoryfields() {
		rp.registeruserwithallmandatoryfields();
	  }
	
	@Test(priority=2)
	public void useralreadyregistered() {
		rp.registeruserwithduplicateEmailid(prop.getProperty("username"));
	  }
	
	@Test(priority=3)
	public void registerwithoutpolicyacceptance() {
		rp.registeruserwithoutpolicycheck();
    }
	
	@AfterMethod
	public void aftertest() {
		driver.quit();
	}
     }