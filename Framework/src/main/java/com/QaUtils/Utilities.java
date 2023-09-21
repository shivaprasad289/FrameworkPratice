package com.QaUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utilities {

	public final static int IMPLICIT_WAIT_TIME = 10;
	public final static int PAGE_LOAD_TIME = 5;

	public static String generate_Emai_With_TimeStamp() {
		Date d = new Date();
		String timeStamp = d.toString().replace(" ", "_").replace(":", "_");
		return "sh" + timeStamp + "@gamil.com";
	}

	public static Object[][] read_All_Data_From_Excel(String sheetName) {
		XSSFWorkbook wb = null;
		try {
			FileInputStream f = new FileInputStream(new File(".\\src\\main\\java\\com\\testData\\TestData.xlsx"));
			wb = new XSSFWorkbook(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
		XSSFSheet sheet = wb.getSheet(sheetName);
		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();
		Object[][] data = new Object[rows][cols];
		for (int i = 0; i < rows; i++) {
			XSSFRow col = sheet.getRow(i + 1);
			for (int j = 0; j < cols; j++) {
				XSSFCell cell = col.getCell(j);
				CellType dataType = cell.getCellType();
				switch (dataType) {
				case STRING:
					data[i][j] = cell.getStringCellValue().toString();
					break;
				case NUMERIC:
					data[i][j] = Integer.toString((int) cell.getNumericCellValue());
					break;
				case BOOLEAN:
					data[i][j] = cell.getBooleanCellValue();
				default:
					break;
				}
			}
		}
		return data;
	}
	
	public static String captureScreenShot(WebDriver driver,String testName) {
		File sec = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String descPath = ".\\Screenshots\\"+testName+".png";	
		File desc = new File(descPath);
		try {
			FileHandler.copy(sec, desc);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return descPath;
	}
}
