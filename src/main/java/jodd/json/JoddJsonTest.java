package jodd.json;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import jodd.json.impl.DateJsonSerializer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JoddJsonTest {
	Person p = new Person();
	Cla cla = new Cla();
	List<Person> pl = new ArrayList<Person>();
	Map<String, Person> map1 = new HashMap<String,Person>();
	Map<String, List<Person>> map2 = new HashMap<String, List<Person>>();
	
	@Before
	public void init() {
		p.setId(123456L);
		p.setAge(28);
		p.setIdcard("429005198812131415");
		p.setName("叶静");
		p.setBirthday(new Date());
		map1.put("person", p);
		for (int i = 0; i < 10; i++) {
			Person person = new Person();
			person.setId(123456L + i);
			person.setAge(28 + i);
			person.setIdcard("429005198812131415" + i);
			person.setName("叶静" + i);
			person.setBirthday(new Date());
			pl.add(person);
		}
		map2.put("persons", pl);
		cla.setName("物流一班");
		cla.setPersons(pl);
	}

	@Test
	public void obj2json() {
//		final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String json = new JsonSerializer()
				.include(new String[] { "id", "age", "idcard", "name","birthday" })// 包含那些属性，如果有设置，下面的排除设置将不起作用
				.excludeTypes(new Class[] { String.class })// 将指定类型的属性排除在外
				.exclude(new String[] { "age", "idcard" })
				.use("birthday", new DateJsonSerializer() {
					@Override
					public void serialize(JsonContext jsonContext, Date date) {
						jsonContext.writeString(dateFormat.format(date));
					}
				})
				.serialize(p);
		System.out.println(json);
	}

	@Test
	public void list2json() {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		String json = new JsonSerializer()
				.include(new String[] { "id", "age", "idcard", "name" })// 包含那些属性，如果有设置，下面的排除设置将不起作用
				.excludeTypes(new Class[] { String.class })// 将指定类型的属性排除在外
				.exclude(new String[] { "age", "idcard" })
				.use("birthday", new DateJsonSerializer() {// 这个地方可以进行格式化处理
					@Override
					public void serialize(JsonContext jsonContext, Date date) {
						jsonContext.writeString(dateFormat.format(date));
					}
				})
				.serialize(pl);
		System.out.println(json);
	}
	
	@Test
	public void map2json() {
		JsonSerializer serializer = new JsonSerializer();
		String json = serializer.serialize(map1);
		System.out.println(json);
		json = serializer.serialize(map2);
		System.out.println(json);// 这个地方打印的结果是{}
	}
	
	@Test
	public void map2json2() {
		JsonSerializer serializer = new JsonSerializer();
		String json = serializer.serialize(map2);
		System.out.println(json);// 这个地方打印的结果是{}
	}
	
	@Test
	public void includeList2json() {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		JsonSerializer serializer = new JsonSerializer();
		// 如果一个对象中含有list、arrays，默认情况下list、arrays不会被序列化
		// 设置deep为true，才会序列化
		String json = serializer
				// 无法对集合中对象的属性进行格式化
				.use("birthday", new DateJsonSerializer() {
					@Override
					public void serialize(JsonContext jsonContext, Date date) {
						jsonContext.writeString(dateFormat.format(date));
					}
				})
				.deep(true)
				.serialize(cla);
		
		
		System.out.println(json);
	}
	
	@Test
	public void bean2json() {
		final Map<String, Object> map = new HashMap<String, Object>();
	    JsonContext jsonContext = new JsonSerializer().createJsonContext(null);

	    BeanSerializer beanSerializer = new BeanSerializer(jsonContext, p) {
	        @Override
	        protected void onSerializableProperty(
	                String propertyName, Class propertyType, Object value) {
	            map.put(propertyName, value);
	        }
	    };
	    beanSerializer.serialize();
	    
	    beanSerializer = new BeanSerializer(jsonContext, cla) {
	        @Override
	        protected void onSerializableProperty(
	                String propertyName, Class propertyType, Object value) {
	        	System.out.println(propertyName+":"+value +":"+propertyType.getName());
	            map.put(propertyName, value);
	        }
	    };
	    beanSerializer.serialize();
	    
	    String jsonString = new JsonSerializer().serialize(map);
	    System.out.println(jsonString);
	    
	}
	

}
