package controller;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ControllerTableUser extends AbstractTableModel{
    
    String[] col = {
        "ID",
        "NAME",
        "USERNAME",
        "PASSWORD",
        "ROLE"
    };
    
    Class[] type_col = {
        
        java.lang.Integer.class,
        java.lang.String.class,
        java.lang.String.class,
        java.lang.String.class,
        java.lang.Integer.class
    };
    
    ArrayList<Object[]> array_users;

    public ControllerTableUser() {
    }

    public ControllerTableUser(ArrayList<Object[]> array_users) {
        this.array_users = array_users;
    }
    
    

    @Override
    public int getRowCount() {
        return array_users.size();
    }

    @Override
    public int getColumnCount() {
        return col.length;
    }
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return array_users.get(rowIndex)[columnIndex];
    }
    
    public String getName(int column){
        return col[column];
    }

}
