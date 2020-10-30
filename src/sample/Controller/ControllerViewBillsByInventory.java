package sample.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Data;
import sample.Main;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ControllerViewBillsByInventory implements Initializable {
    private Connection conn = Main.returnCon();
    @FXML
    private TableView<Data> table;
    @FXML
    private TableColumn<Data, Integer> col3;
    @FXML
    private TableColumn<Data, String> col1;
    @FXML
    private TableColumn<Data, Date> col2;
    @FXML
    private DatePicker date;
    @FXML
    private TextField inventory;
    private ObservableList<Data> DataTable = FXCollections.observableArrayList();

    private PreparedStatement preparedStatementSearch() throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("select a.name,b.date,b.quantity from inventory a \n" +
                "inner join bill b on b.id_inventory=a.id_inventory\n" +
                "WHERE a.name=?;");
        preparedStatement.setString(1, inventory.toString());
        return preparedStatement;
    }

    public void search() {
        table.getItems().clear();
        col1.setCellValueFactory(new PropertyValueFactory<Data, String>("name"));
        col2.setCellValueFactory(new PropertyValueFactory<Data, Date>("date"));
        col3.setCellValueFactory(new PropertyValueFactory<Data, Integer>("quantity"));


        try(PreparedStatement preparedStatement = preparedStatementSearch();
            ResultSet rs = preparedStatement.executeQuery();) {
            System.out.println(preparedStatement);
            while (rs.next()) {
                DataTable.add(new Data(rs.getString(1), rs.getDate(2).toLocalDate(),rs.getDouble(3)));
            }
            table.setItems(DataTable);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
