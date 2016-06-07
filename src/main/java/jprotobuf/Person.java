package jprotobuf;

import java.util.Date;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class Person {
	
	@Protobuf(fieldType = FieldType.STRING, order=1, required = true)
	private String name;
	@Protobuf(fieldType = FieldType.UINT32, order=1, required = true)
	private Integer age;
	
	private Date birthday;
	
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
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
}
