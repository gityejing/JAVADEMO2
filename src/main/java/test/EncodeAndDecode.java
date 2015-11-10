/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author yejing
 */
public class EncodeAndDecode {

    public static void main(String args[]) {
        try {
            String str = "jjjjj";
            str = URLEncoder.encode(str, "utf-8"); // %25E5%25AE%25A1%25E6%25A0%25B8
            str = URLEncoder.encode(str, "utf-8"); // %E5%AE%A1%E6%A0%B8
            str = URLDecoder.decode(str, "utf-8");//iso8859-1/gbk/utf-8  %E5%AE%A1%E6%A0%B8
            str = URLDecoder.decode(str, "utf-8"); // ???
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Demo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
