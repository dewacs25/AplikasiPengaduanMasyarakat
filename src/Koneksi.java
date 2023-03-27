/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author haudy
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {

    private static Connection mysqlconfig;

    public static Connection configDB() throws SQLException {
        try {
            String url = "jdbc:mysql://localhost:3306/java_aplikasi_pengaduan"; //url database
            String user = "root"; //user database
            String pass = "dewa"; //password database
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            mysqlconfig = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            System.err.println("koneksi gagal " + e.getMessage()); //perintah menampilkan error
        }
        return mysqlconfig;
    }

    public static void main(String[] args) {
        try {
            Connection c = Koneksi.configDB();
            System.out.println(String.format("Koneksi ke database %s " + "Berhasil", c.getCatalog()));
            Futama utama = new Futama();
            utama.setVisible(true);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
