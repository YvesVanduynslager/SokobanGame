package gui;
//USE CASE 2

import domein.DomeinController;
import exceptions.GebruikerBestaatException;
import java.util.Scanner;

/**
 *
 * @author Jeroen
 */
public class Registreer
{
    private final DomeinController controller;
    public Registreer(DomeinController controller)
    {
        this.controller = controller;
    }

    public void registreer()
    {
        Scanner scanner = new Scanner(System.in);

        boolean isGeldigWachtwoord;// = false;
        boolean isGeldigeGebruikersnaam;// = false;
        String gebruikersnaam = "", wachtwoord = "", naam = "", voornaam = "";
        boolean geldig = false;

        System.out.println();
        System.out.printf("%s%n%s%n%s%n", " -------------", "| REGISTREREN |", " -------------");
        do
        {
            System.out.print("Geef naam | (\"terug\" om terug te gaan naar het hoofdmenu): ");
            naam = scanner.next();
            if (naam.equals("terug"))
            {
                return; /*keert terug naar de methode die de call heeft gepleegd (hier dus ConsoleApplicatie.startUI())
                dus kortweg: opnieuw tonen van hoofdmenu */
            }
            System.out.print("Geef voornaam | (\"terug\" om terug te gaan naar het hoofdmenu): ");
            voornaam = scanner.next();
            if (voornaam.equals("terug"))
            {
                return; /*keert terug naar de methode die de call heeft gepleegd (hier dus ConsoleApplicatie.startUI())
                dus kortweg: opnieuw tonen van hoofdmenu */
            }
            System.out.print("Geef gebruikersnaam (minimum 8 karakters lang; moet uniek zijn) | (\"terug\" om terug te gaan naar het hoofdmenu): ");
            gebruikersnaam = scanner.next();
            if (gebruikersnaam.equals("terug"))
            {
                return; /*keert terug naar de methode die de call heeft gepleegd (hier dus ConsoleApplicatie.startUI())
                dus kortweg: opnieuw tonen van hoofdmenu */
            }
            System.out.print("Geef wachtwoord (minimum 8 karakters lang, moet zowel een kleine letter als een hoofdletter en een cijfer bevatten)"
                    + " | (\"terug\" om terug te gaan naar het hoofdmenu): ");
            wachtwoord = scanner.next();
            if (wachtwoord.equals("terug"))
            {
                return; /*keert terug naar de methode die de call heeft gepleegd (hier dus ConsoleApplicatie.startUI())
                dus kortweg: opnieuw tonen van hoofdmenu */
            }

            try
            {
                controller.registreer(gebruikersnaam, wachtwoord, voornaam, voornaam);
                geldig = true;
            }
            catch(GebruikerBestaatException gbe)
            {
                System.err.println(gbe);
                //geldig = false;
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
