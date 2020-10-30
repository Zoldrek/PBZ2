package sample.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Data;
import sample.Main;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerStorage implements Initializable {
    private Connection conn = Main.returnCon();
    @FXML
    private TableView<Data> table;
    @FXML
    private TableColumn<Data, String> col3, col4, col5, col6, col7, col8;
    @FXML
    private TableColumn<Data, Integer> col1, col2, col9;
    @FXML
    private TextField id, name, number, address, telephone;
    @FXML
    private ChoiceBox type, id_region;
    private ObservableList<Data> DataTable = FXCollections.observableArrayList();


    public void createTable() {

        table.getItems().clear();


        col1.setCellValueFactory(new PropertyValueFactory<Data, Integer>("id"));
        col2.setCellValueFactory(new PropertyValueFactory<Data, Integer>("number"));
        col3.setCellValueFactory(new PropertyValueFactory<Data, String>("name"));
        col4.setCellValueFactory(new PropertyValueFactory<Data, String>("telephone"));

        try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM storage");
             ResultSet rs = preparedStatement.executeQuery();) {
            while (rs.next()) {
                DataTable.add(new Data(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4)));
            }
            table.setItems(DataTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private PreparedStatement preparedStatementSearch() throws SQLException{
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM storage WHERE id_storage=?");
        preparedStatement.setInt(1, Integer.parseInt(id.getText()));
        return preparedStatement;
    }

    public void search() {
        try(PreparedStatement preparedStatement = preparedStatementSearch();
            ResultSet rs = preparedStatement.executeQuery();) {
            while (rs.next()) {
                number.setText(rs.getString(2));
                name.setText(rs.getString(3));
                telephone.setText(rs.getString(4));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private PreparedStatement preparedStatementCount() throws SQLException{
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT COUNT(id_storage) FROM storage WHERE id_storage=?");
        preparedStatement.setInt(1, Integer.parseInt(id.getText()));
        return preparedStatement;
    }

    private PreparedStatement preparedStatementSave() throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO storage(number,name,telephone) " +
                "VALUES (?,?,?)");
        preparedStatement.setInt(1, Integer.parseInt(number.getText()));
        preparedStatement.setString(2, name.getText());
        preparedStatement.setString(3, telephone.getText());
        return preparedStatement;
    }

    private PreparedStatement preparedStatementUpdate() throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("UPDATE storage SET number=?,name=?,telephone=? WHERE id_storage=?");
        preparedStatement.setInt(1, Integer.parseInt(number.getText()));
        preparedStatement.setString(2, name.getText());
        preparedStatement.setString(3, telephone.getText());
        preparedStatement.setInt(4, Integer.parseInt(id.getText()));
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
        PreparedStatement   preparedStatement = conn.prepareStatement("DELETE FROM storage CASCADE WHERE id_storage=?");
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
