/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TableMod;


import Model.Model_Pemesan;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Asus
 */
public class TableMod_Pemesan extends AbstractTableModel{
    
    private List<Model_Pemesan>list = new ArrayList<>();
  
    public void tambahData(Model_Pemesan mod_penbar){
        list.add(mod_penbar);
        fireTableRowsInserted(list.size()-1, list.size()-1);
        JOptionPane.showMessageDialog(null, "Data Berhasil Ditambahkan");
    }
    public void perbaruiData(int row,Model_Pemesan mod_penbar){
        list.add(mod_penbar);
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
    public void setData(List<Model_Pemesan>list){
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }
    public void setData(int index, Model_Pemesan mod_penbar){
        list.set(index, mod_penbar);
        fireTableRowsUpdated(index, index);
    }
    public Model_Pemesan getData(int index){
        return list.get(index);
    }
    
    @Override
    public int getRowCount() {
       return list.size();
    }
    private final String[] columnNames = {"NO", "Id Pemesan", "Nama Pemesan","Unit Pemesan"};

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
            case 0 : return list.get(rowIndex).getId_pemesan();
            case 1 : return list.get(rowIndex).getNama_pemesan();
            case 2 : return list.get(rowIndex).getUnit_pemesan();
          
           
            
            
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


   
    

