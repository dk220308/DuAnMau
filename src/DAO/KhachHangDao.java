/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.KhachHang;
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
public class KhachHangDao {
    
    public int themKH(KhachHang kh) {
        String sql = "INSERT INTO KhachHang (MaKH, TenKH, SDT, DiaChi, EMAIL) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, kh.getMaKH());
            pstm.setString(2, kh.getTenKH());
            pstm.setString(3,kh.getSoDienThoai());
            pstm.setString(4, kh.getDiaChi());
            pstm.setString(5, kh.getEmail());
            if (pstm.executeUpdate() > 0) {
                return 1;
            }
        } catch (Exception ex) {
        }
        return 0;
    }
    
    public int suaKH(KhachHang kh, int ma) {
        String sql = "update KhachHang set\n"
                + "MaKH = ?,\n"
                + "TenKH = ?,\n"
                + "SDT = ?,\n"
                + "DiaChi= ?\n"
                + "EMAIL = ?";

        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, kh.getMaKH());
            pstm.setString(2, kh.getTenKH());
            pstm.setString(3,kh.getSoDienThoai());
            pstm.setString(4, kh.getDiaChi());
            pstm.setString(5, kh.getEmail());
            pstm.setInt(5, ma);
            if (pstm.executeUpdate() > 0) {
                return 1;
            }
        } catch (Exception ex) {
        }
        return 0;
    }
    
    public int xoaKH(int ma) {
        String sql = "delete KhachHang where MaKH = ?";

        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, ma);
            if (pstm.executeUpdate() > 0) {
                return 1;
            }
        } catch (Exception ex) {
        }
        return 0;
    }

    public Object[] getRow(KhachHang kh) {
        int maKH = kh.getMaKH();
        String tenKH = kh.getTenKH();
        String sdt = kh.getSoDienThoai();
        String dchi = kh.getDiaChi();
        String email = kh.getEmail();

        Object[] row = new Object[]{maKH, tenKH, sdt, dchi, email};
        return row;
    }

    public List<KhachHang> getAll() {
        List<KhachHang> listkh = new ArrayList<>();
        String sql = "SELECT * FROM KhachHang";
        try {
            Connection con = DBConnect.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                int maKH = rs.getInt(1);
                String tenKH = rs.getString(2);
                String sdt = rs.getString(3);
                String dchi = rs.getString(4);
                String email = rs.getString(5);
                KhachHang kh = new KhachHang(maKH, tenKH, sdt, dchi, email);
                listkh.add(kh);
            }
        } catch (Exception ex) {
        }

        return listkh;
    }
}
