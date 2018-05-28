package classCopy;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
/**
 * Created by Administrator on 2015/4/15.
 */
public class ReflectUtils {

    public static Object copy(Object resource) throws Exception {
        Class<? extends Object> classType = resource.getClass();
        Object newObject = classType.newInstance();
        Field[] declaredFields = classType.getDeclaredFields();
        for (Field filed : declaredFields) {
            String firstLetter = filed.getName().substring(0, 1).toUpperCase();
            String getMethodName = "get" + firstLetter + filed.getName().substring(1);
            String setMethodName = "set" + firstLetter + filed.getName().substring(1);
            Method getMethod = classType.getMethod(getMethodName, new Class[]{});// get 方法没有参数
            Method setMethod = classType.getMethod(setMethodName, new Class[]{filed.getType()});// set 方法有参数
            Object value = getMethod.invoke(resource,new Object[]{});
            setMethod.invoke(newObject, new Object[]{value});
        }
        return newObject;
    }
}

