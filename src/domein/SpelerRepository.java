package domein;

import persistentie.*;

/**
 * Staat in voor het beheren, toevoegen en verwijderen van spelers.
 * @author Yves
 */
public class SpelerRepository
{
    private SpelerMapper spelerMapper;

    /**
     *
     * @param gebruikersnaam gebruikersnaam van de speler
     * @param wachtwoord wachtwoord van de speler
     * @return gevuld Speler-object met waarden uit gewenste record uit databank.
     */
    public Speler geefSpeler(String gebruikersnaam, String wachtwoord)
    {
        spelerMapper = new SpelerMapper();
        return spelerMapper.zoek(gebruikersnaam, wachtwoord);
    }
}
