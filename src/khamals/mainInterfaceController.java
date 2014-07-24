/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package khamals;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Pagination;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author blissmen
 */
public class mainInterfaceController implements Initializable {
    @FXML
    private AnchorPane mainAchorPane;
    @FXML
    private Button closeApplicationBtn;
    @FXML
    private Button loadImagesBtn;
    @FXML
    private Pagination pagination;
    @FXML
    private Button orderHistoryBtn;
    @FXML
    private Button newOrderBtn;
    @FXML
    private Button removeOrderBtn;
    @FXML
    private TableView<?> ordersTable;
    @FXML
    private TableColumn<?, ?> customerNameColumn;
    @FXML
    private TableColumn<?, ?> numberRequestedColumn;
    @FXML
    private TableColumn<?, ?> orderDateColumn;
    @FXML
    private TableColumn<?, ?> deliveryDateColumn;
    @FXML
    private TableColumn<?, ?> priceColumn;
    @FXML
    private TableColumn<?, ?> orderIDColumn;
    @FXML
    private TableColumn<?, ?> productNameColumn;
    @FXML
    private Tab measurementsTab;
    @FXML
    private ComboBox<?> customersComboBox;
    @FXML
    private Button getSelectedCustomersMeasurement;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void loadUserManagement(MouseEvent event) {
    }

    @FXML
    private void loadMakeSales(MouseEvent event) {
    }

    @FXML
    private void addNewOrder(MouseEvent event) {
    }

    @FXML
    private void loadReport(MouseEvent event) {
    }


    @FXML
    private void selectImages(ActionEvent event) {
    }

    @FXML
    private void loadInventory(MouseEvent event) {
    }

    @FXML
    private void viewOrderHistoryActionPerformed(MouseEvent event) {
    }

    @FXML
    private void loadMeasurements(MouseEvent event) {
    }

    @FXML
    private void loadCustomers(MouseEvent event) {
    }

    @FXML
    private void loadSalesReport(MouseEvent event) {
    }

    @FXML
    private void loadRevokeSales(MouseEvent event) {
    }

    @FXML
    private void loadInvetoryReport(MouseEvent event) {
        System.out.println("Hello");
    }

    @FXML
    private void viewOrderHistoryActionPerformed(ActionEvent event) {
    }

    @FXML
    private void addNewOrder(ActionEvent event) {
    }

    @FXML
    private void refreshOrdersTableBtnActionPerformed(ActionEvent event) {
    }

    @FXML
    private void loadOrderHistory(Event event) {
    }

    @FXML
    private void loadMeasurements(ActionEvent event) {
    }

    @FXML
    private void newMeasurement(ActionEvent event) {
    }

    @FXML
    private void updateMeasurement(ActionEvent event) {
    }

    @FXML
    private void loadMeasurements1(ActionEvent event) {
    }

    @FXML
    private void loadCustomers(Event event) {
    }

    @FXML
    private void loadAcustomer(MouseEvent event) {
    }

    @FXML
    private void loadMCustmer(MouseEvent event) {
    }

    @FXML
    private void loadADebt(MouseEvent event) {
    }

    @FXML
    private void loadPDebt(MouseEvent event) {
    }

    @FXML
    private void LoadCashierRepot(MouseEvent event) {
    }
    
}
