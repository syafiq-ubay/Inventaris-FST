/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;
import Model.Model_DetBarangMasuk;
import java.util.List;

/**
 *
 * @author Asus
 */
public interface Service_DetBarangMasuk {
    
    void tambahData(Model_DetBarangMasuk mod_detbarsuk);
    void perbaruiData(Model_DetBarangMasuk mod_detbarsuk);
    void hapusData(Model_DetBarangMasuk mod_detbarsuk);
   
    List <Model_DetBarangMasuk> getByid(String id); 
    List<Model_DetBarangMasuk> getData();
    List<Model_DetBarangMasuk> pencarian(String id);
    
    String nomor();
}

