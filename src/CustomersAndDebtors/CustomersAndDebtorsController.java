/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomersAndDebtors;

import General.ControlledScreen;
import General.ScreensController;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import static khamals.mainInterfaceController.ADD_ACCOUNT_ID;
import static khamals.mainInterfaceController.MODIFY_ACCOUNT_ID;
import static khamals.mainInterfaceController.PAY_DEBT_ID;
import static khamals.mainInterfaceController.TAKE_DEBT_ID;

/**
 * FXML Controller class
 *
 * @author Harvey
 */
public class CustomersAndDebtorsController implements Initializable, ControlledScreen {

    @FXML
    private TableView<Customer> CusorDeb;
    @FXML
    private TableColumn index;
    @FXML
    private TableColumn nam;
    @FXML
    private TableColumn location;
    @FXML
    private TableColumn tel;
    @FXML
    private TableColumn id;
    @FXML
    private AnchorPane anchorPane;

    ObservableList tfname;
    ObservableList tindex;
    ObservableList tid;
    ObservableList ttel;
    ObservableList tloc;

    ArrayList Names = new ArrayList();
    ArrayList ID = new ArrayList();
    ArrayList tlocation = new ArrayList();
    ArrayList telephone = new ArrayList();
    ArrayList fname = new ArrayList();
    ArrayList sname = new ArrayList();

    private ScreensController screen;

    public static PopTabCustomers Populator;

    ;

    /**
     * initialises the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Populator = new CustomersAndDebtorsController.PopTabCustomers();
        Populator.populateTabeCus();
    }

    @FXML
    public void populateDeb() {
        CusorDeb.getItems().clear();
//        TableColumn sta = new TableColumn("STATUS");
        TableColumn ind = new TableColumn("INDEX");
        TableColumn da = new TableColumn("DATE TACKEN");
        TableColumn tda = new TableColumn("LATEST DATE OF PAYMENT");
        TableColumn amoun = new TableColumn("AMOUNT IN TOTAL");
        TableColumn name = new TableColumn("NAMES");
        da.setPrefWidth(200);
        name.setPrefWidth(250);
        amoun.setPrefWidth(250);
        tda.setPrefWidth(225);
        ind.setPrefWidth(200);
//        sta.setPrefWidth(150);

        ind.setCellValueFactory(new PropertyValueFactory("Index"));
        name.setCellValueFactory(new PropertyValueFactory("Name"));
        tda.setCellValueFactory(new PropertyValueFactory("Name1"));
        da.setCellValueFactory(new PropertyValueFactory("Name2"));
        amoun.setCellValueFactory(new PropertyValueFactory("Amount"));
//        sta.setCellValueFactory(new PropertyValueFactory("status"));
        CusorDeb.getColumns().clear();
        CusorDeb.getColumns().addAll(ind, name, amoun, da, tda);

        Functions function = new Functions();
        ArrayList vname = new ArrayList();
        ArrayList inde = new ArrayList();
        ArrayList amount2 = new ArrayList();
        ArrayList ldate = new ArrayList();
        ArrayList tdate = new ArrayList();
        ArrayList stat = new ArrayList();
        //Functions function = new Functions();
        ldate = function.getAllDData("LATEST_DATE_OF_PAYMENT", "DEBTORS");
        tdate = function.getAllDData("DATE_TACKEN", "DEBTORS");
//        stat = function.getAllDData("STATUS", "DEBTORS");
        vname = function.getAllDData("NAME", "DEBTORS");
        inde = function.getAllDData("ID", "DEBTORS");
        amount2 = function.getAllDData("AMOUNT", "DEBTORS");//HERE

        tloc = FXCollections.observableArrayList();
        String tempName;
        for (int i = 0; i < vname.size(); i++) {
            Debtors data = new Debtors();
            //tempName =  fname.get(i).toString();
            tempName = vname.get(i).toString();
            //data.Name.setValue(tempName);
//            data.status.setValue(stat.get(i).toString());
            data.Name1.setValue(ldate.get(i).toString());

            data.Name2.setValue(tdate.get(i).toString());
            data.Name.setValue(tempName);
            data.Index.setValue(String.valueOf(i));
            data.Amount.setValue(amount2.get(i).toString());
            tloc.add(data);
            //System.out.println();  
        }
        try {
            CusorDeb.getItems().setAll(tloc);
            CusorDeb.setItems(tloc);
        } catch (Exception nn) {
            System.out.println(nn.getMessage());
        }
        // System.out.println(tloc.toString());

    }

    @FXML
    public void populateTabeCus(ActionEvent event) {
        Populator.populateTabeCus();
    }

    @Override
    public void setScreenParent(ScreensController pane) {
        screen = pane;
    }

    @FXML
    private void loadAcustomer(ActionEvent event) {
        screen.setScreen(ADD_ACCOUNT_ID);
        System.out.println("4");
    }

    @FXML
    private void loadMCustmer(ActionEvent event) throws SQLException {
        ModifyAccountController.reload.loadFields();
        screen.setScreen(MODIFY_ACCOUNT_ID);
        System.out.println("3");
    }

    @FXML
    private void loadADebt(ActionEvent event) throws SQLException {
        TakeDebtController.reload.reload();
        screen.setScreen(TAKE_DEBT_ID);
        System.out.println("2");
    }

    @FXML
    private void loadPDebt(ActionEvent event) throws SQLException {
        PayDebtController.reload.getDebtors();
        screen.setScreen(PAY_DEBT_ID);
        System.out.println("1");
    }

    public class PopTabCustomers {

        public void populateTabeCus() {
            index.setCellValueFactory(new PropertyValueFactory("index"));
            index.setStyle("-fx-font-size: 13px;");
            nam.setCellValueFactory(new PropertyValueFactory("Name"));
            nam.setStyle("-fx-font-size: 13px;");
            location.setCellValueFactory(new PropertyValueFactory("Location"));
            tel.setCellValueFactory(new PropertyValueFactory("Tel"));
            id.setCellValueFactory(new PropertyValueFactory("ID"));
            CusorDeb.getColumns().clear();
            CusorDeb.getColumns().addAll(index, nam, location, tel, id);
            Functions function = new Functions();
            fname = function.getAllData("FIRST_NAME", "Customers");
            sname = function.getAllData("LAST_NAME", "Customers");
            ID = function.getAllData("ID", "Customers");
            tlocation = function.getAllData("LOCATION", "Customers");
            telephone = function.getAllData("TELEPHONE", "Customers");
            tfname = FXCollections.observableArrayList(fname);
            tid = FXCollections.observableArrayList(ID);
            tindex = FXCollections.observableArrayList();
            tloc = FXCollections.observableArrayList();
            ttel = FXCollections.observableArrayList(telephone);

            String tempName;
            for (int i = 0; i < fname.size(); i++) {
                Customer data = new Customer();
                tempName = fname.get(i).toString();
                tempName += " " + sname.get(i).toString();
                data.Name.setValue(tempName);
                data.ID.setValue(ID.get(i).toString());
                data.Tel.setValue(telephone.get(i).toString());
                data.Location.setValue(tlocation.get(i).toString());
                data.index.setValue(String.valueOf(i));
                tloc.add(data);
            }
            try {
                CusorDeb.getItems().setAll(tloc);
                CusorDeb.setItems(tloc);

            } catch (Exception nn) {
                System.out.println(nn.getMessage());
            }
        }
    }

}
