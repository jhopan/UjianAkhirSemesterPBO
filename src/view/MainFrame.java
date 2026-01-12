package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * MainFrame - MDI (Multiple Document Interface) Frame utama aplikasi
 * Menggunakan JDesktopPane untuk menampung multiple internal frames
 */
public class MainFrame extends JFrame {
    private JDesktopPane desktopPane;
    private JMenuBar menuBar;
    
    public MainFrame() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
    }
    
    private void initComponents() {
        setTitle("Sistem Pendaftaran Siswa Baru - SMA X");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1024, 768);
        
        // Desktop Pane untuk MDI
        desktopPane = new JDesktopPane();
        desktopPane.setBackground(new Color(220, 220, 220));
        
        // Tambahkan background pattern atau logo (optional)
        desktopPane.setLayout(new BorderLayout());
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        
        // Panel untuk menampung welcome text dan info
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));
        welcomePanel.setOpaque(false);
        
        JLabel lblWelcome = new JLabel("Selamat Datang di Sistem Pendaftaran Siswa Baru");
        lblWelcome.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblWelcome.setForeground(new Color(150, 150, 150));
        lblWelcome.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel lblSubtitle = new JLabel("SMA X");
        lblSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblSubtitle.setForeground(new Color(180, 180, 180));
        lblSubtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel lblDivider = new JLabel("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        lblDivider.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblDivider.setForeground(new Color(200, 200, 200));
        lblDivider.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel lblCredit1 = new JLabel("Tugas UAS Pemrograman Berbasis Objek");
        lblCredit1.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblCredit1.setForeground(new Color(120, 120, 120));
        lblCredit1.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel lblCredit2 = new JLabel("Dibuat oleh: Jhosua & Rafly");
        lblCredit2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblCredit2.setForeground(new Color(76, 175, 80));
        lblCredit2.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        welcomePanel.add(lblWelcome);
        welcomePanel.add(Box.createVerticalStrut(10));
        welcomePanel.add(lblSubtitle);
        welcomePanel.add(Box.createVerticalStrut(20));
        welcomePanel.add(lblDivider);
        welcomePanel.add(Box.createVerticalStrut(15));
        welcomePanel.add(lblCredit1);
        welcomePanel.add(Box.createVerticalStrut(5));
        welcomePanel.add(lblCredit2);
        
        centerPanel.add(welcomePanel);
        desktopPane.add(centerPanel, BorderLayout.CENTER);
        
        // Menu Bar
        createMenuBar();
        
        setJMenuBar(menuBar);
        add(desktopPane, BorderLayout.CENTER);
        
        // Status bar
        createStatusBar();
    }
    
    /**
     * Membuat menu bar dengan menu items
     */
    private void createMenuBar() {
        menuBar = new JMenuBar();
        menuBar.setBackground(Color.WHITE);
        menuBar.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        
        // Set menu selection colors
        UIManager.put("Menu.selectionBackground", new Color(76, 175, 80)); // Green
        UIManager.put("Menu.selectionForeground", Color.WHITE);
        UIManager.put("MenuItem.selectionBackground", new Color(76, 175, 80)); // Green
        UIManager.put("MenuItem.selectionForeground", Color.WHITE);
        
        // Menu File
        JMenu menuFile = new JMenu("File");
        menuFile.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        menuFile.setForeground(new Color(50, 50, 50));
        
        JMenuItem itemExit = new JMenuItem("Keluar");
        itemExit.setIcon(UIManager.getIcon("FileView.computerIcon"));
        itemExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
        itemExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitApplication();
            }
        });
        
        menuFile.add(itemExit);
        
        // Menu Data
        JMenu menuData = new JMenu("Data");
        menuData.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        menuData.setForeground(new Color(50, 50, 50));
        
        JMenuItem itemPendaftaran = new JMenuItem("Pendaftaran Siswa");
        itemPendaftaran.setIcon(UIManager.getIcon("FileView.fileIcon"));
        itemPendaftaran.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        itemPendaftaran.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openStudentForm();
            }
        });
        
        menuData.add(itemPendaftaran);
        
        // Menu Window
        JMenu menuWindow = new JMenu("Window");
        menuWindow.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        menuWindow.setForeground(new Color(50, 50, 50));
        
        JMenuItem itemCascade = new JMenuItem("Cascade");
        itemCascade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cascadeWindows();
            }
        });
        
        JMenuItem itemTile = new JMenuItem("Tile");
        itemTile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tileWindows();
            }
        });
        
        JMenuItem itemCloseAll = new JMenuItem("Tutup Semua");
        itemCloseAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeAllWindows();
            }
        });
        
        menuWindow.add(itemCascade);
        menuWindow.add(itemTile);
        menuWindow.addSeparator();
        menuWindow.add(itemCloseAll);
        
        // Menu Help
        JMenu menuHelp = new JMenu("Help");
        menuHelp.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        menuHelp.setForeground(new Color(50, 50, 50));
        
        JMenuItem itemAbout = new JMenuItem("Tentang");
        itemAbout.setIcon(UIManager.getIcon("OptionPane.informationIcon"));
        itemAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAbout();
            }
        });
        
        menuHelp.add(itemAbout);
        
        // Tambahkan menu ke menu bar
        menuBar.add(menuFile);
        menuBar.add(menuData);
        menuBar.add(menuWindow);
        menuBar.add(menuHelp);
    }
    
    /**
     * Membuat status bar di bagian bawah frame
     */
    private void createStatusBar() {
        JPanel statusBar = new JPanel();
        statusBar.setLayout(new BorderLayout());
        statusBar.setBackground(new Color(240, 240, 240));
        statusBar.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
        JLabel lblStatus = new JLabel(" Ready");
        lblStatus.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        statusBar.add(lblStatus, BorderLayout.WEST);
        
        JLabel lblUser = new JLabel("User: admin ");
        lblUser.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        statusBar.add(lblUser, BorderLayout.EAST);
        
        add(statusBar, BorderLayout.SOUTH);
    }
    
    /**
     * Method untuk membuka form pendaftaran siswa
     */
    private void openStudentForm() {
        // Cek apakah form sudah dibuka
        JInternalFrame[] frames = desktopPane.getAllFrames();
        for (JInternalFrame frame : frames) {
            if (frame instanceof StudentForm) {
                try {
                    frame.setSelected(true);
                    frame.toFront();
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        
        // Buka form baru
        StudentForm studentForm = new StudentForm();
        desktopPane.add(studentForm);
        studentForm.setVisible(true);
        
        // Posisikan di tengah
        try {
            studentForm.setMaximum(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Method untuk cascade windows
     */
    private void cascadeWindows() {
        JInternalFrame[] frames = desktopPane.getAllFrames();
        int x = 0;
        int y = 0;
        
        for (JInternalFrame frame : frames) {
            if (!frame.isIcon()) {
                try {
                    frame.setMaximum(false);
                    frame.reshape(x, y, 800, 600);
                    x += 25;
                    y += 25;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * Method untuk tile windows
     */
    private void tileWindows() {
        JInternalFrame[] frames = desktopPane.getAllFrames();
        int frameCount = 0;
        
        // Hitung frame yang tidak di-minimize
        for (JInternalFrame frame : frames) {
            if (!frame.isIcon()) {
                frameCount++;
            }
        }
        
        if (frameCount == 0) return;
        
        int cols = (int) Math.sqrt(frameCount);
        int rows = (int) Math.ceil((double) frameCount / cols);
        
        int w = desktopPane.getWidth() / cols;
        int h = desktopPane.getHeight() / rows;
        
        int x = 0;
        int y = 0;
        int index = 0;
        
        for (JInternalFrame frame : frames) {
            if (!frame.isIcon()) {
                try {
                    frame.setMaximum(false);
                    frame.reshape(x, y, w, h);
                    x += w;
                    
                    if (++index % cols == 0) {
                        x = 0;
                        y += h;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * Method untuk menutup semua windows
     */
    private void closeAllWindows() {
        JInternalFrame[] frames = desktopPane.getAllFrames();
        for (JInternalFrame frame : frames) {
            frame.dispose();
        }
    }
    
    /**
     * Method untuk menampilkan dialog About
     */
    private void showAbout() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        JLabel title = new JLabel("Sistem Pendaftaran Siswa Baru");
        title.setFont(new Font("Segoe UI", Font.BOLD, 14));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel version = new JLabel("Version 1.0");
        version.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        version.setAlignmentX(Component.CENTER_ALIGNMENT);
        version.setForeground(Color.GRAY);
        
        JLabel desc = new JLabel("<html><center>Aplikasi CRUD untuk mengelola<br>pendaftaran siswa baru</center></html>");
        desc.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        desc.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel tech = new JLabel("<html><center><br>Java Swing + MySQL<br>© 2025 - SMA X</center></html>");
        tech.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        tech.setAlignmentX(Component.CENTER_ALIGNMENT);
        tech.setForeground(Color.GRAY);
        
        panel.add(title);
        panel.add(Box.createVerticalStrut(5));
        panel.add(version);
        panel.add(Box.createVerticalStrut(10));
        panel.add(desc);
        panel.add(tech);
        
        JOptionPane.showMessageDialog(this, panel, "Tentang Aplikasi",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Method untuk keluar dari aplikasi
     */
    private void exitApplication() {
        int confirm = JOptionPane.showConfirmDialog(this,
            "Apakah Anda yakin ingin keluar?",
            "Konfirmasi",
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
