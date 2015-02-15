package gui;

import domein.DomeinController;
import java.util.Scanner;

/**
 *
 * @author Yves
 */
public class SokobanApp
{
    public static void main(String args[])
    {
        Scanner scanner = new Scanner(System.in);
        DomeinController spelController = new DomeinController();
        System.out.print("Geef gebruikersnaam: ");
        String gebruikersnaam = scanner.next();
        System.out.print("Geef wachtwoord: ");
        String wachtwoord = scanner.next();

        spelController.meldAan(gebruikersnaam, wachtwoord);
        String[] spelerGegevens = spelController.getSpeler();
        
        System.out.println("Succesvol aangemeld met volgende gegevens: ");
        System.out.println();
        System.out.printf("%s%20s%20s%20s%n", "ID", "Gebruikersnaam", "Wachtwoord", "Adminrechten");
        System.out.printf("%s%20s%20s%20b%n", spelerGegevens[0], spelerGegevens[1], spelerGegevens[2],
                spelController.getSpelerAdminrechten());
        System.out.println();
    }
}
