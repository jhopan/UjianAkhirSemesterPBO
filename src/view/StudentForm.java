package view;

import dao.StudentDAO;
import model.Student;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.List;
import com.toedter.calendar.JDateChooser;

/**
 * StudentForm - Internal frame untuk CRUD data pendaftaran siswa
 * Menggunakan konsep OOP dan MVC pattern
 */
public class StudentForm extends JInternalFrame {
    private StudentDAO studentDAO;
    
    // Form components
    private JTextField txtNoPendaftaran;
    private JTextField txtNamaLengkap;
    private JTextField txtTempatLahir;
    private JDateChooser dateChooserTglLahir;
    private JRadioButton rbLaki;
    private JRadioButton rbPerempuan;
    private ButtonGroup genderGroup;
    private JTextArea txtAlamat;
    private JTextField txtAsalSekolah;
    private JTextField txtNilaiUan;
    private JTextField txtNamaOrtu;
    private JTextField txtNoTelp;
    
    // Buttons
    private JButton btnNew;
    private JButton btnSave;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnCancel;
    private JButton btnSearch;
    
    // Search field
    private JTextField txtSearch;
    
    // Table
    private JTable table;
    private DefaultTableModel tableModel;
    
    public StudentForm() {
        studentDAO = new StudentDAO();
        initComponents();
        loadTableData();
        setFormState(true);
    }
    
    private void initComponents() {
        setTitle("Form Pendaftaran Siswa Baru");
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
        setResizable(true);
        setSize(1000, 700);
        
        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Form panel
        JPanel formPanel = createFormPanel();
        
        // Button panel
        JPanel buttonPanel = createButtonPanel();
        
        // Search panel
        JPanel searchPanel = createSearchPanel();
        
        // Table panel
        JPanel tablePanel = createTablePanel();
        
        // Top panel (form + buttons)
        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        topPanel.add(formPanel, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.EAST);
        
        // Bottom panel (search + table)
        JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
        bottomPanel.add(searchPanel, BorderLayout.NORTH);
        bottomPanel.add(tablePanel, BorderLayout.CENTER);
        
        // Split pane
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topPanel, bottomPanel);
        splitPane.setDividerLocation(380);
        splitPane.setResizeWeight(0.5);
        
        mainPanel.add(splitPane, BorderLayout.CENTER);
        
        add(mainPanel);
    }
    
    /**
     * Membuat panel form input
     */
    private JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(5, 10, 15, 10),
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                "Data Siswa",
                0, 0,
                new Font("Segoe UI", Font.BOLD, 12)
            )
        ));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 5, 8);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        int row = 0;
        
        // No Pendaftaran
        gbc.gridx = 0; gbc.gridy = row;
        panel.add(new JLabel("No. Pendaftaran:"), gbc);
        gbc.gridx = 1;
        txtNoPendaftaran = new JTextField(20);
        txtNoPendaftaran.setEditable(false);
        txtNoPendaftaran.setBackground(new Color(240, 240, 240));
        panel.add(txtNoPendaftaran, gbc);
        
        // Nama Lengkap
        row++;
        gbc.gridx = 0; gbc.gridy = row;
        panel.add(new JLabel("Nama Lengkap:"), gbc);
        gbc.gridx = 1;
        txtNamaLengkap = new JTextField(20);
        panel.add(txtNamaLengkap, gbc);
        
        // Tempat Lahir
        row++;
        gbc.gridx = 0; gbc.gridy = row;
        panel.add(new JLabel("Tempat Lahir:"), gbc);
        gbc.gridx = 1;
        txtTempatLahir = new JTextField(20);
        panel.add(txtTempatLahir, gbc);
        
        // Tanggal Lahir
        row++;
        gbc.gridx = 0; gbc.gridy = row;
        panel.add(new JLabel("Tanggal Lahir:"), gbc);
        gbc.gridx = 1;
        dateChooserTglLahir = new JDateChooser();
        dateChooserTglLahir.setDateFormatString("yyyy-MM-dd");
        panel.add(dateChooserTglLahir, gbc);
        
        // Jenis Kelamin
        row++;
        gbc.gridx = 0; gbc.gridy = row;
        panel.add(new JLabel("Jenis Kelamin:"), gbc);
        gbc.gridx = 1;
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rbLaki = new JRadioButton("Laki-laki");
        rbPerempuan = new JRadioButton("Perempuan");
        genderGroup = new ButtonGroup();
        genderGroup.add(rbLaki);
        genderGroup.add(rbPerempuan);
        genderPanel.add(rbLaki);
        genderPanel.add(rbPerempuan);
        panel.add(genderPanel, gbc);
        
        // Alamat
        row++;
        gbc.gridx = 0; gbc.gridy = row;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        panel.add(new JLabel("Alamat:"), gbc);
        gbc.gridx = 1;
        txtAlamat = new JTextArea(3, 20);
        txtAlamat.setLineWrap(true);
        txtAlamat.setWrapStyleWord(true);
        JScrollPane scrollAlamat = new JScrollPane(txtAlamat);
        panel.add(scrollAlamat, gbc);
        
        // Asal Sekolah
        row++;
        gbc.gridx = 0; gbc.gridy = row;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("Asal Sekolah:"), gbc);
        gbc.gridx = 1;
        txtAsalSekolah = new JTextField(20);
        panel.add(txtAsalSekolah, gbc);
        
        // Nilai UAN
        row++;
        gbc.gridx = 0; gbc.gridy = row;
        panel.add(new JLabel("Nilai UAN:"), gbc);
        gbc.gridx = 1;
        txtNilaiUan = new JTextField(20);
        panel.add(txtNilaiUan, gbc);
        
        // Nama Ortu/Wali
        row++;
        gbc.gridx = 0; gbc.gridy = row;
        panel.add(new JLabel("Nama Ortu/Wali:"), gbc);
        gbc.gridx = 1;
        txtNamaOrtu = new JTextField(20);
        panel.add(txtNamaOrtu, gbc);
        
        // No Telp
        row++;
        gbc.gridx = 0; gbc.gridy = row;
        panel.add(new JLabel("No. Telp:"), gbc);
        gbc.gridx = 1;
        txtNoTelp = new JTextField(20);
        panel.add(txtNoTelp, gbc);
        
        return panel;
    }
    
    /**
     * Membuat panel tombol CRUD
     */
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 1, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 5, 5, 5));
        
        btnNew = new JButton("Baru");
        btnNew.setIcon(UIManager.getIcon("FileView.fileIcon"));
        btnNew.addActionListener(e -> newData());
        addButtonHoverEffect(btnNew, new Color(76, 175, 80), Color.WHITE);
        
        btnSave = new JButton("Simpan");
        btnSave.setIcon(UIManager.getIcon("FileView.floppyDriveIcon"));
        btnSave.addActionListener(e -> saveData());
        addButtonHoverEffect(btnSave, new Color(33, 150, 243), Color.WHITE);
        
        btnUpdate = new JButton("Update");
        btnUpdate.setIcon(UIManager.getIcon("FileView.computerIcon"));
        btnUpdate.addActionListener(e -> updateData());
        addButtonHoverEffect(btnUpdate, new Color(255, 152, 0), Color.WHITE);
        
        btnDelete = new JButton("Hapus");
        btnDelete.setIcon(UIManager.getIcon("OptionPane.warningIcon"));
        btnDelete.addActionListener(e -> deleteData());
        addButtonHoverEffect(btnDelete, new Color(244, 67, 54), Color.WHITE);
        
        btnCancel = new JButton("Batal");
        btnCancel.setIcon(UIManager.getIcon("OptionPane.errorIcon"));
        btnCancel.addActionListener(e -> cancelInput());
        addButtonHoverEffect(btnCancel, new Color(158, 158, 158), Color.WHITE);
        
        panel.add(btnNew);
        panel.add(btnSave);
        panel.add(btnUpdate);
        panel.add(btnDelete);
        panel.add(btnCancel);
        
        return panel;
    }
    
    /**
     * Menambahkan efek hover pada tombol
     */
    private void addButtonHoverEffect(JButton button, Color hoverColor, Color hoverForeground) {
        final Color originalBackground = button.getBackground();
        final Color originalForeground = button.getForeground();
        
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hoverColor);
                button.setForeground(hoverForeground);
                button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(originalBackground);
                button.setForeground(originalForeground);
                button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }
    
    /**
     * Membuat panel pencarian
     */
    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Pencarian"));
        
        panel.add(new JLabel("Cari:"));
        txtSearch = new JTextField(30);
        panel.add(txtSearch);
        
        btnSearch = new JButton("Cari");
        btnSearch.setIcon(UIManager.getIcon("FileView.directoryIcon"));
        btnSearch.addActionListener(e -> searchData());
        addButtonHoverEffect(btnSearch, new Color(76, 175, 80), Color.WHITE);
        panel.add(btnSearch);
        
        JButton btnRefresh = new JButton("Refresh");
        btnRefresh.setIcon(UIManager.getIcon("FileView.computerIcon"));
        btnRefresh.addActionListener(e -> loadTableData());
        addButtonHoverEffect(btnRefresh, new Color(33, 150, 243), Color.WHITE);
        panel.add(btnRefresh);
        
        // Enter key untuk search
        txtSearch.addActionListener(e -> searchData());
        
        return panel;
    }
    
    /**
     * Membuat panel tabel data
     */
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Data Pendaftaran Siswa"));
        
        // Table model
        String[] columns = {"No. Pendaftaran", "Nama Lengkap", "Tempat Lahir", 
                           "Tgl Lahir", "JK", "Alamat", "Asal Sekolah", 
                           "Nilai UAN", "Nama Ortu/Wali", "No. Telp"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        // Set column widths
        table.getColumnModel().getColumn(0).setPreferredWidth(120);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(120);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(40);
        table.getColumnModel().getColumn(5).setPreferredWidth(200);
        table.getColumnModel().getColumn(6).setPreferredWidth(150);
        table.getColumnModel().getColumn(7).setPreferredWidth(80);
        table.getColumnModel().getColumn(8).setPreferredWidth(150);
        table.getColumnModel().getColumn(9).setPreferredWidth(120);
        
        // Mouse listener untuk select row
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    selectTableRow();
                }
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    // Flag untuk membedakan mode baru atau edit
    private boolean isNewMode = false;
    
    /**
     * Method untuk data baru
     */
    private void newData() {
        clearForm();
        String noPendaftaran = studentDAO.generateNoPendaftaran();
        txtNoPendaftaran.setText(noPendaftaran);
        isNewMode = true;
        setFormState(false);
        // Sembunyikan tombol Update dan Hapus saat mode baru
        btnUpdate.setVisible(false);
        btnDelete.setVisible(false);
        txtNamaLengkap.requestFocus();
    }
    
    /**
     * Method untuk menyimpan data
     */
    private void saveData() {
        if (!validateInput()) {
            return;
        }
        
        // Cek duplikat data
        if (studentDAO.isDuplicate(
                txtNamaLengkap.getText().trim(),
                txtTempatLahir.getText().trim(),
                dateChooserTglLahir.getDate(),
                null)) {
            JOptionPane.showMessageDialog(this,
                "Data siswa dengan nama, tempat lahir, dan tanggal lahir yang sama sudah ada!",
                "Duplikat Data", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            Student student = new Student();
            student.setNoPendaftaran(txtNoPendaftaran.getText());
            student.setNamaLengkap(txtNamaLengkap.getText().trim());
            student.setTempatLahir(txtTempatLahir.getText().trim());
            student.setTglLahir(dateChooserTglLahir.getDate());
            student.setJnsKelamin(rbLaki.isSelected() ? "L" : "P");
            student.setAlamat(txtAlamat.getText().trim());
            student.setAsalSekolah(txtAsalSekolah.getText().trim());
            student.setNilaiUan(Float.parseFloat(txtNilaiUan.getText().trim()));
            student.setNamaOrtuWali(txtNamaOrtu.getText().trim());
            student.setNoTelp(txtNoTelp.getText().trim());
            
            if (studentDAO.createStudent(student)) {
                JOptionPane.showMessageDialog(this,
                    "Data berhasil disimpan!",
                    "Sukses", JOptionPane.INFORMATION_MESSAGE);
                loadTableData();
                clearForm();
                setFormState(true);
                isNewMode = false;
                btnUpdate.setVisible(true);
                btnDelete.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this,
                    "Gagal menyimpan data!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    /**
     * Method untuk update data
     */
    private void updateData() {
        if (txtNoPendaftaran.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Pilih data yang akan diupdate!",
                "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (!validateInput()) {
            return;
        }
        
        // Cek duplikat data (exclude current record)
        if (studentDAO.isDuplicate(
                txtNamaLengkap.getText().trim(),
                txtTempatLahir.getText().trim(),
                dateChooserTglLahir.getDate(),
                txtNoPendaftaran.getText())) {
            JOptionPane.showMessageDialog(this,
                "Data siswa dengan nama, tempat lahir, dan tanggal lahir yang sama sudah ada!",
                "Duplikat Data", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this,
            "Apakah Anda yakin ingin mengupdate data ini?",
            "Konfirmasi", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                Student student = new Student();
                student.setNoPendaftaran(txtNoPendaftaran.getText());
                student.setNamaLengkap(txtNamaLengkap.getText().trim());
                student.setTempatLahir(txtTempatLahir.getText().trim());
                student.setTglLahir(dateChooserTglLahir.getDate());
                student.setJnsKelamin(rbLaki.isSelected() ? "L" : "P");
                student.setAlamat(txtAlamat.getText().trim());
                student.setAsalSekolah(txtAsalSekolah.getText().trim());
                student.setNilaiUan(Float.parseFloat(txtNilaiUan.getText().trim()));
                student.setNamaOrtuWali(txtNamaOrtu.getText().trim());
                student.setNoTelp(txtNoTelp.getText().trim());
                
                if (studentDAO.updateStudent(student)) {
                    JOptionPane.showMessageDialog(this,
                        "Data berhasil diupdate!",
                        "Sukses", JOptionPane.INFORMATION_MESSAGE);
                    loadTableData();
                    clearForm();
                    setFormState(true);
                    isNewMode = false;
                } else {
                    JOptionPane.showMessageDialog(this,
                        "Gagal mengupdate data!",
                        "Error", JOptionPane.ERROR_MESSAGE);
                }
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                    "Error: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Method untuk menghapus data
     */
    private void deleteData() {
        if (txtNoPendaftaran.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Pilih data yang akan dihapus!",
                "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this,
            "Apakah Anda yakin ingin menghapus data ini?",
            "Konfirmasi", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            String noPendaftaran = txtNoPendaftaran.getText();
            
            if (studentDAO.deleteStudent(noPendaftaran)) {
                JOptionPane.showMessageDialog(this,
                    "Data berhasil dihapus!",
                    "Sukses", JOptionPane.INFORMATION_MESSAGE);
                loadTableData();
                clearForm();
                setFormState(true);
            } else {
                JOptionPane.showMessageDialog(this,
                    "Gagal menghapus data!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    /**
     * Method untuk membatalkan input
     */
    private void cancelInput() {
        clearForm();
        setFormState(true);
        isNewMode = false;
        // Tampilkan kembali tombol Update dan Hapus
        btnUpdate.setVisible(true);
        btnDelete.setVisible(true);
    }
    
    /**
     * Method untuk mencari data
     */
    private void searchData() {
        String keyword = txtSearch.getText().trim();
        
        if (keyword.isEmpty()) {
            loadTableData();
            return;
        }
        
        List<Student> students = studentDAO.searchStudents(keyword);
        updateTable(students);
    }
    
    /**
     * Method untuk load data ke table
     */
    private void loadTableData() {
        List<Student> students = studentDAO.getAllStudents();
        updateTable(students);
    }
    
    /**
     * Method untuk update table dengan list student
     */
    private void updateTable(List<Student> students) {
        tableModel.setRowCount(0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        for (Student student : students) {
            Object[] row = {
                student.getNoPendaftaran(),
                student.getNamaLengkap(),
                student.getTempatLahir(),
                sdf.format(student.getTglLahir()),
                student.getJnsKelamin(),
                student.getAlamat(),
                student.getAsalSekolah(),
                student.getNilaiUan(),
                student.getNamaOrtuWali(),
                student.getNoTelp()
            };
            tableModel.addRow(row);
        }
    }
    
    /**
     * Method untuk select row dari table ke form
     */
    private void selectTableRow() {
        int selectedRow = table.getSelectedRow();
        
        if (selectedRow >= 0) {
            String noPendaftaran = tableModel.getValueAt(selectedRow, 0).toString();
            Student student = studentDAO.getStudentByNo(noPendaftaran);
            
            if (student != null) {
                txtNoPendaftaran.setText(student.getNoPendaftaran());
                txtNamaLengkap.setText(student.getNamaLengkap());
                txtTempatLahir.setText(student.getTempatLahir());
                dateChooserTglLahir.setDate(student.getTglLahir());
                
                if (student.getJnsKelamin().equals("L")) {
                    rbLaki.setSelected(true);
                } else {
                    rbPerempuan.setSelected(true);
                }
                
                txtAlamat.setText(student.getAlamat());
                txtAsalSekolah.setText(student.getAsalSekolah());
                txtNilaiUan.setText(String.valueOf(student.getNilaiUan()));
                txtNamaOrtu.setText(student.getNamaOrtuWali());
                txtNoTelp.setText(student.getNoTelp());
                
                isNewMode = false;
                setFormState(false);
                btnSave.setEnabled(false);
                // Tampilkan tombol Update dan Hapus saat edit mode
                btnUpdate.setVisible(true);
                btnDelete.setVisible(true);
            }
        }
    }
    
    /**
     * Method untuk validasi input
     */
    private boolean validateInput() {
        if (txtNamaLengkap.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Nama lengkap harus diisi!",
                "Validasi", JOptionPane.WARNING_MESSAGE);
            txtNamaLengkap.requestFocus();
            return false;
        }
        
        if (txtTempatLahir.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Tempat lahir harus diisi!",
                "Validasi", JOptionPane.WARNING_MESSAGE);
            txtTempatLahir.requestFocus();
            return false;
        }
        
        if (dateChooserTglLahir.getDate() == null) {
            JOptionPane.showMessageDialog(this,
                "Tanggal lahir harus diisi!",
                "Validasi", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if (!rbLaki.isSelected() && !rbPerempuan.isSelected()) {
            JOptionPane.showMessageDialog(this,
                "Jenis kelamin harus dipilih!",
                "Validasi", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if (txtAlamat.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Alamat harus diisi!",
                "Validasi", JOptionPane.WARNING_MESSAGE);
            txtAlamat.requestFocus();
            return false;
        }
        
        if (txtAsalSekolah.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Asal sekolah harus diisi!",
                "Validasi", JOptionPane.WARNING_MESSAGE);
            txtAsalSekolah.requestFocus();
            return false;
        }
        
        if (txtNilaiUan.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Nilai UAN harus diisi!",
                "Validasi", JOptionPane.WARNING_MESSAGE);
            txtNilaiUan.requestFocus();
            return false;
        }
        
        try {
            float nilai = Float.parseFloat(txtNilaiUan.getText().trim());
            if (nilai <= 0 || nilai > 400) {
                JOptionPane.showMessageDialog(this,
                    "Nilai UAN harus lebih dari 0 dan maksimal 400!",
                    "Validasi", JOptionPane.WARNING_MESSAGE);
                txtNilaiUan.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                "Nilai UAN harus berupa angka!",
                "Validasi", JOptionPane.WARNING_MESSAGE);
            txtNilaiUan.requestFocus();
            return false;
        }
        
        if (txtNamaOrtu.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Nama orang tua/wali harus diisi!",
                "Validasi", JOptionPane.WARNING_MESSAGE);
            txtNamaOrtu.requestFocus();
            return false;
        }
        
        if (txtNoTelp.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "No. telepon harus diisi!",
                "Validasi", JOptionPane.WARNING_MESSAGE);
            txtNoTelp.requestFocus();
            return false;
        }
        
        // Validasi nomor telepon hanya boleh angka
        String noTelp = txtNoTelp.getText().trim();
        if (!noTelp.matches("[0-9]+")) {
            JOptionPane.showMessageDialog(this,
                "No. telepon hanya boleh berisi angka!",
                "Validasi", JOptionPane.WARNING_MESSAGE);
            txtNoTelp.requestFocus();
            return false;
        }
        
        return true;
    }
    
    /**
     * Method untuk clear form
     */
    private void clearForm() {
        txtNoPendaftaran.setText("");
        txtNamaLengkap.setText("");
        txtTempatLahir.setText("");
        dateChooserTglLahir.setDate(null);
        genderGroup.clearSelection();
        txtAlamat.setText("");
        txtAsalSekolah.setText("");
        txtNilaiUan.setText("");
        txtNamaOrtu.setText("");
        txtNoTelp.setText("");
        txtSearch.setText("");
    }
    
    /**
     * Method untuk set state form (enable/disable)
     */
    private void setFormState(boolean isReadOnly) {
        txtNamaLengkap.setEditable(!isReadOnly);
        txtTempatLahir.setEditable(!isReadOnly);
        dateChooserTglLahir.setEnabled(!isReadOnly);
        rbLaki.setEnabled(!isReadOnly);
        rbPerempuan.setEnabled(!isReadOnly);
        txtAlamat.setEditable(!isReadOnly);
        txtAsalSekolah.setEditable(!isReadOnly);
        txtNilaiUan.setEditable(!isReadOnly);
        txtNamaOrtu.setEditable(!isReadOnly);
        txtNoTelp.setEditable(!isReadOnly);
        
        btnNew.setEnabled(isReadOnly);
        btnSave.setEnabled(!isReadOnly);
        btnUpdate.setEnabled(!isReadOnly);
        btnDelete.setEnabled(!isReadOnly);
        btnCancel.setEnabled(!isReadOnly);
    }
}
