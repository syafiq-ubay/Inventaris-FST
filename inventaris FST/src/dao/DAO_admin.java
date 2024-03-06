/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Model.Model_Admin;
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
import javax.swing.JOptionPane;
import service.Service_Admin;

/**
 *
 * @author SyafiqUbai
 */
public class DAO_admin implements Service_Admin{
    
   private Connection conn;
    
    public DAO_admin() {
        conn = koneksi.getConnection();
    }

    @Override
    public void tambahData(Model_Admin mod_adm) {
        PreparedStatement st = null;
        String sql = "INSERT INTO public.admin(id_admin, nama_admin, telp_admin, email_admin, username, password)VALUES (?, ?, ?, ?, ?, ?)";
        try{
            st = conn.prepareStatement(sql);
            
            st.setString(1, mod_adm.getId_admin());
            st.setString(2, mod_adm.getNama_admin());
            st.setString(3, mod_adm.getTelp_admin());
            st.setString(4, mod_adm.getEmail_admin());
            st.setString(5, mod_adm.getUsername());
            st.setString(6, Encrypt.getmd5java(mod_adm.getPassword()));
            
           
                 
            st.executeUpdate();
        } catch (SQLException ex){
            java.util.logging.Logger.getLogger(DAO_admin.class.getName()).log(Level.SEVERE,null,ex);
        } finally {
            if(st != null){
                try{
                    st.close();
                } catch (SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_admin.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }
    }

    @Override
    public void perbaruiData(Model_Admin mod_adm) {
       PreparedStatement st = null;
        String sql = "UPDATE admin SET nama_admin = ?, telp_admin = ?, email_admin = ?, username=?, password=? "
                +"WHERE id_admin = '"+mod_adm.getId_admin()+"'";
        try{
            st = conn.prepareStatement(sql);
            
            st.setString(1, mod_adm.getNama_admin());
            st.setString(2, mod_adm.getTelp_admin());
            st.setString(3, mod_adm.getEmail_admin());
            st.setString(4,mod_adm.getUsername());
            st.setString(5,Encrypt.getmd5java(mod_adm.getPassword()));
            
            st.executeUpdate();
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Perbarui data gagal!");
            java.util.logging.Logger.getLogger(DAO_admin.class.getName()).log(Level.SEVERE,null,ex);
        } finally {
            if(st != null){
                try{
                    st.close();
                } catch (SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_admin.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }
    }

    @Override
    public void hapusData(Model_Admin mod_adm) {
        PreparedStatement st = null;
        String sql = "DELETE FROM admin WHERE id_admin = ?";
        try{
            st = conn.prepareStatement(sql);
            
            st.setString(1, mod_adm.getId_admin());
            
            st.executeUpdate();
        } catch (SQLException ex){
            java.util.logging.Logger.getLogger(DAO_admin.class.getName()).log(Level.SEVERE,null,ex);
        } finally {
            if(st != null){
                try{
                    st.close();
                } catch (SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_admin.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }
    }

    @Override
    public Model_Admin getByid(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Model_Admin> getData() {
         PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql = "SELECT * FROM admin ORDER BY id_admin ASC";
        try{
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                Model_Admin mod = new Model_Admin();
                
                mod.setId_admin(rs.getString("id_admin"));
                mod.setNama_admin(rs.getString("nama_admin"));
                mod.setTelp_admin(rs.getString("telp_admin"));
                mod.setEmail_admin(rs.getString("email_admin"));
                
                list.add(mod);
            }
            return list;
        } catch (SQLException ex){
            java.util.logging.Logger.getLogger(DAO_admin.class.getName()).log(Level.SEVERE,null,ex);
            return null;
        } finally {
            if(st != null){
                try{
                    st.close();
                } catch (SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_admin.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }
    }

    @Override
    public List<Model_Admin> pencarian(String id) {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql = "SELECT * FROM admin "
                    +"WHERE (LOWER(id_admin) LIKE LOWER('"+id+"%') "
                    +"OR LOWER(nama_admin) LIKE LOWER('"+id+"%') "
                    +"OR LOWER(telp_admin) LIKE LOWER('"+id+"%') "
                    +"OR LOWER(email_admin) LIKE LOWER('"+id+"%'))"
                    +"ORDER BY id_admin ASC ";
        try{
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                Model_Admin mod = new Model_Admin();
                
                mod.setId_admin(rs.getString("id_admin"));
                mod.setNama_admin(rs.getString("nama_admin"));
                mod.setTelp_admin(rs.getString("telp_admin"));
                mod.setEmail_admin(rs.getString("email_admin"));
                
                list.add(mod);
            }
            return list;
        } catch (SQLException ex){
            java.util.logging.Logger.getLogger(DAO_admin.class.getName()).log(java.util.logging.Level.SEVERE,null,ex);
            return null;
        } finally {
            if(st != null){
                try{
                    st.close();
                } catch (SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_admin.class.getName()).log(java.util.logging.Level.SEVERE,null,ex);
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
        
        String sql = "SELECT RIGHT (id_admin, 3) AS nomor "
                +"FROM admin "
                +"WHERE id_admin LIKE 'AD"+no+"%' "
                +"ORDER BY id_admin DESC LIMIT 1";
        
        try{
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            if(rs.next()){
                int nomor1 = Integer.parseInt(rs.getString("nomor"));
                nomor1++;
                urutan = "AD"+no+String.format("%03d", nomor1);
            } else {
                urutan = "AD"+no+"001";
            }
        } catch (SQLException ex){
            java.util.logging.Logger.getLogger(DAO_admin.class.getName()).log(Level.SEVERE,null,ex);
        } finally {
            if(st != null){
                try{
                    st.close();
                } catch (SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_admin.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }
        return urutan;
    }
    }
    
