/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.BLL;

/**
 *
 * @author Orics
 */
public class Book {
    private String BookID;
    private String Title;
    private String Category;
    private String Author;
    private String Publisher;
    private int Price;
    private int Quantity;
    private String ImgUrl;
    
    
    public Book(){
    
    }
    
    public Book(String BookID, String Title, String Category, String Author, String Publisher, int Price, int Quantity, String ImgUrl) {
        this.BookID = BookID;
        this.Title = Title;
        this.Category = Category;
        this.Author = Author;
        this.Publisher = Publisher;
        this.Price = Price;
        this.Quantity = Quantity;
        this.ImgUrl = ImgUrl;
    }
    
    public String getBookID() {
        return BookID;
    }

    public String getTitle() {
        return Title;
    }

    public String getCategory() {
        return Category;
    }

    public String getAuthor() {
        return Author;
    }

    public String getPublisher() {
        return Publisher;
    }

    public int getPrice() {
        return Price;
    }

    public int getQuantity() {
        return Quantity;
    }

    public String getImgUrl() {
        return ImgUrl;
    }
     
    public void setBookID(String BookID) {
        this.BookID = BookID;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public void setAuthor(String Author) {
        this.Author = Author;
    }

    public void setPublisher(String Publisher) {
        this.Publisher = Publisher;
    }

    public void setPrice(int Price) {
        this.Price = Price;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public void setImgUrl(String ImgUrl) {
        this.ImgUrl = ImgUrl;
    }
    
    
}
