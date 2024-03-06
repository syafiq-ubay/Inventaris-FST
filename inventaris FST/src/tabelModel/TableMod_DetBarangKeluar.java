/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TableMod;





import Model.Model_DetBarangKeluar;
import Model.Model_DetBarangMasuk;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Asus
 */
public class TableMod_DetBarangKeluar extends AbstractTableModel{
    
    private List<Model_DetBarangKeluar>list = new ArrayList<>();
  
    public void tambahData(Model_DetBarangKeluar mod_detkeluar){
        list.add(mod_detkeluar);
        fireTableRowsInserted(list.size()-1, list.size()-1);
        JOptionPane.showMessageDialog(null, "Data Berhasil Ditambahkan");
    }
    public void perbaruiData(int row,Model_DetBarangKeluar mod_detmasuk){
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
    public void setData(List<Model_DetBarangKeluar>list){
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }
    public void setData(int index, Model_DetBarangKeluar mod_detmasuk){
        list.set(index, mod_detmasuk);
        fireTableRowsUpdated(index, index);
    }
    public Model_DetBarangKeluar getData(int index){
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
            case 0 : return list.get(rowIndex).getBarangkeluar().getKode_keluar();
            case 1 : return list.get(rowIndex).getBarang().getKode_barang();
            case 2 : return list.get(rowIndex).getBarang().getNama_barang();
            case 3 : return list.get(rowIndex).getJml_keluar();
            
            default : return null;
        }
    }
    
    @Override
    public String getColumnName(int column){
       switch (column) {
            case 0 : return  "Kode Keluar";
            case 1 : return  "Kode Barang";
            case 2 : return  "Nama Barang";
            case 3 : return  "Jumlah Keluar";
        
            default : return null;
        }
   }

}
   
    

