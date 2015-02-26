package gui;
//USE CASE 1

import java.util.Scanner;
import domein.DomeinController;

/**
 * UC1MeldAan. Dit is de klasse die de UI verzorgt voor UC meld aan.
 *
 * @author Yves
 */
public class MeldAan
{
    private final DomeinController controller;
    public MeldAan(DomeinController controller)
    {
        this.controller = controller;
    }
    public void meldAan()
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
            if (gebruikersnaam.equals("stop"))
            {
                return; /*keert terug naar de methode die de call heeft gepleegd (hier dus ConsoleApplicatie.startUI())
                dus kortweg: opnieuw tonen van hoofdmenu.
                ZIE: http://www.java-samples.com/showtutorial.php?tutorialid=280*/
            }
            System.out.print("Geef wachtwoord (\"terug\" om terug te gaan naar het hoofdmenu): ");
            wachtwoord = scanner.next();
            if (wachtwoord.equals("stop"))
            {
                return; /*keert terug naar de methode die de call heeft gepleegd (hier dus ConsoleApplicatie.startUI())
                dus kortweg: opnieuw tonen van hoofdmenu */
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

        System.out.println();
        System.out.println("Succesvol aangemeld met volgende gegevens: ");
        System.out.printf("%s%14s%n", "Gebruikersnaam", "Adminrechten"); //wachtwoord wordt hier niet meer weergegeven.
        for (String spelerGegeven : spelerGegevens)
        {
            System.out.printf("%14s", spelerGegeven);
        }
        System.out.println();
    }
}