package FeeTool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/4/17.
 */
public class RangeTempletFactory {

    private List<Range> rl = null; // 区间模板

    public static List<Range> initRangeTemplet(){

        List<Range> dl = new ArrayList<Range>();
        Range r = null;
        r = new Range(1, 0, 100,0.078f);
        dl.add(r);
        r = new Range(2, 100, 500,0.066f);
        dl.add(r);
        r = new Range(3, 500, 1000,0.054f);
        dl.add(r);
        r = new Range(4, 1000, 5000,0.042f);
        dl.add(r);
        r = new Range(5, 5000, 10000,0.03f);
        dl.add(r);
        r = new Range(6, 10000, Integer.MAX_VALUE,0.024f);
        dl.add(r);
        return dl;
    }

    /**
     * 根据金额区间和费率区间生成区间模板
     * @param moneyRange 金额区间
     * @param rateRange 费率区间
     * @return
     * @throws Exception
     */
    public static List<Range> initRangeTemplet(int[] moneyRange,float[] rateRange) throws Exception {
        List<Range> dl = new ArrayList<Range>();
        if(moneyRange.length != rateRange.length){
            throw new Exception("金额区间和费率区间长度不一致！");
        }else{
            int len = moneyRange.length;
            for (int i = 0 ;i < len ; i++){
                Range r = new Range();
                r.setLevel(i);
                r.setRate(rateRange[i]);
                if(i < len-1){
                    r.setXx(moneyRange[i]);
                    r.setSx(moneyRange[i+1]);
                }else{// 最后一个区间没有上限
                    r.setXx(moneyRange[i]);
                }
                dl.add(r);
            }
        }
        return dl;
    }
}
