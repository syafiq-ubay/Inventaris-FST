/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author Asus
 */
public class Model_BarangKeluar {
    
    private String kode_keluar;
    private Model_Pemesan pemesanbarang;
    private Model_Admin admin;
    private Date tgl_keluar;

    public String getKode_keluar() {
        return kode_keluar;
    }

    public void setKode_keluar(String kode_keluar) {
        this.kode_keluar = kode_keluar;
    }

    public Model_Pemesan getPemesanbarang() {
        return pemesanbarang;
    }

    public void setPemesanbarang(Model_Pemesan pemesanbarang) {
        this.pemesanbarang = pemesanbarang;
    }

    public Model_Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Model_Admin admin) {
        this.admin = admin;
    }

    public Date getTgl_keluar() {
        return tgl_keluar;
    }

    public void setTgl_keluar(Date tgl_keluar) {
        this.tgl_keluar = tgl_keluar;
    }
}
