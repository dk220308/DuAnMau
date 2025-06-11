/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.HoaDon;
import Service.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author XPS
 */
public class HoaDonDAO {

    public int themHD(HoaDon hd) {
        String sql = "INSERT INTO HoaDon (MaHD, MaKH, MaNV, NgayLap, TrangThai, TongTien) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, hd.getMaHD());
            pstm.setInt(2, hd.getMaKH());
            pstm.setInt(3, hd.getMaNV());
            pstm.setDate(4, hd.getNgayLap());
            pstm.setString(5, hd.getTrangThai());
            pstm.setDouble(6, hd.getTongTien());    
            if (pstm.executeUpdate() > 0) {
                return 1;
            }
        } catch (Exception ex) {
        }
        return 0;
    }

    public int suaHD(HoaDon hd, int maHD) {
        String sql = "update HoaDon set\n"
                + "MaHD = ?,\n"
                + "MaKH = ?,\n"
                + "MaNV = ?,\n"
                + "NgayLap= ?, \n"
                + "TrangThai= ?, \n"
                + "TongTien= ?"
                + "WHERE MaHD = ?";
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, hd.getMaHD());
            pstm.setInt(2, hd.getMaKH());
            pstm.setInt(3, hd.getMaNV());
            pstm.setDate(4, hd.getNgayLap());
            pstm.setString(5, hd.getTrangThai());
            pstm.setDouble(6, hd.getTongTien());          
            pstm.setInt(7, maHD);
            if (pstm.executeUpdate() > 0) {
                return 1;
            }
        } catch (Exception ex) {
        }
        return 0;
    }

    public int xoaHD(int maHD) {
        String sql = "delete HoaDon where MaHD = ?";

        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, maHD);
            if (pstm.executeUpdate() > 0) {
                return 1;
            }
        } catch (Exception ex) {
        }
        return 0;
    }

    public Object[] getRow(HoaDon hd) {
        int maHD = hd.getMaHD();
        int maKH = hd.getMaKH();
        int maNV = hd.getMaNV();
        Date ngayLap = hd.getNgayLap();
        String trangThai = hd.getTrangThai();
        double tongTien = hd.getTongTien();
        

        Object[] row = new Object[]{maHD, maKH, maNV, ngayLap, trangThai, tongTien};
        return row;
    }

    public List<HoaDon> getAll() {
        List<HoaDon> listHD = new ArrayList<>();
        String sql = "select * from HoaDon";
        try {
            Connection con = DBConnect.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                int maHD = rs.getInt(1);
                int maKH = rs.getInt(2);
                int maNV = rs.getInt(3);
                Date ngayLap = rs.getDate(4);
                String trangThai = rs.getString(5);
                double tongTien = rs.getDouble(6);              
                HoaDon hd = new HoaDon(maHD, maKH, maNV, ngayLap, trangThai, tongTien);
                listHD.add(hd);
            }
        } catch (Exception ex) {
        }

        return listHD;
    }
}
