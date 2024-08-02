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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class NhanVienController implements Initializable {
    @FXML
    TextField TenNv;
    @FXML
    TextField id;
    @FXML
    TextField Email;

    @FXML
    TextField Sdt;
    @FXML
    TextField Tuoi;
    /*@FXML
    TextField Role;*/
    @FXML
    TextField password;

    @FXML
    ComboBox<String> ChucVu;
    @FXML
    Label lgName;
    @FXML
    TableView<NhanVienData> id_tableNv;
    @FXML
    TableColumn<NhanVienData,Integer> id_user;
    @FXML
    TableColumn<NhanVienData,String> id_ten;
    @FXML
    TableColumn<NhanVienData,String> id_email;
    @FXML
    TableColumn<NhanVienData,String > id_sdt;
    @FXML
    TableColumn<NhanVienData,Integer> id_tuoi;
    @FXML
    TableColumn<NhanVienData,String> id_chucvu;
    @FXML
    TableColumn<NhanVienData,String> id_pass;

    Database data = new Database();

    Alert alert;

    @FXML
    private void addNhanvien(ActionEvent event) throws SQLException {
        String ten = TenNv.getText();
        String email = Email.getText();
        String sdt = Sdt.getText();
        int tuoi = Integer.parseInt(Tuoi.getText());
        String role = ChucVu.getSelectionModel().getSelectedItem();
        String pass = password.getText();
        int id = data.getUserID();
        data.insertUser(id , ten , email, sdt , tuoi , role , pass);
        show();
    }

    public void updateUser(ActionEvent actionEvent) throws SQLException {

        String ten = TenNv.getText();
        String email = Email.getText();
        String sdt = Sdt.getText();
        int tuoi = Integer.parseInt(Tuoi.getText());
        String role = ChucVu.getSelectionModel().getSelectedItem();
        String pass = password.getText();
        int macode = Integer.parseInt(id.getText());
        NhanVienData row = new NhanVienData(macode ,ten, email, sdt , tuoi , role , pass);
        NhanVienData selectedCategory = id_tableNv.getSelectionModel().getSelectedItem();
        data.updateUser(row);
        show();


    }

    public void deleteUser(ActionEvent actionEvent) throws SQLException {
        String ten = TenNv.getText();
        String email = Email.getText();
        String sdt = Sdt.getText();
        int tuoi = Integer.parseInt(Tuoi.getText());
        String role = ChucVu.getSelectionModel().getSelectedItem();
        String pass = password.getText();
        int macode = Integer.parseInt(id.getText());
        data.DeleteUser(macode);
        show();
    }

    Integer index;
    @FXML
    void getItem(MouseEvent mouseEvent){
        index = id_tableNv.getSelectionModel().getSelectedIndex();
        id.setText(id_user.getCellData(index).toString());
        TenNv.setText(id_ten.getCellData(index));
        Email.setText(id_email.getCellData(index));
        Sdt.setText(id_sdt.getCellData(index));
        Tuoi.setText(id_tuoi.getCellData(index).toString());
        ChucVu.setValue(id_chucvu.getCellData(index));
        password.setText(id_pass.getCellData(index));

    }

    public void initialize(URL url , ResourceBundle rb){
        try {
            show();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ChucVu.getItems().addAll("Nhân viên" , "Admin");
    }


    public void show() throws SQLException {
        init();

        ArrayList<NhanVienData> categoryData = arrayList();
        id_tableNv.getItems().addAll(categoryData);
    }

    public ArrayList<NhanVienData> arrayList() throws SQLException {
        ArrayList<NhanVienData> arrayList = new ArrayList<>();
        ResultSet r = data.getUser();

        while (r.next()){
            arrayList.add(new NhanVienData(r.getInt(1) ,r.getString(2), r.getString(3), r.getString(4), r.getInt(5), r.getString(6) , r.getString(7)
            ));
        }

        return  arrayList;

    }


    public void init(){
        id_tableNv.getItems().clear();
        id_user.setCellValueFactory(new PropertyValueFactory<>("ID"));
        id_ten.setCellValueFactory(new PropertyValueFactory<>("Ten"));
        id_email.setCellValueFactory(new PropertyValueFactory<>("Email"));
        id_sdt.setCellValueFactory(new PropertyValueFactory<>("Sdt"));
        id_tuoi.setCellValueFactory(new PropertyValueFactory<>("Tuoi"));
        id_chucvu.setCellValueFactory(new PropertyValueFactory<>("Role"));
        id_pass.setCellValueFactory(new PropertyValueFactory<>("Pass"));
    }

    public void Clear(ActionEvent event){
        id.setText(null);
        TenNv.setText(null);
        Email.setText(null);
        Sdt.setText(null);
        Tuoi.setText(null);
        //Role.setText(null);
        password.setText(null);
        ChucVu.setValue(null);

    }
    Stage stage;
    Scene scene;
    public void catepage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("category.fxml"));


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
