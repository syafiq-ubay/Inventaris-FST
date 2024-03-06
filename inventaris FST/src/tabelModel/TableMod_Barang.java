/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TableMod;


import Model.Model_Barang;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Asus
 */
public class TableMod_Barang extends AbstractTableModel{
    
    private List<Model_Barang>list = new ArrayList<>();
  
    public void tambahData(Model_Barang mod_bar){
        list.add(mod_bar);
        fireTableRowsInserted(list.size()-1, list.size()-1);
        JOptionPane.showMessageDialog(null, "Data Berhasil Ditambahkan");
    }
    public void perbaruiData(int row,Model_Barang mod_bar){
        list.add(mod_bar);
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
    public void setData(List<Model_Barang>list){
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }
    public void setData(int index, Model_Barang mod_bar){
        list.set(index, mod_bar);
        fireTableRowsUpdated(index, index);
    }
    public Model_Barang getData(int index){
        return list.get(index);
    }
    
    @Override
    public int getRowCount() {
       return list.size();
    }
    private final String[] columnNames = {"NO", "Kode Barang", "Kode Jenis","Nama Jenis","Nama Barang","Stok"};

    @Override
    public int getColumnCount() {
       return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex == 0){
            return "    " + (rowIndex + 1);
        }else{
            
        
        switch (columnIndex-1){
            case 0 : return list.get(rowIndex).getKode_barang();
            case 1 : return list.get(rowIndex).getJenisbarang().getKode_jenis();
            case 2 : return list.get(rowIndex).getJenisbarang().getNama_jenis();
            case 3 : return list.get(rowIndex).getNama_barang();
            case 4 : return list.get(rowIndex).getStok();
        
            default : return null;
        }
       
    }
    }
    
    @Override
   public String getColumnName(int column){
       if(column == 0){
           return "    " + columnNames[column];
       }else{
           return columnNames[column];
       }
       }
   }


   
    

