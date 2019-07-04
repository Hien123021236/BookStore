/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.DAL;

import bookstore.BLL.Employee;
import java.util.ArrayList;

/**
 *
 * @author Orics
 */
public class EmployeeDTO {
     public boolean InsertData(Employee nv){
        DatabaseAccess da = new DatabaseAccess();
        String sql = "insert into NhanVien values ('?','?','?','?','?','?')";
        sql = sql.replaceFirst("?", nv.getPassword());
        sql = sql.replaceFirst("?", nv.getRole());
        sql = sql.replaceFirst("?", nv.getName());
        sql = sql.replaceFirst("?", nv.getGender());
        sql = sql.replaceFirst("?", nv.getDateOfBirth());       
        if(da.ExecuteNonQuery(sql) > 0)
            return true;
        else
            return false;
    }
    
    public boolean UpdateData(Employee nv){
        DatabaseAccess da = new DatabaseAccess();
        String sql = "update NhanVien set Password=?, ChucVu=?, TenNV=?, GioiTinh=?, NgaySinh=? where MaNV=?";
        sql = sql.replaceFirst("?", nv.getPassword());
        sql = sql.replaceFirst("?", nv.getRole());
        sql = sql.replaceFirst("?", nv.getName());
        sql = sql.replaceFirst("?", nv.getGender());
        sql = sql.replaceFirst("?", nv.getDateOfBirth());   
        sql = sql.replaceFirst("?", nv.getEmployeeID());   
        if(da.ExecuteNonQuery(sql) > 0)
            return true;
        else
            return false;
    }
    
    public boolean DeleteData(String manv){
        DatabaseAccess da = new DatabaseAccess();
        String sql = "delete NhanVien where MaNV=?"; 
        sql = sql.replaceFirst("?", manv);   
        if(da.ExecuteNonQuery(sql) > 0)
            return true;
        else
            return false;
    }
      
    public ArrayList<Employee> GetAllEmployees(){
        ArrayList<Employee> list = new ArrayList<Employee>();
        DatabaseAccess da = new DatabaseAccess();
        String sql = "select * from NhanVien";
        DataTable dt = da.ExecuteQuery(sql);
        if(dt != null){
            for (int i = 0; i < dt.getRowCount(); i++) {
                Employee nv = new Employee( dt.getValue()[i][0],
                                            dt.getValue()[i][1],
                                            dt.getValue()[i][2],
                                            dt.getValue()[i][3],
                                            dt.getValue()[i][4],
                                            dt.getValue()[i][5]); 
                list.add(nv);
            }
        }
        return null;
    }
    
    public Employee GetNhanVien(String empid){
        DatabaseAccess da = new DatabaseAccess();
        String sql = "select * from NhanVien where MaNV = '@'";
        sql = sql.replaceAll("@", empid);
        DataTable dt = da.ExecuteQuery(sql);
        if(dt.getRowCount()>=1){
            Employee nv = new Employee( dt.getValue()[0][0],
                                        dt.getValue()[0][1], 
                                        dt.getValue()[0][2],
                                        dt.getValue()[0][3],
                                        dt.getValue()[0][4],
                                        dt.getValue()[0][5]);
            return nv;
        }
        return null;
    }
    
    public boolean CheckLogin(String manv, String pass){
        DatabaseAccess da = new DatabaseAccess();
        String sql = "select Password from NhanVien where MaNV='@'";
        sql = sql.replaceFirst("@", manv);       
        if(pass.compareTo(da.ExecuteScalarQuery(sql)) == 0){
            return true;
        }
        return false;
    }
}
