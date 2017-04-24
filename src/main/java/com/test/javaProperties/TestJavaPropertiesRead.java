package com.test.javaProperties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Arpan on 4/3/17.
 */
public class TestJavaPropertiesRead {
    public static void main(String[] args) {

        String str = null;
        Properties p = new Properties();

        try {
            p.load(new FileInputStream("config.properties"));

            str = p.getProperty("name");
            System.out.println("str = " + str);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
