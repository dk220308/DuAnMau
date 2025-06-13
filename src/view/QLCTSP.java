/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import DAO.ChiTietSPDAO;
import Model.ChiTIetSP;
import Model.SanPham;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author XPS
 */
public class QLCTSP extends javax.swing.JPanel {

    DefaultTableModel tableModel;
    ChiTietSPDAO ctdao = new ChiTietSPDAO();

    /**
     * Creates new form QLCTSP
     */
    public QLCTSP() {
        initComponents();
        initTable();
        fillTable();
    }

    public void initTable() {
        String[] cols = new String[]{"Mã CTSP", "Mã SP", "Tên SP", "Kích thước", "Màu sắc", "Giá", "Số lượng tồn"};
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(cols);
        jTable1.setModel(tableModel);
    }

    public void fillTable() {
        tableModel.setRowCount(0);
        for (ChiTIetSP ctsp : ctdao.getAll()) {
            tableModel.addRow(ctdao.getRow(ctsp));
        }
    }

    private boolean validateForm() {
        if (TF_Mactsp.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã chi tiết sản phẩm.");
            return false;
        }
        if (TF_Masp.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã sản phẩm.");
            return false;
        }
        if (TF_tensp.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên.");
            return false;
        }
        if (TF_Kthuoc.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập kích thước.");
            return false;
        }
        if (TF_Msac.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập màu sắc.");
            return false;
        }
        if (TF_Gia.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập giá.");
            return false;
        }
        if (TF_sluongton.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng tồn.");
            return false;
        }
        try {
            double gia = Double.parseDouble(TF_sluongton.getText().trim());
            if (gia <= 0) {
                JOptionPane.showMessageDialog(this, "Giá tiền phải lớn hơn 0.");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Giá tiền phải là số hợp lệ.");
            return false;
        }
        return true;
    }
    
    public void showDetail() {
        int i = jTable1.getSelectedRow();
        if (i >= 0) {
            ChiTIetSP ctsp = ctdao.getAll().get(i);
            TF_Mactsp.setText(String.valueOf(ctsp.getMaCTSP()));
            TF_Masp.setText(String.valueOf(ctsp.getMaSP()));
            TF_tensp.setText(ctsp.getTenSP());
            TF_Kthuoc.setText(ctsp.getKichThuoc());
            TF_Msac.setText(ctsp.getMauSac());
            TF_Gia.setText(String.valueOf(ctsp.getGia()));
            TF_sluongton.setText(String.valueOf(ctsp.getSoLuong()));
        }
    }

    public void sua() {
        int i = jTable1.getSelectedRow();
        if (i >= 0) {
            int chon = JOptionPane.showConfirmDialog(this, "Bạn có thực sự muốn sửa",
                    "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (chon == JOptionPane.YES_OPTION) {  // Đã sửa từ YES_NO_OPTION thành YES_OPTION
                ChiTIetSP ctspcu = ctdao.getAll().get(i);
                int macu = ctspcu.getMaCTSP();

                int mactsp = Integer.parseInt(TF_Mactsp.getText());
                int masp = Integer.parseInt(TF_Masp.getText());
                String ten = (TF_tensp.getText());
                String kt = (TF_Kthuoc.getText());
                String ms = (TF_Msac.getText());
                double dgia = Double.parseDouble(TF_Gia.getText());
                int slt = Integer.parseInt(TF_sluongton.getText());

                ChiTIetSP ctsp = new ChiTIetSP(mactsp, masp, ten, kt, ms, dgia, slt);
                int result = ctdao.suaCTHD(ctsp, macu);
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
        int mactsp = Integer.parseInt(TF_Mactsp.getText());
        int masp = Integer.parseInt(TF_Masp.getText());
        String ten = (TF_tensp.getText());
        String kt = (TF_Kthuoc.getText());
        String ms = (TF_Msac.getText());
        double dgia = Double.parseDouble(TF_Gia.getText());
        int slt = Integer.parseInt(TF_sluongton.getText());

        ChiTIetSP ctsp = new ChiTIetSP(mactsp, masp, ten, kt, ms, dgia, slt);

        int Result = ctdao.themCTHD(ctsp);
        if (Result == 1) {
            fillTable();
            JOptionPane.showMessageDialog(this, "Thêm thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Thêm thất bại");
        }
    }

    public void xoa() {
        int i = jTable1.getSelectedRow();
        if (i >= 0) {
            int chon = JOptionPane.showConfirmDialog(this, "Bạn có thực sự muốn xoá",
                    "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (chon == JOptionPane.YES_NO_OPTION) {
                ChiTIetSP ctsp = ctdao.getAll().get(i);
                int result = ctdao.xoaCTHD(ctsp.getMaCTSP());
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

        TF_Msac = new javax.swing.JTextField();
        TF_Gia = new javax.swing.JTextField();
        Button_Sua = new javax.swing.JButton();
        Button_Them = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        Button_Xoa = new javax.swing.JButton();
        TF_Masp = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        TF_sluongton = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        TF_Mactsp = new javax.swing.JTextField();
        TF_Kthuoc = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        TF_tensp = new javax.swing.JTextField();

        TF_Msac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TF_MsacActionPerformed(evt);
            }
        });

        Button_Sua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Button_Sua.setText("Sửa");
        Button_Sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_SuaActionPerformed(evt);
            }
        });

        Button_Them.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Button_Them.setText("Thêm");
        Button_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_ThemActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 255));
        jLabel2.setText("Quản lý chi tiết sản phẩm");

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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        Button_Xoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Button_Xoa.setText("Xóa");
        Button_Xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_XoaActionPerformed(evt);
            }
        });

        TF_Masp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TF_MaspActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Mã chi tiết sản phẩm : ");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Màu sắc :");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Mã sản phẩm :");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Số lượng tồn :");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Kích thước :");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Giá :");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Tên sản phẩm :");

        TF_tensp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TF_tenspActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(TF_Kthuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TF_sluongton, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TF_Msac, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TF_Gia, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TF_Mactsp, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TF_Masp, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TF_tensp, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(237, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 57, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Button_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Button_Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Button_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(74, 74, 74))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(219, 219, 219))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TF_Mactsp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(Button_Them))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(Button_Sua)
                        .addGap(35, 35, 35)
                        .addComponent(Button_Xoa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TF_Masp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(TF_tensp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TF_Kthuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TF_Msac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TF_Gia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TF_sluongton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(39, 39, 39)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void Button_SuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_SuaActionPerformed
        // TODO add your handling code here:
        if (validateForm()) {
            sua();
        }
    }//GEN-LAST:event_Button_SuaActionPerformed

    private void Button_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_ThemActionPerformed
        // TODO add your handling code here:
        if (validateForm()) {
            them();
        }
    }//GEN-LAST:event_Button_ThemActionPerformed

    private void Button_XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_XoaActionPerformed
        // TODO add your handling code here:
        if (validateForm()) {
            xoa();
        }
    }//GEN-LAST:event_Button_XoaActionPerformed

    private void TF_MaspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TF_MaspActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TF_MaspActionPerformed

    private void TF_MsacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TF_MsacActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TF_MsacActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        showDetail();
    }//GEN-LAST:event_jTable1MouseClicked

    private void TF_tenspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TF_tenspActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TF_tenspActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Button_Sua;
    private javax.swing.JButton Button_Them;
    private javax.swing.JButton Button_Xoa;
    private javax.swing.JTextField TF_Gia;
    private javax.swing.JTextField TF_Kthuoc;
    private javax.swing.JTextField TF_Mactsp;
    private javax.swing.JTextField TF_Masp;
    private javax.swing.JTextField TF_Msac;
    private javax.swing.JTextField TF_sluongton;
    private javax.swing.JTextField TF_tensp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
