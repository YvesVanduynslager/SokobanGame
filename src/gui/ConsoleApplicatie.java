package gui;

import java.util.Scanner;
import domein.DomeinController;

/**
 * Centrale klasse die de verschillende use cases zal opstarten.
 *
 * @author Yves
 */
public class ConsoleApplicatie
{
    public static void StartUI(DomeinController spelController)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.printf("%s%n%s%n%s%n%s%n", "Wilt u:", "[1] registreren", "[2] aanmelden", "[0] stoppen");
        System.out.print("Uw keuze: ");
        int keuze = scanner.nextInt();
        while (keuze != 0)
        {
            if (keuze == 1)
            {
                //START USE CASE Registreer
                System.exit(0);
            }
            else
            {
                if (keuze == 2)
                {
                    //START USE CASE Meld aan
                    gui.UC1MeldAan.meldAan(spelController);
                }
                
                System.out.println();
                System.out.printf("%s%n%s%n%s%n%s%n", "Keuzemenu:", "[1] Speel spel", "[2] Configureer nieuw spel", "[3] Wijzig een bestaand spel");
                System.out.print("Uw keuze: ");
                
                keuze = scanner.nextInt();
                switch (keuze)
                {
                    /*hier voorlopig nog System.exit(0) om het programma te doen stoppen */
                    case 1:
                        System.exit(0); //START USE CASE Speel spel
                        break;
                    case 2:
                        System.exit(0); //START USE CASE Configureer nieuw spel
                        break;
                    case 3:
                        System.exit(0); //START USE CASE Wijzig spel
                        break;
                    default:
                        System.exit(0);
                }
            }
        }
    }
}