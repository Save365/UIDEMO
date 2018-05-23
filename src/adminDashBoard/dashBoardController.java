package adminDashBoard;

import dbUtil.dbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class dashBoardController implements Initializable {

    @FXML
    private Button btnload;
    @FXML
    private TableView<Employee>employeeTable;
    //add column form sb
    @FXML
    private TableColumn<Employee,String> idcolumn;
    @FXML
    private TableColumn<Employee,String> namecolumn;
    @FXML
    private TableColumn<Employee,String> positioncolumn;
    @FXML
    private TableColumn<Employee,String> emailcolumn;
    @FXML
    private TableColumn<Employee,String> salarycolumn;

    private dbConnection db;
    private ObservableList<Employee> data;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.db = new dbConnection();

    }
    @FXML
    private void loadEmployeeData(ActionEvent event){
        try {
            Connection conn = dbConnection.getConnection();
            this.data = FXCollections.observableArrayList();
            String sql = "select * from employee";
            ResultSet rs = conn.createStatement().executeQuery(sql);

            while (rs.next()){
                this.data.add(new Employee(rs.getString(1),rs.getString(2),
                        rs.getString(3),rs.getString(4),rs.getString(5)));
            }//while
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.idcolumn.setCellValueFactory(new PropertyValueFactory<Employee,String>("id"));
        this.namecolumn.setCellValueFactory(new PropertyValueFactory<Employee,String>("name"));
        this.positioncolumn.setCellValueFactory(new PropertyValueFactory<Employee,String>("position"));
        this.emailcolumn.setCellValueFactory(new PropertyValueFactory<Employee,String>("email"));
        this.salarycolumn.setCellValueFactory(new PropertyValueFactory<Employee,String>("salary"));

        this.employeeTable.setItems(null);
        this.employeeTable.setItems(this.data);


    }
}