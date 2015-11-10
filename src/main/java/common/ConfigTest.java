/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.net.URL;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.DefaultConfigurationBuilder;

/**
 *
 * @author Administrator
 */
public class ConfigTest {

    public static void main(String[] args) {
        URL configURL = ConfigTest.class.getResource("config.xml");
        DefaultConfigurationBuilder db = new DefaultConfigurationBuilder();
        db.setConfigurationBasePath(configURL.getPath());
        Configuration configuration = null;
        try {
            configuration = db.getConfiguration();
        } catch (ConfigurationException ex) {
            
        }

        if (configuration != null) {
            String[] fileNames = configuration.getStringArray("fileName");
            for (String fileName : fileNames) {
                System.out.println(fileName);
            }
        }

    }
}
