/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tabelModel;

import Model.Model_DetBarangMasuk;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Asus
 */
public class TableMod_DetBarangMasuk extends AbstractTableModel {
    
    private List<Model_DetBarangMasuk>list = new ArrayList<>();
    
    public void tambahData(Model_DetBarangMasuk mod_detmasuk){
        list.add(mod_detmasuk);
        fireTableRowsInserted(list.size()-1, list.size()-1);
        JOptionPane.showMessageDialog(null, "Data Berhasil Ditambahkan");
    }
    public void perbaruiData(int row,Model_DetBarangMasuk mod_detmasuk){
        list.add(mod_detmasuk);
        fireTableDataChanged();
        JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
    }
    public void hapusData(int index){
        list.remove(index);
        fireTableRowsDeleted(index, index);
        JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
    }
    public void clear(){
        list.clear();
        fireTableDataChanged();
    }
    public void setData(List<Model_DetBarangMasuk>list){
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }
    public void setData(int index, Model_DetBarangMasuk mod_detmasuk){
        list.set(index, mod_detmasuk);
        fireTableRowsUpdated(index, index);
    }
    public Model_DetBarangMasuk getData(int index){
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
        switch (columnIndex){
            case 0 : return list.get(rowIndex).getPesanan().getId_pesanan();
            case 1 : return list.get(rowIndex).getBarang().getKode_barang();
            case 2 : return list.get(rowIndex).getBarang().getNama_barang();
            case 3 : return list.get(rowIndex).getJml_masuk();
            
            default : return null;
        }
    }
    
     @Override
    public String getColumnName(int column){
       switch (column) {
            case 0 : return  "ID Pesanan";
            case 1 : return  "Kode Barang";
            case 2 : return  "Nama Barang";
            case 3 : return  "Jumlah Supply";
        
            default : return null;
        }
   }
}
