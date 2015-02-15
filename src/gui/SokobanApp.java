package gui;

import domein.DomeinController;
import java.util.Scanner;

/**
 * Main-klasse.
 * @author Yves
 */
public class SokobanApp
{
    public static void main(String args[])
    {
        Scanner scanner = new Scanner(System.in);
        DomeinController spelController = new DomeinController();
        String[] spelerGegevens;
        do
        {
            System.out.print("Geef gebruikersnaam: ");
            String gebruikersnaam = scanner.next();
            System.out.print("Geef wachtwoord: ");
            String wachtwoord = scanner.next();

            spelController.meldAan(gebruikersnaam, wachtwoord);
            spelerGegevens = spelController.getSpeler();
            
            if(spelerGegevens[0] == null) /*Bij het uitvoeren van een sql-statement met onbestaande
                gebruikersnaam of onbestaand wachtwoord, zal de spelerGegevens-string gevuld worden met null-waarden.
            */
            {
                System.out.println("Gebruiker niet gevonden of foutief wachtwoord ingegeven, probeer opnieuw");
            }
        }
        while (spelerGegevens[0] == null); //hier nogmaals controle om lus opnieuw te starten
        
        System.out.println("Succesvol aangemeld met volgende gegevens: ");
        System.out.println();
        System.out.printf("%s%20s%20s%n", "ID", "Gebruikersnaam", "Adminrechten"); //wachtwoord geef ik hier niet meer weer.
        System.out.printf("%s%20s%20b%n", spelerGegevens[0], spelerGegevens[1],
                spelController.getSpelerAdminrechten());
        System.out.println();
    }
}
