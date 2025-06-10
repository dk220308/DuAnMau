/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.SanPham;
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
public class SanPhamDAO {

    public int themKH(SanPham sp) {
        String sql = "INSERT INTO SanPham (MaSP, TenSP, MoTa, TrangThai) VALUES (?, ?, ?, ?)";

        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, sp.getMaSP());
            pstm.setString(2, sp.getTenSP());
            pstm.setString(3, sp.getMoTa());
            pstm.setString(4, sp.getTrangThai());
            if (pstm.executeUpdate() > 0) {
                return 1;
            }
        } catch (Exception ex) {
        }
        return 0;
    }

    public int suaKH(SanPham sp, int maSP) {
        String sql = "update SanPham set\n"
                + "MaSP = ?,\n"
                + "TenSP = ?,\n"
                + "MoTa = ?,\n"
                + "TrangThai= ?";

        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, sp.getMaSP());
            pstm.setString(2, sp.getTenSP());
            pstm.setString(3, sp.getMoTa());
            pstm.setString(4, sp.getTrangThai());
            pstm.setInt(5, maSP);
            if (pstm.executeUpdate() > 0) {
                return 1;
            }
        } catch (Exception ex) {
        }
        return 0;
    }

    public int xoaKH(int maSP) {
        String sql = "delete SanPham where MaSP = ?";

        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, maSP);
            if (pstm.executeUpdate() > 0) {
                return 1;
            }
        } catch (Exception ex) {
        }
        return 0;
    }

    public Object[] getRow(SanPham sp) {
        int maSP = sp.getMaSP();
        String tenSP = sp.getTenSP();
        String moTa = sp.getMoTa();
        String trangThai = sp.getTrangThai();

        Object[] row = new Object[]{maSP, tenSP, moTa, trangThai};
        return row;
    }

    public List<SanPham> getAll() {
        List<SanPham> listsp = new ArrayList<>();
        String sql = "SELECT * FROM SanPham";
        try {
            Connection con = DBConnect.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                int maSP = rs.getInt(1);
                String tenSP = rs.getString(2);
                String moTa = rs.getString(3);
                String trangThai = rs.getString(4);
                SanPham sp = new SanPham(maSP, tenSP, moTa, trangThai);
                listsp.add(sp);
            }
        } catch (Exception ex) {
        }

        return listsp;
    }
}
