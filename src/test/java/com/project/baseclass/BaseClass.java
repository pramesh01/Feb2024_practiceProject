package com.project.baseclass;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

public class BaseClass {
	
	WebDriver driver;
	public Properties prop;
	
	public BaseClass() {
		String path=System.getProperty("user.dir")+"//src//main//resources//project.properties";
		prop=new Properties();
		try {
		FileInputStream fis=new FileInputStream(path);
		prop.load(fis);
		}catch(Throwable e) {
			e.printStackTrace();	
		}
	  }
	
	public WebDriver inittializebrowserandopenurl(String BName) {

		if(BName.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();
			driver.manage().window().maximize();
		}
		else if(BName.equalsIgnoreCase("firefox")) {
			driver=new FirefoxDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		//driver.get("https://www.tutorialsninja.com/demo/");
		driver.get(prop.getProperty("url"));
		return driver;
	}
}
