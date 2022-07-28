package com.example;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class TestJson {
    public static void main( String[] args ) 
    {
        ////// Teste différenets méthodes de manipulation de fichier JSON avec java //////
        
        /// Ecrit sur un fichier
        JSONObject personnes = new JSONObject();

        JSONArray personneslist = new JSONArray();

        JSONObject personneDetails = new JSONObject();
        personneDetails.put("Prenom", "J-P");
        personneDetails.put("Nom", "M-C");
        personneDetails.put("Age", "30");

       personneslist.add(personneDetails);

       personnes.put("personnesList", personneslist);

        updateJsonFile("JsonPersonne.json", personnes);


        /// Va lire puis ajouter de l'info a un fichier sans modifier ce qui a deja ete entre
        JSONObject personneDetails2 = new JSONObject();
        personneDetails2.put("Prenom", "N");
        personneDetails2.put("Nom", "M-C");
        personneDetails2.put("Age", "15");

        JSONObject personneObjet2 = new JSONObject();
        personneObjet2.put("personne", personneDetails2);
        

        JSONObject personneList2 = translateFileToJSONObject("JsonPersonne.json");
        JSONArray ja = (JSONArray)personneList2.get("personnesList");
        ja.add(personneDetails2);
        updateJsonFile("JsonPersonne.json", personneList2);
      


        /// Va lire le fichier puis corriger la valeur de mon age (30->20)
        modifyArgumentOfList("JsonPersonne.json", "Prenom", "J-P", "Age", "20");
    }

    ////// Foctions generales pour manipuler les fichiers JSON (a ajouter plus tard dans une classe du genre "JSON manager") //////

    public static void updateJsonFile(String targetFile, JSONObject updatedFileInfo){
        try(FileWriter file = new FileWriter(targetFile)){
            file.write(updatedFileInfo.toJSONString());
            file.flush();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    

    public static JSONObject translateFileToJSONObject(String fileName){
        JSONParser jp = new JSONParser();
        try(FileReader reader = new FileReader(fileName)){
            Object object = jp.parse(reader);
            JSONObject jObject = (JSONObject)object;
            return jObject;

        }catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void modifyArgumentOfList(String targetFile, String keySearch, String keyValue, String keyChanged, String newValue){
        
        JSONObject file = translateFileToJSONObject(targetFile);
        String rawListName = file.keySet().toString();
        String listName = rawListName.replace("[", "").replace("]", "");
        int index = findIndexInJsonList(file, keySearch, keyValue, listName);

        JSONArray jArray = (JSONArray)file.get(listName);
        JSONObject object = (JSONObject)jArray.get(index);
        //object.remove(keyChanged);
        object.put(keyChanged, newValue);
        updateJsonFile("JsonPersonne.json", file);

    }


    public static int findIndexInJsonList(JSONObject obj, String keySearch, String keyValue,String listName){
        
        JSONArray arr = (JSONArray)obj.get(listName);

        int index = 0;
        for (Object object : arr) {
            JSONObject jobject = (JSONObject)object;
            
            if(keyValue.equals((String)jobject.get(keySearch))){
                return index;
            }
            index++;
        }

        return -1;
    }
}
