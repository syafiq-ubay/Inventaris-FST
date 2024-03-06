/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import Model.Model_DetBarangKeluar;
import java.util.List;

/**
 *
 * @author Asus
 */
public interface Service_DetBarangKeluar {
    
    void tambahData(Model_DetBarangKeluar mod_detbarkel);
    void perbaruiData(Model_DetBarangKeluar mod_detbarkel);
    void hapusData(Model_DetBarangKeluar mod_detbarkel);
    
    List<Model_DetBarangKeluar> getByid(String id);
    List<Model_DetBarangKeluar> getData();
    List<Model_DetBarangKeluar> pencarian(String id);
    
    String nomor();
}

