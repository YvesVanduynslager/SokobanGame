package gui;

/* IMPORTS */
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
    /* DECLARATIES CONSTANTEN */
    private final DomeinController controller;
    /* DECLARATIES VARIABELEN */
    private boolean geldig = false;

    public Registreer(DomeinController controller)
    {
        this.controller = controller;
    }

    /**
     * Tekent de registratie-ui en vraagt gegevens van gebruiker om deze te
     * registreren in het systeem.
     */
    public void startRegistreerUI()
    {
        Scanner scanner = new Scanner(System.in);
        String gebruikersnaam, wachtwoord, naam, voornaam;

        System.out.println();
        System.out.printf("%s%n%s%n%s%n", localeString("registreer.border"), localeString("registreer.main"), localeString("registreer.border"));
        do
        {
            System.out.print(localeString("registreer.naam"));
            naam = scanner.next();
            if (naam.equals(localeString("terug")))
            {
                return; /* toonHoofdmenu() in ConsoleApplicatie opnieuw uitvoeren.
                 dus kortweg: opnieuw tonen van hoofdmenu */

            }
            System.out.print(localeString("registreer.voornaam"));
            voornaam = scanner.next();
            if (voornaam.equals(localeString("terug")))
            {
                return;

            }
            System.out.print(localeString("registreer.gebruikersnaam"));
            gebruikersnaam = scanner.next();
            if (gebruikersnaam.equals(localeString("terug")))
            {
                return;

            }
            System.out.print(localeString("registreer.wachtwoord"));
            wachtwoord = scanner.next();
            if (wachtwoord.equals(localeString("terug")))
            {
                return;
            }

            try
            {
                controller.registreer(gebruikersnaam, wachtwoord, voornaam, voornaam);
                geldig = true;
            }
            catch (GebruikerBestaatException gbe)
            {
                System.err.println(gbe + localeString("registreer.gebruikerbestaat"));
                geldig = false;
            }
            catch (IllegalArgumentException iae)
            {
                System.err.println(iae + localeString("registreer.ongeldig"));
                geldig = false;
            }
        }
        while (!geldig);

        String[] spelerGegevens = controller.geefSpeler();

        System.out.println();
        System.out.println(localeString("registreer.succes"));
        System.out.printf("%s%14s%n", localeString("output.gebruikersnaam"), localeString("output.rechten")); //wachtwoord wordt hier niet meer weergegeven.

        for (String spelerGegeven : spelerGegevens)
        {
            System.out.printf("%14s", spelerGegeven);
        }
        System.out.println();
    }

    /**
     * Geeft een boolean terug om later te controleren of het registreren een
     * succes is of niet.
     *
     * @return True voor registreren succes, false voor registreren geen succes.
     */
    public boolean isSucces()
    {
        return geldig;
    }
    
    private String localeString(String key)
    {
        return controller.getString(key);
    }
}
