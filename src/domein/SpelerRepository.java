package domein;

/* IMPORTS */
import exceptions.GebruikerBestaatException;
import persistentie.SpelerMapper;

/**
 * Staat in voor het beheren, toevoegen en verwijderen van spelers. (CRUD)
 *
 * @author Yves
 */
public final class SpelerRepository
{
    /* DECLARATIES CONSTANTEN */
    private final SpelerMapper spelerMapper;

    /**
     * Default-constructor initialiseert een SpelerMapper-object.
     */
    public SpelerRepository()
    {
        spelerMapper = new SpelerMapper();
    }

    /**
     * Vraagt aan SpelerMapper om het Speler-object terug te geven volgens
     * ingegeven parameters.
     *
     * @param gebruikersnaam gebruikersnaam van de speler
     * @param wachtwoord wachtwoord van de speler
     * @return gevuld Speler-object met waarden uit gewenste record uit
     * databank.
     */
    public Speler geefSpeler(String gebruikersnaam, String wachtwoord)
    {
        return spelerMapper.zoek(gebruikersnaam, wachtwoord);
    }

    /**
     * Vraagt aan SpelerMapper om het Speler-object in parameterlijst toe te voegen aan databank.
     *
     * @param speler Het spelerobject dat toegevoegd moet worden aan de
     * database.
     * @throws exceptions.GebruikerBestaatException throws naar
     * DomeinController.
     */
    public void voegToe(Speler speler) throws GebruikerBestaatException
    {
            spelerMapper.voegToe(speler);
    }
}