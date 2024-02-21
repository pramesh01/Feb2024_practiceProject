package com.project.pageclasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;


public class DashboardPage {
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
	
	@FindBy(xpath="//*[@id='top-links']/ul/li[2]/ul/li[5]/a")
	WebElement logOutfield;
	
	@FindBy(xpath="//*[@id='menu']/div[2]/ul/li[3]/a")
	WebElement componentelement;
	
	@FindBy(xpath="//*[@id='menu']/div[2]/ul/li[3]/div/a")
	WebElement showAllComponentsLink;
	
	public DashboardPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	public LoginPage searchobjectinapplication() {
		 
		WebElement Components=componentelement;
		//WebElement Components=driver.findElement(By.xpath("//*[@id='menu']/div[2]/ul/li[3]/a"));
		Actions act=new Actions(driver);
		act.moveToElement(Components).build().perform();
		//driver.findElement(By.xpath("//*[@id='menu']/div[2]/ul/li[3]/div/a")).click();
		showAllComponentsLink.click();
		String actualText=driver.findElement(By.xpath("//*[@id='content']/h3")).getText();
		String expectedText="Refine Search";
		Assert.assertEquals(actualText, expectedText);
		System.out.println("item searched successfully & clicked Component option after login");
		return new LoginPage(driver);
	}
	
	
	public void logOutFromWebsite() {
	
		myAccountBreadcrumb.click();
		loginFromBreadcrumb.click();
		enterusername.sendKeys("bloggerdelhi123@gmail.com");
		enterpassword.sendKeys("demo");
		signinButton.click();
		myAccountBreadcrumb.click();
		logOutfield.click();
		
		String expectedlogoutconfirmmsg="You have been logged off your account. It is now safe to leave the computer.";
		String actuallogoutconfirmmsg=driver.findElement(By.xpath("//*[@id='content']/p[1]")).getText();
		Assert.assertEquals(actuallogoutconfirmmsg, expectedlogoutconfirmmsg);
		System.out.println("Logged Out successfully from console.");
		
	}
}
