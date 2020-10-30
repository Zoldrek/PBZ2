package sample.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerWinMain {

    @FXML
    public void WinStorage() throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/Storage.fxml"));
        primaryStage.setTitle("Window");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void WinInventory(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/Inventory.fxml"));
        primaryStage.setTitle("Window");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void WinBill(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/Bill.fxml"));
        primaryStage.setTitle("Window");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void WinCheckInventoryByStorage(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/CheckInventoryByStorage.fxml"));
        primaryStage.setTitle("Window");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void WinCheckStorages(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/CheckStorages.fxml"));
        primaryStage.setTitle("Window");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void WinViewBillsByInventory(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/ViewBillsByInventory.fxml"));
        primaryStage.setTitle("Window");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
