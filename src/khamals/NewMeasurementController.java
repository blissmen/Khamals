/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package khamals;

import General.ControlledScreen;
import General.DatabaseHelper;
import General.ScreensController;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.controlsfx.dialog.Dialogs;

/**
 * FXML Controller class
 *
 * @author Klexy
 */
public class NewMeasurementController implements Initializable, ControlledScreen {
    @FXML
    private ComboBox<ComboItem> customerCmBox;
    @FXML
    private TextField burstTxtFld;
    @FXML
    private TextField waistTxtFld;
    @FXML
    private TextField hipsTxtFld;
    @FXML
    private TextField sleeveTxtFld;
    @FXML
    private TextField backTxtFld;
    @FXML
    private TextField armTxtFld;
    @FXML
    private TextField chestTxtFld;
    @FXML
    private TextField lengthTxtFld;
    @FXML
    private TextField bandTxtFld;
    @FXML
    private DatePicker dateJXDatePicker;
    
    DatabaseHelper db;
    @FXML
    private Button cancelBtn;
    @FXML
    private Button updateBtn;
    public static ScreensController newMeasurementsScreen;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            db = new DatabaseHelper();
        } catch (SQLException ex) {
            Logger.getLogger(NewMeasurementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.loadCustomers();
        this.dateJXDatePicker.setValue(LocalDate.now());
    }    
    
    //LOAD ALL THE CUSTOMERS IN THE SYSTEM INTO THE DATABASE
    public void loadCustomers(){
            String name;
            String queryString = "select first_name, last_name, id from customers"
                + " except (select first_name, last_name, id from customers, measurements where"
                + " customers.id = measurements.customer_id)";
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
    
    //function to submit the new measurements to the database
    @FXML
    public void submitToDatabase() throws ParseException{
        //DATE FORMATTER OBJECT DEFINITION
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        ComboItem customer = (ComboItem) this.customerCmBox.getSelectionModel().getSelectedItem();
        
        String query = "insert into measurements values("
                + customer.getValue() + ", " + this.burstTxtFld.getText() + ", "
                + this.waistTxtFld.getText() + ", " + this.hipsTxtFld.getText() + ", "
                + this.sleeveTxtFld.getText() + ", " + this.backTxtFld.getText() + ", "
                + this.armTxtFld.getText() + ", " + this.chestTxtFld.getText() + ", "
                + this.lengthTxtFld.getText() + ", " + this.bandTxtFld.getText() + ", '"
                + formatter.format(formatter.parse(this.dateJXDatePicker.getValue().toString())) + "')";
        clearFields();
        
        try{
            db.setQuery(query);
            Dialogs.create().title("Congratulations").
                      message("You have succesfully recorded a new measurement").
                      showInformation(); 
            clearFields();
            } catch (SQLException ex) {
                    Dialogs.create().title("Failure").
                            masthead("Make sure you have filled all the required fields").
                      message("Press OK to continue").showWarning();
                }
    }
    
    public void clearFields(){
        this.armTxtFld.clear();
        this.backTxtFld.clear();
        this.bandTxtFld.clear();
        this.chestTxtFld.clear();
        this.hipsTxtFld.clear();
        this.lengthTxtFld.clear();
        this.sleeveTxtFld.clear();
        this.waistTxtFld.clear();
        this.burstTxtFld.clear();
    }

    @Override
    public void setScreenParent(ScreensController pane) {
        newMeasurementsScreen = pane;
    }
}
