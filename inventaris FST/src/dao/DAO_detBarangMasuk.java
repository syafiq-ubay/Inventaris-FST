/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Model.Model_Admin;
import Model.Model_Barang;
import Model.Model_BarangKeluar;
import Model.Model_DetBarangKeluar;
import Model.Model_DetBarangMasuk;
import Model.Model_pesananSupply;
import config.koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import service.Service_DetBarangMasuk;

/**
 *
 * @author Asus
 */
public class DAO_detBarangMasuk implements Service_DetBarangMasuk{
    
    private Connection conn;
    
    public DAO_detBarangMasuk() {
        conn = koneksi.getConnection();
    }

    @Override
    public void tambahData(Model_DetBarangMasuk mod_detbarsuk) {
        PreparedStatement st = null;
        String sql = "INSERT INTO detail_barang_masuk(kode_barang, jmh_masuk, id_pesanan)VALUES (?, ?, ?)";
        try {
            st = conn.prepareStatement(sql);

            st.setString(1, mod_detbarsuk.getBarang().getKode_barang());
            st.setInt(2,mod_detbarsuk.getJml_masuk());
            st.setString(3, mod_detbarsuk.getPesanan().getId_pesanan());
            

            st.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_detBarangMasuk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_detBarangMasuk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void perbaruiData(Model_DetBarangMasuk mod_detbarsuk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void hapusData(Model_DetBarangMasuk mod_detbarsuk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Model_DetBarangMasuk> getByid(String id) {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql = "SELECT p.id_pesanan,b.kode_barang, b.nama_barang, d.jmh_masuk "
          + "FROM detail_barang_masuk d "
          + "INNER JOIN barang b on b.kode_barang=d.kode_barang "
          + "INNER JOIN pesanan p on p.id_pesanan=d.id_pesanan "
                       +"WHERE p.id_pesanan = '"+id+"' ORDER BY id_pesanan ASC";
//        AND det_sp.status_supply = 'Pending' 
        try{
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                Model_DetBarangMasuk det_bk = new Model_DetBarangMasuk();
                Model_pesananSupply bk = new Model_pesananSupply();
                Model_Barang b = new Model_Barang();
                
                bk.setId_pesanan(rs.getString("id_pesanan"));
                b.setKode_barang(rs.getString("kode_barang"));
                b.setNama_barang(rs.getString("nama_barang"));
                det_bk.setJml_masuk(rs.getInt("jmh_masuk"));

//                System.out.println(rs.getString("id_pesanan"));
                det_bk.setBarang(b);
                det_bk.setPesanan(bk);
                list.add(det_bk);
            }
            return list;
        } catch (SQLException ex){
            java.util.logging.Logger.getLogger(DAO_detBarangKeluar.class.getName()).log(Level.SEVERE,null,ex);
            return null;
        } finally {
            if(st != null){
                try{
                    st.close();
                } catch (SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_detBarangKeluar.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
            if(rs != null){
                try{
                    rs.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_detBarangKeluar.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }
    }
    

    @Override
    public List<Model_DetBarangMasuk> getData() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Model_DetBarangMasuk> pencarian(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String nomor() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
