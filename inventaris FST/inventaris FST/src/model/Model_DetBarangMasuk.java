/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Asus
 */
public class Model_DetBarangMasuk {
    
    private Model_pesananSupply pesananSupply;
    private Model_Barang barang;
    private int jml_masuk;
    

    public Model_Barang getBarang() {
        return barang;
    }

    public void setBarang(Model_Barang barang) {
        this.barang = barang;
    }

    public int getJml_masuk() {
        return jml_masuk;
    }

    public void setJml_masuk(int jml_masuk) {
        this.jml_masuk = jml_masuk;
    }

    public Model_pesananSupply getPesanan() {
        return pesananSupply;
    }

    public void setPesanan(Model_pesananSupply pesanan) {
        this.pesananSupply = pesanan;
    }
    
}
