package sample.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Data;
import sample.Main;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ControllerBill implements Initializable {
    private Connection conn = Main.returnCon();
    @FXML
    private TableView<Data> table;
    @FXML
    private TableColumn<Data, String> col3,col2, col6,col7,col8, col5;
    @FXML
    private TableColumn<Data, Integer> col1;
    @FXML
    private TableColumn<Data, Double> col4;
    @FXML
    private TextField id, quantity, selling_price,name_employee;
    @FXML
    private ChoiceBox storage, inventory,position;
    @FXML
    private DatePicker date;
    private ObservableList<Data> DataTable = FXCollections.observableArrayList();


    public void createTable() {
        String positionArray []={"работник","деректор"};
        table.getItems().clear();
        storage.getItems().clear();
        inventory.getItems().clear();
        position.getItems().clear();

        position.getItems().addAll(positionArray);

        col1.setCellValueFactory(new PropertyValueFactory<Data, Integer>("id"));
        col2.setCellValueFactory(new PropertyValueFactory<Data, String>("name"));
        col3.setCellValueFactory(new PropertyValueFactory<Data, String>("name_inventory"));
        col4.setCellValueFactory(new PropertyValueFactory<Data, Double>("quantity"));
        col5.setCellValueFactory(new PropertyValueFactory<Data, String>("date"));
        col6.setCellValueFactory(new PropertyValueFactory<Data, String>("name_employee"));
        col7.setCellValueFactory(new PropertyValueFactory<Data, String>("position_employee"));

        try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM bill");
             PreparedStatement preparedStatementStorage = conn.prepareStatement("SELECT id_storage FROM storage");
             PreparedStatement preparedStatementinventory = conn.prepareStatement("SELECT id_inventory FROM inventory");
             ResultSet rs = preparedStatement.executeQuery();
             ResultSet rsStorage = preparedStatementStorage.executeQuery();
             ResultSet rsinventory = preparedStatementinventory.executeQuery();) {
            while (rs.next()) {
                DataTable.add(new Data(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4),
                       rs.getDate(5).toLocalDate(), rs.getString(6), rs.getString(7)));
            }
            table.setItems(DataTable);
            while (rsStorage.next()) {
                storage.getItems().addAll(rsStorage.getString(1));
            }
            while (rsinventory.next()) {
                inventory.getItems().addAll(rsinventory.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private PreparedStatement preparedStatementSearch() throws SQLException{
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM bill WHERE id_bill=?");
        preparedStatement.setInt(1, Integer.parseInt(id.getText()));
        return preparedStatement;
    }

    public void search() {
        try(PreparedStatement preparedStatement = preparedStatementSearch();
            ResultSet rs = preparedStatement.executeQuery();) {
            while (rs.next()) {
                storage.setValue(rs.getString(2));
                inventory.setValue(rs.getString(3));
                quantity.setText(rs.getString(4));
                date.setValue(rs.getDate(5).toLocalDate());
                name_employee.setText(rs.getString(6));
                position.setValue(rs.getString(7));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private PreparedStatement preparedStatementCount() throws SQLException{
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT COUNT(id_bill) FROM bill WHERE id_bill=?");
        preparedStatement.setInt(1, Integer.parseInt(id.getText()));
        return preparedStatement;
    }

    private PreparedStatement preparedStatementSave() throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO bill(id_storage,id_inventory,quantity,date,name_employee," +
                "position_employee) " +
                "VALUES (?,?,?,?,?,?)");
        preparedStatement.setInt(1, Integer.parseInt(storage.getValue().toString()));
        preparedStatement.setInt(2,Integer.parseInt(inventory.getValue().toString()));
        preparedStatement.setDouble(3, Double.parseDouble(quantity.getText()));
        preparedStatement.setDate(4, Date.valueOf(date.getValue()));
        preparedStatement.setString(5, name_employee.getText());
        preparedStatement.setString(6, position.getValue().toString());
        return preparedStatement;
    }

    private PreparedStatement preparedStatementUpdate() throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("UPDATE bill SET id_storage=?,id_inventory=?,quantity=?,date=?,name_employee=?,position_employee=? WHERE id_bill=?");
        preparedStatement.setInt(1, Integer.parseInt(storage.getValue().toString()));
        preparedStatement.setInt(2,Integer.parseInt(inventory.getValue().toString()));
        preparedStatement.setDouble(3, Double.parseDouble(quantity.getText()));
        preparedStatement.setDate(4, Date.valueOf(date.getValue()));;
        preparedStatement.setInt(5, Integer.parseInt(id.getText()));
        preparedStatement.setString(6, name_employee.getText());
        preparedStatement.setString(7, position.getValue().toString());
        return preparedStatement;
    }

    public void save(ActionEvent actionEvent) throws SQLException {
        int count = 0;
        if (!id.getText().equals("")) {
            try (PreparedStatement preparedStatement = preparedStatementCount();
                 ResultSet rs = preparedStatement.executeQuery();) {
                while (rs.next()) {
                    count = rs.getInt(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (count == 1) {
            try (PreparedStatement preparedStatement = preparedStatementUpdate();) {
                System.out.println(preparedStatement);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (count == 0) {
            try (PreparedStatement preparedStatement = preparedStatementSave();) {
                System.out.println(preparedStatement);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        createTable();
        id.clear();
    }

    private PreparedStatement preparedStatementDelete() throws SQLException{
        PreparedStatement   preparedStatement = conn.prepareStatement("DELETE FROM bill CASCADE WHERE id_bill=?");
        preparedStatement.setInt(1, Integer.parseInt(id.getText()));
        return preparedStatement;
    }

    public void delete() {
        try(PreparedStatement preparedStatement = preparedStatementDelete();) {
            preparedStatement.executeUpdate();
            createTable();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createTable();
    }
}
