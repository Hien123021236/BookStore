/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.BLL;

import bookstore.DAL.BookDTO;

/**
 *
 * @author Orics
 */
public class ImportCouponDetail {
    private String ImportCouponID;
    private String BookID;
    private String BookTitle;
    private int Price;
    private int Quantity;


    public ImportCouponDetail() {
    }

    public ImportCouponDetail(String ImportCouponID, String BookID, int Price, int Quantity) {
        this.ImportCouponID = ImportCouponID;
        this.BookID = BookID;
        this.Price = Price;
        this.Quantity = Quantity;
    }

    public String getImportCouponID() {
        return ImportCouponID;
    }

    public String getBookID() {
        return BookID;
    }

    public int getPrice() {
        return Price;
    }

    public int getQuantity() {
        return Quantity;
    }
    
    public String getBookTitle(){
        BookDTO bDTO = new BookDTO();
        BookTitle = bDTO.GetBook(BookID).getTitle();
        return BookTitle;
    }

    public void setImportCouponID(String ImportCouponID) {
        this.ImportCouponID = ImportCouponID;
    }

    public void setBookID(String BookID) {
        this.BookID = BookID;
    }

    public void setPrice(int Price) {
        this.Price = Price;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }
}
