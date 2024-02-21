package com.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import com.microsoft.schemas.office.visio.x2012.main.CellType;

public class DataUtil {
	
	@DataProvider(name="hashDataProvider")
	public static Object[][] GetTestData(MyXLSReader xls_received, String testName, String sheetName) throws Exception{
		
		MyXLSReader xls = xls_received;
	
		String testCaseName = testName;
		
		String testDataSheet = sheetName;
		
		int testStartRowNumber=1;		
		
		while(!(xls.getCellData(testDataSheet, 1, testStartRowNumber).equals(testCaseName))){
			
			testStartRowNumber++;
			
		}
		
		int columnStartRowNumber = testStartRowNumber+1;
		int dataStartRowNumber = testStartRowNumber+2;
		
		int rows=0;
		while(!(xls.getCellData(testDataSheet, 1, dataStartRowNumber+rows).equals(""))){
			
			rows++;
			
		}
		
		//Total number of columns in the required test
		int columns=1;
		
		while(!(xls.getCellData(testDataSheet, columns, columnStartRowNumber).equals(""))){
			
			columns++;
			
		}
		
		Object[][] obj = new Object[rows][1];
		
		HashMap<String,String> map = null;
		
		//Reading the data in the test
		for(int i=0,row=dataStartRowNumber;row<dataStartRowNumber+rows;row++,i++){
			
			map = new HashMap<String,String>();
			
			for(int j=0,column=1;column<columns;column++,j++){
				
				String key=xls.getCellData(testDataSheet, column, columnStartRowNumber);
				
				String value=xls.getCellData(testDataSheet, column, row);
				
				map.put(key,value);
				
			}
			
			obj[i][0]=map;
		
		}	
		
		return obj;
	
	}
	
	public static boolean isRunnable(MyXLSReader xls_received, String tName, String sheet){
		
		String sheetName = sheet;

		MyXLSReader xls = xls_received;
		
		int rows = xls.getRowCount(sheetName);
		
		for(int r=2;r<=rows;r++){
			
			String testName = xls.getCellData(sheetName, 1, r);
			
			if(testName.equals(tName)){
				
				String runmode = xls.getCellData(sheetName, "Runmode", r);
				
				if(runmode.equals("Y"))					
					return true;
				else
					return false;	
			}	
		}
		return false;
	}
	
	public static Object[][] getTestDataFromExcel(String sheetName) {
		File excelFile = new File(System.getProperty("user.dir")+"//src//test//resources//TestData.xlsx");
		XSSFWorkbook workbook = null;
		try {
			FileInputStream fisExcel = new FileInputStream(excelFile);
			workbook = new XSSFWorkbook(fisExcel);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		XSSFSheet sheet = workbook.getSheet(sheetName);
		
		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();
		
		Object[][] data = new Object[rows][cols];
		
		for(int i=0;i<rows;i++) {
			
			XSSFRow row = sheet.getRow(i+1);
			
			for(int j=0;j<cols;j++) {
				
				XSSFCell cell = row.getCell(j);
				int cellType = cell.getCellType();
				
				
			/*	switch(cellType) {
				
				case STRING:
					data[i][j] = cell.getStringCellValue();
					break;
				case NUMERIC:
					data[i][j] = Integer.toString((int)cell.getNumericCellValue());
					break;
				case BOOLEAN:
					data[i][j] = cell.getBooleanCellValue();
					break;	
		}  */
				}
				
			}
		
		return data;
		
	}
	
}