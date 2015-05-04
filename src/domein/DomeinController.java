package domein;

/* IMPORTS */
import exceptions.GebruikerBestaatException;
import exceptions.GebruikersnaamOngeldigException;
import exceptions.SpelNaamBestaatException;
import exceptions.WachtwoordOngeldigException;
import java.util.List;

/**
 * Staat in voor communicatie tussen GUI en business-logica.
 *
 * @author Yves
 */
public final class DomeinController
{
    /* DECLARATIES CONSTANTEN */
    private final SpelerRepository spelerRepository;
    private final SpelRepository spelRepository;
    private final Taal resourceBundle;

    /* DECLARATIES VARIABELEN */
    private Speler huidigeSpeler;
    private Spel huidigSpel;
    private String[] spelerString;
    private Spelbord customSpelbord;

    /**
     * Default-constructor maakt een SpelerRepository (spelers opslaan en
     * ophalen), SpelRepository (spellen opslaan en ophalen) en een Taal-object
     * (object om de gewenste taal in te stellen) aan.
     */
    public DomeinController()
    {
        /* Initialiseren van een SpelerRepository en SpelRepository voor ophalen
         en opslaan van Speler -en Spelgegevens. */
        spelerRepository = new SpelerRepository();
        spelRepository = new SpelRepository();

        /* Initialiseren van een Taalobject om een taal te kunnen instellen*/
        resourceBundle = new Taal();
    }

    /**
     * UC1 Meld aan. Methode die zorgt voor het aanmelden van een gebruiker
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
     * UC1 Meld aan, UC2 Registreer. Bevat de gebruikersnaam en adminrechten van
     * de aangemelde speler.
     *
     * @return String[] met gebruikersnaam en adminrechten.
     */
    public String[] geefSpeler()
    {
        spelerString = new String[2];
        spelerString[0] = huidigeSpeler.getGebruikersnaam();
        spelerString[1] = huidigeSpeler.getAdminrechten();
        return spelerString;
    }

    /**
     * UC1 Meld aan, UC2 Registreer. Instellen van de aangemelde speler.
     *
     * @param speler Speler-object met gegevens van aangemelde speler.
     */
    private void setHuidigeSpeler(Speler speler)
    {
        this.huidigeSpeler = speler;
    }

    /**
     * UC2 Registreer. Maakt een Speler-object aan met ingegeven parameters, en
     * geeft dit object door aan spelerRepository-object. Stelt de huidige
     * speler in met het gemaakte Speler-object.
     *
     * @param gebruikersnaam gebruikersnaam van de speler
     * @param wachtwoord wachtwoord van de speler
     * @param voornaam voornaam van de speler
     * @param achternaam achternaam van de speler
     * @throws exceptions.GebruikerBestaatException Als de gebruiker al
     * geregistreerd is in het systeem. Throws naar en handelt af in Registreer.
     * 
     * @throws GebruikersnaamOngeldigException
     * @throws WachtwoordOngeldigException
     */
    public void registreer(String gebruikersnaam, String wachtwoord,
            String voornaam, String achternaam)
            throws /*IllegalArgumentException*/GebruikersnaamOngeldigException, WachtwoordOngeldigException, GebruikerBestaatException
    {
        Speler sp = new Speler(gebruikersnaam, wachtwoord,
                voornaam, achternaam, "nee");
        spelerRepository.voegToe(sp);
        setHuidigeSpeler(sp);
    }

    /**
     * UC3: Speel spel. Selecteert het gewenste spel. Haalt het gewenste spel op
     * en stelt dit spel in als huidige spel.
     *
     * @param spelNaam De naam van het spel dat de gebruiker wenst te spelen,
     * als String.
     */
    public void selecteerSpel(String spelNaam)
    {
        this.setHuidigSpel(spelRepository.geefSpel(spelNaam));
    }

    /**
     * UC4: Voltooi spelbord. Stelt het volgende spelbord in.
     */
    public void startVolgendSpelbord()
    {
        huidigSpel.start();
    }

    /**
     * UC3: Speel spel, UC4: Voltooi spelbord. Geeft een boolean terug die laat
     * weten of het spelbord voltooid is of niet.
     *
     * @return True voor voltooid, false voor onvoltooid.
     */
    public boolean huidigSpelbordVoltooid()
    {
        return huidigSpel.getHuidigSpelbord().isVoltooid();
    }

    /**
     * UC3: Speel spel. Stelt het huidige spel in.
     *
     * @param spel Object van Spel dat ingesteld moet worden als huidig spel.
     */
    private void setHuidigSpel(Spel spel)
    {
        this.huidigSpel = spel;
    }

    /**
     * UC3: Speel spel. Geeft het totaal aantal spelborden terug.
     *
     * @return Aantal beschikbare spelborden.
     */
    public int geefAantalSpelborden()
    {
        return huidigSpel.geefAantalSpelborden();
    }

    /**
     * UC3: Speel spel. Geeft het aantal voltooide spelborden weer.
     *
     * @return Aantal voltooide spelborden.
     */
    public int geefAantalVoltooideBorden()
    {
        return huidigSpel.geefAantalVoltooideBorden();
    }

    /**
     * UC3: Speel spel. Geeft een lijst terug met beschikbare spellen.
     *
     * @return List met beschikbare spellen, String.
     */
    public List<String> geefSpelNamen()
    {
        return spelRepository.geefSpelNamen();
    }

    /**
     * UC3: Speel spel, UC4: Voltooi spelbord. Geeft het huidige spelbord terug
     * als String.
     *
     * @return Huidige spelbord als String;
     */
    public String spelbordToString()
    {
        return huidigSpel.getHuidigSpelbord().toString();
    }

    /**
     * UC3: Speel spel, UC4: Voltooi spelbord. Deze methode geeft het huidige
     * spelbord terug in String[][]-formaat. Dient als basis voor de opbouw van
     * het spelbord in de GUI.
     *
     * @return Het huidige spelbord als String[][]
     */
    public String[][] geefHuidigSpelbord()
    {
        return huidigSpel.getHuidigSpelbord().to2DString();
    }

    /**
     * UC4: Voltooi spelbord. Staat in voor het kiezen van een richting waar het
     * mannetje naar toe moet bewegen op het huidige spelbord.
     *
     * @param richting Gewenste richting waar bewogen moet naar worden. 0 voor
     * omhoog, 1 voor omlaag, 2 voor links en 3 voor rechts.
     */
    public void beweeg(int richting)
    {
        huidigSpel.getHuidigSpelbord().verplaatsMannetje(richting);
    }

    /**
     * UC4: Voltooi spelbord. Geeft het aantal gemaakte zetten op het spelbord
     * terug.
     *
     * @return Het aantal gemaakte zetten als int.
     */
    public int geefAantalZetten()
    {
        return huidigSpel.getHuidigSpelbord().geefAantalZetten();
    }

    /**
     * Alle UC's. Selecteren van de gewenste taal. Geeft de taalkeuze van de
     * gebruiker door aan het Taal-object.
     *
     * @param locale Code van de taalkeuze als int.
     * @param forGUI true voor GUI-resources, false voor CUI-resources.
     */
    public void setTaalKeuze(int locale, boolean forGUI)
    {
        if (forGUI)
        {
            resourceBundle.setTaalKeuzeGUI(locale);
        }
        else
        {
            resourceBundle.setTaalKeuzeCUI(locale);
        }
    }

    /**
     * Alle UC's. Ophalen van een String uit ingestelde resource bundle.
     *
     * @param key de key die in de ResourceBundles overeenkomt met de op te
     * halen tekst.
     * @return De opgehaalde tekst adhv key, als String.
     */
    public String getString(String key)
    {
        return resourceBundle.getStringUitBundle(key);
    }

    /**
     * UC5. Maakt een nieuw spelobject met ingegeven parameter en roept maakLeegSpelbord() aan
     * @param spelNaam naam van het nieuwe spel.
     */
    public void configureerNieuwSpel(String spelNaam)
    {
        huidigSpel = new Spel(spelNaam);
        this.maakLeegSpelbord();
    }
    
    public void maakLeegSpelbord()
    {
        huidigSpel.maakLeegSpelbord();
    }
    
    /**
     * UC5. Plaatst een gekozen element op het spelbord op meegegeven index.
     * @param elementType verwijst naar welk element er moet geplaatst worden.
     * @param xPositie x-coördinaat van het toe te voegen element.
     * @param yPositie y-coördinaat van het toe te voegen element.
     */
    public void plaatsElement(String elementType, int xPositie, int yPositie)
    {
        huidigSpel.getHuidigSpelbord().plaatsElement(elementType, xPositie, yPositie);
    }

    /**
     * UC5. Registreert het geconfigureerde spel in de databank.
     * @throws SpelNaamBestaatException
     */
    public void registreerCustomSpel() throws SpelNaamBestaatException
    {
        spelRepository.voegSpelToe(huidigSpel);
    }
    
    /**
     * UC5. Registreert het gemaakte spelbord in het huidige te configureren spel.
     */
    public void registreerCustomSpelbord()
    {
        huidigSpel.registreerSpelbord();
    }
}