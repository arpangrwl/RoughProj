import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Arpan on 4/2/17.
 */
public class RoughJsonParson {

    public static void main(String[] args) {

        Map<String, String> m = new HashMap<String, String>();
        Iterator<String> itrMap ;
        try {
        ObjectMapper om = new ObjectMapper();
        JsonFactory jf = new JsonFactory();
        JsonParser jp = jf.createJsonParser(new File("Facebook.json"));

            String first = null, sec = null;
            JsonNode jn = om.readTree(jp);
            JsonNode rootNode = null, rootAction = null;


            JsonNode typeValue = jn.path("data");

            Iterator itr = typeValue.getElements();

            Iterator itr1 ;

            int n = 0 ;

            while(itr.hasNext())
            {
              rootNode = (JsonNode) itr.next();
              rootAction = rootNode.path("actions");

                itr1 = rootAction.getElements();
                while(itr1.hasNext()){
                    n++;
                    JsonNode jName = (JsonNode) itr1.next();
                    JsonNode NameValue = jName.path("name");

                    if(!NameValue.isMissingNode()) {
                        switch (n) {
                            case 1:
                                first = NameValue.getTextValue();
                                break;
                            case 2:
                                sec = NameValue.getTextValue();
                                break;
                        }
                    }

                }
                n = 0;
                m.put(first.toString(), sec.toString());
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        String key;

        itrMap = m.keySet().iterator();
        while(itrMap.hasNext())
        {
            key = (String)itrMap.next();
            System.out.println(key+ "     " + m.get(key));
        }

    }
}
