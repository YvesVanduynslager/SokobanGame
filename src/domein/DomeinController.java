package domein;

import exceptions.GebruikerBestaatException;
import java.util.List;

/**
 * Staat in voor communicatie tussen GUI en business-logica.
 *
 * @author Yves
 */
public class DomeinController
{
    private final SpelerRepository spelerRepository;
    private final Taal resourceBundle;
    private Speler huidigeSpeler;
    private String[] spelerString;

    private final SpelRepository spelRepository;
    private Spel huidigSpel;
    
    private int aantalZetten;

    /**
     * Default-constructor maakt een SpelerRepository (spelers opslaan en
     * ophalen), SpelRepository (spellen opslaan en ophalen) en een Taal-object
     * (object om de gewenste taal in te stellen) aan.
     */
    public DomeinController()
    {
        /* Aanmaken van een SpelerRepository en SpelRepository.*/
        spelerRepository = new SpelerRepository();
        spelRepository = new SpelRepository();

        /* Taalobject initialiseren om later een taal te kunnen instellen*/
        resourceBundle = new Taal();
    }

    /**
     * UC1 Meld aan
     * Methode die zorgt voor het aanmelden van een gebruiker
     *
     * @param gebruikersnaam Instellen ven gebruikersnaam
     * @param wachtwoord Instellen van wachtwoord
     */
    public void meldAan(String gebruikersnaam, String wachtwoord)
    {
        Speler sp = spelerRepository.geefSpeler(gebruikersnaam, wachtwoord);
        setHuidigeSpeler(sp);
    }

    /**
     * UC1 Meld aan, UC2 Registreer
     * @return geeft de gebruikersnaam en adminrechten weer van de speler via
     * een String[]
     */
    public String[] geefSpeler()
    {
        spelerString = new String[2];
        spelerString[0] = huidigeSpeler.getGebruikersnaam();
        spelerString[1] = huidigeSpeler.getAdminrechten();
        return spelerString;
    }

    /**
     * UC1 Meld aan, UC2 Registreer
     * Bijhouden welke speler is aangemeld.
     *
     * @param speler Speler-object die tussentijds bewaard moet worden.
     */
    private void setHuidigeSpeler(Speler speler) //UC1 Meld aan, UC2 Registreer
    {
        this.huidigeSpeler = speler;
    }

    /**
     * UC2 Registreer
     * Maakt een Speler-object aan met ingegeven parameters, en geeft dit object
     * door aan spelerRepository-object, stelt de huidige speler in met het
     * gemaakte Speler-object.
     *
     * @param gebruikersnaam gebruikersnaam van de speler
     * @param wachtwoord wachtwoord van de speler
     * @param voornaam voornaam van de speler
     * @param achternaam achternaam van de speler
     * @throws exceptions.GebruikerBestaatException throws naar en handelt af in
     * Registreer.
     */
    public void registreer(String gebruikersnaam, String wachtwoord, String voornaam, String achternaam) throws IllegalArgumentException, GebruikerBestaatException
    {
        Speler sp = new Speler(gebruikersnaam, wachtwoord, voornaam, achternaam, "nee");
        spelerRepository.voegToe(sp);
        setHuidigeSpeler(sp);
    }

    /**
     * Alle UC's
     * Geeft de taalkeuze van de gebruiker door aan het Taal-object.
     *
     * @param locale code van de taalkeuze
     */
    public void setTaalKeuze(int locale)
    {
        resourceBundle.setTaalKeuze(locale);
    }

    /**
     * Alle UC's
     * @param key de key die in de ResourceBundles overeenkomt met de op te
     * halen tekst
     * @return De opgehaalde tekst adhv key.
     */
    public String getString(String key)
    {
        return resourceBundle.getStringUitBundle(key);
    }

    /**
     * UC3: Speel spel
     * Methode om een opgeslaan spel te selecteren en te starten.
     * @param spelNaam Naam van het spel dat de gebruiker wenst te spelen.
     */
    public void selecteerSpel(String spelNaam)
    {
        this.setHuidigSpel(spelRepository.geefSpel(spelNaam));
        huidigSpel.start();
    }
    
    public void startVolgendSpelbord()
    {
        huidigSpel.start();
    }


    /**
     * UC3: Speel spel, UC4: Voltooi spelbord
     * Geeft een boolean terug die laat weten of het spelbord voltooid is of niet.
     * @return True voor voltooid, false voor onvoltooid.
     */
    public boolean huidigSpelbordVoltooid()
    {
        return huidigSpel.getHuidigSpelbord().isVoltooid();
    }

    /**
     * UC3: Speel spel, UC4: Voltooi spelbord
     * Stelt het huidige spel in.
     * @param spel Object van Spel dat ingesteld moet worden als huidig Spel.
     */
    private void setHuidigSpel(Spel spel)
    {
        this.huidigSpel = spel;
        this.aantalZetten = 0;
    }

    /**
     * UC3: Speel spel, UC4: Voltooi spelbord
     * Deze methode geeft het huidige spelbord weer in String[][]-formaat.
     * @return Het huidige spelbord als String[][]
     */
    public String[][] geefHuidigSpelbord()
    {
        Spelbord huidigSpelbord = huidigSpel.getHuidigSpelbord();
        Element[][] velden = huidigSpelbord.geefVelden();

        String[][] veldenString = new String[10][10];

        for (int i = 0; i < velden.length; i++)
        {
            for (int j = 0; j < velden[i].length; j++)
            {
                veldenString[i][j] = velden[i][j].toString();
            }
        }
        return veldenString;
    }

    /**
     * UC3: Speel spel
     * Geeft het totaal aantal beschikbare spelborden weer.
     * @return Aantal beschikbare spelborden.
     */
    public int geefAantalSpelborden()
    {
        return huidigSpel.geefAantalSpelborden();
    }

    /**
     * UC3: Speel spel
     * Geeft het aantal voltooide spelborden weer.
     * @return Aantal voltooide spelborden.
     */
    public int geefAantalVoltooideBorden()
    {
        return huidigSpel.geefAantalVoltooideBorden();
    }

    /**
     * UC3: Speel spel
     * @return Lijst met beschikbare spellen
     */
    public List<String> geefSpelNamen()
    {
        return spelRepository.geefSpelNamen();
    }

    /**
     * UC4: Voltooi spelbord
     * Staat in voor het kiezen van een richting waar het mannetje naar toe moet
     * bewegen op het huidige spelbord.
     * @param richting Gewenste richting waar bewogen moet naar worden.
     */
    public void beweeg(int richting)
    {
        huidigSpel.getHuidigSpelbord().verplaatsMannetje(richting);
        ++aantalZetten;
    }

    /**
     * UC3: Speel spel, UC4: Voltooi spelbord
     * Geeft het huidige spelbord terug als een String;
     * @return huidige spelbord als String;
     */
    public String spelbordToString()
    {
        return huidigSpel.spelbordToString();
    }
    
    /**
     * UC4: Voltooi spelbord
     * @return het aantal gedane zetten als int.
     */
    public int getAantalZetten()
    {
        return aantalZetten;
    }

//    public void setSpelbordVoltooid()
//    {
//        huidigSpel.getHuidigSpelbord().setVoltooid(true);
//    }
}