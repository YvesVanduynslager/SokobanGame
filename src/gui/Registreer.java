package gui;

import domein.DomeinController;
import exceptions.GebruikerBestaatException;
import java.util.Scanner;

/**
 * USE CASE 2 REGISTREER. Deze klasse staat in voor het tekenen en beheer van de
 * ui voor UC2 Registreer.
 *
 * @author Yves
 */
public class Registreer
{
    private final DomeinController controller;
    private boolean geldig = false;

    public Registreer(DomeinController controller)
    {
        this.controller = controller;
    }

    /**
     * Tekent ui en vraagt gegevens van gebruiker om deze te registreren in het
     * systeem.
     */
    public void startRegistreerUI()
    {
        Scanner scanner = new Scanner(System.in);

//        boolean isGeldigWachtwoord;// = false;
//        boolean isGeldigeGebruikersnaam;// = false;
        String gebruikersnaam, wachtwoord, naam, voornaam;

        System.out.println();
        System.out.printf("%s%n%s%n%s%n", " -------------", "| REGISTREREN |", " -------------");
        do
        {
            System.out.print("Geef naam | (\"terug\" om terug te gaan naar het hoofdmenu): ");
            naam = scanner.next();
            if (naam.equals("terug"))
            {
                return; /* toonHoofdmenu() in ConsoleApplicatie opnieuw uitvoeren.
                 dus kortweg: opnieuw tonen van hoofdmenu */

            }
            System.out.print("Geef voornaam | (\"terug\" om terug te gaan naar het hoofdmenu): ");
            voornaam = scanner.next();
            if (voornaam.equals("terug"))
            {
                return; /* toonHoofdmenu() in ConsoleApplicatie opnieuw uitvoeren.
                 dus kortweg: opnieuw tonen van hoofdmenu */

            }
            System.out.print("Geef gebruikersnaam (minimum 8 karakters lang; moet uniek zijn) | (\"terug\" om terug te gaan naar het hoofdmenu): ");
            gebruikersnaam = scanner.next();
            if (gebruikersnaam.equals("terug"))
            {
                return; /* toonHoofdmenu() in ConsoleApplicatie opnieuw uitvoeren.
                 dus kortweg: opnieuw tonen van hoofdmenu */

            }
            System.out.print("Geef wachtwoord (minimum 8 karakters lang, moet zowel een kleine letter als een hoofdletter en een cijfer bevatten)"
                    + " | (\"terug\" om terug te gaan naar het hoofdmenu): ");
            wachtwoord = scanner.next();
            if (wachtwoord.equals("terug"))
            {
                return; /* toonHoofdmenu() in ConsoleApplicatie opnieuw uitvoeren.
                 dus kortweg: opnieuw tonen van hoofdmenu */
            }

            try
            {
                controller.registreer(gebruikersnaam, wachtwoord, voornaam, voornaam);
                geldig = true;
            }
            catch (IllegalArgumentException iae)
            {
                System.err.println("Ongeldige gebruikersnaam of wachtwoord ingegeven!");
                geldig = false;
            }
            catch (GebruikerBestaatException gbe)
            {
                System.err.println("Gebruiker bestaat al in het systeem!");
                geldig = false;
            }
//            catch(Exception e)
//            {
//                System.err.println(e);
//                geldig = false;
//            }
            // valideren ingevulde gegevens
//            isGeldigWachtwoord = validerenWachtwoord(wachtwoord);
//            isGeldigeGebruikersnaam = validerenGebruikersnaam(gebruikersnaam);

//            if (!isGeldigWachtwoord || !isGeldigeGebruikersnaam)
//            {
//                System.out.println("Ongeldige gebruikersnaam en/of wachtwoord, gelieve opnieuw te proberen");
//            }
        }
        while (!geldig /*!isGeldigWachtwoord || !isGeldigeGebruikersnaam*/);

        //controller.registreer(gebruikersnaam, wachtwoord, voornaam, naam);
        String[] spelerGegevens = controller.geefSpeler();
        System.out.println();
        System.out.println("Succesvol geregistreerd en aangemeld met volgende gegevens: ");
        System.out.printf("%s%14s%n", "Gebruikersnaam", "Adminrechten"); //wachtwoord wordt hier niet meer weergegeven.
        for (String spelerGegeven : spelerGegevens)
        {
            System.out.printf("%14s", spelerGegeven);
        }
        System.out.println();
    }
    
    public boolean isSucces()
    {
        return geldig;
    }
// gebaseerd op: http://www.coderanch.com/t/583177/java/java/validate-string-characters-letter-number 

//    public boolean validerenWachtwoord(String password)
//    {
//        int numOfUpperLetters = 0; // initialiseren aantal lowerCase letters
//        int numOfLowerLetters = 0; // initialiseren aantal upperCase letters
//        int numOfDigits = 0; // initialiseren aantal cijfers
//        //boolean geldigWachtwoord = false; // initialiseren geldigheid wachtwoord
//
//        byte[] bytes = password.getBytes();
//        for (byte tempByte : bytes)
//        {
//            char tempChar = (char) tempByte;
//            if (Character.isDigit(tempChar))
//            {
//                numOfDigits++;
//            }
//
//            if (Character.isUpperCase(tempChar))
//            {
//                numOfUpperLetters++;
//            }
//
//            if (Character.isLowerCase(tempChar))
//            {
//                numOfLowerLetters++;
//            }
//        }
//        // indien aan alles werd voldaan: resultaat >= 1; anders 0
//        if (numOfDigits * numOfUpperLetters * numOfLowerLetters * (password.length() / 8) >= 1)
//        {
//            return true;
//        }
//        else
//        {
//            return false;
//        }
//    }
//    public boolean validerenGebruikersnaam(String gebruikersnaam)
//    {
//        DomeinController c = new DomeinController();
//        boolean isReedsBestaande = c.bestaatSpeler(gebruikersnaam);
//        
//        if (isReedsBestaande == false && gebruikersnaam.length() >= 8)
//        {
//            return true;
//        }
//        else
//        {
//            return false;
//        }
//    }
}
