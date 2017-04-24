package com.test.javaProperties;

import java.io.*;
import java.util.Properties;

/**
 * Created by Arpan on 4/3/17.
 */
public class TestJavaPropertiesWrite {
    public static void main(String[] args) {

        Properties p = new Properties();
        OutputStream os = null;
        InputStream input = null;

        try{
//            os = new FileOutputStream("config.properties");
//
//            p.setProperty("name","Arpan");
//            p.setProperty("age","24");
//            p.setProperty("status","Married");
//            p.setProperty("CheckInt","No int values");
//            p.store(os,"Lets_see_what_it_is_used_for");

            input = TestJavaPropertiesWrite.class.getClassLoader().getResourceAsStream("config.properties");
            if(input==null){
                System.out.println("Sorry, unable to find file");
                return;
            }

            p.load(input);
          String age =  p.getProperty("age");

            System.out.println("age = " + age);

        }catch(FileNotFoundException e)
        {

        }
        catch(IOException e)
        {

        }


    }
}
