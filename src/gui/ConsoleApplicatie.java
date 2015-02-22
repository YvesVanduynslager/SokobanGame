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
    public void StartUI()
    {
        ConsoleApplicatie app = new ConsoleApplicatie();
        DomeinController controller = new DomeinController();
        Scanner scanner = new Scanner(System.in);

        System.out.printf("%s%n%s%n%s%n%s%n", "Wilt u:", "[1] registreren", "[2] aanmelden", "[0] stoppen");
        System.out.print("Uw keuze: ");
        int keuze = scanner.nextInt();
        if (keuze != 0)
        {
            if (keuze == 1)
            {

                //use case registreer
                System.out.print(" ------------");
                System.out.print("| REGISTREER |");
                System.out.print(" ------------");
            UC2Registreer registreren = new UC2Registreer();            
            registreren.registreer();
            }
            else
            {
                if (keuze == 2)
                {

                UC1MeldAan aanmelden = new UC1MeldAan();
                aanmelden.meldAan();
                }
//                    System.out.printf("%s%14s%14b%14s%14s%n", spelerGegevens[0], spelerGegevens[1],
//                            spelerGegevens[3], spelerGegevens[4], spelerGegevens[5]);
                System.out.println();

                
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


