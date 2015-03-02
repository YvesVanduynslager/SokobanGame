package gui;

import domein.DomeinController;
import java.util.Locale;
import java.util.Scanner;
import java.util.ResourceBundle;
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
        
        //integer declareren om taalkeuze in op te slaan
        int locale;
        
        System.out.printf("%s%n%s%n%s%n%s%n", "Kies uw taal | Choose your language | Choisissez votre langue", "[1] Nederlands", "[2] English", "[3] Français");
        System.out.print("Uw keuze | Your choice | Votre choix: ");
        locale = scanner.nextInt();
        
        controller.setTaalKeuze(locale);
        
        do //bij ingeven van "terug" in MeldAan en Registreer zal dit stuk code opnieuw uitgevoerd worden
        {
            System.out.println();
            System.out.printf("%s%n%s%n%s%n%s%n", controller.getString("hoofdmenu.main"), controller.getString("hoofdmenu.optie1"), controller.getString("hoofdmenu.optie2"), controller.getString("hoofdmenu.optie0"));
            System.out.print(controller.getString("hoofdmenu.keuze"));
            keuze = scanner.nextInt();
            if (keuze == 1)
            {
                //START USE CASE 2: Registreer
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
                    //START USE CASE 1: Meld aan
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
            System.out.printf("%s%n%s%n%s%n%s%n", controller.getString("spelmenu.main"), controller.getString("spelmenu.optie1"), controller.getString("spelmenu.optie2"), controller.getString("spelmenu.optie3"));
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
            System.out.printf("%s%n%s%n", controller.getString("spelmenu.main"), controller.getString("spelmenu.optie1"));
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
