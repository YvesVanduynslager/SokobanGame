package gui;

import domein.DomeinController;
import exceptions.GebruikerBestaatException;
import java.util.ResourceBundle;
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
    private ResourceBundle resource;
    private boolean geldig = false;

    public Registreer(DomeinController controller)
    {
        this.controller = controller;
        this.resource = this.controller.getResourseBundle();
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
        System.out.printf("%s%n%s%n%s%n", resource.getString("registreer.border"), resource.getString("registreer.main"), " -------------");
        do
        {
            System.out.print(resource.getString("registreer.naam"));
            naam = scanner.next();
            if (naam.equals(resource.getString("terug")))
            {
                return; /* toonHoofdmenu() in ConsoleApplicatie opnieuw uitvoeren.
                 dus kortweg: opnieuw tonen van hoofdmenu */

            }
            System.out.print(resource.getString("registreer.voornaam"));
            voornaam = scanner.next();
            if (voornaam.equals(resource.getString("terug")))
            {
                return;

            }
            System.out.print(resource.getString("registreer.gebruikersnaam"));
            gebruikersnaam = scanner.next();
            if (gebruikersnaam.equals(resource.getString("terug")))
            {
                return;

            }
            System.out.print(resource.getString("registreer.wachtwoord"));
            wachtwoord = scanner.next();
            if (wachtwoord.equals(resource.getString("terug")))
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
                System.err.println(resource.getString("registreer.ongeldig"));
                geldig = false;
            }
            catch (GebruikerBestaatException gbe)
            {
                System.err.println(resource.getString("registreer.gebruikerbestaat"));
                geldig = false;
            }
        }
        while (!geldig);

        String[] spelerGegevens = controller.geefSpeler();
        
        System.out.println();
        System.out.println(resource.getString("registreer.succes"));
        System.out.printf("%s%14s%n", resource.getString("output.gebruikersnaam"), resource.getString("output.rechten")); //wachtwoord wordt hier niet meer weergegeven.
        
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
