/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author haudy
 */
public class SessionPetugas {

    private static String id_petugas;

    public static String getIdPetugas() {
        return id_petugas;
    }

    public static void setIdPetugas(String idPetugas) {
        SessionPetugas.id_petugas = idPetugas;
    }

    private static String level;

    public static String getLevel() {
        return level;
    }

    public static void setLevel(String level) {
        SessionPetugas.level = level;
    }
}
