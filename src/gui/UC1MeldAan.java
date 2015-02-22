/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.Scanner;
import domein.DomeinController;

/**
 *
 * @author Jeroen
 */
public class UC1MeldAan
{
    public void meldAan()
    {
        DomeinController controller = new DomeinController();
        Scanner scanner = new Scanner(System.in);
        String[] spelerGegevens;

        System.out.println();
        System.out.printf("%s%n%s%n%s%n", " -----------", "| AANMELDEN |", " -----------");
        do
        {
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
    }
}
