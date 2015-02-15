package domein;

/**
 * 
 * @author Yves
 * De gebruiker die een spel speelt wordt bewaard in een Speler-object
 */
public class Speler
{
    private String id;
    private String gebruikersnaam;
    private String wachtwoord;
    private boolean adminrechten = false;

    /**
     * Instellen van het volgnummer van de speler
     * @param id volgnummer van de speler
     */
    public void setID(String id)
    {
        this.id = id;
    }

    /**
     * Instellen van de gebruikersnaam van de speler
     * @param gebruikersnaam gebruikersnaam van de speler
     */
    public void setGebruikersnaam(String gebruikersnaam)
    {
        this.gebruikersnaam = gebruikersnaam;
    }

    /**
     * Instellen van het wachtwoord van de Speler
     * @param wachtwoord wachtwoord van de speler
     */
    public void setWachtwoord(String wachtwoord)
    {
        this.wachtwoord = wachtwoord;
    }

    /**
     * Instellen van adminrechten van de speler
     * @param adminrechten adminrechten van de gebruiker
     */
    public void setAdminrechten(boolean adminrechten)
    {
        this.adminrechten = adminrechten;
    }

    public String getGebruikersnaam()
    {
        return this.gebruikersnaam;
    }

    public String getWachtwoord()
    {
        return this.wachtwoord;
    }

    public boolean heeftAdminrechten()
    {
        return this.adminrechten;
    }

    public String getID()
    {
        return id;
    }
}