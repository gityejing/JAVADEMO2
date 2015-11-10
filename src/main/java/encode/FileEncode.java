/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encode;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 检查文件的编码 GBK、UTF-8
 *
 * @author 叶静
 */
public class FileEncode {

    public static String getEncode(String fileName) {
        File file = new File(fileName);
        String charset = "GBK";
        byte[] first3Bytes = new byte[3];
        BufferedInputStream bis = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(file));
            bis.mark(0);
            int read = bis.read(first3Bytes, 0, 3);
            if (read == -1) {
                return charset;
            }
            if (first3Bytes[0] == (byte) 0xFF && first3Bytes[1] == (byte) 0xFE) {
                charset = "UTF-16LE";
            } else if (first3Bytes[0] == (byte) 0xFE 
                    && first3Bytes[1] == (byte) 0xFF) {
                charset = "UTF-16BE";
            } else if (first3Bytes[0] == (byte) 0xEF
                    && first3Bytes[1] == (byte) 0xBB
                    && first3Bytes[2] == (byte) 0xBF) {
                charset = "UTF-8";
            }
        } catch (Exception ex) {
            Logger.getLogger(FileEncode.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (Exception ex) {
                    Logger.getLogger(FileEncode.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return charset;
    }

    public static String output(String fileName) throws Exception {
        String code = getEncode(fileName);
        File file = new File(fileName);
        FileInputStream fInputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(fInputStream,code);
        BufferedReader in = new BufferedReader(inputStreamReader);
        StringBuffer sBuffer = new StringBuffer("");
        String strTmp = "";
        while ((strTmp = in.readLine()) != null) {
            sBuffer.append(strTmp + "\n");
        }
        return sBuffer.toString();
    }

    public static void main(String[] args) {
        String fileName = "E:\\netBean\\JavaTest\\src\\excult\\FutureTaskAndExecutor.java";
        try {
            System.out.println(getEncode(fileName));
            System.out.println(output(fileName));
        } catch (Exception ex) {
            Logger.getLogger(FileEncode.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
