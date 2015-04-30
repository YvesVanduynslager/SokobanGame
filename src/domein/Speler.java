package domein;

/**
 * Staat in voor het beheren van spelers. Instellen en controleren van
 * spelergegevens.
 *
 * @author Yves
 */
public final class Speler
{
    /* DECLARATIES VARIABELEN */
    private int id;
    private String gebruikersnaam, wachtwoord, adminrechten, voornaam,
            achternaam;

    /**
     * Default-constructor.
     */
    public Speler()
    {
    }

    /**
     * Initialiseert gebruikersnaam, wachtwoord, achternaam, voornaam en
     * adminrechten.
     *
     * @param gebruikersnaam gebruikersnaam van de speler als String.
     * @param wachtwoord wachtwoord van de speler als String.
     * @param achternaam achternaam van de speler als String.
     * @param voornaam voornaam van de speler als String.
     * @param adminrechten "ja" als speler adminrechten heeft, "nee" als speler
     * geen adminrechten heeft.
     */
    public Speler(String gebruikersnaam, String wachtwoord, String achternaam,
            String voornaam, String adminrechten)
    {
        this.setEnControleerGebruikersnaam(gebruikersnaam);
        this.setEnControleerWachtwoord(wachtwoord);
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.adminrechten = adminrechten;
    }

    /**
     * Instellen van de gebruikersnaam van de speler.
     *
     * @param gebruikersnaam Gebruikersnaam van de speler als String.
     */
    public void setGebruikersnaam(String gebruikersnaam)
    {
        this.gebruikersnaam = gebruikersnaam;
    }

    /**
     * Instellen van het wachtwoord van de speler.
     *
     * @param wachtwoord Wachtwoord van de speler als String.
     */
    public void setWachtwoord(String wachtwoord)
    {

        this.wachtwoord = wachtwoord;
    }

    /**
     * Instellen van de voornaam van de speler.
     *
     * @param voornaam Voornaam van de speler als String.
     */
    public void setVoornaam(String voornaam)
    {
        this.voornaam = voornaam;
    }

    /**
     * Instellen van de achternaam van de speler.
     *
     * @param achternaam Achternaam van de speler als String.
     */
    public void setAchternaam(String achternaam)
    {
        this.achternaam = achternaam;
    }

    /**
     * Instellen van de adminrechten van de speler.
     *
     * @param adminrechten Adminrechten van de speler.
     */
    public void setAdminrechten(String adminrechten)
    {
        if (adminrechten.equals("ja") || adminrechten.equals("nee"))
        {
            this.adminrechten = adminrechten;
        }
        else
        {
            this.adminrechten = "nee";
        }
    }

    /**
     * Teruggeven van de gebruikersnaam van de speler.
     *
     * @return Gebruikersnaam als String.
     */
    public String getGebruikersnaam()
    {
        return this.gebruikersnaam;
    }

    /**
     * Teruggeven van het wachtwoord van de Speler.
     *
     * @return Wachtwoord als String.
     */
    public String getWachtwoord()
    {
        return this.wachtwoord;
    }

    /**
     * Teruggeven van de adminrechten van de Speler.
     *
     * @return Adminrechten als String.
     */
    public String getAdminrechten()
    {
        return this.adminrechten;
    }

    /**
     * Teruggeven van de voornaam van de Speler.
     *
     * @return Voornaam als String.
     */
    public String getVoornaam()
    {
        return this.voornaam;
    }

    /**
     * Teruggeven van de achternaam van de Speler.
     *
     * @return Achternaam als String.
     */
    public String getAchternaam()
    {
        return this.achternaam;
    }

    /**
     * Controleert of gebruikersnaam geldig is. Zoja, wordt deze ingesteld.
     *
     * @param gebruikersnaam Te controleren en in te stellen gebruikersnaam als
     * String.
     */
    private void setEnControleerGebruikersnaam(String gebruikersnaam)
    {
        if (!(gebruikersnaam.length() >= 8))
        {
            throw new IllegalArgumentException();
        }
        this.setGebruikersnaam(gebruikersnaam);
    }

    /**
     * Controleert of wachtwoord geldig is mbv geldigWachtwoord(). Zoja, wordt
     * deze ingesteld.
     *
     * @param wachtwoord Te controleren en in te stellen wachtwoord als String.
     */
    private void setEnControleerWachtwoord(String wachtwoord)
    {
        if (!geldigWachtwoord(wachtwoord))
        {
            throw new IllegalArgumentException();
        }
        this.setWachtwoord(wachtwoord);
    }

    /**
	 * 
	 * @param wachtwoord Het in te stellen wachtwoord als String.
	 */
    private boolean geldigWachtwoord(String wachtwoord)
    {
        int numOfUpperLetters = 0; // initialiseren aantal lowerCase letters
        int numOfLowerLetters = 0; // initialiseren aantal upperCase letters
        int numOfDigits = 0; // initialiseren aantal cijfers

        byte[] bytes = wachtwoord.getBytes();
        for (byte tempByte : bytes)
        {
            char tempChar = (char) tempByte;
            if (Character.isDigit(tempChar))
            {
                numOfDigits++;
            }

            if (Character.isUpperCase(tempChar))
            {
                numOfUpperLetters++;
            }

            if (Character.isLowerCase(tempChar))
            {
                numOfLowerLetters++;
            }
        }
        // indien aan alles werd voldaan: resultaat >= 1; anders 0
        if (numOfDigits * numOfUpperLetters * numOfLowerLetters *
                (wachtwoord.length() / 8) >= 1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
