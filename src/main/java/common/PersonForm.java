/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.util.Date;

/**
 *
 * @author Administrator
 */
public class PersonForm {
    private String name;
    private Integer age;
    private String birthday;
    private Double salary;

    public PersonForm(String name, Integer age, String birthday, Double salary) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
        this.salary = salary;
    }

    public PersonForm() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Person{" + "name=" + name + ", age=" + age + ", birthday=" + birthday + ", salary=" + salary + '}';
    }
    
}
