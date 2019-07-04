/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.BLL;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Orics
 */
public class ImportCoupon {
    private String ImportID;
    private String EmployeeID;
    private String Datetime;
    private int TotalAmount;
    private ArrayList<ImportCouponDetail> ListImportDetail;
    
    public ImportCoupon (){
       
    }
    
    
    public String getImportID() {
        return ImportID;
    }

    public String getEmployeeID() {
        return EmployeeID;
    }

    public String getDatetime() {
        return Datetime;
    }

    public ArrayList<ImportCouponDetail> getListImportDetail() {
        return ListImportDetail;
    }

    public int getTotalAmount(){
        return TotalAmount;
    }
    
    public void setImportID(String ImportID) {
        this.ImportID = ImportID;
    }

    public void setEmployeeID(String EmployeeID) {
        this.EmployeeID = EmployeeID;
    }

    public void setDatetime(String Datetime) {
        this.Datetime = Datetime;
    }

    public void setTotalAmount(int TotalAmount) {
        this.TotalAmount = TotalAmount;
    }

    
    public void setListImportDetail(ArrayList<ImportCouponDetail> ListImportDetail) {
        this.ListImportDetail = ListImportDetail;
    }
        
    
    public static void main(String[] args) {
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try { 
            Date d = sdf.parse("2019-02-20 08:20:22");
            sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
            System.out.println(sdf.format(d));
        } catch (ParseException ex) {
            Logger.getLogger(ImportCoupon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
