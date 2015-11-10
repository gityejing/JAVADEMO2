/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

/**
 * @author 叶静
 * 进行文件的编码转换
 */
public class FileEncodeConverter {
    private static String srcDir = "C:\\SRC"; // 原文件目录，需要根据你的情况修改
    private static String desDir = "C:\\DEST"; // 转换后的存放目录，需要根据你的情况修改
    private static String srcEncode = "GBK"; // 源文件编码
    private static String desEncode = "UTF-8"; // 输出文件编码

    // 处理的文件过滤,过滤器，只有.java文件才进行编码的转换
    // 只能过滤当前文件夹中的文件，不能过滤子文件夹中的文件
    private static FileFilter filter = new FileFilter() {
        public boolean accept(File pathname) {
            // 只处理：目录 或是 .java文件      
            if (pathname.isFile() && pathname.getName().endsWith(".java")) {
                return true;
            } else {
                return false;
            }
        }
    };

    /**
     * @param file 源文件
     * 读取源文件夹，将其中的.java文件转换成指定编码的文件
     */
    public static void readDir(File file) {
        // 以过滤器作为参数
        File[] files = file.listFiles(filter);
        for (File subFile : files) {
                // 建立目标目录
                if (subFile.isDirectory()) {
                    File file3 = new File(desDir + subFile.getAbsolutePath().substring(srcDir.length()));
                    if (!file3.exists()) {
                        file3.mkdir();
                    }
                    file3 = null;
                    readDir(subFile);
                } else {
                try {
                    convert(subFile.getAbsolutePath(), desDir + subFile.getAbsolutePath().substring(srcDir.length()), srcEncode, desEncode);
                } catch (UnsupportedEncodingException e) {
                } catch (IOException e) {
                }
            }
        }
    }


    /**
     * 将原文件夹中的.java文件，按照指定的字符集编码进行转换
     * @param srcDir 原文件目录
     * @param destDir 转换后存放的目录
     * @param encode 编码
     * @return
     */
    public static boolean conventDir(String srcDir,String destDir,String encode) {
        File srcFile = new File(srcDir);
        if(!srcFile.exists()){
            return false;
        }
        File destFile = new File(destDir);
        if(!destFile.exists()){
            destFile.mkdirs();
        }

        if(srcFile.isFile()){
            try {
                convert(srcFile.getAbsolutePath(),destDir+"//"+srcFile.getName(), JChardetFacadeUnit.getFileEncode(srcFile), encode);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            File[] files = srcFile.listFiles();
            for (File subFile : files) {
                if(subFile.isFile()&&subFile.getName().endsWith(".java")){
                    try {
                        convert(subFile.getAbsolutePath(),destDir+"//"+subFile.getName(), JChardetFacadeUnit.getFileEncode(subFile), encode);
                    } catch (UnsupportedEncodingException e) {
                    } catch (IOException e) {
                    }
                }else{
                    conventDir(subFile.getAbsolutePath(), destDir + "//" + subFile.getName()+"//", encode);
                }
            }
        }
        return true;
    }



    /**
     * 进行编码转换
     * @param infile 源文件路径
     * @param outfile 输出文件路径
     * @param from 源文件编码
     * @param to 目标文件编码
     * @throws java.io.IOException
     * @throws java.io.UnsupportedEncodingException
     */
    public static void convert(String infile, String outfile, String from,
            String to) throws IOException, UnsupportedEncodingException {
        // set up byte streams      
        InputStream in;
        if (infile != null) {
            in = new FileInputStream(infile);
        } else {
            in = System.in;
        }
        OutputStream out;
        if (outfile != null) {
            out = new FileOutputStream(outfile);
        } else {
            out = System.out;
        }

        // Use default encoding if no encoding is specified.      
        if (from == null) {
            from = System.getProperty("file.encoding");
        }
        if (to == null) {
            to = System.getProperty("file.encoding");
        }

        // Set up character stream      
        Reader r = new BufferedReader(new InputStreamReader(in, from));// 根据指定的编码读取文件内容
        Writer w = new BufferedWriter(new OutputStreamWriter(out, to));// 根据指定的编码写出文件的内容
        char[] buffer = new char[4096];
        int len;
        while ((len = r.read(buffer)) != -1) {
            w.write(buffer, 0, len);
        }
        r.close();
        w.flush();
        w.close();
    }

    public static void main(String[] args) {
        srcDir = "E:\\src"; // 原文件目录，需要根据你的情况修改
        desDir = "E:\\dest"; // 转换后的存放目录，需要根据你的情况修改 
        
        FileEncodeConverter.conventDir(srcDir,desDir,"UTF-8");
    }
}
