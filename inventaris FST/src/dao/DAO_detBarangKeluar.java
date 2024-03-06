/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Model.Model_Barang;
import Model.Model_BarangKeluar;
import Model.Model_DetBarangKeluar;
import config.koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import service.Service_DetBarangKeluar;

/**
 *
 * @author SyafiqUbai
 */
public class DAO_detBarangKeluar implements Service_DetBarangKeluar{
     private Connection conn;

    public DAO_detBarangKeluar() {
        conn = koneksi.getConnection();
    }

    @Override
    public void tambahData(Model_DetBarangKeluar mod_detbarkel) {
        PreparedStatement st = null;
        String sql = "INSERT INTO public.detail_barang_keluar(kode_barang, kode_keluar, jmh_keluar)VALUES (?, ?, ?);";
        try{
            st = conn.prepareStatement(sql);
            
            st.setString(1, mod_detbarkel.getBarang().getKode_barang());
            st.setString(2, mod_detbarkel.getBarangkeluar().getKode_keluar());
            st.setInt(3, mod_detbarkel.getJml_keluar());
            
            st.executeUpdate();
        } catch (SQLException ex){
            java.util.logging.Logger.getLogger(DAO_detBarangKeluar.class.getName()).log(Level.SEVERE,null,ex);
        } finally {
            if(st != null){
                try{
                    st.close();
                } catch (SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_detBarangKeluar.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }
    }

    @Override
    public void perbaruiData(Model_DetBarangKeluar mod_detbarkel) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void hapusData(Model_DetBarangKeluar mod_detbarkel) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Model_DetBarangKeluar> getData() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Model_DetBarangKeluar> pencarian(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String nomor() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Model_DetBarangKeluar> getByid(String id) {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql = "SELECT bk.kode_keluar, p.kode_barang, p.nama_barang, dbk.jmh_keluar " 
                       + "FROM detail_barang_keluar dbk " 
                       + "INNER JOIN barang_keluar bk ON bk.kode_keluar=dbk.kode_keluar " 
                       + "INNER JOIN barang p ON p.kode_barang=dbk.kode_barang "
                       +"WHERE dbk.kode_keluar = '"+id+"' ORDER BY kode_keluar ASC";
//        AND det_sp.status_supply = 'Pending' 
        try{
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                Model_DetBarangKeluar det_bk = new Model_DetBarangKeluar();
                Model_BarangKeluar bk = new Model_BarangKeluar();
                Model_Barang b = new Model_Barang();
                
                bk.setKode_keluar(rs.getString("kode_keluar"));
                b.setKode_barang(rs.getString("kode_barang"));
                b.setNama_barang(rs.getString("nama_barang"));
                det_bk.setJml_keluar(rs.getInt("jmh_keluar"));

//                System.out.println(rs.getString("kode_keluar"));
                det_bk.setBarang(b);
                det_bk.setBarangkeluar(bk);
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
    
}
