package com.example;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Unite {
    
    //Methodes statiques(peu y acceder sans instancier un objet de cette classe) 
    public static void addUniteToJson(String type, String adresse, String ville, long aire, String nomProprietaire, long nbChambre, long nbSalleDeBain, String date, String condition, String etat){
        JSONObject unite = new JSONObject();
        unite.put("Type", type);
        unite.put("Adresse", adresse);
        unite.put("Ville", ville);
        unite.put("Aire", aire);
        unite.put("Nom d'utilisateur du proprietaire", nomProprietaire);
        if(nbChambre > 0){
            unite.put("Nombre de chambre", nbChambre);
        }
        unite.put("Nombre de salle de bain", nbSalleDeBain);
        unite.put("Date de contruction", date);
        unite.put("Condition", condition);
        unite.put("Etat", etat);
        if(condition.equals("Louable")&&etat.equals("Libre")){
            unite.put("Louable", true);
        }else{unite.put("Louable", false);}

        JSONObject proprietaire = JsonManager.getJsonObjectOfAList("JsonProprietaire.json", "Nom d'utilisateur", nomProprietaire);
        String id = nomProprietaire + proprietaire.get("Nombre d'unite creer").toString();
        unite.put("Identifiant", id);
        long nouveauCompteur = (long)proprietaire.get("Nombre d'unite creer")+1;
        JsonManager.modifyIntArgumentOfList("JsonProprietaire.json", "Nom d'utilisateur", nomProprietaire, "Nombre d'unite creer", nouveauCompteur);

        JsonManager.addObjectToJsonList(unite, "JsonUnite.json");
    }
}
