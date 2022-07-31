package com.example;

import java.time.LocalDateTime;

import java.util.Calendar;

import org.json.simple.JSONObject;

public class TimeManager {

    public static long calulateTimeIntervalInSeconds(String periode, long nbPeriode){
        long timeInterval;
        if(periode.equals("o")){
            timeInterval = nbPeriode*2592000;
        }
        else if(periode.equals("j")){
            timeInterval = nbPeriode*86400;
        }
        else if(periode.equals("h")){
            timeInterval = nbPeriode*3600;
        }
        else if(periode.equals("m")){
            timeInterval = nbPeriode*60;
        }
        else{
            timeInterval = nbPeriode;
        }
        return timeInterval;
    }

    public static JSONObject addTimeIntervalToJDate(JSONObject jDate, long interval){
        JSONObject newJDate = jDate;
        int anne = (int)jDate.get("Annee");
        int mois = (int)jDate.get("Mois");
        int jour = (int)jDate.get("Jour");
        int heure = (int)jDate.get("Heure");
        int minute = (int)jDate.get("Minute");
        int seconde = (int)jDate.get("Seconde");
        Calendar cal = Calendar.getInstance();
        cal.set(anne,mois,jour,heure,minute,seconde);
        cal.add(cal.SECOND,(int)interval);
        jDate.put("Annee",(long)cal.YEAR);
        jDate.put("Mois",(long)cal.MONTH);
        jDate.put("Jour",(long)cal.DAY_OF_MONTH);
        jDate.put("Heure",(long)cal.HOUR);
        jDate.put("Minute",(long)cal.MINUTE);
        jDate.put("Seconde",(long)cal.SECOND);
        return jDate;
    }

    //Générise l'entrée de date pour renvoyer un objet de type JSONObject
    public static JSONObject takeValidJObjectDate(){
        long seconde, minute, heure, jour, mois, annee;
        Boolean isToday;
        LocalDateTime date = LocalDateTime.now();
        
        while(true){
            System.out.println("Entrez l'année:");
            annee = Interface.takePositiveInteger();
            if(!(annee>=date.getYear())){
                if(annee == date.getYear()){isToday = true;}
                else{isToday = false;}
                break;
            }
            System.out.println("ERREUR: Entrez une reponse valide");
        }

        while(true){
            System.out.println("Entrez le mois:");
            mois = Interface.takePositiveInteger();
            if(!(mois<=12)){
                if(isToday){
                    if(mois >= date.getMonthValue()){
                        if(!(mois == date.getHour())){
                            isToday = false;
                        }
                        break;
                    }
                }else{break;}
            }
            System.out.println("ERREUR: Entrez une reponse valide");
        }

        while(true){
            System.out.println("Entrez le jour:");
            jour = Interface.takePositiveInteger();
            if((mois == 1 || mois == 3 || mois == 5 || mois == 7 || mois == 8 || mois == 10 || mois == 12)&&jour<=31){
                if(isToday){
                    if(jour >= date.getMonthValue()){
                        if(!(jour == date.getHour())){
                            isToday = false;
                        }
                        break;
                    }
                }else{break;}
            }
            else if((mois == 4 || mois == 6 || mois == 9 || mois == 11)&&jour<=30){
                if(isToday){
                    if(jour >= date.getMonthValue()){
                        if(!(jour == date.getHour())){
                            isToday = false;
                        }
                        break;
                    }
                }else{break;}
            }
            else if(jour <= 27){
                if(isToday){
                    if(jour >= date.getMonthValue()){
                        if(!(jour == date.getHour())){
                            isToday = false;
                        }
                        break;
                    }
                }else{break;}
            }
            System.out.println("ERREUR: Entrez une reponse valide");
        }

        while(true){
            System.out.println("Entrez l'heure:");
            heure = Interface.takePositiveInteger();
            if(!(heure<24)){
                if(isToday){
                    if(heure >= date.getHour()){
                        if(!(heure == date.getHour())){
                            isToday = false;
                        }
                        break;
                    }
                }else{break;}
            }
            System.out.println("ERREUR: Entrez une reponse valide");
        }

        while(true){
            System.out.println("Entrez la minute:");
            minute = Interface.takePositiveInteger();
            if(!(minute<60)){
                if(isToday){
                    if(heure >= date.getMinute()){
                        if(!(heure == date.getMinute())){
                            isToday = false;
                        }
                        break;
                    }
                }else{break;}
            }
            System.out.println("ERREUR: Entrez une reponse valide");
        }

        while(true){
            System.out.println("Entrez la seconde:");
            seconde = Interface.takePositiveInteger();
            if(!(seconde<60)){
                if(isToday){
                    if(seconde >= date.getSecond()+1){
                        if(!(seconde == date.getSecond())){
                            isToday = false;
                        }
                        break;
                    }
                }else{break;}
            }
            System.out.println("ERREUR: Entrez une reponse valide");
        }
        JSONObject jDate = new JSONObject();
        jDate.put("Annee", annee);
        jDate.put("Mois", mois);
        jDate.put("Jour", jour);
        jDate.put("Heure", heure);
        jDate.put("Minute", minute);
        jDate.put("Seconde", seconde);
        return jDate;
    }
}
