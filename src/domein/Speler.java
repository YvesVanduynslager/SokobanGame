package domein;

/**
 * @author Yves De gebruiker die een spel speelt wordt bewaard in een
 * Speler-object
 */
public class Speler
{
    private String id, gebruikersnaam, wachtwoord, adminrechten, voornaam, naam;

    public Speler(){}
    
    public Speler(String gebruikersnaam, String wachtwoord, String naam, String voornaam, String adminrechten)
    {
        this.gebruikersnaam = gebruikersnaam;
        this.wachtwoord = wachtwoord;
        this.voornaam = voornaam;
        this.naam = naam;
        this.adminrechten = adminrechten;
    }

    /**
     * Instellen van id van de speler
     *
     * @param id id van de speler
     */
    public void setId(String id)
    {
        this.id = id;
    }

    /**
     * Instellen van de gebruikersnaam van de speler
     *
     * @param gebruikersnaam gebruikersnaam van de speler
     */
    public void setGebruikersnaam(String gebruikersnaam)
    {
        this.gebruikersnaam = gebruikersnaam;
    }

    /**
     * Instellen van het wachtwoord van de speler
     *
     * @param wachtwoord wachtwoord van de speler
     */
    public void setWachtwoord(String wachtwoord)
    {
        this.wachtwoord = wachtwoord;
    }

    /**
     * Instellen van de adminrechten van de speler
     *
     * @param adminrechten adminrechten van de speler
     */
    public void setAdminrechten(String adminrechten)
    {
        this.adminrechten = adminrechten;
    }

    /**
     * Instellen van de voornaam van de speler
     *
     * @param voornaam voornaam van de speler
     */
    public void setVoornaam(String voornaam)
    {
        this.voornaam = voornaam;
    }

    /**
     * Instellen van de achternaam van de speler
     *
     * @param achternaam achternaam van de speler
     */
    public void setAchternaam(String achternaam)
    {
        this.naam = achternaam;
    }


    public String getId()
    {
        return this.id;
    }

    public String getGebruikersnaam()
    {
        return this.gebruikersnaam;
    }

    public String getWachtwoord()
    {
        return this.wachtwoord;
    }

    public String getAdminrechten()
    {
        return this.adminrechten;
    }

    public String getVoornaam()
    {
        return this.voornaam;
    }

    public String getAchternaam()
    {
        return this.naam;
    }

    @Override
    public String toString()
    {
        return String.format("", "");
    }

	/**
	 * Instellen van id van de speler
	 * @param id id van de speler
	 */
	public void setID(String id) {
		// TODO - implement Speler.setID
		throw new UnsupportedOperationException();
	}

	public String getID() {
		// TODO - implement Speler.getID
		throw new UnsupportedOperationException();
	}
}
