/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.ChiTIetSP;
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

    public int suaCTHD(ChiTIetSP ctsp, int maCTSP) {
        String sql = "update ChiTietHoaDon set\n"
                + "MaCTSP = ?,\n"
                + "MaSP = ?,\n"
                + "Size = ?,\n"
                + "MauSac= ?,\n"
                + "Gia= ?,\n"
                + "SoLuongTon= ?";
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, ctsp.getMaCTSP());
            pstm.setInt(2, ctsp.getMaSP());
            pstm.setString(3, ctsp.getKichThuoc());
            pstm.setString(4, ctsp.getMauSac());
            pstm.setDouble(5, ctsp.getGia());
            pstm.setInt(6, ctsp.getSoLuong());
            pstm.setInt(7, maCTSP);
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
        String kichThuoc = ctsp.getKichThuoc();
        String mauSac = ctsp.getMauSac();
        double gia = ctsp.getGia();
        int soLuong = ctsp.getSoLuong();

        Object[] row = new Object[]{maCTSP, maSP, kichThuoc, mauSac, gia, soLuong};
        return row;
    }

    public List<ChiTIetSP> getAll() {
        List<ChiTIetSP> listsp = new ArrayList<>();
        String sql = "SELECT * FROM ChiTietSanPham";
        try {
            Connection con = DBConnect.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                int maCTSP = rs.getInt(1);
                int maSP = rs.getInt(2);
                String kichThuoc = rs.getString(3);
                String mauSac = rs.getString(4);
                double gia = rs.getDouble(5);
                int soLuong = rs.getInt(6);
                ChiTIetSP ctsp = new ChiTIetSP(maCTSP, maSP, kichThuoc, mauSac, gia, soLuong);
                listsp.add(ctsp);
            }
        } catch (Exception ex) {
        }

        return listsp;
    }
}
