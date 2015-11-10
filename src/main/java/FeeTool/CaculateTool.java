package FeeTool;

import java.util.List;

public class CaculateTool {
    public static RangeConfig rc = new RangeConfig(false);
    public static void registerRangeConfig(RangeConfig rc){
        rc = rc;
    }
    /**
     * 按照差额累进计数法计算
     * @param base 计算基数
     * @param rl 区间模板
     * @return
     */
	public static Double caculate(Double base, List<Range> rl) {
		int index = getIndex(base,rl);
        Double result = 0.0;
		for (int i = 0; i <= index; i++) {
            Range r = rl.get(i);
            int sx = r.getSx();
            int xx = r.getXx();
            float rate = r.getRate();
            if(rc.isEqr()){
                if(i <= index-1){
                    result += ((sx-xx-1)*rate);
                }else{
                    result += ((base-xx-1)*rate);
                }
            }else{
                if(i <= index-1){
                    result += ((sx-xx-1)*rate);
                }else{
                    result += ((base-xx)*rate);
                }
            }
		}
		return result;
	}

    /**
     * 获取区间索引
     * @param dl 区间模板
     * @param money 计算基数
     * @return
     */
    private static int getIndex(Double money, List<Range> dl) {
        int index = -1;
        if(rc.isEqr()){// 包尾不包头
            if(money > 0){
                for (Range d : dl) {
                    if(d.getXx() < money && money <= d.getSx()){ // 大于下限，小于等于上限
                        ++index;
                        break;
                    }else if(money > d.getSx()){ // 大于上限
                        ++index;
                    }
                }
            }
        }else{// 包头不包尾
            if(money > 0){
                for (Range d : dl) {
                    if(d.getXx() <= money && money < d.getSx()){ // 大于等于下限，小于上限
                        ++index;
                        break;
                    }else if(money > d.getSx()){ // 大于上限
                        ++index;
                    }
                }
            }
        }
        return index;
    }
}