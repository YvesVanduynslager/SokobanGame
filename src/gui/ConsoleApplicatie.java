package gui;

import java.util.Scanner;

/**
 * Centrale klasse die de verschillende use cases zal opstarten.
 *
 * @author Yves
 */
public class ConsoleApplicatie
{
    public void startUI()
    {
        Scanner scanner = new Scanner(System.in);
        int keuze;

        System.out.printf("%s%n%s%n%s%n%s%n", "Wilt u:", "[1] registreren", "[2] aanmelden", "[0] afsluiten");
        System.out.print("Uw keuze: ");
        keuze = scanner.nextInt();
        
        if (keuze != 0)
        {
            if (keuze == 1)
            {
                //START USE CASE registreer
                UC2Registreer registreren = new UC2Registreer();
                registreren.registreer();
            }
            else
            {
                if (keuze == 2)
                {
                    //START USE CASE meld aan
                    UC1MeldAan aanmelden = new UC1MeldAan();
                    aanmelden.meldAan();
                }
            }
        }
        else
        {
            //AFSLUITEN
            System.exit(0);
        }
    }
}
