
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
    private String id_petugas = SessionPetugas.getIdPetugas();
    private String idMasyarakat;
    private String level = SessionPetugas.getLevel();
    private String idPetugas;

    public Fadmin() {
        initComponents();
        setSize(1366, 768);
        super.setResizable(false);
        btnSection();
        iniPanel();
        panelDashboard.setVisible(true);
        load_table1();
        loadTableMasyarakat();
        cekBtn();
        loadTablePetugas();
    }

    private void btnSection() {
        if ("petugas".equals(level)) {
            btnFMasyarakat.setEnabled(false);
            btnDataPetugas.setEnabled(false);
        }
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
        } else if ("proses".equals(status)) {
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
        panelDataMasyarakat.setVisible(false);
        panelDataPetugas.setVisible(false);
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

    public void loadTableMasyarakat() {
        DefaultTableModel model1 = new DefaultTableModel();
        tableMasyarakat.setModel(model1);
        model1.addColumn("No");
        model1.addColumn("id");
        model1.addColumn("Nama"); // Menambahkan kolom "id" ke dalam model tabel
        model1.addColumn("username");
        model1.addColumn("Telp");

        // Menampilkan data database kedalam tabel
        try {
            int no = 1;
            String sql = "SELECT * FROM masyarakat";
            java.sql.Connection conn = (Connection) Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                model1.addRow(new Object[]{no++, res.getString("id_masyarakat"), res.getString("nama"), res.getString("username"), res.getString("telp")});
            }
            tableMasyarakat.setModel(model1);

            // Menyembunyikan kolom "id_pengaduan"
            TableColumnModel tcm = tableMasyarakat.getColumnModel();
            tcm.getColumn(1).setMinWidth(0);
            tcm.getColumn(1).setMaxWidth(0);
            tcm.getColumn(1).setWidth(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadTablePetugas() {
        DefaultTableModel model1 = new DefaultTableModel();
        tablePetugas.setModel(model1);
        model1.addColumn("No");
        model1.addColumn("id");
        model1.addColumn("Nama Petugas"); // Menambahkan kolom "id" ke dalam model tabel
        model1.addColumn("username");
        model1.addColumn("Telp");
        model1.addColumn("Level");

        // Menampilkan data database kedalam tabel
        try {
            int no = 1;
            String sql = "SELECT * FROM petugas";
            java.sql.Connection conn = (Connection) Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                model1.addRow(new Object[]{no++, res.getString("id_petugas"), res.getString("nama_petugas"), res.getString("username"), res.getString("telp"), res.getString("level")});
            }
            tablePetugas.setModel(model1);

            // Menyembunyikan kolom "id_pengaduan"
            TableColumnModel tcm = tablePetugas.getColumnModel();
            tcm.getColumn(1).setMinWidth(0);
            tcm.getColumn(1).setMaxWidth(0);
            tcm.getColumn(1).setWidth(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void cariData(String key) {
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
            String sql = "SELECT pengaduan.*, masyarakat.username FROM pengaduan JOIN masyarakat ON pengaduan.id_masyarakat = masyarakat.id_masyarakat WHERE masyarakat.username LIKE '%" + key + "%'";
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

    private void cariDataMasyarakat(String key2) {
        DefaultTableModel model1 = new DefaultTableModel();
        tableMasyarakat.setModel(model1);
        model1.addColumn("No");
        model1.addColumn("id");
        model1.addColumn("Nama"); // Menambahkan kolom "id" ke dalam model tabel
        model1.addColumn("username");
        model1.addColumn("Telp");

        // Menampilkan data database kedalam tabel
        try {
            int no = 1;
            String sql = "SELECT * FROM  masyarakat WHERE username LIKE '%" + key2 + "%'";
            java.sql.Connection conn = (Connection) Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                model1.addRow(new Object[]{no++, res.getString("id_masyarakat"), res.getString("nama"), res.getString("username"), res.getString("telp")});
            }
            tableMasyarakat.setModel(model1);

            // Menyembunyikan kolom "id_pengaduan"
            TableColumnModel tcm = tableMasyarakat.getColumnModel();
            tcm.getColumn(1).setMinWidth(0);
            tcm.getColumn(1).setMaxWidth(0);
            tcm.getColumn(1).setWidth(0);
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

        panelDataPetugas = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tablePetugas = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        txtNamaPetugas = new javax.swing.JTextField();
        txtTelepon = new javax.swing.JTextField();
        txtUsernamePetugas = new javax.swing.JTextField();
        txtLevel = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        txtPassword = new javax.swing.JPasswordField();
        jButton14 = new javax.swing.JButton();
        panelDataMasyarakat = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tableMasyarakat = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txtNik = new javax.swing.JTextField();
        txtNama = new javax.swing.JTextField();
        txtTelp = new javax.swing.JTextField();
        txtUsername = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btnEditMasyarakat = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        cariMasyarakat = new javax.swing.JTextField();
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
        txtCariLaporan = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        panelDashboard = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        btnFMasyarakat = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btnDataPetugas = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelDataPetugas.setBackground(new java.awt.Color(58, 58, 58));
        panelDataPetugas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Ubuntu", 0, 36)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(254, 254, 254));
        jLabel12.setText("Data Petuga");
        panelDataPetugas.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, -1, -1));

        tablePetugas.setModel(new javax.swing.table.DefaultTableModel(
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
        tablePetugas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablePetugasMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tablePetugas);

        panelDataPetugas.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, -1, 230));
        panelDataPetugas.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 102, 220, 40));
        panelDataPetugas.add(txtNamaPetugas, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 150, 270, 40));

        txtTelepon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTeleponActionPerformed(evt);
            }
        });
        panelDataPetugas.add(txtTelepon, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 240, 270, 40));
        panelDataPetugas.add(txtUsernamePetugas, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 320, 270, 40));

        txtLevel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "admin", "petugas" }));
        panelDataPetugas.add(txtLevel, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 480, 270, 40));

        jLabel13.setForeground(new java.awt.Color(254, 254, 254));
        jLabel13.setText("Nama Petugas :");
        panelDataPetugas.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 120, -1, -1));

        jLabel14.setForeground(new java.awt.Color(254, 254, 254));
        jLabel14.setText("Telepon :");
        panelDataPetugas.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 220, 70, -1));

        jLabel15.setForeground(new java.awt.Color(254, 254, 254));
        jLabel15.setText("Username :");
        panelDataPetugas.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 300, 80, -1));

        jLabel16.setForeground(new java.awt.Color(254, 254, 254));
        jLabel16.setText("Password :");
        panelDataPetugas.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 380, -1, -1));

        jLabel17.setForeground(new java.awt.Color(254, 254, 254));
        jLabel17.setText("Level :");
        panelDataPetugas.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 460, -1, -1));

        jButton8.setText("Add");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        panelDataPetugas.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 560, -1, 40));

        jButton12.setText("Edit");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        panelDataPetugas.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 560, 70, 40));

        jButton13.setText("Delete");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        panelDataPetugas.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 560, -1, 40));
        panelDataPetugas.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 412, 270, 40));

        jButton14.setText("Clear");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        panelDataPetugas.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 150, -1, -1));

        getContentPane().add(panelDataPetugas, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 1070, 750));

        panelDataMasyarakat.setBackground(new java.awt.Color(58, 58, 58));
        panelDataMasyarakat.setForeground(new java.awt.Color(254, 254, 254));
        panelDataMasyarakat.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tableMasyarakat.setModel(new javax.swing.table.DefaultTableModel(
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
        tableMasyarakat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMasyarakatMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tableMasyarakat);

        panelDataMasyarakat.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, -1, 260));

        jLabel7.setFont(new java.awt.Font("Ubuntu", 0, 36)); // NOI18N
        jLabel7.setText("Data Masyarakat");
        panelDataMasyarakat.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));
        panelDataMasyarakat.add(txtNik, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 100, 360, 40));
        panelDataMasyarakat.add(txtNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 190, 360, 40));
        panelDataMasyarakat.add(txtTelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 270, 360, 40));

        txtUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsernameActionPerformed(evt);
            }
        });
        panelDataMasyarakat.add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 350, 360, 40));

        jLabel8.setForeground(new java.awt.Color(254, 254, 254));
        jLabel8.setText("Nik :");
        panelDataMasyarakat.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 70, 60, -1));

        jLabel9.setForeground(new java.awt.Color(254, 254, 254));
        jLabel9.setText("Nama :");
        panelDataMasyarakat.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 160, 60, -1));

        jLabel10.setForeground(new java.awt.Color(254, 254, 254));
        jLabel10.setText("Telp :");
        panelDataMasyarakat.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 240, 40, -1));

        jLabel11.setForeground(new java.awt.Color(254, 254, 254));
        jLabel11.setText("Username :");
        panelDataMasyarakat.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 320, -1, -1));

        btnEditMasyarakat.setBackground(new java.awt.Color(216, 223, 37));
        btnEditMasyarakat.setForeground(new java.awt.Color(254, 254, 254));
        btnEditMasyarakat.setText("Edit");
        btnEditMasyarakat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditMasyarakatActionPerformed(evt);
            }
        });
        panelDataMasyarakat.add(btnEditMasyarakat, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 420, 70, 40));

        jButton10.setBackground(new java.awt.Color(255, 0, 0));
        jButton10.setForeground(new java.awt.Color(254, 254, 254));
        jButton10.setText("Hapus");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        panelDataMasyarakat.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 420, 80, 40));

        jButton9.setText("Clear");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        panelDataMasyarakat.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 420, -1, 40));

        jButton11.setText("Tambah");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        panelDataMasyarakat.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 430, -1, -1));

        cariMasyarakat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cariMasyarakatKeyReleased(evt);
            }
        });
        panelDataMasyarakat.add(cariMasyarakat, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 200, -1));

        getContentPane().add(panelDataMasyarakat, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 1070, 750));

        panelPengaduan.setBackground(new java.awt.Color(58, 58, 58));
        panelPengaduan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tableIsiLaporan.setBackground(new java.awt.Color(58, 58, 45));
        tableIsiLaporan.setForeground(new java.awt.Color(254, 254, 254));
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
        tableIsiLaporan.setEnabled(false);
        tableIsiLaporan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableIsiLaporanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableIsiLaporan);

        panelPengaduan.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, -1, 230));

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

        txtCariLaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariLaporanActionPerformed(evt);
            }
        });
        txtCariLaporan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCariLaporanKeyReleased(evt);
            }
        });
        panelPengaduan.add(txtCariLaporan, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 450, 30));

        jButton7.setText("Clear");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        panelPengaduan.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 590, -1, -1));

        getContentPane().add(panelPengaduan, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 1070, 750));

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

        btnFMasyarakat.setBackground(new java.awt.Color(254, 254, 254));
        btnFMasyarakat.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        btnFMasyarakat.setText("Data Masyarakat");
        btnFMasyarakat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFMasyarakatActionPerformed(evt);
            }
        });
        getContentPane().add(btnFMasyarakat, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 240, 50));

        jButton2.setBackground(new java.awt.Color(254, 254, 254));
        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setText("Pengaduan Masyarakat");
        jButton2.setBorder(null);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 240, 50));

        btnDataPetugas.setBackground(new java.awt.Color(254, 254, 254));
        btnDataPetugas.setText("Data Petugas");
        btnDataPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDataPetugasActionPerformed(evt);
            }
        });
        getContentPane().add(btnDataPetugas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 240, 50));

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
            txt_isitanggapan.setText(null);
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
                    clear();
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
        clear();

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
        clear();
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
        clear();
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
        clear();


    }//GEN-LAST:event_btnAcceptActionPerformed

    private void tableTanggapanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableTanggapanMouseClicked
        int baris = tableTanggapan.rowAtPoint(evt.getPoint());
        idTanggapan = tableTanggapan.getValueAt(baris, 1).toString();

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

    private void txtCariLaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariLaporanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariLaporanActionPerformed

    private void txtCariLaporanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariLaporanKeyReleased
        String key = txtCariLaporan.getText();
        System.out.println(key);

        if (key != "") {
            cariData(key);
        } else {
            load_table1();
        }
    }//GEN-LAST:event_txtCariLaporanKeyReleased

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        clear();

    }//GEN-LAST:event_jButton7ActionPerformed

    private void btnFMasyarakatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFMasyarakatActionPerformed
        iniPanel();
        panelDataMasyarakat.setVisible(true);
    }//GEN-LAST:event_btnFMasyarakatActionPerformed

    private void txtUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsernameActionPerformed

    private void tableMasyarakatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMasyarakatMouseClicked

        int baris = tableMasyarakat.rowAtPoint(evt.getPoint());
        idMasyarakat = tableMasyarakat.getValueAt(baris, 1).toString();
        try {
            String sql = "select * from masyarakat where id_masyarakat = '" + idMasyarakat + "'";
            java.sql.Connection conn = (Connection) Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            if (res.next()) {

                txtNik.setText(res.getString("nik"));
                txtNama.setText(res.getString("nama"));
                txtTelp.setText(res.getString("telp"));
                txtUsername.setText(res.getString("username"));

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }//GEN-LAST:event_tableMasyarakatMouseClicked

    private void btnEditMasyarakatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditMasyarakatActionPerformed
        try {
            String sql = "UPDATE masyarakat SET nik = ?, nama = ?, telp = ?, username = ? WHERE id_masyarakat = ?";
            java.sql.Connection conn = (Connection) Koneksi.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, txtNik.getText());
            pst.setString(2, txtNama.getText());
            pst.setString(3, txtTelp.getText());
            pst.setString(4, txtUsername.getText());
            pst.setString(5, idMasyarakat);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Edit Berhasil");
            loadTableMasyarakat();
            clear();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Perubahan Data Gagal" + e.getMessage());
        }
        load_table1();

    }//GEN-LAST:event_btnEditMasyarakatActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        clear();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        if (idMasyarakat == null) {
            JOptionPane.showMessageDialog(this, "Pilih Data Masyarakat Terlebih Dahulu");
            return;
        }

        try {
            String sql = "SELECT * FROM pengaduan WHERE id_masyarakat = ?";
            Connection conn = Koneksi.configDB();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, idMasyarakat);
            ResultSet res = stmt.executeQuery();

            while (res.next()) {
                String namaGambar2 = res.getString("foto");
                int idPengaduan2 = res.getInt("id_pengaduan");

                try {
                    File fileGambar = new File(lokasiSimpan + File.separator + namaGambar2);
                    if (fileGambar.exists()) {
                        if (fileGambar.delete()) {
                            System.out.println("Gambar Dihapus");
                        } else {
                            System.out.println("Gagal Menghapus Gambar");
                        }
                    } else {
                        System.out.println("Gambar Tidak Ditemukan");
                    }

                    String sqlDeleteTanggapan = "DELETE FROM tanggapan WHERE id_pengaduan = ?";
                    PreparedStatement pstTanggapan = conn.prepareStatement(sqlDeleteTanggapan);
                    pstTanggapan.setInt(1, idPengaduan2);
                    pstTanggapan.executeUpdate();

                    String sqlDeletePengaduan = "DELETE FROM pengaduan WHERE id_pengaduan = ?";
                    PreparedStatement pstPengaduan = conn.prepareStatement(sqlDeletePengaduan);
                    pstPengaduan.setInt(1, idPengaduan2);
                    pstPengaduan.executeUpdate();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Gagal menghapus pengaduan");
                    e.printStackTrace();
                }
            }

            String sqlDeleteMasyarakat = "DELETE FROM masyarakat WHERE id_masyarakat = ?";
            PreparedStatement pstMasyarakat = conn.prepareStatement(sqlDeleteMasyarakat);
            pstMasyarakat.setString(1, idMasyarakat);
            pstMasyarakat.executeUpdate();

            loadTableMasyarakat();
            load_table1();

            JOptionPane.showMessageDialog(this, "Akun User Dan Pengaduan Berhasil Dihapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal");
            e.printStackTrace();
        }


    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        FTambahMasyarakat Tm = new FTambahMasyarakat();
        Tm.setVisible(true);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void cariMasyarakatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cariMasyarakatKeyReleased
        String key2 = cariMasyarakat.getText();
        System.out.println(key2);

        if (key2 != "") {
            cariDataMasyarakat(key2);
        } else {
            loadTableMasyarakat();
        }
    }//GEN-LAST:event_cariMasyarakatKeyReleased

    private void btnDataPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDataPetugasActionPerformed
        iniPanel();
        panelDataPetugas.setVisible(true);
    }//GEN-LAST:event_btnDataPetugasActionPerformed

    private void txtTeleponActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTeleponActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTeleponActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed

        String levelValue = txtLevel.getSelectedItem().toString();

        String nama = txtNamaPetugas.getText();
        String username = txtUsernamePetugas.getText();
        String password = new String(txtPassword.getPassword());
        String telepon = txtTelepon.getText();

        if (nama.isEmpty() || telepon.isEmpty() || username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Lengkapi Data Anda", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!isUsernamePetugasUnique(username)) {
            JOptionPane.showMessageDialog(this, "Username sudah digunakan!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Connection conn = Koneksi.configDB();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO petugas (nama_petugas, username, password, telp, level) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, nama);
            ps.setString(2, username);
            ps.setString(3, password);
            ps.setString(4, telepon);
            ps.setString(5, levelValue);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Tambah Petugas berhasil!", "Success", JOptionPane.INFORMATION_MESSAGE);

            loadTablePetugas();

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error", "Success", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        if (idPetugas == null) {
            JOptionPane.showMessageDialog(this, "Pilih Data Petugas Terlebih dahlu");
            return;
        }

        try {
            String sql = "DELETE FROM petugas WHERE id_petugas=?";
            Connection conn = Koneksi.configDB();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, idPetugas);
            int result = pst.executeUpdate();
            if (result > 0) {
                JOptionPane.showMessageDialog(null, "Data Akun Terhapus");
                loadTablePetugas();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan: " + e.getMessage());
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        clear();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void tablePetugasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablePetugasMouseClicked
        int baris = tablePetugas.rowAtPoint(evt.getPoint());
        idPetugas = tablePetugas.getValueAt(baris, 1).toString();
        try {
            String sql = "select * from petugas where id_petugas = '" + idPetugas + "'";
            java.sql.Connection conn = (Connection) Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            if (res.next()) {

                txtNamaPetugas.setText(res.getString("nama_petugas"));
                txtTelepon.setText(res.getString("telp"));
                txtUsernamePetugas.setText(res.getString("username"));
                txtPassword.setText(res.getString("password"));
                txtLevel.setSelectedItem(res.getString("level"));

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_tablePetugasMouseClicked

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        try {
            String sql = "UPDATE petugas SET nama_petugas = ?, telp = ?, username = ?, password = ?, level = ? WHERE id_petugas = ?";
            java.sql.Connection conn = (Connection) Koneksi.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, txtNamaPetugas.getText());
            pst.setString(2, txtTelepon.getText());
            pst.setString(3, txtUsernamePetugas.getText());
            pst.setString(4, txtPassword.getText());
            pst.setString(5, txtLevel.getSelectedItem().toString());

            pst.setString(6, idPetugas);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Edit Berhasil");
            loadTablePetugas();
            clear();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Perubahan Data Gagal" + e.getMessage());
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private boolean isUsernamePetugasUnique(String username) {
        try {
            Connection conn = Koneksi.configDB();
            PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM petugas WHERE username = ?");
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

    private void clear() {
        isiLaporan.setText(null);
        idPengaduan = null;
        fotoLaporan.setIcon(null);
        status = null;
        cekBtn();

        idMasyarakat = null;
        txtNik.setText(null);
        txtNama.setText(null);
        txtTelp.setText(null);
        txtUsername.setText(null);

        txtNamaPetugas.setText(null);
        txtUsernamePetugas.setText(null);
        txtTelepon.setText(null);
        txtPassword.setText(null);
        idPetugas = null;
        txtLevel.setSelectedItem("admin");

    }

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
    private javax.swing.JButton btnDataPetugas;
    private javax.swing.JButton btnEditMasyarakat;
    private javax.swing.JButton btnFMasyarakat;
    private javax.swing.JToggleButton btnKirimTanggapan;
    private javax.swing.JButton btnR;
    private javax.swing.JButton btnUnverified;
    private javax.swing.JButton btnVerified;
    private javax.swing.JTextField cariMasyarakat;
    private javax.swing.JLabel fotoLaporan;
    private javax.swing.JTextArea isiLaporan;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPanel panelDashboard;
    private javax.swing.JPanel panelDataMasyarakat;
    private javax.swing.JPanel panelDataPetugas;
    private javax.swing.JPanel panelPengaduan;
    private javax.swing.JTable tableIsiLaporan;
    private javax.swing.JTable tableMasyarakat;
    private javax.swing.JTable tablePetugas;
    private javax.swing.JTable tableTanggapan;
    private javax.swing.JTextField txtCariLaporan;
    private javax.swing.JComboBox<String> txtLevel;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNamaPetugas;
    private javax.swing.JTextField txtNik;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtTelepon;
    private javax.swing.JTextField txtTelp;
    private javax.swing.JTextField txtUsername;
    private javax.swing.JTextField txtUsernamePetugas;
    private javax.swing.JTextArea txt_isitanggapan;
    // End of variables declaration//GEN-END:variables
}
