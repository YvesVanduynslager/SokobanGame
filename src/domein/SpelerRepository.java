package domein;

import exceptions.GebruikerBestaatException;
import persistentie.SpelerMapper;


/**
 * Staat in voor het beheren, toevoegen en verwijderen van spelers. (CRUD)
 *
 * @author Yves
 */
public class SpelerRepository
{
    private final SpelerMapper spelerMapper;

    public SpelerRepository()
    {
        spelerMapper = new SpelerMapper();
    }

    /**
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
     *
     * @param speler Het spelerobject die toegevoegd moet worden aan de
     * database.
     * @throws exceptions.GebruikerBestaatException throws naar DomeinController.
     */
    public void voegToe(Speler speler) throws GebruikerBestaatException //throw naar DomeinController
    {
//        try
//        {
            spelerMapper.voegToe(speler);
        //}
//        catch (GebruikerBestaatException gbe)
//        {
//            throw gbe;
//        }
    }
}