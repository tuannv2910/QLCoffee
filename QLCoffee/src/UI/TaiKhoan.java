/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import DAO.TaiKhoanDao;
import Helper.CheckHelper;
import java.awt.Color;
import static java.awt.Color.pink;
import static java.awt.Color.white;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.TaiKhoanModel;

public class TaiKhoan extends javax.swing.JPanel {

    TaiKhoanDao tkdao = new TaiKhoanDao();
    DefaultTableModel model;
    int index = 0;
    List<TaiKhoanModel> listTaiKhoan = new ArrayList<>();

    public TaiKhoan() {
        initComponents();
        loadTableHD();
    }

    //Đổ tài khoản hoạt động vào table
    public void loadTableHD() {
        String vaiTro;
        DefaultTableModel model = (DefaultTableModel) tblTaiKhoan.getModel();
        model.setRowCount(0);
        try {
            int i = 1;
            List<TaiKhoanModel> hd1 = TaiKhoanDao.HienThiHD();
            for (TaiKhoanModel hd : hd1) {
                if (hd.isVaiTro() == false) {
                    vaiTro = "Nhân Viên";

                } else {
                    vaiTro = "Quản lí";
                }

                Object[] row = {
                    i++,
                    hd.getTenTaiKhoan(),
                    hd.getHoTen(),
                    hd.getMatKhau(),
                    hd.getEmail(),
                    vaiTro};

                model.addRow(row);
                btnXoa.setEnabled(false);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    //đổ tài khoản không hoạt động lên form
    public void loadTableKhongHD() {
        String vaiTro;
        DefaultTableModel model = (DefaultTableModel) tblTaiKhoan.getModel();
        model.setRowCount(0);
        try {
            int i = 1;
            List<TaiKhoanModel> hd1 = TaiKhoanDao.HienThiKhongHD();
            for (TaiKhoanModel hd : hd1) {
                if (hd.isVaiTro() == false) {
                    vaiTro = "Nhân Viên";

                } else {
                    vaiTro = "Quản lí";
                }

                Object[] row = {
                    i++,
                    hd.getTenTaiKhoan(),
                    hd.getHoTen(),
                    hd.getMatKhau(),
                    hd.getEmail(),
                    vaiTro};
                model.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    //
    public TaiKhoanModel getmodel() {
        TaiKhoanModel model = new TaiKhoanModel();
        model.setTenTaiKhoan(txtTenDN.getText());
        model.setHoTen(txtHoten.getText());
        model.setMatKhau(new String(txtPass.getPassword()));  //chuyển char[] thành String
        model.setEmail(txtEmail.getText());
        if (rdQuanLi.isSelected()) {
            model.setVaiTro(true);
        }
        if (rdNhanVien.isSelected()) {
            model.setVaiTro(false);
        }
        if (rdVoHieuHoa.isSelected() == false) {
            model.setTrangThai("1");
        }
        if (rdVoHieuHoa.isSelected() == true) {
            model.setTrangThai("0");
        }
        model.setAn(true);
        return model;
    }

    //tạo tài khoản mới.
    public void insert() {
        TaiKhoanModel model = getmodel();
        try {
            TaiKhoanDao.insert(model);
            JOptionPane.showMessageDialog(this, "Thêm thành công");
            this.loadTableHD();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Thêm thất bại");
        }
    }

    public void update() {
        TaiKhoanModel model = getmodel();
        String confirm = new String(txtPassConfirm.getPassword());
        if (!confirm.equals(model.getMatKhau())) {
            txtPassConfirm.setBackground(pink);
            JOptionPane.showMessageDialog(this, "Xác nhận mật khẩu không đúng!");
            checkmail();
        } else {
            try {
                TaiKhoanDao.update(model);     //cập nhật nhân viên theo maNV
                this.loadTableHD();//điền tt mới vào bảng
                JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
                txtPass.setBackground(Color.white);
                txtPassConfirm.setBackground(white);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //Khóa tài khoản
    public void delete() {
        int hoi = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa?", "Hỏi xóa", JOptionPane.YES_NO_OPTION);
        if (hoi != JOptionPane.YES_OPTION) {
            return;
        }
        String tendn = txtTenDN.getText();
        try {
            tkdao.delete(tendn);   //xóa nhân viên theo maNV
            this.loadTableKhongHD();//điền tt mới vào bảng
            //  this.clear();       //xóa trắng form và chỉnh lại status
            JOptionPane.showMessageDialog(this, "Xóa thành công!");
        } catch (Exception e) {
        }

    }

    //checkGmail trong tài khoản
    public boolean checkmail() {
        String mail = txtEmail.getText();
        try {
            TaiKhoanModel tk = tkdao.checkTrungGmail(mail);
            if (tk != null) {
                JOptionPane.showMessageDialog(this, "Đã trùng Gmail");
                return false;
            }
            if (tk == null) {
                System.out.println("Thêm thành công");
                return true;
            }
        } catch (Exception e) {
        }
        return true;

    }

    //click vào bảng sẽ đổ dữ liệu ra form
    public void showDetail(int row) {
        txtHoten.setText(tblTaiKhoan.getValueAt(row, 2).toString());
        txtTenDN.setText(tblTaiKhoan.getValueAt(row, 1).toString());
        txtPass.setText(tblTaiKhoan.getValueAt(row, 3).toString());
        txtEmail.setText(tblTaiKhoan.getValueAt(row, 4).toString());
        if (tblTaiKhoan.getValueAt(row, 5).equals("Quản lí")) {
            rdQuanLi.setSelected(true);
        }
        if (tblTaiKhoan.getValueAt(row, 5).equals("Nhân Viên")) {
            rdNhanVien.setSelected(true);
        }
    }

    // Check đổi mật khẩu trong form
    public boolean doiMatKhau() {
        TaiKhoanModel model = getmodel();
        String confirm = new String(txtPassConfirm.getPassword());
        if (!confirm.equals(model.getMatKhau())) {
            txtPassConfirm.setBackground(pink);
            JOptionPane.showMessageDialog(this, "Xác nhận mật khẩu không đúng!");
            return false;
        } else {
            return true;
        }
    }

    //check trùng mã khóa chính tên tài khoản
    public boolean checkMa() {
        String tenTaiKhoan = txtTenDN.getText();
        try {
            TaiKhoanModel tk = tkdao.checkTrungMa(tenTaiKhoan);
            if (tk != null) {
                JOptionPane.showMessageDialog(this, "Đã trùng tên đăng nhập");
                return false;
            }
            if (tk == null) {
                System.out.println("Thêm thành công2");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
    
    public void resertForm() {
        this.txtTenDN.setText("");
        this.txtHoten.setText("");
        this.txtPass.setText("");
        this.txtPassConfirm.setText("");
        this.txtEmail.setText("");
        this.txtTenDN.setBackground(white);
        this.txtHoten.setBackground(white);
        this.txtPass.setBackground(white);
        this.txtPassConfirm.setBackground(white);
        this.txtEmail.setBackground(white);
        txtTenDN.setEnabled(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTenDN = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtPass = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        txtPassConfirm = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btNew = new javax.swing.JButton();
        btUpdate = new javax.swing.JButton();
        btAdd = new javax.swing.JButton();
        btFind = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTaiKhoan = new javax.swing.JTable();
        rdVoHieuHoa = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cbTaiKhoan = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtHoten = new javax.swing.JTextField();
        rdQuanLi = new javax.swing.JRadioButton();
        rdNhanVien = new javax.swing.JRadioButton();
        btnXoa = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(1400, 640));
        setMinimumSize(new java.awt.Dimension(1400, 640));
        setPreferredSize(new java.awt.Dimension(1400, 640));
        setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setBackground(new java.awt.Color(212, 181, 152));
        jPanel1.setMaximumSize(new java.awt.Dimension(1600, 900));
        jPanel1.setMinimumSize(new java.awt.Dimension(1600, 900));
        jPanel1.setPreferredSize(new java.awt.Dimension(1600, 900));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Quản lý tài khoản");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tên đăng nhập:");

        txtTenDN.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtTenDN.setForeground(new java.awt.Color(107, 70, 52));
        txtTenDN.setVerifyInputWhenFocusTarget(false);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Mật khẩu:");

        txtPass.setVerifyInputWhenFocusTarget(false);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nhập lại mật khẩu:");

        txtPassConfirm.setVerifyInputWhenFocusTarget(false);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Email:");

        txtEmail.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtEmail.setForeground(new java.awt.Color(107, 70, 52));
        txtEmail.setVerifyInputWhenFocusTarget(false);

        jPanel2.setBackground(new java.awt.Color(212, 181, 152));
        jPanel2.setLayout(new java.awt.GridLayout(2, 2, 20, 20));

        btNew.setBackground(new java.awt.Color(50, 191, 190));
        btNew.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btNew.setForeground(new java.awt.Color(255, 255, 255));
        btNew.setText("Làm mới");
        btNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNewActionPerformed(evt);
            }
        });
        jPanel2.add(btNew);

        btUpdate.setBackground(new java.awt.Color(253, 138, 79));
        btUpdate.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btUpdate.setText("Sửa");
        btUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUpdateActionPerformed(evt);
            }
        });
        jPanel2.add(btUpdate);

        btAdd.setBackground(new java.awt.Color(107, 70, 52));
        btAdd.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btAdd.setForeground(new java.awt.Color(255, 255, 255));
        btAdd.setText("Thêm tài khoản");
        btAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddActionPerformed(evt);
            }
        });
        jPanel2.add(btAdd);

        btFind.setBackground(new java.awt.Color(107, 70, 52));
        btFind.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btFind.setForeground(new java.awt.Color(255, 255, 255));
        btFind.setText("Tìm kiếm");
        btFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFindActionPerformed(evt);
            }
        });
        jPanel2.add(btFind);

        tblTaiKhoan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tblTaiKhoan.setForeground(new java.awt.Color(107, 70, 52));
        tblTaiKhoan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên đăng nhập", "Họ tên", "Mật khẩu", "Email", "Vai trò"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTaiKhoan.setFillsViewportHeight(true);
        tblTaiKhoan.setRowHeight(35);
        tblTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTaiKhoanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTaiKhoan);

        rdVoHieuHoa.setBackground(new java.awt.Color(107, 70, 52));
        rdVoHieuHoa.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        rdVoHieuHoa.setForeground(new java.awt.Color(255, 255, 255));
        rdVoHieuHoa.setText("Vô hiệu hóa");
        rdVoHieuHoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdVoHieuHoaActionPerformed(evt);
            }
        });

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/logo1.png"))); // NOI18N

        cbTaiKhoan.setBackground(new java.awt.Color(204, 204, 255));
        cbTaiKhoan.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        cbTaiKhoan.setForeground(new java.awt.Color(107, 70, 52));
        cbTaiKhoan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tài khoản đang hoạt động", "Tài khoản không hoạt động" }));
        cbTaiKhoan.setRequestFocusEnabled(false);
        cbTaiKhoan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbTaiKhoanItemStateChanged(evt);
            }
        });
        cbTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbTaiKhoanMouseClicked(evt);
            }
        });
        cbTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTaiKhoanActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Ho Tên:");

        txtHoten.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtHoten.setForeground(new java.awt.Color(107, 70, 52));
        txtHoten.setVerifyInputWhenFocusTarget(false);
        txtHoten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHotenActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdQuanLi);
        rdQuanLi.setText("Quản lý");
        rdQuanLi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdQuanLiActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdNhanVien);
        rdNhanVien.setSelected(true);
        rdNhanVien.setText("Nhân viên");

        btnXoa.setBackground(new java.awt.Color(255, 102, 102));
        btnXoa.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setText("Xóa nhân vên");
        btnXoa.setPreferredSize(new java.awt.Dimension(161, 31));
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel1)
                        .addGap(81, 81, 81))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(cbTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtHoten, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPass, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPassConfirm, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenDN, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(rdVoHieuHoa)
                                .addGap(18, 18, 18)
                                .addComponent(rdQuanLi)
                                .addGap(29, 29, 29)
                                .addComponent(rdNhanVien))
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addComponent(jLabel7)))
                .addGap(48, 48, 48)
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(73, 73, 73))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtHoten, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTenDN, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPassConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdVoHieuHoa)
                            .addComponent(rdQuanLi)
                            .addComponent(rdNhanVien))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                            .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        add(jPanel1);

        getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        this.delete();
        resertForm();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void rdQuanLiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdQuanLiActionPerformed

    }//GEN-LAST:event_rdQuanLiActionPerformed

    private void txtHotenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHotenActionPerformed

    }//GEN-LAST:event_txtHotenActionPerformed

    private void cbTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTaiKhoanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTaiKhoanActionPerformed

    private void cbTaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbTaiKhoanMouseClicked

    }//GEN-LAST:event_cbTaiKhoanMouseClicked

    private void cbTaiKhoanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbTaiKhoanItemStateChanged
        if (cbTaiKhoan.getSelectedIndex() == 0) {
            loadTableHD();
            btnXoa.setEnabled(false);
        }
        if (cbTaiKhoan.getSelectedIndex() == 1) {
            loadTableKhongHD();
            btnXoa.setEnabled(true);
        }
    }//GEN-LAST:event_cbTaiKhoanItemStateChanged

    private void rdVoHieuHoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdVoHieuHoaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdVoHieuHoaActionPerformed

    private void tblTaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTaiKhoanMouseClicked
        if (evt.getClickCount() == 2) {
            this.index = tblTaiKhoan.getSelectedRow();
            if (this.index >= 0) {
                showDetail(index);
                txtTenDN.setEnabled(false);
            }
        }
    }//GEN-LAST:event_tblTaiKhoanMouseClicked

    private void btFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFindActionPerformed
        String vaiTro;
        String timkiem = JOptionPane.showInputDialog(this, "Tìm kiếm tài khoản !");
        DefaultTableModel tblmodel = (DefaultTableModel) tblTaiKhoan.getModel();
        tblmodel.setRowCount(0);
        try {
            int i = 1;
            List<TaiKhoanModel> model = TaiKhoanDao.TimKiemMa(timkiem);
            for (TaiKhoanModel hd : model) {
                if (hd.isVaiTro() == false) {
                    vaiTro = "Nhân Viên";

                } else {
                    vaiTro = "Quản lí";
                }
                Object[] row = {
                    i++,
                    hd.getTenTaiKhoan(),
                    hd.getHoTen(),
                    hd.getMatKhau(),
                    hd.getEmail(),
                    vaiTro};
                tblmodel.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);

        }
    }//GEN-LAST:event_btFindActionPerformed

    private void btAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddActionPerformed
        // TODO add your handling code here:
        if (!CheckHelper.checkNullText(txtHoten)) {
            return;
        }
        if (!CheckHelper.checkNullText(txtTenDN)) {
            return;
        }
        if (!CheckHelper.checkNulPass(txtPass)) {
            return;
        }
        if (!CheckHelper.checkNulPass(txtPassConfirm)) {
            return;
        }
        if (!CheckHelper.checkNullText(txtEmail)) {
            return;
        }
        if (!CheckHelper.checkName(txtHoten)) {
            return;
        }
        if (!CheckHelper.checkMaNV(txtTenDN)) {
            return;
        }
        if (!CheckHelper.checkPass(txtPass)) {
            return;
        }
        if (!doiMatKhau()) {
            return;
        }
        if(checkMa() == false) {
            return;
        }
        if (!CheckHelper.checkEmail(txtEmail)) {
            return;
        }
        if(!checkmail()){
            return;
        }
        insert();
        resertForm();
    }//GEN-LAST:event_btAddActionPerformed

    private void btUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUpdateActionPerformed
        // TODO add your handling code here:
        if (!CheckHelper.checkNullText(txtHoten)) {
            return;
        }
        if (!CheckHelper.checkNulPass(txtPass)) {
            return;
        }
        if (!CheckHelper.checkNulPass(txtPassConfirm)) {
            return;
        }
        if (!CheckHelper.checkNullText(txtEmail)) {
            return;
        }
        if (!CheckHelper.checkName(txtHoten)) {
            return;
        }
        if (!CheckHelper.checkMaNV(txtTenDN)) {
            return;
        }
        if (!CheckHelper.checkPass(txtPass)) {
            return;
        }
        if (!doiMatKhau()) {
            return;
        }
        if (!CheckHelper.checkEmail(txtEmail)) {
            return;
        }
        if(!checkmail()){
            return;
        }
        this.update();
        resertForm();
    }//GEN-LAST:event_btUpdateActionPerformed

    private void btNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNewActionPerformed
        // TODO add your handling code here:
        resertForm();
    }//GEN-LAST:event_btNewActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAdd;
    private javax.swing.JButton btFind;
    private javax.swing.JButton btNew;
    private javax.swing.JButton btUpdate;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbTaiKhoan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdNhanVien;
    private javax.swing.JRadioButton rdQuanLi;
    private javax.swing.JCheckBox rdVoHieuHoa;
    private javax.swing.JTable tblTaiKhoan;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoten;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JPasswordField txtPassConfirm;
    private javax.swing.JTextField txtTenDN;
    // End of variables declaration//GEN-END:variables
}
