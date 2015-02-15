package domein;
/**
 * Staat in voor communicatie tussen GUI en businuess-logica.
 * @author Yves
 */
public class DomeinController
{
    private SpelerRepository spelerRepository;
    private String[] spelerString;
    private boolean spelerAdminrechten;
    private Speler speler;

    /**
     * Aanmelden van een gebruiker
     * @param gebruikersnaam
     * @param wachtwoord
     */
    public void meldAan(String gebruikersnaam, String wachtwoord)
    {
        spelerRepository = new SpelerRepository();
        Speler sp = spelerRepository.geefSpeler(gebruikersnaam, wachtwoord);
        setSpeler(sp);
    }

    public String[] getSpeler()
    {
        spelerString = new String[3];
        spelerString[0] = speler.getID();
        spelerString[1] = speler.getGebruikersnaam();
        spelerString[2] = speler.getWachtwoord();
        return spelerString;
    }

    public boolean getSpelerAdminrechten()
    {
        return spelerAdminrechten;
    }

    /**
     * Bijhouden welke speler is aangemeld.
     * @param speler Speler-object die tussentijds bewaard moet worden.
     */
    private void setSpeler(Speler speler)
    {
        this.speler = speler;
        this.spelerAdminrechten = speler.heeftAdminrechten();
    }
}