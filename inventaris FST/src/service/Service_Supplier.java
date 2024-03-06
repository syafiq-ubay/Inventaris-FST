/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;



import Model.Model_Supplier;
import java.util.List;

/**
 *
 * @author Asus
 */
public interface Service_Supplier {
    
    void tambahData(Model_Supplier mod_sup);
    void perbaruiData(Model_Supplier mod_sup);
    void hapusData(Model_Supplier mod_sup);
   
    Model_Supplier getByid(String id);
    
    List<Model_Supplier> getData();
    List<Model_Supplier> pencarian(String id);
    
    String nomor();
}

