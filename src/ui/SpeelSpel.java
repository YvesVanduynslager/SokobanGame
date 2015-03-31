package ui;

/* IMPORTS */
import domein.DomeinController;
import java.util.List;
import java.util.Scanner;

/**
 * Staat in voor het Speel spel menu.
 *
 * @author Yves
 */
public class SpeelSpel
{
    /* DECLARATIES CONSTANTEN */
    private final DomeinController controller;

    /**
     * Constructor initialiseert een DomeinController object.
     *
     * @param controller DomeinController object dat moet ingesteld worden.
     */
    public SpeelSpel(DomeinController controller)
    {
        this.controller = controller;
    }

    /**
     * Start de ui voor Speel spel.
     */
    public void startSpeelSpelUI()
    {
        /* DECLARATIES EN/OF INITIALISATIES VARIABELEN */
        List<String> spelnamen = controller.geefSpelNamen();
        Scanner scanner = new Scanner(System.in);
        int keuze = 0;
        boolean nogSpelborden;

        do
        {
            System.out.println(localeString("speelspel.keuze"));

            int index = 0;
            for (String spelnaam : spelnamen)
            {
                ++index;
                System.out.print("[" + index + "] " + spelnaam + "\n");
            }

            System.out.print(localeString("keuze"));
            System.out.println();
            keuze = scanner.nextInt();

            String keuzeString = spelnamen.get(--keuze);
            
            controller.selecteerSpel(keuzeString); //selecteert spel
            controller.startVolgendSpelbord(); //start het volgende spelbord

            do
            {
                while (!controller.huidigSpelbordVoltooid()) //Wordt uitgevoerd als het spelbord nog niet alle kisten op de doelen heeft staan.
                {
                    System.out.println(controller.spelbordToString());
                    System.out.println(localeString("speelspel.aantalzetten") + ": " + controller.geefAantalZetten());
                    System.out.println(localeString("speelspel.beweging"));
                    System.out.println(localeString("speelspel.omhoog"));
                    System.out.println(localeString("speelspel.omlaag"));
                    System.out.println(localeString("speelspel.links"));
                    System.out.println(localeString("speelspel.rechts"));
                    //System.out.println(localeString("speelspel.reset"));
                    System.out.println("[0] " + localeString("terug"));
                    System.out.print(localeString("keuze"));

                    keuze = scanner.nextInt();

                    if (keuze == 0)
                    {
                        return;
                    }
//                    else if (keuze == 5)
//                    {
//                        controller.resetSpelbord(keuzeString);
//                    }
                    else
                    {
                        beweeg(keuze);
                    }
                }

                /* Wordt uitgevoerd vanaf het spelbord alle kisten op de doelen heeft staan */
                controller.verhoogAantalVoltooideBorden();
                System.out.println(controller.spelbordToString());

                System.out.println(localeString("speelspel.gewonnen1") + controller.geefAantalZetten() + " " + localeString("speelspel.gewonnen2"));
                System.out.println(localeString("speelspel.bordenvoltooid1") + " " + controller.geefAantalVoltooideBorden() + " "
                        + localeString("speelspel.bordenvoltooid2") + " " + controller.geefAantalSpelborden() + " "
                        + localeString("speelspel.bordenvoltooid3"));

                System.out.println();

                if (controller.geefAantalVoltooideBorden() == controller.geefAantalSpelborden())
                {
                    nogSpelborden = false;
                    System.out.println(localeString("speelspel.geenspelborden"));
                }
                else
                {
                    nogSpelborden = true;
                    System.out.println(localeString("speelspel.volgendspelbord"));
                    System.out.print(localeString("keuze"));
                    keuze = scanner.nextInt();
                    if (keuze == 1)
                    {
                        controller.startVolgendSpelbord();
                    }
                }
            }
            while (keuze != 2 && nogSpelborden);

            System.out.println(localeString("speelspel.volgendspel"));
            System.out.print(localeString("keuze"));
            keuze = scanner.nextInt();
            System.out.println();
        }
        while (keuze != 2 && keuze != 0);
    }

    /**
     * Geeft de keuze van beweging door aan DomeinController.
     *
     * @param keuzeBeweging de keuze van beweging als int.
     */
    private void beweeg(int keuzeBeweging)
    {
        controller.beweeg(--keuzeBeweging);
    }
    
    private String localeString(String key)
    {
        return controller.getString(key);
    }
}
