/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket.fileDownload;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * 
 * @author Administrator
 */
public class DownLoadUtils {
	
    private String urlPath;

    /**
     * 下载文件
     * @param callback
     */
    public void downLoadResaource(ResourceDownLoad callback) {
        Object o = null;
        URL url = null;
        InputStream is = null;
        ByteArrayOutputStream out = null;
        try {
            url = new URL(urlPath);
            is = url.openStream();
            out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            while (true) {
                int byteRead = is.read(buffer);
                if (byteRead == -1) {
                    break;
                }
                out.write(buffer, 0, byteRead);
            }
            callback.getResource(out.toByteArray());
        } catch (MalformedURLException ex) {
            Logger.getLogger(DownLoadUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DownLoadUtils.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ex) {
                    Logger.getLogger(DownLoadUtils.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ex) {
                    Logger.getLogger(DownLoadUtils.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * 具体下载的接口
     * @author Administrator
     *
     */
    public interface ResourceDownLoad {
        public void getResource(byte[] o);
    }
    
    public DownLoadUtils(String urlPath) {
        this.urlPath = urlPath;
    }
}
