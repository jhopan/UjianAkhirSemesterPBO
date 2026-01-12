package view;

import config.DatabaseConnection;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * LoginForm - Form login untuk autentikasi pengguna
 * Menggunakan konsep OOP untuk membuat GUI form
 */
public class LoginForm extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnCancel;
    
    public LoginForm() {
        initComponents();
        setLocationRelativeTo(null);
    }
    
    private void initComponents() {
        setTitle("Login - Sistem Pendaftaran Siswa Baru");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 380);
        setResizable(false);
        
        // Panel utama
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(240, 240, 240));
        
        // Panel judul dan informasi
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBackground(new Color(240, 240, 240));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        
        // Judul utama
        JLabel lblTitle = new JLabel("SISTEM PENDAFTARAN SISWA BARU");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setForeground(new Color(0, 120, 215));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Selamat datang
        JLabel lblWelcome = new JLabel("Selamat Datang di Aplikasi Pendaftaran");
        lblWelcome.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblWelcome.setForeground(new Color(100, 100, 100));
        lblWelcome.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Info tugas
        JLabel lblInfo1 = new JLabel("Tugas UAS Pemrograman Berbasis Objek");
        lblInfo1.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblInfo1.setForeground(new Color(51, 51, 51));
        lblInfo1.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel lblInfo2 = new JLabel("Jhosua & Rafly");
        lblInfo2.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblInfo2.setForeground(new Color(76, 175, 80));
        lblInfo2.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel lblSeparator = new JLabel("━━━━━━━━━━━━━━━━━━━━━━━━━━");
        lblSeparator.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        lblSeparator.setForeground(new Color(200, 200, 200));
        lblSeparator.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        titlePanel.add(Box.createVerticalStrut(5));
        titlePanel.add(lblTitle);
        titlePanel.add(Box.createVerticalStrut(8));
        titlePanel.add(lblWelcome);
        titlePanel.add(Box.createVerticalStrut(10));
        titlePanel.add(lblSeparator);
        titlePanel.add(Box.createVerticalStrut(5));
        titlePanel.add(lblInfo1);
        titlePanel.add(Box.createVerticalStrut(2));
        titlePanel.add(lblInfo2);
        titlePanel.add(Box.createVerticalStrut(5));
        
        // Panel form
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Username
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        formPanel.add(lblUsername, gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        txtUsername = new JTextField(15);
        txtUsername.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        formPanel.add(txtUsername, gbc);
        
        // Password
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        formPanel.add(lblPassword, gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        txtPassword = new JPasswordField(15);
        txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        formPanel.add(txtPassword, gbc);
        
        // Panel tombol
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(new Color(240, 240, 240));
        
        btnLogin = new JButton("Login");
        btnLogin.setPreferredSize(new Dimension(100, 35));
        btnLogin.setBackground(new Color(0, 120, 215)); // Windows blue
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLogin.setFocusPainted(false);
        btnLogin.setBorderPainted(false);
        btnLogin.setOpaque(true);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginAction();
            }
        });
        
        btnCancel = new JButton("Batal");
        btnCancel.setPreferredSize(new Dimension(100, 35));
        btnCancel.setBackground(new Color(200, 200, 200)); // Gray
        btnCancel.setForeground(new Color(50, 50, 50)); // Dark gray text
        btnCancel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnCancel.setFocusPainted(false);
        btnCancel.setBorderPainted(false);
        btnCancel.setOpaque(true);
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        buttonPanel.add(btnLogin);
        buttonPanel.add(btnCancel);
        
        // Tambahkan panel ke main panel
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
        
        // Enter key listener untuk login
        txtPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginAction();
            }
        });
    }
    
    /**
     * Method untuk proses login
     */
    private void loginAction() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword());
        
        // Validasi input
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Username dan Password tidak boleh kosong!",
                "Validasi", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Cek koneksi database terlebih dahulu
        if (!DatabaseConnection.testConnection()) {
            JOptionPane.showMessageDialog(this,
                "Gagal terkoneksi ke database. Pastikan MySQL sudah berjalan!",
                "Error Koneksi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Autentikasi sederhana (dalam implementasi nyata, sebaiknya cek dari database)
        if (username.equals("admin") && password.equals("admin123")) {
            JOptionPane.showMessageDialog(this,
                "Login berhasil! Selamat datang, " + username,
                "Sukses", JOptionPane.INFORMATION_MESSAGE);
            
            // Tutup form login dan buka main frame
            this.dispose();
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
            
        } else {
            JOptionPane.showMessageDialog(this,
                "Username atau Password salah!",
                "Login Gagal", JOptionPane.ERROR_MESSAGE);
            txtPassword.setText("");
            txtUsername.requestFocus();
        }
    }
    
    /**
     * Main method untuk menjalankan form login
     */
    public static void main(String[] args) {
        // Set Look and Feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginForm loginForm = new LoginForm();
                loginForm.setVisible(true);
            }
        });
    }
}
