/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Model.Model_Barang;
import Model.Model_JenisBarang;
import config.koneksi;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import service.Service_JenisBarang;

/**
 *
 * @author SyafiqUbai
 */
public class DAO_jenisBarang implements Service_JenisBarang{
    
    private Connection conn;
    
    public DAO_jenisBarang() {
        conn = koneksi.getConnection();
    }
    
    @Override
    public void tambahData(Model_JenisBarang mod_jenbar) {
        PreparedStatement st = null;
        String sql = "INSERT INTO public.jenisbarang(kode_jenis, nama_jenis) VALUES (?, ?)";
        try{
            st = conn.prepareStatement(sql);
            
            st.setString(1, mod_jenbar.getKode_jenis());
            st.setString(2, mod_jenbar.getNama_jenis());
            
            st.executeUpdate();
        } catch (SQLException ex){
            java.util.logging.Logger.getLogger(DAO_jenisBarang.class.getName()).log(Level.SEVERE,null,ex);
        } finally {
            if(st != null){
                try{
                    st.close();
                } catch (SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_jenisBarang.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }
    }

    @Override
    public void perbaruiData(Model_JenisBarang mod_jenbar) {
        PreparedStatement st = null;
        String sql = "UPDATE jenisbarang SET kode_jenis=?, nama_jenis=? "
                +"WHERE kode_jenis = '"+mod_jenbar.getKode_jenis()+"'";
        try{
            st = conn.prepareStatement(sql);
            
            st.setString(1, mod_jenbar.getKode_jenis());
            st.setString(2, mod_jenbar.getNama_jenis());
            
            st.executeUpdate();
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Perbarui data gagal!");
            java.util.logging.Logger.getLogger(DAO_jenisBarang.class.getName()).log(java.util.logging.Level.SEVERE,null,ex);
        } finally {
            if(st != null){
                try{
                    st.close();
                } catch (SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_jenisBarang.class.getName()).log(java.util.logging.Level.SEVERE,null,ex);
                }
            }
            
        }
    }

    @Override
    public void hapusData(Model_JenisBarang mod_jenbar) {
        PreparedStatement st = null;
        String sql = "DELETE FROM jenisbarang WHERE kode_jenis = ?";
        try{
            st = conn.prepareStatement(sql);
            
            st.setString(1, mod_jenbar.getKode_jenis());
            
            st.executeUpdate();
        } catch (SQLException ex){
            java.util.logging.Logger.getLogger(DAO_jenisBarang.class.getName()).log(java.util.logging.Level.SEVERE,null,ex);
        } finally {
            if(st != null){
                try{
                    st.close();
                } catch (SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_jenisBarang.class.getName()).log(java.util.logging.Level.SEVERE,null,ex);
                }
            }
        }
    }

    @Override
    public Model_JenisBarang getByid(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Model_JenisBarang> getData() {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql = "SELECT * FROM jenisbarang ORDER BY kode_jenis ASC";
        try{
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                Model_JenisBarang mod = new Model_JenisBarang();
                
                mod.setKode_jenis(rs.getString("kode_jenis"));
                mod.setNama_jenis(rs.getString("nama_jenis"));
                               
                list.add(mod);
            }
            return list;
        } catch (SQLException ex){
            java.util.logging.Logger.getLogger(DAO_jenisBarang.class.getName()).log(Level.SEVERE,null,ex);
            return null;
        } finally {
            if(st != null){
                try{
                    st.close();
                } catch (SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_jenisBarang.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }
    }

    @Override
    public List<Model_JenisBarang> pencarian(String id) {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql = "SELECT * FROM jenisbarang WHERE (LOWER(kode_jenis) LIKE LOWER('"+id+"%') OR LOWER(nama_jenis) LIKE LOWER('"+id+"%'))";
        try{
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                Model_JenisBarang mod = new Model_JenisBarang();
                
                mod.setKode_jenis(rs.getString("kode_jenis"));
                mod.setNama_jenis(rs.getString("nama_jenis"));
                
                list.add(mod);
            }
            return list;
        } catch (SQLException ex){
            java.util.logging.Logger.getLogger(DAO_jenisBarang.class.getName()).log(Level.SEVERE,null,ex);
            return null;
        } finally {
            if(st != null){
                try{
                    st.close();
                } catch (SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_jenisBarang.class.getName()).log(Level.SEVERE,null,ex);
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
        
        String sql = "SELECT RIGHT (kode_jenis, 3) AS nomor "
                +"FROM jenisbarang "
                +"WHERE kode_jenis LIKE 'JN"+no+"%' "
                +"ORDER BY kode_jenis DESC LIMIT 1";
        
        try{
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            if(rs.next()){
                int nomor1 = Integer.parseInt(rs.getString("nomor"));
                nomor1++;
                urutan = "JN"+no+String.format("%03d", nomor1);
            } else {
                urutan = "JN"+no+"001";
            }
        } catch (SQLException ex){
            java.util.logging.Logger.getLogger(DAO_jenisBarang.class.getName()).log(Level.SEVERE,null,ex);
        } finally {
            if(st != null){
                try{
                    st.close();
                } catch (SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_jenisBarang.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }
        return urutan;
    }

    @Override
    public void exportToExcel(List<Model_JenisBarang> data, String filePath) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Data");

            // Membuat header kolom
            Row headerRow = sheet.createRow(0);
            String[] columns = {"NO", "Kode Jenis", "Nama Jenis"};

            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
            }

            // Membuat baris data
            for (int i = 0; i < data.size(); i++) {
                Row row = sheet.createRow(i + 1);
                Model_JenisBarang barang = data.get(i);

                row.createCell(0).setCellValue(i + 1);
                row.createCell(1).setCellValue(barang.getKode_jenis());
                row.createCell(2).setCellValue(barang.getNama_jenis());
            }

            // Menyimpan file Excel
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }

            JOptionPane.showMessageDialog(null, "Data berhasil di ekspor ke excel");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat mengekspor data ke Excel: " + e.getMessage());
        }
    }

    @Override
    public void importFromExcel(List<Model_JenisBarang> data, String filePath) {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(filePath));
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheetAt(0);
            data.clear();

            boolean firstRowSkipped = false;
            DataFormatter dataFormatter = new DataFormatter();
            for (Row row : sheet) {
                if (!firstRowSkipped) {
                    firstRowSkipped = true;
                    continue;
                }
                Model_JenisBarang barang = new Model_JenisBarang();
                for (Cell cell : row) {
                    int columnIndex = cell.getColumnIndex();

                    switch (columnIndex) {
                        case 0: 
                            barang.setKode_jenis(dataFormatter.formatCellValue(cell));
                            break;
                        case 1: 
                            barang.setNama_jenis(dataFormatter.formatCellValue(cell));
                            break;
                        case 2: 
                        break;
                        default:
                            break;
                    }
                }
                data.add(barang);
                simpanKeDB(barang);
            }
            workbook.close();
           JOptionPane.showMessageDialog(null, "Data berhasil di impor dari excel!");

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat mengimpor data dari Excel: " + e.getMessage());
        }
    }

    private void simpanKeDB(Model_JenisBarang Jenis) {
        PreparedStatement st = null;
        String sql = "INSERT INTO public.jenisbarang(kode_jenis, nama_jenis) VALUES (?, ?)";
        try {
            st = conn.prepareStatement(sql);

            st.setString(1, Jenis.getKode_jenis());
            st.setString(2, Jenis.getNama_jenis());

            st.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
}
