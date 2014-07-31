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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import static khamals.mainInterfaceController.MAIN_CUSTOMER_ID;
import org.controlsfx.dialog.Dialogs;

/**
 * FXML Controller class
 *
 * @author Harvey
 */
public class PayDebtController implements Initializable, ControlledScreen {
    
    @FXML
    private TextField amtIncurred;
    @FXML
    private TextField deadline;
    @FXML
    private TextField paid;
    @FXML
    private TextField left;
    @FXML
    private ComboBox<String> nameBox;
    @FXML
    private ComboBox<String> dateIncurred;
    @FXML
    private TextField paidOn;
    String query;
    int paying = 0;
    ArrayList<String> names = new ArrayList<>();
    ArrayList<String> dates = new ArrayList<>();
    private int debt;
    private int remainingDebt;
    private ScreensController screen;
    public static PayDebt reload;

    /**
     * initialises the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        reload = new PayDebt();
        
        try {
            reload.getDebtors();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        nameBox.valueProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (newValue != null) {
                try {
                    query = "SELECT DATE_TACKEN FROM DEBTORS WHERE NAME = '"
                            + newValue + "'";
                    helper.setQuery(query);
                    if (helper.resultSet.first()) {
                        dates.clear();
                        do {
                            dates.add(helper.resultSet.getString("DATE_TACKEN"));
                        } while (helper.resultSet.next());
                        dateIncurred.getItems().clear();
                        dateIncurred.getItems().addAll(dates);
                        dateIncurred.getSelectionModel().select(0);
                        getDebt(nameBox.getSelectionModel().getSelectedItem(),
                                dateIncurred.getSelectionModel().getSelectedItem());
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(PayDebtController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        
        dateIncurred.getSelectionModel().selectedIndexProperty().
                addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
                    if (newValue.intValue() >= 0) {
                        try {
                            getDebt(nameBox.getSelectionModel().getSelectedItem(), dates.get(newValue.intValue()));
                        } catch (SQLException ex) {
                            Logger.getLogger(PayDebtController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });   
    }
    
    @FXML
    private void handle(KeyEvent event) {
        General.GeneralFunctions.handleNumbers(event);
        
        if (paid.getText() != null) {//the Amount Paid field should have a value
            paying = Integer.parseInt(paid.getText());//get the amount the user wants to pay
            System.out.println("paying " + paying + "\tdebt " + debt);
            if (paying > debt) {//if the amount entered is > than that old consume the input
                event.consume();
                return;
            }
            remainingDebt = debt - paying;//calculate the remaining debt
            left.setText(Integer.toString(remainingDebt));//set the remaining debt
        }
    }
    
    @FXML
    private void payDebt(ActionEvent event) {
        if (paying == debt) {//if the debt is completely paid remove row date from the database
            query = "DELETE FROM DEBTORS WHERE NAME = '"
                    + nameBox.getSelectionModel().getSelectedItem()
                    + "' AND DATE_TACKEN = '"
                    + dateIncurred.getSelectionModel().getSelectedItem() + "'";
        } else {
            query = "UPDATE DEBTORS SET AMOUNT = " + remainingDebt
                    + " WHERE NAME = '"
                    + nameBox.getSelectionModel().getSelectedItem()
                    + "' AND DATE_TACKEN = '"
                    + dateIncurred.getSelectionModel().getSelectedItem() + "'";
        }
        try {
            helper.setQuery(query);
            
        } catch (SQLException | IllegalStateException ex) {
//            Dialogs.create().title("Error").
//                    message("An error occurred while trying to"
//                            + "update the dept status.").
//                    showInformation();
            ex.printStackTrace();
        }
    }
    
    @FXML
    private void cancel(ActionEvent event) {
        gotoMain();
    }
    
    private void getDebt(String name, String date) throws SQLException {
        query = "SELECT AMOUNT, LATEST_DATE_OF_PAYMENT FROM DEBTORS WHERE NAME = '"
                + name + "' AND DATE_TACKEN = '" + date + "'";
        helper.setQuery(query);
        if (helper.resultSet.first()) {
            deadline.setText(helper.resultSet.getString("LATEST_DATE_OF_PAYMENT"));
            amtIncurred.setText(helper.resultSet.getString("AMOUNT"));
            debt = Integer.parseInt(amtIncurred.getText()); //get the amount customer is owing
        }
    }
    
    public void gotoMain() {
        screen.setScreen(MAIN_CUSTOMER_ID);
    }
    
    @Override
    public void setScreenParent(ScreensController pane) {
        screen = pane;
    }
    
    public class PayDebt {
        
        public void getDebtors() throws SQLException {
            query = "SELECT NAME, DATE_TACKEN FROM DEBTORS";
            
            helper.setQuery(query);
            if (helper.resultSet.first()) {
                do {
                    names.add(helper.resultSet.getString("NAME"));
                    dates.add(helper.resultSet.getString("DATE_TACKEN"));
                } while (helper.resultSet.next());
                nameBox.getItems().clear();
                nameBox.getItems().addAll(names);
                nameBox.getSelectionModel().select(0);
                
                dateIncurred.getItems().clear();
                dateIncurred.getItems().addAll(dates);
                dateIncurred.getSelectionModel().select(0);
                left.setText(null);
                paid.setText(null);
                getDebt(nameBox.getSelectionModel().getSelectedItem(),
                        dateIncurred.getSelectionModel().getSelectedItem());
            }
            
        }
    }
}
