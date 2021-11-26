/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Helper.jdbcHelper;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.TaiKhoanModel;
import Helper.UtilsHelper;
import java.sql.PreparedStatement;

/**
 *
 * @author ADMIN
 */
public class TaiKhoanDao {

    static Connection con = UtilsHelper.myConnection();

    public TaiKhoanModel readFromResultSet(ResultSet rs) throws SQLException {
        TaiKhoanModel model = new TaiKhoanModel();
        model.setTenTaiKhoan(rs.getString(1));
        model.setHoTen(rs.getString(2));
        model.setMatKhau(rs.getString(3));
        model.setEmail(rs.getString(4));
        model.setVaiTro(rs.getBoolean(5));
        return model;
    }

    public synchronized static List<TaiKhoanModel> HienThiHD() {
        List<TaiKhoanModel> list = new ArrayList<>();
        try {
            String sql = "select tentaikhoan,matkhau,hoten,email,vaitro,trangthai from taikhoan\n"
                    + "where trangthai='1' and an='1'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                TaiKhoanModel hd = new TaiKhoanModel();
                hd.setTenTaiKhoan(rs.getString(1));
                hd.setMatKhau(rs.getString(2));
                hd.setHoTen(rs.getString(3));
                hd.setEmail(rs.getString(4));
                hd.setVaiTro(rs.getBoolean(5));
                hd.setTrangThai(rs.getString(6));

                list.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public synchronized static List<TaiKhoanModel> HienThiKhongHD() {
        List<TaiKhoanModel> list = new ArrayList<>();
        try {
            String sql = "select tentaikhoan,matkhau,hoten,email,vaitro,trangthai from taikhoan\n"
                    + "where trangthai= 0 and an='1'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                TaiKhoanModel hd = new TaiKhoanModel();
                hd.setTenTaiKhoan(rs.getString(1));
                hd.setMatKhau(rs.getString(2));
                hd.setHoTen(rs.getString(3));
                hd.setEmail(rs.getString(4));
                hd.setVaiTro(rs.getBoolean(5));
                hd.setTrangThai(rs.getString(6));
                list.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    //thực hiện truy vấn lấy về 1 tập ResultSet rồi điền tập ResultSet đó vào 1 List
    public List<TaiKhoanModel> select(String sql, Object... args) {
        List<TaiKhoanModel> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = jdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    list.add(readFromResultSet(rs));
                }
            } finally {
                rs.getStatement().getConnection().close();      //đóng kết nối từ resultSet
            }
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
        return list;
    }

    public void doimk(TaiKhoanModel emiu) {
        String sql = "update TAIKHOAN set MATKHAU = ?\n"
                + "where EMAIL = ?";
        jdbcHelper.executeUpdate(sql,
                emiu.getMatKhau(),
                emiu.getEmail());

    }

    public TaiKhoanModel findByEmail(String email) {
        String sql = "select * from TAIKHOAN\n"
                + "where EMAIL = ?";
        List<TaiKhoanModel> list = select(sql, email);
        return list.size() > 0 ? list.get(0) : null;
    }

    public static void insert(TaiKhoanModel entity) {
        String sql = "INSERT INTO TAIKHOAN VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcHelper.executeUpdate(sql,
                entity.getTenTaiKhoan(),
                entity.getMatKhau(),
                entity.getHoTen(),
                entity.getEmail(),
                entity.isVaiTro(),
                entity.getTrangThai(),
                entity.isAn());
    }
    //check Trùng gmail trong bảng tài khoản.
    public  TaiKhoanModel checkTrungGmail(String Gmail) {
        String sql = "select * from taikhoan \n"
                + "where gmail = ? and trangthai=1";
        List<TaiKhoanModel> list = select(sql, Gmail);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    //Update tài khoản
    public static void update(TaiKhoanModel entity) {
        String sql = "UPDATE TAIKHOAN SET MatKhau=?, HoTen=?, VaiTro=? , EMAIL=?,an=?,trangthai=? WHERE TENTAIKHOAN=?";
        jdbcHelper.executeUpdate(sql,
                entity.getMatKhau(),
                entity.getHoTen(),
                entity.isVaiTro(),
                entity.getEmail(),
                entity.isAn(),
                entity.getTrangThai(),
                entity.getTenTaiKhoan());
    }
    
    //Check trùng khóa chính
    public  TaiKhoanModel checkTrungMa(String tenTaiKhoan) {
        String sql = "select * from taikhoan \n"
                + "where tentaikhoan = ? and trangthai=1";
        List<TaiKhoanModel> list = select(sql, tenTaiKhoan);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    //xóa tài khoản.Thực ra là cho an = 0 để không còn thao tác được nữa với tài khoản này
     public void delete(String tenTaiKhoan) {
        String sql = "UPDATE taikhoan\n"
                + "SET an = 0\n"
                + "where tentaikhoan = ?";
        jdbcHelper.executeUpdate(sql, tenTaiKhoan);
    }
     
    //tìm kiếm tài khoản 
    public synchronized static List<TaiKhoanModel> TimKiemMa(String ma) {
        List<TaiKhoanModel> list = new ArrayList<>();
        try {
            String sql = "select * from taikhoan\n"
                    + "where (tentaikhoan + hoten + email) like ?";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, "%" + ma + "%");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                TaiKhoanModel tk = new TaiKhoanModel();
                tk.setTenTaiKhoan(rs.getString(1));
                tk.setHoTen(rs.getString(2));
                tk.setMatKhau(rs.getString(3));
                tk.setEmail(rs.getString(4));
                tk.setVaiTro(rs.getBoolean(5));
                list.add(tk);
            }
        } catch (Exception e) {
        }
        return list;
    }
}
