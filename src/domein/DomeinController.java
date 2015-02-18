package domein;
/**
 * Staat in voor communicatie tussen GUI en businuess-logica.
 * @author Yves
 */
public class DomeinController
{
    SpelerRepository spelerRepository;
    private String[] spelerString;
    Speler speler;

    public DomeinController()
    {
        spelerRepository = new SpelerRepository();
    }
    /**
     * Aanmelden van een gebruiker
     * @param gebruikersnaam Instellen ven gebruikersnaam
     * @param wachtwoord Instellen van wachtwoord
     */
    public void meldAan(String gebruikersnaam, String wachtwoord)
    {
        Speler sp = spelerRepository.geefSpeler(gebruikersnaam, wachtwoord);
        setSpeler(sp);
    }

    public String[] geefSpeler()
    {
        spelerString = new String[2];
        spelerString[0] = speler.getGebruikersnaam();
        spelerString[1] = speler.getAdminrechten();
        return spelerString;
    }

    /**
     * Bijhouden welke speler is aangemeld.
     * @param speler Speler-object die tussentijds bewaard moet worden.
     */
    private void setSpeler(Speler speler)
    {
        this.speler = speler;
    }
}