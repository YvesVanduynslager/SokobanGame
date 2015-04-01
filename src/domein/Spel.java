package domein;

/* IMPORTS */
import java.util.ArrayList;
import java.util.List;

/**
 * Staat in voor het starten en beheren van een Spel.
 *
 * @author Yves
 */
public class Spel
{
    /* DECLARATIES VARIABELEN */
    private List<Spelbord> spelborden;
    private String spelNaam;
    private Spelbord huidigSpelbord;
//    private int spelbordIndex = 0;
    private int aantalSpelbordenVoltooid = 0;

    /**
     * Default-constructor initialiseert een ArrayList van
     * Spelbord-objecten.
     */
    public Spel()
    {
        spelborden = new ArrayList<>();
    }

    /**
     * Constructor stelt een Spel in met een spelNaam en een List van Spelbord.
     *
     * @param spelNaam Naam van het spel.
     * @param spelborden List van Spelbord-objecten.
     */
    public Spel(String spelNaam, List<Spelbord> spelborden)
    {
        this.spelNaam = spelNaam;
        this.spelborden = spelborden;        
    }

    /**
     * Deze methode loopt door de aanwezige spelborden en stelt het huidige
     * spelbord in als dit nog niet is voltooid.
     */
    public void start()
    {
        for(Spelbord bord : spelborden)
        {
            if(!bord.isVoltooid())
            {
                this.setHuidigSpelbord(bord);   
                break;
            }
        }
//        if (spelbordIndex < spelborden.size())
//        {
//            Spelbord bord = spelborden.get(spelbordIndex);
//            origineelSpelbord = bord;
//            this.setHuidigSpelbord(bord);
//        }
//        spelbordIndex++;
    }

    public List<Spelbord> getSpelborden() {
        return spelborden;
    }
    
//    public void resetHuidigSpelbord(Spel spel)
//    {
////        spelborden.set(spelbordIndex, spel.getSpelborden().get(spelbordIndex));
//    }

    /**
     * Geeft de naam van het spel.
     *
     * @return Geeft de naam van het spel terug als een String.
     */
    public String getSpelNaam()
    {
        return this.spelNaam;
    }

    /**
     * Geeft het aantal voltooide spelborden.
     *
     * @return Het aantal voltooide spelborden als int.
     */
    public int geefAantalVoltooideBorden()
    {
        return aantalSpelbordenVoltooid;
    }

    /**
     * Verhoogd het aantal voltooide spelborden.
     */
    public void verhoogAantalVoltooideBorden()
    {
        if (huidigSpelbord != null && huidigSpelbord.isVoltooid()
                && aantalSpelbordenVoltooid < this.geefAantalSpelborden())
        {
            aantalSpelbordenVoltooid++;
        }
    }

    /**
     * Geeft het aantal spelborden.
     *
     * @return Het aantal spelborden als int.
     */
    public int geefAantalSpelborden()
    {
        return spelborden.size();
    }

    /**
     * Stelt de spelborden in die zullen behoren tot het spel.
     *
     * @param spelborden ArrayList van Spelbord-objecten.
     */
    public void setSpelborden(ArrayList<Spelbord> spelborden)
    {
        this.spelborden = spelborden;
    }

    /**
     * Geeft het huidig ingestelde spelbord terug
     *
     * @return Huidig ingestelde Spelbord-object
     */
    public Spelbord getHuidigSpelbord()
    {
        return this.huidigSpelbord;
    }

    /**
     * Instellen van het huidige spelbord
     *
     * @param spelbord Spelbord-object dat in te stellen valt als huidig
     * spelbord.
     */
    private void setHuidigSpelbord(Spelbord spelbord)
    {
        this.huidigSpelbord = spelbord;
    }

    /**
     * Geeft het huidigSpelbord object terug als String.
     *
     * @return huidigSpelbord als String.
     */
    public String spelbordToString()
    {
        return huidigSpelbord.toString();
    }
}