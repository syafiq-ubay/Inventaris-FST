/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Model.Model_Admin;
import Model.Model_BarangMasuk;
import Model.Model_pesananSupply;
import config.koneksi;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import service.Service_BarangMasuk;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;

/**
 *
 * @author Asus
 */
public class DAO_BarangMasuk implements Service_BarangMasuk {

    private Connection conn;

    public DAO_BarangMasuk() {
        conn = koneksi.getConnection();
    }

    @Override
    public void tambahData(Model_BarangMasuk mod_barsuk) {
        PreparedStatement st = null;
        String sql = "INSERT INTO public.barang_masuk(kode_masuk, id_pesanan, id_admin, tgl_masuk)VALUES (?, ?, ?, ?);";
        try {
            st = conn.prepareStatement(sql);

            st.setString(1, mod_barsuk.getKode_masuk());
            st.setString(2, mod_barsuk.getPesanan().getId_pesanan());
            st.setString(3, mod_barsuk.getAdmin().getId_admin());

            // Convert java.util.Date to java.sql.Date
            java.sql.Date sqlDate = new java.sql.Date(mod_barsuk.getTgl_masuk().getTime());
            st.setDate(4, sqlDate);

            st.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_BarangMasuk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_BarangMasuk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void perbaruiData(Model_BarangMasuk mod_barsuk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void hapusData(Model_BarangMasuk mod_barsuk) {
        PreparedStatement st = null;
        String sql = "DELETE FROM barang_masuk WHERE kode_masuk = ? ";
        try {
            st = conn.prepareStatement(sql);

            st.setString(1, mod_barsuk.getKode_masuk());

            st.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_BarangMasuk.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_BarangMasuk.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public Model_BarangMasuk getByid(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Model_BarangMasuk> getData() {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql = "SELECT bm.kode_masuk, p.id_pesanan, bm.tgl_masuk, p.status "
                + "FROM barang_masuk bm "
                + "INNER JOIN pesanan p ON p.id_pesanan = bm.id_pesanan "
                + "ORDER BY kode_masuk ASC ";
        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
//           
            while (rs.next()) {

                Model_BarangMasuk bm = new Model_BarangMasuk();
                Model_pesananSupply pes = new Model_pesananSupply();

                bm.setKode_masuk(rs.getString("kode_masuk"));
                pes.setId_pesanan(rs.getString("id_pesanan"));
                bm.setTgl_masuk(rs.getDate("tgl_masuk"));
                pes.setStatus(rs.getString("status"));

//                bm.setAdmin(am);
                bm.setPesanan(pes);

                list.add(bm);
            }
            return list;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_BarangMasuk.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_BarangMasuk.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_BarangMasuk.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<Model_BarangMasuk> pencarian(String id) {
        PreparedStatement st = null;
        List<Model_BarangMasuk> list = new ArrayList<>();
        ResultSet rs = null;
        String sql = "SELECT bm.kode_masuk, p.id_pesanan, bm.tgl_masuk, p.status "
                + "FROM barang_masuk bm "
                + "INNER JOIN pesanan p ON p.id_pesanan = bm.id_pesanan "
                + "WHERE (LOWER(bm.kode_masuk) LIKE LOWER(?) "
                + "OR LOWER(p.id_pesanan) LIKE LOWER(?) "
                + "OR LOWER(bm.tgl_masuk) LIKE LOWER(?) "
                + "OR LOWER(p.status) LIKE LOWER(?)) "
                + "ORDER BY bm.kode_masuk ASC";

        try {
            st = conn.prepareStatement(sql);

            // Set parameters for the placeholders
            for (int i = 1; i <= 4; i++) {
                st.setString(i, ""+id+"%"); 
            }

            rs = st.executeQuery();

            while (rs.next()) {
                Model_BarangMasuk bm = new Model_BarangMasuk();
                Model_pesananSupply pes = new Model_pesananSupply();

                bm.setKode_masuk(rs.getString("kode_masuk"));
                pes.setId_pesanan(rs.getString("id_pesanan"));
                bm.setTgl_masuk(rs.getDate("tgl_masuk"));
                pes.setStatus(rs.getString("status"));

                bm.setPesanan(pes);

                list.add(bm);
            }
            return list;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_BarangMasuk.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            // Close resources in the finally block
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_BarangMasuk.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_BarangMasuk.class.getName()).log(Level.SEVERE, null, ex);
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
        SimpleDateFormat nonformat = new SimpleDateFormat("MMd");
        String tgl = tanggal.format(now);
        String no = nonformat.format(now);

        String sql = "SELECT RIGHT (kode_masuk, 3) AS nomor "
                + "FROM barang_masuk "
                + "WHERE kode_masuk LIKE 'BM" + no + "%' "
                + "ORDER BY kode_masuk DESC LIMIT 1";

        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            if (rs.next()) {
                int nomor1 = Integer.parseInt(rs.getString("nomor"));
                nomor1++;
                urutan = "BM" + no + String.format("%03d", nomor1);
            } else {
                urutan = "BM" + no + "001";
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_BarangMasuk.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_BarangMasuk.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return urutan;
    }

    @Override
    public void ubahData(Model_BarangMasuk mod_barsuk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
