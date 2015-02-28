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
                return;

            }
            System.out.print("Geef gebruikersnaam (minimum 8 karakters lang; moet uniek zijn) | (\"terug\" om terug te gaan naar het hoofdmenu): ");
            gebruikersnaam = scanner.next();
            if (gebruikersnaam.equals("terug"))
            {
                return;

            }
            System.out.print("Geef wachtwoord (minimum 8 karakters lang, moet zowel een kleine letter als een hoofdletter en een cijfer bevatten)"
                    + " | (\"terug\" om terug te gaan naar het hoofdmenu): ");
            wachtwoord = scanner.next();
            if (wachtwoord.equals("terug"))
            {
                return;
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
        }
        while (!geldig);

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
}
