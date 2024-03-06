/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;



import Model.Model_BarangMasuk;
import java.util.List;

/**
 *
 * @author Asus
 */
public interface Service_BarangMasuk {
    
    void tambahData(Model_BarangMasuk mod_barsuk);
    void perbaruiData(Model_BarangMasuk mod_barsuk);
    void hapusData(Model_BarangMasuk mod_barsuk);
    void ubahData(Model_BarangMasuk mod_barsuk);
   
    Model_BarangMasuk getByid(String id);
    
    List<Model_BarangMasuk> getData();
    List<Model_BarangMasuk> pencarian(String id);
    
    String nomor();
}

