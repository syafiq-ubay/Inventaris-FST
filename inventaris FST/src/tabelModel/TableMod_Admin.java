/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TableMod;

import Model.Model_Admin;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Asus
 */
public class TableMod_Admin extends AbstractTableModel{
    
    private List<Model_Admin>list = new ArrayList<>();
  
    public void tambahData(Model_Admin mod_admn){
        list.add(mod_admn);
        fireTableRowsInserted(list.size()-1, list.size()-1);
        JOptionPane.showMessageDialog(null, "Data Berhasil Ditambahkan");
    }
    public void perbaruiData(int row,Model_Admin mod_admn){
        list.add(mod_admn);
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
    public void setData(List<Model_Admin>list){
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }
    public void setData(int index, Model_Admin mod_admn){
        list.set(index, mod_admn);
        fireTableRowsUpdated(index, index);
    }
    public Model_Admin getData(int index){
        return list.get(index);
    }
    
    @Override
    public int getRowCount() {
       return list.size();
    }
    private final String[] columnNames = {"NO", "Id Admin", "Nama Admin","Telp Admin","Email Admin"};

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
            case 0 : return list.get(rowIndex).getId_admin();
            case 1 : return list.get(rowIndex).getNama_admin();
            case 2 : return list.get(rowIndex).getTelp_admin();
            case 3 : return list.get(rowIndex).getEmail_admin();
//            case 4 : return list.get(rowIndex).getUsername();
//            case 5 : return list.get(rowIndex).getPassword();
           
           
            
            
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


   
    

