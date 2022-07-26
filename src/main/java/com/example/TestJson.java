package com.example;

import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONObject;
import org.json.JSONArray;;


public class TestJson {
    public static void main( String[] args )
    {
        JSONObject j = new JSONObject();
        j.put("name","JP");
        j.put("age", 18);
        JSONArray prevjobs = new JSONArray();
        prevjobs.put("student");
        prevjobs.put("couche tard");
        prevjobs.put("plongeur");
        j.put("previous jobs", prevjobs);

        String fileName = "jpProfile";
        try(FileWriter file = new FileWriter(fileName)){

            file.write(j.toString());

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
