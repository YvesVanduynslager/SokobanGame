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

    public void startUI()
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
            }
            else
            {
                if (keuze == 2)
                {
                    //START USE CASE 1: Meld aan
                    MeldAan aanmelden = new MeldAan(controller);
                    aanmelden.meldAan();
                }
            }
        }
        while (keuze != 0);
    }
}
