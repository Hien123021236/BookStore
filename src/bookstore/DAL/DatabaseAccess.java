/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.DAL;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Orics
 */
public class DatabaseAccess {

    private static String ConnectString = "jdbc:sqlserver://DESKTOP-90A5TG5\\SQLEXPRESS:1433;databaseName=QLNhaSach";
    private static String Username = "sa";
    private static String Password = "123456";
    private static Connection con;
    
    public DatabaseAccess() {
        
    }
    
    public void Test(){
        try {
            Connection  con = DriverManager.getConnection(ConnectString,Username, Password);
            System.out.println("Connect Ok");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseAccess.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Connect fail");
        }
               
    }
    
    
    public String ExecuteScalarQuery(String sql){
        Statement St = null;
        try { 
            Connection conn = DriverManager.getConnection(ConnectString, Username, Password);
            St = conn.createStatement();
            ResultSet rs = St.executeQuery(sql);
            rs.next();
            String str = rs.getString(1);
            St.close();
            return str;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseAccess.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } 
    }
    
    public DataTable ExecuteQuery(String sql){        
        Statement pSt = null;
        DataTable dt = null;
        try { 
            Connection conn = DriverManager.getConnection(ConnectString, Username, Password);
            pSt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pSt.executeQuery(sql);
            
            rs.last();
            int col = rs.getMetaData().getColumnCount();
            int row = rs.getRow();
            rs.beforeFirst();
            
            dt = new DataTable(row, col);
            int i=0,j;
            for(i = 0; rs.next(); i++){
                for(j = 0; j<col; j++){
                     dt.getValue()[i][j] = rs.getString(j+1);
                }     
            }
            pSt.close();
            return dt;
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseAccess.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } 
        
    }
    
    public int ExecuteNonQuery(String sql){
        System.out.println("exe non sql DAL");
        PreparedStatement pSt = null;
        int nRow = 0;
        try {
            Connection conn = DriverManager.getConnection(ConnectString, Username, Password);
            pSt = conn.prepareStatement(sql);
            nRow = pSt.executeUpdate();  
            pSt.close();
            return nRow; 
        } catch (SQLException ex) {
            System.out.println(" exe non sql DAL fail");
            return 0;
        }
    }
    
    public String ExecuteFunction(String sql){
        try {
            Connection conn = DriverManager.getConnection(ConnectString, Username, Password);
            CallableStatement statement = conn.prepareCall(sql);
            statement.registerOutParameter(1, java.sql.Types.INTEGER);
            statement.execute();
            return Integer.toString(statement.getInt(1));
        } catch (SQLException ex) {
             System.out.println("Exe func fail");
        }
        return null;
    }

    public boolean ExecuteProcedure(String sql){
        System.out.println("exe proce DAL");
        try {
            Connection conn = DriverManager.getConnection(ConnectString, Username, Password);
            CallableStatement statement = conn.prepareCall(sql);
            statement.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("Exec Procedure fail");
            return false;
        }
    }
    
}
