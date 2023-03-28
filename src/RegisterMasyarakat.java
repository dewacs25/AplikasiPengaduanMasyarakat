
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import javax.swing.SwingUtilities;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author haudy
 */
public class RegisterMasyarakat extends javax.swing.JFrame {

    /**
     * Creates new form RegisterMasyarakat
     */
    public RegisterMasyarakat() {
        initComponents();
        centerFrame();
        setSize(852, 664);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        txt_nik = new javax.swing.JTextField();
        txt_nama = new javax.swing.JTextField();
        txt_username = new javax.swing.JTextField();
        txt_telp = new javax.swing.JTextField();
        btn_register = new javax.swing.JButton();
        txt_password = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Ubuntu Light", 1, 14)); // NOI18N
        jButton1.setText("x");
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 30, 40));

        txt_nik.setBackground(new java.awt.Color(217, 217, 217));
        txt_nik.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_nik.setBorder(null);
        txt_nik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nikActionPerformed(evt);
            }
        });
        getContentPane().add(txt_nik, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, 310, 30));

        txt_nama.setBackground(new java.awt.Color(217, 217, 217));
        txt_nama.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_nama.setBorder(null);
        txt_nama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_namaActionPerformed(evt);
            }
        });
        getContentPane().add(txt_nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, 310, 30));

        txt_username.setBackground(new java.awt.Color(217, 217, 217));
        txt_username.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_username.setBorder(null);
        txt_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_usernameActionPerformed(evt);
            }
        });
        getContentPane().add(txt_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 410, 310, 30));

        txt_telp.setBackground(new java.awt.Color(217, 217, 217));
        txt_telp.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_telp.setBorder(null);
        txt_telp.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        getContentPane().add(txt_telp, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 320, 310, 30));

        btn_register.setBackground(new java.awt.Color(51, 153, 255));
        btn_register.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btn_register.setForeground(new java.awt.Color(255, 255, 255));
        btn_register.setText("Register Akun");
        btn_register.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_registerActionPerformed(evt);
            }
        });
        getContentPane().add(btn_register, new org.netbeans.lib.awtextra.AbsoluteConstraints(86, 563, 270, 50));

        txt_password.setBackground(new java.awt.Color(217, 217, 217));
        txt_password.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_password.setBorder(null);
        txt_password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_passwordActionPerformed(evt);
            }
        });
        getContentPane().add(txt_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 500, 310, 30));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/RegisterMasyarakat.png"))); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 870, 670));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Window window = SwingUtilities.windowForComponent(jButton1);
        if (window instanceof RegisterMasyarakat) {
            RegisterMasyarakat RegisterM = (RegisterMasyarakat) window;
            RegisterM.dispose();
        }
        LoginMasyarakat loginM = new LoginMasyarakat();
        loginM.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_registerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_registerActionPerformed

        String nik = txt_nik.getText();
        String nama = txt_nama.getText();
        String username = txt_username.getText();
        String password = new String(txt_password.getPassword());
        String telepon = txt_telp.getText();

        if (nik.isEmpty() || nama.isEmpty() || telepon.isEmpty() || username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Lengkapi Data Anda", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!isNikUnique(nik)) {
            JOptionPane.showMessageDialog(this, "NIK sudah digunakan!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!isUsernameUnique(username)) {
            JOptionPane.showMessageDialog(this, "Username sudah digunakan!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Masukkan data registrasi baru ke tabel masyarakat di MySQL
        try {
            Connection conn = Koneksi.configDB();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO masyarakat (nik, nama, username, password, telp) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, nik);
            ps.setString(2, nama);
            ps.setString(3, username);
            ps.setString(4, password);
            ps.setString(5, telepon);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Registrasi berhasil!", "Success", JOptionPane.INFORMATION_MESSAGE);
            Window window = SwingUtilities.windowForComponent(btn_register);
            if (window instanceof RegisterMasyarakat) {
                RegisterMasyarakat RegisterM = (RegisterMasyarakat) window;
                RegisterM.dispose();
            }
            LoginMasyarakat loginM = new LoginMasyarakat();
            loginM.setVisible(true);

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btn_registerActionPerformed

    private void txt_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_usernameActionPerformed

    private void txt_passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_passwordActionPerformed

    private void txt_namaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_namaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_namaActionPerformed

    private void txt_nikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nikActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nikActionPerformed

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
            java.util.logging.Logger.getLogger(RegisterMasyarakat.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegisterMasyarakat.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegisterMasyarakat.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegisterMasyarakat.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegisterMasyarakat().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_register;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JTextField txt_nik;
    private javax.swing.JPasswordField txt_password;
    private javax.swing.JTextField txt_telp;
    private javax.swing.JTextField txt_username;
    // End of variables declaration//GEN-END:variables
private void centerFrame() {
        // Mendapatkan ukuran layar
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        // Mendapatkan ukuran JFrame
        int w = this.getSize().width;
        int h = this.getSize().height;

        // Menghitung posisi x dan y untuk JFrame agar berada di tengah layar
        int x = (dim.width - w) / 2;
        int y = (dim.height - h) / 2;

        // Menentukan posisi JFrame
        this.setLocation(x, y);
    }

    private boolean isNikUnique(String nik) {
        try {
            Connection conn = Koneksi.configDB();
            PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM masyarakat WHERE nik = ?");
            ps.setString(1, nik);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) == 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private boolean isUsernameUnique(String username) {
        try {
            Connection conn = Koneksi.configDB();
            PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM masyarakat WHERE username = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) == 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
