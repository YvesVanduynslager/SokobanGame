package gui; 

import java.util.Scanner;
import domein.DomeinController;

/**
 * USE CASE 1 Meld aan. Deze klasse staat in voor het tekenen en beheer van de
 * ui voor UC1 MeldAan.
 *
 * @author Yves
 */
public class MeldAan
{
    private final DomeinController controller;
    private boolean geldig = false;

    public MeldAan(DomeinController controller)
    {
        this.controller = controller;
    }

    public void startMeldAanUI()
    {
        Scanner scanner = new Scanner(System.in);

        String[] spelerGegevens;
        String gebruikersnaam, wachtwoord;

        System.out.println();
        System.out.printf("%s%n%s%n%s%n", " -----------", "| AANMELDEN |", " -----------");
        do
        {
            System.out.print("Geef gebruikersnaam (\"terug\" om terug te gaan naar het hoofdmenu): ");
            gebruikersnaam = scanner.next();
            if (gebruikersnaam.equals("terug"))
            {
                return;  /* toonHoofdmenu() in ConsoleApplicatie opnieuw uitvoeren.
                 dus kortweg: opnieuw tonen van hoofdmenu */

            }
            System.out.print("Geef wachtwoord (\"terug\" om terug te gaan naar het hoofdmenu): ");
            wachtwoord = scanner.next();
            if (wachtwoord.equals("terug"))
            {
                return;
            }

            controller.meldAan(gebruikersnaam, wachtwoord);
            spelerGegevens = controller.geefSpeler();

            if (spelerGegevens[0] == null) /*Bij het uitvoeren van een sql-statement met onbestaande
             gebruikersnaam of onbestaand wachtwoord, zal de spelerGegevens-string gevuld worden met null-waarden.*/

            {
                System.out.println("\nGebruiker niet gevonden of foutief wachtwoord ingegeven, probeer opnieuw");
            }
        }
        while (spelerGegevens[0] == null); /*als er geen gegevens opgehaald werden uit de databank
         (dus null, ofwel gebruiker niet gevonden, ofwel wachtwoord niet gevonden) zal de lus
         opnieuw doorlopen worden.
         */

        geldig = true;
        
        System.out.println();
        System.out.println("Succesvol aangemeld met volgende gegevens: ");
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
