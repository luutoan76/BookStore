package com.example.bookstore;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Database {
    Statement statement;
    Alert alert;
    public static Connection getConnection() {
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/nhasach";
        String user = "root";
        String password = "anhtoan1";

        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the MySQL database!");
        } catch (SQLException e) {
            System.out.println("Failed to connect to the MySQL database!");
            e.printStackTrace();
        }
        return conn;
    }

    public boolean loginAdmin(String username , String password){
        String sql = "SELECT * FROM admin WHERE username = ? and pass = ?";
        try{
            PreparedStatement prepare = getConnection().prepareStatement(sql);

            prepare.setString(1 , username);
            prepare.setString(2 , password);
            ResultSet result = prepare.executeQuery();
            result = prepare.executeQuery();
            if(result.next()){
                return true;
            }else {
                return false;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    //

    public void insertCategory(String ten , String mota, int id){
        String strNum = Integer.toString(id);
        Alert alert;
        try {
            if(ten.isEmpty() || mota.isEmpty() || strNum.isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Khong duoc de trong");
            }
            else {
                String sql = "INSERT INTO loaisp (TenLoai, MoTa, MaLoai) VALUES (?, ?, ?)";
                PreparedStatement statement = getConnection().prepareStatement(sql);
                statement.setString(1, ten);
                statement.setString(2, mota);
                statement.setInt(3, id);
                statement.executeUpdate();
            }

        } catch (SQLException e) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Khong duoc de trong");
        }

    }
    public int getCateID() throws SQLException {
        String sql = "select MaLoai from loaisp order by MaLoai desc limit 1;";
        PreparedStatement statement = getConnection().prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        int maloai = 0;
        if(resultSet.next()){
            maloai = resultSet.getInt("MaLoai");
        }
        return maloai + 1;
    }

    public void close() {
        System.exit(0);
    }
    public ResultSet getCate() throws SQLException {
        statement = getConnection().createStatement();
        return statement.executeQuery("SELECT * FROM loaisp");
    }

    //update 3
    public void updateCate(CategoryData cate) throws SQLException{
        PreparedStatement st = getConnection().prepareStatement("UPDATE loaisp SET TenLoai = ?, MoTa = ? WHERE MaLoai = ?");
        st.setString(1, cate.getTenLoai());
        st.setString(2, cate.getMoTa());
        st.setInt(3, cate.getMaloai());
        st.executeUpdate();
    }

    public boolean checkID(int id) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("SELECT MaLoai FROM loaisp");
        ResultSet resultSet = statement.executeQuery();
        ArrayList<Integer> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(resultSet.getInt("MaLoai"));
        }
        for (int check: list){
            if(id == check){
                return false;
            }
        }
        return true;
    }

    public void Delete(int id) throws SQLException{
        String sql = "DELETE FROM loaisp WHERE MaLoai = ?";
        PreparedStatement statement = getConnection().prepareStatement(sql);
        statement.setInt(1 , id);
        statement.executeUpdate();
    }

    /// product
    public void insertProduct(String mota , String masp , String tensp ,int gia , String tacgia , String theloai){
        //String strNum = Integer.toString(maloai);
        try {
            String sql = "INSERT INTO sanpham (Mota, MaSP, TenSP , Gia , TacGia , TheLoai) VALUES (?, ?, ?, ? , ? , ? )";
            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.setString(1, mota);
            statement.setString(2, masp);
            statement.setString(3, tensp);
            statement.setInt(4, gia);
            statement.setString(5, tacgia);
            statement.setString(6, theloai);

            statement.executeUpdate();
        } catch (SQLException e) {

        }

    }

    public ArrayList<String> dropdownMaloai() throws SQLException {
        PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT TenLoai FROM loaisp");
        ResultSet results = preparedStatement.executeQuery();
        ArrayList<String> list = new ArrayList<>();
        //ObservableList<String> catenameList = FXCollections.observableArrayList();
        while (results.next()){
            String tenloai = results.getString("TenLoai");
            list.add(tenloai);
        }
        return list;
    }


    public ArrayList<String> dropdownTenloai() throws SQLException {
        PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT TenLoai FROM loaisp");
        ResultSet results = preparedStatement.executeQuery();
        ArrayList<String> list = new ArrayList<>();
        //ObservableList<String> catenameList = FXCollections.observableArrayList();
        while (results.next()){
            String tenloai = results.getString("TenLoai");
            list.add(tenloai);
        }
        return list;
    }

    public int convertTenloai(String tenloai) throws SQLException {
        PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT MaLoai FROM loaisp where TenLoai = ?");
        preparedStatement.setString(1 , tenloai);
        ResultSet resultSet = preparedStatement.executeQuery();
        int id;
        if(resultSet.next()){
            return id = resultSet.getInt("MaLoai");
        }
        return 0;
    }
    public boolean checkIdPro(String masp) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("SELECT MaSP FROM sanpham");
        ResultSet rs = statement.executeQuery();
        ArrayList<String> list = new ArrayList<>();
        while (rs.next()) {
            list.add(rs.getString("MaSP").toString());
        }

        for (String check: list){
            if(masp.equals(check)){
                return false;
            }
        }
        return true;
    }
    public ResultSet getPro() throws SQLException {
        statement = getConnection().createStatement();
        return statement.executeQuery("SELECT * FROM sanpham");
    }

    public void updatePro(String mota , String masp  , int gia , String tacgia , String theloai) throws SQLException {
        PreparedStatement st = getConnection().prepareStatement("UPDATE sanpham SET Mota = ?, Gia = ?, TacGia = ?, TheLoai = ? WHERE MaSP = ?");
        st.setString(1, mota);
        st.setInt(2, gia);
        st.setString(3 , tacgia);
        st.setString(4 , theloai);
        st.setString(5 , masp);

        st.executeUpdate();
    }

    public void DeletePro(String id) throws SQLException{
        String sql = "DELETE FROM sanpham WHERE MaSP = ?";
        PreparedStatement statement = getConnection().prepareStatement(sql);
        statement.setString(1 , id);
        statement.executeUpdate();
    }

    // bang thanh toan

    public void insertHD(int id , LocalDateTime time) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("INSERT into hoadon(MaHD , NgayIn) values (? , ?)");
        statement.setInt(1 , id);
        statement.setObject(2 , time);
        statement.executeUpdate();
    }

    public ResultSet getHD() throws SQLException {
        statement = getConnection().createStatement();
        return statement.executeQuery("SELECT * FROM hoadon");
    }
    public ResultSet getHDde() throws SQLException {
        statement = getConnection().createStatement();
        return statement.executeQuery("SELECT * FROM chitiethoadon");
    }

    public ArrayList<String> dropdownTensach() throws SQLException {
        PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT TenSP FROM sanpham");// Tensp chỉnh thàn id sản phẩm trong bảng
        ResultSet results = preparedStatement.executeQuery();
        ArrayList<String> list = new ArrayList<>();// string -> int
        //ObservableList<String> catenameList = FXCollections.observableArrayList();
        while (results.next()){
            String tensach = results.getString("TenSP"); // string thành int tensp thành idsanpham
            list.add(tensach);
        }
        return list;
    }
    public int getGia(String ten) throws SQLException {
        PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT Gia FROM sanpham where TenSP = ?");
        ResultSet resultSet;
        preparedStatement.setString(1,ten);
        resultSet = preparedStatement.executeQuery();
        int gia =0;
        if(resultSet.next()){
            gia = resultSet.getInt("Gia");
        }
        return gia;
    }

    public int getidHD() throws SQLException {
        int id = 0;
        PreparedStatement statement2 = getConnection().prepareStatement("SELECT MaHD FROM hoadon ORDER BY MaHD DESC LIMIT 1");
        ResultSet resultSet = statement2.executeQuery();
        if (resultSet.next()){
            id = resultSet.getInt("MaHD") + 1;
        }
        return id;
    }
    public int getMaHD() throws SQLException {
        int id = 0;
        PreparedStatement statement2 = getConnection().prepareStatement("SELECT MaHD FROM hoadon ORDER BY MaHD DESC LIMIT 1");
        ResultSet resultSet = statement2.executeQuery();
        if (resultSet.next()){
            id = resultSet.getInt("MaHD");
        }
        return id;
    }

    public int getidchitiet() throws SQLException {
        int id = 0;
        PreparedStatement statement2 = getConnection().prepareStatement("SELECT Id FROM chitiethoadon ORDER BY Id DESC LIMIT 1");
        ResultSet resultSet = statement2.executeQuery();
        if (resultSet.next()){
            id = resultSet.getInt("Id") + 1;
        }
        return id;
    }
    public void addThanhToanl(int id , int mahd ,String tensp ,int soluong ,int gia , int tongtien) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("INSERT into chitiethoadon(Id , MaHd , TenSp , SoLuong , Gia , TongTien) values (? , ?, ?, ?, ?,?)");
        statement.setInt(1 , id);
        statement.setInt(2 , mahd);
        statement.setString(3 , tensp);
        statement.setInt(4 , soluong);
        statement.setInt(5 , gia);
        statement.setInt(6 , tongtien);
        statement.executeUpdate();
        // cái hàm này chỗ nào tên sp chỉnh thành int hết
    }
    public int TongTien() throws SQLException {
        int tong = 0;
        int id = getMaHD();
        PreparedStatement statement = getConnection().prepareStatement("select sum(TongTien) from chitiethoadon where MaHd = ?");
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        if(rs.next()){
            tong = rs.getInt("sum(TongTien)");
        }
        return tong;
    }
    public void updateTong(int tong , int id) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("update hoadon set TongTien  = ? where MaHD = ?");
        statement.setInt(1 , tong);
        statement.setInt(2 , id);
        statement.executeUpdate();
    }

    // Nhân viên

    public int getUserID() throws SQLException {
        String sql = "select IDUser from users order by IDUser desc limit 1;";
        PreparedStatement statement = getConnection().prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        int maloai = 0;
        if(resultSet.next()){
            maloai = resultSet.getInt("IDUser");
        }
        return maloai + 1;
    }

    public void insertUser(int id , String ten , String email ,String sdt, int tuoi , String role , String pass){
        //String strNum = Integer.toString(maloai);
        try {
            String sql = "INSERT INTO users (IDUser, Ten, Email, SDT , Tuoi , Role , Password) VALUES (?, ?, ?, ? , ? , ? , ? )";
            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.setInt(1, id);
            statement.setString(2, ten);
            statement.setString(3, email);
            statement.setString(4, sdt);
            statement.setInt(5, tuoi);
            statement.setString(6, role);
            statement.setString(7, pass);
            statement.executeUpdate();
        } catch (SQLException e) {

        }

    }

    public ResultSet getUser() throws SQLException {
        statement = getConnection().createStatement();
        return statement.executeQuery("SELECT * FROM users");
    }

    public void updateUser(NhanVienData cate) throws SQLException{
        PreparedStatement st = getConnection().prepareStatement("UPDATE users SET Ten = ?, Email = ?, SDT = ? , Tuoi = ?, Role = ? , Password = ? WHERE IDUser = ?");
        st.setString(1, cate.getTen());
        st.setString(2, cate.getEmail());
        st.setString(3, cate.getSdt());
        st.setInt(4, cate.getTuoi());
        st.setString(5, cate.getRole());
        st.setString(6, cate.getPass());
        st.setInt(7, cate.getID());

        st.executeUpdate();
    }

    public void DeleteUser(int id) throws SQLException{
        String sql = "DELETE FROM users WHERE IDUser = ?";
        PreparedStatement statement = getConnection().prepareStatement(sql);
        statement.setInt(1 , id);
        statement.executeUpdate();
    }

    // TÁC GIẢ
    public void insertAuthor(String ten){
        //String strNum = Integer.toString(maloai);
        try {
            String sql = "INSERT INTO tacgia (ten) VALUES (?)";
            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.setString(1, ten);
            statement.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public void updateAuthor(TacGiaData cate) throws SQLException{
        PreparedStatement st = getConnection().prepareStatement("UPDATE tacgia SET ten = ? WHERE id = ?");
        st.setString(1, cate.getTen());
        st.setInt(2, cate.getId());
        st.executeUpdate();
    }
    public void Deletetacgia(int id) throws SQLException{
        String sql = "DELETE FROM tacgia WHERE id = ?";
        PreparedStatement statement = getConnection().prepareStatement(sql);
        statement.setInt(1 , id);
        statement.executeUpdate();
    }
    public int getTacgiaID() throws SQLException {
        String sql = "select id from tacgia order by id desc limit 1;";
        PreparedStatement statement = getConnection().prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        int maloai = 0;
        if(resultSet.next()){
            maloai = resultSet.getInt("id");
        }
        return maloai;
    }

    public ResultSet getAuthor() throws SQLException {
        statement = getConnection().createStatement();
        return statement.executeQuery("SELECT * FROM tacgia");
    }

    public ArrayList<String> dropdownAuthorname() throws SQLException {
        PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT ten FROM tacgia");
        ResultSet results = preparedStatement.executeQuery();
        ArrayList<String> list = new ArrayList<>();
        //ObservableList<String> catenameList = FXCollections.observableArrayList();
        while (results.next()){
            String tenloai = results.getString("ten");
            list.add(tenloai);
        }
        return list;
    }

    public int getMoney() throws SQLException {
        PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT Sum(TongTien) FROM hoadon where DATE(NgayIn) = CURDATE()");
        ResultSet rs = preparedStatement.executeQuery();
        int tongtien = 0;
        if (rs.next()){
            tongtien = rs.getInt("Sum(TongTien)");
        }
        return tongtien;
    }

    public int booksold() throws SQLException {
        PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT Sum(SoLuong) FROM chitiethoadon");
        ResultSet rs = preparedStatement.executeQuery();
        int tongtien = 0;
        if (rs.next()){
            tongtien = rs.getInt("Sum(SoLuong)");
        }
        return tongtien;
    }

    public int tongdoanhthu() throws SQLException {
        PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT Sum(TongTien) FROM chitiethoadon");
        ResultSet rs = preparedStatement.executeQuery();
        int tongtien = 0;
        if (rs.next()){
            tongtien = rs.getInt("Sum(TongTien)");
        }
        return tongtien;
    }

    public ResultSet Dsngay() throws SQLException {
        statement = getConnection().createStatement();
        return statement.executeQuery("SELECT DATE(NgayIn) AS ngay, SUM(TongTien) FROM hoadon GROUP BY ngay;");
        /*ArrayList<String> ngay = new ArrayList<>();
        if(rs.next()){
            ngay.add(rs.getString("ngay"));
        }*/
        //return  ngay;
    }

    public ArrayList<Integer> Dstien() throws SQLException {
        PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT TongTien FROM hoadon");
        ResultSet rs = preparedStatement.executeQuery();
        ArrayList<Integer> ngay = new ArrayList<>();
        if(rs.next()){
            ngay.add(rs.getInt("TongTien"));
        }
        return  ngay;
    }
}
