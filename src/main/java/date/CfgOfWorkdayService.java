package date;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2015/4/16.
 */
public class CfgOfWorkdayService {
    
	public int getCount(CfgOfWorkday cwd){
        int count = 0;
        Calendar now = Calendar.getInstance();
        now.setTime(cwd.getSt());
        while(now.getTime().compareTo(cwd.getEn())<=0){//遍历时间段，计算天数
            //1和7为周末  2到6为周一到周五
            Integer weekday = now.get(Calendar.DAY_OF_WEEK);
            if(cwd.getType().intValue()==1){// 1.周一到周五放假,正常工作日调休
                if(weekday>=2 && weekday<=6){//（正常周六日放假的不添加）
                    count +=1;
                }
            }else if(cwd.getType().intValue()==2){//2.休息日调上班,周六周日上班
                if(weekday==1 || weekday==7){//（正常周一到周五上班的不添加）
                    count +=1;
                }
            }
            now.add(Calendar.DAY_OF_MONTH,1);
        }
        return count;
    }

    public Date getBeforeOrAfterWorkDay(Date date ,int num){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int mod = num % 5;
        int other = num / 5 * 7;
        for (int i = 0; i < mod;) {
            cal.add(Calendar.DATE, 1);
            switch (cal.get(Calendar.DAY_OF_WEEK)) {
                case Calendar.SUNDAY:
                case Calendar.SATURDAY:
                    break;
                default:
                    i++;
                    break;
            }
        }
        if (other > 0)
            cal.add(Calendar.DATE, other);
        return new Date(cal.getTimeInMillis());
    }

}
