package base;

/**
 * Created by Administrator on 2015/4/23.
 */

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class TestGetInt {
    public static void main(String[] args) {
        double i = 2, j = 2.1, k = 2.5, m = 2.9;
        System.out.println("舍掉小数取整:Math.floor(2)=" + (int) Math.floor(i));
        System.out.println("舍掉小数取整:Math.floor(2.1)=" + (int) Math.floor(j));
        System.out.println("舍掉小数取整:Math.floor(2.5)=" + (int) Math.floor(k));
        System.out.println("舍掉小数取整:Math.floor(2.9)=" + (int) Math.floor(m));

        //这段被注释的代码不能正确的实现四舍五入取整
        System.out.println("四舍五入取整:Math.rint(2)=" + (int) Math.rint(i));
        System.out.println("四舍五入取整:Math.rint(2.1)=" + (int) Math.rint(j));
        System.out.println("四舍五入取整:Math.rint(2.5)=" + (int) Math.rint(k));
        System.out.println("四舍五入取整:Math.rint(2.9)=" + (int) Math.rint(m));

        System.out.println("四舍五入取整:(2)=" + new DecimalFormat("0").format(i));
        System.out.println("四舍五入取整:(2.1)=" + new DecimalFormat("0").format(i));
        System.out.println("四舍五入取整:(2.5)=" + new DecimalFormat("0").format(i));
        System.out.println("四舍五入取整:(2.9)=" + new DecimalFormat("0").format(i));

        System.out.println("四舍五入取整:(2)=" + new BigDecimal("2").setScale(0, BigDecimal.ROUND_HALF_UP));
        System.out.println("四舍五入取整:(2.1)=" + new BigDecimal("2.1").setScale(0, BigDecimal.ROUND_HALF_UP));
        System.out.println("四舍五入取整:(2.5)=" + new BigDecimal("2.5").setScale(0, BigDecimal.ROUND_HALF_UP));
        System.out.println("四舍五入取整:(2.9)=" + new BigDecimal("2.9").setScale(0, BigDecimal.ROUND_HALF_UP));

        System.out.println("凑整:Math.ceil(2)=" + (int) Math.ceil(i));
        System.out.println("凑整:Math.ceil(2.1)=" + (int) Math.ceil(j));
        System.out.println("凑整:Math.ceil(2.5)=" + (int) Math.ceil(k));
        System.out.println("凑整:Math.ceil(2.9)=" + (int) Math.ceil(m));

        System.out.println("舍掉小数取整:Math.floor(-2)=" + (int) Math.floor(-i));
        System.out.println("舍掉小数取整:Math.floor(-2.1)=" + (int) Math.floor(-j));
        System.out.println("舍掉小数取整:Math.floor(-2.5)=" + (int) Math.floor(-k));
        System.out.println("舍掉小数取整:Math.floor(-2.9)=" + (int) Math.floor(-m));

        System.out.println("四舍五入取整:(-2)=" + new BigDecimal("-2").setScale(0, BigDecimal.ROUND_HALF_UP));
        System.out.println("四舍五入取整:(-2.1)=" + new BigDecimal("-2.1").setScale(0, BigDecimal.ROUND_HALF_UP));
        System.out.println("四舍五入取整:(-2.5)=" + new BigDecimal("-2.5").setScale(0, BigDecimal.ROUND_HALF_UP));
        System.out.println("四舍五入取整:(-2.9)=" + new BigDecimal("-2.9").setScale(0, BigDecimal.ROUND_HALF_UP));

        System.out.println("凑整:Math.ceil(-2)=" + (int) Math.ceil(-i));
        System.out.println("凑整:Math.ceil(-2.1)=" + (int) Math.ceil(-j));
        System.out.println("凑整:Math.ceil(-2.5)=" + (int) Math.ceil(-k));
        System.out.println("凑整:Math.ceil(-2.9)=" + (int) Math.ceil(-m));

        Double value = 83.115;

        value = round(value);
        System.out.print(value);

        System.out.println(5l + 10l);
        System.out.println(getLongAdd(5l, 10l));
        System.out.println(5l - 10l);
        System.out.println(getLongSub(5l, 10l));
        System.out.println(5l * 10l);
        System.out.println(getLongMultiply(5l, 10l));
        System.out.println(5l / 10l);
        System.out.println(getLongDivide(5l, 10l, 4));
    }

    public static double round(double value) {
        return Math.round(value * 100) / 100.0;
    }

    public static Long getLongAdd(Long l1, Long l2) {
        BigDecimal bd1 = new BigDecimal(l1);
        BigDecimal bd2 = new BigDecimal(l2);
        BigDecimal bd3 = bd1.add(bd2);
        return bd3.longValue();
    }

    public static Long getLongSub(Long l1, Long l2) {
        BigDecimal bd1 = new BigDecimal(l1);
        BigDecimal bd2 = new BigDecimal(l2);
        BigDecimal bd3 = bd1.subtract(bd2);
        return bd3.longValue();
    }

    public static Double getLongMultiply(Long l1, Long l2) {
        BigDecimal bd1 = new BigDecimal(l1);
        BigDecimal bd2 = new BigDecimal(l2);
        BigDecimal bd3 = bd1.multiply(bd2);
        return bd3.doubleValue();
    }

    public static Double getLongDivide(Long l1, Long l2, int point) {
        BigDecimal bd1 = new BigDecimal(l1);
        BigDecimal bd2 = new BigDecimal(l2);
        BigDecimal bd3 = bd1.divide(bd2, point, BigDecimal.ROUND_HALF_EVEN); //point为小数点后几位
        return bd3.doubleValue();
    }

}
