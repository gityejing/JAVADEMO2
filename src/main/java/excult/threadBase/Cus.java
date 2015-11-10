/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excult.threadBase;

/**
 * 消费者
 * @author Administrator
 */
public class Cus implements Runnable{
    Animal per =null;
    public Cus(Animal p){
       this.per=p;
    }
 
    public void run() {
        while(true){
             per.get();
        }
    }
}
