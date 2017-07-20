package com.test;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 * Created by Arpan on 4/24/17.
 */
public class testClass {
    public static void main(String[] args) {

        HashMap<String, Integer> uniqHost = new HashMap<String, Integer>();
        Iterator<String> uniqHostItr ;
        HashMap<String, String> workerDetails = new HashMap<String, String>();
        Iterator<String> workerDetailsItr ;

        String host;
        ArrayList<LinkedHashMap<String, Object>> workers = new ArrayList<LinkedHashMap<String, Object>>();

        String url="https://if1-carnival.icloud.apple.com/Carnival/services/isapplications/health?appName=ireporterstreamingservice";
        String[] command = {"curl", "-k" ,"Accept:application/json", url};
        ProcessBuilder process = new ProcessBuilder(command);
        Process p;
        try
        {
            p = process.start();
            BufferedReader reader =  new BufferedReader(new InputStreamReader(p.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line = null;
            while ( (line = reader.readLine()) != null) {
                builder.append(line);
                builder.append(System.getProperty("line.separator"));
            }
            String result = builder.toString();
           // System.out.print(result);
            JSONArray jsonarray = new JSONArray(result);
            JSONObject jsonobject;
            for (int i = 0; i < jsonarray.length(); i++) {
                jsonobject = jsonarray.getJSONObject(i);
                String hostName = jsonobject.getString("hostName");
                String health = jsonobject.getString("health");
                int instance = jsonobject.getInt("instance");
                String buildVersion = jsonobject.getString("buildVersion");

                if(uniqHost.containsKey(host = hostName.substring(0,2)))
                    uniqHost.put(host,uniqHost.get(host)+1);
                else
                    uniqHost.put(host,1);

                if(health.equals("HAPPY"))
                    workerDetails.put(hostName + ":" + String.valueOf(instance),buildVersion);
            }

            uniqHostItr = uniqHost.keySet().iterator();


            String dc;

            String workerKey;

            while(uniqHostItr.hasNext())
            {
                LinkedHashMap<String, Object> workerResp = new LinkedHashMap<String, Object>();
                dc = uniqHostItr.next();
                workerResp.put("DC", dc);
                workerResp.put("configuredWorkers",uniqHost.get(dc));

                ArrayList<LinkedHashMap<String, String>> workerArr = new ArrayList<LinkedHashMap<String, String>>();

                workerDetailsItr = workerDetails.keySet().iterator();
                while(workerDetailsItr.hasNext())
                {
                    LinkedHashMap<String, String> instanceDtl = new LinkedHashMap<String, String>();
                    workerKey = workerDetailsItr.next();
                    if(dc.equals(workerKey.substring(0,2))){
                        instanceDtl.put("hostname",workerKey.substring(0,workerKey.indexOf(':')));
                        instanceDtl.put("instance",workerKey.substring(workerKey.indexOf(':') + 1));
                        instanceDtl.put("State","HAPPY");
                        workerArr.add(instanceDtl);
                    }

                }
                workerResp.put("liveWorkers",workerArr.size());
                workerResp.put("worker", workerArr);
                workers.add(workerResp);
            }

            System.out.println(workers);

        }
        catch (IOException e)
        {   System.out.print("error");
            e.printStackTrace();
        }catch (JSONException e) {
            e.printStackTrace();
        }
        }
}
