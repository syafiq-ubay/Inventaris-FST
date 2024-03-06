/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Model.Model_Pemesan;
import Model.Model_Supplier;
import config.koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import service.Service_Pemesan;

/**
 *
 * @author SyafiqUbai
 */
public class DAO_pemesan implements Service_Pemesan{
    
    private Connection conn;
    public DAO_pemesan() {
        conn = koneksi.getConnection();
    }

    @Override
    public void tambahData(Model_Pemesan mod_pembar) {
        PreparedStatement st = null;
        String sql = "INSERT INTO public.pemesan_barang(id_pemesan, nama_pemesan, unit_pemesan)VALUES (?, ?, ?)";
        try{
            st = conn.prepareStatement(sql);
            
            st.setString(1, mod_pembar.getId_pemesan());
            st.setString(2, mod_pembar.getNama_pemesan());
            st.setString(3, mod_pembar.getUnit_pemesan());
            
            st.executeUpdate();
        } catch (SQLException ex){
            java.util.logging.Logger.getLogger(DAO_pemesan.class.getName()).log(Level.SEVERE,null,ex);
        } finally {
            if(st != null){
                try{
                    st.close();
                } catch (SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_pemesan.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }
    }

    @Override
    public void perbaruiData(Model_Pemesan mod_pembar) {
        PreparedStatement st = null;
        String sql = "UPDATE pemesan_barang SET nama_pemesan = ?, unit_pemesan = ? "
                +"WHERE id_pemesan = '"+mod_pembar.getId_pemesan()+"'";
        try{
            st = conn.prepareStatement(sql);
            
            st.setString(1, mod_pembar.getNama_pemesan());
            st.setString(2, mod_pembar.getUnit_pemesan());
            
            st.executeUpdate();
        } catch (SQLException ex){
            java.util.logging.Logger.getLogger(DAO_pemesan.class.getName()).log(Level.SEVERE,null,ex);
        } finally {
            if(st != null){
                try{
                    st.close();
                } catch (SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_pemesan.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }
    }

    @Override
    public void hapusData(Model_Pemesan mod_pembar) {
        PreparedStatement st = null;
        String sql = "DELETE FROM pemesan_barang WHERE id_pemesan = ?";
        try{
            st = conn.prepareStatement(sql);
            
            st.setString(1, mod_pembar.getId_pemesan());
            
            st.executeUpdate();
        } catch (SQLException ex){
            java.util.logging.Logger.getLogger(DAO_pemesan.class.getName()).log(Level.SEVERE,null,ex);
        } finally {
            if(st != null){
                try{
                    st.close();
                } catch (SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_pemesan.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }
    }

    @Override
    public Model_Pemesan getByid(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Model_Pemesan> getData() {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql = "SELECT * FROM pemesan_barang ORDER BY id_pemesan ASC";
        try{
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                Model_Pemesan mod = new Model_Pemesan();
                
                mod.setId_pemesan(rs.getString("id_pemesan"));
                mod.setNama_pemesan(rs.getString("nama_pemesan"));
                mod.setUnit_pemesan(rs.getString("unit_pemesan"));
                
                list.add(mod);
            }
            return list;
        } catch (SQLException ex){
            java.util.logging.Logger.getLogger(DAO_pemesan.class.getName()).log(Level.SEVERE,null,ex);
            return null;
        } finally {
            if(st != null){
                try{
                    st.close();
                } catch (SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_pemesan.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }
    }

    @Override
    public List<Model_Pemesan> pencarian(String id) {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql = "SELECT * FROM pemesan_barang WHERE (LOWER(id_pemesan) LIKE LOWER('"+id+"%') OR LOWER(nama_pemesan) LIKE LOWER('"+id+"%') OR LOWER(unit_pemesan) LIKE LOWER('"+id+"%'))";
        try{
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                Model_Pemesan mod = new Model_Pemesan();
                
                mod.setId_pemesan(rs.getString("id_pemesan"));
                mod.setNama_pemesan(rs.getString("nama_pemesan"));
                mod.setUnit_pemesan(rs.getString("unit_pemesan"));
                
                list.add(mod);
            }
            return list;
        } catch (SQLException ex){
            java.util.logging.Logger.getLogger(DAO_pemesan.class.getName()).log(Level.SEVERE,null,ex);
            return null;
        } finally {
            if(st != null){
                try{
                    st.close();
                } catch (SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_pemesan.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }
    }

    @Override
    public String nomor() {
        PreparedStatement st = null;
        ResultSet rs = null;
        String urutan = null;
        
        Date now = new Date();
        SimpleDateFormat tanggal = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat nonformat = new SimpleDateFormat("MM");
        String tgl = tanggal.format(now);
        String no = nonformat.format(now);
        
        String sql = "SELECT RIGHT (id_pemesan, 3) AS nomor "
                +"FROM pemesan_barang "
                +"WHERE id_pemesan LIKE 'UNT"+no+"%' "
                +"ORDER BY id_pemesan DESC LIMIT 1";
        
        try{
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            if(rs.next()){
                int nomor1 = Integer.parseInt(rs.getString("nomor"));
                nomor1++;
                urutan = "UNT"+no+String.format("%03d", nomor1);
            } else {
                urutan = "UNT"+no+"001";
            }
        } catch (SQLException ex){
            java.util.logging.Logger.getLogger(DAO_pemesan.class.getName()).log(Level.SEVERE,null,ex);
        } finally {
            if(st != null){
                try{
                    st.close();
                } catch (SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_pemesan.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }
        return urutan;
    }
}
