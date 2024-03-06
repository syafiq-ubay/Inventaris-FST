/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

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
import service.Service_Supplier;

/**
 *
 * @author SyafiqUbai
 */
public class DAO_supplier implements Service_Supplier{
    
    private Connection conn;
    
    public DAO_supplier() {
        conn = koneksi.getConnection();
    }
    

    @Override
    public void tambahData(Model_Supplier mod_sup) {
        PreparedStatement st = null;
        String sql = "INSERT INTO public.supplier(id_supplier, nama_supplier, telp_supplier, email_supplier)VALUES (?, ?, ?, ?)";
        try{
            st = conn.prepareStatement(sql);
            
            st.setString(1, mod_sup.getId_supplier());
            st.setString(2, mod_sup.getNama_supplier());
            st.setString(3, mod_sup.getTelp_supplier());
            st.setString(4, mod_sup.getEmail_suppier());

            
            st.executeUpdate();
        } catch (SQLException ex){
            java.util.logging.Logger.getLogger(DAO_supplier.class.getName()).log(Level.SEVERE,null,ex);
        } finally {
            if(st != null){
                try{
                    st.close();
                } catch (SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_supplier.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }
    }

    @Override
    public void perbaruiData(Model_Supplier mod_sup) {
        PreparedStatement st = null;
        String sql = "UPDATE supplier SET nama_supplier = ?, telp_supplier = ?, email_supplier = ? "
                +"WHERE id_supplier = '"+mod_sup.getId_supplier()+"'";
        try{
            st = conn.prepareStatement(sql);
            
            st.setString(1, mod_sup.getNama_supplier());
            st.setString(2, mod_sup.getTelp_supplier());
            st.setString(3, mod_sup.getEmail_suppier());
            
            st.executeUpdate();
        } catch (SQLException ex){
            java.util.logging.Logger.getLogger(DAO_supplier.class.getName()).log(Level.SEVERE,null,ex);
        } finally {
            if(st != null){
                try{
                    st.close();
                } catch (SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_supplier.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }
    }

    @Override
    public void hapusData(Model_Supplier mod_sup) {
        PreparedStatement st = null;
        String sql = "DELETE FROM supplier WHERE id_supplier = ?";
        try{
            st = conn.prepareStatement(sql);
            
            st.setString(1, mod_sup.getId_supplier());
            
            st.executeUpdate();
        } catch (SQLException ex){
            java.util.logging.Logger.getLogger(DAO_supplier.class.getName()).log(Level.SEVERE,null,ex);
        } finally {
            if(st != null){
                try{
                    st.close();
                } catch (SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_supplier.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }
    }

    @Override
    public Model_Supplier getByid(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Model_Supplier> getData() {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql = "SELECT * FROM supplier ORDER BY id_supplier ASC";
        try{
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                Model_Supplier mod = new Model_Supplier();
                
                mod.setId_supplier      (rs.getString("id_supplier"));
                mod.setNama_supplier    (rs.getString("nama_supplier"));
                mod.setTelp_supplier    (rs.getString("telp_supplier"));
                mod.setEmail_suppier    (rs.getString("email_supplier"));
                
                list.add(mod);
            }
            return list;
        } catch (SQLException ex){
            java.util.logging.Logger.getLogger(DAO_supplier.class.getName()).log(Level.SEVERE,null,ex);
            return null;
        } finally {
            if(st != null){
                try{
                    st.close();
                } catch (SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_supplier.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }
    }

    @Override
    public List<Model_Supplier> pencarian(String id) {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql = "SELECT * FROM supplier WHERE (LOWER(id_supplier) LIKE LOWER('"+id+"%') OR LOWER(nama_supplier) LIKE LOWER('"+id+"%') OR LOWER(email_supplier) LIKE LOWER('"+id+"%'))";
        try{
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                Model_Supplier mod = new Model_Supplier();
                
                mod.setId_supplier      (rs.getString("id_supplier"));
                mod.setNama_supplier    (rs.getString("nama_supplier"));
                mod.setTelp_supplier    (rs.getString("telp_supplier"));
                mod.setEmail_suppier    (rs.getString("email_supplier"));
                
                list.add(mod);
            }
            return list;
        } catch (SQLException ex){
            java.util.logging.Logger.getLogger(DAO_supplier.class.getName()).log(Level.SEVERE,null,ex);
            return null;
        } finally {
            if(st != null){
                try{
                    st.close();
                } catch (SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_supplier.class.getName()).log(Level.SEVERE,null,ex);
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
        
        String sql = "SELECT RIGHT (id_supplier, 3) AS nomor "
                +"FROM supplier "
                +"WHERE id_supplier LIKE 'SP"+no+"%' "
                +"ORDER BY id_supplier DESC LIMIT 1";
        
        try{
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            if(rs.next()){
                int nomor1 = Integer.parseInt(rs.getString("nomor"));
                nomor1++;
                urutan = "SP"+no+String.format("%03d", nomor1);
            } else {
                urutan = "SP"+no+"001";
            }
        } catch (SQLException ex){
            java.util.logging.Logger.getLogger(DAO_supplier.class.getName()).log(Level.SEVERE,null,ex);
        } finally {
            if(st != null){
                try{
                    st.close();
                } catch (SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_supplier.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }
        return urutan;
    }
    
}
