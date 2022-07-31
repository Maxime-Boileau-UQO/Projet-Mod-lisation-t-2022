package com.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class PropositionDeBail {
    public static void createPropositionDeBail(String idUnite, String periode, long nbPeriode, long loyer, JSONObject dateDeDebut, JSONObject dateDeFin, Boolean renouvelable, String nomProprietaire, JSONArray suplements){
        JSONObject propDeBail = new JSONObject();
        propDeBail.put("Identifiant de l'unite", idUnite);
        propDeBail.put("Periode", periode);
        propDeBail.put("Nombre de periode", nbPeriode);
        propDeBail.put("Loyer", loyer);
        propDeBail.put("Date de debut", dateDeDebut);
        propDeBail.put("Date de fin", dateDeFin);
        propDeBail.put("Renouvelable", renouvelable);
        propDeBail.put("Proprietaire", nomProprietaire);
        propDeBail.put("Supplements", suplements);

        JSONObject unite = JsonManager.getJsonObjectOfAList("JsonUnite.json", "Identifiant", idUnite);
        String id = nomProprietaire + unite.get("Nombre de proposition de bail creer").toString();
        propDeBail.put("Identifiant", id);
        long nouveauCompteur = (long)unite.get("Nombre de proposition de bail creer")+1;
        JsonManager.modifyIntArgumentOfList("JsonUnite.json", "Identifiant", idUnite, "Nombre de proposition de bail creer", nouveauCompteur);
        JsonManager.modifyBoolArgumentOfList("JsonUnite.json", "Identifiant", idUnite, "Possede une proposition de bail", true);

        JsonManager.addObjectToJsonList(propDeBail, "JsonPropositionDeBail.json");
    }
}
