package com.project.pageclasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.utilities.TimeStamp;

public class RegisterPage  {
	
	WebDriver driver;
	
	@FindBy(xpath="//*[@id='top-links']/ul/li[2]/a/span[1]")
	WebElement MyAccountBreadcrumb;
	@FindBy(xpath="//*[@id='top-links']/ul/li[2]/ul/li[1]/a")
	WebElement RegisterLink;
	@FindBy(id="input-firstname")
	WebElement firstnamefield;
	@FindBy(id="input-lastname")
	WebElement lastnamefield;
	@FindBy(id="input-email")
	WebElement emailtextfield;
	@FindBy(id="input-telephone")
	WebElement telephonetextfield;
	@FindBy(id="input-password")
	WebElement passwordtextfield;
	@FindBy(id="input-confirm")
	WebElement passwordconfirmfield;
	@FindBy(xpath="//*[@id='content']/form/div/div/input[1]")
	WebElement privacypolicycheckbox;
	@FindBy(xpath="//*[@id='content']/form/div/div/input[2]")
	WebElement continuebutton;
	
	public RegisterPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	public LoginPage registeruserwithallmandatoryfields() {
		MyAccountBreadcrumb.click();
		RegisterLink.click();
		firstnamefield.sendKeys("renu"+TimeStamp.randomnumber());
		lastnamefield.sendKeys("singh");
		emailtextfield.sendKeys("renu.singh"+TimeStamp.randomnumber()+"@gmail.com");
		telephonetextfield.sendKeys("9999999999");
		passwordtextfield.sendKeys("demo");
		passwordconfirmfield.sendKeys("demo");
		privacypolicycheckbox.click();
		continuebutton.click();
String expectedConfirmationTextMessage="Congratulations! Your new account has been successfully created!";
String actualConfirmationTextmessage=driver.findElement(By.xpath("//*[@id='content']/p[1]")).getText();
		Assert.assertEquals(actualConfirmationTextmessage, expectedConfirmationTextMessage);
		System.out.println("User is Registered successfully.got the successful registered message.");
		return new LoginPage(driver);
	}
	
	public LoginPage registeruserwithduplicateEmailid(String emailid) {
		MyAccountBreadcrumb.click();
		RegisterLink.click();
		firstnamefield.sendKeys("renu"+TimeStamp.randomnumber());
		lastnamefield.sendKeys("singh");
		emailtextfield.sendKeys(emailid);
		telephonetextfield.sendKeys("9999999999");
		passwordtextfield.sendKeys("demo");
		passwordconfirmfield.sendKeys("demo");
		privacypolicycheckbox.click();
		continuebutton.click();
		
		String expectedwarningmessage="Warning: E-Mail Address is already registered!";
		String actualwarningmessage= driver.findElement(By.xpath("//*[@id='account-register']/div[1]")).getText();
		Assert.assertEquals(actualwarningmessage, expectedwarningmessage);
		System.out.println("test passed because got successfull warning message of already registered user.");
		return new LoginPage(driver);
	}
	
	public LoginPage registeruserwithoutpolicycheck() {
		MyAccountBreadcrumb.click();
		RegisterLink.click();
		firstnamefield.sendKeys("renu"+TimeStamp.randomnumber());
		lastnamefield.sendKeys("singh");
		emailtextfield.sendKeys("renu.singh"+TimeStamp.randomnumber()+"@gmail.com");
		telephonetextfield.sendKeys("9999999999");
		passwordtextfield.sendKeys("demo");
		passwordconfirmfield.sendKeys("demo");
		//privacypolicycheckbox.click();
		continuebutton.click();
String expectedwarningmessage="Warning: You must agree to the Privacy Policy!";
String actualwarningmessage= driver.findElement(By.xpath("//*[@id='account-register']/div[1]")).getText();
Assert.assertEquals(actualwarningmessage, expectedwarningmessage);
System.out.println("test passed because got successfull warning message of Policy related Issue.");
	return new LoginPage(driver);
	}
}