/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.DAL;

import bookstore.BLL.ImportCouponDetail;

/**
 *
 * @author Orics
 */
public class ImportCouponDetailDTO {
    public void InsertDatabase(ImportCouponDetail icd){
        String sql = "insert into ChiTietNhapSach values('@','@','@','@')";
        sql = sql.replaceFirst("@", icd.getImportCouponID());
        sql = sql.replaceFirst("@", icd.getBookID());
        sql = sql.replaceFirst("@", Integer.toString(icd.getPrice()));
        sql = sql.replaceFirst("@", Integer.toString(icd.getQuantity()));
        DatabaseAccess da = new DatabaseAccess();
        da.ExecuteNonQuery(sql);
    }
    
    public void DeleteDatabase(ImportCouponDetail icd){
        String sql = "delete ChiTietNhapSach where MaCTNS = " + icd.getImportCouponDetailID();
        DatabaseAccess da = new DatabaseAccess();
        da.ExecuteNonQuery(sql);
    }
}
