package jprotobuf;

import java.io.IOException;
import java.util.Date;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;

public class Main {

	public static void main(String[] args) {
		
		Person person = new Person();
		person.setName("黄晓明");
		person.setAge(26);
		
		Codec<Person> codec = ProtobufProxy.create(Person.class);
		try {
			byte[] b = codec.encode(person);
			
			Person nPerson = codec.decode(b);
			
			System.out.println(nPerson.getName());
			System.out.println(nPerson.getAge());
			System.out.println(nPerson.getBirthday());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
