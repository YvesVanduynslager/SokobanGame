package gui;

import domein.DomeinController;
import java.util.Scanner;

/**
 * Centrale klasse die de verschillende use cases zal opstarten.
 *
 * @author Yves
 */
public class ConsoleApplicatie
{
    private final DomeinController controller;

    public ConsoleApplicatie(DomeinController controller)
    {
        this.controller = controller;
    }

    public void toonHoofdmenu()
    {
        Scanner scanner = new Scanner(System.in);
        int keuze;

        do //bij ingeven van "stop" in MeldAan en Registreer zal dit stuk code opnieuw uitgevoerd worden
        {
            System.out.println();
            System.out.printf("%s%n%s%n%s%n%s%n", "Wilt u:", "[1] registreren", "[2] aanmelden", "[0] afsluiten");
            System.out.print("Uw keuze: ");
            keuze = scanner.nextInt();
            if (keuze == 1)
            {
                //START USE CASE 2: Registreer
                Registreer registreren = new Registreer(controller);
                registreren.registreer();
                toonSpelMenu();
            }
            else
            {
                if (keuze == 2)
                {
                    //START USE CASE 1: Meld aan
                    MeldAan aanmelden = new MeldAan(controller);
                    aanmelden.meldAan();
                    toonSpelMenu();
                }
            }
        }
        while (keuze != 0);
    }
    
    public void toonSpelMenu()
    {
        Scanner scanner = new Scanner(System.in);
        boolean adminrechten;
        int keuze;
        
        String[] spelerGegevens = controller.geefSpeler();
        adminrechten = (spelerGegevens[1].equals("ja"));

        if (adminrechten)
        {
            System.out.println();
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
            System.out.println();
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
