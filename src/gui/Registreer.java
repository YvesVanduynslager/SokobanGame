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
        System.out.printf("%s%n%s%n%s%n", controller.getString("registreer.border"), controller.getString("registreer.main"), controller.getString("registreer.border"));
        do
        {
            System.out.print(controller.getString("registreer.naam"));
            naam = scanner.next();
            if (naam.equals(controller.getString("terug")))
            {
                return; /* toonHoofdmenu() in ConsoleApplicatie opnieuw uitvoeren.
                 dus kortweg: opnieuw tonen van hoofdmenu */

            }
            System.out.print(controller.getString("registreer.voornaam"));
            voornaam = scanner.next();
            if (voornaam.equals(controller.getString("terug")))
            {
                return;

            }
            System.out.print(controller.getString("registreer.gebruikersnaam"));
            gebruikersnaam = scanner.next();
            if (gebruikersnaam.equals(controller.getString("terug")))
            {
                return;

            }
            System.out.print(controller.getString("registreer.wachtwoord"));
            wachtwoord = scanner.next();
            if (wachtwoord.equals(controller.getString("terug")))
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
                System.err.println(gbe + controller.getString("registreer.gebruikerbestaat"));
                geldig = false;
            }
            catch (IllegalArgumentException iae)
            {
                System.err.println(iae + controller.getString("registreer.ongeldig"));
                geldig = false;
            }
        }
        while (!geldig);

        String[] spelerGegevens = controller.geefSpeler();
        
        System.out.println();
        System.out.println(controller.getString("registreer.succes"));
        System.out.printf("%s%14s%n", controller.getString("output.gebruikersnaam"), controller.getString("output.rechten")); //wachtwoord wordt hier niet meer weergegeven.
        
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
