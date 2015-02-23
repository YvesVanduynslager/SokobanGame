package gui;

import java.util.Scanner;
import domein.DomeinController;

/**
 * UC1MeldAan. Dit is de klasse die de UI verzorgt voor UC meld aan.
 *
 * @author Yves
 */
public class UC1MeldAan
{
    public void meldAan()
    {
        DomeinController controller = new DomeinController();
        ConsoleApplicatie app = new ConsoleApplicatie();
        Scanner scanner = new Scanner(System.in);

        String[] spelerGegevens;
        String gebruikersnaam, wachtwoord;
        boolean adminrechten;
        int keuze;

        System.out.println();
        System.out.printf("%s%n%s%n%s%n", " -----------", "| AANMELDEN |", " -----------");
        do
        {
            System.out.print("Geef gebruikersnaam ('stop' om te stoppen): ");
            gebruikersnaam = scanner.next();
            if (gebruikersnaam.equals("stop"))
            {
                app.startUI(); //starten van nieuwe startui
                System.gc();
            }
            System.out.print("Geef wachtwoord ('stop' om te stoppen): ");
            wachtwoord = scanner.next();
            if (wachtwoord.equals("stop"))
            {
                app.startUI();
                System.gc();
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

        adminrechten = (spelerGegevens[1].equals("ja"));

        if (adminrechten)
        {
            System.out.printf("%s%n%s%n%s%n%s%n", "Keuzemenu:", "[1] Speel spel", "[2] Configureer nieuw spel", "[3] Wijzig een bestaand spel");
            System.out.print("Uw keuze: ");
            keuze = scanner.nextInt();

            switch (keuze)
            {
                /*hier voorlopig nog System.exit(0) om het programma te doen stoppen */
                case 1:
                    //System.exit(0); //START USE CASE Speel spel
                    break;
                case 2:
                    System.exit(0); //START USE CASE Configureer nieuw spel
                    break;
                case 3:
                    System.exit(0); //START USE CASE Wijzig spel
                    break;
            }
        }
        else
        {
            System.out.printf("%s%n%s%n", "Keuzemenu:", "[1] Speel spel");
            System.out.print("Uw keuze: ");
            keuze = scanner.nextInt();

            switch (keuze)
            {
                /*hier voorlopig nog System.exit(0) om het programma te doen stoppen */
                case 1:
                    System.exit(0); //START USE CASE Speel spel
                    break;
            }
        }

    }
}
