/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket.fileDownload;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class DownLoadTest {
    public static void main(String [] args){
        String urlPath = "http://pic7.nipic.com/20100603/668573_211435005500_2.jpg";
        DownLoadUtils du = new DownLoadUtils(urlPath);
        du.downLoadResaource(new DownLoadUtils.ResourceDownLoad() {
            @Override
            public void getResource(byte[] o) {
                FileOutputStream outputStream = null;
                try {
                    outputStream = new FileOutputStream(new File("d:/a.jpg"));
                    outputStream.write(o, 0, o.length);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(DownLoadTest.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(DownLoadTest.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    if(outputStream != null){
                        try {
                            outputStream.close();
                        } catch (IOException ex) {
                            Logger.getLogger(DownLoadTest.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        });
    }
}
