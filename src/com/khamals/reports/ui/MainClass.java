/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khamals.reports.ui;
import Database.DatabaseHelper;
import java.util.Date;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author MINGO LAWRENCE
 */
public class MainClass {

    public static String convertDate(String input) {
        if (!input.contains("/")) {
            return input;
        } else {
            return input.replace("/", "-");
        }
    }

    public static void main(String[] args) throws
            SQLException {

        Calendar cal = Calendar.getInstance();
        Date time = cal.getTime();
//        Date time = new Date(cal);
        DatabaseHelper db, db1;
        db = db1 = new DatabaseHelper();
        
//        String query = "insert INTO KHAMALS.PRODUCT VALUES('dsvd45', "
//               + "'summom', 'bandit', 'oN sen fou de cela.', 10, 8500, 10000,"
//               + " '2004-11-06'" + ", " + "'2014-04-12'" + ", 25, 8250)";
//                db.setQuery(query);
        
//        db.setQuery("insert INTO LOGIN VALUES(0, 'Hola', 'Fiona', '91535540',"
//                + "'There is none here who understands', '2011-06-07')");
//        db.setQuery("insert INTO LOGIN VALUES(1, 'Hola1', 'Fiona1', '91535541',"
//                + "'There is none here who understands one', '2011-06-01')");
//        db.setQuery("insert INTO LOGIN VALUES(2, 'Hola2', 'Fiona2', '91535542',"
//                + "'There is none here who understands two', '2011-06-02')");
//        db.setQuery("insert INTO LOGIN VALUES(3, 'Hola3', 'Fiona3', '91535543',"
//                + "'There is none here who understands three', '2011-06-03')");
//        db.setQuery("insert INTO LOGIN VALUES(4, 'Hola4', 'Fiona4', '91535544',"
//                + "'There is none here who understands four', '2011-06-04')");
//        db.setQuery("insert INTO LOGIN VALUES(5, 'Hola5', 'Fiona5', '91535545',"
//                + "'There is none here who understands five', '2011-06-05')");              
//        
//
//        db.setQuery ("insert INTO KHAMALS.SALES_GENERAL VALUES(002, 'Hola Fiona', 'Mr. Exo Blikn',"
//	       + "'2014-03-10', 91000, 42, 85450)");
//        db.setQuery ("insert INTO KHAMALS.SALES_GENERAL VALUES(165, 'Klexy House', 'Mr. Echu angbor',"
//	       + "'2012-08-12', 48, 746, 9500)");
//        db.setQuery ("insert INTO KHAMALS.SALES_GENERAL VALUES(152, 'Ware machines', 'Mrs. Nsang Mel',"
//	       + "'2012-08-12', 15, 4860, 9500)");
//        db.setQuery ("insert INTO KHAMALS.SALES_GENERAL VALUES(150, 'Nothing here', 'Bakia Holladia',"
//	       + "'2012-08-12', 62, 1589, 9500)");
//        
//        db.setQuery("insert INTO KHAMALS.SALES_DETAILED VALUES(002, 'Shameless', '1g8zY7',"
//                + " 5415, 45, 6, 7100)");
//        db.setQuery("insert INTO KHAMALS.SALES_DETAILED VALUES(165, 'hail Jesus', '84fju',"
//                + " 24365, 30, 15, 1625)");
//        db.setQuery("insert INTO KHAMALS.SALES_DETAILED VALUES(152, 'bongo chobi', 'tray1',"
//                + " 12000, 12, 10, 1200)");
//        db.setQuery("insert INTO KHAMALS.SALES_DETAILED VALUES(150, 'besombe', 'be§§somb',"
//                + " 38000, 10, 2, 19000)");
        
//                String query = "insert INTO KHAMALS.PRODUCT VALUES('dkb45', "
//               + "'KROTAL1', 'bandit', 'oN sen fou de cela.', 10, 8500, 10000,"
//               + " '2004-11-06'" + ", " + "'2014-04-12'" + ", 25, 8250)";
//                db1.setQuery(query);
//
//        String boo = "SELECT SALES_DETAILED.PRODUCT_NAME, SALES_DETAILED.PRODUCT_CODE,"
//                + " SALES_GENERAL.CASHIER_NAME, SALES_GENERAL.CUSTOMER_NAME"
//                + " FROM SALES_GENERAL INNER JOIN SALES_DETAILED ON SALES_GENERAL.TRANSACTION_ID="
//                + "SALES_DETAILED.TRANSACTION_ID WHERE SALES_DETAILED.PRICE>1000 "
//                + "ORDER BY SALES_GENERAL.TRANSACTION_ID desc";
                
                String boo = "SELECT SALES_GENERAL.CASHIER_NAME, SALES_DETAILED.PRODUCT_NAME, "
                            + "SALES_DETAILED.PRODUCT_CODE, SALES_GENERAL.CUSTOMER_NAME, "
                            + "SALES_GENERAL.TOTAL_AMOUNT_PAID "
                            + "FROM SALES_GENERAL INNER JOIN SALES_DETAILED ON SALES_GENERAL.TRANSACTION_ID="
                            + "SALES_DETAILED.TRANSACTION_ID WHERE "
                            + "SALES_GENERAL.CASHIER_NAME='Hola Fiona'";
                
        db1.setQuery(boo);
        for (int i = 0; i < db1.numberOfRows; i++) {
            for (int j = 0; j < db1.getColumnCount(); j++) {
                System.out.printf("%s\t", db1.getValueAt(i, j).toString());

            }
            System.out.println("\n");
        }

        /**
         *
         * // String stringOne; // stringOne = getInput("Enter a numeric value:
         * "); // Use a try-with-resources and catch block since resources may
         * be limited, in order // to obtain a reference to the database you
         * intend to connect to. // Since we're working with java 7, that idea's
         * GENIUS!! yay!!! try ( // connect to database via UTILITY class
         * Connection conn = DBInutil.getConnection(); // Create the query
         * statement using the connection string // also set the type of display
         * of results to read_only.. Statement state2 =
         * conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
         * ResultSet.CONCUR_READ_ONLY); Statement state =
         * conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
         * ResultSet.CONCUR_READ_ONLY);
         *
         * // Execute the given query and map the results to a result-set
         *
         * ResultSet reset2 = state2.executeQuery("SELECT * FROM DEPARTMENT " +
         * "ORDER BY FACULTY_FACULTY_ID");
         *
         * ResultSet reset = state.executeQuery("select * from users ");
         *
         * )
         * {
         * run(reset, reset2); // reset.last(); // move the cursor to the last
         * row and find out row number System.out.println("number of rows is:
         * "); // + reset.getRow());
         *
         * } catch (SQLException ex) { DBInutil.procExceptions(ex); // close
         * objects in reverse order of opening } }
         *
         * private static String getInput(String prompt) { BufferedReader stdin
         * = new BufferedReader( new InputStreamReader(System.in));
         *
         * System.out.println(prompt); System.out.flush();
         *
         * try { return stdin.readLine(); } catch (Exception ex) { return
         * "Error: " + ex.getMessage(); } }
         *
         * public static void run(ResultSet r1, ResultSet r2 ) throws
         * SQLException { Users.displayData(r1); Faculty.displayData(r2); }
         *
         * public MainClass() { }
         *
         */
    }
}
