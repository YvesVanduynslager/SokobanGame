package domein;

/**
 * Staat in voor communicatie tussen GUI en businuess-logica.
 *
 * @author Yves
 */
public class DomeinController
{
    private final SpelerRepository spelerRepository;
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
     * @return geeft de gebruikersnaam en adminrechten weer van de speler via String[]
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
     * Maakt een Speler-object aan met ingegeven parameters, en geeft dit object door aan spelerRepository-object,
     * stelt de huidige speler in met het gemaakte Speler-object.
     *
     * @param gebruikersnaam gebruikersnaam van de speler
     * @param wachtwoord wachtwoord van de speler
     * @param voornaam voornaam van de speler
     * @param achternaam achternaam van de speler
     */
    public void registreer(String gebruikersnaam, String wachtwoord, String voornaam, String achternaam)
    {
        Speler sp = new Speler(gebruikersnaam, wachtwoord, voornaam, achternaam, "nee");
        spelerRepository.voegToe(sp);
        setHuidigeSpeler(sp);
    }
    
    /**
     * 
     * @param gebruikersnaam gebruikersnaam van de speler
     * @return 
     */
    public boolean bestaatSpeler(String gebruikersnaam)
    {
        return spelerRepository.bestaatSpeler(gebruikersnaam);
    }
}