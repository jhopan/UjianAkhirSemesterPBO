package model;

import java.util.Date;

/**
 * Student - Model class untuk merepresentasikan data siswa
 * Menggunakan konsep encapsulation dengan getter dan setter
 */
public class Student {
    private String noPendaftaran;
    private String namaLengkap;
    private String tempatLahir;
    private Date tglLahir;
    private String jnsKelamin;
    private String alamat;
    private String asalSekolah;
    private float nilaiUan;
    private String namaOrtuWali;
    private String noTelp;
    
    /**
     * Constructor default
     */
    public Student() {
    }
    
    /**
     * Constructor dengan parameter lengkap
     */
    public Student(String noPendaftaran, String namaLengkap, String tempatLahir, 
                   Date tglLahir, String jnsKelamin, String alamat, 
                   String asalSekolah, float nilaiUan, String namaOrtuWali, String noTelp) {
        this.noPendaftaran = noPendaftaran;
        this.namaLengkap = namaLengkap;
        this.tempatLahir = tempatLahir;
        this.tglLahir = tglLahir;
        this.jnsKelamin = jnsKelamin;
        this.alamat = alamat;
        this.asalSekolah = asalSekolah;
        this.nilaiUan = nilaiUan;
        this.namaOrtuWali = namaOrtuWali;
        this.noTelp = noTelp;
    }
    
    // Getter dan Setter methods
    public String getNoPendaftaran() {
        return noPendaftaran;
    }
    
    public void setNoPendaftaran(String noPendaftaran) {
        this.noPendaftaran = noPendaftaran;
    }
    
    public String getNamaLengkap() {
        return namaLengkap;
    }
    
    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }
    
    public String getTempatLahir() {
        return tempatLahir;
    }
    
    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }
    
    public Date getTglLahir() {
        return tglLahir;
    }
    
    public void setTglLahir(Date tglLahir) {
        this.tglLahir = tglLahir;
    }
    
    public String getJnsKelamin() {
        return jnsKelamin;
    }
    
    public void setJnsKelamin(String jnsKelamin) {
        this.jnsKelamin = jnsKelamin;
    }
    
    public String getAlamat() {
        return alamat;
    }
    
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    
    public String getAsalSekolah() {
        return asalSekolah;
    }
    
    public void setAsalSekolah(String asalSekolah) {
        this.asalSekolah = asalSekolah;
    }
    
    public float getNilaiUan() {
        return nilaiUan;
    }
    
    public void setNilaiUan(float nilaiUan) {
        this.nilaiUan = nilaiUan;
    }
    
    public String getNamaOrtuWali() {
        return namaOrtuWali;
    }
    
    public void setNamaOrtuWali(String namaOrtuWali) {
        this.namaOrtuWali = namaOrtuWali;
    }
    
    public String getNoTelp() {
        return noTelp;
    }
    
    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }
    
    @Override
    public String toString() {
        return "Student{" +
                "noPendaftaran='" + noPendaftaran + '\'' +
                ", namaLengkap='" + namaLengkap + '\'' +
                ", tempatLahir='" + tempatLahir + '\'' +
                ", tglLahir=" + tglLahir +
                ", jnsKelamin='" + jnsKelamin + '\'' +
                ", alamat='" + alamat + '\'' +
                ", asalSekolah='" + asalSekolah + '\'' +
                ", nilaiUan=" + nilaiUan +
                ", namaOrtuWali='" + namaOrtuWali + '\'' +
                ", noTelp='" + noTelp + '\'' +
                '}';
    }
}
