package aviator;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;

/**
 * 
 * @author 30000133
 *
 */
public class Demo {

	@Test
	public void test1(){
		Double songshen = 6000.00;
		String str = "songshen<=5000?10.0:13.0";
		Expression expression = AviatorEvaluator.compile(str);
		Map<String, Object> evn = new HashMap<String, Object>();
		evn.put("songshen", songshen);
		Double date = (Double) expression.execute(evn);
		System.out.println(date);
	}
}
