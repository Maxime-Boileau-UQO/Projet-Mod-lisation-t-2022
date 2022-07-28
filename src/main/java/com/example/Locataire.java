package com.example;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Locataire {
    
    //Methodes statiques(peu y acceder sans instancier un objet de cette classe) 
    public static void addLocataireToJson(String nomDUtilisateur, String motDePasse, String prenom, String nom, String coteDeCredit){
        JSONObject nouveauLocataire = new JSONObject();
        nouveauLocataire.put("Nom d'utilisateur", nomDUtilisateur);
        nouveauLocataire.put("Cote de credit", coteDeCredit);
        nouveauLocataire.put("Cherche location", true);
        JSONArray jarray = new JSONArray();
        nouveauLocataire.put("Interet", jarray);
        nouveauLocataire.put("Proprietaire actuel", "");
        JsonManager.addObjectToJsonList(nouveauLocataire, "JsonLocataire.json");
        Personne.addPersonneToJson(nomDUtilisateur, motDePasse, prenom, nom);
    }

    public static JSONObject getLocataire(String nomDUtilisateur){
        String listName = "locataireList";
        JSONObject file = JsonManager.translateFileToJSONObject("JsonLocataire.json"); 
        int index = JsonManager.findIndexInJsonList(file, "Nom d'utilisateur", nomDUtilisateur, listName);
        
        if(index == -1){
            return null;
        }
        JSONArray jArray = (JSONArray)file.get(listName);
        return (JSONObject)jArray.get(index);
    }
}