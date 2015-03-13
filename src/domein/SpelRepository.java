package domein;

import java.util.List;
import persistentie.SpelMapper;

public class SpelRepository
{

    private final SpelMapper spelMapper;

    public SpelRepository()
    {
        spelMapper = new SpelMapper();
    }

    public List<String> geefSpelNamen()
    {
       return spelMapper.geefSpelNamen();// TODO - implement SpelRepository.geefSpelNamen
        //throw new UnsupportedOperationException();
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

//        public Spel[] geefSpellen()
//        {
//            return spelMapper.geefSpellen();
//        }
}
