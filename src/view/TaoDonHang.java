/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import DAO.ChiTietHDDAO;
import DAO.ChiTietSPDAO;
import DAO.GioHangDao;
import DAO.HoaDonDAO;
import DAO.MenuDao;
import Model.ChiTIetSP;
import Model.ChiTietHD;
import Model.GioHang;
import Model.HoaDon;
import Service.DBConnect;
import java.awt.Font;
import java.util.Date;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author XPS
 */
public class TaoDonHang extends javax.swing.JPanel {

    private List<GioHang> gioHangList = new ArrayList<>();
    DefaultTableModel tableModel;
    HoaDonDAO hđao = new HoaDonDAO();
    ChiTietSPDAO ctdao = new ChiTietSPDAO();
    ChiTietHDDAO cthddao = new ChiTietHDDAO();
    GioHangDao ghdao = new GioHangDao();
    private TrangChuNV parentFrame;
    MenuDao mnDao = new MenuDao();

    /**
     * Creates new form TaoDonHang
     */
    public TaoDonHang() {
        initComponents();
        initTable();
        fillTable();
        getHoaDonHeader();
        jTextArea1.setFont(new Font("Monospaced", Font.PLAIN, 12));
        initTable1();
        fillTable1();
        initTable2();
        fillTable2();

        // Kiểm tra model của JTable2
        System.out.println("JTable2 model class: " + jTable2.getModel().getClass());
        System.out.println("JTable2 column count: " + jTable2.getColumnCount());
    }

    public TaoDonHang(TrangChuNV parent) {
        initComponents();
        this.parentFrame = parent;
        jButton1.addActionListener(e -> parentFrame.showmenu());
    }

    public void initTable() {
        String[] cols = new String[]{"Mã hoá đơn", "Mã khách hàng", "Mã nhân viên", "Ngày lập", "Trạng thái", "Tổng tiền"};
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(cols);
        jTable3.setModel(tableModel);
    }

    public void fillTable() {
        tableModel.setRowCount(0);
        for (HoaDon hd : hđao.getAll()) {
            tableModel.addRow(hđao.getRow(hd));
        }
    }

    public void initTable1() {
        String[] cols = new String[]{"Mã CTSP", "Mã SP", "Tên SP", "Kích thước", "Màu sắc", "Giá", "Số lượng tồn"};
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(cols);
        jTable1.setModel(tableModel);
    }

    public void fillTable1() {
        tableModel.setRowCount(0);
        for (ChiTIetSP ctsp : ctdao.getAll()) {
            tableModel.addRow(ctdao.getRow(ctsp));
        }
    }

    public void initTable2() {
        String[] cols = new String[]{"Mã CTSP", "Mã HĐ", "Mã SP", "Tên SP", "Kích thước", "Màu sắc", "Số lượng ", "Giá", "Tổng tiền"};
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(cols);
        jTable2.setModel(tableModel);
    }

    public void fillTable2() {
        tableModel.setRowCount(0);
        for (GioHang gh : ghdao.getAll()) {
            tableModel.addRow(ghdao.getRow(gh));
        }
    }

    private void displayChiTietHoaDon() {
        int row = jTable2.getSelectedRow();
        if (row < 0) {
            return;
        }

        // Lấy mã hóa đơn từ dòng được chọn
        String maHD = jTable3.getValueAt(row, 0).toString();

        // Lấy tất cả chi tiết hóa đơn từ DAO
        List<ChiTietHD> allCTHD = new ChiTietHDDAO().getAll();
        List<ChiTietHD> filteredCTHD = new ArrayList<>();

        // Lọc chi tiết theo mã hóa đơn
        for (ChiTietHD cthd : allCTHD) {
            if (String.valueOf(cthd.getMaHD()).equals(maHD)) {
                filteredCTHD.add(cthd);
            }
        }

        // Xóa dữ liệu cũ trong bảng chi tiết
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0);

        // Thêm dữ liệu mới vào bảng chi tiết
        for (ChiTietHD cthd : filteredCTHD) {
            model.addRow(new Object[]{
                cthd.getMaCTHD(),
                cthd.getMaSP(),
                cthd.getMaHD(),
                cthd.getSoLuong(),
                cthd.getGiaBan(),});
        }

    }

    public void showDetail() {
        int i = jTable3.getSelectedRow();
        List<HoaDon> listHD = hđao.getAll(); // tách ra để dễ kiểm soát
        if (i >= 0 && i < listHD.size()) {
            HoaDon hd = listHD.get(i);
            TF_MaHD.setText(String.valueOf(hd.getMaHD()));
            TF_MaNV.setText(String.valueOf(hd.getMaKH()));
            TF_MaKH.setText(String.valueOf(hd.getMaNV()));
            TF_NgayLap.setText(String.valueOf(hd.getNgayLap()));
            TF_Gia.setText(hd.getTrangThai());
            TF_TrangThai.setText(String.valueOf(hd.getTongTien()));
        } else {
            System.out.println("Chỉ số dòng không hợp lệ: " + i);
        }
    }

    private boolean validateFormHĐ() {
        if (TF_MaHD.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã hóa đơn.");
            return false;
        }
        if (TF_MaNV.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã khách hàng.");
            return false;
        }
        if (TF_MaKH.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã nhân viên.");
            return false;
        }
        if (TF_NgayLap.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày lập.");
            return false;
        }
        if (TF_Gia.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập trạng thái.");
            return false;
        }
        if (TF_TrangThai.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tổng tiền.");
            return false;
        }
        try {
            double gia = Double.parseDouble(TF_TrangThai.getText().trim());
            if (gia <= 0) {
                JOptionPane.showMessageDialog(this, "Giá tiền phải lớn hơn 0.");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Giá tiền phải là số hợp lệ.");
            return false;
        }
        if (TF_MaNV.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã khách hàng.");
            return false;
        }
        if (TF_MaKH.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã nhân viên.");
            return false;
        }
        if (TF_NgayLap.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày lập.");
            return false;
        }
        if (TF_Gia.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập trạng thái.");
            return false;
        }
        return true;
    }

    public String getHoaDonHeader() {
        StringBuilder sb = new StringBuilder();
        sb.append("╔════════════════════════════════════════════════════════════════════════════════╗\n");
        sb.append("║                        CỬA HÀNG THỜI TRANG FPT                      ║\n");
        sb.append("║                     Địa chỉ: FPT polyschool Hà Nội                  ║\n");
        sb.append("║                        Điện thoại: 03 44 55 2008                    ║\n");
        sb.append("╟────────────────────────────────────────────────────────────────────────────────╢\n");
        return sb.toString();
    }

    private void loadDataGioHang(int maHD) {
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ

        double tongTien = 0;

        for (GioHang item : gioHangList) {
            if (item.getMaHD() == maHD) {
                model.addRow(new Object[]{
                    item.getMaCTSP(),
                    item.getMaHD(),
                    item.getMaSP(),
                    item.getTenSP(),
                    item.getSize(),
                    item.getMsac(),
                    item.getSluong(),
                    item.getDonGia(),
                    item.getTtien()
                });
                tongTien += item.getTtien();
            }
        }

        // Cập nhật tổng tiền
        TF_TrangThai.setText(String.valueOf(tongTien));

        // Cập nhật giao diện
        jTable2.revalidate();
        jTable2.repaint();
    }

    private void updateTongTienHoaDon(int maHD) {
        try (Connection conn = DBConnect.getConnection()) {
            String sql = "SELECT DonGia*Sluong FROM GioHang WHERE MaHD = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, maHD);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    double tongTien = rs.getDouble(1);
                    TF_TrangThai.setText(String.valueOf(tongTien));

                    // Cập nhật tổng tiền vào CSDL
                    String updateSql = "UPDATE HoaDon SET TongTien = ? WHERE MaHD = ?";
                    try (PreparedStatement updatePs = conn.prepareStatement(updateSql)) {
                        updatePs.setDouble(1, tongTien);
                        updatePs.setInt(2, maHD);
                        updatePs.executeUpdate();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật tổng tiền: " + e.getMessage());
        }
    }

    private void xacNhanHoaDon() {
        String maHDText = TF_MaHD.getText().trim();
        if (maHDText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng tạo hóa đơn trước!");
            return;
        }

        int maHD = Integer.parseInt(maHDText);

        try (Connection conn = DBConnect.getConnection()) {
            // 1. Tạo hóa đơn
            String createHDSql = "INSERT INTO HoaDon (MaHD, NgayLap, TrangThai, TongTien) VALUES (?, GETDATE(), 'Đang xử lý', ?)";
            try (PreparedStatement stmt = conn.prepareStatement(createHDSql)) {
                stmt.setInt(1, maHD);
                stmt.setDouble(2, Double.parseDouble(TF_TrangThai.getText()));
                stmt.executeUpdate();
            }

            // 2. Thêm chi tiết hóa đơn
            String insertCTHDSql = "INSERT INTO ChiTietHD (MaHD, MaSP, SoLuong, GiaBan) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(insertCTHDSql)) {
                for (GioHang item : gioHangList) {
                    if (item.getMaHD() == maHD) {
                        stmt.setInt(1, maHD);
                        stmt.setInt(2, item.getMaSP());
                        stmt.setInt(3, item.getSluong());
                        stmt.setDouble(4, item.getDonGia());
                        stmt.executeUpdate();

                        // Cập nhật số lượng tồn kho
                        String updateTonKhoSql = "UPDATE ChiTietSanPham SET SoLuongTon = SoLuongTon - ? WHERE MaCTSP = ?";
                        try (PreparedStatement updateStmt = conn.prepareStatement(updateTonKhoSql)) {
                            updateStmt.setInt(1, item.getSluong());
                            updateStmt.setInt(2, item.getMaCTSP());
                            updateStmt.executeUpdate();
                        }
                    }
                }
            }

            JOptionPane.showMessageDialog(this, "Đã tạo hóa đơn thành công!");
            gioHangList.removeIf(item -> item.getMaHD() == maHD); // Xóa các mục đã thanh toán
            loadDataGioHang(maHD); // Cập nhật hiển thị

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tạo hóa đơn: " + ex.getMessage());
        }
    }

    public void them() {
        int mahd = Integer.parseInt(TF_MaHD.getText());
        int makh = Integer.parseInt(TF_MaNV.getText());
        int manv = Integer.parseInt(TF_MaKH.getText());
        java.sql.Date ngayLap = java.sql.Date.valueOf(TF_NgayLap.getText());
        String tthai = TF_Gia.getText();
        double tongTien = Double.parseDouble(TF_TrangThai.getText());

        HoaDon hd = new HoaDon(mahd, makh, manv, ngayLap, tthai, tongTien);

        int Result = hđao.themHD(hd);
        if (Result == 1) {
            fillTable();
            JOptionPane.showMessageDialog(this, "Thêm thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Thêm thất bại");
        }
    }

    public void xoa() {
        int i = jTable3.getSelectedRow();
        if (i >= 0) {
            int chon = JOptionPane.showConfirmDialog(this, "Bạn có thực sự muốn xoá",
                    "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (chon == JOptionPane.YES_NO_OPTION) {
                HoaDon hdcu = hđao.getAll().get(i);
                int result = hđao.xoaHD(hdcu.getMaHD());
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

        jSeparator1 = new javax.swing.JSeparator();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnthem = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        TF_Gia = new javax.swing.JTextField();
        TF_TrangThai = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        TF_MaHD = new javax.swing.JTextField();
        TF_MaKH = new javax.swing.JTextField();
        TF_NgayLap = new javax.swing.JTextField();
        TF_MaNV = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        btninhoadon = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Chi tiết sản phẩm"));

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

        btnthem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnthem.setText("Thêm vào hoá đơn");
        btnthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnthem)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 566, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(btnthem))
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder("Giỏ hàng"));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable2);

        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setText("Xoá khỏi hoá đơn");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addGap(32, 32, 32))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 566, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder("Hoá đơn"));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setText("Tìm");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel4.setText("Từ ngày");

        jLabel5.setText("Đến ngày");

        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton6.setText("Xoá hoá đơn");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 566, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                    .addContainerGap(489, Short.MAX_VALUE)
                    .addComponent(jButton6)
                    .addGap(8, 8, 8)))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                    .addContainerGap(232, Short.MAX_VALUE)
                    .addComponent(jButton6)
                    .addContainerGap()))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 255));
        jLabel1.setText("Tạo hoá đơn");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Mã hóa đơn:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Mã khách hàng:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Ngày lập:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Tổng tiền:");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("Mã nhân viên:");

        TF_MaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TF_MaNVActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Trạng thái:");

        jButton8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton8.setText("Tạo hoá đơn");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton9.setText("Làm mới");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton10.setText("Hiển thị hoá đơn");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton11.setText("Thanh toán");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton12.setText("Thêm vào DB");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TF_MaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TF_TrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel12)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TF_MaKH, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TF_MaNV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TF_NgayLap, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TF_Gia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jButton9))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton8))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton10))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton12)))
                .addGap(98, 98, 98))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton11)
                .addGap(118, 118, 118))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(254, 254, 254)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(TF_MaHD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(TF_MaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jButton9)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(TF_MaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(TF_NgayLap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TF_Gia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jButton10)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(TF_TrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jButton11)
                .addGap(30, 30, 30))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane4.setViewportView(jTextArea1);

        btninhoadon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btninhoadon.setText("In hoá đơn");
        btninhoadon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btninhoadonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btninhoadon)
                .addGap(228, 228, 228))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btninhoadon)
                .addGap(55, 55, 55))
        );

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 585, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(0, 15, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:

        String tuNgay = jTextField1.getText().trim();  // ví dụ: 2025-06-01
        String denNgay = jTextField2.getText().trim(); // ví dụ: 2025-06-13

        if (tuNgay.isEmpty() || denNgay.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ Từ ngày và Đến ngày");
            return;
        }

        try (Connection conn = DBConnect.getConnection()) {
            String sql = "SELECT * FROM HoaDon WHERE NgayLap BETWEEN ? AND ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, tuNgay);
            ps.setString(2, denNgay);

            ResultSet rs = ps.executeQuery();

            // Hiển thị dữ liệu ra bảng
            DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
            model.setRowCount(0); // Clear bảng

            while (rs.next()) {
                Object[] row = new Object[]{
                    rs.getInt("MaHD"),
                    rs.getString("MaKH"),
                    rs.getString("MaNV"),
                    rs.getDate("NgayLap"),
                    rs.getString("TrangThai"),
                    rs.getDouble("TongTien")
                };
                model.addRow(row);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi truy vấn: " + ex.getMessage());
        }


    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:

        int maHD = hđao.taoMaHoaDonMoi(); // Lấy mã mới từ DB
        String ngayHienTai = java.time.LocalDate.now().toString(); // Lấy ngày hiện tại (yyyy-MM-dd)

        TF_MaHD.setText(String.valueOf(maHD));      // Hiển thị mã vào textfield
        TF_NgayLap.setText(ngayHienTai);            // Hiển thị ngày vào textfield

        JOptionPane.showMessageDialog(null, "Đã tạo mã hóa đơn mới (chưa lưu): " + maHD);
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0);


    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        TF_Gia.setText("");
        TF_MaHD.setText("");
        TF_MaKH.setText("");
        TF_MaNV.setText("");
        TF_NgayLap.setText("");
        TF_TrangThai.setText("");
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:

        if (validateFormHĐ()) {

            try {
                // Lấy ngày hiện tại
                LocalDate currentDate = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String formattedDate = currentDate.format(formatter);

                // Tạo nội dung hoá đơn
                StringBuilder hoaDonContent = new StringBuilder();
                hoaDonContent.append(getHoaDonHeader()); // Thêm header
                hoaDonContent.append("\nNgày: ").append(formattedDate).append("\n\n");
                hoaDonContent.append("Mã hoá đơn:\t").append(TF_MaHD.getText()).append("\n");
                hoaDonContent.append("Mã khách hàng:\t").append(TF_MaNV.getText()).append("\n");
                hoaDonContent.append("Mã nhân viên:\t").append(TF_MaKH.getText()).append("\n");
                hoaDonContent.append("Ngày lập:\t").append(TF_NgayLap.getText()).append("\n");
                hoaDonContent.append("Trạng thái:\t").append(TF_Gia.getText()).append("\n");
                hoaDonContent.append("Tổng tiền:\t").append(TF_TrangThai.getText()).append("\n");

                jTextArea1.setText(hoaDonContent.toString());
                jTextArea1.setFont(new Font("Monospaced", Font.PLAIN, 12));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi khi hiển thị hoá đơn: " + ex.getMessage());
            }

        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void TF_MaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TF_MaNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TF_MaNVActionPerformed

    private void btninhoadonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btninhoadonActionPerformed
        // TODO add your handling code here:
        try {
            jTextArea1.print();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btninhoadonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        // TODO add your handling code here:
        showDetail();
    }//GEN-LAST:event_jTable3MouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:


    }//GEN-LAST:event_jTable1MouseClicked

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed
        // TODO add your handling code here:

        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm!");
            return;
        }

        try {
            // Lấy thông tin sản phẩm từ bảng
            int maCTSP = Integer.parseInt(jTable1.getValueAt(selectedRow, 0).toString());
            int maSP = Integer.parseInt(jTable1.getValueAt(selectedRow, 1).toString());
            String tenSP = jTable1.getValueAt(selectedRow, 2).toString();
            String size = jTable1.getValueAt(selectedRow, 3).toString();
            String mauSac = jTable1.getValueAt(selectedRow, 4).toString();
            double donGia = Double.parseDouble(jTable1.getValueAt(selectedRow, 5).toString());
            int soLuongTon = Integer.parseInt(jTable1.getValueAt(selectedRow, 6).toString());

            // Nhập số lượng
            String input = JOptionPane.showInputDialog(this, "Nhập số lượng:");
            if (input == null || input.trim().isEmpty()) {
                return;
            }

            int soLuong;
            try {
                soLuong = Integer.parseInt(input.trim());
                if (soLuong <= 0 || soLuong > soLuongTon) {
                    JOptionPane.showMessageDialog(this, "Số lượng phải từ 1 đến " + soLuongTon);
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Số lượng phải là số nguyên!");
                return;
            }

            // Lấy mã hóa đơn
            String maHDText = TF_MaHD.getText().trim();
            if (maHDText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng tạo hóa đơn trước khi thêm sản phẩm!");
                return;
            }

            int maHD = Integer.parseInt(maHDText);

            // Kiểm tra xem sản phẩm đã có trong giỏ chưa
            boolean found = false;
            for (GioHang item : gioHangList) {
                if (item.getMaCTSP() == maCTSP && item.getMaHD() == maHD) {
                    // Kiểm tra số lượng tồn kho trước khi cập nhật
                    int soLuongMoi = item.getSluong() + soLuong;
                    if (soLuongMoi > soLuongTon) {
                        JOptionPane.showMessageDialog(this, "Vượt quá số lượng tồn kho! Chỉ còn " + soLuongTon);
                        return;
                    }

                    // Cập nhật số lượng và tổng tiền
                    item.setSluong(soLuongMoi);
                    item.setTongTien(soLuongMoi * donGia);  // Sửa thành setTongTien()
                    found = true;
                    break;
                }
            }

            if (!found) {
                // Tính tổng tiền ngay tại đây
                double tongTien = soLuong * donGia;
                GioHang newItem = new GioHang(maCTSP, maHD, maSP, tenSP, size, mauSac, soLuong, donGia, tongTien);
                gioHangList.add(newItem);
            }

            // Cập nhật hiển thị
            loadDataGioHang(maHD);
            JOptionPane.showMessageDialog(this, "Đã thêm vào giỏ hàng!");

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage());
        }


    }//GEN-LAST:event_btnthemActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:

//        int i = jTable3.getSelectedRow();
//        if (i >= 0) {
//            int chon = JOptionPane.showConfirmDialog(this, "Bạn có thực sự muốn xoá",
//                    "Xác nhận", JOptionPane.YES_NO_OPTION);
//            if (chon == JOptionPane.YES_NO_OPTION) {
//                HoaDon hdcu = hđao.getAll().get(i);
//                int result = hđao.xoaHD(hdcu.getMaHD());
//                if (result == 1) {
//
//                    JOptionPane.showMessageDialog(this, "Xoá thành công");
//                } else {
//                    JOptionPane.showMessageDialog(this, "Xoá thất bại");
//                }
//            }
//        } else {
//            JOptionPane.showMessageDialog(this, "Chọn sản phẩm để xoá");
//        }
xoa();

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:

        int selectedRow = jTable2.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần xóa!");
            return;
        }

        int maCTSP = (int) jTable2.getValueAt(selectedRow, 0);
        int maHD = (int) jTable2.getValueAt(selectedRow, 1);

        // Xóa khỏi danh sách
        gioHangList.removeIf(item -> item.getMaCTSP() == maCTSP && item.getMaHD() == maHD);

        // Cập nhật hiển thị
        loadDataGioHang(maHD);
        JOptionPane.showMessageDialog(this, "Đã xóa sản phẩm khỏi giỏ hàng!");

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:

        int selectedRow = jTable3.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn cần thanh toán từ bảng!");
            return;
        }

        // Kiểm tra null cho các giá trị từ bảng
        Object maHDObj = jTable3.getValueAt(selectedRow, 0);
        Object trangThaiObj = jTable3.getValueAt(selectedRow, 4);
        Object tongTienObj = jTable3.getValueAt(selectedRow, 5);

        if (maHDObj == null || trangThaiObj == null || tongTienObj == null) {
            JOptionPane.showMessageDialog(this, "Dữ liệu hóa đơn không hợp lệ!");
            return;
        }

        int maHD = (int) maHDObj;
        String trangThai = trangThaiObj.toString();
        double tongTien = 0;

        try {
            tongTien = Double.parseDouble(tongTienObj.toString());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Tổng tiền không hợp lệ!");
            return;
        }

        if ("Đã thanh toán".equals(trangThai)) {
            JOptionPane.showMessageDialog(this, "Hóa đơn #" + maHD + " đã được thanh toán rồi!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Xác nhận thanh toán hóa đơn #" + maHD + "?\nTổng tiền: " + tongTien,
                "Xác nhận thanh toán",
                JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        try (Connection conn = DBConnect.getConnection()) {
            conn.setAutoCommit(false); // Bắt đầu transaction

            try {
                // 1. Cập nhật trạng thái hóa đơn
                String sqlUpdateHD = "UPDATE HoaDon SET TrangThai = 'Đã thanh toán' WHERE MaHD = ?";
                try (PreparedStatement ps = conn.prepareStatement(sqlUpdateHD)) {
                    ps.setInt(1, maHD);
                    if (ps.executeUpdate() == 0) {
                        throw new SQLException("Cập nhật hóa đơn thất bại, có thể không tồn tại hóa đơn #" + maHD);
                    }
                }

                // 2. Cập nhật số lượng tồn kho
                String sqlGetCTHD = "SELECT MaSP, SoLuong FROM ChiTietHoaDon WHERE MaCTHD = ?";
                String sqlUpdateTonKho = "UPDATE ChiTietSanPham SET SoLuongTon = SoLuongTon - ? WHERE MaCTSP = ?";

                try (PreparedStatement psGet = conn.prepareStatement(sqlGetCTHD); PreparedStatement psUpdate = conn.prepareStatement(sqlUpdateTonKho)) {

                    psGet.setInt(1, maHD);
                    ResultSet rs = psGet.executeQuery();

                    while (rs.next()) {
                        int maSP = rs.getInt("MaSP");
                        int soLuong = rs.getInt("SoLuong");

                        // Kiểm tra số lượng tồn kho trước khi trừ
                        if (!kiemTraSoLuongTonKho(conn, maSP, soLuong)) {
                            conn.rollback();
                            JOptionPane.showMessageDialog(this,
                                    "Sản phẩm mã " + maSP + " không đủ số lượng tồn kho!");
                            return;
                        }

                        psUpdate.setInt(1, soLuong);
                        psUpdate.setInt(2, maSP);
                        psUpdate.executeUpdate();
                    }
                }

                conn.commit(); // Commit transaction
                JOptionPane.showMessageDialog(this, "Thanh toán thành công hóa đơn #" + maHD);

                // 3. Làm mới dữ liệu
                fillTable(); // Cập nhật bảng hóa đơn
                fillTable2(); // Cập nhật bảng giỏ hàng nếu cần

                // Nếu đang xem hóa đơn này thì cập nhật trạng thái trên form
                if (TF_MaHD.getText().equals(String.valueOf(maHD))) {
                    TF_Gia.setText("Đã thanh toán");
                }

            } catch (SQLException ex) {
                conn.rollback(); // Rollback nếu có lỗi
                throw ex;
            } finally {
                conn.setAutoCommit(true);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi thanh toán: " + ex.getMessage());
        }
    }

// Thêm phương thức kiểm tra số lượng tồn kho
    private boolean kiemTraSoLuongTonKho(Connection conn, int maSP, int soLuongCanTru) throws SQLException {
        String sql = "SELECT SoLuongTon FROM ChiTietSanPham WHERE MaCTSP = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maSP);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int soLuongTon = rs.getInt("SoLuongTon");
                return soLuongTon >= soLuongCanTru;
            }
            return false;
        }


    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        if(validateFormHĐ()){
            them();
        }
//        try {
//            // Lấy và kiểm tra dữ liệu đầu vào
//            String maHDText = TF_MaHD.getText().trim();
//            String maKHText = TF_MaNV.getText().trim();
//            String maNVText = TF_MaKH.getText().trim();
//            String ngayLapText = TF_NgayLap.getText().trim();
//            String tthai = TF_Gia.getText().trim();
//            String tongTienText = TF_TrangThai.getText().trim();
//
//            // Kiểm tra rỗng
//            if (maHDText.isEmpty() || maKHText.isEmpty() || maNVText.isEmpty()
//                    || ngayLapText.isEmpty() || tthai.isEmpty() || tongTienText.isEmpty()) {
//                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
//                return;
//            }
//
//            // Chuyển đổi dữ liệu
//            int mahd = Integer.parseInt(maHDText);
//            int makh = Integer.parseInt(maKHText);
//            int manv = Integer.parseInt(maNVText);
//            java.sql.Date ngayLap = java.sql.Date.valueOf(ngayLapText); // cần đúng định dạng yyyy-MM-dd
//            float tongTien = Float.parseFloat(tongTienText);
//
//            // Tạo hóa đơn và thêm
//            HoaDon hd = new HoaDon(mahd, makh, manv, ngayLap, tthai, tongTien);
//            int result = hđao.themHD(hd);
//
//            if (result == 1) {
//                fillTable();
//                JOptionPane.showMessageDialog(this, "Thêm thành công");
//            } else {
//                JOptionPane.showMessageDialog(this, "Thêm thất bại");
//            }
//
//        } catch (NumberFormatException e) {
//            JOptionPane.showMessageDialog(this, "Mã, khách hàng, nhân viên và tổng tiền phải là số!");
//        } catch (IllegalArgumentException e) {
//            JOptionPane.showMessageDialog(this, "Ngày lập không đúng định dạng (yyyy-MM-dd)!");
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
//        }


    }//GEN-LAST:event_jButton12ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField TF_Gia;
    private javax.swing.JTextField TF_MaHD;
    private javax.swing.JTextField TF_MaKH;
    private javax.swing.JTextField TF_MaNV;
    private javax.swing.JTextField TF_NgayLap;
    private javax.swing.JTextField TF_TrangThai;
    private javax.swing.JButton btninhoadon;
    private javax.swing.JButton btnthem;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
