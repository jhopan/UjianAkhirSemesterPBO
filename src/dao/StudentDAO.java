package dao;

import config.DatabaseConnection;
import model.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * StudentDAO - Data Access Object untuk operasi CRUD pada tabel pendaftaran_siswa
 * Menggunakan konsep abstraction dan encapsulation
 */
public class StudentDAO {
    private Connection connection;
    
    public StudentDAO() {
        this.connection = DatabaseConnection.getConnection();
    }
    
    /**
     * Generate nomor pendaftaran otomatis dengan format: thn-bln-tgl-urutanpendaftaran
     * Contoh: 1712120001
     * @return Nomor pendaftaran yang di-generate
     */
    public String generateNoPendaftaran() {
        String noPendaftaran = "";
        Calendar calendar = Calendar.getInstance();
        
        // Format: YY-MM-DD
        String year = String.format("%02d", calendar.get(Calendar.YEAR) % 100);
        String month = String.format("%02d", calendar.get(Calendar.MONTH) + 1);
        String day = String.format("%02d", calendar.get(Calendar.DAY_OF_MONTH));
        
        String datePrefix = year + month + day;
        
        try {
            String query = "SELECT MAX(CAST(SUBSTRING(no_pendaftaran, 7, 4) AS UNSIGNED)) as max_urut " +
                          "FROM pendaftaran_siswa " +
                          "WHERE no_pendaftaran LIKE ?";
            
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, datePrefix + "%");
            ResultSet rs = stmt.executeQuery();
            
            int urutan = 1;
            if (rs.next()) {
                int maxUrut = rs.getInt("max_urut");
                if (maxUrut > 0) {
                    urutan = maxUrut + 1;
                }
            }
            
            noPendaftaran = datePrefix + String.format("%04d", urutan);
            
            rs.close();
            stmt.close();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "Error generate nomor pendaftaran: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        
        return noPendaftaran;
    }
    
    /**
     * Create - Menambahkan data siswa baru ke database
     * @param student Object student yang akan ditambahkan
     * @return true jika berhasil, false jika gagal
     */
    public boolean createStudent(Student student) {
        String query = "INSERT INTO pendaftaran_siswa (no_pendaftaran, nama_lengkap, tempat_lahir, " +
                      "tgl_lahir, jns_kelamin, alamat, asal_sekolah, nilai_uan, nama_ortu_wali, no_telp) " +
                      "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, student.getNoPendaftaran());
            stmt.setString(2, student.getNamaLengkap());
            stmt.setString(3, student.getTempatLahir());
            stmt.setDate(4, new java.sql.Date(student.getTglLahir().getTime()));
            stmt.setString(5, student.getJnsKelamin());
            stmt.setString(6, student.getAlamat());
            stmt.setString(7, student.getAsalSekolah());
            stmt.setFloat(8, student.getNilaiUan());
            stmt.setString(9, student.getNamaOrtuWali());
            stmt.setString(10, student.getNoTelp());
            
            int result = stmt.executeUpdate();
            stmt.close();
            
            return result > 0;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "Error menambahkan data: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Retrieve - Mengambil semua data siswa dari database
     * @return List of Student objects
     */
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM pendaftaran_siswa ORDER BY no_pendaftaran DESC";
        
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                Student student = new Student();
                student.setNoPendaftaran(rs.getString("no_pendaftaran"));
                student.setNamaLengkap(rs.getString("nama_lengkap"));
                student.setTempatLahir(rs.getString("tempat_lahir"));
                student.setTglLahir(rs.getDate("tgl_lahir"));
                student.setJnsKelamin(rs.getString("jns_kelamin"));
                student.setAlamat(rs.getString("alamat"));
                student.setAsalSekolah(rs.getString("asal_sekolah"));
                student.setNilaiUan(rs.getFloat("nilai_uan"));
                student.setNamaOrtuWali(rs.getString("nama_ortu_wali"));
                student.setNoTelp(rs.getString("no_telp"));
                
                students.add(student);
            }
            
            rs.close();
            stmt.close();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "Error mengambil data: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        
        return students;
    }
    
    /**
     * Retrieve by ID - Mengambil data siswa berdasarkan nomor pendaftaran
     * @param noPendaftaran Nomor pendaftaran siswa
     * @return Student object atau null jika tidak ditemukan
     */
    public Student getStudentByNo(String noPendaftaran) {
        Student student = null;
        String query = "SELECT * FROM pendaftaran_siswa WHERE no_pendaftaran = ?";
        
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, noPendaftaran);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                student = new Student();
                student.setNoPendaftaran(rs.getString("no_pendaftaran"));
                student.setNamaLengkap(rs.getString("nama_lengkap"));
                student.setTempatLahir(rs.getString("tempat_lahir"));
                student.setTglLahir(rs.getDate("tgl_lahir"));
                student.setJnsKelamin(rs.getString("jns_kelamin"));
                student.setAlamat(rs.getString("alamat"));
                student.setAsalSekolah(rs.getString("asal_sekolah"));
                student.setNilaiUan(rs.getFloat("nilai_uan"));
                student.setNamaOrtuWali(rs.getString("nama_ortu_wali"));
                student.setNoTelp(rs.getString("no_telp"));
            }
            
            rs.close();
            stmt.close();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "Error mengambil data: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        
        return student;
    }
    
    /**
     * Update - Mengupdate data siswa di database
     * @param student Object student dengan data yang akan diupdate
     * @return true jika berhasil, false jika gagal
     */
    public boolean updateStudent(Student student) {
        String query = "UPDATE pendaftaran_siswa SET nama_lengkap = ?, tempat_lahir = ?, " +
                      "tgl_lahir = ?, jns_kelamin = ?, alamat = ?, asal_sekolah = ?, " +
                      "nilai_uan = ?, nama_ortu_wali = ?, no_telp = ? " +
                      "WHERE no_pendaftaran = ?";
        
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, student.getNamaLengkap());
            stmt.setString(2, student.getTempatLahir());
            stmt.setDate(3, new java.sql.Date(student.getTglLahir().getTime()));
            stmt.setString(4, student.getJnsKelamin());
            stmt.setString(5, student.getAlamat());
            stmt.setString(6, student.getAsalSekolah());
            stmt.setFloat(7, student.getNilaiUan());
            stmt.setString(8, student.getNamaOrtuWali());
            stmt.setString(9, student.getNoTelp());
            stmt.setString(10, student.getNoPendaftaran());
            
            int result = stmt.executeUpdate();
            stmt.close();
            
            return result > 0;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "Error mengupdate data: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Delete - Menghapus data siswa dari database
     * @param noPendaftaran Nomor pendaftaran siswa yang akan dihapus
     * @return true jika berhasil, false jika gagal
     */
    public boolean deleteStudent(String noPendaftaran) {
        String query = "DELETE FROM pendaftaran_siswa WHERE no_pendaftaran = ?";
        
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, noPendaftaran);
            
            int result = stmt.executeUpdate();
            stmt.close();
            
            return result > 0;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "Error menghapus data: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Check - Mengecek apakah data siswa dengan nama dan tanggal lahir yang sama sudah ada
     * @param namaLengkap Nama lengkap siswa
     * @param tempatLahir Tempat lahir siswa
     * @param tglLahir Tanggal lahir siswa
     * @param excludeNoPendaftaran Nomor pendaftaran yang dikecualikan (untuk update)
     * @return true jika duplikat ditemukan, false jika tidak
     */
    public boolean isDuplicate(String namaLengkap, String tempatLahir, java.util.Date tglLahir, String excludeNoPendaftaran) {
        String query = "SELECT COUNT(*) as count FROM pendaftaran_siswa WHERE " +
                      "nama_lengkap = ? AND tempat_lahir = ? AND tgl_lahir = ?";
        
        if (excludeNoPendaftaran != null && !excludeNoPendaftaran.isEmpty()) {
            query += " AND no_pendaftaran != ?";
        }
        
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, namaLengkap);
            stmt.setString(2, tempatLahir);
            stmt.setDate(3, new java.sql.Date(tglLahir.getTime()));
            
            if (excludeNoPendaftaran != null && !excludeNoPendaftaran.isEmpty()) {
                stmt.setString(4, excludeNoPendaftaran);
            }
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                int count = rs.getInt("count");
                rs.close();
                stmt.close();
                return count > 0;
            }
            
            rs.close();
            stmt.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
    /**
     * Search - Mencari data siswa berdasarkan keyword
     * @param keyword Kata kunci pencarian
     * @return List of Student objects yang sesuai dengan keyword
     */
    public List<Student> searchStudents(String keyword) {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM pendaftaran_siswa WHERE " +
                      "no_pendaftaran LIKE ? OR " +
                      "nama_lengkap LIKE ? OR " +
                      "tempat_lahir LIKE ? OR " +
                      "alamat LIKE ? OR " +
                      "asal_sekolah LIKE ? OR " +
                      "nama_ortu_wali LIKE ? " +
                      "ORDER BY no_pendaftaran DESC";
        
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            String searchPattern = "%" + keyword + "%";
            
            stmt.setString(1, searchPattern);
            stmt.setString(2, searchPattern);
            stmt.setString(3, searchPattern);
            stmt.setString(4, searchPattern);
            stmt.setString(5, searchPattern);
            stmt.setString(6, searchPattern);
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Student student = new Student();
                student.setNoPendaftaran(rs.getString("no_pendaftaran"));
                student.setNamaLengkap(rs.getString("nama_lengkap"));
                student.setTempatLahir(rs.getString("tempat_lahir"));
                student.setTglLahir(rs.getDate("tgl_lahir"));
                student.setJnsKelamin(rs.getString("jns_kelamin"));
                student.setAlamat(rs.getString("alamat"));
                student.setAsalSekolah(rs.getString("asal_sekolah"));
                student.setNilaiUan(rs.getFloat("nilai_uan"));
                student.setNamaOrtuWali(rs.getString("nama_ortu_wali"));
                student.setNoTelp(rs.getString("no_telp"));
                
                students.add(student);
            }
            
            rs.close();
            stmt.close();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "Error mencari data: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        
        return students;
    }
}
