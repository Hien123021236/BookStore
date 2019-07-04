/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.DAL;

import bookstore.BLL.Book;
import bookstore.BLL.Employee;
import java.util.ArrayList;

/**
 *
 * @author Orics
 */
public class BookDTO {
    public boolean InsertData(Book b){
        DatabaseAccess da = new DatabaseAccess();
        String sql = "insert into Sach values ('@','@','@','@','@','@','@')";
        sql = sql.replaceFirst("@", b.getTitle());
        sql = sql.replaceFirst("@", b.getCategory());
        sql = sql.replaceFirst("@", b.getAuthor());
        sql = sql.replaceFirst("@", b.getPublisher());
        sql = sql.replaceFirst("@", Integer.toString(b.getPrice()));
        sql = sql.replaceFirst("@", Integer.toString(b.getQuantity()));    
        sql = sql.replaceFirst("@", b.getImgUrl());     
        if(da.ExecuteNonQuery(sql) > 0)
            return true;
        else
            return false;
    }
    
    public boolean UpdateData(Book b){
        DatabaseAccess da = new DatabaseAccess();
        String sql = "update Sach set   TenSach = '@'," + 
                                        "TheLoai = '@'," +
                                        "TacGia = '@'," +
                                        "NhaXuatBan = '@'," +
                                        "GiaBan = '@'," +
                                        "SoLuongTon = '@'," +
                                        "ImgUrl = '@' " +
                     "where MaSach = '@'";
                                                            
        sql = sql.replaceFirst("@", b.getTitle());
        sql = sql.replaceFirst("@", b.getCategory());
        sql = sql.replaceFirst("@", b.getAuthor());
        sql = sql.replaceFirst("@", b.getPublisher());
        sql = sql.replaceFirst("@", Integer.toString(b.getPrice()));   
        sql = sql.replaceFirst("@", Integer.toString(b.getQuantity()));  
        sql = sql.replaceFirst("@", b.getImgUrl());
        sql = sql.replaceFirst("@", b.getBookID());  
        if(da.ExecuteNonQuery(sql) > 0)
            return true;
        else
            return false;
    }
    
    public boolean DeleteData(String bookid){
        DatabaseAccess da = new DatabaseAccess();
        String sql = "delete Sach where MaSach = '@'"; 
        sql = sql.replaceFirst("@", bookid);   
        if(da.ExecuteNonQuery(sql) > 0)
            return true;
        else
            return false;
    }
   
    public ArrayList<Book> GetAllBooks(){
        ArrayList<Book> list = new ArrayList<Book>();
        DatabaseAccess da = new DatabaseAccess();
        String sql = "select * from Sach";
        DataTable dt = da.ExecuteQuery(sql);
        if(dt != null){
            for (int i = 0; i < dt.getRowCount(); i++) {
                Book b = new Book(  dt.getValue()[i][0],
                                    dt.getValue()[i][1],
                                    dt.getValue()[i][2],
                                    dt.getValue()[i][3],
                                    dt.getValue()[i][4],
                                    Integer.parseInt(dt.getValue()[i][5]),
                                    Integer.parseInt(dt.getValue()[i][6]),
                                    dt.getValue()[i][7]); 
                list.add(b);
            }
            return list;
        }
        return null;
    }
    
     public ArrayList<Book> GetBooksOfCategory(String Category){
        ArrayList<Book> list = new ArrayList<Book>();
        DatabaseAccess da = new DatabaseAccess();
        String sql = "select * from Sach where TheLoai = " + Category;
        DataTable dt = da.ExecuteQuery(sql);
        if(dt != null){
            for (int i = 0; i < dt.getRowCount(); i++) {
                Book b = new Book(  dt.getValue()[i][0],
                                    dt.getValue()[i][1],
                                    dt.getValue()[i][2],
                                    dt.getValue()[i][3],
                                    dt.getValue()[i][4],
                                    Integer.parseInt(dt.getValue()[i][5]),
                                    Integer.parseInt(dt.getValue()[i][6]),
                                    dt.getValue()[i][7]); 
                list.add(b);
            }
            return list;
        }
        return null;
    }
     
    public ArrayList<Book> GetBooksNeedImported(){
        ArrayList<Book> list = new ArrayList<Book>();
        DatabaseAccess da = new DatabaseAccess();
        String sql = "select * from Sach where SoLuongTon <= 20";
        DataTable dt = da.ExecuteQuery(sql);
        if(dt != null){
            for (int i = 0; i < dt.getRowCount(); i++) {
                Book b = new Book(  dt.getValue()[i][0],
                                    dt.getValue()[i][1],
                                    dt.getValue()[i][2],
                                    dt.getValue()[i][3],
                                    dt.getValue()[i][4],
                                    Integer.parseInt(dt.getValue()[i][5]),
                                    Integer.parseInt(dt.getValue()[i][6]),
                                    dt.getValue()[i][7]); 
                list.add(b);
            }
            return list;
        }
        return null;
    }
    
    public ArrayList<String> GetAllCategories(){
        ArrayList<String> list = new ArrayList<String>();
        DatabaseAccess da = new DatabaseAccess();
        String sql = "select distinct TheLoai from Sach";
        DataTable dt = da.ExecuteQuery(sql);
        if(dt != null){
            for (int i = 0; i < dt.getRowCount(); i++) {
               list.add(dt.getValue()[i][0]);
            }
            return list;
        }
        return null;
    }
    
    
    
    public Book GetBook(String bookid){
        DatabaseAccess da = new DatabaseAccess();
        String sql = "select * from Sach where MaSach = '@'";
        sql = sql.replaceAll("@", bookid);
        DataTable dt = da.ExecuteQuery(sql);
        if(dt.getRowCount()>=1){
             Book b = new Book( dt.getValue()[0][0],
                                dt.getValue()[0][1],
                                dt.getValue()[0][2],
                                dt.getValue()[0][3],
                                dt.getValue()[0][4],
                                Integer.parseInt(dt.getValue()[0][5]),
                                Integer.parseInt(dt.getValue()[0][6]),
                                dt.getValue()[0][7]); 
            return b;
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
