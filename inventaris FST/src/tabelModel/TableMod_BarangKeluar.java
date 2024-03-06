/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TableMod;





import Model.Model_BarangKeluar;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Asus
 */
public class TableMod_BarangKeluar extends AbstractTableModel{
    
    private List<Model_BarangKeluar>list = new ArrayList<>();
  
    public void tambahData(Model_BarangKeluar mod_keluar){
        list.add(mod_keluar);
        fireTableRowsInserted(list.size()-1, list.size()-1);
        JOptionPane.showMessageDialog(null, "Data Berhasil Ditambahkan");
    }
    public void perbaruiData(int row,Model_BarangKeluar mod_keluar){
        list.add(mod_keluar);
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
    public void setData(List<Model_BarangKeluar>list){
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }
    public void setData(int index, Model_BarangKeluar mod_keluar){
        list.set(index, mod_keluar);
        fireTableRowsUpdated(index, index);
    }
    public Model_BarangKeluar getData(int index){
        return list.get(index);
    }
    
    @Override
    public int getRowCount() {
       return list.size();
    }

    @Override
    public int getColumnCount() {
       return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0 : return list.get(rowIndex).getKode_keluar();
            case 1 : return list.get(rowIndex).getPemesanbarang().getId_pemesan();
            case 2 : return list.get(rowIndex).getPemesanbarang().getNama_pemesan();
            case 3 : return list.get(rowIndex).getAdmin().getId_admin();
            case 4 : return list.get(rowIndex).getAdmin().getNama_admin();
            case 5 : return list.get(rowIndex).getTgl_keluar();

            default : return null;
        }
       
    }
    
    
    @Override
   public String getColumnName(int column){
       switch (column) {
            case 0 : return  "Kode Keluar";
            case 1 : return  "ID Pemesan";
            case 2 : return  "Nama Pemesan";
            case 3 : return  "ID Admin";
            case 4 : return  "Nama Admin";
            case 5 : return  "Tanggal Keluar";
        
            default : return null;
        }
   }
}

   
    

