package domein;

import exceptions.GebruikerBestaatException;
import java.util.ResourceBundle;

/**
 * Staat in voor communicatie tussen GUI en businuess-logica.
 *
 * @author Yves
 */
public class DomeinController
{
    private final SpelerRepository spelerRepository;
    private ResourceBundle resourceBundle;
    private String[] spelerString;
    private Speler huidigeSpeler;

    /**
     * Default-constructor maakt SpelerRepository-object aan.
     */
    public DomeinController()
    {
        spelerRepository = new SpelerRepository();
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
     * @throws exceptions.GebruikerBestaatException throws naar en handelt af in Registreer.
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
            throw new IllegalArgumentException(iae);
        }
        catch (GebruikerBestaatException gbe)
        {
            throw new GebruikerBestaatException(gbe);
        }   
    }
    
    /*
    * Setter voor het ResourseBundle-object
    * 
    * @param locale code van de taalkeuze
    */
    public void setResourseBundle(int locale)
    {
        switch (locale) //resourcebundle selecteren op basis van genomen keuze
        {
            case 1: this.resourceBundle = ResourceBundle.getBundle("resources.Resources_nl_BE");
            break;
            case 2: this.resourceBundle = ResourceBundle.getBundle("resources.Resources_en_UK");
            break;
            case 3: this.resourceBundle = ResourceBundle.getBundle("resources.Resources_fr_BE");
            break;
            default: this.resourceBundle = ResourceBundle.getBundle("resources.Resources_nl_BE");
            break; 
        }
    }
    
    /*
    * Getter voor het ResourseBundle-object
    */
    public ResourceBundle getResourseBundle(){
        return this.resourceBundle;
    }
}
