package date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2015/4/16.
 */
public class WorkDayTest {
    public static void main(String args[]){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        CfgOfWorkday cwd = new CfgOfWorkday();
        try {
            cwd.setSt(sf.parse("2015-05-01"));
            cwd.setEn(sf.parse("2015-05-07"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        CfgOfWorkdayService service = new CfgOfWorkdayService();
        cwd.setType(1);// 工作日放假
        int count = service.getCount(cwd);
        System.out.println(count);
        cwd.setType(2);// 周末上班
        count = service.getCount(cwd);
        System.out.println(count);
    }
}
