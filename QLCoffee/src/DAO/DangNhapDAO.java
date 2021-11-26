/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Helper.jdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.TaiKhoanModel;

/**
 *
 * @author ADMIN
 */
public class DangNhapDAO {
    //đọc nhân viên mới từ bản ghi
    public static TaiKhoanModel readFromResultSet(ResultSet rs) throws SQLException {
        TaiKhoanModel mode = new TaiKhoanModel();
        mode.setTenTaiKhoan(rs.getString(1));
        mode.setMatKhau(rs.getString(2));
        mode.setHoTen(rs.getString(3));
        mode.setEmail(rs.getString(4));
        mode.setVaiTro(rs.getBoolean(5));
        return mode;

    }

    // lấy list danh sách tài khoản
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
                rs.getStatement().getConnection().close(); //đóng kết nối từ resultset
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(list);
        return list;
    }

    public TaiKhoanModel findByTenTaiKhoan(String tenTaiKhoan) {
        String sql = "select * from TaiKhoan\n"
                + "where TenTaiKhoan = ? and trangThai = 1";
        List<TaiKhoanModel> list = select(sql, tenTaiKhoan);
        return list.size() > 0 ? list.get(0) : null;
    }

}
