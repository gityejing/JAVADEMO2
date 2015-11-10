/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jodd.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import jodd.datetime.DateTimeStamp;
import jodd.datetime.JDateTime;
import jodd.datetime.format.JdtFormatter;

/**
 *
 * @author Administrator
 * 自定义string <---> jdatetime 的转换
 */
public class MyJdtFormatter implements JdtFormatter {
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    
    @Override // 转换日期到一个字符串
    public String convert(JDateTime jdt, String format) {
        if(format != null && !format.equals("")) sf = new SimpleDateFormat(format);
        if (jdt != null) {
            Date date = jdt.convertToDate();
            return sf.format(date);
        } else {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public DateTimeStamp parse(String value, String format) {
        if(value != null && format != null){
            sf = new SimpleDateFormat(format);
            try {
                Date date = sf.parse(value);
                JDateTime jdate = new JDateTime(date);
                return jdate.getDateTimeStamp();
            } catch (ParseException ex) {
                Logger.getLogger(MyJdtFormatter.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }else{
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

}
