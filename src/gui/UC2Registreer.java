/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import java.util.Scanner;
import persistentie.SpelerMapper;
/**
 *
 * @author Jeroen
 */
public class UC2Registreer {
    
public void registreer() {
Scanner scanner = new Scanner(System.in);
DomeinController controller = new DomeinController();

String[] spelerGegevens;
boolean isGeldigWachtwoord=false;
boolean isGeldigeGebruikersnaam=false;
String gebruikersnaam="", wachtwoord="", naam="", voornaam="";
int counter=0;// inlezen naam, voornaam, gebruikersnaam, wachtwoord 
while (isGeldigWachtwoord==false || isGeldigeGebruikersnaam==false) {
    if(++counter>1)
    {
    System.out.println("Ongeldige gebruikersnaam en/of wachtwoord, gelieve opnieuw te proberen");
    }
    System.out.printf("%n%s","Geef naam ('stop' om te stoppen): ");
    naam = scanner.next();
    if (naam.equals("stop"))
    {
        System.exit(0);
    }
    System.out.print("Geef voornaam ('stop' om te stoppen): ");
    voornaam = scanner.next();
    if (voornaam.equals("stop"))
    {
        System.exit(0);
    }
    System.out.print("Geef gebruikersnaam ('stop' om te stoppen; min. 8 karakters lang; moet uniek zijn): ");
    gebruikersnaam = scanner.next();
    if (gebruikersnaam.equals("stop"))
    {
        System.exit(0);
    }
    System.out.print("Geef wachtwoord ('stop' om te stoppen; min. 8 karakters lang, moet zowel een kleine letter als een hoofdletter en een cijfer bevatten): ");
    wachtwoord = scanner.next();
    if (wachtwoord.equals("stop"))
    {
        System.exit(0);
    }
 // valideren ingevulde gegevens
    isGeldigWachtwoord = validerenWachtwoord(wachtwoord);
    isGeldigeGebruikersnaam = validerenGebruikersnaam(gebruikersnaam);
  
}  
controller.registreer(gebruikersnaam, wachtwoord, voornaam, naam);

}
// gebaseerd op: http://www.coderanch.com/t/583177/java/java/validate-string-characters-letter-number 
 public boolean validerenWachtwoord (String password) {
        int numOfUpperLetters = 0; // initialiseren aantal lowerCase letters
        int numOfLowerLetters = 0; // initialiseren aantal upperCase letters
        int numOfDigits = 0; // initialiseren aantal cijfers
        boolean geldigWachtwoord=false; // initialiseren geldigheid wachtwoord
        
        byte[] bytes = password.getBytes();
        for (byte tempByte : bytes) {
            char tempChar = (char) tempByte;
            if (Character.isDigit(tempChar)) {
                numOfDigits++;
            }
 
            if (Character.isUpperCase(tempChar)) {
                numOfUpperLetters++;
            }
 
            if (Character.isLowerCase(tempChar)) {
                numOfLowerLetters++;
            }
        }
    // indien aan alles werd voldaan: resultaat >= 1; anders 0
    if (numOfDigits*numOfUpperLetters*numOfLowerLetters*(password.length()/8)>=1)
    geldigWachtwoord=true;
    return geldigWachtwoord;
 }
public boolean validerenGebruikersnaam (String gebruikersnaam){
SpelerMapper spelerMapper = new SpelerMapper();
boolean geldigeGebruikersnaam=false;
boolean isReedsBestaande=spelerMapper.bestaatGebruikerAl(gebruikersnaam);
if (isReedsBestaande==false && gebruikersnaam.length()>=8)
geldigeGebruikersnaam=true;
return geldigeGebruikersnaam;
}

}
