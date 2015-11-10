/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excult.threadBase;

/**
 * 生产者
 * @author Administrator
 */
public class Pro implements Runnable{
    Animal per =null;
    public Pro(Animal p){
        this.per=p;
    }

    public void run() {
        int i =0;
        while (true){
            if(i==0){
                per.set("米琪", "母");
                i=1;
            }else{
                per.set("唐老鸭", "公");
                i=0;
            }
        }
    }
}
