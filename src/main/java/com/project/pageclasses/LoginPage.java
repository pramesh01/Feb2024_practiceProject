package com.project.pageclasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage {
	
	WebDriver driver;
	
	@FindBy(xpath="//*[@id='top-links']/ul/li[2]/a/span[1]")
	WebElement myAccountBreadcrumb;
	
	@FindBy(xpath="//*[@id='top-links']/ul/li[2]/ul/li[2]/a")
	WebElement loginFromBreadcrumb;
	
	@FindBy(id="input-email")
	WebElement enterusername;
	
	@FindBy(id="input-password")
	WebElement enterpassword;
	
	@FindBy(xpath="//*[@id='content']/div/div[2]/div/form/input")
	WebElement signinButton;
	
	@FindBy(xpath="//*[@class='form-group']/a")
	WebElement forgothyperlink;
	
	@FindBy(xpath="//*[@id='input-email']")
	WebElement emailfieldforforgottext;
	
	@FindBy(xpath="//*[@id='content']/form/div/div[2]/input")
	WebElement continuebuttonafterenteringmailid;
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	public LoginPage gotouserandpasswordenterpage() {
		myAccountBreadcrumb.click();
		loginFromBreadcrumb.click();
		return new LoginPage(driver);	
	     }
	
	public DashboardPage gotoApplicationDashboardPage(String emailid,String password) {
		
		   enterusername.sendKeys(emailid);
		   enterpassword.sendKeys(password);
		   signinButton.click();
			String expectedText="Edit your account information";
			String actualText=driver.findElement(By.xpath("//*[@id='content']/ul[1]/li[1]/a")).getText();
			Assert.assertEquals(actualText, expectedText);
			System.out.println("User is Logged Successfully.");
			return new DashboardPage(driver);
	}
	public DashboardPage gotoApplicationDashboardPageforwrongcrdentials(String emailid,String password) {
		
		   enterusername.sendKeys(emailid);
		   enterpassword.sendKeys(password);
		   signinButton.click();
		   String actualWarningMessage="Warning: No match for E-Mail Address and/or Password.";
			String expectedWarningMessage=driver.findElement(By.xpath("//*[@id='account-login']/div[1]")).getText();
			Assert.assertEquals(actualWarningMessage, expectedWarningMessage);
			System.out.println("login credentials are incorrect , so resisting login to console.");
			return new DashboardPage(driver);
	}
	
	public DashboardPage forgotlinkfunctioanlity(String emailid) {
		   
		   forgothyperlink.click();
		   emailfieldforforgottext.sendKeys(emailid);
		   continuebuttonafterenteringmailid.click();
		   String expectedText="An email with a confirmation link has been sent your email address.";
			String actualText=driver.findElement(By.xpath("//*[@id='account-login']/div[1]")).getText();
			Assert.assertEquals(actualText, expectedText)  ;  
			System.out.println("Email sent message confirmation has been send successfully for forget password");
			return new DashboardPage(driver);
	}

}
