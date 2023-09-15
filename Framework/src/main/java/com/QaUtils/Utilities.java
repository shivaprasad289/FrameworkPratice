package com.QaUtils;

import java.util.Date;

public class Utilities {
	
	public final static int IMPLICIT_WAIT_TIME = 10;
	public final static int PAGE_LOAD_TIME = 5;
	public static String generate_Emai_With_TimeStamp() {
		Date d = new Date();
		String timeStamp = d.toString().replace(" ","_").replace(":","_");
		return "sh"+timeStamp+"@gamil.com";
	}
}
