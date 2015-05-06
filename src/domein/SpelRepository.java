package domein;

/* IMPORTS */
import exceptions.SpelNaamBestaatException;
import java.util.List;
import persistentie.SpelMapper;

/**
 * Staat in voor het ophalen en teruggeven van Spelgegevens uit de SpelMapper.
 *
 * @author Yves
 */
public final class SpelRepository
{
    /* DECLARATIES CONSTANTEN */
    private final SpelMapper spelMapper;

    /**
     * Default-constructor initialiseert een SpelMapper-object.
     */
    public SpelRepository()
    {
        spelMapper = new SpelMapper();
    }

    /**
     * Haalt via SpelMapper een lijst van spelnamen op.
     * 
     * @return List van spelnamen.
     */
    public List<String> geefSpelNamen()
    {
        return spelMapper.geefSpelNamen();
    }

    /**
     * Haalt via SpelMapper een gewenst spel op.
     * 
     * @param spelnaam Naam van spel dat opgehaald moet worden als String.
     * @return Het opgehaalde spel als Spel-object.
     */
    public Spel geefSpel(String spelnaam)
    {
        return spelMapper.geefSpel(spelnaam);
    }
    
    /**
     * Geeft een Spel-object door aan SpelMapper.
     * 
     * @param customSpel Het door te geven Spel-object.
     * @throws SpelNaamBestaatException 
     */
    public void voegSpelToe(Spel customSpel) throws SpelNaamBestaatException
    {
        spelMapper.voegToe(customSpel);
    }
}