/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Model.Model_Admin;
import Model.Model_pesananSupply;
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
import service.Service_Pesanan;

/**
 *
 * @author Asus
 */
public class DAO_pesanansuplay implements Service_Pesanan {

    private Connection conn;

    public DAO_pesanansuplay() {
        conn = koneksi.getConnection();
    }

    @Override
    public void tambahData(Model_pesananSupply mod_psn) {
        PreparedStatement st = null;
        String sql = "INSERT INTO public.pesanan(id_pesanan, id_supplier, id_admin, tgl_pesanan, status)VALUES (?, ?, ?, ?, ?);";
        try {
            st = conn.prepareStatement(sql);

            st.setString(1, mod_psn.getId_pesanan());
            st.setString(2, mod_psn.getSupplier().getId_supplier());
            st.setString(3, mod_psn.getAdmin().getId_admin());
            java.sql.Date sqlDate = new java.sql.Date(mod_psn.getTgl_pesanan().getTime());
            st.setDate(4, sqlDate);
            st.setString(5, mod_psn.getStatus());

            st.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_pesanansuplay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_pesanansuplay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void perbaruiData(Model_pesananSupply mod_psn) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void hapusData(Model_pesananSupply mod_psn) {
        PreparedStatement st = null;
        String sql = "DELETE FROM pesanan WHERE id_pesanan = ? ";
        try {
            st = conn.prepareStatement(sql);

            st.setString(1, mod_psn.getId_pesanan());

            st.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_pesanansuplay.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_pesanansuplay.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<Model_pesananSupply> getByid(String id) {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql = "SELECT p.id_pesanan,s.id_supplier, s.nama_supplier, a.id_admin, a.nama_admin, p.tgl_pesanan, p.status "
                + "FROM pesanan p "
                + "INNER JOIN supplier s on s.id_supplier=p.id_supplier "
                + "INNER JOIN admin a on a.id_admin=p.id_admin "
                + "WHERE p.id_pesanan='" + id + "' ORDER BY id_pesanan DESC ";
        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
//           
            while (rs.next()) {
                Model_pesananSupply psn = new Model_pesananSupply();
                Model_Supplier kar = new Model_Supplier();
                Model_Admin dis = new Model_Admin();

                psn.setId_pesanan(rs.getString("id_pesanan"));
                kar.setId_supplier(rs.getString("id_supplier"));
                kar.setNama_supplier(rs.getString("nama_supplier"));
                dis.setId_admin(rs.getString("id_admin"));
                dis.setNama_admin(rs.getString("nama_admin"));
//                dis.setNama_distributor("nama_distributor");
//                psn.setJml_pesanan(rs.getInt("jml_pesanan"));
                psn.setTgl_pesanan(rs.getDate("tgl_pesanan"));
                psn.setStatus(rs.getString("status"));

                psn.setAdmin(dis);
                psn.setSupplier(kar);

                list.add(psn);

            }
            return list;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_pesanansuplay.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_pesanansuplay.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_pesanansuplay.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<Model_pesananSupply> getData() {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs = null;
        String sql = "SELECT p.id_pesanan,s.id_supplier, s.nama_supplier, a.id_admin, a.nama_admin, p.tgl_pesanan, p.status "
                + "FROM pesanan p "
                + "INNER JOIN supplier s on s.id_supplier=p.id_supplier "
                + "INNER JOIN admin a on a.id_admin=p.id_admin ";
        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
//           
            while (rs.next()) {
                Model_pesananSupply psn = new Model_pesananSupply();
                Model_Supplier kar = new Model_Supplier();
                Model_Admin dis = new Model_Admin();

                psn.setId_pesanan(rs.getString("id_pesanan"));
                kar.setId_supplier(rs.getString("id_supplier"));
                kar.setNama_supplier(rs.getString("nama_supplier"));
                dis.setId_admin(rs.getString("id_admin"));
                dis.setNama_admin(rs.getString("nama_admin"));
//                dis.setNama_distributor("nama_distributor");
//                psn.setJml_pesanan(rs.getInt("jml_pesanan"));
                psn.setTgl_pesanan(rs.getDate ("tgl_pesanan"));
                psn.setStatus(rs.getString("status"));

                psn.setAdmin(dis);
                psn.setSupplier(kar);

                list.add(psn);

            }
            return list;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_pesanansuplay.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_pesanansuplay.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_pesanansuplay.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public List<Model_pesananSupply> pencarian(String id) {
    PreparedStatement st = null;
    List<Model_pesananSupply> list = new ArrayList<>();
    ResultSet rs = null;
    String sql = "SELECT p.id_pesanan, s.id_supplier, s.nama_supplier, a.id_admin, a.nama_admin, p.tgl_pesanan, p.status "
            + "FROM pesanan p "
            + "INNER JOIN supplier s ON s.id_supplier = p.id_supplier "
            + "INNER JOIN admin a ON a.id_admin = p.id_admin "
            + "WHERE LOWER(p.id_pesanan) LIKE LOWER(?) "
            + "OR LOWER(s.id_supplier) LIKE LOWER(?) "
            + "OR LOWER(s.nama_supplier) LIKE LOWER(?) "
            + "OR LOWER(a.id_admin) LIKE LOWER(?) "
            + "OR LOWER(a.nama_admin) LIKE LOWER(?) "
            + "ORDER BY p.id_pesanan ASC";

    try {
        st = conn.prepareStatement(sql);

        // Set parameters for the placeholders
        for (int i = 1; i <= 5; i++) {
            st.setString(i, ""+id+"%");
        }

        rs = st.executeQuery();

        while (rs.next()) {
            Model_pesananSupply psn = new Model_pesananSupply();
            Model_Supplier kar = new Model_Supplier();
            Model_Admin dis = new Model_Admin();

            psn.setId_pesanan(rs.getString("id_pesanan"));
            kar.setId_supplier(rs.getString("id_supplier"));
            kar.setNama_supplier(rs.getString("nama_supplier"));
            dis.setId_admin(rs.getString("id_admin"));
            dis.setNama_admin(rs.getString("nama_admin"));
            psn.setTgl_pesanan(rs.getDate("tgl_pesanan"));
            psn.setStatus(rs.getString("status"));

            psn.setAdmin(dis);
            psn.setSupplier(kar);

            list.add(psn);
        }
        return list;
    } catch (SQLException ex) {
        java.util.logging.Logger.getLogger(DAO_pesanansuplay.class.getName()).log(Level.SEVERE, null, ex);
        return null;
    } finally {
        // Close resources in the finally block
        if (st != null) {
            try {
                st.close();
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(DAO_pesanansuplay.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(DAO_pesanansuplay.class.getName()).log(Level.SEVERE, null, ex);
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

        String sql = "SELECT RIGHT (id_pesanan, 3) AS nomor "
                + "FROM pesanan "
                + "WHERE id_pesanan LIKE 'PB" + no + "%' "
                + "ORDER BY id_pesanan DESC LIMIT 1";

        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            if (rs.next()) {
                int nomor1 = Integer.parseInt(rs.getString("nomor"));
                nomor1++;
                urutan = "PB" + no + String.format("%03d", nomor1);
            } else {
                urutan = "PB" + no + "001";
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_pesanansuplay.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_pesanansuplay.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return urutan;
    }

    @Override
    public void ubahData(Model_pesananSupply mod_psn) {
        PreparedStatement st = null;
        String sql = "UPDATE pesanan SET status = ? "
                + "WHERE id_pesanan ='" + mod_psn.getId_pesanan() + "'";
        try {

            st = conn.prepareStatement(sql);

//           
            st.setString(1, mod_psn.getStatus());

            st.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DAO_pesanansuplay.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DAO_pesanansuplay.class.getName()).log(Level.SEVERE, null, ex);

                }
            }
        }
    }

}
