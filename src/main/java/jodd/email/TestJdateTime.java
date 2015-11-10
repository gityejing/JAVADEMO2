/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jodd.email;


import org.apache.log4j.Logger;

import jodd.datetime.JDateTime;
import jodd.time.JDateTimeUtil;

/**
 *
 * @author Administrator
 */
public class TestJdateTime {
	private Logger logger = Logger.getLogger(TestJdateTime.class);
    public static void main(String args[]){
        new TestJdateTime();
    }
    
    public TestJdateTime() {
        JDateTime jdt = new JDateTime(1975, 1, 1);
        jdt.toString();                     // "1975-01-01 00:00:00.000"
        logger.info(jdt.toString());
        jdt.toString("YYYY.MM.DD");         // "1975.01.01"
        jdt.toString("MM: MML (MMS)");      // "01: January (Jan)"
        jdt.toString("DD is D: DL (DS)");   // "01 is 3: Wednesday (Wed)"
    
//      JDateTime jdt = new JDateTime(1968, 9, 30);
        jdt.toString("'''' is a sign, W is a week number and 'W' is a letter");
    // "' is a sign, 5 is a week number and W is a letter"

        jdt.parse("2003-11-24 23:18:38.173");
        jdt.parse("2003-11-23");                // 2003-11-23 00:00:00.000
        jdt.parse("01.01.1975", "DD.MM.YYYY");  // 1975-01-01
        jdt.parse("2001-01-31", "YYYY-MM-***"); // 2001-01-01, since day is not parsed

    //    JDateTime jdt = new JDateTime();
    //    JdtFormatter fmt = new DefaultFormatter();
    //    fmt.convert(jdt, "YYYY-MM.DD");         // external conversion

//        JdtFormat format = new JdtFormat(new DefaultFormatter(), "YYYY+DD+MM");
//        jdt.toString(format);
//        format.convert(jdt);
//
//        DateFormat df = new SimpleDateFormat();
//        df.format(jdt.convertToDate());         // date formatter
    }
    
}
