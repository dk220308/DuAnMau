/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.GioHang;
import Service.DBConnect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author XPS
 */
public class GioHangDao {

    public Object[] getRow(GioHang gh) {
        int mactsp = gh.getMaCTSP();
        int mahd = gh.getMaHD();
        int maSP = gh.getMaSP();
        String tenSP = gh.getTenSP();
        String size = gh.getSize();
        String msac = gh.getMsac();
        int soLuong = gh.getSluong();
        double dgia = gh.getDonGia();
        double ttien = gh.getTtien();

        Object[] row = new Object[]{mactsp, mahd, maSP, tenSP, size, msac, soLuong, dgia, ttien};
        return row;
    }

    public List<GioHang> getAll() {
        List<GioHang> listgh = new ArrayList<>();
        String sql = "SELECT * FROM GioHang";
        try {
            Connection con = DBConnect.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                int mactsp = rs.getInt(1);
                int mahd = rs.getInt(2);
                int maSP = rs.getInt(3);
                String tenSP = rs.getString(4);
                String size = rs.getString(5);
                String msac = rs.getString(6);
                int soLuong = rs.getInt(7);
                double dgia = rs.getDouble(8);
                double ttien = rs.getDouble(9);
                GioHang gh = new GioHang(mactsp, mahd, maSP, tenSP, size, msac, soLuong, dgia, ttien);
                listgh.add(gh);
            }
        } catch (Exception ex) {
        }

        return listgh;
    }
}
