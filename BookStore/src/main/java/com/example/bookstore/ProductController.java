package com.example.bookstore;

import javafx.collections.FXCollections;
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

public class ProductController implements Initializable{
    @FXML
    TextField MaSp;
    @FXML
    TextField TenSp;
    @FXML
    ComboBox<Integer> MaL;
    @FXML
    TextField Gia;
    @FXML
    TextField MoTa;
    /*@FXML
    TextField tacgia;*/
    @FXML
    TextField theloai;

    @FXML
    ComboBox<String> author;
    @FXML
    ComboBox<String> tenloai;
    @FXML
    Label lgName;
    @FXML
    TableView<ProductData> id_tableProduct;
    @FXML
    TableColumn<ProductData,String> id_masp;
    @FXML
    TableColumn<ProductData,String> id_tensp;
    @FXML
    TableColumn<ProductData,String> id_mota;
    @FXML
    TableColumn<ProductData,String> id_maloai;
    @FXML
    TableColumn<ProductData,Integer> id_gia;
    @FXML
    TableColumn<ProductData,String> id_tacgia;

    Connection connection;
    ArrayList<String> list = new ArrayList<>();
    Database database = new Database();
    Stage stage;
    Alert alert1;
    ArrayList<String> nameAuthor = new ArrayList<>();


    public void addProduct(ActionEvent event) throws SQLException {
        String mota = MoTa.getText();
        String masp = MaSp.getText();
        //int maloai  = MaL.getSelectionModel().getSelectedItem();
        String tensp = TenSp.getText();
        String gia  = Gia.getText();
        String tg = author.getSelectionModel().getSelectedItem();
        String cate = tenloai.getSelectionModel().getSelectedItem();
        int price = Integer.parseInt(gia);
        boolean checkID = database.checkIdPro(masp);
        if(checkID == true){
            database.insertProduct(mota, masp , tensp , price , tg , cate);
            show();
        }if (checkID == false){
            alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Message");
            alert1.setHeaderText(null);
            alert1.setContentText("MaSP bi trung");
            alert1.showAndWait();
        }
    }

    public void show() throws SQLException {
        init();

        ArrayList<ProductData> productData = arrayList();
        id_tableProduct.getItems().addAll(productData);

    }
    public ArrayList<ProductData> arrayList() throws SQLException {
        ArrayList<ProductData> arrayList = new ArrayList<>();
        ResultSet r = database.getPro();

        while (r.next()){
            arrayList.add(new ProductData(r.getString(1), r.getString(2), r.getString(3), r.getInt(4),r.getInt(5) , r.getString(6) , r.getString(7)));
        }

        return  arrayList;
    }

    public void init(){
        id_tableProduct.getItems().clear();
        id_masp.setCellValueFactory(new PropertyValueFactory<>("masp"));
        id_tensp.setCellValueFactory(new PropertyValueFactory<>("tensp"));
        id_mota.setCellValueFactory(new PropertyValueFactory<>("mota"));
        id_maloai.setCellValueFactory(new PropertyValueFactory<>("Theloai"));
        id_gia.setCellValueFactory(new PropertyValueFactory<>("gia"));
        id_tacgia.setCellValueFactory(new PropertyValueFactory<>("Tacgia"));
    }
    public void cateButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("category.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene;
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            list = database.dropdownMaloai();
            nameAuthor = database.dropdownAuthorname();
            show();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        tenloai.setItems(FXCollections.observableArrayList(list));
        author.setItems(FXCollections.observableArrayList(nameAuthor));
    }


    public void updatePro(ActionEvent event) throws SQLException {
        String mota = MoTa.getText();
        String masp = MaSp.getText();
        String cate = tenloai.getSelectionModel().getSelectedItem();
        String tensp = TenSp.getText();
        String gia  = Gia.getText();
        int price = Integer.parseInt(gia);
        String Tacgia = author.getSelectionModel().getSelectedItem();
/*
        ProductData data = new ProductData(mota, masp, tensp , maloai , price , Tacgia , Theloai);
        ProductData selectedPro = id_tableProduct.getSelectionModel().getSelectedItem();*/

        database.updatePro(mota , masp , price , Tacgia , cate);

        boolean checkID = database.checkIdPro(masp);
        show();


    }

    public void deletePro(ActionEvent event) throws SQLException {
        String masp = MaSp.getText();
        database.DeletePro(masp);
        show();
    }

    public void clear(ActionEvent event){
        MoTa.setText(null);
        MaSp.setText(null);
        author.setValue(null);
        tenloai.setValue(null);
        TenSp.setText(null);
        Gia.setText(null);
    }


    Scene scene;

    public void catepage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("loaisp.fxml"));

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    Integer index;
    @FXML
    void getItem(MouseEvent mouseEvent){
        index = id_tableProduct.getSelectionModel().getSelectedIndex();
        MaSp.setText(id_masp.getCellData(index));
        MoTa.setText(id_mota.getCellData(index));
        TenSp.setText(id_tensp.getCellData(index));
        tenloai.setValue(id_maloai.getCellData(index));
        author.setValue(id_tacgia.getCellData(index));
        Gia.setText(id_gia.getCellData(index).toString());

    }

    public void billpage(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("thanhtoan.fxml"));

        Stage stage1 = new Stage();
        //stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        scene = new Scene(root);
        stage1.setScene(scene);
        stage1.show();

    }

    public void nvPage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("nhanvien.fxml"));

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
    public void displayname(String name){
        lgName.setText("Hello: " + name);
    }
}
