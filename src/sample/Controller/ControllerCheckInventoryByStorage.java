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

public class ControllerCheckInventoryByStorage implements Initializable {
    private Connection conn = Main.returnCon();
    @FXML
    private TableView<Data> table;
    @FXML
    private TableColumn<Data, String> col1;
    @FXML
    private TableColumn<Data, Double> col2;
    @FXML
    private DatePicker date;
    @FXML
    private ChoiceBox type;
    @FXML
    private TextField name;
    private ObservableList<Data> DataTable = FXCollections.observableArrayList();

    private PreparedStatement preparedStatementSearch() throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT c.name,sum(quantity) FROM bill b\n" +
                "inner join  storage a on a.id_storage=b.id_storage\n" +
                "inner join  inventory c on c.id_inventory=b.id_inventory\n" +
                "where b.date<=? and a.name=? and c.type=? group by c.name");
        preparedStatement.setDate(1, Date.valueOf((date.getValue())));
        preparedStatement.setString(2, name.getText());
        preparedStatement.setString(3, type.getValue().toString());
        System.out.println();
        return preparedStatement;
    }

    public void search() {
        table.getItems().clear();
        col1.setCellValueFactory(new PropertyValueFactory<Data, String>("name"));
        col2.setCellValueFactory(new PropertyValueFactory<Data, Double>("quantity"));

        try(PreparedStatement preparedStatement = preparedStatementSearch();
            ResultSet rs = preparedStatement.executeQuery();) {
            System.out.println(preparedStatement);
            while (rs.next()) {
                DataTable.add(new Data(rs.getString(1), rs.getDouble(2)));
            }
            table.setItems(DataTable);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String typeArray []={"спецодежда","инструменты", "подсобные средства"};;
        type.getItems().addAll(typeArray);
    }
}
