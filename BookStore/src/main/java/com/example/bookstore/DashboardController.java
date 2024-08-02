package com.example.bookstore;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    Label money;
    @FXML
    Label book;
    @FXML
    Label doanhthu;
    @FXML
    BarChart<? , ?> barChart;
    Database db = new Database();
    int total = 0;
    int soluong = 0;
    int tongdoanhthu = 0;
    Stage stage;
    ArrayList<String> day = new ArrayList<>();
    ArrayList<Integer> tien = new ArrayList<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            total = db.getMoney();
            soluong = db.booksold();
            tongdoanhthu = db.tongdoanhthu();
            barchart();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        money.setText(total + " đ");
        book.setText(soluong + " quyển");
        doanhthu.setText(tongdoanhthu + " đ");
    }
    public void chart()  {

        String sql = "select NgayIn , Sum(TongTien) from hoadon group by NgayIn order by TIMESTAMP(NgayIn) ASC LIMIT 8";
        try {

            XYChart.Series chartData = new XYChart.Series();
            PreparedStatement ps = Database.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                chartData.getData().add(new XYChart.Data(resultSet.getString(1) , resultSet.getInt(2)));

            }
            barChart.getData().add(chartData);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void barchart() throws SQLException {
        XYChart.Series series = new XYChart.Series<>();
        series.setName("Ngày");
        day = DsDay();
        //day = db.Dsngay();
        tien = DsTien();
        for(int i = 0; i < day.size();i++){
            series.getData().add(new XYChart.Data(day.get(i), tien.get(i)));
        }
        /*series.getData().add(new XYChart.Data<>("Tháng 1", 100));
        series.getData().add(new XYChart.Data<>("Tháng 2", 200));
        series.getData().add(new XYChart.Data<>("Tháng 3", 150));
        series.getData().add(new XYChart.Data<>("Tháng 4", 300));*/
        barChart.getData().add(series);
    }

    public ArrayList<String> DsDay() throws SQLException {
        ArrayList<String> arrayList = new ArrayList<>();
        ResultSet r = db.Dsngay();
        while (r.next()){
            arrayList.add(r.getString(1));
        }
        return  arrayList;
    }

    public ArrayList<Integer> DsTien() throws SQLException {
        ArrayList<Integer> arrayList = new ArrayList<>();
        ResultSet r = db.Dsngay();
        while (r.next()){
            arrayList.add(r.getInt(2));
        }
        return  arrayList;
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
}
