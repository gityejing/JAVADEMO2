/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

/**
 *
 * @author Administrator
 */
public class ClassUtils {
   /*
    *  为了保证原bean中的数据不为空，需要自己设置转换器。
    *  当执行BeanUtils.copyProperties(dest, src);
       时会首先去调用转换器(Converter接口的实现方法convert)，然后再执行转换.
    */
    public static void copyBean(Object src, Object dest) {
        try {
            // 注册日期转换器   frombean-->birthday=""  user-birhday=null
            ConvertUtils.register(new Converter() {
                public Object convert(Class type, Object value) {  //198a
                    if (value == null) {
                        return null;
                    }
                    String s = (String) value;
                    if (s.trim().equals("")) {
                        return null;
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        return sdf.parse(s);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }
            },Date.class);
            //实现bean的拷呗
            BeanUtils.copyProperties(dest, src);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void copyProperty(Object dest, String name, Object value) {
        try {
            // 注册日期转换器从字符串到Date
            ConvertUtils.register(new Converter() {
                public Object convert(Class type, Object value) {  //198a
                    if (value == null) {
                        return null;
                    }
                    String s = (String) value;
                    if (s.trim().equals("")) {
                        return null;
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        return sdf.parse(s);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }
            }, Date.class);
            BeanUtils.copyProperty(dest, name, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String args[]) {
        Person p1 = new Person("yejing", 27, new Date(), 6500.0);
        PersonForm form = new PersonForm("yejing", 27,"2015-03-12", 6500.0);
        Person p2 = new Person();
        copyBean(form, p2);
        System.out.println(p2);
        
        copyProperty(p2,"birthday","2015-03-11");
        copyProperty(p2,"name","叶静");
        System.out.println(p2);
    }
}
