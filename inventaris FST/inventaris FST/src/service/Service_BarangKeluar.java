/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;



import Model.Model_BarangKeluar;

import java.util.List;

/**
 *
 * @author Asus
 */
public interface Service_BarangKeluar {
    
    void tambahData(Model_BarangKeluar mod_barkel);
    void perbaruiData(Model_BarangKeluar mod_barkel);
    void hapusData(Model_BarangKeluar mod_barkel);
   
    Model_BarangKeluar getByid(String id);
    
    List<Model_BarangKeluar> getData();
    List<Model_BarangKeluar> pencarian(String id);
    
    String nomor();
}

