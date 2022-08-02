package com.example;

import java.util.ArrayList;

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
        propDeBail.put("Visible", true);
        propDeBail.put("Est pour un renouvlement", false);

        JSONObject unite = JsonManager.getJsonObjectOfAList("JsonUnite.json", "Identifiant", idUnite);
        String id = nomProprietaire + unite.get("Nombre de proposition de bail creer").toString();
        propDeBail.put("Identifiant", id);
        long nouveauCompteur = (long)unite.get("Nombre de proposition de bail creer")+1;
        JsonManager.modifyIntArgumentOfList("JsonUnite.json", "Identifiant", idUnite, "Nombre de proposition de bail creer", nouveauCompteur);
        JsonManager.modifyBoolArgumentOfList("JsonUnite.json", "Identifiant", idUnite, "Possede une proposition de bail", true);

        JsonManager.addObjectToJsonList(propDeBail, "JsonPropositionDeBail.json");
    }


    public static void modifierPropositionDeBail(String nomProprietaire){
        System.out.println("Voici les propositions que vous pouvez modiffier:");
        JSONArray propositions = JsonManager.getArrayOfJsonFile("JsonUnite.json");
        ArrayList<String> listeIdentifiant = new ArrayList<String>();
        ArrayList<String> listeIndexe = new ArrayList<String>(); 
        listeIndexe.add("r");

        int compte = 0;
        System.out.println("X - ///// Adresse /// Date de début /// Durée d'une période  /// Nombre de période /// Loyer /////");
        for (Object object : propositions) {
            JSONObject proposition = (JSONObject)object;
            JSONObject date = (JSONObject)proposition.get("Date de debut");
            JSONObject unite = JsonManager.getJsonObjectOfAList("JsonUnite.json", "Identifiant", proposition.get("Identifiant de l'unite").toString());
            if(proposition.get("Nom d'utilisateur du proprietaire").equals(nomProprietaire)&&!(Boolean)proposition.get("Est pour un renouvelement")){
                listeIndexe.add(String.valueOf(compte));
                listeIdentifiant.add(proposition.get("Identifiant").toString());
                System.out.println(compte + " - ///// "+unite.get("Adresse")+
                " /// Date: "+date.get("Annee")+"/"+date.get("Mois")+"/"+date.get("Jour")+" "+
                date.get("Heure")+":"+date.get("Minute")+":"+date.get("Seconde")+
                " /// "+proposition.get("Periode").toString()+
                " /// "+proposition.get("Nombre de periode").toString()+
                " /// "+proposition.get("Loyer").toString()+"$ /////");
                compte++;
            }
        }
        //2 - Demande quelle unité il veut modifier ou s'il veut retourner au menu unité
        String[] stringArray1 = new String[listeIndexe.size()];
        stringArray1 = listeIndexe.toArray(stringArray1);
        System.out.println("\nVeuillez entrer le numéro de la poposition que vous désirez modiffier ou enter r pour retourner au menu des unités.");
        String reponse1 = Interface.takeValidAnswer(stringArray1);
        if(!reponse1.equals("r")){
            return;
        }
        String nomProposition = listeIdentifiant.get(Integer.valueOf(reponse1));
        JSONObject proposition = JsonManager.getJsonObjectOfAList("JsonPropositionDeBail.json", "Identifiant", nomProposition);
        ArrayList<String> stringArrayList1 = new ArrayList<String>();
        //3 - Demande quel élément de l'unité il veut changer et s'il veut retourner au menu unité
        System.out.println("Quel élément de l'unité voulez vous changer?");
        System.out.println("- L'aire = a");
        stringArrayList1.add("a");
        if(proposition.get("Type").toString().equals("Logement")){
            System.out.println("- Le nombre de chambre de l'unité = m");
            stringArrayList1.add("c");
        }
        System.out.println("- Le nombre de salle de bain de l'unité = s");
        stringArrayList1.add("s");
        System.out.println("- La condition de l'unité = c");
        stringArrayList1.add("a");
        String[] stringArray2 = new String[stringArrayList1.size()];
        stringArray1 = stringArrayList1.toArray(stringArray2);
        String reponse3 = Interface.takeValidAnswer(stringArray2);
        //4 - Input la nouvelle valeur et met dans une boucle jusqu'à avoir une valeur satisfaisante
        String elementChange;
        if(reponse3.equals("a")){elementChange = "Aire";}
        else if(reponse3.equals("m")){elementChange = "Nombre de chambre";}
        else if(reponse3.equals("s")){elementChange = "Nombre de salle de bain";}
        else{elementChange = "Condition";}

        System.out.println();
        if(reponse3.equals("c")){
            System.out.println("Enterz la nouvelle condition de l'unité. (l = louable, r = pas louable pour réparation)");
            String[] stringArray3 = {"l","r"};
            String reponse4 = Interface.takeValidAnswer(stringArray3);
            if(reponse4.equals("l")){
                JsonManager.modifyArgumentOfList("JsonUnite.json", "Identifiant de l'unite", nomProposition, elementChange, "Louable");
            }
            else{
                JsonManager.modifyArgumentOfList("JsonUnite.json", "Identifiant", nomProposition, elementChange, "Pas louable pour reparation");
                JSONArray jArray2 = JsonManager.getArrayOfJsonFile("JsonPropositionDeBail.json");
                for (Object object : jArray2) {
                    //JSONObject proposition = (JSONObject)object;
                    if(proposition.get("Proprietaire").equals(nomProprietaire)){
                        JsonManager.removeObjectToJsonList("Proprietaire", nomProprietaire, "JsonPropositionDeBail.json");
                        break;
                    }
                }
            }
        }
        else{
            System.out.println("Entrez la nouvelle valeur désiré.");
            Long reponse = Interface.takePositiveInteger();
            JsonManager.modifyIntArgumentOfList("JsonUnite.json", "Identifiant", nomProposition, elementChange, reponse);
        }
        //5 - Retour au menu
    } 
}
