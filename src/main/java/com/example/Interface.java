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

    public static void main( String[] args ) {
        startSystem();
    }


    private static void startSystem(){
        System.out.println();
        System.out.println("//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
        System.out.println("////  0      000   0000  000  00000 00000  000  0   0     00000     0   0 00000 0   0 0   0 00000 00000  000  00  ////");
        System.out.println("////  0     0   0 0     0   0   0     0   0   0 00  0     0         00 00   0   00  0 0   0   0   0     0     00  ////");
        System.out.println("////  0     0   0 0     00000   0     0   0   0 0 0 0     00000     0 0 0   0   0 0 0 0   0   0   00000  00   00  ////");
        System.out.println("////  0     0   0 0     0   0   0     0   0   0 0  00         0     0   0   0   0  00 0   0   0   0        0      ////");
        System.out.println("////  00000  000   0000 0   0   0   00000  000  0   0     0000      0   0 00000 0   0  000    0   00000 000   00  ////");
        System.out.println("//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////\n\n\n");
        
        System.out.println("Bivenu sur le logiciel de location de propriété \"Location 5 minutes!\"\n\n");
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


    ////////////////////////////////////////
    /////// Fonctions pour locataire ///////
    ////////////////////////////////////////

    private static void seConnecterLocataire(){
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
                    menuLocataire();
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


    private static void sInscrireLocataire(){
        System.out.println("\n\n////// Inscription - Locataire //////");
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.print("Veuillex entrer votre nom d'utilisayeur: ");
            String nomDUtilisateur = scanner.next();
            System.out.print("Veuillex entrer votre mot de passe: ");
            String motDePasse = scanner.next();
            System.out.print("Veuillex entrer votre prenom: ");
            String prenom = scanner.next();
            System.out.print("Veuillex entrer votre nom: ");
            String nom = scanner.next();
            System.out.print("Veuillex entrer votre cote de crédit: ");
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
    

    private static void menuLocataire(){
        System.out.println("In menu l");
    }



    ///////////////////////////////////////////
    /////// Fonctions pour proprietaire ///////
    ///////////////////////////////////////////

    private static void seConnecterProprietaire(){
        System.out.println("\n\n////// Connexion - Propriétaire //////");
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.print("Veuillex entrer votre nom d'utilisayeur: ");
            String nomDUtilisateur = scanner.next();
            System.out.print("Veuillex entrer votre mot de passe: ");
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
                    menuProprietaire();
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


    private static void sInscrireProprietaire(){
        System.out.println("\n\n////// Inscription - Propriétaire //////");
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.print("Veuillex entrer votre nom d'utilisayeur: ");
            String nomDUtilisateur = scanner.next();
            System.out.print("Veuillex entrer votre mot de passe: ");
            String motDePasse = scanner.next();
            System.out.print("Veuillex entrer votre prenom: ");
            String prenom = scanner.next();
            System.out.print("Veuillex entrer votre nom: ");
            String nom = scanner.next();
            System.out.println("Ètes-vous satisfait avec ces valeurs entrées pour créer votre compte locataire? (y = oui, n = non)");
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

    private static void menuProprietaire(){
        System.out.println("In menu p");
    }

}