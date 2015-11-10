/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

/**
 *
 * @author Administrator
 */
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 文件夹遍历
 *
 * @author once
 *
 */
public class DirTraversal {

    //no recursion
    public static LinkedList<File> listLinkedFiles(String strPath) {
        LinkedList<File> list = new LinkedList<File>();
        File dir = new File(strPath);
        File file[] = dir.listFiles();
        for (int i = 0; i < file.length; i++) {
            if (file[i].isDirectory()) {
                list.add(file[i]);
            } else {
                System.out.println(file[i].getAbsolutePath());
            }
        }
        File tmp;
        while (!list.isEmpty()) {
            tmp = (File) list.removeFirst();
            if (tmp.isDirectory()) {
                file = tmp.listFiles();
                if (file == null) {
                    continue;
                }
                for (int i = 0; i < file.length; i++) {
                    if (file[i].isDirectory()) {
                        list.add(file[i]);
                    } else {
                        System.out.println(file[i].getAbsolutePath());
                    }
                }
            } else {
                System.out.println(tmp.getAbsolutePath());
            }
        }
        return list;
    }

    //recursion
    public static ArrayList<File> listFiles(String strPath) {
        return refreshFileList(strPath);
    }

    public static ArrayList<File> refreshFileList(String strPath) {
        ArrayList<File> filelist = new ArrayList<File>();
        File dir = new File(strPath);
        File[] files = dir.listFiles();

        if (files == null) {
            return null;
        }
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                refreshFileList(files[i].getAbsolutePath());
            } else {
                if (files[i].getName().toLowerCase().endsWith("zip")) {
                    filelist.add(files[i]);
                }
            }
        }
        return filelist;
    }
    
    public static LinkedList<File> listLinkedFiles(File father) {
        LinkedList<File> list = new LinkedList<File>();
        File[] file = father.listFiles();
        for (int i = 0; i < file.length; i++) {
            if (file[i].isDirectory()) {
                list.add(file[i]);
                listLinkedFiles(file[i]);
            } else {
                System.out.println(file[i].getAbsolutePath());
            }
        }
        return list;
    }
    
    public static void main(String [] args){
        String dir = "d://";
        File father = new File (dir);
        listLinkedFiles(father);
    }
}
