/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Asus
 */
public class Model_DetBarangKeluar {
    private Model_Barang barang;
    private Model_BarangKeluar barangkeluar;
    private int jml_keluar;

    public Model_Barang getBarang() {
        return barang;
    }

    public void setBarang(Model_Barang barang) {
        this.barang = barang;
    }

    public Model_BarangKeluar getBarangkeluar() {
        return barangkeluar;
    }

    public void setBarangkeluar(Model_BarangKeluar barangkeluar) {
        this.barangkeluar = barangkeluar;
    }

    public int getJml_keluar() {
        return jml_keluar;
    }

    public void setJml_keluar(int jml_keluar) {
        this.jml_keluar = jml_keluar;
    }
}
