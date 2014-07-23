/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khamals.reports.tables;

import Database.DatabaseHelper;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;

/**
 *
 * @author MINGO LAWRENCE
 */
public class TestDeHSSFXSSF {

    
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws IOException, SQLException {
        DatabaseHelper data = new DatabaseHelper();
        String query = "SELECT * FROM PRODUCT";
    }
    
    
    public static void showOfficeInstalled()   {
        try {
        Process p = Runtime.getRuntime().exec
          (new String [] { "cmd.exe", "/c", "assoc", ".xls"});
        BufferedReader input =
          new BufferedReader
            (new InputStreamReader(p.getInputStream()));
        String extensionType = input.readLine();
        input.close();
        // extract type
        if (extensionType == null) {
          System.out.println("no office installed ?");
          System.exit(1);
        }
        String fileType[] = extensionType.split("=");

        p = Runtime.getRuntime().exec
          (new String [] { "cmd.exe", "/c", "ftype", fileType[1]});
        input =
          new BufferedReader
            (new InputStreamReader(p.getInputStream()));
        String fileAssociation = input.readLine();
        // extract path
        String officePath = fileAssociation.split("=")[1];
        System.out.println(officePath);
      }
      catch (IOException err) {
//        err.printStackTrace();
          System.out.println(err.getMessage());
      }
    }
     

    // This method uses a databasehelper, takes the query, writes to a given
    // workbook on  a sheet and parses the query by the query's Identifier

    /**
     *
     * @param database
     * @param query
     * @param sheetName
     * @param queryString
     * @throws java.io.IOException
     */
        public static void createEXCELSheet(DatabaseHelper database,
            String query, String sheetName, String queryString) throws IOException {
        Date dNow = new Date( );
        SimpleDateFormat ft = 
        new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");

        System.out.println("Current Date: " + ft.format(dNow));
        
        String finalDate = ft.format(dNow);
        
        try {
//            sheetName = "My First Sheet";
            database.setQuery(query);

            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet1 = wb.createSheet(sheetName);
            System.out.println("Width of columns is: " + sheet1.getDefaultColumnWidth());
            
            HSSFRow rowStarters = sheet1.createRow(0);  // First row or Header
            HSSFRow rowedes;         // Next set of rows
            
            CellStyle lockedCellStyle = wb.createCellStyle();
            lockedCellStyle.setLocked(true);
            lockedCellStyle.setWrapText(true);
            lockedCellStyle.setBorderBottom((short)3);
            lockedCellStyle.setBorderTop((short)3);
            lockedCellStyle.setBorderLeft((short)3);
            lockedCellStyle.setBorderRight((short)3);
            lockedCellStyle.setFillBackgroundColor((short)45);
            lockedCellStyle.setWrapText(true);
            
            String detailSummarySalesString[] = {
                "TRANSACTION ID", "TRANSACTION DATE", "CUSTOMER", "CASHIER", "TOTAL AMOUNT",
                "DISCOUNT", "AMOUNT PAID", "PRODUCT", "PRODUCT CODE", "SELLING PRICE",
                "QUANTITY AVAILABLE", "QUANTITY SOLD"
            };

            String summarySummarySalesString[] = {
                "TRANSACTION ID", "TOTAL PAID", "CUSTOMER", "CASHIER",
                "PRODUCT CODE", "PRODUCT_NAME", "SELLING PRICE"
            };
            
            String detailWeeklyString[] = {
                "TRANSACTION ID", "TRANSACTION DATE", "CUSTOMER", "CASHIER", "TOTAL AMOUNT",
                "DISCOUNT", "AMOUNT PAID", "PRODUCT", "PRODUCT CODE", "SELLING PRICE",
                "QUANTITY AVAILABLE", "QUANTITY SOLD"
            };

            String summaryWeeklyString[] = {
                "TRANSACTION ID", "TOTAL PAID", "CUSTOMER", "CASHIER",
                "PRODUCT CODE", "PRODUCT", "SELLING PRICE"
            };
            
            String staffSalesString[] = {
                "CASHIER", "PRODUCT", "PRODUCT CODE", 
                "CUSTOMER NAME", "TOTAL_AMOUNT_PAID "             
            };
            
            String salesByCustomers[] = {
                "CUSTOMER", "TRANSACTION DATE", "PRODUCT",
                "SELLING PRICE", "QUANTITY SOLD", "TOTAL PAID"
            };

            String salesByQtyString[] = {
                "TRANSACTION ID", "TRANSACTION DATE", "CUSTOMER", "CASHIER", "TOTAL AMOUNT",
                "DISCOUNT", "AMOUNT PAID", "PRODUCT", "PRODUCT CODE", "SELLING PRICE",
                "QUANTITY AVAILABLE", "QUANTITY SOLD"
            };
            
            String salesByGrossCostString[] = {
                 "TRANSACTION DATE", "CUSTOMER", "TOTAL AMOUNT",
                 "DISCOUNT", "AMOUNT PAID", "CASHIER"
                
            };
            
            String salesByDateSoldString[] = {
                "TRANSACTION DATE", "CUSTOMER", "PRODUCT","QUANTITY SOLD",
                   "CASHIER", "TOTAL AMOUNT", "DISCOUNT", "AMOUNT PAID"
            };
            
            String salesByLastQtyString[] = {
                 "TRANSACTION DATE", "QUANTITY SOLD", "PRODUCT", "CUSTOMER", "CASHIER"
            };
            
            String salesBySellingPriceString[] = {
                "TRANSACTION ID", "TRANSACTION DATE", "CUSTOMER", "CASHIER",
                "TOTAL AMOUNT", "DISCOUNT", "AMOUNT PAID", "PRODUCT", 
                "PRODUCT CODE", "SELLING PRICE", "QUANTITY AVAILABLE",
                 "QUANTITY SOLD" 
            };
            
            switch (queryString) {
                case "DETAILED TOTALS OF SALES":
                    for (int i = 0; i < 12; i++) {
                        rowStarters.createCell(i).setCellValue(detailSummarySalesString[i]);
                        rowStarters.getCell(i).setCellStyle(lockedCellStyle);
                        //sheet1.setColumnWidth(i, rowStarters.getCell(i).getStringCellValue().length() * 100);
                        sheet1.setColumnWidth(i, 4000);
                    }
                break;
                    
                case "SUMMARY TOTALS OF SALES":
                    for (int i = 0; i < 7; i++) {
                        rowStarters.createCell(i).setCellValue(summarySummarySalesString[i]);
                        rowStarters.getCell(i).setCellStyle(lockedCellStyle);
                        //sheet1.setColumnWidth(i, rowStarters.getCell(i).getStringCellValue().length() * 100);
                        sheet1.setColumnWidth(i, 4000);
                    }
                break;
                    
                case "DETAILED WEEKLY REPORT":
                    for (int i = 0; i < 12; i++) {
                        rowStarters.createCell(i).setCellValue(detailWeeklyString[i]);
                        //sheet1.setColumnWidth(i, rowStarters.getCell(i).getStringCellValue().length() * 100);
                        sheet1.setColumnWidth(i, 4000);
                    }
                break;
                    
                case "SUMMARY WEEKLY REPORT":
                    for (int i = 0; i < 7; i++) {
                        rowStarters.createCell(i).setCellValue(summaryWeeklyString[i]);
                        rowStarters.getCell(i).setCellStyle(lockedCellStyle);
                        //sheet1.setColumnWidth(i, rowStarters.getCell(i).getStringCellValue().length() * 100);
                    }
                break;
                    
                case "STAFF SALES":
                    for (int i = 0; i < 5; i++) {
                        rowStarters.createCell(i).setCellValue(staffSalesString[i]);
                        rowStarters.getCell(i).setCellStyle(lockedCellStyle);
                        //sheet1.setColumnWidth(i, rowStarters.getCell(i).getStringCellValue().length() * 100);
                        sheet1.setColumnWidth(i, 4000);
                    }
                break;
                    
                case "SALES BY QUANTITY":
                        for (int i = 0; i < 12; i++) {
                        rowStarters.createCell(i).setCellValue(salesByQtyString[i]);
                        rowStarters.getCell(i).setCellStyle(lockedCellStyle);
                        //sheet1.setColumnWidth(i, rowStarters.getCell(i).getStringCellValue().length() * 100);
                        sheet1.setColumnWidth(i, 4000);
                    }
                break;
                    
                case "SALES BY COST":
                        for (int i = 0; i < 6; i++) {
                        rowStarters.createCell(i).setCellValue(salesByGrossCostString[i]);
                        rowStarters.getCell(i).setCellStyle(lockedCellStyle);
                        //sheet1.setColumnWidth(i, rowStarters.getCell(i).getStringCellValue().length() * 100);
                        sheet1.setColumnWidth(i, 4000);
                    }
                break;
                    
                case "SALES BY DATE SOLD":
                        for (int i = 0; i < 8; i++) {
                        rowStarters.createCell(i).setCellValue(salesByDateSoldString[i]);
                        rowStarters.getCell(i).setCellStyle(lockedCellStyle);
                        //sheet1.setColumnWidth(i, rowStarters.getCell(i).getStringCellValue().length() * 100);
                        sheet1.setColumnWidth(i, 4000);
                    }
                break; 
                    
                case "SALES BY LAST QUANTITY SOLD":
                        for (int i = 0; i < 5; i++) {
                        rowStarters.createCell(i).setCellValue(salesByLastQtyString[i]);
                        rowStarters.getCell(i).setCellStyle(lockedCellStyle);
                        //sheet1.setColumnWidth(i, rowStarters.getCell(i).getStringCellValue().length() * 100);
                        sheet1.setColumnWidth(i, 4000);
                    }
                break; 
                    
                case "SALES BY SELLING PRICE":
                    for (int i = 0; i < 12; i++) {
                        rowStarters.createCell(i).setCellValue(salesBySellingPriceString[i]);
                        rowStarters.getCell(i).setCellStyle(lockedCellStyle);
                        HSSFCell celln = rowStarters.getCell(i);
                        //sheet1.setColumnWidth(i, rowStarters.getCell(i).getStringCellValue().length() * 100);
                        System.out.println(celln.getCellStyle().toString());
                        sheet1.setColumnWidth(i, 4000);
                    }
                break;
                    
                case "SALES DONE PER CUSTOMER":
                    for (int i = 0; i < 6; i++) {
                        rowStarters.createCell(i).setCellValue(salesByCustomers[i]);
                        //sheet1.setColumnWidth(i, rowStarters.getCell(i).getStringCellValue().length() * 100);
                        HSSFCell celln = rowStarters.getCell(i);
                        System.out.println(celln.getCellStyle().toString());
                        sheet1.setColumnWidth(i, 4000);
                    }
            }

            for (int i = 0; i < database.numberOfRows; i++) {
                rowedes = sheet1.createRow(i + 1);
                for (int j = 0; j < database.getColumnCount(); j++) {
                    rowedes.createCell(j).setCellValue(database.getValueAt(i, j).toString());
                    rowedes.setHeight((short)300);
                    rowedes.getCell(j).setCellStyle(lockedCellStyle);
                    
                    System.out.printf("%s\t", database.getValueAt(i, j).toString());
                }
                System.out.println("\n");

            }

            FileOutputStream fileOut = null;
            String doroMe = "D:\\Business Reports\\" + queryString + "\\" + 
                "Report generated on " + finalDate.replace(".", "_").replace(":", "_") + ".xls";
            try {
/**
 * This Block does three things. The first is to write out all the data obtained from the database
 * unto the sheet which eventually gets attached unto the workbook. Secondly We create the directory
 * where the files will live. In fact if they're not yet created, they get created and added to the 
 * directory. The final thing is 
 */                   
 
	File files = new File("D:\\Business Reports\\" + queryString );
	if (files.exists()) {
	} else {
            if (files.mkdirs()) {
                System.out.println("Multiple directories are created!");
            } else {
                System.out.println("Failed to create multiple directories!");
            } 
                } 
                
                
                fileOut = new FileOutputStream(doroMe);
            try {
                wb.write(fileOut);
                fileOut.flush();
                fileOut.close();
                try {
                    System.out.println(""+wb.isWriteProtected());  
                     wb.writeProtectWorkbook("thisisnotjustokaydotcom", "thisisnotjustokaydotcom");
                System.out.println(""+wb.isWriteProtected());  
                 sheet1.protectSheet("");
                    System.out.println(sheet1.getProtect());
                    
                    System.out.println(""+sheet1.getPassword());
                    
                    
                } catch (Exception e) {
                    System.out.println("eror is: " + e.getMessage());
                System.out.println(""+wb.isWriteProtected());  
                    
                }

            } catch (IOException ex) {
                Logger.getLogger(TestDeHSSFXSSF.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        
                 
            } catch (FileNotFoundException ex) {
                Logger.getLogger(TestDeHSSFXSSF.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
            try{       
                
//          String[] cmdarray=new String[]{"cmd.exe","/c","D:\\Business Reports\\" + queryString + "\\" + 
//                "Report generated on " + finalDate.replace(".", "_").replace(":", "_") + ".xls"}; 
//          Runtime.getRuntime().exec("cmd /c start \"\"\"D:\\\\Business Reports\\\\\" + queryString + \"\\\\\" + \n" +
//"\"Report generated on \" + finalDate.replace(\".\", \"_\").replace(\":\", \"_\") + \".xls\"");
          
          
                Desktop.getDesktop().open(new File("D:\\Business Reports\\" + queryString + "\\" + 
                "Report generated on " + finalDate.replace(".", "_").replace(":", "_") + ".xls"));
          }catch(IOException  e){  
 //             e.printStackTrace();
                System.out.println(e.getMessage());
          }
//
        } catch (SQLException | IllegalStateException ex) {
            Logger.getLogger(TestDeHSSFXSSF.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
}
