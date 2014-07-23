/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import CustomersDebtors.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

/**
 *
 * @author Harvey
 */
public class DatabaseHelper {

    public Connection connection;
    public ResultSet resultSet;
     public ResultSetMetaData metaData;
    public int numberOfRows;
    public Statement statement;
     public boolean connectedToDatabase;
     public ResultSet result;
     public ResultSetMetaData metadata;

    public DatabaseHelper() throws SQLException {

        this.numberOfRows = 0;
        this.statement = null;
        this.connectedToDatabase = false;
        this.resultSet = null;
        this.metaData = null;
        this.connection = null;

        createDB();//create the database if it does not exist
        // update database connection status
        connectedToDatabase = true;
    }

    public final void setQuery(String query)
            throws SQLException, IllegalStateException {
        // ensure database connection is available
        if (!connectedToDatabase) {
            throw new IllegalStateException("Not Connected to Database");
        }

        // specify query and execute it WATCH OUT: statement.executequery doesnot 
//        run on queries that return number of rows such as inserts, so I use statement.execute()
        statement.execute(query);
        resultSet = statement.getResultSet();
        if (resultSet != null) {//a resultset is null if it is an update, count, and I think insert
            // determine number of rows in ResultSet
            resultSet.last(); // move to last row
            numberOfRows = resultSet.getRow(); // get row number
            metaData = resultSet.getMetaData();
        }else{
            numberOfRows = 0;
        }

    } // end method setQuery

    public int getColumnCount() throws IllegalStateException {
        // ensure database connection is available
        if (!connectedToDatabase) {
            throw new IllegalStateException("Not Connected to Database");
        }

        // determine number of columns
        try {
            return metaData.getColumnCount();
        } // end try
        catch (SQLException sqlException) {
        } // end catch

        return 0; // if problems occur above, return 0 for number of columns
    } // end method getColumnCount

    // get name of a particular column in ResultSet
    public String getColumnName(int column) throws IllegalStateException {
        // ensure database connection is available
        if (!connectedToDatabase) {
            throw new IllegalStateException("Not Connected to Database");
        }

        // determine column name
        try {
            return metaData.getColumnName(column + 1);
        } // end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } // end catch

        return ""; // if problems, return empty string for column name
    } // end method getColumnName
    // return number of rows in ResultSet

    public int getRowCount() throws IllegalStateException {
        // ensure database connection is available

        if (!connectedToDatabase) {
            throw new IllegalStateException("Not Connected to Database");
        }

        return numberOfRows;
    } // end method getRowCount

    public Object getValueAt(int row, int column)
            throws IllegalStateException {
        // ensure database connection is available
        if (!connectedToDatabase) {
            throw new IllegalStateException("Not Connected to Database");
        }

        // obtain a value at specified ResultSet row and column
        try {
            resultSet.absolute(row + 1);
            return resultSet.getObject(column + 1);
        } // end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } // end catch

        return ""; // if problems, return empty string object
    } // end method getValueAt

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public void disconnectFromDatabase() {
        if (connectedToDatabase) {
// close Statement and Connection
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } // end try
            catch (SQLException sqlException) {
            } // end catch
            finally // update database connection status
            {
                connectedToDatabase = false;
            } // end finally
        } // end if
    } // end method disconnectFromDatabase
    
    
    public int Query(String sql)
    {
        try{
            statement = connection.createStatement();
            System.out.println(sql);
            return  statement.executeUpdate(sql);
        
        }
        catch(SQLException pp)
        {
            System.out.println(sql);
            System.out.println(pp.toString());
        }
        return 0;
    
    
    }
    public ArrayList ExecuteQuery(String sql) throws SQLException
    {
        ArrayList resultSet = new  ArrayList();
       try
       { 
           if(connection!=null)
           {
      statement = connection.createStatement();
        result = statement.executeQuery(sql);
       metadata = result.getMetaData();
     //  String[] res=null;
       int numcolls = metadata.getColumnCount();
       while(result.next())
       {
       
       for ( int i = 1; i <= numcolls; i++ )
       {
            
             System.out.println(i+":"+result.getObject(i));  
            
             resultSet.add(result.getObject(i));
       
            
       }
       }
           }
       }
       
       
       
       catch(SQLException pp)
       {
           System.out.println(pp.toString());
       }
       
       
        return resultSet;
       }

    /**
     *
     * @param sql
     * @return
     * @throws SQLException
     */
    
       
       
       
    
        
    

    private void createDB()  {
        try {
            String userHomeDir = System.getProperty("user.home", ".");
            String systemDir = userHomeDir + "/.khamals";
            // Set the db system directory.
            System.setProperty("derby.system.home", systemDir);
            
            String url = "jdbc:derby:khamals;create=true";
            Properties props = new Properties();
            props.put("user", "khamals");
            
            try {
                //Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
                Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
                connection = DriverManager.getConnection(url, props);
                statement = connection.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println(e.toString());
            }
            
            String query;
            String qu;
       
            qu = "CREATE TABLE KHAMALS.LOGIN ("
                    + "	USER_ID VARCHAR(50) NOT NULL,"
                    + "	FIRST_NAME VARCHAR(15) NOT NULL,"
                    + "	LAST_NAME VARCHAR(15) NOT NULL,"
                    + "	TELEPHONE INTEGER NOT NULL,"
                    + "	PD VARCHAR(50) NOT NULL,"
                    + "	ADDRESS VARCHAR(50) NOT NULL,"
                    + "	STATUS INTEGER NOT NULL,"
                    + "	LAST_LOGIN DATE NOT NULL,"
                    + "	PRIMARY KEY (USER_ID)"
                    + ")";
                try{ statement.execute(qu);
                 System.out.println(qu);
                }
                catch(Exception ds)
                {
                    System.out.println(ds.toString());
                }
                 query = "CREATE TABLE KHAMALS.PRODUCT ("
                    + "	CODE VARCHAR(15) NOT NULL,"
                    + "	NAME VARCHAR(50) NOT NULL,"
                    + "	CATEGORY VARCHAR(20) NOT NULL,"
                    + "	DESCRIPTION VARCHAR(200),"
                    + "	QUANTITY_AVAILABLE INTEGER NOT NULL,"
                    + "	COST_PRICE INTEGER NOT NULL,"
                    + "	MIN_SELLING_PRICE INTEGER NOT NULL,"
                    + "	LASTLY_ADDED_ON DATE NOT NULL,"
                    + " QUANTITY_ADDED INTEGER NOT NULL,"
                    + "	LASTLY_SOLD_ON DATE,"
                    + "	LAST_QUANTITY_SOLD INTEGER,"
                    + "	SELLING_PRICE INTEGER,"
                    + "	PRIMARY KEY (CODE)"
                    + ")";

            statement.execute(query);
                statement.execute(query);
            
                String  query2= "CREATE TABLE KHAMALS.CUSTOMERS ("
                        + "FIRST_NAME VARCHAR(45) NOT NULL,"
                        + "LAST_NAME VARCHAR(45) NOT NULL,"
                        + "LOCATION VARCHAR(45),"
                        + "IMAGE BLOB(2147483647),"
                        + "TELEPHONE VARCHAR(45),"
                        + "DATE DATE,"
                        + "ID INTEGER,"
                        + "TYPE VARCHAR(45),"
                        + "EMAIL VARCHAR(45),"
                        + "CODE VARCHAR(45)"
                        + ")";
                
               statement.execute(query2);
               
                    query = " CREATE TABLE KHAMALS.DEBTORS ("
                            + "NAME VARCHAR(45),"
                            + "DATE_TACKEN DATE NOT NULL,"
                            + "LATEST_DATE_OF_PAYMENT DATE NOT NULL,"
                            + "AMOUNT INTEGER NOT NULL,"
                            + "DESCRIPTION LONG VARCHAR NOT NULL,"
                            + "ID INTEGER,"
                            + "STATUS VARCHAR(45) DEFAULT 'Not Paid' NOT NULL)";
                    
                    
                    statement.execute(query);
                    
                    query= "INSERT INTO KHAMALS.DEBTORS(NAME, DATE_TACKEN, LATEST_DATE_OF_PAYMENT, AMOUNT, DESCRIPTION, ID, STATUS) "
                            + "VALUES ('Tangwe Caleb ojang', '2014-02-01', '2014-02-03', 0, 'Alot of earings', 12457854, 'paid'),"
                            + "('Tangwe tCaleb ojang', '2014-02-01', '2014-02-03', 0, 'Alot of earings', 12457854, 'paid'),"
                            + "('tTangwe tCaleb ojang', '2014-02-01', '2014-02-03', 0, 'Alot of earings', 12457854, 'partial'),"
                            + "('Tangwe Caleb ojang', '2002-02-02', '2013-02-03', 0, '', 12457854, 'Not Paid'),"
                            + "('Tangwe Caleb ojang', '2002-02-02', '2013-02-03', 0, '', 12457854, 'Not Paid'),"
                            + "('Tangwe Caleb ojang', '2002-02-02', '2013-02-03', 0, '', 12457854, 'Not Paid'),"
                            + "('Tangwe Caleb ojang', '2002-02-02', '2013-02-03', 0, '', 12457854, 'Not Paid'),"
                            + "('Tangwe Caleb ojang', '2002-02-02', '2013-02-03', 0, '', 12457854, 'Not Paid'),"
                            + "('Tangwe Caleb ojang', '2014-07-09', '2013-02-03', 0, '', 12457854, 'Not Paid'),"
                            + "('Tangwe Caleb ojang', '2014-07-09', '2014-07-09', 0, '', 12457854, 'Not Paid')";
                    
                    statement.execute(query);
                    
               
                
            
            query = "CREATE TABLE KHAMALS.MEASUREMENTS ("
                    + "	CUSTOMER_ID INTEGER NOT NULL,"
                    + "	BURST DOUBLE NOT NULL,"
                    + "	WAIST DOUBLE NOT NULL,"
                    + "	HIPS DOUBLE NOT NULL,"
                    + "	SLEEVE DOUBLE NOT NULL,"
                    + "	BACK DOUBLE NOT NULL,"
                    + "	ARM DOUBLE NOT NULL,"
                    + "	CHEST DOUBLE NOT NULL,"
                    + "	LENGTH DOUBLE NOT NULL,"
                    + "	BAND DOUBLE NOT NULL,"
                    + "	DATE DATE NOT NULL,"
                    + "	PRIMARY KEY (CUSTOMER_ID)"
                    + ")";

            statement.execute(query);

            query = "CREATE TABLE KHAMALS.SALES_GENERAL ("
                    + "	TRANSACTION_ID INTEGER NOT NULL,"
                    + "	CASHIER_NAME VARCHAR(45) NOT NULL,"
                    + "	CUSTOMER_NAME VARCHAR(45) NOT NULL,"
                    + "	TRANSACTION_DATE DATE,"
                    + "	TOTAL_AMOUNT INTEGER,"
                    + "	DISCOUNT INTEGER,"
                    + "	TOTAL_AMOUNT_PAID INTEGER,"
                    + "	PRIMARY KEY (TRANSACTION_ID)"
                    + ")";

            statement.execute(query);
            
            
            query = "CREATE TABLE KHAMALS.SALES_DETAILED ("
                    + "	TRANSACTION_ID INTEGER NOT NULL,"
                    + "	PRODUCT_NAME VARCHAR(50) NOT NULL,"
                    + "	PRODUCT_CODE VARCHAR(15) NOT NULL,"
                    + "	SELLING_PRICE INTEGER NOT NULL,"
                    + "	QUANTITY_AVAILABLE INTEGER,"
                    + "	QUANTITY_TO_BE_SOLD INTEGER,"
                    + "	PRICE INTEGER"                                        
                    + ")";

           statement.execute(query);
           
            query = "CREATE TABLE KHAMALS.ORDERS ("
                    + "	ORDER_NUMBER INTEGER NOT NULL GENERATED ALWAYS "
                    + " AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                    + "	CUSTOMER_ID INTEGER NOT NULL,"
                    + "	USER_ID INTEGER NOT NULL,"
                    + "	PRODUCT_CODE VARCHAR(9) NOT NULL," 
                    + "	NUMBER_OF_UNITS INTEGER NOT NULL,"
                    + "	DATE_PLACED DATE NOT NULL,"
                    + "	DATE_DUE DATE NOT NULL,"
                    + "	ORDER_TYPE VARCHAR(4) ,"
                    + "	TOTAL_COST DOUBLE NOT NULL,"
                    + "	PRIMARY KEY (ORDER_NUMBER)"
                    + ")";

            statement.execute(query);

            query = "CREATE TABLE KHAMALS.REPORT ("
                    + "	ID SMALLINT NOT NULL,"
                    + "	CODE VARCHAR(50),"
                    + "	DATEOFDELIVERY TIMESTAMP,"
                    + "	QUANTITY BIGINT,"
                    + "	PRIMARY KEY (ID)"
                    + ")";
            statement.execute(query);
         
        }
        catch (SQLException ex) {
            System.out.println(""+ex.getCause()); 
        
        }
        
    }
}


