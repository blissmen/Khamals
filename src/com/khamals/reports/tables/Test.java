/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khamals.reports.tables;

import Database.DatabaseHelper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author MINGO LAWRENCE
 */
public class Test {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        DatabaseHelper dbHelp = new DatabaseHelper();
        String stri = "SELECT * FROM PRODUCT";
        dbHelp.setQuery(stri);
        
//        displayData(result);
    }

        private static void displayData(DatabaseHelper dbh) throws SQLException {
        // assuming the cursor before the first row
            
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet1;
            sheet1 = wb.createSheet("Products");
            HSSFRow rowStarters = sheet1.createRow(0);
            HSSFRow rowContinues = sheet1.createRow(1);

            ResultSet result;
            result = dbh.getResultSet();
        
        int index = 1;
        while (result.next()) {            
            StringBuilder build = new StringBuilder();
            StringBuilder returnValues;  
            returnValues = build.append("COST_PRICE")  
// using binary positioning in the getInt() method is appreciated
                        .append(result.getInt(0b1)).append(":  ");

            String userName = result.getString("NAME");
            returnValues.append(" ").append(userName);
            
            String cat = result.getString("CATEGORY ");
            returnValues.append("   ").append(cat);
            
            String desc = result.getString("DESCRIPTION");
            returnValues.append("   ").append(desc);
            
            String codeName = result.getString("CODE");
            returnValues.append(" ").append(codeName);
            
            System.out.println(returnValues);
            String qty = result.getString("QUANTITY_AVAILABLE");
            returnValues.append("   ").append(qty);
            
            String last_sale_date = result.getString("LASTLY_SOLD_ON");
            returnValues.append("   ").append(last_sale_date);
            
            String min_sell = result.getString("MIN_SELLING_PRICE");
            returnValues.append(" ").append(min_sell);
            
            String last_add_date = result.getString("LASTLY_ADDED_ON");
            returnValues.append("   ").append(last_add_date);
            
            String last_qty = result.getString("LAST_QUANTITY_SOLD");
            returnValues.append("   ").append(last_qty);
            
            String sell_price = result.getString("SELLING_PRICE");
            returnValues.append("   ").append(sell_price);
            
            System.out.println(returnValues);
            
            
            rowStarters.createCell( 0).setCellValue("COST PRICE");
            rowStarters.createCell( 1).setCellValue("NAME");
            rowStarters.createCell( 2).setCellValue("CATEGORY");
            rowStarters.createCell( 3).setCellValue("DESCRIPTION");
            rowStarters.createCell( 4).setCellValue("CODE");
            rowStarters.createCell( 5).setCellValue("QUANTITY_AVAILABLE");
            rowStarters.createCell( 6).setCellValue("LASTLY_SOLD_ON");
            rowStarters.createCell( 7).setCellValue("MIN_SELLING_PRICE");
            rowStarters.createCell( 8).setCellValue("LASTLY_ADDED_ON");
            rowStarters.createCell( 9).setCellValue("LAST_QUANTITY_SOLD");
            rowStarters.createCell( 10).setCellValue("SELLING_PRICE");
            
            
            for (int i = 1; i < dbh.numberOfRows; i++) {
                
                for (int j = 0; j < dbh.getColumnCount(); j++) {
                    rowContinues.createCell( j-1).setCellValue(dbh.getValueAt(i, j).toString());
                    System.out.printf("%s\t", dbh.getValueAt(i, j).toString());
                }
                System.out.println("\n");
            }
            
            
            rowContinues.createCell( 0).setCellValue(result.getString(1));
            rowContinues.createCell( 1).setCellValue(result.getString(2));
            rowContinues.createCell( 2).setCellValue(result.getString(3));
            rowContinues.createCell( 3).setCellValue(result.getString(4));
            rowContinues.createCell( 4).setCellValue(result.getString(5));
            rowContinues.createCell( 5).setCellValue(result.getString(6));
            rowContinues.createCell( 6).setCellValue(result.getString(7));
            rowContinues.createCell( 7).setCellValue(result.getString(8));
            rowContinues.createCell( 8).setCellValue(result.getString(9));
            rowContinues.createCell( 9).setCellValue(result.getString(10));
            
        }
        
        try
        {
            try (FileOutputStream out = new FileOutputStream
                    (new File("Reports.xls"))) {
                wb.write(out);
                
            }
            System.out.println("Reports.xlsx got written on disk.");
        } 
        catch (IOException e) 
        {
            System.err.println("Message is:  " + e.getMessage());
            System.err.println("Code is      " + e.hashCode());
        
        }
    }
    
    
    
    
    
    
//    
//    
//    protected static void display(DatabaseHelper db) throws SQLException {
//        HSSFWorkbook book1 = new HSSFWorkbook();
//        HSSFSheet sheet1;
//        sheet1 = book1.createSheet("Products");
//        HSSFRow rowStarters = sheet1.createRow( 0);
//        HSSFRow rowContinues = sheet1.createRow( 1);
//        
//        
//        String stri = "SELECT * FROM PRODUCT";
//        
//        
//        db.setQuery(stri);
//        ResultSet result = db.getResultSet();
//        while (result.next()) {
//            StringBuilder build = new StringBuilder();
//            StringBuilder returnValues;
//            returnValues = build.append("COST_PRICE")
//                    // using binary positioning in the getInt() method is appreciated
//                    .append(result.getInt(0b1)).append(":  ");
//
//            String userName = result.getString("NAME");
//            returnValues.append(" ").append(userName);
//
//            String cat = result.getString("CATEGORY ");
//            returnValues.append("   ").append(cat);
//
//            String desc = result.getString("DESCRIPTION");
//            returnValues.append("   ").append(desc);
//
//            String codeName = result.getString("CODE");
//            returnValues.append(" ").append(codeName);
//
//            System.out.println(returnValues);
//            String qty = result.getString("QUANTITY_AVAILABLE");
//            returnValues.append("   ").append(qty);
//
//            String last_sale_date = result.getString("LASTLY_SOLD_ON");
//            returnValues.append("   ").append(last_sale_date);
//
//            String min_sell = result.getString("MIN_SELLING_PRICE");
//            returnValues.append(" ").append(min_sell);
//
//            String last_add_date = result.getString("LASTLY_ADDED_ON");
//            returnValues.append("   ").append(last_add_date);
//
//            String last_qty = result.getString("LAST_QUANTITY_SOLD");
//            returnValues.append("   ").append(last_qty);
//
//            String sell_price = result.getString("SELLING_PRICE");
//            returnValues.append("   ").append(sell_price);
//
//            rowStarters.createCell( 0).setCellValue("COST PRICE");
//            rowStarters.createCell( 1).setCellValue("NAME");
//            rowStarters.createCell( 2).setCellValue("CATEGORY");
//            rowStarters.createCell( 3).setCellValue("DESCRIPTION");
//            rowStarters.createCell( 4).setCellValue("CODE");
//            rowStarters.createCell( 5).setCellValue("QUANTITY_AVAILABLE");
//            rowStarters.createCell( 6).setCellValue("LASTLY_SOLD_ON");
//            rowStarters.createCell( 7).setCellValue("MIN_SELLING_PRICE");
//            rowStarters.createCell( 8).setCellValue("LASTLY_ADDED_ON");
//            rowStarters.createCell( 9).setCellValue("LAST_QUANTITY_SOLD");
//            rowStarters.createCell( 10).setCellValue("SELLING_PRICE");
//            
//            for (int i = 1; i < db.numberOfRows; i++) {
//                
//                for (int j = 1; j < db.getColumnCount(); j++) {
//                    rowContinues.createCell( j-1).setCellValue(db.getValueAt(i, j).toString());
//                }
//                
//            }
////            rowContinues.createCell( 0).setCellValue(db.getValueAt(1, 1).toString());
////            rowContinues.createCell( 1).setCellValue(db.getValueAt(1, 2).toString());
////            rowContinues.createCell( 2).setCellValue(result.getString(3));
////            rowContinues.createCell( 3).setCellValue(result.getString(4));
////            rowContinues.createCell( 4).setCellValue(result.getString(5));
////            rowContinues.createCell( 5).setCellValue(result.getString(6));
////            rowContinues.createCell( 6).setCellValue(result.getString(7));
////            rowContinues.createCell( 7).setCellValue(result.getString(8));
////            rowContinues.createCell( 8).setCellValue(result.getString(9));
////            rowContinues.createCell( 9).setCellValue(result.getString(10));
//        }
//
//    }
}
