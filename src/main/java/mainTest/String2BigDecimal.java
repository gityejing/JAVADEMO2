/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainTest;

import java.math.BigDecimal;

/**
 *
 * @author Administrator
 */
public class String2BigDecimal {

    public static void main(String[] args) {
        //数字字符串 
        String StrBd = "1048576.1024";
        //构造以字符串内容为值的BigDecimal类型的变量bd 
        BigDecimal bd = new BigDecimal(StrBd);
        //设置小数位数，第一个变量是小数位数，第二个变量是取舍方法(四舍五入) 
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        //转化为字符串输出 
        String OutString = bd.toString();
        System.err.println(OutString);
    }
}
