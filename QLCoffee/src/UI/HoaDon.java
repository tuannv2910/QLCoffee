/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import model.CTHoaDon;
import DAO.CtHoaDonDao;
import DAO.HoaDonDao;
import Helper.ChuyenDoi;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CHIEN
 */
public class HoaDon extends javax.swing.JPanel {

    HoaDonDao hdd = new HoaDonDao();
    CtHoaDonDao cthd = new CtHoaDonDao();
    int index = 0;
    String maHD;

    public HoaDon() {
        initComponents();
        loadTable();
        showDetail(0);
        tblCTHoaDon.getColumnModel().getColumn(0).setMinWidth(100);
        tblCTHoaDon.getColumnModel().getColumn(0).setMaxWidth(100);
    }

    public void loadTable() {
        DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
        model.setRowCount(0);
        tblHoaDon.getColumnModel().getColumn(0).setMinWidth(100);
        tblHoaDon.getColumnModel().getColumn(0).setMaxWidth(100);
        try {
            int i = 1;
            List<model.HoaDon> hd1 = hdd.select();
            for (model.HoaDon hd : hd1) {
                Object[] row = {
                    i++,
                    hd.getMaHD(),
                    hd.getNgayBan(),
                    ChuyenDoi.chuyenDoiTien(hd.getTongTien()) + " VND",
                    hd.getGhiChu()};
                model.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNgayMua = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTenTK = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCTHoaDon = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        txtTkMa = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        DateNgay = new com.toedter.calendar.JDateChooser();
        btXuatHoaDon = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(1280, 640));
        setMinimumSize(new java.awt.Dimension(1280, 640));
        setPreferredSize(new java.awt.Dimension(1280, 640));
        setVerifyInputWhenFocusTarget(false);
        setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setBackground(new java.awt.Color(107, 70, 52));
        jPanel1.setMaximumSize(new java.awt.Dimension(1600, 900));
        jPanel1.setMinimumSize(new java.awt.Dimension(1600, 900));
        jPanel1.setPreferredSize(new java.awt.Dimension(1600, 900));
        jPanel1.setLayout(new java.awt.GridLayout(1, 2));

        jPanel3.setBackground(new java.awt.Color(107, 70, 52));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Hóa đơn");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Ngày mua:");

        txtNgayMua.setEditable(false);
        txtNgayMua.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtNgayMua.setForeground(new java.awt.Color(107, 70, 52));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tổng tiền:");

        txtTongTien.setEditable(false);
        txtTongTien.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtTongTien.setForeground(new java.awt.Color(107, 70, 52));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nhân viên bán hàng");

        txtTenTK.setEditable(false);
        txtTenTK.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtTenTK.setForeground(new java.awt.Color(107, 70, 52));
        txtTenTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenTKActionPerformed(evt);
            }
        });

        tblHoaDon.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        tblHoaDon.setForeground(new java.awt.Color(107, 70, 52));
        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"ss", "ss", "ss", "ss", "ss"},
                {"ss", "ss", "ss", "ss", "ss"},
                {"ss", "ss", "ss", "ss", "ss"},
                {"ss", "ss", "ss", "ss", null}
            },
            new String [] {
                "STT", "Mã HD", "Ngày bán", "Tổng tiền", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDon.setFillsViewportHeight(true);
        tblHoaDon.setRowHeight(35);
        tblHoaDon.setSelectionBackground(new java.awt.Color(212, 181, 152));
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDon);
        tblHoaDon.getColumnModel().getColumn(0).setPreferredWidth(20);
        tblHoaDon.getColumnModel().getColumn(1).setPreferredWidth(30);
        tblHoaDon.getColumnModel().getColumn(2).setPreferredWidth(100);
        tblHoaDon.getColumnModel().getColumn(3).setPreferredWidth(50);
        if (tblHoaDon.getColumnModel().getColumnCount() > 0) {
            tblHoaDon.getColumnModel().getColumn(0).setResizable(false);
        }

        jPanel2.setBackground(new java.awt.Color(212, 181, 152));

        tblCTHoaDon.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        tblCTHoaDon.setForeground(new java.awt.Color(107, 70, 52));
        tblCTHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên SP", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCTHoaDon.setFillsViewportHeight(true);
        tblCTHoaDon.setRowHeight(35);
        tblCTHoaDon.setSelectionBackground(new java.awt.Color(212, 181, 152));
        jScrollPane2.setViewportView(tblCTHoaDon);
        tblCTHoaDon.getColumnModel().getColumn(0).setPreferredWidth(5);
        tblCTHoaDon.getColumnModel().getColumn(1).setPreferredWidth(50);
        tblCTHoaDon.getColumnModel().getColumn(2).setPreferredWidth(5);
        tblCTHoaDon.getColumnModel().getColumn(3).setPreferredWidth(50);

        jButton2.setBackground(new java.awt.Color(107, 70, 52));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/hienThi.png"))); // NOI18N
        jButton2.setText("Hiển thị tất cả");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(107, 70, 52));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/timKiem.png"))); // NOI18N
        jButton1.setText("Tìm hóa đơn");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Mã hóa đơn:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Chọn ngày:");

        btXuatHoaDon.setText("Xuất Hóa Đơn");
        btXuatHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btXuatHoaDonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTkMa, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(DateNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(81, 81, 81)
                                .addComponent(btXuatHoaDon)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                        .addGap(95, 95, 95)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTkMa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btXuatHoaDon))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(12, 12, 12)
                            .addComponent(jLabel8))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addComponent(DateNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNgayMua, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                                    .addComponent(txtTongTien))))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(txtTenTK)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 671, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 328, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel2))
                            .addComponent(txtNgayMua, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenTK, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3);

        add(jPanel1);
    }// </editor-fold>//GEN-END:initComponents

    private void txtTenTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenTKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenTKActionPerformed
    public void timKiemNgay() {
        DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
        model.setRowCount(0);
        try {
            int i = 1;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String date = sdf.format(DateNgay.getDate());
            List<model.HoaDon> hd1 = hdd.TimKiemNgay(date);
            for (model.HoaDon hd : hd1) {
                Object[] row = {
                    i++,
                    hd.getMaHD(),
                    hd.getNgayBan(),
                    hd.getTongTien(),
                    hd.getGhiChu()};
                model.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    public void timKiemMa() {
        DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
        model.setRowCount(0);
        try {
            int i = 1;
            List<model.HoaDon> hd1 = hdd.TimKiemMa(txtTkMa.getText());
            for (model.HoaDon hd : hd1) {
                Object[] row = {
                    i++,
                    hd.getMaHD(),
                    hd.getNgayBan(),
                    ChuyenDoi.chuyenDoiTien(hd.getTongTien()) + " VND",
                    hd.getGhiChu()};
                model.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }
    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        if (evt.getClickCount() == 1) {
            this.index = tblHoaDon.getSelectedRow();
            if (this.index >= 0) {
                showDetail(index);
                loadTable2();
                loadTxt();
            }

        }
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (txtTkMa.getText().equals("")) {
            timKiemNgay();
        } else {
            timKiemMa();
        }
        txtTkMa.setText("");
        DefaultTableModel model = (DefaultTableModel) tblCTHoaDon.getModel();
        model.setRowCount(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        loadTable();
        DefaultTableModel model = (DefaultTableModel) tblCTHoaDon.getModel();
        model.setRowCount(0);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btXuatHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btXuatHoaDonActionPerformed
        // TODO add your handling code here:
        xuatHoaDon(maHD);
    }//GEN-LAST:event_btXuatHoaDonActionPerformed

    public void loadTable2() {
        DefaultTableModel model = (DefaultTableModel) tblCTHoaDon.getModel();
        model.setRowCount(0);
        tblCTHoaDon.getColumnModel().getColumn(0).setMinWidth(100);
        tblCTHoaDon.getColumnModel().getColumn(0).setMaxWidth(100);
        try {
            int i = 1;
            List<CTHoaDon> hd1 = (List<CTHoaDon>) cthd.findById(tblHoaDon.getValueAt(index, 1).toString());
            for (CTHoaDon hd : hd1) {
                Object[] row = {
                    i++,
                    hd.getTenMon(),
                    hd.getSoLuong(),
                    ChuyenDoi.chuyenDoiTien(hd.getDonGia()) + " VND",
                    ChuyenDoi.chuyenDoiTien(hd.getThanhTien()) + " VND"};
                model.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    public void loadTxt() {
        try {
            int i = 1;
            List<model.HoaDon> hd1 = (List<model.HoaDon>) hdd.findById(tblHoaDon.getValueAt(index, 1).toString());
            for (model.HoaDon hd : hd1) {
                txtTenTK.setText(hd.getTenTaiKhoan());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    public void showDetail(int row) {
        txtNgayMua.setText(tblHoaDon.getValueAt(row, 2).toString());
        txtTongTien.setText(tblHoaDon.getValueAt(row, 3).toString());
        loadTxt();
        tblHoaDon.setRowSelectionInterval(index, index);
        maHD=tblHoaDon.getValueAt(row, 1).toString();
    }
    void xuatHoaDon(String name) {
        try {
            BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            //khởi tạo dc
            Document dc = new Document();
            //tạo font tiếng việt
            Font header = new Font(bf, 40, Font.BOLD, BaseColor.BLUE);//header
            Font headerTB = new Font(bf, 14, Font.BOLD, BaseColor.BLACK);//headertbale
            Font tiny = new Font(bf, 10, Font.UNDERLINE, BaseColor.GRAY);
            Font text = new Font(bf, 14, Font.NORMAL, BaseColor.BLACK);//normal text
            Font textTB = new Font(bf, 12, Font.NORMAL, BaseColor.BLACK);//normal text table
            System.out.println(name);
            PdfWriter.getInstance(dc, new FileOutputStream("hoaDon/" + name + ".pdf"));
            dc.open();
            //nội dung
            Paragraph[] p = new Paragraph[6];
            p[0] = new Paragraph("Số 1 Bà Triệu", header);
            p[0].setAlignment(Element.ALIGN_CENTER);
            p[0].setSpacingBefore(12);
            p[1] = new Paragraph("HOÁ ĐƠN BÁN HÀNG", tiny);
            p[1].setAlignment(Element.ALIGN_CENTER);
            p[1].setSpacingBefore(5);
            p[2] = new Paragraph("Ngày Mua: " + txtNgayMua.getText(), text);
            p[2].setAlignment(Element.ALIGN_LEFT);
            p[3] = new Paragraph("Tổng tiền: " + txtTongTien.getText(), text);
            p[3].setAlignment(Element.ALIGN_LEFT);
            //p[4] = new Paragraph("Ngày mua hàng: " + ChuyenDoi.chuyenDoiNgay(name).toString(new Date(), "dd/MM/yyyy"), text);
           // p[4].setAlignment(Element.ALIGN_LEFT);
            p[4] = new Paragraph("Tên nhân viên: " + txtTenTK.getText(), text);
            p[4].setAlignment(Element.ALIGN_LEFT);
            p[5] = new Paragraph("Danh sách sản phẩm: ", text);
            p[5].setAlignment(Element.ALIGN_LEFT);
            p[5].setSpacingAfter(20);
            //add nội dung
            for (int i = 0; i < 6; i++) {
                dc.add(p[i]);
            }
            //table
            //Khởi tạo một table có 3 cột
            PdfPTable table = new PdfPTable(5);
            //Khởi tạo ô header và thêm vào table
            String h[] = {"STT", "Tên", "Số lượng", "Đơn giá", "Thành tiền"};
            PdfPCell title[] = new PdfPCell[5];
            for (int i = 0; i < 5; i++) {
                title[i] = new PdfPCell(new Paragraph(h[i], headerTB));
                title[i].setPaddingLeft(2.0f);
                table.addCell(title[i]);
            }
            //Khởi tạo  ô data và thêm vào bảng
            for (int i = 0; i < tblCTHoaDon.getRowCount(); i++) {
                for (int j = 0; j < 5; j++) {
                    PdfPCell data = new PdfPCell(new Paragraph(tblCTHoaDon.getValueAt(i, j) + "", textTB));
                    table.addCell(data);
                }
            }
            dc.add(table);
            dc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser DateNgay;
    private javax.swing.JButton btXuatHoaDon;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblCTHoaDon;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTextField txtNgayMua;
    private javax.swing.JTextField txtTenTK;
    private javax.swing.JTextField txtTkMa;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
