/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jodd.db.person;

import jodd.db.oom.meta.DbColumn;
import jodd.db.oom.meta.DbTable;

/**
 *
 * @author Administrator
 */
@DbTable("person")
public class Person2 {
    @DbColumn("name")
    private String name2;// 如果名称和表中字段名不一致，就必须用注解说明
    @DbColumn("idcard")
    private String idcard;
    @DbColumn("age")
    private Integer age;

    public Person2() {
    }

    public Person2(String name2, String idcard, Integer age) {
        this.name2 = name2;
        this.idcard = idcard;
        this.age = age;
    }

    public String getName2() {
        return name2;
    }

    public String getIdcard() {
        return idcard;
    }

    public Integer getAge() {
        return age;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person2{" + "name2=" + name2 + ", idcard=" + idcard + ", age=" + age + '}';
    }
    
    
}
