/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package khamals;

import General.ControlledScreen;
import General.DatabaseHelper;
import General.ScreensController;
import static com.sun.deploy.trace.Trace.print;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.controlsfx.dialog.Dialogs;

/**
 * FXML Controller class
 *
 * @author Klexy
 */
public class NewOrderController implements Initializable, ControlledScreen{
    @FXML
    private DatePicker placedDatePicker;
    @FXML
    private ComboBox<ComboItem> customerCmBox;
    @FXML
    private ComboBox<ComboItem> pdtCodeCmBox;
    @FXML
    private TextField numberReqJSpinner;
    @FXML
    private CheckBox requireMeasurementsChkBox;
    @FXML
    private TextField totalCostTxtFld;
    @FXML
    private DatePicker dueDatePicker;
    @FXML
    private Button submitBtn;
    @FXML
    private Button cancelBtn;
    
    int SESSION_USER;
    DatabaseHelper db;
    public static ScreensController newOrderScreen;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            db = new DatabaseHelper();
            loadCustomers();
            loadProducts();
            restrictDatePickers();
        } catch (SQLException ex) {
            Logger.getLogger(NewOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
    @FXML 
    private void submitBtnActionPerformed(Event event) throws IOException, InstantiationException, IllegalAccessException{
        
    }
    
    //SET A DEFUALT VALUE FOR THE DATE PICKERS
    @FXML
    public void restrictDatePickers(){
        this.placedDatePicker.setValue(LocalDate.now());
        this.dueDatePicker.setValue(LocalDate.now());
    }
    
    //LOAD ALL THE CUSTOMERS IN THE SYSTEM INTO THE DATABASE
    public void loadCustomers(){
        System.out.println("Hello");
            String name;
            String queryString = "select first_name, last_name, id from customers";
            System.out.println(queryString);
        try {
             db.setQuery(queryString);             
             for(int i=0; i<db.getRowCount(); i++){
                 System.out.println(db.getValueAt(i, 0));
                String firstName;
                firstName = db.getValueAt(i, 0).toString();
                String lastName;
                lastName = db.getValueAt(i, 1).toString();
                String customer_id;
                customer_id = db.getValueAt(i, 2).toString();
                name = firstName + " " + lastName;
                this.customerCmBox.getItems().add(new ComboItem(name, customer_id));
                System.out.printf("%s %s\n", firstName, lastName);
             }
         } 
             catch (SQLException | IllegalStateException ex) {
             Logger.getLogger(NewOrderController.class.getName()).log(Level.SEVERE, null, ex);
         }   
         this.customerCmBox.getSelectionModel().selectFirst();
        System.out.println("Finished loading customers");
       
    }
    
    //function to load the various products which are in the system
    public void loadProducts() throws SQLException{
        //this.pdtCodeCmBox.getItems().rem;
        String queryString = "select code, name from product";
        db.setQuery(queryString);
        for(int i=0; i<db.numberOfRows; i++){            
            String code;
            code = db.getValueAt(i, 0).toString(); 
            String name = db.getValueAt(i, 1).toString();
            this.pdtCodeCmBox.getItems().add(new ComboItem(name, code));
        } 
        this.pdtCodeCmBox.getSelectionModel().selectFirst();
    }
    
    //end of funtion definition
    
    //next up is a funtion to restrict entry into a text filed to be restricted only to
    //numbers

    @FXML
    public void restrictToNumbers(javafx.scene.input.KeyEvent event) {
        char c = event.getCharacter().charAt(0);
        System.out.println("character c: " + c);
        if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_TAB
                || c == KeyEvent.VK_ENTER || c == KeyEvent.VK_BACK_SPACE))) {
            Dialogs.create().title("Wrong Input").
                    message("You must enter only numbers here.").
                    showInformation();
           event.consume();
       }
   }
    
    @FXML
    public void submitDataToDatabase() throws SQLException, ParseException{
        //DATE FORMATTER OBJECT DEFINITION
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        //String to determine if the order needs make use of customer measurements
        //Default value of the string is OWOW:-order without measuremnt
        String measure = "OWOM";
        if(this.requireMeasurementsChkBox.isSelected()){
            measure = "OWM";
        }
        String hell = formatter.format(formatter.parse(this.placedDatePicker.getValue().toString()));
        System.out.println("\ntested date: " + hell);
        //int numberOfRows = this.numberOfRowsIn("orders");
        ComboItem customer = (ComboItem) this.customerCmBox.getSelectionModel().getSelectedItem();
        ComboItem product = (ComboItem) this.pdtCodeCmBox.getSelectionModel().getSelectedItem();
        String queryString = "insert into ORDERS(customer_id, user_id, product_code, number_of_units,"
                + "date_placed, date_due, order_type, total_cost) values("
                + customer.getValue() + ","
                + SESSION_USER + ",'"
                + product.getValue() + "',"
                + this.numberReqJSpinner.getText() + ", '"
                + formatter.format(formatter.parse(this.placedDatePicker.getValue().toString()))+ "', '"
                + formatter.format(formatter.parse(this.dueDatePicker.getValue().toString())) + "', '"
                + measure + "', " + Float.parseFloat(this.totalCostTxtFld.getText()) + ")";
        
        //execute the query string
        System.out.println("Query String: " + queryString);
        db.setQuery(queryString);
        
        Dialogs.create().title("Congratulations").
                      message("You have succesfully added a new order").
                      showInformation();       
        //db.disconnectFromDatabase();
    }

    @Override
    public void setScreenParent(ScreensController pane) {
        newOrderScreen = pane;
    }
}
