/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TableMod;



import Model.Model_pesananSupply;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Asus
 */
public class TableMod_pesananSupply extends AbstractTableModel{
    
    private List<Model_pesananSupply>list = new ArrayList<>();
  
    public void tambahData(Model_pesananSupply mod_psn){
        list.add(mod_psn);
        fireTableRowsInserted(list.size()-1, list.size()-1);
        JOptionPane.showMessageDialog(null, "Data Berhasil Ditambahkan");
    }
    public void perbaruiData(int row,Model_pesananSupply mod_psn){
        list.add(mod_psn);
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
    public void setData(List<Model_pesananSupply>list){
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }
    public void setData(int index, Model_pesananSupply mod_psn){
        list.set(index, mod_psn);
        fireTableRowsUpdated(index, index);
    }
    public Model_pesananSupply getData(int index){
        return list.get(index);
    }
    
    @Override
    public int getRowCount() {
       return list.size();
    }
      @Override
    public int getColumnCount() {
       return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        switch (columnIndex){
            case 0 : return list.get(rowIndex).getId_pesanan();
            case 1 : return list.get(rowIndex).getSupplier().getId_supplier();
            case 2 : return list.get(rowIndex).getSupplier().getNama_supplier();
            case 3 : return list.get(rowIndex).getAdmin().getId_admin();
            case 4 : return list.get(rowIndex).getAdmin().getNama_admin();
            case 5 : return list.get(rowIndex).getTgl_pesanan();
            case 6 : return list.get(rowIndex).getStatus();
            
            default : return null;
        }
       
    }
     @Override
   public String getColumnName(int column){
       switch (column) {
            case 0 : return  "Id Pesanan";
            case 1 : return  "Id Supplier";
            case 2 : return  "Nama Supplier";
            case 3 : return  "Id Admin";
            case 4 : return  "Nama Admin";
            case 5 : return  "Tanggal Pesanan";
            case 6 : return  "Status";
        
            default : return null;
        }
   }
       }
   


   
    

