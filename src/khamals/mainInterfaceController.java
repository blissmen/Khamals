/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khamals;

import General.DatabaseHelper;
import General.ScreensController;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.imageio.ImageIO;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import static khamals.DisplayAndEditOrdersController.displayOrdersScreen;
import static khamals.NewMeasurementController.newMeasurementsScreen;
import static khamals.NewOrderController.newOrderScreen;
import static khamals.ViewOrUpdateMeasurementController.viewMeasurementsScreen;
import ordersAndMeasurements.*;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

;

/**
 *
 * @author Klexy
 */
public class mainInterfaceController implements Initializable {

    //section for screen controller shit
    public static String MAIN_ORDER_ID = "mainOder";
    public static String MAIN_ORDER_FXML = "/khamals/displayAndEditOrders.fxml";
    public static String NEWMEASUREMENT_ID = "newMeasurement";
    public static String NEWMEASUREMENT_FXML = "/khamals/newMeasurement.fxml";
    public static String NEWORDER_ID = "newOrder";
    public static String NEWORDER_FXML = "/khamals/newOrder.fxml";
    public static String VIEWMEAS_ID = "viewMeasurement";
    public static String VIEWMEAS_FXML = "/khamals/viewOrUpdateMeasurement.fxml";
    /////
    /**
     * *************************************************
     */
    //Other people's static tab strings will be declared in this section   
    //Customers and Debtors
    public static String MAIN_CUSTOMER_ID = "mainCustomers";
    public static String MAIN_CUSTOMER_FXML = "/CustomersAndDebtors/CustomersDebtors.fxml";
    public static String ADD_ACCOUNT_ID = "addaccount";
    public static String ADD_ACCOUNT_FXML = "/CustomersAndDebtors/AddAccount.fxml";
    public static String MODIFY_ACCOUNT_ID = "modifyaccount";
    public static String MODIFY_ACCOUNT_FXML = "/CustomersAndDebtors/ModifyAccount.fxml";
    public static String TAKE_DEBT_ID = "takedept";
    public static String TAKE_DEBT_FXML = "/CustomersAndDebtors/TakeDebt.fxml";
    public static String PAY_DEBT_ID = "paydept";
    public static String PAY_DEBT_FXML = "/CustomersAndDebtors/PayDebt.fxml";
    /**
     * ************************************************
     */
    /**
     * *****************INVENTORY**********************
     */
    public static final String INVENTORY_SCREEN_ID = "main";
    public static final String MAIN_SCREEN_FXML = "/Inventory/Inventory.fxml";
    public static final String ADD_MODIFY_ID = "screen1";
    public static final String ADD_MODIFY_FXML = "/Inventory/AddModify.fxml";
    /**
     * ************************************************
     *
     */ //login management
    public static String MAIN_USERS_ID = "/Login/manageAccount";
    public static String MAIN_USERS_FXML = "/Login/ManageAccount.fxml";
    public static String CHG_PASSWD_ID = "/Login/changePassword";
    public static String CHG_PASSWD_FXML = "/Login/ChangePassword.fxml";
    public static String DELETE_ACC_ID = "/Login/delteAccount";
    public static String DELETE_ACC_FXML = "/Login/DeleteAccount.fxml";
    public static String LOGIN_ID = "/Login/login";
    public static String LOGIN_FXML = "/Login/FXMLDocument.fxml";
    /**
     * ************************************************
     */
    //sales 
    public static String SALES_ID = "sales";
    public static String SALES_FXML = "/Sales/sales.fxml";
    /**
     * ************************************************
     */
    public static String REPORT_ID = "report";
    public static String REPORT_FXML = "/Report/report.fxml";
    /**
     * ************************************************
     */

    private ObservableList<Order> orders;
    //Variables that will be used all through the program
    private File filesJpg[];

    //The next set of variables are for precaution
    boolean ordersTableLoaded;
    boolean ordersTableEdited;
    boolean customerMeasurementsLoaded;
    //the above boolean vaiables help to know when to load the Table View or when to reload it

    DatabaseHelper db;
    @FXML
    private Pagination pagination;

    private Label label;
    @FXML
    private Button closeApplicationBtn;
    @FXML
    private Button loadImagesBtn;

    private Button removeOrderBtn;
    @FXML
    private AnchorPane mainAchorPane;
    private TableView<Order> ordersTable;
    private TableColumn<Order, String> customerNameColumn;
    private TableColumn<Order, String> productNameColumn;
    private TableColumn<Order, String> numberRequestedColumn;
    private TableColumn<Order, String> orderDateColumn;
    private TableColumn<Order, String> deliveryDateColumn;
    private TableColumn<Order, String> priceColumn;
    private TableColumn<Order, String> orderIDColumn;
    private ComboBox<ComboItem> customersComboBox;
    @FXML
    private Tab measurementsTab;
    private TextField customerNameSearchTextFld;
    private DatePicker dateFromFilterDatePiker;
    private DatePicker dateToFilterDatePicker;
    @FXML
    private AnchorPane mainOrderAnchor;
    @FXML
    private AnchorPane measurementsAnchorPane;

    String defaultQuery, query;
    @FXML
    private AnchorPane customerAnchor;
    @FXML
    private AnchorPane inventoryAnchor;
    @FXML
    private AnchorPane salesAnchorPane;
    @FXML
    private AnchorPane userAnchor;
    @FXML
    private AnchorPane reportsAnchor;

    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ScreensController ordersContainer = new ScreensController(mainOrderAnchor);
        ordersContainer.loadScreen(MAIN_ORDER_ID, MAIN_ORDER_FXML);
        ordersContainer.loadScreen(NEWMEASUREMENT_ID, NEWMEASUREMENT_FXML);
        ordersContainer.loadScreen(NEWORDER_ID, NEWORDER_FXML);
        ordersContainer.loadScreen(VIEWMEAS_ID, VIEWMEAS_FXML);
        ordersContainer.setScreen(MAIN_ORDER_ID);

        Group ordersRoot = new Group();
        ordersRoot.getChildren().addAll(ordersContainer);
        /**
         * **********************************************************************
         */
        ScreensController measurementsContainer = new ScreensController(measurementsAnchorPane);
        measurementsContainer.loadScreen(NEWMEASUREMENT_ID, NEWMEASUREMENT_FXML);
        measurementsContainer.loadScreen(VIEWMEAS_ID, VIEWMEAS_FXML);
        measurementsContainer.setScreen(VIEWMEAS_ID);
        Group measurementsGroup = new Group();
        measurementsGroup.getChildren().addAll(measurementsContainer);

        //CREATING STACK FOR CUSTOMER/DEBTORS
        ScreensController customerDebtors = new ScreensController(this.customerAnchor);
        customerDebtors.loadScreen(MAIN_CUSTOMER_ID, MAIN_CUSTOMER_FXML);
        System.out.println("main customer fxml loaded");
        customerDebtors.loadScreen(ADD_ACCOUNT_ID, ADD_ACCOUNT_FXML);
        System.out.println("add account fxml loaded");
        customerDebtors.loadScreen(MODIFY_ACCOUNT_ID, MODIFY_ACCOUNT_FXML);
        System.out.println("modify account fxml loaded");
        customerDebtors.loadScreen(TAKE_DEBT_ID, TAKE_DEBT_FXML);
        System.out.println("take debt fxml loaded");
        customerDebtors.loadScreen(PAY_DEBT_ID, PAY_DEBT_FXML);
        System.out.println("pay DEBT fxml loaded");
        customerDebtors.setScreen(MAIN_CUSTOMER_ID);
        Group customersRoot = new Group();
        customersRoot.getChildren().addAll(customerDebtors);

        /**
         * ***********************************************
         */
        /* CREATING STACK FOR IINVENTORY*******************/
        ScreensController inventoryContainer = new ScreensController(this.inventoryAnchor);
        inventoryContainer.loadScreen(INVENTORY_SCREEN_ID, MAIN_SCREEN_FXML);
        inventoryContainer.loadScreen(ADD_MODIFY_ID, ADD_MODIFY_FXML);
        inventoryContainer.setScreen(INVENTORY_SCREEN_ID);
        Group inventoryRoot = new Group();
        inventoryRoot.getChildren().addAll(inventoryContainer);
        /**
         * ***********************************************
         */
        /**
         * *********************Login********************************************
         */
        ScreensController loginContainer = new ScreensController(userAnchor);
        loginContainer.loadScreen(MAIN_USERS_ID, MAIN_USERS_FXML);
        loginContainer.loadScreen(CHG_PASSWD_ID, CHG_PASSWD_FXML);
        loginContainer.loadScreen(DELETE_ACC_ID, DELETE_ACC_FXML);
        loginContainer.loadScreen(LOGIN_ID, LOGIN_FXML);
        loginContainer.setScreen(MAIN_USERS_ID);
        Group loginGroup = new Group(loginContainer);
        /**
         * *********************Sales********************************************
         */
        ScreensController salesContainer = new ScreensController(salesAnchorPane);
        salesContainer.loadScreen(SALES_ID, SALES_FXML);
        salesContainer.setScreen(SALES_ID);

        /**
         * *********************REPORT********************************************
         */
        ScreensController reportContainer = new ScreensController(reportsAnchor);
        reportContainer.loadScreen(REPORT_ID, REPORT_FXML);
        reportContainer.setScreen(REPORT_ID);

    }

    /**
     * *************************************************************************************
     */
    //The codes that will come after this section are mainly responding to event listeners
//The first on is starts immediatedly after
    /**
     * *************************************************************************************
     */
    private void addNewOrder(Event event) throws IOException, InstantiationException, IllegalAccessException, SQLException, InterruptedException {
        newOrderScreen.setScreen(NEWORDER_ID);
        //remove((ActionEvent) event, mainOrderAnchor, "newOrder.fxml");
    }

    @FXML
    private void viewOrderHistoryActionPerformed(Event event) throws IOException, InstantiationException, IllegalAccessException, SQLException {
        displayOrdersScreen.setScreen(MAIN_ORDER_ID);
        if (event.getSource() == this.removeOrderBtn) {
            Dialogs.create().masthead(""
                    + "Tip on how to delete an order:").message(""
                            + "Select the order and then click the delete "
                            + "button \nwhich is found at the botton left corner of\n"
                            + "of your screen").showInformation();
        }
    }

    //Next three fucntions are to call the measurement forms
    @FXML
    private void loadMeasurements(Event event) throws IOException, InstantiationException, IllegalAccessException, SQLException, InterruptedException {
        viewMeasurementsScreen.setScreen(VIEWMEAS_ID);
        //remove((ActionEvent) event, this.measurementsAnchorPane, "viewOrUpdateMeasurement.fxml");
    }

    private void newMeasurement(Event event) throws IOException, InstantiationException, IllegalAccessException, SQLException, InterruptedException {
        newMeasurementsScreen.setScreen(NEWMEASUREMENT_ID);
        //remove((ActionEvent) event, this.measurementsAnchorPane, "newMeasurement.fxml");
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

    //the code from here has tree functions that simply load images to a paination object
    //the end shall be indicated as due
    @FXML
    private void selectImages(Event event) throws IOException, InstantiationException, IllegalAccessException {
        System.out.println("you clicked me ");
        openDirectoryChooser(Stage.class
                .newInstance());
    }

    //the open directory function is that which opens the directory from which the 
    //pagination images will be loaded.
    //this function is called by the button
    private void openDirectoryChooser(Stage parent) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory
                = directoryChooser.showDialog(parent);

        if (selectedDirectory != null) {
            FilenameFilter filterJpg = new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.toLowerCase().endsWith(".jpg");
                }
            };

            filesJpg = selectedDirectory.listFiles(filterJpg);
            putImagesToPagination();

        }

    }

    public void putImagesToPagination() {
        int numberOfPages = filesJpg.length;
        pagination.setPageCount(this.filesJpg.length);

        pagination.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer pageIndex) {
                return createPage(pageIndex);
            }
        });
    }

    public VBox createPage(int index) {

        ImageView imageView = new ImageView();
        //pagination.setMaxHeight(350);
        //pagination.set
        File file = filesJpg[index];
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
            imageView.setImage(image);
            imageView.setFitWidth(400);
            //imageView.setFitHeight(500);
            imageView.setPreserveRatio(true);
            imageView.setSmooth(true);
            imageView.setCache(true);

        } catch (IOException ex) {
            Logger.getLogger(Main.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        VBox pageBox = new VBox();
        pageBox.getChildren().add(imageView);
        return pageBox;
    }

    //End of sub-section to display images on paginaton
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

    //Order class
    @FXML
    public void closeApplication(Event event) {
        Action showConfirm = Dialogs.create().
                title("Closing application").
                masthead("You are bout to close the main application").
                message("Are you sure you want to continue?").showConfirm();
        if (showConfirm == Dialog.Actions.OK) {
            Platform.exit();
            System.exit(DISPOSE_ON_CLOSE);
        }
    }

    /////////////////////////////////////////////////////
    //Harvey is here////////////////////////////////////
    private void remove(ActionEvent event, AnchorPane anchor, String markup) throws IOException {
        ObservableList<Node> list = anchor.getChildren();
        list.clear();
        try {
            Parent root = FXMLLoader.load(getClass().getResource(markup));
            list.add(root);

        } catch (IOException ex) {
            Logger.getLogger(mainInterfaceController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
//        final DoubleProperty opacity = mainOrderAnchor.opacityProperty();
//        Timeline fade = new Timeline(
//                new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),
//                new KeyFrame(new Duration(1000), new EventHandler<ActionEvent>() {
//                    @Override
//                    public void handle(ActionEvent t) {
//                        list.clear();
//                        try {
//                            Parent root = FXMLLoader.load(getClass().getResource("newOrder"));
//                            list.add(root);
//                        } catch (IOException ex) {
//                            Logger.getLogger(mainInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//
//                        Timeline fadeIn = new Timeline(
//                                new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
//                                new KeyFrame(new Duration(800), new KeyValue(opacity, 1.0)));
//                        fadeIn.play();
//                    }
//                }, new KeyValue(opacity, 0.0)));
//        fade.play();

    }

    @FXML
    private void loadUserManagement(MouseEvent event) {
    }

    @FXML
    private void loadMakeSales(MouseEvent event) {
    }

    @FXML
    private void loadReport(MouseEvent event) {
    }

    @FXML
    private void loadInventory(MouseEvent event) {
    }

    @FXML
    private void loadRevokeSales(MouseEvent event) {
    }

    @FXML
    private void logout(ActionEvent event) {
    }

    @FXML
    private void loadUserManagement(ActionEvent event) {
    }

    @FXML
    private void removeUser(ActionEvent event) {
    }

}
