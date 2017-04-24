import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.JsonMappingException;

import java.io.File;
import java.io.IOException;

public class JacksonTester {
    public static void main(String args[]){

        try {
            JsonFactory jasonFactory = new JsonFactory();
            JsonParser jsonParser = jasonFactory.createJsonParser(new File("student.json"));

            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                //get the current token
                String fieldname = jsonParser.getCurrentName();

                if ("name".equals(fieldname)) {
                    //move to next token
                    jsonParser.nextToken();
                    System.out.println(jsonParser.getText());
                }
            }

        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


//
//class Student {
//    private String name;
//    private int age;
//
//    public Student(){}
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//    public String toString(){
//        return "Student [ name: "+name+", age: "+ age+ " ]";
//    }
//
//    public void printValue()
//    {
//
//        System.out.println("Student name is :-" + name + "   , Age is :- "+ age);
//    }
//}