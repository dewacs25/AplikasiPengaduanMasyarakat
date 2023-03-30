
import java.awt.Image;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.sql.ResultSet;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author haudy
 */
public class Fadmin extends javax.swing.JFrame {

    /**
     * Creates new form Fadmin
     */
    private String namaGambar;
    private String lokasiSimpan = "src/imgPengaduan/";
    private String idPengaduan;
    private String idTanggapan;
    private String status;

    public Fadmin() {
        initComponents();
        setSize(1366, 768);
        super.setResizable(false);
        iniPanel();
        panelDashboard.setVisible(true);
        load_table1();
        cekBtn();

    }

    private void cekBtn() {

        btnUnverified.setVisible(false);
        btnAccept.setVisible(false);

        if ("selesai".equals(status)) {
            btnVerified.setVisible(false);
            btnUnverified.setVisible(true);
            btnR.setVisible(false);

        } else if ("ditolak".equals(status)) {
            btnVerified.setVisible(false);
            btnUnverified.setVisible(false);
            btnR.setVisible(false);
            btnAccept.setVisible(true);
        } else if ("prose".equals(status)) {
            btnVerified.setVisible(true);
            btnUnverified.setVisible(false);
            btnAccept.setVisible(false);
            btnR.setVisible(true);
        } else {
            btnVerified.setVisible(true);
            btnR.setVisible(true);
        }

    }

    private void iniPanel() {
        panelPengaduan.setVisible(false);
        panelDashboard.setVisible(false);
    }

    private void load_table1() {
        DefaultTableModel model1 = new DefaultTableModel();
        tableIsiLaporan.setModel(model1);
        model1.addColumn("No");
        model1.addColumn("id"); // Menambahkan kolom "id" ke dalam model tabel
        model1.addColumn("username");
        model1.addColumn("Tanggal Pengiriman");
        model1.addColumn("Status");

        // Menampilkan data database kedalam tabel
        try {
            int no = 1;
            String sql = "SELECT pengaduan.*, masyarakat.username FROM pengaduan JOIN masyarakat ON pengaduan.id_masyarakat = masyarakat.id_masyarakat";
            java.sql.Connection conn = (Connection) Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                model1.addRow(new Object[]{no++, res.getString("id_pengaduan"), res.getString("username"), res.getString("tgl_pengaduan"), res.getString("status")});
            }
            tableIsiLaporan.setModel(model1);

            // Menyembunyikan kolom "id_pengaduan"
            TableColumnModel tcm = tableIsiLaporan.getColumnModel();
            tcm.getColumn(1).setMinWidth(0);
            tcm.getColumn(1).setMaxWidth(0);
            tcm.getColumn(1).setWidth(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void load_table2() {
        DefaultTableModel model1 = new DefaultTableModel();
        tableTanggapan.setModel(model1);
        model1.addColumn("No");
        model1.addColumn("id"); // Menambahkan kolom "id" ke dalam model tabel
        model1.addColumn("Tanggapan");
        model1.addColumn("Tanggal Pengiriman");

        // Menampilkan data database kedalam tabel
        try {
            int no = 1;
            java.sql.Connection conn = (Connection) Koneksi.configDB();
            String sql = "SELECT tanggapan.*, pengaduan.* FROM tanggapan JOIN pengaduan ON tanggapan.id_pengaduan = pengaduan.id_pengaduan WHERE pengaduan.id_pengaduan=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, idPengaduan);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                model1.addRow(new Object[]{no++, rs.getString("id_tanggapan"), rs.getString("tanggapan"), rs.getString("tgl_tanggapan")});
            }
            tableTanggapan.setModel(model1);

            // Menyembunyikan kolom "id_pengaduan"
            TableColumnModel tcm2 = tableTanggapan.getColumnModel();
            tcm2.getColumn(1).setMinWidth(0);
            tcm2.getColumn(1).setMaxWidth(0);
            tcm2.getColumn(1).setWidth(0);
        } catch (SQLException e) {
            e.printStackTrace();
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

        panelPengaduan = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableIsiLaporan = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_isitanggapan = new javax.swing.JTextArea();
        btnKirimTanggapan = new javax.swing.JToggleButton();
        jLabel4 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        isiLaporan = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        fotoLaporan = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableTanggapan = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        btnUnverified = new javax.swing.JButton();
        btnVerified = new javax.swing.JButton();
        btnAccept = new javax.swing.JButton();
        btnR = new javax.swing.JButton();
        panelDashboard = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelPengaduan.setBackground(new java.awt.Color(58, 58, 58));
        panelPengaduan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tableIsiLaporan.setModel(new javax.swing.table.DefaultTableModel(
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
        tableIsiLaporan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableIsiLaporanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableIsiLaporan);

        panelPengaduan.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 70, -1, 323));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Data Pengaduan Masyarakat");
        panelPengaduan.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 35, 323, -1));

        txt_isitanggapan.setColumns(20);
        txt_isitanggapan.setRows(5);
        jScrollPane2.setViewportView(txt_isitanggapan);

        panelPengaduan.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 439, 452, 65));

        btnKirimTanggapan.setText("Kirim ");
        btnKirimTanggapan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKirimTanggapanActionPerformed(evt);
            }
        });
        panelPengaduan.add(btnKirimTanggapan, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 516, 105, -1));

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Berikan Tanggapan :");
        panelPengaduan.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 411, 154, -1));

        jButton6.setText("Hapus Tanggapan");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        panelPengaduan.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 590, -1, -1));

        isiLaporan.setColumns(20);
        isiLaporan.setRows(5);
        jScrollPane3.setViewportView(isiLaporan);

        panelPengaduan.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(538, 145, 440, 93));

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Isi Pengaduan :");
        panelPengaduan.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(538, 101, 106, 32));
        panelPengaduan.add(fotoLaporan, new org.netbeans.lib.awtextra.AbsoluteConstraints(538, 256, 230, 170));

        tableTanggapan.setModel(new javax.swing.table.DefaultTableModel(
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
        tableTanggapan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableTanggapanMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tableTanggapan);

        panelPengaduan.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(538, 485, 382, 100));

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Tanggapan :");
        panelPengaduan.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(538, 457, 110, -1));

        jButton5.setBackground(new java.awt.Color(255, 0, 51));
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Delete");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        panelPengaduan.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(286, 592, 93, -1));

        btnUnverified.setBackground(new java.awt.Color(255, 0, 0));
        btnUnverified.setForeground(new java.awt.Color(255, 245, 245));
        btnUnverified.setText("Unverifeid");
        btnUnverified.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUnverifiedActionPerformed(evt);
            }
        });
        panelPengaduan.add(btnUnverified, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 590, 100, -1));

        btnVerified.setBackground(new java.awt.Color(102, 255, 0));
        btnVerified.setForeground(new java.awt.Color(255, 255, 255));
        btnVerified.setText("Verified");
        btnVerified.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerifiedActionPerformed(evt);
            }
        });
        panelPengaduan.add(btnVerified, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 592, 105, -1));

        btnAccept.setText("Accept");
        btnAccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAcceptActionPerformed(evt);
            }
        });
        panelPengaduan.add(btnAccept, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 590, 100, -1));

        btnR.setBackground(new java.awt.Color(223, 223, 223));
        btnR.setForeground(new java.awt.Color(254, 254, 254));
        btnR.setText("Reject");
        btnR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRActionPerformed(evt);
            }
        });
        panelPengaduan.add(btnR, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 592, 98, -1));

        getContentPane().add(panelPengaduan, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 0, 1070, 760));

        panelDashboard.setBackground(new java.awt.Color(58, 58, 58));
        panelDashboard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton3.setBackground(new java.awt.Color(51, 255, 51));
        jButton3.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Jumlah Pengaduan : ");
        panelDashboard.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, 313, 168));

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Pengaduan Selesai :");
        panelDashboard.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 80, 312, 168));

        getContentPane().add(panelDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 1010, 750));

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setText("Pengaduan Masyarakat");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 240, 50));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setText("Dashboard");
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 240, 50));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Back Office");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/bgAdmin.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 770));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        iniPanel();
        panelPengaduan.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        iniPanel();
        panelDashboard.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tableIsiLaporanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableIsiLaporanMouseClicked
        int baris = tableIsiLaporan.rowAtPoint(evt.getPoint());
        idPengaduan = tableIsiLaporan.getValueAt(baris, 1).toString();
        try {
            String sql = "select * from pengaduan where id_pengaduan = '" + idPengaduan + "'";
            java.sql.Connection conn = (Connection) Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            if (res.next()) {
                load_table2();
                isiLaporan.setText(res.getString(4));
                isiLaporan.setEditable(false);
                namaGambar = res.getString(5);
                File lokasiGambar = new File(lokasiSimpan + namaGambar);
                ImageIcon gambar = new ImageIcon(lokasiGambar.getAbsolutePath());
                Image img = gambar.getImage().getScaledInstance(230, 170, Image.SCALE_SMOOTH);
                gambar = new ImageIcon(img);
                fotoLaporan.setIcon(gambar);
                status = res.getString("status");

                cekBtn();
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_tableIsiLaporanMouseClicked

    private void btnKirimTanggapanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKirimTanggapanActionPerformed
        if (idPengaduan == null) {
            JOptionPane.showMessageDialog(this, "Pilih Data Laporan Terlebihdahulu");
            return;
        }

        if (txt_isitanggapan.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tanggapan Wajib Di isi");
            return;
        }

        String id_petugas = SessionPetugas.getIdPetugas();
        LocalDate tanggal = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String tgl_tanggapan = tanggal.format(dtf);
        String tanggapan = txt_isitanggapan.getText();

        try {
            Connection conn = Koneksi.configDB();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO tanggapan (id_pengaduan, tgl_tanggapan, tanggapan, id_petugas) VALUES (?, ?, ?, ?)");
            ps.setString(1, idPengaduan);
            ps.setString(2, tgl_tanggapan);
            ps.setString(3, tanggapan);
            ps.setString(4, id_petugas);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Tanggapan Berhasil di kirim", "Success", JOptionPane.INFORMATION_MESSAGE);
            load_table2();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnKirimTanggapanActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        if (idPengaduan == null) {
            JOptionPane.showMessageDialog(this, "Pilih Data Laporan Terlebihdahulu");
            return;
        }
        try {
            String sql = "select * from pengaduan where id_pengaduan = '" + idPengaduan + "'";
            java.sql.Connection conn = (Connection) Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            if (res.next()) {
                if (res.getString(6) == "selesai") {
                    JOptionPane.showMessageDialog(this, "Pengaduan Anda Sudah Diterima", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    try {
                        File fileGambar = new File(lokasiSimpan + File.separator + namaGambar);
                        if (fileGambar.exists()) {
                            fileGambar.delete();
                            System.out.println("Gambar Dihapus");
                        } else {
                            System.out.println("Gambar Tidak Ditemukan");
                        }

                        String sqlDelete = "delete from pengaduan where id_pengaduan='" + idPengaduan + "'";
                        java.sql.PreparedStatement pst = conn.prepareStatement(sqlDelete);
                        pst.execute();
                        JOptionPane.showMessageDialog(this, "berhasil di hapus");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e.getMessage());
                    }
                    load_table1();
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }//GEN-LAST:event_jButton5ActionPerformed

    private void btnVerifiedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerifiedActionPerformed

        if (idPengaduan == null) {
            JOptionPane.showMessageDialog(this, "Pilih Data Laporan Terlebihdahulu");
            return;
        }

        try {
            String sql = "UPDATE pengaduan SET status = 'selesai' WHERE id_pengaduan='" + idPengaduan + "'";
            java.sql.Connection conn = (Connection) Koneksi.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Berhasil");
            status = "selesai";
            cekBtn();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Perubahan Data Gagal" + e.getMessage());
        }
        load_table1();


    }//GEN-LAST:event_btnVerifiedActionPerformed

    private void btnUnverifiedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUnverifiedActionPerformed
        if (idPengaduan == null) {
            JOptionPane.showMessageDialog(this, "Pilih Data Laporan Terlebihdahulu");
            return;
        }

        try {
            String sql = "UPDATE pengaduan SET status = 'proses' WHERE id_pengaduan='" + idPengaduan + "'";
            java.sql.Connection conn = (Connection) Koneksi.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Berhasil");
            status = "proses";
            cekBtn();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Perubahan Data Gagal" + e.getMessage());
        }
        load_table1();
    }//GEN-LAST:event_btnUnverifiedActionPerformed

    private void btnRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRActionPerformed
        if (idPengaduan == null) {
            JOptionPane.showMessageDialog(this, "Pilih Data Laporan Terlebihdahulu");
            return;
        }

        try {
            String sql = "UPDATE pengaduan SET status = 'ditolak' WHERE id_pengaduan='" + idPengaduan + "'";
            java.sql.Connection conn = (Connection) Koneksi.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Berhasil Ditolak");
            status = "ditolak";
            cekBtn();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Perubahan Data Gagal" + e.getMessage());
        }
        load_table1();
    }//GEN-LAST:event_btnRActionPerformed

    private void btnAcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAcceptActionPerformed

        if (idPengaduan == null) {
            JOptionPane.showMessageDialog(this, "Pilih Data Laporan Terlebihdahulu");
            return;
        }

        try {
            String sql = "UPDATE pengaduan SET status = 'proses' WHERE id_pengaduan='" + idPengaduan + "'";
            java.sql.Connection conn = (Connection) Koneksi.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Berhasil");
            status = "proses";
            cekBtn();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Perubahan Data Gagal" + e.getMessage());
        }
        load_table1();

    }//GEN-LAST:event_btnAcceptActionPerformed

    private void tableTanggapanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableTanggapanMouseClicked
        int baris = tableTanggapan.rowAtPoint(evt.getPoint());
        idTanggapan = tableIsiLaporan.getValueAt(baris, 1).toString();

    }//GEN-LAST:event_tableTanggapanMouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if (idTanggapan == null) {
            JOptionPane.showMessageDialog(this, "Pilih Data Tanggapan Terlebih dahlu");
            return;
        }
        try {
            String sql = "DELETE FROM tanggapan WHERE id_tanggapan=?";
            Connection conn = Koneksi.configDB();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, idTanggapan);
            int result = pst.executeUpdate();
            if (result > 0) {
                JOptionPane.showMessageDialog(null, "Tanggapan Terhapus");
                load_table2();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan: " + e.getMessage());
        }
    }//GEN-LAST:event_jButton6ActionPerformed

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
            java.util.logging.Logger.getLogger(Fadmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Fadmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Fadmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Fadmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Fadmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAccept;
    private javax.swing.JToggleButton btnKirimTanggapan;
    private javax.swing.JButton btnR;
    private javax.swing.JButton btnUnverified;
    private javax.swing.JButton btnVerified;
    private javax.swing.JLabel fotoLaporan;
    private javax.swing.JTextArea isiLaporan;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel panelDashboard;
    private javax.swing.JPanel panelPengaduan;
    private javax.swing.JTable tableIsiLaporan;
    private javax.swing.JTable tableTanggapan;
    private javax.swing.JTextArea txt_isitanggapan;
    // End of variables declaration//GEN-END:variables
}
