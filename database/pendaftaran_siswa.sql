-- ========================================
-- Database untuk Sistem Pendaftaran Siswa Baru
-- ========================================

-- Buat database
CREATE DATABASE IF NOT EXISTS pendaftaran_siswa_db;

-- Gunakan database
USE pendaftaran_siswa_db;

-- Tabel pendaftaran_siswa
CREATE TABLE `pendaftaran_siswa` (
  `no_pendaftaran` VARCHAR(10) NOT NULL DEFAULT '',
  `nama_lengkap` VARCHAR(50) NOT NULL,
  `tempat_lahir` VARCHAR(50) NOT NULL,
  `tgl_lahir` DATE NOT NULL,
  `jns_kelamin` ENUM('L','P') NOT NULL,
  `alamat` VARCHAR(150) NOT NULL,
  `asal_sekolah` VARCHAR(50) NOT NULL,
  `nilai_uan` FLOAT UNSIGNED NOT NULL,
  `nama_ortu_wali` VARCHAR(50) NOT NULL,
  `no_telp` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`no_pendaftaran`)
) COLLATE='utf8_general_ci' ENGINE=InnoDB;

-- Insert data sample
INSERT INTO `pendaftaran_siswa` 
(`no_pendaftaran`, `nama_lengkap`, `tempat_lahir`, `tgl_lahir`, `jns_kelamin`, 
 `alamat`, `asal_sekolah`, `nilai_uan`, `nama_ortu_wali`, `no_telp`) 
VALUES
('1712120001', 'I Putu Wibawa', 'Denpasar', '1996-10-16', 'L', 
 'Jl. P. Tarakan No. 25, Denpasar', 'SMPN 1 Denpasar', 350.5, 'I Putu Subawa', '08123604567'),
 
('1712120002', 'Ni Made Ari Santhi', 'Denpasar', '1996-06-20', 'P', 
 'Jl. P. Sula No. 10, Denpasar', 'SMPN 7 Denpasar', 345.5, 'I Wayan Sadia', '08180564567'),
 
('1712130001', 'Ni Putu Purnama', 'Denpasar', '1996-08-09', 'P', 
 'Jl. Tukad Banyusari No. 105, Denpasar', 'SMPN 6 Denpasar', 346.7, 'I Wayan Setiawan', '0857375678'),
 
('1712130002', 'I Made Doni Setiawan', 'Denpasar', '1996-06-11', 'P', 
 'Jl. Tukad Pakerisan Gang Perkutut No. 5, Denpasar', 'SMPN 6 Denpasar', 342.8, 'I Putu Satya Subawa', '08133846578'),
 
('1712140001', 'I Made Putranta', 'Denpasar', '1996-08-20', 'L', 
 'Jl. Imam Bonjol Gang Gurita No. 2, Denpasar', 'SMPN 7 Denpasar', 352.8, 'I Made Supartha', '08133842543');

-- Query untuk melihat semua data
-- SELECT * FROM pendaftaran_siswa ORDER BY no_pendaftaran;

-- Query untuk mencari data berdasarkan nama
-- SELECT * FROM pendaftaran_siswa WHERE nama_lengkap LIKE '%putu%';

-- Query untuk menghitung total siswa
-- SELECT COUNT(*) as total_siswa FROM pendaftaran_siswa;

-- Query untuk menghitung siswa berdasarkan jenis kelamin
-- SELECT jns_kelamin, COUNT(*) as jumlah FROM pendaftaran_siswa GROUP BY jns_kelamin;
