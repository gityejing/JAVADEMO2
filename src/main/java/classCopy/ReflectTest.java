package classCopy;

import org.junit.Test;
import org.junit.runners.JUnit4;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2015/4/15.
 */

public class ReflectTest {

    @Test
    public void test_copy() throws Exception {
        Person person = new Person("andy", 1);
        Person copyPerson = (Person) ReflectUtils.copy(person);
        System.out.println(copyPerson);
    }
}




