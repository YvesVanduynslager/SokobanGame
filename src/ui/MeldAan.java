package ui; 

/* IMPORTS */
import java.util.Scanner;
import domein.DomeinController;
import wachtwoordbeveiliging.BCrypt;

/**
 * USE CASE 1 Meld aan. Deze klasse staat in voor het tekenen en beheer van de
 * ui voor UC1 MeldAan.
 *
 * @author Yves
 */
public class MeldAan
{
    /* DECLARATIES CONSTANTEN */
    private final DomeinController controller;
    /* DECLARATIES VARIABELEN */
    private boolean geldig = false;

    /**
     * Constructor initialiseert een DomeinController object.
     * @param controller DomeinController object dat moet ingesteld worden.
     */
    public MeldAan(DomeinController controller)
    {
        this.controller = controller;
    }

    /**
     * Start de ui voor het aanmelden.
     */
    public void startMeldAanUI()
    {
        Scanner scanner = new Scanner(System.in);

        String[] spelerGegevens;
        String gebruikersnaam, wachtwoord;

        System.out.println();
        System.out.printf("%s%n%s%n%s%n", localeString("meldaan.border"), localeString("meldaan.main"), localeString("meldaan.border"));
        do
        {
            System.out.print(localeString("meldaan.input.gebruikersnaam"));
            gebruikersnaam = scanner.next();
            if (gebruikersnaam.equals(localeString("terug")))
            {
                return;  /* toonHoofdmenu() in ConsoleApplicatie opnieuw uitvoeren.
                 dus kortweg: opnieuw tonen van hoofdmenu */

            }
            System.out.print(localeString("meldaan.input.wachtwoord"));
            wachtwoord = scanner.next();
            if (wachtwoord.equals(localeString("terug")))
            {
                return;
            }
            wachtwoord = BCrypt.hashpw(wachtwoord, "$2a$10$RV4IhXXJFyL3EmzvS4sqHu");

            controller.meldAan(gebruikersnaam, wachtwoord);
            spelerGegevens = controller.geefSpeler();

            if (spelerGegevens[0] == null) /*Bij het uitvoeren van een sql-statement met onbestaande
             gebruikersnaam of onbestaand wachtwoord, zal de spelerGegevens-string gevuld worden met null-waarden.*/

            {
                System.out.println(localeString("meldaan.fout"));
            }
        }
        while (spelerGegevens[0] == null); /*als er geen gegevens opgehaald werden uit de databank
         (dus null, ofwel gebruiker niet gevonden, ofwel wachtwoord niet gevonden) zal de lus
         opnieuw doorlopen worden.
         */

        geldig = true; //nadat lus breekt, dus speler gevonden en opgehaald.
        
        System.out.println();
        System.out.println(localeString("meldaan.success"));
        System.out.printf("%s%14s%n", localeString("output.gebruikersnaam"), localeString("output.rechten")); //wachtwoord wordt hier niet meer weergegeven.
        for (String spelerGegeven : spelerGegevens)
        {
            System.out.printf("%14s", spelerGegeven);
        }
        System.out.println();
    }
    
    /**
     * Geeft een boolean terug om later te controleren of het aanmelden een succes is of niet.
     * @return True voor aanmelden succes, false voor aanmelden geen succes.
     */
    public boolean isSucces()
    {
        return geldig;
    }
    
    private String localeString(String key)
    {
        return controller.getString(key);
    }
}
