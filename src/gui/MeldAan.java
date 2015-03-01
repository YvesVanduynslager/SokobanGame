package gui; 

import java.util.Scanner;
import domein.DomeinController;
import java.util.ResourceBundle;

/**
 * USE CASE 1 Meld aan. Deze klasse staat in voor het tekenen en beheer van de
 * ui voor UC1 MeldAan.
 *
 * @author Yves
 */
public class MeldAan
{
    private final DomeinController controller;
    private ResourceBundle resource;
    private boolean geldig = false;

    public MeldAan(DomeinController controller)
    {
        this.controller = controller;
        this.resource = this.controller.getResourseBundle();
    }

    public void startMeldAanUI()
    {
        Scanner scanner = new Scanner(System.in);

        String[] spelerGegevens;
        String gebruikersnaam, wachtwoord;

        System.out.println();
        System.out.printf("%s%n%s%n%s%n", resource.getString("meldaan.border"), resource.getString("meldaan.main"), resource.getString("meldaan.border"));
        do
        {
            System.out.print(resource.getString("meldaan.input.gebruikersnaam"));
            gebruikersnaam = scanner.next();
            if (gebruikersnaam.equals(resource.getString("terug")))
            {
                return;  /* toonHoofdmenu() in ConsoleApplicatie opnieuw uitvoeren.
                 dus kortweg: opnieuw tonen van hoofdmenu */

            }
            System.out.print(resource.getString("meldaan.input.wachtwoord"));
            wachtwoord = scanner.next();
            if (wachtwoord.equals(resource.getString("terug")))
            {
                return;
            }

            controller.meldAan(gebruikersnaam, wachtwoord);
            spelerGegevens = controller.geefSpeler();

            if (spelerGegevens[0] == null) /*Bij het uitvoeren van een sql-statement met onbestaande
             gebruikersnaam of onbestaand wachtwoord, zal de spelerGegevens-string gevuld worden met null-waarden.*/

            {
                System.out.println(resource.getString("meldaan.fout"));
            }
        }
        while (spelerGegevens[0] == null); /*als er geen gegevens opgehaald werden uit de databank
         (dus null, ofwel gebruiker niet gevonden, ofwel wachtwoord niet gevonden) zal de lus
         opnieuw doorlopen worden.
         */

        geldig = true;
        
        System.out.println();
        System.out.println(resource.getString("meldaan.success"));
        System.out.printf("%s%14s%n", resource.getString("output.gebruikersnaam"), resource.getString("output.rechten")); //wachtwoord wordt hier niet meer weergegeven.
        for (String spelerGegeven : spelerGegevens)
        {
            System.out.printf("%14s", spelerGegeven);
        }
        System.out.println();
    }
    
    public boolean isSucces()
    {
        return geldig;
    }
}
