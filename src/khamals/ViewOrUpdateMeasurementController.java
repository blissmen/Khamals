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
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

/**
 * FXML Controller class
 *
 * @author Klexy
 */
public class ViewOrUpdateMeasurementController implements Initializable, ControlledScreen {

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
    private Button updateBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private DatePicker dateJXDatePicker;

    DatabaseHelper db;
    public static ScreensController viewMeasurementsScreen;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            db = new DatabaseHelper();
        } catch (SQLException ex) {
            Logger.getLogger(ViewOrUpdateMeasurementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadCustomers();
        this.loadCustomerMeasurements();
        this.dateJXDatePicker.setValue(LocalDate.now());

        //add event listener for the customer combo box
        this.customerCmBox.setOnAction((event) -> {
            this.loadCustomerMeasurements();
        });
    }

    //function to load all the customers who have got measurements unto the combo box
    public void loadCustomers() {
        String name;
        String queryString = "select first_name, last_name, id from customers, measurements where "
                + "customers.id = measurements.customer_id";
        System.out.println(queryString);
        try {
            db.setQuery(queryString);
            if (!(db.numberOfRows == 0)) {
                for (int i = 0; i < db.getRowCount(); i++) {
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
        } catch (SQLException | IllegalStateException ex) {
            Logger.getLogger(NewOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!(db.numberOfRows == 0)) {
            this.customerCmBox.getSelectionModel().selectFirst();
        }
        System.out.println("Finished loading customers");
    }

    //function load to load measurements for the particular users to the 
    //given fields
    @FXML
    public void loadCustomerMeasurements() {
        ComboItem customer = this.customerCmBox.getSelectionModel().getSelectedItem();
        System.out.println("Loading old measurements now");
        boolean doIt = true;
        String query;
        query = "select * from measurements";
        try {
            db.setQuery(query);
            if (db.numberOfRows == 0) {
                doIt = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ViewOrUpdateMeasurementController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(ViewOrUpdateMeasurementController.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (doIt) {
            try {
                query = "select burst, waist, hips, sleeve, back, arm, chest, length, band from measurements where"
                        + " customer_id = " + customer.getValue();
                System.out.println(query + "\n");
                db.setQuery(query);

                this.burstTxtFld.setText(db.getValueAt(0, 0).toString());
                this.waistTxtFld.setText(db.getValueAt(0, 1).toString());
                this.hipsTxtFld.setText(db.getValueAt(0, 2).toString());
                this.sleeveTxtFld.setText(db.getValueAt(0, 3).toString());
                this.backTxtFld.setText(db.getValueAt(0, 4).toString());
                this.armTxtFld.setText(db.getValueAt(0, 5).toString());
                this.chestTxtFld.setText(db.getValueAt(0, 6).toString());
                this.lengthTxtFld.setText(db.getValueAt(0, 7).toString());
                this.bandTxtFld.setText(db.getValueAt(0, 8).toString());

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    //Update the existing selected records
    @FXML
    private void submitToDatabase(MouseEvent event) throws ParseException {
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        ComboItem customer = this.customerCmBox.getSelectionModel().getSelectedItem();
        String query = "update measurements set burst = "
                + this.burstTxtFld.getText() + ", waist = " + this.waistTxtFld.getText() + ", "
                + "hips = " + this.hipsTxtFld.getText() + ", sleeve = " + this.sleeveTxtFld.getText() + ","
                + " back = " + this.backTxtFld.getText() + ", "
                + "arm = " + this.armTxtFld.getText() + ", chest = " + this.chestTxtFld.getText() + ", "
                + "length = " + this.lengthTxtFld.getText() + ", band = " + this.bandTxtFld.getText() + ", date = '"
                + formatter.format(formatter.parse(this.dateJXDatePicker.getValue().toString()))
                + "' where customer_id = " + customer.getValue();
        System.out.println(query);
        Action showConfirm = Dialogs.create().
                title("Change Fields").
                masthead("You are about to update " + customer.getKey() + "'s Measurements").
                message("Are you sure you want to continue?").showConfirm();

        if (showConfirm == Dialog.Actions.YES) {
            System.out.println("Final query string: " + query);
            try {
                db.setQuery(query);
                Dialogs.create().title("Congratulations").
                        message("You have succesfully recorded a new measurement").
                        showInformation();
                this.loadCustomerMeasurements();
            } catch (SQLException ex) {
                Dialogs.create().title("Failure").
                        message("No modifications have been made").
                        showWarning();
                ex.printStackTrace();
            }
        }

    }

    @Override
    public void setScreenParent(ScreensController pane) {
        viewMeasurementsScreen = pane;
    }

}
