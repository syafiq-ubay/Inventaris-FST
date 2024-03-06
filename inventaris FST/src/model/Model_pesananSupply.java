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
public class Model_pesananSupply {
    
    private String id_pesanan;
    private Model_Supplier supplier;
    private Model_Admin admin;
    private Date tgl_pesanan;
    private int jml_pesanan;
    private String status;

    public String getId_pesanan() {
        return id_pesanan;
    }

    public void setId_pesanan(String id_pesanan) {
        this.id_pesanan = id_pesanan;
    }

    public Model_Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Model_Supplier supplier) {
        this.supplier = supplier;
    }

    public Model_Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Model_Admin admin) {
        this.admin = admin;
    }

    public Date getTgl_pesanan() {
        return tgl_pesanan;
    }

    public void setTgl_pesanan(Date tgl_pesanan) {
        this.tgl_pesanan = tgl_pesanan;
    }

    public int getJml_pesanan() {
        return jml_pesanan;
    }

    public void setJml_pesanan(int jml_pesanan) {
        this.jml_pesanan = jml_pesanan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
