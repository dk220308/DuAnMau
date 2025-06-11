/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import DAO.NhanVienDao;
import Model.HoaDon;
import Model.NhanVien;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class QLNVpanel extends javax.swing.JPanel {

    DefaultTableModel tableModel;
    NhanVienDao nvDao = new NhanVienDao();

    /**
     * Creates new form QLNVpanel
     */
    public QLNVpanel() {
        initComponents();
        initTable();
        fillTable();
    }

    public void initTable() {
        String[] cols = new String[]{"Mã NV", "Tên tài khoản", "Tên NV", "Chức vụ", "Tuổi NV", "SĐT NV",};
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(cols);
        tbQLNV.setModel(tableModel);
    }

    public void fillTable() {
        tableModel.setRowCount(0);
        for (NhanVien nv : nvDao.getAll()) {
            tableModel.addRow(nvDao.getRow(nv));
        }
    }

    private boolean validateForm() {
        if (TF_Manv.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã nhân viên.");
            return false;
        }
        if (TF_Tennv.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên nhân viên.");
            return false;
        }
        if (TF_ChucVu.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập chức vụ nhân viên.");
            return false;
        }
        if (TF_SDTNV.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập SDT nhân viên.");
            return false;
        }
        if (TF_Email.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập email nhân viên.");
            return false;
        }
        if (TF_DiaChi.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập địa chỉ.");
            return false;
        }

        if (TF_SDTNV.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại.");
            return false;
        }

        String sdt = TF_SDTNV.getText().trim();
        if (!sdt.matches("0\\d{9}")) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ. Định dạng: 0xxxxxxxxx (10 số).");
            return false;
        }

        return true;
    }

    public void showDetail() {
        int i = tbQLNV.getSelectedRow();
        if (i >= 0) {
            NhanVien nv = nvDao.getAll().get(i);
            TF_Manv.setText(String.valueOf(nv.getMaNV()));
            TF_tenTK.setText(nv.getTenTK());
            TF_Tennv.setText(nv.getTenNV());
            TF_ChucVu.setText(nv.getChucVu());
            TF_SDTNV.setText(nv.getSoDienThoai());
            TF_Email.setText(nv.getEmail());
            TF_DiaChi.setText(nv.getDiaChi());
        }
    }

    public void sua() {
        int i = tbQLNV.getSelectedRow();
        if (i >= 0) {
            int chon = JOptionPane.showConfirmDialog(this, "Bạn có thực sự muốn sửa",
                    "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (chon == JOptionPane.YES_OPTION) {  // Đã sửa từ YES_NO_OPTION thành YES_OPTION
                NhanVien nvcu = nvDao.getAll().get(i);
                int macu = nvcu.getMaNV();

                int manv = Integer.parseInt(TF_Manv.getText());
                String tentk = TF_tenTK.getText();
                String tennv = TF_Tennv.getText();
                String chucvu = TF_ChucVu.getText();
                String sdt = TF_SDTNV.getText();
                String email = TF_Email.getText();
                String dchi = TF_DiaChi.getText();

                NhanVien nvmoi = new NhanVien(manv, tentk, tennv, chucvu, sdt, email, dchi);
                int result = nvDao.suaNV(nvmoi, macu);
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
        int manv = Integer.parseInt(TF_Manv.getText());
        String tentk = TF_tenTK.getText();
        String tennv = TF_Tennv.getText();
        String chucvu = TF_ChucVu.getText();
        String sdt = TF_SDTNV.getText();
        String email = TF_Email.getText();
        String dchi = TF_DiaChi.getText();

        NhanVien nv = new NhanVien(manv, tentk, tennv, chucvu, sdt, email, dchi);

        int Result = nvDao.themCTHD(nv);
        if (Result == 1) {
            fillTable();
            JOptionPane.showMessageDialog(this, "Thêm thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Thêm thất bại");
        }
    }

    public void xoa() {
        int i = tbQLNV.getSelectedRow();
        if (i >= 0) {
            int chon = JOptionPane.showConfirmDialog(this, "Bạn có thực sự muốn xoá",
                    "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (chon == JOptionPane.YES_NO_OPTION) {
                NhanVien nvcu = nvDao.getAll().get(i);
                int result = nvDao.xoaNV(nvcu.getMaNV());
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

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        lbQLNV = new javax.swing.JLabel();
        lbMNV = new javax.swing.JLabel();
        lbTNV = new javax.swing.JLabel();
        lbSĐTNV = new javax.swing.JLabel();
        lbTnvien = new javax.swing.JLabel();
        TF_Manv = new javax.swing.JTextField();
        TF_Tennv = new javax.swing.JTextField();
        TF_ChucVu = new javax.swing.JTextField();
        TF_SDTNV = new javax.swing.JTextField();
        Button_Them = new javax.swing.JButton();
        Button_Sua = new javax.swing.JButton();
        Button_Xoa = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbQLNV = new javax.swing.JTable();
        lbTNV1 = new javax.swing.JLabel();
        TF_Email = new javax.swing.JTextField();
        lbTNV2 = new javax.swing.JLabel();
        TF_DiaChi = new javax.swing.JTextField();
        TF_tenTK = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable1);

        lbQLNV.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbQLNV.setForeground(new java.awt.Color(51, 51, 255));
        lbQLNV.setText("Quản Lý Nhân Viên");

        lbMNV.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbMNV.setText("Mã Nhân Viên:");

        lbTNV.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbTNV.setText("Tên Nhân Viên:");

        lbSĐTNV.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbSĐTNV.setText("SĐT Nhân Viên:");

        lbTnvien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbTnvien.setText("Chức vụ:");

        TF_Manv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TF_ManvActionPerformed(evt);
            }
        });

        Button_Them.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Button_Them.setText("Thêm");
        Button_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_ThemActionPerformed(evt);
            }
        });

        Button_Sua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Button_Sua.setText("Sửa");
        Button_Sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_SuaActionPerformed(evt);
            }
        });

        Button_Xoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Button_Xoa.setText("Xoá");
        Button_Xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_XoaActionPerformed(evt);
            }
        });

        tbQLNV.setModel(new javax.swing.table.DefaultTableModel(
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
        tbQLNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbQLNVMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbQLNV);

        lbTNV1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbTNV1.setText("Email:");

        lbTNV2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbTNV2.setText("Địa Chỉ:");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Tên tài khoản :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lbTNV2)
                                            .addComponent(lbTNV1)
                                            .addComponent(lbSĐTNV))
                                        .addGap(34, 34, 34)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(TF_Email, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(TF_DiaChi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(TF_SDTNV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel1)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(TF_tenTK, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(lbTNV)
                                                .addComponent(lbMNV)
                                                .addComponent(lbTnvien))
                                            .addGap(34, 34, 34)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(TF_Manv, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(TF_ChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(TF_Tennv, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(72, 72, 72)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Button_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Button_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Button_Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 546, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(207, 207, 207)
                        .addComponent(lbQLNV)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lbQLNV)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbMNV)
                    .addComponent(TF_Manv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Button_Them))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(Button_Sua))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TF_tenTK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(Button_Xoa)
                        .addGap(40, 40, 40))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TF_Tennv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbTNV))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TF_ChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbTnvien))
                        .addGap(27, 27, 27)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TF_SDTNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbSĐTNV))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TF_Email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTNV1))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TF_DiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTNV2))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void Button_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_ThemActionPerformed
        // TODO add your handling code here:
        if( validateForm()){
            them();
        }
    }//GEN-LAST:event_Button_ThemActionPerformed

    private void Button_SuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_SuaActionPerformed
        // TODO add your handling code here:
        if( validateForm()){
            sua();
        }
    }//GEN-LAST:event_Button_SuaActionPerformed

    private void Button_XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_XoaActionPerformed
        // TODO add your handling code here:
        if( validateForm()){
            xoa();
        }
    }//GEN-LAST:event_Button_XoaActionPerformed

    private void TF_ManvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TF_ManvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TF_ManvActionPerformed

    private void tbQLNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbQLNVMouseClicked
        // TODO add your handling code here:
        showDetail();
    }//GEN-LAST:event_tbQLNVMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Button_Sua;
    private javax.swing.JButton Button_Them;
    private javax.swing.JButton Button_Xoa;
    private javax.swing.JTextField TF_ChucVu;
    private javax.swing.JTextField TF_DiaChi;
    private javax.swing.JTextField TF_Email;
    private javax.swing.JTextField TF_Manv;
    private javax.swing.JTextField TF_SDTNV;
    private javax.swing.JTextField TF_Tennv;
    private javax.swing.JTextField TF_tenTK;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbMNV;
    private javax.swing.JLabel lbQLNV;
    private javax.swing.JLabel lbSĐTNV;
    private javax.swing.JLabel lbTNV;
    private javax.swing.JLabel lbTNV1;
    private javax.swing.JLabel lbTNV2;
    private javax.swing.JLabel lbTnvien;
    private javax.swing.JTable tbQLNV;
    // End of variables declaration//GEN-END:variables
}
