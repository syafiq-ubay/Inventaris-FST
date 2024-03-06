    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Model.Model_Admin;
import Model.Model_BarangKeluar;
import Model.Model_Pemesan;
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

/**
 *
 * @author SyafiqUbai
 */
public class DAO_BarangKeluar implements service.Service_BarangKeluar {

    private Connection conn;

    public DAO_BarangKeluar() {
        conn = koneksi.getConnection();
    }

    @Override
    public void tambahData(Model_BarangKeluar mod_barkel) {
    PreparedStatement st = null;
    String sql = "INSERT INTO public.barang_keluar(kode_keluar, id_pemesan, id_admin, tgl_keluar) VALUES (?, ?, ?, ?);";
    try {
        st = conn.prepareStatement(sql);

        st.setString(1, mod_barkel.getKode_keluar());
        st.setString(2, mod_barkel.getPemesanbarang().getId_pemesan());
        st.setString(3, mod_barkel.getAdmin().getId_admin());

        // Convert java.util.Date to java.sql.Date
        java.sql.Date sqlDate = new java.sql.Date(mod_barkel.getTgl_keluar().getTime());
        st.setDate(4, sqlDate);

        st.executeUpdate();
    } catch (SQLException ex) {
        java.util.logging.Logger.getLogger(DAO_BarangKeluar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } finally {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(DAO_BarangKeluar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }
    }
}


    @Override
    public void perbaruiData(Model_BarangKeluar mod_barkel) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void hapusData(Model_BarangKeluar mod_barkel) {
        PreparedStatement st = null;
        String sql = "DELETE FROM barang_keluar WHERE kode_Keluar = ?";
        try {
            st = conn.prepareStatement(sql);

            st.setString(1, mod_barkel.getKode_keluar());

            st.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_BarangKeluar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_BarangKeluar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public Model_BarangKeluar getByid(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Model_BarangKeluar> getData() {
    PreparedStatement st = null;
    List<Model_BarangKeluar> list = new ArrayList<>();
    ResultSet rs = null;
    String sql = "SELECT bk.kode_keluar, p.id_pemesan, p.nama_pemesan, a.id_admin, a.nama_admin, bk.tgl_keluar "
            + "FROM barang_keluar bk "
            + "INNER JOIN pemesan_barang p ON p.id_pemesan=bk.id_pemesan "
            + "INNER JOIN admin a ON a.id_admin=bk.id_admin "
            // + "WHERE bk.status='Lunas' "
            + "ORDER BY Kode_keluar ASC";
    try {
        st = conn.prepareStatement(sql);
        rs = st.executeQuery();
        while (rs.next()) {
            Model_BarangKeluar bk = new Model_BarangKeluar();
            Model_Pemesan sv = new Model_Pemesan();
            Model_Admin ks = new Model_Admin();

            bk.setKode_keluar(rs.getString("kode_keluar"));
            sv.setId_pemesan(rs.getString("id_pemesan"));
            sv.setNama_pemesan(rs.getString("nama_pemesan"));
            ks.setId_admin(rs.getString("id_admin"));
            ks.setNama_admin(rs.getString("nama_admin"));

            // Retrieve the date from the ResultSet and set it in Model_BarangKeluar
            java.sql.Date sqlDate = rs.getDate("tgl_keluar");
            bk.setTgl_keluar(sqlDate);

            bk.setAdmin(ks);
            bk.setPemesanbarang(sv);

            list.add(bk);
        }
        return list;
    } catch (SQLException ex) {
        java.util.logging.Logger.getLogger(DAO_BarangKeluar.class.getName()).log(Level.SEVERE, null, ex);
        return null;
    } finally {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(DAO_BarangKeluar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(DAO_BarangKeluar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}


    @Override
    public List<Model_BarangKeluar> pencarian(String id) {
        PreparedStatement st = null;
        List<Model_BarangKeluar> list = new ArrayList<>();
        ResultSet rs = null;
        String sql = "SELECT bk.kode_keluar, p.id_pemesan, p.nama_pemesan, a.id_admin, a.nama_admin, bk.tgl_keluar "
                + "FROM barang_keluar bk "
                + "INNER JOIN pemesan_barang p ON p.id_pemesan=bk.id_pemesan "
                + "INNER JOIN admin a ON a.id_admin=bk.id_admin "
//                + "WHERE bk.status='Lunas' "
                + "AND (LOWER(bk.kode_keluar) LIKE LOWER(?) "
                + "OR LOWER(p.id_pemesan) LIKE LOWER(?) "
                + "OR LOWER(p.nama_pemesan) LIKE LOWER(?) "
                + "OR LOWER(a.id_admin) LIKE LOWER(?) "
                + "OR LOWER(a.nama_admin) LIKE LOWER(?)) "
                + "ORDER BY bk.kode_keluar ASC";

        try {
            st = conn.prepareStatement(sql);

            // Set parameters for the placeholders
            for (int i = 1; i <= 5; i++) {
                st.setString(i, ""+id+"%");
            }

            rs = st.executeQuery();

            while (rs.next()) {
                Model_BarangKeluar bk = new Model_BarangKeluar();
                Model_Pemesan sv = new Model_Pemesan();
                Model_Admin ks = new Model_Admin();

                bk.setKode_keluar(rs.getString("kode_keluar"));
                sv.setId_pemesan(rs.getString("id_pemesan"));
                sv.setNama_pemesan(rs.getString("nama_pemesan"));
                ks.setId_admin(rs.getString("id_admin"));
                ks.setNama_admin(rs.getString("nama_admin"));
                java.sql.Date sqlDate = rs.getDate("tgl_keluar");
                bk.setTgl_keluar(sqlDate);

                bk.setAdmin(ks);
                bk.setPemesanbarang(sv);

                list.add(bk);
            }
            return list;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_BarangKeluar.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            // Close resources in the finally block
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_BarangKeluar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_BarangKeluar.class.getName()).log(Level.SEVERE, null, ex);
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

        String sql = "SELECT RIGHT (kode_keluar, 3) AS nomor "
                + "FROM barang_keluar "
                + "WHERE kode_keluar LIKE 'BK" + no + "%' "
                + "ORDER BY kode_keluar DESC LIMIT 1";

        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            if (rs.next()) {
                int nomor1 = Integer.parseInt(rs.getString("nomor"));
                nomor1++;
                urutan = "BK" + no + String.format("%03d", nomor1);
            } else {
                urutan = "BK" + no + "001";
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_BarangKeluar.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_BarangKeluar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return urutan;
    }

}
