package com.QaUtils;

import java.util.Date;

public class Utilities {

	public static String generate_Emai_With_TimeStamp() {
		Date d = new Date();
		String timeStamp = d.toString().replace(" ","_").replace(":","_");
		return "sh"+timeStamp+"@gamil.com";
	}
}
