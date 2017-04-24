import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;

/**
 * Created by Arpan on 4/2/17.
 */
public class RoughJsonFactory {

    public static void main(String[] args) {

        //{"name":"Mahesh Kumar","age":21,"verified":false,"marks":[100,90,85]}
        ObjectMapper om = new ObjectMapper();

        JsonFactory jf = new JsonFactory();
        try {
            JsonParser jp = jf.createJsonParser(new File("student.json"));
//            JsonNode jn  = om.readTree(jp);
//            JsonNode NameNd = jn.path("name");
         //   System.out.println(NameNd.getTextValue());

            String fieldname = null;

            while(jp.nextToken()!= JsonToken.END_OBJECT) {
                 fieldname = jp.getCurrentName();
                if("name".equals(fieldname))
                {
                    //jp.nextToken();
                    System.out.println(jp.nextToken());
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
