package com.example;

import java.util.Scanner;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Interface{

    public static void main( String[] args ) throws ParseException {
        startSystem();
    }


    private static void startSystem() throws ParseException{
        System.out.println();
        System.out.println("//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
        System.out.println("////  0      000   0000  000  00000 00000  000  0   0     00000     0   0 00000 0   0 0   0 00000 00000  000  00  ////");
        System.out.println("////  0     0   0 0     0   0   0     0   0   0 00  0     0         00 00   0   00  0 0   0   0   0     0     00  ////");
        System.out.println("////  0     0   0 0     00000   0     0   0   0 0 0 0     00000     0 0 0   0   0 0 0 0   0   0   00000  00   00  ////");
        System.out.println("////  0     0   0 0     0   0   0     0   0   0 0  00         0     0   0   0   0  00 0   0   0   0        0      ////");
        System.out.println("////  00000  000   0000 0   0   0   00000  000  0   0     0000      0   0 00000 0   0  000    0   00000 000   00  ////");
        System.out.println("//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////\n\n\n");
        
        System.out.println("Bivenu sur le logiciel de location de propriété \"Location 5 minutes!\"\nVous pouvez quitter le logiciel à tout moment avec la comande Ctrl+c.\n\n");
        System.out.println("Voulez-vous vous conecter ou vous inscrire? (c = connecter, i = inscrire)");
        String[] stringArray0 = {"c","i"};
        String reponse0 = takeValidAnswer(stringArray0);

        String[] stringArray1 = {"l","p"};
        if(reponse0.equals("c")){
            System.out.println("Voulez-vous vous connecter comme un locataire ou comme un propriétaire? (l = locataire, p = propriétire)");
            String reponse1 = takeValidAnswer(stringArray1);
            if(reponse1.equals("l")){
                seConnecterLocataire();
            }else{
                seConnecterProprietaire();
            }
        }
        else{
            System.out.println("Voulez-vous vous incrire comme un locataire ou comme un propriétaire? (l = locataire, p = propriétire)");
            String reponse1 = takeValidAnswer(stringArray1);
            if(reponse1.equals("l")){
                sInscrireLocataire();
            }else{
                sInscrireProprietaire();
            }
        }

    }


    private static String takeValidAnswer(String[] arguments){
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.print("Réponse: ");
            String reponse = scanner.next();

            for (String string : arguments) {
                if(reponse.equals(string)){
                    return reponse;
                }
            }
            System.out.println("ERREUR: Réponse invalide. Svp entrer une réponse valide.");
        }
    }

    private static long takePositiveInteger(){
        Scanner scanner = new Scanner(System.in);
        while(true){
            try{
                System.out.print("Réponse: ");
                long reponse = Integer.valueOf(scanner.next());
                if(reponse >= 0){
                    return reponse;
                }
                System.out.println("ERREUR: Réponse invalide. Svp entrer une réponse valide.");
            }catch(Exception e){
                System.out.println("ERREUR: Réponse invalide. Svp entrer une réponse valide.");
            }
        } 
    }


    ////////////////////////////////////////
    /////// Fonctions pour locataire ///////
    ////////////////////////////////////////

    private static void seConnecterLocataire() throws ParseException{
        System.out.println("\n\n////// Connexion - Locataire //////");
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.print("Veuillex entrer votre nom d'utilisayeur: ");
            String nomDUtilisateur = scanner.next();
            System.out.print("Veuillex entrer votre mot de passe: ");
            String motDePasse = scanner.next();

            if(Locataire.getLocataire(nomDUtilisateur) != null){
                JSONObject personne = Personne.getPersonne(nomDUtilisateur);
                String motDePasseSystem = (String)personne.get("Mot de passe");

                if(motDePasse.equals(motDePasseSystem)){
                    System.out.println("La connexion s'est fait avec succès!");
                    System.out.println("Vous ètes maintenant redirigé vers le menu du logiel en tant que locataire.\n");
                    System.out.print("Appuyez enter pour continuer.");
                    scanner.nextLine();
                    scanner.nextLine();
                    menuLocataire(nomDUtilisateur);
                    return;
                }
                System.out.println("ERREUR: Mot de passe invalide. Svp entrer un mot de passe valide.");
            }
            else{
                System.out.println("ERREUR: Nom d'utilisateur invalide. Svp entrer un nom d'utilisateur valide.");
            }
            System.out.println("--------------------");
        }
    }


    private static void sInscrireLocataire() throws ParseException{
        System.out.println("\n\n////// Inscription - Locataire //////");
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.print("Veuillez entrer votre nom d'utilisayeur: ");
            String nomDUtilisateur = scanner.next();
            System.out.print("Veuillez entrer votre mot de passe: ");
            String motDePasse = scanner.next();
            System.out.print("Veuillez entrer votre prenom: ");
            String prenom = scanner.next();
            System.out.print("Veuillez entrer votre nom: ");
            String nom = scanner.next();
            System.out.print("Veuillez entrer votre cote de crédit: ");
            String coteDeCredit = scanner.next();
            System.out.println("Ètes-vous satisfait avec ces valeurs entrées pour créer votre compte locataire? (y = oui, n = non)");
            String[] stringArray = {"y","n"};
            String reponse = takeValidAnswer(stringArray);
            if(reponse.equals("y")){
                Locataire.addLocataireToJson(nomDUtilisateur, motDePasse, prenom, nom, coteDeCredit);
                System.out.println("L'inscrption s'est fait avec succès!");
                System.out.println("Vous ètes maintenant redirigé vers la connexion au logiel en tant que locataire.\n");
                System.out.print("Appuyez enter pour continuer.");
                scanner.nextLine();
                scanner.nextLine();
                seConnecterLocataire();
                return;
            }else{System.out.println("--------------------");}

        }
    }
    

    private static void menuLocataire(String locataireName) throws ParseException{
        System.out.println("\n\n\n////////////////////////////////////////");
        System.out.println("/////////// Menu - Locataire ///////////");
        System.out.println("////////////////////////////////////////");
        while(true){
            System.out.println("Veuillez sélectionner la page ou l'action que vous voulez consulter ou effectuer.");
            System.out.println("- Rechercher unités = u");
            System.out.println("- Paiment = p");
            System.out.println("- Consulter bail = b");
            System.out.println("- Compte = c");
            System.out.println("- Retourner à la connexion = r");
            System.out.println("- Quitter le logiciel = q");

            String[] stringArray0 = {"u","p","b","c","r","q"};
            String reponse0 = takeValidAnswer(stringArray0);
            if(reponse0.equals("u")){
                rechercheUniteLocataire();
            }
            else if(reponse0.equals("p")){
                paimentLocataire();
            }
            else if(reponse0.equals("b")){
                bailLocataire();
            }
            else if(reponse0.equals("c")){
                compteLocataire(locataireName);
                return;
            }
            else if(reponse0.equals("r")){
                System.out.println("Ètes-vous sûr de vouloir quitter la session? (y = oui, n = non)");
                String[] stringArray1 = {"y","n"};
                String reponse1 = takeValidAnswer(stringArray1);
                if(reponse1.equals("y")){
                    seConnecterLocataire();
                    return;
                }
            }
            else{
                System.out.println("Ètes-vous sûr de vouloir quitter le logiciel? (y = oui, n = non)");
                String[] stringArray1 = {"y","n"};
                String reponse1 = takeValidAnswer(stringArray1);
                if(reponse1.equals("y")){
                    System.out.println("\nBonne fin de journée!");
                    System.out.println("\n\n------ Fermeture du logiciel ------");
                    System.exit(0);
                }
            }
            System.out.println();
        }
    }

    private static void rechercheUniteLocataire(){
        System.out.println("In unnite l");
    }


    private static void paimentLocataire(){
        System.out.println("In paiment l");
    }


    private static void compteLocataire(String nomLocataire) throws ParseException{
        System.out.println("\n\n////// Compte - Locataire //////");

        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("Veuillez sélectionner l'action que vous voulez effectuer.");
            System.out.println("- Changer de mot de passe = m");
            System.out.println("- Changer la cote de crédit = c");
            System.out.println("- Changer les types d'intérêt de location = i");
            System.out.println("- Retourner au menu principal = r");

            String[] stringArray0 = {"m","c","i","r"};
            String reponse0 = takeValidAnswer(stringArray0);
            
            if(reponse0.equals("m")){
                System.out.print("\nVeuillez entrer le nouveau mot de passe: ");
                String nouveauMDP = scanner.next();
                JsonManager.modifyArgumentOfList("JsonPersonne.json", "Nom d'utilisateur", nomLocataire, "Mot de passe", nouveauMDP);
                System.out.println();
            }
            else if (reponse0.equals("c")){
                System.out.print("\nVeuillez entrer la nouvlle cote de crédit: ");
                String nouvelleCote = scanner.next();
                JsonManager.modifyArgumentOfList("JsonLocataire.json", "Nom d'utilisateur", nomLocataire, "Cote de credit", nouvelleCote);
                System.out.println();
            }

            // Changer les intérêts 
            else if (reponse0.equals("i")){
                JSONObject object = JsonManager.getJsonObjectOfAList("JsonLocataire.json", "Nom d'utilisateur", nomLocataire);
                JSONObject interets = (JSONObject)object.get("Interet");
                System.out.print("\nVoici à quoi ressemble vos préférence pour le moment: ");
                System.out.println(interets);

                System.out.println("Voulez vous ajouter ou enlever un type d'intérêt? (a = ajouter, e = enlever)");
                String[] stringArray1 = {"a","e"};
                String reponse1 = takeValidAnswer(stringArray1);

                if(reponse1.equals("a")){
                    System.out.println("\nVoulez-vous ajouter le type commercial = c, résidentiel = r ou autre = a?");
                    String[] stringArray2 = {"c","r","a"};
                    String reponse2 = takeValidAnswer(stringArray2);

                    if(reponse2.equals("c")){interets.put("Commercial", true);}
                    else if (reponse2.equals("r")){interets.put("Residentiel", true);}
                    else{interets.put("Autre", true);}
                }
                else{
                    System.out.println("\nVoulez-vous enlever le type commercial = c, résidentiel = r ou autre = a?");
                    String[] stringArray2 = {"c","r","a"};
                    String reponse2 = takeValidAnswer(stringArray2);

                    if(reponse2.equals("c")){interets.put("Commercial", false);}
                    else if (reponse2.equals("r")){interets.put("Residentiel", false);}
                    else{interets.put("Autre", false);}
                }
                JsonManager.modifyJSONObjectOfAnObject("JsonLocataire.json", "Nom d'utilisateur", nomLocataire, "Interet", interets.toString());
            }
            else{
                menuLocataire(nomLocataire);
                System.out.println("--------------------");
                return;
            }
            System.out.println("\n--------------------\n");
        }
    }


    private static void bailLocataire(){
        System.out.println("In bail l");
    }



    ///////////////////////////////////////////
    /////// Fonctions pour proprietaire ///////
    ///////////////////////////////////////////

    private static void seConnecterProprietaire() throws ParseException{
        System.out.println("\n\n////// Connexion - Propriétaire //////");
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.print("Veuillez entrer votre nom d'utilisayeur: ");
            String nomDUtilisateur = scanner.next();
            System.out.print("Veuillez entrer votre mot de passe: ");
            String motDePasse = scanner.next();

            if(Proprietaire.getProprietaire(nomDUtilisateur) != null){
                JSONObject personne = Personne.getPersonne(nomDUtilisateur);
                String motDePasseSystem = (String)personne.get("Mot de passe");

                if(motDePasse.equals(motDePasseSystem)){
                    System.out.println("La connexion s'est fait avec succès!");
                    System.out.println("Vous ètes maintenant redirigé vers le menu du logiel en tant que prpopriétaite.\n");
                    System.out.print("Appuyez enter pour continuer.");
                    scanner.nextLine();
                    scanner.nextLine();
                    menuProprietaire(nomDUtilisateur);
                    return;
                }
                System.out.println("ERREUR: Mot de passe invalide. Svp entrer un mot de passe valide.");
            }
            else{
                System.out.println("ERREUR: Nom d'utilisateur invalide. Svp entrer un nom d'utilisateur valide.");
            }
            System.out.println("--------------------");
        }
    }


    private static void sInscrireProprietaire() throws ParseException{
        System.out.println("\n\n////// Inscription - Propriétaire //////");
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.print("Veuillez entrer votre nom d'utilisayeur: ");
            String nomDUtilisateur = scanner.next();
            System.out.print("Veuillez entrer votre mot de passe: ");
            String motDePasse = scanner.next();
            System.out.print("Veuillez entrer votre prenom: ");
            String prenom = scanner.next();
            System.out.print("Veuillez entrer votre nom: ");
            String nom = scanner.next();
            System.out.println("Ètes-vous satisfait avec ces valeurs entrées pour créer votre compte propriétaire? (y = oui, n = non)");
            String[] stringArray = {"y","n"};
            String reponse = takeValidAnswer(stringArray);
            if(reponse.equals("y")){
                Proprietaire.addProprietaireToJson(nomDUtilisateur, motDePasse, prenom, nom);
                System.out.println("L'inscrption s'est fait avec succès!");
                System.out.println("Vous ètes maintenant redirigé vers la connexion au logiel en tant que prpopriétaite.\n");
                System.out.print("Appuyez enter pour continuer.");
                scanner.nextLine();
                scanner.nextLine();
                seConnecterProprietaire();
                return;
            }else{System.out.println("--------------------");}

        }
    }


    private static void menuProprietaire(String nomProprietaire) throws ParseException{
        System.out.println("\n\n\n/////////////////////////////////////////");
        System.out.println("////////// Menu - Propriétaire //////////");
        System.out.println("/////////////////////////////////////////");
        while(true){
            System.out.println("Veuillez sélectionner la page ou l'action que vous voulez consulter ou effectuer.");
            System.out.println("- Paiments = p");
            System.out.println("- Unités et baux = u");
            System.out.println("- Compte = c");
            System.out.println("- Retourner à la connexion = r");
            System.out.println("- Quitter le logiciel = q");

            String[] stringArray0 = {"p","u","c","r","q"};
            String reponse0 = takeValidAnswer(stringArray0);
            if(reponse0.equals("p")){
                paimentProprietaire();
            }
            else if(reponse0.equals("u")){
                uniteProprietaire(nomProprietaire);
                return;
            }
            else if(reponse0.equals("c")){
                compteProprietaire(nomProprietaire);
                return;
            }
            else if(reponse0.equals("r")){
                System.out.println("Ètes-vous sûr de vouloir quitter la session? (y = oui, n = non)");
                String[] stringArray1 = {"y","n"};
                String reponse1 = takeValidAnswer(stringArray1);
                if(reponse1.equals("y")){
                    seConnecterProprietaire();
                    return;
                }
            }
            else{
                System.out.println("Ètes-vous sûr de vouloir quitter le logiciel? (y = oui, n = non)");
                String[] stringArray1 = {"y","n"};
                String reponse1 = takeValidAnswer(stringArray1);
                if(reponse1.equals("y")){
                    System.out.println("\nBonne fin de journée!");
                    System.out.println("\n\n------ Fermeture du logiciel ------");
                    System.exit(0);
                }
            }
            System.out.println();
        }
    }


    private static void compteProprietaire(String nomProprietaire) throws ParseException{
        System.out.println("\n\n////// Compte - Propriétaire //////");

        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("Veuillez sélectionner l'action que vous voulez effectuer.");
            System.out.println("- Changer de mot de passe = m");
            System.out.println("- Retourner au menu principal = r");

            String[] stringArray0 = {"m","r"};
            String reponse0 = takeValidAnswer(stringArray0);
            
            if(reponse0.equals("m")){
                System.out.print("\nVeuillez entrer le nouveau mot de passe: ");
                String nouveauMDP = scanner.next();
                JsonManager.modifyArgumentOfList("JsonPersonne.json", "Nom d'utilisateur", nomProprietaire, "Mot de passe", nouveauMDP);
                System.out.println();
            }
            
            else{
                menuLocataire(nomProprietaire);
                System.out.println("--------------------");
                return;
            }
            System.out.println("\n--------------------\n");
        }
        
    }


    private static void paimentProprietaire(){
        System.out.println("In paiment p");
    }


    private static void uniteProprietaire(String nomProprietaire) throws ParseException{
        System.out.println("\n\n////// Unités et baux - Propriétaire //////");

        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("Veuillez sélectionner l'action que vous voulez effectuer.");
            System.out.println("- Créer une nouvelle unité = u");
            System.out.println("- Modifier une unité existante = m");
            System.out.println("- Consulter les unités bientôt vacantes = v");
            System.out.println("- Créer une proposition de bail - b");
            System.out.println("- Modifier une proposition de bail - p");
            System.out.println("- Consulter les renouvellement de baux = c");
            System.out.println("- Retourner au menu principal = r");

            String[] stringArray0 = {"u","m","v","b","p","c","r"};
            String reponse0 = takeValidAnswer(stringArray0);
            
            if(reponse0.equals("u")){
                while(true){
                    System.out.println("\nVeuillez entrer toutes les nouvelles informations en lien avec la nouvelle unité.");
                    System.out.print("Entrez le type de l'unité (l = logement, s = surface ouverte commerciale, m = magasin)");
                    String[] stringArray1 = {"l","s","m"};
                    String reponse1 = takeValidAnswer(stringArray1);
                    String type;
                    if(reponse1.equals("l")){type = "Logement";}
                    else if(reponse1.equals("s")){type = "Surface ouverte commerciale";}
                    else{type = "Magasin";}
                    System.out.print("Entrez l'adresse de l'unité: ");
                    String adresse = scanner.next();
                    System.out.print("Entrez la ville de l'unité: ");
                    String ville = scanner.next();
                    System.out.println("Entrez l'aire de l'unité (en m2):");
                    long aire = takePositiveInteger();
                    long nbChambre;
                    if(type.equals("Logement")){
                        System.out.println("Entrez le nombre de chambre(s):");
                        nbChambre = takePositiveInteger();
                    }
                    else{nbChambre = -1;}
                    System.out.println("Entrez le nombre de salle(s) de bains(s):");
                    long nbSalleDeBain= takePositiveInteger();
                    System.out.print("Entrez la date de construction de l'unité: ");
                    String date = scanner.next();
                    System.out.println("Entrez la condition de l'unité (l = louable, r = pas louable pour réparation)");
                    String[] stringArray2 = {"l","r"};
                    String reponse2 = takeValidAnswer(stringArray2);
                    String condition;
                    if(reponse2.equals("l")){condition = "Louable";}
                    else{condition="Pas louable pour reparation";}
                    System.out.println("Entrez l'état de l'unité (l = loué, i = libre, r = réservé)");
                    String[] stringArray3 = {"l","i","r"};
                    String reponse3 = takeValidAnswer(stringArray3);
                    String etat;
                    if(reponse3.equals("l")){etat = "Loue";}
                    else if (reponse3.equals("i")){etat = "Libre";}
                    else {etat = "Reserve";}

                    System.out.println("\nÈtes-vous satisfait avec ces valeurs entrées pour créer cette nouvelle unité? (y = oui, n = non)");
                    String[] stringArray = {"y","n"};
                    String reponse = takeValidAnswer(stringArray);
                    if(reponse.equals("y")){
                        Unite.addUniteToJson(type, adresse, ville, aire, nomProprietaire, nbChambre, nbSalleDeBain, date, condition, etat);
                        System.out.println("L'ajout de l'unnité s'est fait avec succès!");
                        break;
                    }else{System.out.println("--------------------");}
                }
                
            }
            else if(reponse0.equals("m")){

            }
            else if(reponse0.equals("v")){

            }
            else if(reponse0.equals("b")){

            }
            else if(reponse0.equals("p")){

            }
            else if(reponse0.equals("c")){

            }
            else{
                menuLocataire(nomProprietaire);
                System.out.println("--------------------");
                return;
            }
            System.out.println("\n--------------------\n");
        }
    }
}