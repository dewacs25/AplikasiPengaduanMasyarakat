
import java.awt.Image;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author haudy
 */
public class HistoryPengaduan extends javax.swing.JFrame {

    /**
     * Creates new form HistoryPengaduan
     */
    private String namaGambar;
    private String lokasiSimpan = "src/imgPengaduan/";
    private String idPengaduan;

    public HistoryPengaduan() {
        initComponents();
        setSize(911, 686);
        load_table1();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fotoLaporan = new javax.swing.JLabel();
        btnHapusPengaduan = new javax.swing.JToggleButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        isiLaporan = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableHistory = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(fotoLaporan, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 420, 230, 170));

        btnHapusPengaduan.setBackground(new java.awt.Color(255, 0, 51));
        btnHapusPengaduan.setForeground(new java.awt.Color(255, 255, 255));
        btnHapusPengaduan.setText("Hapus");
        btnHapusPengaduan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusPengaduanActionPerformed(evt);
            }
        });
        getContentPane().add(btnHapusPengaduan, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 620, -1, -1));

        isiLaporan.setColumns(20);
        isiLaporan.setRows(5);
        jScrollPane2.setViewportView(isiLaporan);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 140, 400, 270));

        tableHistory.setModel(new javax.swing.table.DefaultTableModel(
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
        tableHistory.setCellSelectionEnabled(true);
        tableHistory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableHistoryMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableHistory);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 330, 450));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("x");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 20, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/historyPengaduan.png"))); // NOI18N
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 255, 51), 5));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 910, 680));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        this.dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void tableHistoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableHistoryMouseClicked
        int baris = tableHistory.rowAtPoint(evt.getPoint());
        idPengaduan = tableHistory.getValueAt(baris, 1).toString();
        try {
            String sql = "select * from pengaduan where id_pengaduan = '" + idPengaduan + "'";
            java.sql.Connection conn = (Connection) Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            if (res.next()) {
                isiLaporan.setText(res.getString(4));
                namaGambar = res.getString(5);
                File lokasiGambar = new File(lokasiSimpan + namaGambar);
                ImageIcon gambar = new ImageIcon(lokasiGambar.getAbsolutePath());
                Image img = gambar.getImage().getScaledInstance(230, 170, Image.SCALE_SMOOTH);
                gambar = new ImageIcon(img);
                fotoLaporan.setIcon(gambar);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
//        txtnip.setText(nip);
//        String nama = tableHistory.getValueAt(baris, 2).toString();
//        txtnama.setText(nama);
    }//GEN-LAST:event_tableHistoryMouseClicked

    private void btnHapusPengaduanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusPengaduanActionPerformed
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
    }//GEN-LAST:event_btnHapusPengaduanActionPerformed

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
            java.util.logging.Logger.getLogger(HistoryPengaduan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HistoryPengaduan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HistoryPengaduan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HistoryPengaduan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HistoryPengaduan().setVisible(true);
            }
        });
    }

    private void load_table1() {
        DefaultTableModel model1 = new DefaultTableModel();
        tableHistory.setModel(model1);
        model1.addColumn("No");
        model1.addColumn("id"); // Menambahkan kolom "id" ke dalam model tabel
        model1.addColumn("Tanggal Pengiriman");
        model1.addColumn("Status");

// Menampilkan data database kedalam tabel
        try {
            String idMasyarakat = SessionMasyarakat.getIdMasyarakat();

            int no = 1;
            String sql = "select * from pengaduan where id_masyarakat = '" + idMasyarakat + "'";
            java.sql.Connection conn = (Connection) Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                model1.addRow(new Object[]{no++, res.getString(1), res.getString(2), res.getString(6)});
            }
            tableHistory.setModel(model1);

            // Menyembunyikan kolom "id"
            TableColumnModel tcm = tableHistory.getColumnModel();
            tcm.getColumn(1).setMinWidth(0);
            tcm.getColumn(1).setMaxWidth(0);
            tcm.getColumn(1).setWidth(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnHapusPengaduan;
    private javax.swing.JLabel fotoLaporan;
    private javax.swing.JTextArea isiLaporan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tableHistory;
    // End of variables declaration//GEN-END:variables
}
