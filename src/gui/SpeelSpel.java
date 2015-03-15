package gui;

import domein.DomeinController;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Yves
 */
public class SpeelSpel
{
    DomeinController controller;

    public SpeelSpel(DomeinController controller)
    {
        this.controller = controller;
    }

    public void startSpelSpelUI()
    {
        List<String> spelnamen = controller.geefSpelNamen();
        Scanner scanner = new Scanner(System.in);
        int verderSpelenKeuze;

        do
        {
            System.out.println("Kies een spel uit de volgende mogelijkheden:");

            int index = 0;
            for (String spelnaam : spelnamen)
            {
                ++index;
                System.out.print("[" + index + "] " + spelnaam + "\n");
            }

            System.out.println("Uw keuze: ");
            int keuze = scanner.nextInt();

            String keuzeString = null;
            switch (keuze)
            {
                case 1:
                    keuzeString = spelnamen.get(--keuze);
                    break;
                case 2:
                    keuzeString = "makkelijk";
                    break;
                case 3:
                    keuzeString = spelnamen.get(--keuze);
            }

            controller.selecteerSpel(keuzeString);

            while (!controller.huidigSpelbordVoltooid())
            {
                System.out.println(controller.spelbordToString());
                System.out.println("Beweging:");
                System.out.println("[1] Omhoog");
                System.out.println("[2] Beneden");
                System.out.println("[3] Links");
                System.out.println("[4] Rechts");
                System.out.print("Uw keuze: ");

                int keuzeBeweging = scanner.nextInt();
                beweeg(keuzeBeweging);
                
            }
            System.out.println("Wilt u verderspelen?\n[1] Ja\n[2] Nee");
            System.out.println("Uw keuze: ");
            verderSpelenKeuze = scanner.nextInt();
        }
        while (verderSpelenKeuze != 2);
    }

    private void beweeg(int keuzeBeweging)
    {
        controller.beweeg(keuzeBeweging-1);
    }
}
