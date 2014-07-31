/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khamals;

import General.ControlledScreen;
import General.DatabaseHelper;
import General.ScreensController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

/**
 * FXML Controller class
 *
 * @author Klexy
 */
public class DisplayAndEditOrdersController implements Initializable, ControlledScreen {

    private ObservableList<Order> orders;

    @FXML
    private Button closeBtn;
    @FXML
    private Button cancelOrder;
    @FXML
    private Button searchBtn;
    @FXML
    private DatePicker dateToFilterDatePicker;
    @FXML
    private DatePicker dateFromFilterDatePiker;
    @FXML
    private TextField customerNameSearchTextFld;
    @FXML
    private TableView<Order> ordersTable;
    @FXML
    private TableColumn<Order, String> customerNameColumn;
    @FXML
    private TableColumn<Order, String> productNameColumn;
    @FXML
    private TableColumn<Order, String> numberRequestedColumn;
    @FXML
    private TableColumn<Order, String> orderDateColumn;
    @FXML
    private TableColumn<Order, String> deliveryDateColumn;
    @FXML
    private TableColumn<Order, String> priceColumn;
    @FXML
    private TableColumn<Order, String> orderIDColumn;

    boolean ordersTableLoaded;

    DatabaseHelper db;

    String defaultQuery;
    String query;
    public static ScreensController displayOrdersScreen;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        defaultQuery = "select customers.first_name, customers.last_name, product.name, orders.number_of_units"
                + ", orders.date_placed, orders.date_due, orders.total_cost, orders.order_number from customers, product, orders where"
                + "(orders.customer_id = customers.id and product.code = orders.product_code)"
                + " order by orders.order_number";
        query = defaultQuery;
        //
        ordersTableLoaded = false;
        //
        populateTable();
        //set the date fields to defualt values
        this.dateFromFilterDatePiker.setValue(LocalDate.now());
        this.dateToFilterDatePicker.setValue(LocalDate.now());
        try {
            db = new DatabaseHelper();
            loadOrderHistory();
        } catch (SQLException ex) {
            Logger.getLogger(DisplayAndEditOrdersController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void populateTable() {
        this.customerNameColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("customerName"));
        this.productNameColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("productName"));
        this.numberRequestedColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("numberRequested"));
        this.orderDateColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("datePlaced"));
        this.deliveryDateColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("dateDue"));
        this.priceColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("price"));
        this.orderIDColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("orderID"));

        orders = FXCollections.observableArrayList();
        this.ordersTable.setItems(orders);
    }

    private void loadOrderHistory() throws SQLException {
        System.out.println("Printing the Order History");
        boolean recordExists = false;
        //Query statement 

        //Query the database for required information
        db.setQuery(defaultQuery);
        String tempName;
        for (int i = 0; i < db.getRowCount(); i++) {
            Order data = new Order();
            tempName = db.getValueAt(i, 0).toString();
            tempName += " " + db.getValueAt(i, 1).toString();
            data.customerName.setValue(tempName);
            data.productName.setValue(db.getValueAt(i, 2).toString());
            data.numberRequested.setValue(db.getValueAt(i, 3).toString());
            data.datePlaced.setValue(db.getValueAt(i, 4).toString());
            data.dateDue.setValue(db.getValueAt(i, 5).toString());
            data.price.setValue(db.getValueAt(i, 6).toString());
            data.orderID.setValue(db.getValueAt(i, 7).toString());

            orders.add(data);
        }

        if (!ordersTableLoaded) {
            System.out.println("Now trying to load the newly added enteries");
            this.ordersTable.setItems(orders);
        }

        if (ordersTableLoaded) {
            System.out.println("Loading everything");
            this.orders.clear();
            this.ordersTable.getItems().setAll(orders);
            this.ordersTableLoaded = true;
        }

    }

    @FXML
    private void refreshOrdersTableBtnActionPerformed(ActionEvent event) {

    }

    //Function to refresh the table entries
    private void refreshOrdersTableBtnActionPerformed(Event event) throws IOException, InstantiationException, IllegalAccessException, InterruptedException, SQLException {
        //this.orders.removeAll(orders);
        this.orders.clear();
        defaultQuery = query;
        this.loadOrderHistory();

    }

    private void refreshOrdersTableBtnActionPerformed() throws SQLException {
        //this.orders.removeAll(orders);
        this.orders.clear();
        this.loadOrderHistory();

    }

    //Filter the table to a restricted date period
    @FXML
    private void searchDatePeriod(Event event) throws ParseException, IOException, InstantiationException, IllegalAccessException, SQLException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateFrom = formatter.format(formatter.parse(this.dateFromFilterDatePiker.getValue().toString()));
        String dateTo = formatter.format(formatter.parse(this.dateToFilterDatePicker.getValue().toString()));
        this.defaultQuery = "select customers.first_name, customers.last_name, product.name, orders.number_of_units"
                + ", orders.date_placed, orders.date_due, orders.total_cost, orders.order_number from customers, product, orders where"
                + "(orders.customer_id = customers.id and product.code = orders.product_code and "
                + "orders.date_placed >= CAST('" + dateFrom + "' as DATE)"
                + " and orders.date_placed <= CAST('" + dateTo + "' as DATE))"
                + " order by orders.order_number";
        this.orders.clear();
        this.loadOrderHistory();
    }

    //function to performe live search ont the database for a user whose name is being input into the text field
    @FXML
    private void performeLiveSearchOnUsers(KeyEvent event) throws SQLException {
        String name = this.customerNameSearchTextFld.getText();
        //Check if the input field is empty
        String tempQuery = this.defaultQuery;
        if (!name.equalsIgnoreCase("")) {
            //this is a very lazy approach to converting the first letter of the string to 
            //an upper case so that it matches what we have in the database as the name formats
            //it involves two dummy strings, one to hold the first letter and the other to hold the rest
            String firstLetter;
            char[] temp = name.toCharArray();
            firstLetter = String.copyValueOf(temp, 0, 1);
            firstLetter = firstLetter.toUpperCase();
            String restOf = String.copyValueOf(temp, 1, temp.length - 1);
            String modifiedName = firstLetter + restOf;
            System.out.println("Modified string" + modifiedName);
            //now continue with the query

            this.defaultQuery = "select customers.first_name, customers.last_name, product.name, orders.number_of_units"
                    + ", orders.date_placed, orders.date_due, orders.total_cost, orders.order_number from customers, product, orders where"
                    + "(orders.customer_id = customers.id and product.code = orders.product_code and "
                    + "(customers.first_name like '%" + modifiedName + "%' or customers.last_name like '%" + modifiedName + "%'))";
            try {
                refreshOrdersTableBtnActionPerformed();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                this.defaultQuery = tempQuery;
                refreshOrdersTableBtnActionPerformed();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        if (name.equalsIgnoreCase("")) {
            this.defaultQuery = "select customers.first_name, customers.last_name, product.name, orders.number_of_units"
                    + ", orders.date_placed, orders.date_due, orders.total_cost, orders.order_number from customers, product, orders where"
                    + "(orders.customer_id = customers.id and product.code = orders.product_code)"
                    + " order by orders.order_number";
            try {
                refreshOrdersTableBtnActionPerformed();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @FXML
    private void deleteSelectedOrder(ActionEvent event) {
        //ObservableList<TablePosition> orderId = this.ordersTable.getSelectionModel().getSelectedCells();
        String orderId = this.ordersTable.getSelectionModel().getSelectedItem().orderID.get();
        System.out.println("selected order id: " + orderId);
        this.defaultQuery = "delete from orders where order_number = " + orderId;
        System.out.println("Query String = " + defaultQuery);
        Action confirmer = Dialogs.create().
                title("Delete confirmation").
                masthead("You are about to delete oder number " + orderId).
                message("Are you sure you want to continue?").showConfirm();

        if (confirmer == Dialog.Actions.YES) {
            try {
                db.setQuery(this.defaultQuery);
                //When the deletion is complete, reload the table with the old query
                this.defaultQuery = query;
                this.refreshOrdersTableBtnActionPerformed();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
               Dialogs.create().message("Delete Failded").showWarning();
        }
    }

    @Override
    public void setScreenParent(ScreensController pane) {
        displayOrdersScreen = pane;
    }

}
