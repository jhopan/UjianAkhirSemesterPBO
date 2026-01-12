# Sistem Pendaftaran Siswa Baru - SMA X

Aplikasi desktop berbasis Java untuk mengelola data pendaftaran siswa baru dengan antarmuka GUI menggunakan Java Swing dan database MySQL.

## 📋 Deskripsi Project

Aplikasi ini merupakan Tugas UAS Pemrograman Berbasis Objek yang mengimplementasikan konsep-konsep OOP seperti:
- **Encapsulation** - Data hiding dengan getter/setter
- **Abstraction** - Pemisahan interface dan implementasi
- **Inheritance** - Pewarisan class Swing components
- **Polymorphism** - Event handling dan method overriding

## ✨ Fitur Utama

### 1. Sistem Login
- Autentikasi user dengan username dan password
- Validasi input
- Pengecekan koneksi database
- Pesan error yang informatif

### 2. CRUD Data Siswa Lengkap
- **Create**: Tambah data siswa baru dengan auto-generate nomor pendaftaran
- **Read**: Tampilkan semua data dalam tabel
- **Update**: Edit data siswa dengan konfirmasi
- **Delete**: Hapus data siswa dengan konfirmasi

### 3. Auto-Generate Nomor Pendaftaran
- Format: YYMMDDXXXX (Tahun-Bulan-Tanggal-Urutan)
- Contoh: 2601120001
- Generate otomatis saat tambah data baru

### 4. Pencarian Data Multi-Field
- Cari berdasarkan: No Pendaftaran, Nama, Tempat Lahir, Alamat, Asal Sekolah, Nama Ortu
- Real-time search

### 5. MDI (Multiple Document Interface)
- JDesktopPane untuk multiple windows
- Menu bar dengan shortcut keyboard
- Professional interface

### 6. Validasi Input Komprehensif
- Required field validation
- Data type validation (angka, tanggal)
- Range validation (Nilai UAN 0-400)
- Pesan error spesifik per field

### 7. User Experience
- Hover effect pada button
- Keyboard shortcuts (Ctrl+P, Enter untuk login)
- Auto-focus pada field error
- Double-click table untuk edit
- Konfirmasi dialog sebelum hapus/update

## 🛠️ Teknologi yang Digunakan

- **Bahasa**: Java 11
- **Database**: MySQL 8.0
- **GUI Framework**: Java Swing
- **Library**: 
  - MySQL Connector/J 8.0.33
  - JCalendar 1.4
- **IDE**: Apache NetBeans
- **Build Tool**: Apache Ant

## 📁 Struktur Project

```
UjianAkhirSemesterPBO/
├── src/
│   ├── config/
│   │   └── DatabaseConnection.java    # Koneksi database (Singleton)
│   ├── dao/
│   │   └── StudentDAO.java            # Data Access Object (CRUD)
│   ├── model/
│   │   └── Student.java               # Model/Entity
│   └── view/
│       ├── LoginForm.java             # Form Login
│       ├── MainFrame.java             # MDI Main Window
│       └── StudentForm.java           # Form CRUD Siswa
├── lib/
│   ├── mysql-connector-java-8.0.33.jar
│   └── jcalendar-1.4.jar
├── database/
│   └── pendaftaran_siswa.sql          # Database schema
├── nbproject/                         # NetBeans project files
├── build.xml                          # Ant build script
└── README.md
```

## 🚀 Cara Menjalankan Aplikasi

### Prasyarat
1. **Java Development Kit (JDK) 11 atau lebih tinggi**
   - Download: https://www.oracle.com/java/technologies/downloads/
   - Cek versi: `java -version`

2. **MySQL Server 8.0 atau lebih tinggi**
   - Download: https://dev.mysql.com/downloads/mysql/
   - Atau gunakan XAMPP/WAMP

3. **Apache NetBeans IDE** (Recommended)
   - Download: https://netbeans.apache.org/download/

### Langkah-Langkah Instalasi

#### 1. Clone/Download Repository
```bash
git clone https://github.com/jhopan/UjianAkhirSemesterPBO.git
cd UjianAkhirSemesterPBO
```

Atau download sebagai ZIP dan extract.

#### 2. Setup Database

**A. Jalankan MySQL Server**
   - Jika menggunakan XAMPP: Start Apache dan MySQL di Control Panel
   - Atau jalankan MySQL service

**B. Import Database**
   - Buka phpMyAdmin atau MySQL Command Line
   - Jalankan script dari file `database/pendaftaran_siswa.sql`
   
   Atau via command line:
   ```bash
   mysql -u root -p < database/pendaftaran_siswa.sql
   ```

**C. Sesuaikan Konfigurasi Database** (jika perlu)
   - Buka file: `src/config/DatabaseConnection.java`
   - Edit baris berikut sesuai konfigurasi MySQL Anda:
   ```java
   private static final String URL = "jdbc:mysql://localhost:3306/db_pendaftaran_siswa";
   private static final String USER = "root";
   private static final String PASSWORD = "";  // Ganti jika ada password
   ```

#### 3. Buka Project di NetBeans

**Metode 1: Open Project**
1. Buka NetBeans IDE
2. File → Open Project
3. Pilih folder project `UjianAkhirSemesterPBO`
4. Klik "Open Project"

**Metode 2: Import ZIP**
1. Download project sebagai ZIP
2. Extract ke folder pilihan Anda
3. Buka NetBeans IDE
4. File → Open Project
5. Browse ke folder hasil extract
6. Klik "Open Project"

#### 4. Tambahkan Library (Jika Belum Otomatis)

1. Klik kanan project → Properties
2. Pilih "Libraries" di menu kiri
3. Klik "Add JAR/Folder"
4. Pilih file:
   - `lib/mysql-connector-java-8.0.33.jar`
   - `lib/jcalendar-1.4.jar`
5. Klik "OK"

#### 5. Run Aplikasi

**Cara 1: Via NetBeans**
- Klik tombol Run (▶️) atau tekan **F6**
- Atau klik kanan project → Run

**Cara 2: Via Menu**
- Run → Run Project

## 🔑 Login Credentials

- **Username**: `admin`
- **Password**: `admin123`

## 📊 Database Schema

**Database Name**: `db_pendaftaran_siswa`

**Table**: `pendaftaran_siswa`

| Field          | Type         | Null | Key | Description              |
|----------------|--------------|------|-----|--------------------------|
| no_pendaftaran | VARCHAR(50)  | NO   | PRI | Nomor pendaftaran (auto) |
| nama_lengkap   | VARCHAR(100) | NO   |     | Nama lengkap siswa       |
| tempat_lahir   | VARCHAR(50)  | NO   |     | Tempat lahir siswa       |
| tgl_lahir      | DATE         | NO   |     | Tanggal lahir siswa      |
| jns_kelamin    | CHAR(1)      | NO   |     | Jenis kelamin (L/P)      |
| alamat         | TEXT         | NO   |     | Alamat lengkap siswa     |
| asal_sekolah   | VARCHAR(100) | NO   |     | Nama sekolah asal        |
| nilai_uan      | FLOAT        | NO   |     | Nilai UAN (0-400)        |
| nama_ortu_wali | VARCHAR(100) | NO   |     | Nama orang tua/wali      |
| no_telp        | VARCHAR(20)  | NO   |     | Nomor telepon            |

## 🎯 Design Pattern yang Diterapkan

### 1. MVC (Model-View-Controller)
- **Model**: `Student.java` - Representasi data
- **View**: `LoginForm.java`, `MainFrame.java`, `StudentForm.java` - UI Layer
- **Controller**: `StudentDAO.java` - Business logic & database operations

### 2. DAO (Data Access Object)
- `StudentDAO.java` memisahkan logika akses data dari logika bisnis
- Centralized database operations
- Easy to test and maintain

### 3. Singleton Pattern
- `DatabaseConnection.java` menggunakan singleton untuk koneksi database
- Memastikan hanya ada satu instance koneksi

## 🐛 Troubleshooting

### Error: "Cannot connect to database"
**Solusi:**
1. Pastikan MySQL Server sudah running
2. Cek username, password, dan database name di `DatabaseConnection.java`
3. Pastikan database `db_pendaftaran_siswa` sudah dibuat

### Error: "ClassNotFoundException: com.mysql.cj.jdbc.Driver"
**Solusi:**
1. Pastikan library `mysql-connector-java-8.0.33.jar` sudah ditambahkan
2. Rebuild project (Clean and Build)

### Error: "java.lang.ClassNotFoundException: com.toedter.calendar.JDateChooser"
**Solusi:**
1. Pastikan library `jcalendar-1.4.jar` sudah ditambahkan
2. Rebuild project

### Build Failed
**Solusi:**
1. Clean and Build project (Shift + F11)
2. Cek Java version: Harus JDK 11 atau lebih tinggi
3. Tools → Java Platforms → pastikan JDK 11+ aktif

## 👥 Developer

**Tugas UAS Pemrograman Berbasis Objek**
- **Jhosua**
- **Rafly**

**Mata Kuliah**: Pemrograman Berbasis Objek  
**Semester**: 5  
**Tahun Ajaran**: 2025/2026

## 📝 Lisensi

Project ini dibuat untuk keperluan akademik - Tugas UAS Pemrograman Berbasis Objek.

## 📞 Kontak & Support

Jika mengalami kesulitan atau menemukan bug, silakan:
- Buat issue di GitHub repository
- Contact developer

---

**© 2026 - Sistem Pendaftaran Siswa Baru | Pemrograman Berbasis Objek**
