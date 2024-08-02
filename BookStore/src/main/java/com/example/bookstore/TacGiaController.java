package com.example.bookstore;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TacGiaController implements Initializable {

    @FXML
    TextField idtg;
    @FXML
    TextField tentg;
    @FXML
    TableView<TacGiaData> id_tableTacgia;
    Database database = new Database();
    @FXML
    TableColumn<TacGiaData,Integer> id_idtg;
    @FXML
    TableColumn<TacGiaData,String> id_tentg;
    ArrayList<TacGiaData> list = new ArrayList<>();
    Alert alert;
    Stage stage;


    public void show() throws SQLException {
        init();

        ArrayList<TacGiaData> categoryData = arrayList();
        id_tableTacgia.getItems().addAll(categoryData);


    }
    public ArrayList<TacGiaData> arrayList() throws SQLException {
        ArrayList<TacGiaData> arrayList = new ArrayList<>();
        ResultSet r = database.getAuthor();

        while (r.next()){
            arrayList.add(new TacGiaData(r.getInt(1), r.getString(2)));
        }

        return  arrayList;

    }
    public void init(){
        id_tableTacgia.getItems().clear();
        id_idtg.setCellValueFactory(new PropertyValueFactory<>("id"));
        id_tentg.setCellValueFactory(new PropertyValueFactory<>("ten"));
    }

    Integer index;
    @FXML
    void getItem(MouseEvent mouseEvent){
        index = id_tableTacgia.getSelectionModel().getSelectedIndex();
        idtg.setText(id_idtg.getCellData(index).toString());
        tentg.setText(id_tentg.getCellData(index));
    }

    @FXML
    private void addAuthor(ActionEvent event) throws SQLException {
        Alert alert1;
        String tenloai = tentg.getText();
        Database data = new Database();
        data.insertAuthor(tenloai);
        show();
    }

    public void updateAuthor(ActionEvent actionEvent) throws SQLException {

        String tenloai = tentg.getText();
        int id = Integer.parseInt(idtg.getText());
        TacGiaData data = new TacGiaData(id, tenloai);
        TacGiaData selectedCategory = id_tableTacgia.getSelectionModel().getSelectedItem();
        database.updateAuthor(data);
        show();

    }

    public void deleteAuthor(ActionEvent actionEvent) throws SQLException {
        String tenloai = tentg.getText();
        int id = Integer.parseInt(idtg.getText());
        Database db = new Database();
        db.Deletetacgia(id);
        show();
        tentg.setText(null);
        idtg.setText(null);
    }

    public void clear(ActionEvent actionEvent) throws SQLException{
        tentg.setText(null);
        idtg.setText(null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            show();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    Scene scene;
    public void catepage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("loaisp.fxml"));

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void product(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("product.fxml"));

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void nvPage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("nhanvien.fxml"));

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void billpage(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("thanhtoan.fxml"));

        Stage stage1 = new Stage();
        //stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        scene = new Scene(root);
        stage1.setScene(scene);
        stage1.show();

    }
    public void tacgiapage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("tacgia.fxml"));

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void dashboard(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
