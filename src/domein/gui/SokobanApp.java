package domein.gui;

import domein.DomeinController;
import java.util.Scanner;

/**
 * Main-klasse. Dit is de klasse die de UI zal starten en weergeven/tekenen
 *
 * @author Yves
 */
public class SokobanApp
{
    public static void main(String args[])
    {
        Scanner scanner = new Scanner(System.in);
        DomeinController spelController = new DomeinController();
        String[] spelerGegevens;

        System.out.printf("%s%n%s%n%s%n%s%n","Wilt u:", "[1] registreren", "[2] aanmelden", "[0] stoppen");
        System.out.print("Uw keuze: ");
        int keuze = scanner.nextInt();
        while (keuze != 0)
        {
            if (keuze == 1)
            {
                //use case registreer
                System.out.print(" ------------");
                System.out.print("| REGISTREER |");
                System.out.print(" ------------");
                System.exit(0);
            }
            else
            {
                if (keuze == 2)
                {
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

                        spelController.meldAan(gebruikersnaam, wachtwoord);
                        spelerGegevens = spelController.getSpeler();

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
                    System.out.printf("%14s%15s%15s%15s%n", "Gebruikersnaam", "Adminrechten", "Voonraam", "Achternaam"); //wachtwoord wordt hier niet meer weergegeven.
                    System.out.printf("%14s%15b%15s%15s%n", spelerGegevens[0], spelController.getSpelerAdminrechten(),
                            spelerGegevens[2], spelerGegevens[3]);
                    System.out.println();

                    System.out.printf("%s%n%s%n%s%n%s%n", "Keuzemenu:", "[1] Speel spel", "[2] Configureer nieuw spel", "[3] Wijzig een bestaand spel");
                    System.out.print("Uw keuze: ");
                    keuze = scanner.nextInt();
                    switch (keuze)
                    {
                        /*hier voorlopig nog System.exit(0) om het programma te doen stoppen */
                        case 1:
                            System.exit(0); //start use case Speel spel
                            break;
                        case 2:
                            System.exit(0); //start use case Configureer nieuw spel
                            break;
                        case 3:
                            System.exit(0); //start use case Wijzig spel
                            break;
                        default:
                            System.exit(0);
                    }
                }
            }
        }
    }
}
