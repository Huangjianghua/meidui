package com.meiduimall.application.search.utility;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

public class PlatformUtil {
	private static final Log log = LogFactory.getLog(PlatformUtil.class);
	
	@SuppressWarnings("unused")
	public void printPlatform(Object obj) throws IOException{
		
		
		File resultFile = null;  
        HSSFWorkbook wb = null;  
        FileOutputStream fos = null;  
        HSSFSheet sheet = null;  
        HSSFRow row = null;  
        HSSFCell cell = null;  
        HSSFCellStyle cellType = null; 
        
        resultFile = new File("D:/报表.xls");  
        // create a workbook  
        wb = new HSSFWorkbook();  
        // create a cell style  
        cellType = wb.createCellStyle();  
        cellType.setBorderTop(HSSFCellStyle.BORDER_THIN);  
        cellType.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
        cellType.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
        cellType.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        cellType.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        cellType.setFillForegroundColor(HSSFColor.GOLD.index);  
        cellType.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
        
        //报表名称
        sheet = wb.createSheet("这是报表");   

        //创建key栏，就是创建一行，0表示第一行，这行主要放各种属性; 
        row = sheet.createRow(0); 
        for(int i = 0;i<5;i++){ 
	        row.createCell(i).setCellValue("属性"+i); 
        }
        
        //将各个对象值放入其中
        for(int j = 1 ;j<2; j++){
        	row = sheet.createRow(j); 
	        for(int i = 0;i<5; i++){
	        	row.createCell(i).setCellValue(i+"对象的值");
	        }
        }
        
        fos = new FileOutputStream(resultFile); 
        
        // save workbook  
        wb.write(fos);  
        fos.flush();  
        fos.close();  
	}
}
