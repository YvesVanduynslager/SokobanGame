package gui;

import domein.DomeinController;
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
        ResourceBundle resource;
        
        switch (locale) //resourcebundle selecteren op basis van genomen keuze
        {
            case 1: resource = ResourceBundle.getBundle("resources.Resources_nl_BE");
            break;
            case 2: resource = ResourceBundle.getBundle("resources.Resources_en_UK");
            break;
            case 3: resource = ResourceBundle.getBundle("resources.Resources_fr_BE");
            break;
            default: resource = ResourceBundle.getBundle("resources.Resources_nl_BE");
            break; 
        }
        
        do //bij ingeven van "stop" in MeldAan en Registreer zal dit stuk code opnieuw uitgevoerd worden
        {
            System.out.println();
            System.out.printf("%s%n%s%n%s%n%s%n", resource.getString("hoofdmenu.main"), resource.getString("hoofmenu.optie1"), resource.getString("hoofdmenu.optie2"), resource.getString("hoofmenu.optie0"));
            System.out.print(resource.getString("keuze"));
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
