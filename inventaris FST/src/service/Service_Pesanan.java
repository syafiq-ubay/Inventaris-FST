/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;




import Model.Model_Admin;
import Model.Model_pesananSupply;
import Model.Model_Supplier;
import java.util.List;

/**
 *
 * @author Asus
 */
public interface Service_Pesanan {
    
    void tambahData(Model_pesananSupply mod_psn);
    void perbaruiData(Model_pesananSupply mod_psn);
    void hapusData(Model_pesananSupply mod_psn);
    void ubahData(Model_pesananSupply mod_psn);
   
    List<Model_pesananSupply> getByid(String id);
    
    List<Model_pesananSupply> getData();
    List<Model_pesananSupply> pencarian(String id);
    
    String nomor();
}

