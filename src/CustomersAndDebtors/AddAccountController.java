/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomersAndDebtors;

import General.ControlledScreen;
import General.DatabaseHelper;
import General.ScreensController;
import java.net.URL;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Formatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.dialog.Dialogs;

/**
 * FXML Controller class
 *
 * @author Harvey
 */
public class AddAccountController implements Initializable, ControlledScreen {

    @FXML
    private TextField fName;
    @FXML
    private TextField lName;
    @FXML
    private TextField id;
    @FXML
    private TextField email;
    @FXML
    private TextField number;
    @FXML
    private TextField location;
    @FXML
    private ChoiceBox<String> typeBox;
    private String firstName, lastName, type,
            cLocation, eMail, phoneNumber, query;
    private int idNumber;

    static DatabaseHelper helper;
    private ScreensController screen;
    @FXML
    private AnchorPane inventoryButtondAnchor;
    @FXML
    private AnchorPane inventoryAnchor;
    private String MAIN_CUSTOMER_ID;

    /**
     * initialises the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            helper = new DatabaseHelper();
            typeBox.getItems().addAll("Individual", "Business");
            typeBox.getSelectionModel().select(0);
        } catch (SQLException ex) {
            Logger.getLogger(AddAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void addCustomer(ActionEvent event) {
        try {
            firstName = fName.getText();
            firstName = General.GeneralFunctions.toSentenceCase(firstName);
            firstName = checkQuote(firstName);
            lastName = lName.getText();
            lastName = General.GeneralFunctions.toSentenceCase(lastName);
            lastName = checkQuote(firstName);
            if ("".equals(id.getText())) {
                idNumber = 0;
            } else {
                System.out.println(id.getText() != null ? "id.getText()" : "null");
                idNumber = Integer.parseInt(id.getText());
            }

            eMail = email.getText();
            phoneNumber = number.getText();
            cLocation = location.getText();
            type = typeBox.getSelectionModel().getSelectedItem();
            Calendar dateTime = Calendar.getInstance();
            Formatter c = new Formatter();
            c = c.format("%tF", dateTime);

            query = "INSERT INTO CUSTOMERS(DATE,FIRST_NAME,LAST_NAME,LOCATION,ID,"
                    + "TELEPHONE,TYPE,EMAIL) VALUES("
                    + "'" + c.toString() + "', "
                    + "'" + firstName + "', "
                    + "'" + lastName + "', "
                    + "'" + cLocation + "', "
                    + idNumber
                    + ",'" + phoneNumber + "',"
                    + "'" + type + "',"
                    + "'" + eMail + "')";
            System.out.println(query);
            helper.setQuery(query);

            Dialogs.create().title("Success").
                    message(firstName + " " + lastName + " was added successfully.").
                    showInformation();
            
            fName.setText("");
            lName.setText("");
            lastName = General.GeneralFunctions.toSentenceCase(lastName);
            id.setText("");
            email.setText("");
            number.setText("");
            location.setText("");
            goToMain();
        } catch (Exception e) {
            Dialogs.create().title("Empty Fields")
                    .message("You must fill the First Name field and"
                            + " Last Name field."
                    ).showInformation();
            e.printStackTrace();
        }
    }

    @FXML
    private void handleNumbers(KeyEvent event) {
        General.GeneralFunctions.handleNumbers(event);
    }

    @FXML
    private void cancel(ActionEvent event) {
        goToMain();
    }

    public void goToMain() {
        screen.setScreen(MAIN_CUSTOMER_ID);
    }

    @Override
    public void setScreenParent(ScreensController pane) {
        screen = pane;
    }

    @FXML
    private void handleCharacters(KeyEvent event) {
//        General.GeneralFunctions.handleStrings(event);
    }
    
    public String checkQuote(String string){
        if (string.contains("'")) {
            string = string.replaceAll("[']", "''");
        }
        return string;
    }
}
