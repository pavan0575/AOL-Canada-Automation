package com.Automation_Framework.frameworkengine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelLibrary {
	public int getRowCount(String sheetName) throws EncryptedDocumentException,
			InvalidFormatException, IOException {
		int retVal = 0;
		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+
					"//src//com//Automation_Framework//xlsx//Contact.xlsx");
			Workbook wb = WorkbookFactory.create(fis);
			Sheet s = wb.getSheet(sheetName);
			retVal = s.getLastRowNum();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return retVal;
	}

	public String getExcelData(String sheetName, int rowNum, int cellNum) {
		String retVal = null;
		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+
					"//src//com//Automation_Framework//xlsx//Contact.xlsx");
			Workbook wb = WorkbookFactory.create(fis);
			Sheet s = wb.getSheet(sheetName);
			Row r = s.getRow(rowNum);
			Cell c = r.getCell(cellNum);
			retVal = c.getStringCellValue();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return retVal;
	}

	public void writeToExcel(String sheetName, int rowNum, int cellNum,
			String val) {
		try {
			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir")
							+ "//src//com//Automation_Framework//xlsx//Contact.xlsx");
			Workbook wb = WorkbookFactory.create(fis);
			Sheet s = wb.getSheet(sheetName);
			Row r = s.getRow(rowNum);
			CellStyle style = wb.createCellStyle();
			if (val.equals("FAIL")) {
				style.setFillForegroundColor(IndexedColors.RED.getIndex());
			} else {
				style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
			}
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			Cell c = r.createCell(cellNum);
			c.setCellType(c.CELL_TYPE_STRING);
			c.setCellValue(val);
			c.setCellStyle(style);
			FileOutputStream fos = new FileOutputStream(
					System.getProperty("user.dir")
							+ "//src//com//Automation_Framework//xlsx//Contact.xlsx");
			wb.write(fos);
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
