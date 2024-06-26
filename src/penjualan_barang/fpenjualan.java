/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package penjualan_barang;

import java.awt.Desktop;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class fpenjualan extends javax.swing.JFrame {
    public long total;
    public long bayar;
    public long kembali;
    public Statement st;
    Connection cn = Koneksi.getKoneksi();
    private DefaultTableModel model;
    /**
     * Creates new form fpenjualan
     */
    public fpenjualan() {
        initComponents();
        model = new DefaultTableModel();
        tpenjualan.setModel(model);
        model.addColumn("ID");
        model.addColumn("kode barang");
        model.addColumn("nama barang");
        model.addColumn("harga satuan");
        model.addColumn("jumlah beli");
        model.addColumn("harga");
        loadData();
        nofaktur();
        tampilpilih();
    }
    public void FilterHuruf(KeyEvent a){
        if(Character.isDigit(a.getKeyChar())){
            a.consume();
            JOptionPane.showMessageDialog(null, "masukan huruf saja!", "peringatan", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void FilterAngka(KeyEvent a){
        if(Character.isAlphabetic(a.getKeyChar())){
            a.consume();
            JOptionPane.showMessageDialog(null, "masukan angka saja!", "peringatan", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public final void loadData() {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try {
            Connection c = Koneksi.getKoneksi();
            Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM tbl_hitung_jual";
            ResultSet r = s.executeQuery(sql);
            while (r.next()) {
                Object[] o = new Object[6];
                o[0] = r.getString("id_hitung");
                o[1] = r.getString("kd_barang");
                o[2] = r.getString("nama_barang");
                o[3] = r.getString("hsatuan");
                o[4] = r.getString("jumlah_jual");
                o[5] = r.getString("harga");
                model.addRow(o);
            }
            r.close();
            s.close();
        } catch (SQLException e) {
           System.out.println("Terjadi Error: " + e.getMessage());
        }
    }
    
     private void tampilpilih() {
        try {
            Connection c = Koneksi.getKoneksi();
            Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT nama_barang FROM tbl_barang WHERE jumlah_barang !='0'";
            ResultSet r = s.executeQuery(sql);
            while (r.next()) {
                    namabarang.addItem(r.getString("nama_barang"));
                }
                r.last();
                int jumlahdata = r.getRow();
                r.first();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
     
     private void nofaktur() {
        try {
            Connection c = Koneksi.getKoneksi();
            Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM tbl_penjualan ORDER by nofaktur desc";
            ResultSet r = s.executeQuery
            (sql);

            if (r.next()) {
                String nofak = r.getString("nofaktur").substring(1);
                String AN = "" + (Integer.parseInt

                (nofak) + 1);

                String Nol = "";
                switch (AN.length()) {
                    case 1:
                        Nol = "000";
                        break;
                    case 2:
                        Nol = "00";
                        break;
                    case 3:
                        Nol = "0";
                        break;
                    case 4:
                        Nol = "";
                        break;
                    default:
                        break;
                }
                nofaktur.setText("F" + Nol + AN);
            } else {
                nofaktur.setText("F0001");
            }
    }   catch (SQLException e) {
            System.out.println(e.toString());
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
        jLabel1 = new javax.swing.JLabel();
        btn_kembali = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        nofaktur = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        namabarang = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        kodebarang = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        hargasatuan = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jumlahjual = new javax.swing.JTextField();
        jumlahhitung = new javax.swing.JTextField();
        hitung = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tpenjualan = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        btn_total = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txt_bayar = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_kembalian = new javax.swing.JTextField();
        selesai = new javax.swing.JButton();
        cetak = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        tambah = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(24, 154, 180));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Penjualan");

        btn_kembali.setText("Kembali");
        btn_kembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_kembaliActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_kembali)
                .addGap(19, 19, 19))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_kembali)
                    .addComponent(jLabel1))
                .addGap(112, 112, 112))
        );

        jPanel3.setBackground(new java.awt.Color(117, 230, 218));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("No.Faktur");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Nama Barang");

        namabarang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kulkas", "Mesin Cuci", "TV", "AC" }));
        namabarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namabarangActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Kode Barang");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Harga Satuan");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Jumlah Jual");

        hitung.setText("Hitung");
        hitung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hitungActionPerformed(evt);
            }
        });

        tpenjualan.setModel(new javax.swing.table.DefaultTableModel(
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
        tpenjualan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tpenjualanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tpenjualan);

        jPanel2.setBackground(new java.awt.Color(5, 68, 94));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Rp.");

        btn_total.setText("Total");
        btn_total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_totalActionPerformed(evt);
            }
        });

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Bayar");

        txt_bayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_bayarKeyReleased(evt);
            }
        });

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Kembalian");

        selesai.setText("Selesai Transaksi");
        selesai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selesaiActionPerformed(evt);
            }
        });

        cetak.setText("Cetak");
        cetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cetakActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(53, 53, 53)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btn_total, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addGap(67, 67, 67)
                                    .addComponent(jLabel10))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addGap(22, 22, 22)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel9)
                                .addComponent(jLabel8))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt_bayar)
                                .addComponent(txt_kembalian, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(selesai, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(cetak, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addComponent(btn_total)
                .addGap(60, 60, 60)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txt_bayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txt_kembalian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addComponent(selesai)
                .addGap(44, 44, 44)
                .addComponent(cetak)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tambah.setText("TAMBAH");
        tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jumlahhitung, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                                    .addComponent(nofaktur)
                                    .addComponent(namabarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(kodebarang)
                                    .addComponent(hargasatuan)
                                    .addComponent(jumlahjual))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(hitung))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(nofaktur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(namabarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(kodebarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(hargasatuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jumlahjual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jumlahhitung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hitung))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                        .addComponent(tambah)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_kembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_kembaliActionPerformed
        // TODO add your handling code here:
        fmenu fb = new fmenu();
        fb.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btn_kembaliActionPerformed

    private void hitungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hitungActionPerformed
        // TODO add your handling code here:
        if(nofaktur.getText().equals("") ||kodebarang.getText().equals("") || namabarang.getSelectedItem().equals("")|| hargasatuan.getText().equals("")|| jumlahjual.getText().equals("")){
           JOptionPane.showMessageDialog(null, "LENGKAPI DATA !", "Aplikasi Penjualan", JOptionPane.INFORMATION_MESSAGE);

        } else {
            String a = jumlahjual.getText();
            int aa = Integer.parseInt(a);

            String b = hargasatuan.getText();
            int bb = Integer.parseInt(b);
            if(aa > bb){
                JOptionPane.showMessageDialog(null, "jumlah melebihi stok", "Aplikasi Penjualan",
               JOptionPane.INFORMATION_MESSAGE);
                    jumlahjual.setText("");
                }else{

                if(jumlahjual.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "ISI JUMLAH JUAL !");
                } else {
                    int jumlah, harga, total;

                    jumlah = Integer.parseInt(jumlahjual.getText());
                    harga = Integer.parseInt(hargasatuan.getText());
                    total = jumlah * harga;


                    jumlahhitung.setText(Integer.toString(total));
                }
            }
         }
    }//GEN-LAST:event_hitungActionPerformed

    private void tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahActionPerformed
        // TODO add your handling code here:
        if(nofaktur.getText().equals("") ||kodebarang.getText().equals("") || namabarang.getSelectedItem().equals("")|| hargasatuan.getText().equals("")|| jumlahjual.getText().equals("")){
           JOptionPane.showMessageDialog(null, "LENGKAPI DATA !", "Aplikasi Penjualan", JOptionPane.INFORMATION_MESSAGE);

        }else{
            String kdbarangg = kodebarang.getText();
            String pilihbarangg = (String)namabarang.getSelectedItem();
            String hsatuann = hargasatuan.getText();
            String tjumlahh = jumlahjual.getText();
            String totall = jumlahhitung.getText();

            try {
                Connection c = Koneksi.getKoneksi();

                String sql = "INSERT INTO tbl_hitung_jual VALUES (?, ?, ?, ?, ?, ?)";


                PreparedStatement p = c.prepareStatement(sql);
                p.setString(1, null);
                p.setString(2, kdbarangg);
                p.setString(3, pilihbarangg);
                p.setString(4, hsatuann);
                p.setString(5, tjumlahh);
                p.setString(6, totall);

                p.executeUpdate();
                p.close();
            } catch (SQLException e) {
                System.out.println("Terjadi Error: " + e.getMessage());
            } finally {
                nofaktur();
                kodebarang.setText("");
                namabarang.setSelectedItem("");
                hargasatuan.setText("");
                jumlahhitung.setText("");
                jumlahjual.setText("");
            
                JOptionPane.showMessageDialog(null, "Data berhasil disimpan", "Aplikasi Penjualan", JOptionPane.INFORMATION_MESSAGE);
                loadData();
            }
        }
    }//GEN-LAST:event_tambahActionPerformed

    private void tpenjualanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tpenjualanMouseClicked
        // TODO add your handling code here:
        int jawaban;
        if ((jawaban = JOptionPane.showConfirmDialog(null,"Yakin batal?", "Konfirmasi", JOptionPane.YES_NO_OPTION)) == 0) {
        
            try {
                int i = tpenjualan.getSelectedRow();
                if (i == -1) {
                return;
            }
                String id = (String) model.getValueAt(i, 0);

                st = cn.createStatement();
                st.executeUpdate("delete from tbl_hitung_jual where id_hitung = '"+id+ "'");
                nofaktur();
                loadData();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_tpenjualanMouseClicked

    private void btn_totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_totalActionPerformed
        // TODO add your handling code here:
        try {
            Connection c = Koneksi.getKoneksi();
            Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT SUM(`harga`) AS total FROM tbl_hitung_jual";
            ResultSet r = s.executeQuery(sql);

            while (r.next()) {
                jLabel10.setText(r.getString("total"));
            }
            r.close();
            s.close();
        } catch (SQLException e) {
            System.out.println("Terjadi Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btn_totalActionPerformed

    private void selesaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selesaiActionPerformed
        // TODO add your handling code here:
        if(txt_bayar.getText().equals("") ||txt_kembalian.getText().equals("")){
            JOptionPane.showMessageDialog(null, "LENGKAPI DATA !", "Aplikasi Penjualan",
           JOptionPane.INFORMATION_MESSAGE);

            }else{
            String a = txt_kembalian.getText();
            int ab = Integer.parseInt(String.valueOf(txt_kembalian.getText()));
            if(ab < 0){
            JOptionPane.showMessageDialog(null, "Uang anda kurang", "Aplikasi Penjualan",
           JOptionPane.INFORMATION_MESSAGE);
            txt_bayar.setText("");
            txt_kembalian.setText("");
            }else{
            try {
            Connection c = Koneksi.getKoneksi();
            Statement s = c.createStatement();
            String sql = "SELECT * FROM tbl_hitung_jual";
            ResultSet r = s.executeQuery(sql);
            while (r.next()) {
            long millis=System.currentTimeMillis();
            java.sql.Date date=new java.sql.Date(millis);
            System.out.println(date);
            String tgl = date.toString();
            String sqla = "INSERT INTO tbl_penjualan VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement p = c.prepareStatement(sqla);
            p.setString(1, nofaktur.getText());
            p.setString(2, r.getString("kd_barang"));
            p.setString(3, r.getString("nama_barang"));
            p.setString(4, r.getString("hsatuan"));
            p.setString(5, r.getString("jumlah_jual"));
            p.setString(6, r.getString("harga"));
            p.setString(7, txt_bayar.getText());
            p.setString(8, txt_kembalian.getText());
            p.setString(9, tgl);

            p.executeUpdate();
            p.close();


            }
            r.close();
            s.close();
            } catch (SQLException e) {
            System.out.println("Terjadi Error: " + e.getMessage());
            }finally{
            try {
            String sqla ="TRUNCATE tbl_hitung_jual";
            java.sql.Connection conn=(Connection)Koneksi.getKoneksi();
            java.sql.PreparedStatement pst=conn.prepareStatement(sqla);
            pst.execute();
            JOptionPane.showMessageDialog(null, "TRANSAKSI SELESAI", "Aplikasi Penjualan",
           JOptionPane.INFORMATION_MESSAGE);
            loadData();
            nofaktur.setText(nofaktur.getText());
            txt_bayar.setText("");
            txt_kembalian.setText("");
            jLabel10.setText("");
            nofaktur();
            selesai.setEnabled(true);
            } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            }
            }
            }
            }
    }//GEN-LAST:event_selesaiActionPerformed

    private void cetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cetakActionPerformed
        // TODO add your handling code here:
        try{
            Desktop.getDesktop().browse(new
           URL("http://localhost/PenjualanBarang/invoice.php?lap&fk="+nofaktur.getText()+"").toURI());
            } catch (Exception e){
            System.out.println(e);
        }
    }//GEN-LAST:event_cetakActionPerformed

    private void txt_bayarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_bayarKeyReleased
        // TODO add your handling code here:
        bayar = Integer.parseInt(String.valueOf(txt_bayar.getText()));
        total = Integer.parseInt(String.valueOf(jLabel10.getText()));
        kembali = bayar - total;

        txt_kembalian.setText(Long.toString(kembali));
    }//GEN-LAST:event_txt_bayarKeyReleased

    private void namabarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namabarangActionPerformed
        // TODO add your handling code here:
        if (namabarang.getSelectedItem().equals("pilih barang")){
            kodebarang.setText("");
            hargasatuan.setText("");
        }else{
            try {
                Connection c = Koneksi.getKoneksi();
                Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                String sql = "SELECT kd_barang, jumlah_barang FROM tbl_barang WHERE nama_barang ='" +namabarang.getSelectedItem() + "'";
                ResultSet r = s.executeQuery(sql);
                while (r.next()) {
                    kodebarang.setText(r.getString("kd_barang"));
                    jumlahjual.setText(r.getString("jumlah_barang"));
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            
            try {
                Connection c = Koneksi.getKoneksi();
                Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                String sql = "SELECT harga_jual FROM tbl_barang WHERE nama_barang ='" +namabarang.getSelectedItem() + "'";
                ResultSet r = s.executeQuery(sql);
                while (r.next()) {
                    hargasatuan.setText(r.getString("harga_jual"));
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_namabarangActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(fpenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(fpenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(fpenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(fpenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new fpenjualan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_kembali;
    private javax.swing.JButton btn_total;
    private javax.swing.JButton cetak;
    private javax.swing.JTextField hargasatuan;
    private javax.swing.JButton hitung;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jumlahhitung;
    private javax.swing.JTextField jumlahjual;
    private javax.swing.JTextField kodebarang;
    private javax.swing.JComboBox<String> namabarang;
    private javax.swing.JTextField nofaktur;
    private javax.swing.JButton selesai;
    private javax.swing.JButton tambah;
    private javax.swing.JTable tpenjualan;
    private javax.swing.JTextField txt_bayar;
    private javax.swing.JTextField txt_kembalian;
    // End of variables declaration//GEN-END:variables
}
