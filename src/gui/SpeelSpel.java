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
            System.out.println(controller.getString("speelspel.keuze"));

            int index = 0;
            for (String spelnaam : spelnamen)
            {
                ++index;
                System.out.print("[" + index + "] " + spelnaam + "\n");
            }

            System.out.print(controller.getString("keuze"));
            System.out.println();
            int keuze = scanner.nextInt();

            String keuzeString = null;
            switch (keuze)
            {
                case 1:
                    keuzeString = spelnamen.get(--keuze);
                    break;
                case 2:
                    keuzeString = "makkelijk"; //spelnamen.get(--keuze);
                    break;
                case 3:
                    keuzeString = spelnamen.get(--keuze);
            }

            controller.selecteerSpel(keuzeString); //selecteert spel en start het eerste spelbord
            int spelbordTeller = 0;
            
            do
            {
                while (!controller.huidigSpelbordVoltooid()) ///Wordt uitgevoerd als het spelbord nog niet alle kisten op de doelen heeft staan.
                {
                    System.out.println(controller.spelbordToString());
                    System.out.println(controller.getString("speelspel.beweging"));
                    System.out.println(controller.getString("speelspel.omhoog"));
                    System.out.println(controller.getString("speelspel.omlaag"));
                    System.out.println(controller.getString("speelspel.links"));
                    System.out.println(controller.getString("speelspel.rechts"));
                    System.out.print(controller.getString("keuze"));

                    int keuzeBeweging = scanner.nextInt();
                    beweeg(keuzeBeweging);
                }
                
                if(controller.huidigSpelbordVoltooid())
                    spelbordTeller++;
                
                /* Wordt uitgevoerd vanaf het spelbord alle kisten op de doelen heeft staan */
                System.out.println(controller.spelbordToString());
                System.out.println(controller.getString("speelspel.gewonnen1") + controller.getAantalZetten() + " " + controller.getString("speelspel.gewonnen2"));
                System.out.println("Aantal spelborden voltooid: " + controller.geefAantalVoltooideBorden() + " van " + controller.geefAantalSpelborden()
                        + " spelborden.");
                System.out.println(controller.getString("speelspel.verderspelen"));
                System.out.println(controller.getString("keuze"));
                verderSpelenKeuze = scanner.nextInt();

                if (verderSpelenKeuze == 1 && spelbordTeller < controller.geefAantalSpelborden())
                {
                    controller.startVolgendSpelbord(spelbordTeller);
                }
            }
            while (verderSpelenKeuze != 2);
            
            System.out.println("Nog een spel? ");
            System.out.println("[1] Ja");
            System.out.println("[2] Nee");
            System.out.println("Uw keuze: ");
            verderSpelenKeuze = scanner.nextInt();
        }
        while (verderSpelenKeuze != 2);
    }

    private void beweeg(int keuzeBeweging)
    {
        controller.beweeg(keuzeBeweging - 1);
    }
}