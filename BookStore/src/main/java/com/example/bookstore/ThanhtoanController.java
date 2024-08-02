package com.example.bookstore;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class ThanhtoanController implements Initializable {

    @FXML
    TextField IdHd;
    @FXML
    ComboBox<String> Tensach; // chỗ này chỉnh lại thành int
    @FXML
    TextField Soluong;
    @FXML
    Label txtGia;
    @FXML
    Label txtTong;
    @FXML
    TableView<BillData> id_tableHD;
    @FXML
    TableColumn<BillData , Integer> id_mahd;
    @FXML
    TableColumn<BillData , LocalDateTime> id_ngayin;
    @FXML
    TableColumn<BillData , Integer> id_tongtien;

    @FXML
    TableView<BilldetailData> id_tableDetail;
    @FXML
    TableColumn<BilldetailData , Integer> id_madetail;

    @FXML
    TableColumn<BilldetailData , String> id_tensach; // string thành int
    @FXML
    TableColumn<BilldetailData , Integer> id_soluong;
    @FXML
    TableColumn<BilldetailData , Integer> id_gia;
    @FXML
    TableColumn<BilldetailData , Integer> id_tien;

    Alert alert1;
    Database database = new Database();
    ArrayList<String> list = new ArrayList<>(); // string -> int
    public void InsertHD(ActionEvent event) throws SQLException {
        int id = database.getidHD();
        LocalDateTime time = LocalDateTime.now();

        alert1 = new Alert(Alert.AlertType.CONFIRMATION);
        alert1.setHeaderText(null);
        alert1.setContentText("Thêm hóa đơn mới ?");
        Optional<ButtonType> option = alert1.showAndWait();
        if(option.get() == ButtonType.OK){
            txtTong.setText("0đ");
            database.insertHD(id , time);
            show();
        } else if (option.get() == ButtonType.CANCEL) {
        }
    }

    public void show() throws SQLException {
        init();

        ArrayList<BillData> billData = arrayList();
        id_tableHD.getItems().addAll(billData);

    }
    public ArrayList<BillData> arrayList() throws SQLException {
        ArrayList<BillData> arrayList = new ArrayList<>();
        ResultSet r = database.getHD();

        while (r.next()){
            arrayList.add(new BillData(r.getInt(1), (LocalDateTime) r.getObject(2), r.getInt(3)));
        }
        return  arrayList;
    }
    public void init(){
        id_tableHD.getItems().clear();
        id_mahd.setCellValueFactory(new PropertyValueFactory<>("MaHd"));
        id_ngayin.setCellValueFactory(new PropertyValueFactory<>("Ngayin"));
        id_tongtien.setCellValueFactory(new PropertyValueFactory<>("TongTien"));
    }

    public void Detailadd(ActionEvent event) throws SQLException { // thêm data vào chi tiết hóa đơn
        int id = database.getidchitiet();
        int mahd  = database.getMaHD();
        String ten = Tensach.getSelectionModel().getSelectedItem(); // string thành int
        int soluong = Integer.parseInt(Soluong.getText());
        int gia = database.getGia(ten);
        int tong = gia * soluong;
        txtGia.setText(String.valueOf(tong));
        database.addThanhToanl(id , mahd , ten , soluong , gia , tong);
        showDetail();
    }

    public void showDetail() throws SQLException {
        init2();

        ArrayList<BilldetailData> billdetailData = arrayList2();
        id_tableDetail.getItems().addAll(billdetailData);

    }
    public ArrayList<BilldetailData> arrayList2() throws SQLException {
        ArrayList<BilldetailData> arrayList = new ArrayList<>();
        ResultSet r = database.getHDde();

        while (r.next()){
            arrayList.add(new BilldetailData(r.getInt(1), r.getInt(2), r.getString(3) , r.getInt(4) ,r.getInt(5) , r.getInt(6)));
        } // cái số 3 getstring thành getint
        return  arrayList;
    }
    public void init2(){
        id_tableDetail.getItems().clear();
        id_madetail.setCellValueFactory(new PropertyValueFactory<>("MaHd"));
        id_tensach.setCellValueFactory(new PropertyValueFactory<>("TenSp"));
        id_soluong.setCellValueFactory(new PropertyValueFactory<>("SoLuong"));
        id_gia.setCellValueFactory(new PropertyValueFactory<>("Gia"));
        id_tien.setCellValueFactory(new PropertyValueFactory<>("TongTien"));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            list = database.dropdownTensach();
            show();
            showDetail();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Tensach.setItems(FXCollections.observableArrayList(list));
    }

    public void endThanhtoan(ActionEvent event) throws SQLException {
        int tong = database.TongTien();
        int id = database.getMaHD();

        alert1 = new Alert(Alert.AlertType.CONFIRMATION);
        alert1.setHeaderText(null);
        alert1.setContentText("Kết thúc thanh toán ?");
        Optional<ButtonType> option = alert1.showAndWait();
        if(option.get() == ButtonType.OK){
            database.updateTong(tong , id);
            txtTong.setText(String.valueOf(tong));
            show();
            Soluong.setText(null);
            Tensach.setValue(null);
            txtGia.setText("0đ");
        } else if (option.get() == ButtonType.CANCEL) {
        }

    }

}