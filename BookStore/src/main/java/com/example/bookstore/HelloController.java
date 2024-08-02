package com.example.bookstore;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private TextField username;

    @FXML
    private TextField password;
    private Stage stage;
    Scene scene;
    Alert alert;
    Parent root;

    @FXML
    protected void loginBtn(ActionEvent event) throws IOException {
        String user = username.getText();
        String pass = password.getText();
        Database data = new Database();

        if(user.isEmpty() || pass.isEmpty()){

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Khong duoc de trong");
            alert.showAndWait();
        }
        boolean check = data.loginAdmin(user , pass);
        if(check){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("loaisp.fxml"));
            root = loader.load();
            CategoryController categoryController = loader.getController();
            categoryController.displayname(user);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();

            scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        }if(!check) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Wrong username and pass");
            alert.showAndWait();
        }


    }
}