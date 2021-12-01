/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import DAO.ThongKeDaoNam;
import DAO.ThongKeDaoThang;
import DAO.Top1Dao;
import DAO.Top2Dao;
import DAO.Top3Dao;
import Helper.ChuyenDoi;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import model.DoanhThuNam;
import model.DoanhThuThang;
import model.Top1;
import model.Top2;
import model.Top3;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author ADMIN
 */
public class ThongKeUI extends javax.swing.JPanel {

    /**
     * Creates new form ThongKeUI
     */
    ThongKeDaoThang tkThag = new ThongKeDaoThang();
    ThongKeDaoNam tkNam = new ThongKeDaoNam();
    Top1Dao top1 = new Top1Dao();
    Top2Dao top2 = new Top2Dao();
    Top3Dao top3 = new Top3Dao();

    public ThongKeUI() {
       initComponents();
        setNam(jpNam);
        loadNam();
        loadNam2();
        setTop_7ngay(jpTop4);
        DtNam();
        DlTop1();
        DlTop2();
        DlTop3();
    }

    public void setNam(JPanel jpnItem) {
        List<DoanhThuNam> listItem = tkNam.select();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        if (listItem != null) {
            for (DoanhThuNam item : listItem) {
                dataset.addValue(item.getDoanhThu(), "Doanh thu", item.getNam());
            }
        }
        JFreeChart barChart = ChartFactory.createLineChart(
                "Biểu đồ thống kê doanh thu theo năm".toUpperCase(),
                "Thời gian(Năm)", "Doanh thu (VND)",
                dataset, PlotOrientation.VERTICAL, false, true, true);
        
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 350));
        
        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    }
    
    public void loadNam() {
        try {
            DefaultComboBoxModel model = (DefaultComboBoxModel) cboNam.getModel(); //kết nối model với cbo
            model.removeAllElements();   //xóa toàn bộ item của cbo
            List<DoanhThuNam> lN = tkNam.HienNam();
            for (DoanhThuNam hd : lN) {
                model.addElement(hd.getNam());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void loadNam2() {
        try {
            DefaultComboBoxModel model = (DefaultComboBoxModel) cboNam2.getModel(); //kết nối model với cbo
            model.removeAllElements();   //xóa toàn bộ item của cbo
            List<DoanhThuNam> lN = tkNam.HienNam();
            for (DoanhThuNam hd : lN) {
                model.addElement(hd.getNam());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void setThang(JPanel jpnItem) {
        String nam = (String) cboNam.getSelectedItem();
        List<DoanhThuThang> listItem = tkThag.Thang(nam);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (listItem != null) {
            for (DoanhThuThang item : listItem) {
                dataset.addValue(item.getDoanhThu(), "Doanh thu", item.getThang());
            }
        }
        JFreeChart barChart = ChartFactory.createLineChart(
                "Biểu đồ thống kê doanh thu các tháng".toUpperCase(),
                "Thời gian (Tháng)", "Doanh thu(vnđ)",
                dataset, PlotOrientation.VERTICAL, false, true, true);
        
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 350));
        
        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    }
    
    public void setTop(JPanel jpnItem) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(dateNgay2.getDate());
        
        List<Top1> listItem = top1.TkTop1(date, date);
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (listItem != null) {
            for (Top1 item : listItem) {
                dataset.addValue(item.getSl(), "Top1" + item.getTenMon(), item.getNgay());
            }
        }
        List<Top2> listItem2 = top2.TkTop2(date, date, date);
        for (Top2 item : listItem2) {
            dataset.addValue(item.getSl(), "Top2" + item.getTenMon(), item.getNgay());
        }
        
        List<Top3> listItem3 = top3.TkTop3(date, date, date, date);
        
        for (Top3 item : listItem3) {
            dataset.addValue(item.getSl(), "Top3" + item.getTenMon(), item.getNgay());
        }
        
        JFreeChart barChart = ChartFactory.createLineChart(
                "Biểu đồ thống kê doanh thu tóp 3 sản phẩm bán chạy".toUpperCase(),
                "Thời gian (Ngày)", "Sản phẩm",
                dataset, PlotOrientation.VERTICAL, true, true, true);
        
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 350));
        
        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    }
    
    public void setTop_7ngay(JPanel jpnItem) {
        List<Top1> listItem = top1.getTop1_7ngay();
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (listItem != null) {
            for (Top1 item : listItem) {
                dataset.addValue(item.getSl(), "Top1" + item.getTenMon(), item.getNgay());
            }
        }
        List<Top2> listItem2 = top2.getTop2_7ngay();
        for (Top2 item : listItem2) {
            dataset.addValue(item.getSl(), "Top2" + item.getTenMon(), item.getNgay());
        }
        
        List<Top3> listItem3 = top3.getTop3_7ngay();
        
        for (Top3 item : listItem3) {
            dataset.addValue(item.getSl(), "Top3" + item.getTenMon(), item.getNgay());
        }
        
        JFreeChart barChart = ChartFactory.createLineChart(
                "Biểu đồ thống kê doanh thu tóp 3 sản phẩm bán chạy trong 7 ngày gần nhất".toUpperCase(),
                "Thời gian (Ngày)", "Sản phẩm",
                dataset, PlotOrientation.VERTICAL, true, true, true);
        
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 350));
        
        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    }
    
    public void DtNam() {
        DefaultTableModel model = (DefaultTableModel) tblNam.getModel();
        model.setRowCount(0);
        tblNam.getColumnModel().getColumn(0).setMinWidth(100);
        tblNam.getColumnModel().getColumn(0).setMaxWidth(100);
        
        try {
            int i = 1;
            List<DoanhThuNam> hd1 = tkNam.select();
            for (DoanhThuNam dt : hd1) {
                Object[] row = {
                    i++,
                    dt.getNam(),
                    ChuyenDoi.chuyenDoiTien(dt.getDoanhThu()) + " VND"};
                model.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
        
    }
    
    public void DtThang() {
        DefaultTableModel model = (DefaultTableModel) tblThang.getModel();
        model.setRowCount(0);
        try {
            String nam = (String) cboNam2.getSelectedItem();
            List<DoanhThuThang> hd1 = tkThag.Thang(nam);
            for (DoanhThuThang dt : hd1) {
                Object[] row = {
                    dt.getThang(),
                    ChuyenDoi.chuyenDoiTien(dt.getDoanhThu()) + " VND"};
                model.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
        
    }
    
    public void DlTop1() {
        List<Top1> top = top1.DlTop1();
        
        for (int i = 0; i < top.size(); i++) {
            Top1 a = top.get(0);
            lbTop1.setText(a.getTenMon() + " " + String.valueOf(a.getSl()) + " Sản phẩm");
        }
        
        if (top.size() == 2) {
            for (int i = 0; i < top.size(); i++) {
                Top1 a = top.get(1);
                lbTop2.setText(a.getTenMon() + " " + String.valueOf(a.getSl()) + " Sản phẩm");
            }
            List<Top2> top2a = top2.DlTop2();
            for (int i = 0; i < top2a.size(); i++) {
                Top2 b = top2a.get(0);
                lbTop3.setText(b.getTenMon() + " " + String.valueOf(b.getSl()) + " Sản phẩm");
            }
        }
        if (top.size() == 3) {
            for (int i = 0; i < top.size(); i++) {
                Top1 a = top.get(0);
                lbTop1.setText(a.getTenMon() + " " + String.valueOf(a.getSl()) + " Sản phẩm");
            }
            for (int i = 0; i < top.size(); i++) {
                Top1 a = top.get(1);
                lbTop2.setText(a.getTenMon() + " " + String.valueOf(a.getSl()) + " Sản phẩm");
            }
            for (int i = 0; i < top.size(); i++) {
                Top1 a = top.get(2);
                lbTop3.setText(a.getTenMon() + " " + String.valueOf(a.getSl()) + " Sản phẩm");
            }
        }
    }
    
    public void DlTop2() {
        List<Top1> top = top1.DlTop1();
        List<Top2> top2a = top2.DlTop2();
        if (top.size() == 1) {
            
            for (int i = 0; i < top2a.size(); i++) {
                Top2 a = top2a.get(i);
                lbTop2.setText(a.getTenMon() + " " + String.valueOf(a.getSl() + " Sản phẩm"));
            }
            
            if (top2a.size() == 2) {
                for (int i = 0; i < top2a.size(); i++) {
                    Top2 a = top2a.get(0);
                    lbTop2.setText(a.getTenMon() + " " + String.valueOf(a.getSl() + " Sản phẩm"));
                }
                for (int i = 0; i < top2a.size(); i++) {
                    Top2 a = top2a.get(1);
                    lbTop3.setText(a.getTenMon() + " " + String.valueOf(a.getSl() + " Sản phẩm"));
                }
            }
        }
        
    }
    
    public void DlTop3() {
        List<Top1> top = top1.DlTop1();
        List<Top2> top2a = top2.DlTop2();
        if (top.size() == 1) {
            if (top2a.size() == 1) {
                List<Top3> a = top3.DlTop3();
                for (Top3 top31 : a) {
                    lbTop3.setText(top31.getTenMon() + " " + String.valueOf(top31.getSl()) + " Sản phẩm");
                }
            }
        }
    }
    
    public void DlTop1_TheoNgay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(dateNgay2.getDate());
        List<Top1> top = top1.DlTkTop1(date, date);
        for (int i = 0; i < top.size(); i++) {
            Top1 a = top.get(0);
            lbTop1.setText(a.getTenMon() + " " + String.valueOf(a.getSl()) + " Sản phẩm");
        }
        
        if (top.size() == 2) {
            for (int i = 0; i < top.size(); i++) {
                Top1 a = top.get(1);
                lbTop2.setText(a.getTenMon() + " " + String.valueOf(a.getSl()) + " Sản phẩm");
            }
            List<Top2> top2a = top2.DlTkTop1(date, date, date);
            for (int i = 0; i < top2a.size(); i++) {
                Top2 b = top2a.get(0);
                lbTop3.setText(b.getTenMon() + " " + String.valueOf(b.getSl()) + " Sản phẩm");
            }
        }
        if (top.size() == 3) {
            for (int i = 0; i < top.size(); i++) {
                Top1 a = top.get(0);
                lbTop1.setText(a.getTenMon() + " " + String.valueOf(a.getSl()) + " Sản phẩm");
            }
            for (int i = 0; i < top.size(); i++) {
                Top1 a = top.get(1);
                lbTop2.setText(a.getTenMon() + " " + String.valueOf(a.getSl()) + " Sản phẩm");
            }
            for (int i = 0; i < top.size(); i++) {
                Top1 a = top.get(2);
                lbTop3.setText(a.getTenMon() + " " + String.valueOf(a.getSl()) + " Sản phẩm");
            }
        }
        if (top.size() > 3) {
            for (int i = 0; i < top.size(); i++) {
                Top1 a = top.get(0);
                lbTop1.setText(a.getTenMon() + " " + String.valueOf(a.getSl()) + " Sản phẩm");
            }
            for (int i = 0; i < top.size(); i++) {
                Top1 a = top.get(1);
                lbTop2.setText(a.getTenMon() + " " + String.valueOf(a.getSl()) + " Sản phẩm");
            }
            for (int i = 0; i < top.size(); i++) {
                Top1 a = top.get(2);
                lbTop3.setText(a.getTenMon() + " " + String.valueOf(a.getSl()) + " Sản phẩm");
            }
            JOptionPane.showMessageDialog(this, "Có nhiều hơn 3 sản phẩm bán chạy có cùng số lượng bán ra");
        }
    }
    
    public void DlTop2_TheoNgay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(dateNgay2.getDate());
        List<Top1> top = top1.DlTkTop1(date, date);
        List<Top2> top2a = top2.DlTkTop1(date, date, date);
        if (top.size() == 1) {
            
            for (int i = 0; i < top2a.size(); i++) {
                Top2 a = top2a.get(i);
                lbTop2.setText(a.getTenMon() + " " + String.valueOf(a.getSl() + " Sản phẩm"));
            }
            
            if (top2a.size() == 2) {
                for (int i = 0; i < top2a.size(); i++) {
                    Top2 a = top2a.get(0);
                    lbTop2.setText(a.getTenMon() + " " + String.valueOf(a.getSl() + " Sản phẩm"));
                }
                for (int i = 0; i < top2a.size(); i++) {
                    Top2 a = top2a.get(1);
                    lbTop3.setText(a.getTenMon() + " " + String.valueOf(a.getSl() + " Sản phẩm"));
                }
            }
        }
        
    }
    
    public void DlTop3_TheoNgay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(dateNgay2.getDate());
        List<Top1> top = top1.DlTkTop1(date, date);
        List<Top2> top2a = top2.DlTkTop1(date, date, date);
        if (top.size() == 1) {
            if (top2a.size() == 1) {
                List<Top3> a = top3.DlTkTop3(date, date, date, date);
                for (Top3 top31 : a) {
                    lbTop3.setText(top31.getTenMon() + " " + String.valueOf(top31.getSl()) + " Sản phẩm");
                }
            }
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

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        dateNgay2 = new com.toedter.calendar.JDateChooser();
        btn7Ngay = new javax.swing.JButton();
        jpTop4 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblNam = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        btnTop3 = new javax.swing.JButton();
        lbTieuDe = new javax.swing.JLabel();
        lbTop1 = new javax.swing.JLabel();
        lbTop2 = new javax.swing.JLabel();
        lbTop3 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        cboNam2 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblThang = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jpNam = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cboNam = new javax.swing.JComboBox<>();
        jpThang = new javax.swing.JPanel();

        setMaximumSize(new java.awt.Dimension(1280, 640));
        setMinimumSize(new java.awt.Dimension(1280, 640));
        setLayout(new java.awt.GridLayout(2, 0));

        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btn7Ngay.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn7Ngay.setText("OK");
        btn7Ngay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn7NgayMouseClicked(evt);
            }
        });
        btn7Ngay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn7NgayActionPerformed(evt);
            }
        });

        jpTop4.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jpTop4Layout = new javax.swing.GroupLayout(jpTop4);
        jpTop4.setLayout(jpTop4Layout);
        jpTop4Layout.setHorizontalGroup(
            jpTop4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jpTop4Layout.setVerticalGroup(
            jpTop4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 246, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpTop4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(dateNgay2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn7Ngay)
                        .addGap(0, 345, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn7Ngay)
                    .addComponent(dateNgay2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpTop4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jPanel3);

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblNam.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Năm", "Doanh thu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblNam);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Năm", jPanel5);

        jLabel2.setText("Chọn ngày :");

        btnTop3.setText("OK");
        btnTop3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTop3ActionPerformed(evt);
            }
        });

        lbTieuDe.setText("Top 3 sản phẩm bán nhiều nhất trong 7 ngày gần nhất.");

        lbTop1.setText("Top 1 : ");

        lbTop2.setText("Top 2 : ");

        lbTop3.setText("Top 3 : ");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTieuDe, javax.swing.GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnTop3))
                            .addComponent(lbTop1)
                            .addComponent(lbTop2)
                            .addComponent(lbTop3))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnTop3)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbTieuDe)
                .addGap(18, 18, 18)
                .addComponent(lbTop1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbTop2)
                .addGap(18, 18, 18)
                .addComponent(lbTop3)
                .addContainerGap(59, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Top3", jPanel7);

        cboNam2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        cboNam2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboNam2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboNam2ItemStateChanged(evt);
            }
        });

        tblThang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tháng", "Doanh thu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblThang);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(cboNam2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cboNam2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Tháng", jPanel6);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        jPanel1.add(jPanel4);

        add(jPanel1);

        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jpNamLayout = new javax.swing.GroupLayout(jpNam);
        jpNam.setLayout(jpNamLayout);
        jpNamLayout.setHorizontalGroup(
            jpNamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 608, Short.MAX_VALUE)
        );
        jpNamLayout.setVerticalGroup(
            jpNamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 286, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpNam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpNam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.add(jPanel8);

        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Năm");

        cboNam.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        cboNam.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboNamItemStateChanged(evt);
            }
        });
        cboNam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboNamMouseClicked(evt);
            }
        });
        cboNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNamActionPerformed(evt);
            }
        });

        jpThang.setBackground(new java.awt.Color(204, 204, 255));

        javax.swing.GroupLayout jpThangLayout = new javax.swing.GroupLayout(jpThang);
        jpThang.setLayout(jpThangLayout);
        jpThangLayout.setHorizontalGroup(
            jpThangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jpThangLayout.setVerticalGroup(
            jpThangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 249, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpThang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboNam, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 421, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cboNam)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpThang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.add(jPanel9);

        add(jPanel2);
    }// </editor-fold>//GEN-END:initComponents

    private void btn7NgayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn7NgayMouseClicked
        setTop(jpTop4);
        if (evt.getClickCount() == 2) {
            setTop_7ngay(jpTop4);
        }
    }//GEN-LAST:event_btn7NgayMouseClicked

    private void btn7NgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn7NgayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn7NgayActionPerformed

    private void cboNam2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboNam2ItemStateChanged
        DtThang();
    }//GEN-LAST:event_cboNam2ItemStateChanged

    private void cboNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboNamActionPerformed

    private void cboNamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboNamMouseClicked
        //          setChart2(jpThang);
    }//GEN-LAST:event_cboNamMouseClicked

    private void cboNamItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboNamItemStateChanged
        setThang(jpThang);
    }//GEN-LAST:event_cboNamItemStateChanged

    private void btnTop3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTop3ActionPerformed
        // TODO add your handling code here:
        DlTop1_TheoNgay();
        DlTop2_TheoNgay();
        DlTop3_TheoNgay();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String date = sdf.format(dateNgay2.getDate());
        lbTieuDe.setText("Tóp 3 sản phẩm bán chạy nhất trong ngày " + date);
    }//GEN-LAST:event_btnTop3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn7Ngay;
    private javax.swing.JButton btnTop3;
    private javax.swing.JComboBox<String> cboNam;
    private javax.swing.JComboBox<String> cboNam2;
    private com.toedter.calendar.JDateChooser dateNgay2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel jpNam;
    private javax.swing.JPanel jpThang;
    private javax.swing.JPanel jpTop4;
    private javax.swing.JLabel lbTieuDe;
    private javax.swing.JLabel lbTop1;
    private javax.swing.JLabel lbTop2;
    private javax.swing.JLabel lbTop3;
    private javax.swing.JTable tblNam;
    private javax.swing.JTable tblThang;
    // End of variables declaration//GEN-END:variables
}
