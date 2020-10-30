package sample.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Data;
import sample.Main;
import java.sql.*;


public class ControllerCheckStorages {
    private Connection conn = Main.returnCon();
    @FXML
    private TableView<Data> table;
    @FXML
    private TableColumn<Data, String> col3,col4,col5;
    @FXML
    private TableColumn<Data, Integer> col1, col2;
    @FXML
    private DatePicker date;
    private ObservableList<Data> DataTable = FXCollections.observableArrayList();

    private PreparedStatement preparedStatementSearch() throws SQLException{
        PreparedStatement preparedStatement = conn.prepareStatement("select * from storage p\n");
        return preparedStatement;
    }

    public void search() {
        table.getItems().clear();
        col1.setCellValueFactory(new PropertyValueFactory<Data, Integer>("id"));
        col2.setCellValueFactory(new PropertyValueFactory<Data, Integer>("number"));
        col3.setCellValueFactory(new PropertyValueFactory<Data, String>("name"));
        col4.setCellValueFactory(new PropertyValueFactory<Data, String>("telephone"));

        try(PreparedStatement preparedStatement = preparedStatementSearch();
            ResultSet rs = preparedStatement.executeQuery();) {
            System.out.println(preparedStatement);
            while (rs.next()) {
                DataTable.add(new Data(rs.getInt(1), rs.getInt(2 ), rs.getString(3),rs.getString(4)));
            }
            table.setItems(DataTable);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
