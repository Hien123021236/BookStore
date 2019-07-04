/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.DAL;

/**
 *
 * @author Orics
 */
public class DataTable {
    private int ColumnCount, RowCount;
    private String Value[][];

    public DataTable (int  RowCount ,int ColumnCount){
        this.ColumnCount = ColumnCount;
        this.RowCount = RowCount;
        this.Value = new String[RowCount][ColumnCount];
    }
      
    public int getColumnCount() {
        return ColumnCount;
    }

    public int getRowCount() {
        return RowCount;
    }

    public String[][] getValue() {
        return Value;
    }

    public void setColumnCount(int ColumnCount) {
        this.ColumnCount = ColumnCount;
    }

    public void setRowCount(int RowCount) {
        this.RowCount = RowCount;
    }

    public void setValue(String[][] Value) {
        this.Value = Value;
    }
    
    
    
}
