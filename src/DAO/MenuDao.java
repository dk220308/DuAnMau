/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Menu;
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
public class MenuDao {

    public Object[] getRow(Menu mn) {
        int maSP = mn.getMaSP();
        String tenSP = mn.getTenSP();
        float giaTien = mn.getGiaSP();

        Object[] row = new Object[]{maSP, tenSP, giaTien};
        return row;
    }

    public List<Menu> getAll() {
        List<Menu> listmn = new ArrayList<>();
        String sql = "SELECT * FROM Menu";
        try {
            Connection con = DBConnect.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                int maSP = rs.getInt(1);
                String tenSP = rs.getString(2);
                float giaTien = rs.getFloat(3);
                Menu mn = new Menu(maSP, tenSP, giaTien);
                listmn.add(mn);
            }
        } catch (Exception ex) {
        }

        return listmn;
    }
}
