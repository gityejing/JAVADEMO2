package com.gootrip.util;

import java.util.*;
import java.util.regex.*;

/**
 * 锟斤拷锟角革拷锟斤拷锟斤拷锟斤拷式应锟斤拷锟洁，锟斤拷锟斤拷匹锟斤拷锟斤拷婊伙拷执锟斤拷玫锟�
 * @author
 * @version
 */
public class RegExUtil {

    /**
     * 要锟斤拷锟叫⌒达拷锟狡ワ拷锟斤拷锟斤拷锟斤拷锟绞�
     *
     * @param pattern 锟斤拷锟斤拷锟斤拷式模式
     * @param str 要匹锟斤拷锟斤拷执锟�
     * @return boolean值
     * @since 1.0
     */
    public static final boolean ereg(String pattern, String str) throws PatternSyntaxException {
        try {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(str);
            return m.find();
        } catch (PatternSyntaxException e) {
            throw e;
        }
    }

    /**
     * 匹锟斤拷锟斤拷锟芥换锟街达拷
     *
     * @param pattern 锟斤拷锟斤拷锟斤拷式模式
     * @param newstr 要锟芥换匹锟戒到锟斤拷锟斤拷锟街达拷
     * @param str 原始锟街达拷
     * @return 匹锟斤拷锟斤拷锟街凤拷
     * @since 1.0
     */
    public static final String ereg_replace(String pattern, String newstr, String str) throws PatternSyntaxException {
        try {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(str);
            return m.replaceAll(newstr);
        } catch (PatternSyntaxException e) {
            throw e;
        }
    }

    /**
     * 锟斤拷要锟斤拷锟斤拷模锟斤拷锟斤拷模锟斤拷锟角凤拷锟斤拷锟斤拷锟斤拷 锟窖诧拷锟揭碉拷锟斤拷元锟截加碉拷vector锟斤拷
     *
     * @param pattern为锟斤拷锟斤拷锟斤拷式模式
     * @param str 原始锟街达拷
     * @return vector
     * @since 1.0
     */
    public static final Vector splitTags2Vector(String pattern, String str) throws PatternSyntaxException {
        Vector vector = new Vector();
        try {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(str);
            while (m.find()) {
                vector.add(ereg_replace("(\\[\\#)|(\\#\\])", "", m.group()));
            }
            return vector;
        } catch (PatternSyntaxException e) {
            throw e;
        }
    }

    /**
     * 模锟斤拷锟角凤拷锟斤拷锟斤拷锟斤拷 锟斤拷锟斤拷锟斤拷要锟角把诧拷锟揭碉拷锟斤拷元锟截加碉拷vector锟斤拷
     *
     * @param pattern为锟斤拷锟斤拷锟斤拷式模式
     * @param str 原始锟街达拷
     * @since 1.0
     */
    public static final String[] splitTags(String pattern, String str) {
        try {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(str);
            String[] array = new String[m.groupCount()];
            int i = 0;
            while (m.find()) {
                array[i] = ereg_replace("(\\[\\#)|(\\#\\])", "", m.group());
                i++;
            }
            return array;
        } catch (PatternSyntaxException e) {
            throw e;
        }
    }

    /**
     * 匹锟斤拷锟斤拷锟叫凤拷锟侥Ｊ揭拷锟斤拷锟街达拷锟斤拷锟接碉拷矢锟斤拷vector锟斤拷锟斤拷锟斤拷
     *
     * @param pattern为锟斤拷锟斤拷锟斤拷式模式
     * @param str 原始锟街达拷
     * @return vector
     * @since 1.0
     */
    public static final Vector regMatchAll2Vector(String pattern, String str) throws PatternSyntaxException {
        Vector vector = new Vector();
        try {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(str);
            while (m.find()) {
                vector.add(m.group());
            }
            return vector;
        } catch (PatternSyntaxException e) {
            throw e;
        }
    }

    /**
     * 匹锟斤拷锟斤拷锟叫凤拷锟侥Ｊ揭拷锟斤拷锟街达拷锟斤拷锟接碉拷锟街凤拷锟斤拷锟斤拷锟斤拷
     *
     * @param pattern为锟斤拷锟斤拷锟斤拷式模式
     * @param str 原始锟街达拷
     * @return array
     * @since 1.0
     */
    public static final String[] regMatchAll2Array(String pattern, String str) throws PatternSyntaxException {
        try {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(str);
            String[] array = new String[m.groupCount()];
            int i = 0;
            while (m.find()) {
                array[i] = m.group();
                i++;
            }
            return array;
        } catch (PatternSyntaxException e) {
            throw e;
        }
    }

    /**
     * 转锟斤拷锟斤拷锟斤拷锟斤拷式锟街凤拷(之锟斤拷锟斤拷锟斤拷要锟斤拷\锟斤拷$锟街凤拷锟斤拷escapeDollarBackslash锟斤拷锟斤拷锟侥凤拷式锟斤拷锟斤拷为锟斤拷repalceAll锟角诧拷锟叫的ｏ拷锟津单碉拷锟斤拷锟斤拷"$".repalceAll("\\$","\\\\$")锟斤拷岱拷锟斤拷锟斤拷锟斤拷锟矫会导锟斤拷锟斤拷锟斤拷越锟斤拷锟斤拷锟�
     *
     * @param pattern为锟斤拷锟斤拷锟斤拷式模式
     * @param str 原始锟街达拷
     * @return array
     * @since 1.0
     */
    public static String escapeDollarBackslash(String original) {
        StringBuffer buffer = new StringBuffer(original.length());
        for (int i = 0; i < original.length(); i++) {
            char c = original.charAt(i);
            if (c == '\\' || c == '$') {
                buffer.append("\\").append(c);
            } else {
                buffer.append(c);
            }
        }
        return buffer.toString();
    }

    /**
     * 锟斤拷取指锟斤拷锟街达拷锟侥猴拷锟斤拷 锟斤拷锟斤拷锟斤拷要锟角把诧拷锟揭碉拷锟斤拷元锟斤拷
     *
     * @param pattern为锟斤拷锟斤拷锟斤拷式模式
     * @param str 原始锟街达拷
     * @since 1.0
     */
    public static final String fetchStr(String pattern, String str) {
        String returnValue = null;
        try {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(str);
            while (m.find()) {
                returnValue = m.group();
            }
            return returnValue;
        } catch (PatternSyntaxException e) {
            return returnValue;
        }
    }

    private RegExUtil() {
    }
}
