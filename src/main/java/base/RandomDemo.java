package base;

import org.junit.Test;

import java.util.Random;

/**
 * Created by Administrator on 2015/4/2.
 * 随机数案例
 */
public class RandomDemo {

    public static void main(String args[]){
    	
    }
    
    

    /**
     *
     */
    @Test
    public void test1(){
        Random rnd = new Random();// 产生0到1之间的随机数
        int intRnd = (new Random())//
                .nextInt(Integer.MAX_VALUE % 1000);// 等同于这个rnd.nextInt(10);
        System.out.println(intRnd);
    }
}
