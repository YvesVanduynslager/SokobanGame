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
     * @param gebruikersnaam Instellen ven gebruikersnaam
     * @param wachtwoord Instellen van wachtwoord
     */
    public void meldAan(String gebruikersnaam, String wachtwoord)
    {
        spelerRepository = new SpelerRepository();
        Speler sp = spelerRepository.geefSpeler(gebruikersnaam, wachtwoord);
        setSpeler(sp);
    }

    public String[] getSpeler()
    {
        spelerString = new String[4];
        spelerString[0] = speler.getGebruikersnaam();
        spelerString[1] = speler.getWachtwoord();
        spelerString[2] = speler.getVoornaam();
        spelerString[3] = speler.getAchternaam();
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