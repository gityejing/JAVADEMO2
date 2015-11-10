/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excult.threadBase;

/**
 * 原料
 * @author Administrator
 */
public class Animal {
    private String name ="唐老鸭";
    private String sex= "公";
    private boolean flag = false;
 
    // 在同一时间只能有一个线程操作 
    public synchronized void set(String name, String sex){
        if(!flag){
            try{
                this.wait();
            }catch(Exception e){}
        }

        //如果向下继续执行了，则表示可以设置， flag =true
        this.name=name;
        this.sex=sex;
        //修改设置的标志
        flag = false;
        //唤醒其他线程
        this.notify();
    }

    // 
    public synchronized void get(){
       if(flag){
           try{ 
               this.wait();
           }catch(Exception e){}
       }

       // 如果向下执行了，就表示允许
       System.out.println(this.name+"-->"+this.sex);
  
       //改变标签
       flag =true;
       this.notify();
    }
}
