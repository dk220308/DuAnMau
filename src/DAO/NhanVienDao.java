/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.ChiTietHD;
import Model.NhanVien;
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
public class NhanVienDao {

    public int themCTHD(NhanVien nv) {
        String sql = "INSERT INTO NhanVien (MaNV, TenTK, HoTen, ChucVu, SDT, Email, DiaChi) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, nv.getMaNV());
            pstm.setString(2, nv.getTenTK());
            pstm.setString(3, nv.getTenNV());
            pstm.setString(4, nv.getChucVu());
            pstm.setString(5, nv.getSoDienThoai());
            pstm.setString(6, nv.getEmail());
            pstm.setString(7, nv.getDiaChi());

            if (pstm.executeUpdate() > 0) {
                return 1;
            }
        } catch (Exception ex) {
        }
        return 0;
    }

    public int suaNV(NhanVien nv, int maNV) {
        String sql = "update NhanVien set\n"
                + "MaNV = ?,\n"
                + "TenTK = ?,\n"
                + "HoTen = ?,\n"
                + "ChucVu= ?,\n"
                + "SDT= ?,\n"
                + "Email= ?,\n"
                + "DiaChi= ?"
                + "Where MaNV= ?";

        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, nv.getMaNV());
            pstm.setString(2, nv.getTenTK());
            pstm.setString(3, nv.getTenNV());
            pstm.setString(4, nv.getChucVu());
            pstm.setString(5, nv.getSoDienThoai());
            pstm.setString(6, nv.getEmail());
            pstm.setString(7, nv.getDiaChi());
            pstm.setInt(8, maNV);
            if (pstm.executeUpdate() > 0) {
                return 1;
            }
        } catch (Exception ex) {
        }
        return 0;
    }

    public int xoaNV(int maNV) {
        String sql = "delete NhanVien where MaNV = ?";

        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, maNV);
            if (pstm.executeUpdate() > 0) {
                return 1;
            }
        } catch (Exception ex) {
        }
        return 0;
    }

    public Object[] getRow(NhanVien nv) {
        int maNV = nv.getMaNV();
        String tenTK = nv.getTenTK();
        String tenNV = nv.getTenNV();
        String chucvu = nv.getChucVu();
        String sdt = nv.getSoDienThoai();
        String email = nv.getEmail();
        String dchi = nv.getDiaChi();

        Object[] row = new Object[]{maNV, tenTK, tenNV, chucvu, sdt, email, dchi};
        return row;
    }

    public List<NhanVien> getAll() {
        List<NhanVien> listnv = new ArrayList<>();
        String sql = "SELECT * FROM NhanVien";
        try {
            Connection con = DBConnect.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                int maNV = rs.getInt(1);
                String tenTK = rs.getString(2);
                String tenNV = rs.getString(3);
                String chucvu = rs.getString(4);
                String sdt = rs.getString(5);
                String email = rs.getString(6);
                String dchi = rs.getString(7);
                NhanVien nv = new NhanVien(maNV, tenTK, tenNV, chucvu, sdt, email, dchi);
                listnv.add(nv);
            }
        } catch (Exception ex) {
        }

        return listnv;
    }
}
