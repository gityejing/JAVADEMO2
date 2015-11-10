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
 * 在没有注解的情况下，保持属性的名字和表中字段的名字一致
 * 有注解的情况下，保持注解的名字和表中字段的名字一致
 * 测试的时候发现：如果完全没有注解，保持名字一致是成功的；
 * 一旦使用了注解，只要有一个字段没有注解，测试是失败的
 */
@DbTable("person")
public class Person {
    @DbColumn("id")
    private Long id;
    @DbColumn("name") // 在使用注解的情况下，如果将这个注解注释掉，测试会得不到结果
    private String name;
    @DbColumn("idcard")
    private String idcard;
    @DbColumn("age")
    private Integer age;

    public Person() {
    }

    public Person(String name, String idcard, Integer age) {
        this.name = name;
        this.idcard = idcard;
        this.age = age;
    }
    
    public Person(Long id, String name, String idcard, Integer age) {
        this.id = id;
        this.name = name;
        this.idcard = idcard;
        this.age = age;
    }
    

    public String getName() {
        return name;
    }

    public String getIdcard() {
        return idcard;
    }

    public Integer getAge() {
        return age;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", name=" + name + ", idcard=" + idcard + ", age=" + age + '}';
    }
    
    
}
