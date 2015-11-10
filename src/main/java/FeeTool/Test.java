package FeeTool;

import java.util.List;

/**
 * Created by Administrator on 2015/4/17.
 */
public class Test {
    public static void main(String[] args){
        Double money = 70.0;
        int[] m = {10,20,50,100};
        float[] r = {0.1f,0.2f,0.3f,0.4f};
        try {
            List<Range> dl = RangeTempletFactory.initRangeTemplet(m,r);// 区间模板
            Double result = CaculateTool.caculate(money, dl); // 计算费用
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
