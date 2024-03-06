/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;
import Model.Model_JenisBarang;
import java.util.List;

/**
 *
 * @author Asus
 */
public interface Service_JenisBarang {
    
    void tambahData(Model_JenisBarang mod_jenbar);
    void perbaruiData(Model_JenisBarang mod_jenbar);
    void hapusData(Model_JenisBarang mod_jenbar);
    void exportToExcel(List<Model_JenisBarang> data, String filePath);
    void importFromExcel(List<Model_JenisBarang> data, String filePath);
   
    Model_JenisBarang getByid(String id);
    
    List<Model_JenisBarang> getData();
    List<Model_JenisBarang> pencarian(String id);
    
    String nomor();
}

