/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.DAL;

import bookstore.BLL.Book;
import bookstore.BLL.ImportCoupon;
import bookstore.BLL.ImportCouponDetail;
import java.util.ArrayList;

/**
 *
 * @author Orics
 */
public class ImportCouponDTO {
    public ArrayList<ImportCoupon> GetAllsImportCoupons(){
        ArrayList<ImportCoupon> list = new ArrayList<ImportCoupon>();
        DatabaseAccess da = new DatabaseAccess();
        String sql = "select * from NhapSach";
        DataTable dt = da.ExecuteQuery(sql);
        if(dt != null){
            for (int i = 0; i < dt.getRowCount(); i++) {
                ImportCoupon ic = new ImportCoupon();
                ic.setImportID(dt.getValue()[i][0]);
                ic.setEmployeeID(dt.getValue()[i][1]);
                ic.setDatetime((new DataType()).FormatDatetimeString(dt.getValue()[i][2], "yyyy-MM-dd hh:mm:ss", "dd-MM-yyyy hh:mm:ss"));
                ic.setTotalAmount(Integer.parseInt(dt.getValue()[i][3]));
                ic.setListImportDetail((GetImportCouponDetails(ic.getImportID())));
                list.add(ic);
            }
            return list;
        }
        return null;      
    }
    
    public ArrayList<ImportCouponDetail> GetImportCouponDetails(String ImportCouponID){
        ArrayList<ImportCouponDetail> list = new ArrayList<ImportCouponDetail>();
        DatabaseAccess da = new DatabaseAccess();
        String sql = "select * from ChiTietNhapSach where MaNhap = " + ImportCouponID;
        DataTable dt = da.ExecuteQuery(sql);
        if(dt != null){
            for (int i = 0; i < dt.getRowCount(); i++) {
                ImportCouponDetail icd = new ImportCouponDetail();
                icd.setImportCouponDetailID(dt.getValue()[i][0]);
                icd.setImportCouponID(dt.getValue()[i][1]);
                icd.setBookID(dt.getValue()[i][2]);
                try {
                    icd.setPrice(Integer.parseInt(dt.getValue()[i][3]));
                    icd.setQuantity(Integer.parseInt(dt.getValue()[i][4]));
                } catch (Exception e) {
                    System.out.println("GetImportCouponDetails: fail convert String to Int");
                    return null;
                }
                list.add(icd);
            }
            return list;
        }
        return null;
    }
    
    public int GetImportIdMax(){
        DatabaseAccess da = new DatabaseAccess();
        String sql = "select MAX(MaNhap) from NhapSach";
        String str = da.ExecuteScalarQuery(sql);
        try {
            int n =  Integer.parseInt(str);
            return n;
        } catch (Exception e) {
            System.out.println("GetImportIdMax: fail convert to int");
            return 9999;
        }
    }
    
    public void InsertDatabse(ImportCoupon ic){
        String sql =    "begin\n" +
                        "set dateformat dmy\n" +
                        "insert into NhapSach(MaNhap, MaNV, ThoiGian, TongTien) values ('@','@','@','@')\n" +
                        "end";
        sql = sql.replaceFirst("@", ic.getImportID());
        sql = sql.replaceFirst("@", ic.getEmployeeID());
        sql = sql.replaceFirst("@", ic.getDatetime());
        sql = sql.replaceFirst("@", Integer.toString(ic.getTotalAmount()));
        DatabaseAccess da = new DatabaseAccess();
        da.ExecuteNonQuery(sql);
        
        for (ImportCouponDetail icd : ic.getListImportDetail()) {
            ImportCouponDetailDTO icdDTO = new ImportCouponDetailDTO();
            icdDTO.InsertDatabase(icd);
        }   
    }
    
    public void DeleteDatabase(ImportCoupon ic){
        if(ic != null){
            DatabaseAccess da = new DatabaseAccess();
            for (ImportCouponDetail icd : ic.getListImportDetail()) {
                String sql = "delete ChitietNhapSach where MaCTNS = " + icd.getImportCouponDetailID();
                da.ExecuteNonQuery(sql);
            }
            String sql = "delete NhapSach where MaNhap = " + ic.getImportID();
            da.ExecuteNonQuery(sql);
        }
    }
}
