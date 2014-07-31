/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomersAndDebtors;

import static CustomersAndDebtors.AddAccountController.helper;
import General.ControlledScreen;
import General.ScreensController;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import static khamals.mainInterfaceController.MAIN_CUSTOMER_ID;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

/**
 * FXML Controller class
 *
 * @author Harvey
 */
public class ModifyAccountController implements Initializable, ControlledScreen {

    @FXML
    private TextField id;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;
    @FXML
    private TextField location;
    @FXML
    private ChoiceBox<String> type;
    @FXML
    private TextField dateCreated;
    @FXML
    private ComboBox<String> name;

    private String query;
    private ScreensController screen;
    private String idNumber;
    private String eMail;
    private String phoneNumber;
    private String cLocation;
    private Object userType;
    private String[] previousName;
    public static LoadFields reload;

    /**
     * initialises the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        reload = new LoadFields();
        try {
            reload.loadFields();
        } catch (SQLException ex) {
            Logger.getLogger(ModifyAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }

        name.valueProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            try {
                if (newValue != null) {
                    getCustomer(newValue);
                }
                
                if (oldValue != null) {
                    previousName = oldValue.split("[ ]");
                }
            } catch (SQLException ex) {
                Logger.getLogger(ModifyAccountController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    @FXML
    private void goToMain(ActionEvent event) {
        screen.setScreen(MAIN_CUSTOMER_ID);
    }

    @FXML
    private void updateAccount(ActionEvent event) {
        try {
            idNumber = id.getText();
            eMail = email.getText();
            phoneNumber = phone.getText();
            cLocation = location.getText();
            userType = type.getSelectionModel().getSelectedItem();

            if (name.getSelectionModel().getSelectedIndex() != -1) {
                String[] split = name.getValue().split("[ ]");

                query = "UPDATE CUSTOMERS SET FIRST_NAME = '" + split[0]
                        + "', LAST_NAME = '" + split[split.length - 1]
                        + "', LOCATION = '" + cLocation
                        + "', EMAIL = '" + eMail
                        + "', TELEPHONE = '" + phoneNumber
                        + "', ID = " + idNumber
                        + ",  TYPE = '" + userType
                        + "' WHERE FIRST_NAME = '" + previousName[0]
                        + "' AND LAST_NAME = '"
                        + previousName[previousName.length - 1] + "'";
                helper.setQuery(query);
                CustomersAndDebtorsController.Populator.populateTabeCus();
                goToMain(event);
            }

        } catch (SQLException | IllegalStateException ex) {
            Dialogs.create().title("Database Error").
                    message("An error occured while "
                            + "updating the customer data").
                    showInformation();

            ex.printStackTrace();
        }
    }

    @FXML
    private void deleteAccount(ActionEvent event) {
        Action response = Dialogs.create().title("Deleting Customer").
                masthead("You are about to remove " + previousName[0] + " "
                        + previousName[previousName.length - 1]
                        + " from your customers.").message("Are you sure you want to do this?")
                .actions(Dialog.Actions.YES, Dialog.Actions.CANCEL).
                showConfirm();
        if (response.equals(Dialog.Actions.YES)) {
            try {
                query = "DELETE FROM CUSTOMERS WHERE FIRST_NAME = '" + previousName[0]
                        + "' AND LAST_NAME = '"
                        + previousName[previousName.length - 1] + "'";
                helper.setQuery(query);
                CustomersAndDebtorsController.Populator.populateTabeCus();
                goToMain(event);
            } catch (SQLException | IllegalStateException ex) {
//                Dialogs.create().title("Database Error").
//                        message("An error occured while "
//                                + "deleting the customer.").
//                        showInformation();
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void setScreenParent(ScreensController pane) {
        screen = pane;
    }

    public void getCustomer(String name) throws SQLException {
//        System.out.println("in getcostumer: " + name);
        String[] split = name.split("[ ]");
        if (split != null) {
            query = "SELECT * FROM CUSTOMERS WHERE FIRST_NAME = '" + split[0]
                    + "' AND LAST_NAME = '" + split[split.length - 1] + "'";
            helper.setQuery(query);
            if (helper.resultSet.first()) {
                id.setText(helper.resultSet.getString("ID"));
                email.setText(helper.resultSet.getString("EMAIL"));
                phone.setText(helper.resultSet.getString("TELEPHONE"));
                location.setText(helper.resultSet.getString("LOCATION"));
                type.getSelectionModel().select(helper.resultSet.getString("TYPE"));
                dateCreated.setText(helper.resultSet.getString("DATE"));

            }
        }

    }

    public class LoadFields {

        public void loadFields() throws SQLException {
            type.getItems().clear();
            type.getItems().addAll("Individual", "Business");
            query = "SELECT * FROM CUSTOMERS";
            helper.setQuery(query);
            if (helper.resultSet.first()) {
                ArrayList<String> items = new ArrayList<>();
                do {
                    items.add(helper.resultSet.getString("FIRST_NAME").
                            concat(" " + helper.resultSet.getString("LAST_NAME")));
                } while (helper.resultSet.next());
                name.getItems().clear();
                name.getItems().addAll(items);
                name.getSelectionModel().select(0);
                getCustomer(items.get(0));

                previousName = items.get(0).split("[ ]");
            }
        }
    }
}
