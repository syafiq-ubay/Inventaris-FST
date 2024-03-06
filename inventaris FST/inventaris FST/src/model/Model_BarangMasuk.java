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
public class Model_BarangMasuk {
    
    private String kode_masuk;
    private Model_pesananSupply pesanan;
    private Model_Admin admin;
    private Date tgl_masuk;

    public String getKode_masuk() {
        return kode_masuk;
    }

    public void setKode_masuk(String kode_masuk) {
        this.kode_masuk = kode_masuk;
    }

    public Model_pesananSupply getPesanan() {
        return pesanan;
    }

    public void setPesanan(Model_pesananSupply pesanan) {
        this.pesanan = pesanan;
    }

    public Model_Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Model_Admin admin) {
        this.admin = admin;
    }
    
    public Date getTgl_masuk() {
        return tgl_masuk;
    }

    public void setTgl_masuk(Date tgl_masuk) {
        this.tgl_masuk = tgl_masuk;
    }

   
}
