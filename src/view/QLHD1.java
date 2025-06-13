/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import DAO.HoaDonDAO;
import Model.HoaDon;
import java.sql.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author XPS
 */
public class QLHD1 extends javax.swing.JPanel {

    DefaultTableModel tableModel;
    HoaDonDAO hdDao = new HoaDonDAO();

    /**
     * Creates new form QLHD
     */
    public QLHD1() {
        initComponents();
        initTable();
        fillTable();
    }

    public void initTable() {
        String[] cols = new String[]{"Mã HĐ", "Mã KH", "Mã NV", "Ngày lập", "Trạng thái", "Tổng tiền"};
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(cols);
        jTable5.setModel(tableModel);
    }

    public void fillTable() {
        tableModel.setRowCount(0);
        for (HoaDon hd : hdDao.getAll()) {
            tableModel.addRow(hdDao.getRow(hd));
        }
    }

    private boolean validateForm() {
        if (TF_MaHD.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã hóa đơn.");
            return false;
        }
        if (TF_MaKH.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã khách hàng.");
            return false;
        }
        if (TF_NgayLap.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã nhân viên.");
            return false;
        }
        if (TF_NgayLap.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày lập.");
            return false;
        }
        if (TF_TrangThai.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập trạng thái.");
            return false;
        }
        if (TF_Gia.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tổng tiền.");
            return false;
        }
        try {
            double gia = Double.parseDouble(TF_Gia.getText().trim());
            if (gia <= 0) {
                JOptionPane.showMessageDialog(this, "Giá tiền phải lớn hơn 0.");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Giá tiền phải là số hợp lệ.");
            return false;
        }
        if (TF_MaKH.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã khách hàng.");
            return false;
        }
        if (TF_MaNV.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã nhân viên.");
            return false;
        }
        if (TF_NgayLap.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày lập.");
            return false;
        }
        if (TF_TrangThai.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập trạng thái.");
            return false;
        }
        return true;
    }

    public void showDetail() {
        int i = jTable5.getSelectedRow();
        if (i >= 0) {
            HoaDon hd = hdDao.getAll().get(i);
            TF_MaHD.setText(String.valueOf(hd.getMaHD()));
            TF_MaKH.setText(String.valueOf(hd.getMaKH()));
            TF_MaNV.setText(String.valueOf(hd.getMaNV()));
            TF_NgayLap.setText(String.valueOf(hd.getNgayLap()));
            TF_Gia.setText(String.valueOf(hd.getTongTien()));
            TF_TrangThai.setText(hd.getTrangThai());

        }
    }
// Trong QLSPpanel.java

    public void sua() {
        int i = jTable5.getSelectedRow();
        if (i >= 0) {
            int chon = JOptionPane.showConfirmDialog(this, "Bạn có thực sự muốn sửa",
                    "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (chon == JOptionPane.YES_OPTION) {  // Đã sửa từ YES_NO_OPTION thành YES_OPTION
                HoaDon hdcu = hdDao.getAll().get(i);
                int macu = hdcu.getMaHD();

                int mahd = Integer.parseInt(TF_MaHD.getText());
                int makh = Integer.parseInt(TF_MaKH.getText());
                int manv = Integer.parseInt(TF_MaNV.getText());
                Date ngayLap = Date.valueOf(TF_NgayLap.getText());
                String tthai = TF_TrangThai.getText();
                float tongTien = Float.parseFloat(TF_Gia.getText());

                HoaDon hdmoi = new HoaDon(mahd, makh, manv, ngayLap, tthai, tongTien);
                int result = hdDao.suaHD(hdmoi, macu);
                if (result == 1) {
                    fillTable();
                    JOptionPane.showMessageDialog(this, "Sửa thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Sửa thất bại");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Chọn sản phẩm để sửa");
        }
    }

    public void them() {
        int mahd = Integer.parseInt(TF_MaHD.getText());
        int makh = Integer.parseInt(TF_MaKH.getText());
        int manv = Integer.parseInt(TF_MaNV.getText());
        Date ngayLap = Date.valueOf(TF_NgayLap.getText());
        String tthai = TF_TrangThai.getText();
        float tongTien = Float.parseFloat(TF_Gia.getText());

        HoaDon hd = new HoaDon(mahd, makh, manv, ngayLap, tthai, tongTien);

        int Result = hdDao.themHD(hd);
        if (Result == 1) {
            fillTable();
            JOptionPane.showMessageDialog(this, "Thêm thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Thêm thất bại");
        }
    }

    public void xoa() {
        int i = jTable5.getSelectedRow();
        if (i >= 0) {
            int chon = JOptionPane.showConfirmDialog(this, "Bạn có thực sự muốn xoá",
                    "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (chon == JOptionPane.YES_NO_OPTION) {
                HoaDon hdcu = hdDao.getAll().get(i);
                int result = hdDao.xoaHD(hdcu.getMaHD());
                if (result == 1) {
                    fillTable();
                    JOptionPane.showMessageDialog(this, "Xoá thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Xoá thất bại");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Chọn sản phẩm để xoá");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TF_Gia = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        TF_TrangThai = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        TF_MaNV = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        Button_Them = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        Button_Sua = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        Button_Xoa = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        TF_MaHD = new javax.swing.JTextField();
        TF_MaKH = new javax.swing.JTextField();
        TF_NgayLap = new javax.swing.JTextField();

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 255));
        jLabel2.setText("Quản lý Hóa Đơn");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Mã hóa đơn:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Mã khách hàng:");

        Button_Them.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Button_Them.setText("Thêm");
        Button_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_ThemActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Ngày lập:");

        Button_Sua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Button_Sua.setText("Sửa");
        Button_Sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_SuaActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Tổng tiền:");

        Button_Xoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Button_Xoa.setText("Xóa");
        Button_Xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_XoaActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Trạng thái:");

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable5MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable5);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Mã nhân viên:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(126, 126, 126))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(TF_TrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(35, 35, 35)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(TF_Gia, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(TF_NgayLap, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(TF_MaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(TF_MaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Button_Sua, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Button_Them, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Button_Xoa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(88, 88, 88))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jLabel1)
                        .addGap(29, 29, 29)
                        .addComponent(TF_MaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(166, 166, 166)
                        .addComponent(jLabel2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(TF_MaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TF_MaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TF_MaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(Button_Sua)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(Button_Them)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TF_NgayLap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(Button_Xoa)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TF_TrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TF_Gia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(101, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void Button_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_ThemActionPerformed
        // TODO add your handling code here:
        if (validateForm()) {
            them();
        }
    }//GEN-LAST:event_Button_ThemActionPerformed

    private void Button_SuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_SuaActionPerformed
        // TODO add your handling code here:
        if (validateForm()) {
            sua();
        }
    }//GEN-LAST:event_Button_SuaActionPerformed

    private void Button_XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_XoaActionPerformed
        // TODO add your handling code here:
        if (validateForm()) {
            xoa();
        }
    }//GEN-LAST:event_Button_XoaActionPerformed

    private void jTable5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable5MouseClicked
        // TODO add your handling code here:
        showDetail();
    }//GEN-LAST:event_jTable5MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Button_Sua;
    private javax.swing.JButton Button_Them;
    private javax.swing.JButton Button_Xoa;
    private javax.swing.JTextField TF_Gia;
    private javax.swing.JTextField TF_MaHD;
    private javax.swing.JTextField TF_MaKH;
    private javax.swing.JTextField TF_MaNV;
    private javax.swing.JTextField TF_NgayLap;
    private javax.swing.JTextField TF_TrangThai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable5;
    // End of variables declaration//GEN-END:variables
}
