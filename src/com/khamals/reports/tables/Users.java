/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * This class accesses the database and specifically the Departments table
 * All queries on this table shall be handled here!!!
 */
package com.khamals.reports.tables;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


/**
 *
 * @author MINGO LAWRENCE
 */
public class Users {
    public static void displayData(ResultSet reset) throws SQLException {
        // assuming the cursor before the first row
            
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet1 = wb.createSheet("Users of System");
            HSSFRow rowStarters = sheet1.createRow((short) 0);

        
        int index = 1;
        while (reset.next()) {            
            StringBuilder build = new StringBuilder();
            StringBuilder returnValues = build.append("User N° ")  
// using binary positioning in the getInt() method is appreciated
                    .append(reset.getInt(0b1)).append(":  ");

            String userName = reset.getString("userName");
            returnValues.append(" ").append(userName);
            
            String pwd = reset.getString("password");
            returnValues.append("   ").append(pwd);
            
            String matri = reset.getString("matricule");
            returnValues.append("   ").append(matri);
            
            System.out.println(returnValues);
            
            rowStarters.createCell((short) 0).setCellValue("matricule");
            rowStarters.createCell((short) 1).setCellValue("User N°");
            
            rowStarters.createCell((short) 1).setCellValue(reset.getString(1));
            rowStarters.createCell((short) 0).setCellValue(reset.getString(2));
        }
        
        try
        {
            try (FileOutputStream out = new FileOutputStream
                    (new File("Reports.xlsx"))) {
                wb.write(out);
            }
            System.out.println("Reports.xlsx got written on disk.");
        } 
        catch (Exception e) 
        {
            System.err.println("Message is:  " + e.getMessage());
            System.err.println("Code is      " + e.hashCode());
        
        }
    
    }
}
    

