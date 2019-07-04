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
public class CheckDataType {
   public boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  
   }
}
