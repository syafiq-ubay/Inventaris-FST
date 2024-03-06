/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;



import Model.Model_Pemesan;
import java.util.List;

/**
 *
 * @author Asus
 */
public interface Service_Pemesan {
    
    void tambahData(Model_Pemesan mod_pembar);
    void perbaruiData(Model_Pemesan mod_pembar);
    void hapusData(Model_Pemesan mod_pembar);
   
    Model_Pemesan getByid(String id);
    
    List<Model_Pemesan> getData();
    List<Model_Pemesan> pencarian(String id);
    
    String nomor();
}

