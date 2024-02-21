package com.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	
	public static ExtentReports generatereports() {
		
		String extenreportstoringpath=System.getProperty("user.dir")+"//Reports//seleniumAutomaticReports.html";
		ExtentSparkReporter esr=new ExtentSparkReporter(extenreportstoringpath);
		esr.config().setTheme(Theme.DARK);
		esr.config().setDocumentTitle("Automtic generated docs title");
		esr.config().setReportName("Automtic Generated Extent Reports");
		esr.config().setTimeStampFormat("dd/MM/YYYY:hh:mm");
		
		ExtentReports extentReports=new ExtentReports();
		extentReports.attachReporter(esr);
		extentReports.setSystemInfo("Selenium Version","4.19.11");
		extentReports.setSystemInfo("executed by","Pramesh KUmar");
		// If you want to include application url and browser name also then include propertise file code
		//and set system info with that property file data as follows
		//extentReports.setSystemInfo("Aplication URL",prop.getprperty("url"));
		//extentReports.setSystemInfo("Browser name",prop.getprperty("browsername"));
		
		extentReports.setSystemInfo("Java Version",System.getProperty("java.version"));
		extentReports.setSystemInfo("Operating System",System.getProperty("os.name"));
		extentReports.setSystemInfo("user name",System.getProperty("user.name"));

		return extentReports;
		
	}
}
