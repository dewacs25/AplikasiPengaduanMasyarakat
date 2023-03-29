
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author adisa
 */
public class FormPengaduanMasyarakat extends javax.swing.JFrame {

    /**
     * Creates new form FormPengaduanMasyarakat
     */
    private String namaGambar;
    private String lokasiSimpan = "src/imgPengaduan/";

    public FormPengaduanMasyarakat() {
        initComponents();
        setSize(629, 517);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txt_isiLaporan = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        btn_kirimLaporan = new javax.swing.JButton();
        btn_uploadgambar = new javax.swing.JButton();
        txt_namaFile = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_isiLaporan.setColumns(20);
        txt_isiLaporan.setRows(5);
        jScrollPane1.setViewportView(txt_isiLaporan);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 550, 270));

        jButton2.setBackground(new java.awt.Color(255, 0, 51));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Batal");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 460, 80, 40));

        btn_kirimLaporan.setBackground(new java.awt.Color(51, 255, 0));
        btn_kirimLaporan.setForeground(new java.awt.Color(255, 255, 255));
        btn_kirimLaporan.setText("Kirim");
        btn_kirimLaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_kirimLaporanActionPerformed(evt);
            }
        });
        getContentPane().add(btn_kirimLaporan, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 460, 80, 40));

        btn_uploadgambar.setBackground(new java.awt.Color(217, 217, 217));
        btn_uploadgambar.setText("Upload Gambar");
        btn_uploadgambar.setBorder(null);
        btn_uploadgambar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_uploadgambarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_uploadgambar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 410, 160, 50));

        txt_namaFile.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        txt_namaFile.setForeground(new java.awt.Color(254, 254, 254));
        getContentPane().add(txt_namaFile, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 430, 100, 20));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/FormPengaduan.png"))); // NOI18N
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 255, 51), 5));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 650, 520));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btn_kirimLaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_kirimLaporanActionPerformed
        String id_masyarakat = SessionMasyarakat.getIdMasyarakat();

        LocalDate tanggal = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String tgl_pengaduan = tanggal.format(dtf);

        String isi_pengaduan = txt_isiLaporan.getText();
        
        String status = "proses";

        try {
            Connection conn = Koneksi.configDB();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO pengaduan (tgl_pengaduan, id_masyarakat, isi_pengaduan, foto, status) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, tgl_pengaduan);
            ps.setString(2, id_masyarakat);
            ps.setString(3, isi_pengaduan);
            ps.setString(4, namaGambar);
            ps.setString(5, status);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Pengaduan Berhaerkirim Dan Sedang Di Proses !", "Success", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

    }//GEN-LAST:event_btn_kirimLaporanActionPerformed

    private void btn_uploadgambarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_uploadgambarActionPerformed

        JFileChooser fc = new JFileChooser();
        int result = fc.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fc.getSelectedFile();
            String namaFile = selectedFile.getName();
            txt_namaFile.setText(namaFile);
            
            LocalDate tanggal = LocalDate.now();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String tgl = tanggal.format(dtf);
            
            String fileExtension = selectedFile.getName().substring(selectedFile.getName().lastIndexOf("."));
            String randomFileName = tgl + UUID.randomUUID().toString() + fileExtension;
            
            namaGambar = randomFileName;

            // tentukan lokasi penyimpanan gambar
            File fileSimpan = new File(lokasiSimpan + namaGambar);

            // copy file gambar ke lokasi penyimpanan
            try {
                Files.copy(selectedFile.toPath(), fileSimpan.toPath(), StandardCopyOption.REPLACE_EXISTING);
                // tampilkan gambar pada komponen JLabel atau JPanel
                // contoh: jLabelGambar.setIcon(new ImageIcon(fileSimpan.getAbsolutePath()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
    }//GEN-LAST:event_btn_uploadgambarActionPerformed

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
            java.util.logging.Logger.getLogger(FormPengaduanMasyarakat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormPengaduanMasyarakat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormPengaduanMasyarakat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormPengaduanMasyarakat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormPengaduanMasyarakat().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_kirimLaporan;
    private javax.swing.JButton btn_uploadgambar;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txt_isiLaporan;
    private javax.swing.JLabel txt_namaFile;
    // End of variables declaration//GEN-END:variables
}