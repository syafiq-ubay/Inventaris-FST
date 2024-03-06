/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tabelModel;

import Model.Model_BarangMasuk;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Asus
 */
public class TableMod_BarangMasuk extends AbstractTableModel {

    private List<Model_BarangMasuk> list = new ArrayList<>();

    public void tambahData(Model_BarangMasuk mod_barsuk) {
        list.add(mod_barsuk);
        fireTableRowsInserted(list.size() - 1, list.size() - 1);
        JOptionPane.showMessageDialog(null, "Data Berhasil Ditambahkan");
    }

    public void perbaruiData(int row, Model_BarangMasuk mod_barsuk) {
        list.add(mod_barsuk);
        fireTableDataChanged();
        JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
    }

    public void hapusData(int index) {
        list.remove(index);
        fireTableRowsDeleted(index, index);
        JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
    }

    public void clear() {
        list.clear();
        fireTableDataChanged();
    }

    public void setData(List<Model_BarangMasuk> list) {
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }

    public void setData(int index, Model_BarangMasuk mod_barsuk) {
        list.set(index, mod_barsuk);
        fireTableRowsUpdated(index, index);
    }

    public Model_BarangMasuk getData(int index) {
        return list.get(index);
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        switch (columnIndex) {
            case 0:
                return list.get(rowIndex).getKode_masuk();
            case 1:
                return list.get(rowIndex).getPesanan().getId_pesanan();
            case 2:
                return list.get(rowIndex).getTgl_masuk();
            case 3:
                return list.get(rowIndex).getPesanan().getStatus();

            default:
                return null;
        }

    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Kode Masuk";
            case 1:
                return "Id Pesanan";
            case 2:
                return "Tanggal Masuk";
            case 3:
                return "Status";

            default:
                return null;
        }
    }
}
