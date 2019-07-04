package bookstore.DAL;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Orics
 */
public class DataType {
    public String FormatDatetimeString(String datetime, String formatIn, String formatOut ){
        SimpleDateFormat sdf_in = new SimpleDateFormat(formatIn);
        SimpleDateFormat sdf_out = new SimpleDateFormat(formatOut);
        String s = null;
        try {
            s = sdf_out.format(sdf_in.parse(datetime));
        } catch (ParseException ex) {
            System.out.println("FormatDatetimeString: fail");
        }
        return s;
    }
}
