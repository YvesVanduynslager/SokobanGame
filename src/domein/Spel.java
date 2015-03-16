package domein;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Yves
 */
public class Spel
{
    private List<Spelbord> spelborden = new ArrayList<>();
    private String spelNaam;
    private Spelbord huidigSpelbord;

    /**
     * 
     */
    public Spel()
    {
    }

    /**
     *
     * @param spelNaam
     * @param spelborden
     */
    public Spel(String spelNaam, List<Spelbord> spelborden)
    {
        this.spelNaam = spelNaam;
        this.spelborden = spelborden;
    }

    /**
     * 
     */
    public void start()
    {
        for (Spelbord spelbord : spelborden)
        {
            if (!spelbord.isVoltooid())
            {
                this.setHuidigSpelbord(spelbord);
            }
        }
    }

    /**
     * 
     * @return 
     */
    public String getSpelNaam()
    {
        return this.spelNaam;
    }

    /**
     * 
     * @return 
     */
    public int geefAantalVoltooideBorden()
    {
        int aantal = 0;
        for (Spelbord spelbord : spelborden)
        {
            if (spelbord.isVoltooid())
            {
                ++aantal;
            }
        }
        return aantal;
    }

    /**
     * 
     * @return 
     */
    public int geefAantalSpelborden()
    {
        return spelborden.size();
    }

    /**
     * 
     * @param spelborden 
     */
    public void setSpelborden(ArrayList<Spelbord> spelborden)
    {
        this.spelborden = spelborden;
    }

    /**
     * 
     * @return 
     */
    public List<Spelbord> getSpelborden()
    {
        return this.spelborden;
        //return this.spelborden;
    }

    /**
     * 
     * @return 
     */
    public Spelbord getHuidigSpelbord()
    {
        return this.huidigSpelbord;
    }

    /**
     * 
     * @param spelbord 
     */
    private void setHuidigSpelbord(Spelbord spelbord)
    {
        this.huidigSpelbord = spelbord;
    }
    
    /**
     * 
     * @return 
     */
    public String spelbordToString()
    {
        return huidigSpelbord.toString();
    }
}
