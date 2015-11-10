package base;

/**
 * Created by Administrator on 2015/4/23.
 */

import java.math.BigDecimal;
import java.math.MathContext;

public class BigDecimalTest {

    /**
     * @param args
     * @reference archie2010
     * @function 实现将 double 类型的值转换为 BigDecimal 类型的值的不同途径以及各途径间的区别
     * 一：有人可能认为在 Java 中写入 new BigDecimal(0.1) 所创建的 BigDecimal 正好等于 0.1（非标度值 1，其标度为 1），
     * 但是它实际上等于 0.1000000000000000055511151231257827021181583404541015625。
     * 这是因为 0.1 无法准确地表示为 double（或者说对于该情况，不能表示为任何有限长度的二进制小数）。
     * 这样，传入到构造方法的值不会正好等于 0.1（虽然表面上等于该值）。
     * 二：另一方面，String 构造方法是完全可预知的：写入 new BigDecimal("0.1") 将创建一个 BigDecimal，
     * 它正好 等于预期的 0.1。因此，比较而言，通常建议优先使用 String 构造方法。
     * 三：使用public static BigDecimal valueOf(double val)
     * 使用 Double.toString(double) 方法提供的 double 规范的字符串表示形式将 double 转换为 BigDecimal。
     * 这通常是将 double（或 float）转化为 BigDecimal 的首选方法，
     * 因为返回的值等于从构造 BigDecimal（使用 Double.toString(double) 得到的结果）得到的值。
     */
    public static void main(String[] args) {
        double d1, d2;
        d1 = 0.10334;
        d2 = 1234.0;
        System.out.println("直接输出double类型的值:");
        printDouble(d1, d2);
        System.out.println("将double类型直接转换为BigDecimal类型,再输出:");
        printDoubleToBigDecimal(d1, d2);
        System.out.println("将double类型转换为String类型，再转换为BigDecimal类型，再输出:");
        printDoubleToStrToBigDecimal(d1, d2);
        System.out.println("使用静态方法valueOf将double转换为BigDecimal类型并输出:");
        printDoubleToBigDecimalWithValueof(d1, d2);
        System.out.println("直接将double类型值转换为确定精度为3的BigDecimal类型值:");
        printDoubleToBigDecimalWithPrecision(d1, d2, 3);
        System.out.println("将double类型值转换为String类型再转换为确定精度(3)的BigDecimal类型值：");
        System.out.println("第三方士大夫撒旦法");
        printDoubleToBigDecimalWithPrecisionAsStr(d1, d2, 3);
    }

    /**
     * @param v1:double类型；
     * @param v2:double类型；
     * @return
     */
    public static void printDouble(double v1, double v2) {
        System.out.println("d1=" + v1);
        System.out.println("d2=" + v2);
    }

    /**
     * @param v1:double类型；
     * @param v2:double类型；
     * @return
     * @Constructor public BigDecimal(double val)
     * 将 double 转换为 BigDecimal，后者是 double 的二进制浮点值准确的十进制表示形式。
     * 返回的 BigDecimal 的标度是使 (10scale × val) 为整数的最小值。
     */
    public static void printDoubleToBigDecimal(double v1, double v2) {
        BigDecimal d1TobigDe = new BigDecimal(v1);
        BigDecimal d2TobigDe = new BigDecimal(v2);
        System.out.println("d1TobigDe=" + d1TobigDe);
        System.out.println("d2TobigDe=" + d2TobigDe);
    }

    /**
     * @param v1:double类型；
     * @param v2:double类型；
     * @return
     * @Constructor public BigDecimal(String val)
     * 将 BigDecimal 的字符串表示形式转换为 BigDecimal。
     * 字符串表示形式由可选符号 '+' ('\u002B') 或 '-' ('\u002D') 组成，
     * 后跟零或多个十进制数字（“整数”）的序列，可以选择后跟一个小数，也可以选择后跟一个指数。
     */
    public static void printDoubleToStrToBigDecimal(double v1, double v2) {
        BigDecimal d1ToStrToBigDe = new BigDecimal(String.valueOf(v1));
        BigDecimal d2ToStrToBigDe = new BigDecimal(String.valueOf(v2));
        System.out.println("d1ToStrToBigDe=" + d1ToStrToBigDe);
        System.out.println("d2ToStrToBigDe=" + d2ToStrToBigDe);
    }

    /**
     * @param v1:double类型；
     * @param v2:double类型；
     * @return
     * @Constructor public static BigDecimal valueOf(double val)
     */
    public static void printDoubleToBigDecimalWithValueof(double v1, double v2) {
        System.out.println("BigDecimal.valueOf(d1)=" + BigDecimal.valueOf(v1));
        System.out.println("BigDecimal.valueOf(d2)=" + BigDecimal.valueOf(v2));
    }

    /**
     * @param v1:double类型；
     * @param v2:double类型；
     * @return
     * @Constructor public BigDecimal(double val,MathContext mc)
     */
    public static void printDoubleToBigDecimalWithPrecision(double v1, double v2, int setPrecision) {
        BigDecimal d1ToBigDeWithPre = new BigDecimal(v1, new MathContext(setPrecision));
        BigDecimal d2ToBigDeWithPre = new BigDecimal(v2, new MathContext(setPrecision));
        System.out.println("d1ToBigDeWithPre=" + d1ToBigDeWithPre);
        System.out.println("d2ToBigDeWithPre=" + d2ToBigDeWithPre);
    }

    /**
     * @param v1:double类型；
     * @param v2:double类型；
     * @return
     * @Constructor public BigDecimal(String val,MathContext mc)
     * 将 BigDecimal 的字符串表示形式转换为 BigDecimal，
     * 接受与 BigDecimal(String) 构造方法相同的字符串（按照上下文设置进行舍入）。
     */
    public static void printDoubleToBigDecimalWithPrecisionAsStr(double v1, double v2, int setPrecision) {
        BigDecimal d1ToStrToBigDeWithPre = new BigDecimal(String.valueOf(v1), new MathContext(setPrecision));
        BigDecimal d2ToStrToBigDeWithPre = new BigDecimal(String.valueOf(v2), new MathContext(setPrecision));
        System.out.println("d1ToStrToBigDeWithPre=" + d1ToStrToBigDeWithPre);
        System.out.println("d2ToStrToBigDeWithPre=" + d2ToStrToBigDeWithPre);
    }
}
