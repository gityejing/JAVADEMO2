/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excult.threadBase;

/**
 *
 * @author Administrator
 */
public class Demo01 {
     public static void main(String[] args){
        // 红色字体中的this都是指的这个
        Animal per =new Animal();

        Pro p =new Pro(per);
        Cus c =new Cus(per);
  
        new Thread(p).start();
        new Thread(c).start();
    }
}
