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
     * @return 
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
     *
     * @param gebruikersnaam
     * @param wachtwoord
     * @param voornaam
     * @param naam
     */
    public void registreer(String gebruikersnaam, String wachtwoord, String voornaam, String naam)
    {
        Speler sp = new Speler(gebruikersnaam, wachtwoord, voornaam, naam, "nee");
        spelerRepository.voegToe(sp);
        setHuidigeSpeler(sp);
    }
    
    /**
     * 
     * @param gebruikersnaam
     * @return 
     */
    public boolean bestaatSpeler(String gebruikersnaam)
    {
        return spelerRepository.bestaatSpeler(gebruikersnaam);
    }
}