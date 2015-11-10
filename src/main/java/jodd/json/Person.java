/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jodd.json;

import java.util.Date;


public class Person {

	private Long id;
	private String name;
	private String idcard;
	private Integer age;
	private Date birthday;

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

	public void setName(String name) {
		this.name = name;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person{" + "id=" + id + ", name=" + name + ", idcard=" + idcard
				+ ", age=" + age + '}';
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

}
