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
        //integer declareren om menukeuze in op te slaan
        int keuze;

        //integer declareren om taalkeuze in op te slaan
        int locale;

        System.out.printf("%s%n%s%n%s%n%s%n", "Kies uw taal | Choose your language | Choisissez votre langue", "[1] Nederlands", "[2] English", "[3] Français");
        System.out.print("Uw keuze | Your choice | Votre choix: ");
        locale = scanner.nextInt();

        controller.setTaalKeuze(locale);

        /* MAIN LOOP:
         bij ingeven van "terug" in MeldAan en Registreer zal dit stuk code opnieuw uitgevoerd worden */
        do
        {
            System.out.println();
            System.out.printf("%s%n%s%n%s%n%s%n", controller.getString("hoofdmenu.main"), controller.getString("hoofdmenu.optie1"), controller.getString("hoofdmenu.optie2"), controller.getString("hoofdmenu.optie0"));
            System.out.print(controller.getString("keuze"));
            keuze = scanner.nextInt();

            if (keuze == 1)
            {
                //START UC 2: Registreer
                Registreer registreren = new Registreer(controller);
                registreren.startRegistreerUI();

                if (registreren.isSucces())
                {
                    toonSpelMenu();
                }
            }
            else
            {
                if (keuze == 2)
                {
                    //START UC 1: Meld aan
                    MeldAan aanmelden = new MeldAan(controller);
                    aanmelden.startMeldAanUI();

                    if (aanmelden.isSucces())
                    {
                        toonSpelMenu();
                    }
                }
            }
        }
        while (keuze != 0);

        System.exit(0); //Afsluiten
    }

    public void toonSpelMenu()
    {
        Scanner scanner = new Scanner(System.in);
        boolean heeftAdminrechten;
        int keuze;

        String[] spelerGegevens = controller.geefSpeler();
        heeftAdminrechten = (spelerGegevens[1].equals("ja"));

        if (heeftAdminrechten) //Menu voor gebruiker MET ADMINRECHTEN
        {
            System.out.println();
            System.out.printf("%s%n%s%n%s%n%s%n%s%n", controller.getString("spelmenu.main"),
                    controller.getString("spelmenu.optie1"),
                    controller.getString("spelmenu.optie2"),
                    controller.getString("spelmenu.optie3"),
                    controller.getString("spelmenu.optie4"));
            System.out.print(controller.getString("keuze"));
            keuze = scanner.nextInt();
            System.out.println();

            switch (keuze)
            {
                case 1: //START UC3: Speel Spel
                    SpeelSpel speelSpel = new SpeelSpel(controller);
                    speelSpel.startSpelSpelUI();
                    break;
                case 2: //START USE CASE Configureer nieuw spel
                    System.exit(0); //hier voorlopig nog System.exit(0) om het programma te doen stoppen.
                    break;
                case 3: //START USE CASE Wijzig spel
                    System.exit(0); //hier voorlopig nog System.exit(0) om het programma te doen stoppen.
                    break;
            }
        }
        else //Menu voor gebruiker ZONDER ADMINRECHTEN
        {
            System.out.println();
            System.out.printf("%s%n%s%n%s%n", controller.getString("spelmenu.main"), controller.getString("spelmenu.optie1"), controller.getString("spelmenu.optie4"));
            System.out.print(controller.getString("keuze"));
            keuze = scanner.nextInt();
            System.out.println();

            switch (keuze)
            {
                case 1: //Start UC Speel Spel
                    SpeelSpel speelSpel = new SpeelSpel(controller);
                    speelSpel.startSpelSpelUI();
                    break;
            }
        }
    }
}
