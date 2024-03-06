/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import Model.Model_Barang;
import java.util.List;

/**
 *
 * @author Asus
 */
public interface Service_Barang {
    
    void tambahData(Model_Barang mod_bar);
    void perbaruiData(Model_Barang mod_bar);
    void hapusData(Model_Barang mod_bar);
    void exportToExcel(List<Model_Barang> data, String filePath);
    void importFromExcel(List<Model_Barang> data, String filePath);
    
    List<Model_Barang> getData();
    List<Model_Barang> pencarian(String id);
    
    String nomor();
}   

