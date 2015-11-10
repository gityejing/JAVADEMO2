/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

/**
 * @author 叶静 
 */
public class FileCharsetConverter {

    public static void main(String[] args) throws Exception {
        String src = "E:\\netBean\\JavaTest\\src\\excult\\FutureTaskAndExecutor.java";
        String srcEncode = "UTF-8";
        String targetEncode = "GBK";
        
        convert(src,srcEncode,targetEncode, new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".java");
            }
        });
    }

    /**
     * 把指定文件或目录转换成指定的编码
     * 
     * @param fileName 要转换的文件
     * @param fromCharsetName 源文件的编码
     * @param toCharsetName 要转换的编码
     * @throws Exception
     */
    public static void convert(String fileName, String fromCharsetName,
            String toCharsetName) throws Exception {
        fileDirConvert(new File(fileName), fromCharsetName, toCharsetName, null);
    }

    /**
     * 把指定文件或目录转换成指定的编码
     *
     * @param file 要转换的文件或目录
     * @param fromCharsetName 源文件的编码
     * @param toCharsetName 要转换的编码
     * @throws Exception
     */
    public static void convert(File file, String fromCharsetName,
            String toCharsetName) throws Exception {
        fileDirConvert(file, fromCharsetName, toCharsetName, null);
    }

    /**
     * 把指定文件或目录转换成指定的编码
     *
     * @param file 要转换的文件或目录
     * @param fromCharsetName 源文件的编码
     * @param toCharsetName 要转换的编码
     * @param filter 文件名过滤器
     * @throws Exception
     */
    public static void convert(String fileName, String fromCharsetName,
            String toCharsetName, FilenameFilter filter) throws Exception {
        fileDirConvert(new File(fileName), fromCharsetName, toCharsetName, filter);
    }

    /**
     * 把指定文件或目录转换成指定的编码
     *
     * @param file 要转换的文件或目录
     * @param fromCharsetName 源文件的编码
     * @param toCharsetName 要转换的编码
     * @param filter 文件名过滤器
     * @throws Exception
     */
    public static void fileDirConvert(File file, String fromCharsetName,
            String toCharsetName, FilenameFilter filter) throws Exception {
        if (file.isDirectory()) {
            if(!file.exists()){
                System.out.println("文件夹不存在");
            }else{
                File[] fileList = null;
                if (filter == null) {
                    fileList = file.listFiles();
                } else {
                    fileList = file.listFiles(filter);
                }
                for (File f : fileList) {
                    fileDirConvert(f, fromCharsetName, toCharsetName, filter);
                }
            }
        } else {
            fileConvert(file, fromCharsetName, toCharsetName, filter);
        }
    }
    
    
    /**
     * 把指定文件转换成指定的编码
     *
     * @param file 要转换的文件或目录
     * @param fromCharsetName 源文件的编码
     * @param toCharsetName 要转换的编码
     * @param filter 文件名过滤器
     * @throws Exception
     */
    public static void fileConvert(File file, String fromCharsetName,
            String toCharsetName, FilenameFilter filter) throws Exception {
        if (file.isDirectory()) {
            System.out.println("====>非文件目录！！");
        } else {
            if (!file.exists()) {
                System.out.println("====>文件不存在");
            }else{
                if (filter == null || filter.accept(file.getParentFile(), file.getName())) {
                    String fileContent = getFileContentFromCharset(file,fromCharsetName);
                    saveFile2Charset(file, toCharsetName, fileContent);
                }
            }
        }
    }

    /**
     * 以指定编码方式读取文件，返回文件内容
     *
     * @param file 要转换的文件
     * @param fromCharsetName 源文件的编码
     * @return
     * @throws Exception
     */
    public static String getFileContentFromCharset(File file,
            String fromCharsetName) throws Exception {
        if (!Charset.isSupported(fromCharsetName)) {
            throw new UnsupportedCharsetException(fromCharsetName);
        }
        InputStream inputStream = new FileInputStream(file);
        InputStreamReader reader = new InputStreamReader(inputStream,fromCharsetName);
        char[] chs = new char[(int) file.length()];
        reader.read(chs);
        String str = new String(chs).trim();
        reader.close();
        return str;
    }

    /**
     * 以指定编码方式写文本文件，存在会覆盖
     *
     * @param file 要写入的文件
     * @param toCharsetName 要转换的编码
     * @param content 文件内容
     * @throws Exception
     */
    public static void saveFile2Charset(File file, String toCharsetName,
            String content) throws Exception {
        if (!Charset.isSupported(toCharsetName)) {
            throw new UnsupportedCharsetException(toCharsetName);
        }
        OutputStream outputStream = new FileOutputStream(file);
        OutputStreamWriter outWrite = new OutputStreamWriter(outputStream,toCharsetName);
        outWrite.write(content);
        outWrite.close();
    }
}
