/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

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
            pstm.setString(2, nv.getTenNV());
            pstm.setString(3, nv.getSoDienThoai());
            pstm.setString(4, nv.getDiaChi());
            pstm.setString(5, nv.getEmail());
            pstm.setString(6, nv.getChucVu());
            pstm.setString(7, nv.getTenTK());
            
            if (pstm.executeUpdate() > 0) {
                return 1;
            }
        } catch (Exception ex) {
        }
        return 0;
    }

    public int suaCTHD(ChiTietHD cthd, int maCTHD) {
        String sql = "update ChiTietHoaDon set\n"
                + "MaCTHD = ?,\n"
                + "MaSP = ?,\n"
                + "MaHD = ?,\n"
                + "SoLuong= ?";

        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, cthd.getMaCTHD());
            pstm.setInt(2, cthd.getMaSP());
            pstm.setInt(3, cthd.getMaHD());
            pstm.setInt(4, cthd.getSoLuong());
            pstm.setInt(5, maCTHD);
            if (pstm.executeUpdate() > 0) {
                return 1;
            }
        } catch (Exception ex) {
        }
        return 0;
    }

    public int xoaCTHD(int maCTHD) {
        String sql = "delete ChiTietHoaDon where MaCTHD = ?";

        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, maCTHD);
            if (pstm.executeUpdate() > 0) {
                return 1;
            }
        } catch (Exception ex) {
        }
        return 0;
    }

    public Object[] getRow(NhanVien nv) {
        int maNV = nv.getMaNV();
        String tenNV = nv.getTenNV();
        String sdt = nv.getSoDienThoai();
        String dchi = nv.getDiaChi();
        String email = nv.getEmail();
        String chucvu = nv.getChucVu()
        String tenTK = nv.getTenTK();

        Object[] row = new Object[]{maNV, tenNV, sdt, dchi, email, chucvu, tenTK};
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
                NhanVien nv = new NhanVien(maNV, tenTK, tenNV, chucvu, dchi, email, dchi)
                listnv.add(nv);
            }
        } catch (Exception ex) {
        }

        return listnv;
    }
}
