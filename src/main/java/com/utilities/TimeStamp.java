package com.utilities;

import java.util.Date;
import java.util.Random;

public class TimeStamp {
	
	public static String  timeStamp() {
		Date d=new Date();
		String timeStamp=d.toString().replace(" ","_").replace(":","_");
		return timeStamp;
	}
	
	public static int randomnumber() {
		Random r=new Random();
		int randomNumber=r.nextInt(100);
		return randomNumber;
	}

}
