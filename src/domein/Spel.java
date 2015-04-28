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
    private final List<Spelbord> spelborden;
    private final String spelNaam;
    private Spelbord huidigSpelbord;

    /**
     * Default-constructor initialiseert een ArrayList van Spelbord-objecten.
     *
     * @param spelNaam Naam van het spel.
     */
    public Spel(String spelNaam)
    {
        this.spelNaam = spelNaam;
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
        for (Spelbord bord : spelborden)
        {
            if (!bord.isVoltooid())
            {
                this.setHuidigSpelbord(bord);
                break;
            }
        }
    }

    public List<Spelbord> geefSpelborden()
    {
        return spelborden;
    }
    public int geefAantalVoltooideBorden()
    {
        int aantal = 0;
        for (Spelbord bord : spelborden)
        {
            if (bord.isVoltooid())
            {
                aantal++;
            }
        }
        return aantal;
    }

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
     * Geeft het aantal spelborden.
     *
     * @return Het aantal spelborden als int.
     */
    public int geefAantalSpelborden()
    {
        return spelborden.size();
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

    
    /*
    TOEGEVOEGD VOOR UC5!!!
    */
    
    /**
     * 
     * @return 
     */
    public Spelbord maakLeegSpelbord()
    {
        //nieuwe elementen-array maken.
        Element velden[][] = new Element[10][10];
        
        for(int rij = 0; rij < velden.length; rij++)
        {
            for(int kolom = 0; kolom < velden[rij].length; kolom++)
            {
                /*
                Randen opvullen met muren.
                */
                if (rij == 0 || rij == velden.length -1 || kolom == 0 || kolom == velden[rij].length)
                {
                    velden[rij][kolom] = new Muur(rij, kolom, true);
                }
                /*
                Rest opvullen met gewone velden
                */
                else
                {
                    velden[rij][kolom] = new Veld(rij, kolom, false);
                }
            }
        }
        return new Spelbord(velden);
    }

    /**
     * 
     * @return 
     */
    public String geefSpelNaam()
    {
        return this.spelNaam;
    }
    
    /**
     * 
     * @param spelbord 
     */
    public void addSpelbord(Spelbord spelbord)
    {
        spelborden.add(spelbord);
    }
}
