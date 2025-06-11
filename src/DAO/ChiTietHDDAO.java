/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.ChiTietHD;
import Service.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import Model.HoaDon;

/**
 *
 * @author XPS
 */
public class ChiTietHDDAO {

    public int themCTHD(ChiTietHD cthd) {
        String sql = "INSERT INTO ChiTietHoaDon (MaCTHD, MaSP, MaHD, SoLuong, GiaBan) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, cthd.getMaCTHD());
            pstm.setInt(2, cthd.getMaSP());
            pstm.setInt(3, cthd.getMaHD());
            pstm.setInt(4, cthd.getSoLuong());
            pstm.setFloat(5, cthd.getGiaBan());
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
                + "SoLuong= ?,\n"
                + "GiaBan= ?";

        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, cthd.getMaCTHD());
            pstm.setInt(2, cthd.getMaSP());
            pstm.setInt(3, cthd.getMaHD());
            pstm.setInt(4, cthd.getSoLuong());
            pstm.setFloat(5, cthd.getGiaBan());
            pstm.setInt(6, maCTHD);
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

    public Object[] getRow(ChiTietHD cthd) {
        int maCTHD = cthd.getMaCTHD();
        int maSP = cthd.getMaSP();
        int maHD = cthd.getMaHD();
        int soLuong = cthd.getSoLuong();
        float giaBan = cthd.getGiaBan();

        Object[] row = new Object[]{maCTHD, maSP, maHD, soLuong, giaBan};
        return row;
    }

    public List<ChiTietHD> getAll() {
        List<ChiTietHD> listCTHD = new ArrayList<>();
        String sql = "SELECT * FROM ChiTietHoaDon";
        try {
            Connection con = DBConnect.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                int maCTHD = rs.getInt(1);
                int maSP = rs.getInt(2);
                int maHD = rs.getInt(3);
                int soLuong = rs.getInt(4);
                float giaBan = rs.getFloat(5);
                ChiTietHD cthd = new ChiTietHD(maCTHD, maSP, maHD, soLuong, giaBan);
                listCTHD.add(cthd);
            }
        } catch (Exception ex) {
        }

        return listCTHD;
    }
}
