package domein;

import java.util.List;
import persistentie.SpelMapper;

/**
 * 
 * @author Yves
 */
public class SpelRepository
{
    private final SpelMapper spelMapper;

    /**
     * 
     */
    public SpelRepository()
    {
        spelMapper = new SpelMapper();
    }

    /**
     * 
     * @return 
     */
    public List<String> geefSpelNamen()
    {
       return spelMapper.geefSpelNamen();
    }

    /**
     *
     * @param spelnaam
     * @return
     */
    public Spel geefSpel(String spelnaam)
    {
        return spelMapper.geefSpel(spelnaam);
    }
}
