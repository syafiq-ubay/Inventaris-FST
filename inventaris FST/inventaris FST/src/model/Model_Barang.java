/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Asus
 */
public class Model_Barang {
    private String kode_barang;
    private Model.Model_JenisBarang jenisbarang;
    private String nama_barang;
    private int stok;

    public String getKode_barang() {
        return kode_barang;
    }

    public void setKode_barang(String kode_barang) {
        this.kode_barang = kode_barang;
    }

    public Model_JenisBarang getJenisbarang() {
        return jenisbarang;
    }

    public void setJenisbarang(Model_JenisBarang jenisbarang) {
        this.jenisbarang = jenisbarang;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

}
