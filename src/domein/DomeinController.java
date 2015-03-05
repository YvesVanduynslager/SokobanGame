package domein;

import exceptions.GebruikerBestaatException;

/**
 * Staat in voor communicatie tussen GUI en businuess-logica.
 *
 * @author Yves
 */
public class DomeinController
{
    private final SpelerRepository spelerRepository;
    private final Taal resourceBundle;
    private String[] spelerString;
    private Speler huidigeSpeler;

    /**
     * Default-constructor maakt SpelerRepository-object aan.
     */
    public DomeinController()
    {
        spelerRepository = new SpelerRepository();
        resourceBundle = new Taal();
    }

    /**
     * Aanmelden van een gebruiker
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
     *
     * @return geeft de gebruikersnaam en adminrechten weer van de speler via
     * String[]
     */
    public String[] geefSpeler()
    {
        spelerString = new String[2];
        spelerString[0] = huidigeSpeler.getGebruikersnaam();
        spelerString[1] = huidigeSpeler.getAdminrechten();
        return spelerString;
    }

    /**
     * Bijhouden welke speler is aangemeld.
     *
     * @param speler Speler-object die tussentijds bewaard moet worden.
     */
    private void setHuidigeSpeler(Speler speler)
    {
        this.huidigeSpeler = speler;
    }

    /**
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
    public void registreer(String gebruikersnaam, String wachtwoord, String voornaam, String achternaam) throws IllegalArgumentException, GebruikerBestaatException//Exception
    {
        try
        {
            Speler sp = new Speler(gebruikersnaam, wachtwoord, voornaam, achternaam, "nee");
            spelerRepository.voegToe(sp);
            setHuidigeSpeler(sp);
        }
        catch (IllegalArgumentException iae)
        {
            throw new IllegalArgumentException(iae + resourceBundle.getStringUitBundle("registreer.ongeldig"));
        }
        catch (GebruikerBestaatException gbe)
        {
            throw new GebruikerBestaatException(gbe + resourceBundle.getStringUitBundle("registreer.gebruikerbestaat"));
        }
    }

    /*
     * Geeft de taalkeuze van de gebruiker door aan het Taal-object.
     * 
     * @param locale code van de taalkeuze
     */
    public void setTaalKeuze(int locale)
    {
        resourceBundle.setTaalKeuze(locale);
    }

    /*
     * Haalt de correcte String op uit het Taal-object
     *
     * @param key de key die in de ResourceBundles overeenkomt met de op te halen tekst
     */
    public String getString(String key)
    {
        return resourceBundle.getStringUitBundle(key);
    }
}
