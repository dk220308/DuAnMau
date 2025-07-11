/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.ChiTIetSP;
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
public class ChiTietSPDAO {

    public int themCTHD(ChiTIetSP ctsp) {
        String sql = "INSERT INTO ChiTietSanPham (MaCTSP, MaSP, TenSP, Size, MauSac, Gia, SoLuongTon) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, ctsp.getMaCTSP());
            pstm.setInt(2, ctsp.getMaSP());
            pstm.setString(3, ctsp.getTenSP());
            pstm.setString(4, ctsp.getKichThuoc());
            pstm.setString(5, ctsp.getMauSac());
            pstm.setDouble(6, ctsp.getGia());
            pstm.setInt(7, ctsp.getSoLuong());

            if (pstm.executeUpdate() > 0) {
                return 1;
            }
        } catch (Exception ex) {
        }
        return 0;
    }

    public int suaCTHD(ChiTIetSP ctsp, int maCTSP) {
        String sql = "update ChiTietSanPham set\n"
                + "MaCTSP = ?,\n"
                + "MaSP = ?,\n"
                + "TenSP = ?,\n"
                + "Size = ?,\n"
                + "MauSac= ?,\n"
                + "Gia= ?,\n"
                + "SoLuongTon= ?";
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, ctsp.getMaCTSP());
            pstm.setInt(2, ctsp.getMaSP());
            pstm.setString(3, ctsp.getTenSP());
            pstm.setString(4, ctsp.getKichThuoc());
            pstm.setString(5, ctsp.getMauSac());
            pstm.setDouble(6, ctsp.getGia());
            pstm.setInt(7, ctsp.getSoLuong());
            pstm.setInt(8, maCTSP);
            if (pstm.executeUpdate() > 0) {
                return 1;
            }
        } catch (Exception ex) {
        }
        return 0;
    }

    public int xoaCTHD(int maCTSP) {
        String sql = "delete ChiTietSanPham where MaCTSP = ?";

        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, maCTSP);
            if (pstm.executeUpdate() > 0) {
                return 1;
            }
        } catch (Exception ex) {
        }
        return 0;
    }

    public Object[] getRow(ChiTIetSP ctsp) {
        int maCTSP = ctsp.getMaCTSP();
        int maSP = ctsp.getMaSP();
        String ten = ctsp.getTenSP();
        String kichThuoc = ctsp.getKichThuoc();
        String mauSac = ctsp.getMauSac();
        double gia = ctsp.getGia();
        int soLuong = ctsp.getSoLuong();

        Object[] row = new Object[]{maCTSP, maSP, ten, kichThuoc, mauSac, gia, soLuong};
        return row;
    }

    public List<ChiTIetSP> getAll() {
        List<ChiTIetSP> listctsp = new ArrayList<>();
        String sql = "SELECT * FROM ChiTietSanPham";
        try {
            Connection con = DBConnect.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                int maCTSP = rs.getInt(1);
                int maSP = rs.getInt(2);
                String ten = rs.getString(3);
                String kichThuoc = rs.getString(4);
                String mauSac = rs.getString(5);
                double gia = rs.getDouble(6);
                int soLuong = rs.getInt(7);
                ChiTIetSP ctsp = new ChiTIetSP(maCTSP, maSP, ten, kichThuoc, mauSac, gia, soLuong);
                listctsp.add(ctsp);
            }
        } catch (Exception ex) {
        }

        return listctsp;
    }
}
