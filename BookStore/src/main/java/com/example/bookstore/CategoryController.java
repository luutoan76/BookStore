package com.example.bookstore;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;


public class CategoryController implements Initializable {
    @FXML
    private TextField Tenloai;

    @FXML
    private TextField Maloai;
    @FXML
    private TextField Mota;
    @FXML
    private Label lgName;

    @FXML
    TableView<CategoryData> id_table;
    Database database = new Database();
    @FXML
    TableColumn<CategoryData,Integer> id_maloai;
    @FXML
    TableColumn<CategoryData,String>  id_loai;
    @FXML
    TableColumn<CategoryData,String>  id_mota;
    Alert alert;
    Stage stage;


    @FXML
    private void addCategory(ActionEvent event) throws SQLException {
        Alert alert1;
        String tenloai = Tenloai.getText();
        String mota = Mota.getText();
        Database data = new Database();
        int id = data.getCateID();
        data.insertCategory(tenloai , mota , id);
        show();
    }
    // update 2

    public void show() throws SQLException {
        init();

        ArrayList<CategoryData> categoryData = arrayList();
        id_table.getItems().addAll(categoryData);
    }



    public void initialize(URL url , ResourceBundle rb){

        try {
            show();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<CategoryData> arrayList() throws SQLException {
        ArrayList<CategoryData> arrayList = new ArrayList<>();
        ResultSet r = database.getCate();

        while (r.next()){
            arrayList.add(new CategoryData(r.getInt(1), r.getString(2), r.getString(3)));
        }

        return  arrayList;

    }
    public void init(){
        id_table.getItems().clear();
        id_maloai.setCellValueFactory(new PropertyValueFactory<>("Maloai"));
        id_loai.setCellValueFactory(new PropertyValueFactory<>("TenLoai"));
        id_mota.setCellValueFactory(new PropertyValueFactory<>("MoTa"));


    }

    // update 3
    public void updateCategory(ActionEvent actionEvent) throws SQLException {

        String tenLoai = Tenloai.getText();
        String maLoai = Maloai.getText();
        String moTa = Mota.getText();
        int idloai = Integer.parseInt(maLoai);
        CategoryData data = new CategoryData(idloai, tenLoai, moTa);
        CategoryData selectedCategory = id_table.getSelectionModel().getSelectedItem();
        database.updateCate(data);
        show();
        if (selectedCategory != null) {
            selectedCategory.setTenLoai(tenLoai);
            selectedCategory.setMaloai(idloai);
            selectedCategory.setMoTa(moTa);

            // Cập nhật lại dữ liệu trong TableView
            id_table.refresh();

        }

    }

    //update 4
    public void deleteCate(ActionEvent actionEvent) throws SQLException {
        String tenLoai = Tenloai.getText();
        String maLoai = Maloai.getText();
        String moTa = Mota.getText();
        int idloai = Integer.parseInt(maLoai);
        Database db = new Database();
        db.Delete(idloai);
        show();
    }

    public void clear(ActionEvent actionEvent) throws SQLException{
        Tenloai.setText("");
        Maloai.setText("");
        Mota.setText("");
    }

    String username;
    public void displayname(String name){
        lgName.setText(name);
        username = name;
    }
    Integer index;
    @FXML
    void getItem(MouseEvent mouseEvent){
        index = id_table.getSelectionModel().getSelectedIndex();
        Tenloai.setText(id_loai.getCellData(index));
        Maloai.setText(id_maloai.getCellData(index).toString());
        Mota.setText(id_mota.getCellData(index));

    }

    Scene scene;
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
