package fastjson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;
import org.junit.Test;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

public class FastJsonUtil {
	public static Bean createBean(){
		Bean bean = new Bean();
		bean.setName("hello world");
		bean.setD(89.56);
		bean.setF(23.45F);
		bean.setL(2345L);
		bean.setDate(new Date());
		return bean;
	}
	
	@Test// "date": 1410944932296,
	public void test1(){
		SerializeConfig config = new SerializeConfig();
		// 解决Date类型转换问题
		config.put(Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd"));
		System.out.println(JSON.toJSONString(createBean(), config));
	}
	
	
}
