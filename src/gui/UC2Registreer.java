/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import java.util.Scanner;

/**
 *
 * @author Jeroen
 */
public class UC2Registreer {
    
public void registreer() {
Scanner scanner = new Scanner(System.in);
String[] spelerGegevens;
boolean isGeldigWachtwoord=false;
boolean isGeldigeGebruikersnaam=false;
// inlezen naam, voornaam, gebruikersnaam, wachtwoord 
while (isGeldigWachtwoord==false) {
    System.out.print("Geef naam ('stop' om te stoppen): ");
    String naam = scanner.next();
    if (naam.equals("stop"))
    {
        System.exit(0);
    }
    System.out.print("Geef voornaam ('stop' om te stoppen): ");
    String voornaam = scanner.next();
    if (voornaam.equals("stop"))
    {
        System.exit(0);
    }
    System.out.print("Geef gebruikersnaam ('stop' om te stoppen): ");
    String gebruikersnaam = scanner.next();
    if (gebruikersnaam.equals("stop"))
    {
        System.exit(0);
    }
    System.out.print("Geef wachtwoord ('stop' om te stoppen): ");
    String wachtwoord = scanner.next();
    if (wachtwoord.equals("stop"))
    {
        System.exit(0);
    }
isGeldigWachtwoord = geldigWachtwoord(wachtwoord);
}   // valideren ingevulde gegevens

}
// gebaseerd op: http://www.coderanch.com/t/583177/java/java/validate-string-characters-letter-number 
 public boolean geldigWachtwoord (String password) {
        int numOfUpperLetters = 0;
        int numOfLowerLetters = 0;
        int numOfDigits = 0;
        boolean geldigWachtwoord=true;
        
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
    if (numOfDigits*numOfUpperLetters*numOfLowerLetters*(password.length()/8)>=1){
    geldigWachtwoord=true;
    }
    return geldigWachtwoord;
 }
public boolean geldigeGebruikersnaam (String gebruikersnaam){
boolean geldigeGebruikersnaam=false;
boolean isReedsBestaande=true;    

if (isReedsBestaande==false && gebruikersnaam.length()>=8)
geldigeGebruikersnaam=true;
return geldigeGebruikersnaam;
}

}
