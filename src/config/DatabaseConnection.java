package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * DatabaseConnection - Kelas untuk mengelola koneksi ke database MySQL
 * Menggunakan pattern Singleton untuk memastikan hanya ada satu instance koneksi
 */
public class DatabaseConnection {
    private static Connection connection = null;
    
    // Database configuration
    private static final String DB_URL = "jdbc:mysql://localhost:3306/pendaftaran_siswa_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";
    
    /**
     * Method untuk mendapatkan koneksi database
     * @return Connection object atau null jika gagal
     */
    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Load MySQL JDBC Driver
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                // Establish connection
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                System.out.println("Koneksi database berhasil!");
                
            } catch (ClassNotFoundException e) {
                JOptionPane.showMessageDialog(null, 
                    "Driver MySQL tidak ditemukan: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, 
                    "Gagal terkoneksi ke database: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
        return connection;
    }
    
    /**
     * Method untuk menutup koneksi database
     */
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("Koneksi database ditutup!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Method untuk test koneksi database
     * @return true jika koneksi berhasil, false jika gagal
     */
    public static boolean testConnection() {
        Connection conn = getConnection();
        return conn != null;
    }
}
