/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Model.Model_Barang;
import Model.Model_JenisBarang;
import config.koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.*;
import java.io.FileOutputStream;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SyafiqUbai
 */
public class DAO_barang implements service.Service_Barang {

    private Connection conn;

    public DAO_barang() {
        conn = koneksi.getConnection();
    }

    @Override
    public void tambahData(Model_Barang mod_bar) {
        PreparedStatement st = null;
        String sql = "INSERT INTO public.barang(kode_barang, kode_jenis, nama_barang, stok)VALUES (?, ?, ?, ?)";
        try {
            st = conn.prepareStatement(sql);

            st.setString(1, mod_bar.getKode_barang());
            st.setString(2, mod_bar.getJenisbarang().getKode_jenis());
            st.setString(3, mod_bar.getNama_barang());
            st.setInt(4, mod_bar.getStok());

            st.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void perbaruiData(Model_Barang mod_bar) {
        PreparedStatement st = null;
        String sql = "UPDATE barang SET kode_jenis=?, nama_barang=?, stok=? "
                + "WHERE kode_barang = '" + mod_bar.getKode_barang() + "'";
        try {
            st = conn.prepareStatement(sql);

            st.setString(1, mod_bar.getJenisbarang().getKode_jenis());
            st.setString(2, mod_bar.getNama_barang());
            st.setInt(3, mod_bar.getStok());

            st.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Perbarui data gagal!");
            java.util.logging.Logger.getLogger(DAO_barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
            }

        }
    }

    @Override
    public void hapusData(Model_Barang mod_bar) {
        PreparedStatement st = null;
        String sql = "DELETE FROM barang WHERE kode_barang = ?";
        try {
            st = conn.prepareStatement(sql);

            st.setString(1, mod_bar.getKode_barang());

            st.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
            }
        }
    }

    public Model_Barang getByid(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Model_Barang> getData() {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql = "SELECT bg.kode_barang,jb.kode_jenis,jb.nama_jenis,bg.nama_barang, bg.stok FROM barang bg INNER JOIN jenisbarang jb ON jb.kode_jenis=bg.kode_jenis ORDER BY kode_barang ASC";
        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Model_Barang brg = new Model_Barang();
                Model_JenisBarang jbr = new Model_JenisBarang();

                brg.setKode_barang(rs.getString("kode_barang"));
                jbr.setKode_jenis(rs.getString("kode_jenis"));
                jbr.setNama_jenis(rs.getString("nama_jenis"));
                brg.setNama_barang(rs.getString("nama_barang"));
                brg.setStok(rs.getInt("stok"));

                brg.setJenisbarang(jbr);

                list.add(brg);
            }
            return list;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_barang.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_barang.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_barang.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<Model_Barang> pencarian(String id) {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql = "SELECT bg.kode_barang,jb.kode_jenis, jb.nama_jenis,bg.nama_barang,bg.stok FROM barang bg INNER JOIN jenisbarang jb ON jb.kode_jenis=bg.kode_jenis "
                + "WHERE (LOWER(kode_barang) LIKE LOWER('" + id + "%') "
                + "OR LOWER(nama_barang) LIKE LOWER('" + id + "%'))";
        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Model_Barang brg = new Model_Barang();
                Model_JenisBarang jbr = new Model_JenisBarang();

                brg.setKode_barang(rs.getString("kode_barang"));
                jbr.setKode_jenis(rs.getString("kode_jenis"));
                jbr.setNama_jenis(rs.getString("nama_jenis"));
                brg.setNama_barang(rs.getString("nama_barang"));
                brg.setStok(rs.getInt("stok"));

                brg.setJenisbarang(jbr);

                list.add(brg);
            }
            return list;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            return null;
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        String sql = "SELECT RIGHT (kode_barang, 3) AS nomor "
                + "FROM barang "
                + "WHERE kode_barang LIKE 'B" + no + "%' "
                + "ORDER BY kode_barang DESC LIMIT 1";

        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            if (rs.next()) {
                int nomor1 = Integer.parseInt(rs.getString("nomor"));
                nomor1++;
                urutan = "B" + no + String.format("%03d", nomor1);
            } else {
                urutan = "B" + no + "001";
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_jenisBarang.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_jenisBarang.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return urutan;
    }

    @Override
    public void exportToExcel(List<Model_Barang> data, String filePath) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Data");

            // Membuat header kolom
            Row headerRow = sheet.createRow(0);
            String[] columns = {"NO", "Kode Barang", "Kode Jenis", "Nama Jenis", "Nama Barang", "Stok"};

            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
            }

            // Membuat baris data
            for (int i = 0; i < data.size(); i++) {
                Row row = sheet.createRow(i + 1);
                Model_Barang barang = data.get(i);

                row.createCell(0).setCellValue(i + 1);
                row.createCell(1).setCellValue(barang.getKode_barang());
                row.createCell(2).setCellValue(barang.getJenisbarang().getKode_jenis());
                row.createCell(3).setCellValue(barang.getJenisbarang().getNama_jenis());
                row.createCell(4).setCellValue(barang.getNama_barang());
                row.createCell(5).setCellValue(barang.getStok());
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
    public void importFromExcel(List<Model_Barang> data, String filePath) {
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
                Model_Barang barang = new Model_Barang();
                Model_JenisBarang jenisBarang = new Model_JenisBarang();
                for (Cell cell : row) {
                    int columnIndex = cell.getColumnIndex();

                    switch (columnIndex) {
                        case 0: 
                            barang.setKode_barang(dataFormatter.formatCellValue(cell));
                            break;
                        case 1: 
                            jenisBarang.setKode_jenis(dataFormatter.formatCellValue(cell));
                            break;
                        case 2: 
                            jenisBarang.setNama_jenis(dataFormatter.formatCellValue(cell));
                            break;
                        case 3: 
                            barang.setNama_barang(dataFormatter.formatCellValue(cell));
                            break;
                        case 4: 
                        try {
                            int stok = Integer.parseInt(dataFormatter.formatCellValue(cell));
                            barang.setStok(stok);
                        } catch (NumberFormatException e) {
                            System.out.println("Format stok tidak valid pada baris: " + (row.getRowNum() + 1));
                        }
                        break;
                        default:
                            break;
                    }
                }
                barang.setJenisbarang(jenisBarang);
                data.add(barang);
                simpanKeDB(barang);
            }
            workbook.close();
           JOptionPane.showMessageDialog(null, "Data berhasil di impor dari excel!");

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat mengimpor data dari Excel: " + e.getMessage());
        }
    }

    private void simpanKeDB(Model_Barang mod_bar) {
        PreparedStatement st = null;
        String sql = "INSERT INTO public.barang(kode_barang, kode_jenis, nama_barang, stok) VALUES (?, ?, ?, ?)";
        try {
            st = conn.prepareStatement(sql);

            st.setString(1, mod_bar.getKode_barang());
            st.setString(2, mod_bar.getJenisbarang().getKode_jenis());
            st.setString(3, mod_bar.getNama_barang());
            st.setInt(4, mod_bar.getStok());

            st.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
    

}
