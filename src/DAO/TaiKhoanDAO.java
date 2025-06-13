/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.ChiTietHD;
import Model.TaiKhoan;
import Service.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author XPS
 */
public class TaiKhoanDAO {

    List<TaiKhoan> listtk = new ArrayList<>();

    
    public int themTK(TaiKhoan tk) {
        String sql = "INSERT INTO TaiKhoan (TenTK, MatKhau, MaNV, VaiTro) VALUES (?, ?, ?, ?)";

        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, tk.getTenTK());
            pstm.setString(2, tk.getMatKhau());
            pstm.setInt(3, tk.getMaNV());
            pstm.setString(4, tk.getVaitro());
            if (pstm.executeUpdate() > 0) {
                return 1;
            }
        } catch (Exception ex) {
        }
        return 0;
    }

    public int xoaTK(int matk) {
        String sql = "delete TaiKhoan where TenTK = ?";

        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, matk);
            if (pstm.executeUpdate() > 0) {
                return 1;
            }
        } catch (Exception ex) {
        }
        return 0;
    }

    public Object[] getRow(TaiKhoan tk) {
        String tenTK = tk.getTenTK();
        String matKhau = tk.getMatKhau();
        int maTK = tk.getMaNV();
        String vaitro = tk.getVaitro();

        Object[] row = new Object[]{tenTK, matKhau, maTK};
        return row;
    }

    public List<TaiKhoan> getAll() {
        List<TaiKhoan> listtk = new ArrayList<>();
        String sql = "SELECT * FROM TaiKhoan";
        try {
            Connection con = DBConnect.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                String tenTK = rs.getString(1);
                String matKhau = rs.getString(2);
                int maTK = rs.getInt(3);
                String vaitro = rs.getString(4);
                TaiKhoan tk = new TaiKhoan(tenTK, matKhau, maTK, vaitro);
                listtk.add(tk);
            }
        } catch (Exception ex) {
        }

        return listtk;
    }

    public int checkUser(String tenTK, String matKhau) {
    String sql = "SELECT VaiTro FROM TaiKhoan WHERE TenTK = ? AND MatKhau = ?";
    
    try (Connection con = DBConnect.getConnection();
         PreparedStatement pstm = con.prepareStatement(sql)) {
        
        pstm.setString(1, tenTK);
        pstm.setString(2, matKhau);
        
        try (ResultSet rs = pstm.executeQuery()) {
            if (rs.next()) {
                String vaitro = rs.getString("VaiTro");
                if ("quanly".equalsIgnoreCase(vaitro)) {
                    return 1;
                } else if ("nhanvien".equalsIgnoreCase(vaitro)) {
                    return -1;
                }
            }
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return 0;
}
}
