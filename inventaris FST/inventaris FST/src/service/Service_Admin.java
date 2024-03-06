/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;


import Model.Model_Admin;
import java.util.List;

/**
 *
 * @author Asus
 */
public interface Service_Admin {
    
    void tambahData(Model_Admin mod_adm);
    void perbaruiData(Model_Admin mod_adm);
    void hapusData(Model_Admin mod_adm);
   
    Model_Admin getByid(String id);
    
    List<Model_Admin> getData();
    List<Model_Admin> pencarian(String id);
    
    String nomor();
}

