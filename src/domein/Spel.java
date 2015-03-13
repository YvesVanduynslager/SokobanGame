package domein;

import java.util.ArrayList;
import java.util.List;

public class Spel
{
    private List<Spelbord> borden = new ArrayList<>();
    private Spelbord[] spelborden;
    private String spelNaam;
    private Spelbord huidigSpelbord;

    public Spel()
    {
        
    }
    
    public Spel(String spelNaam, List<Spelbord> borden)
    {
        this.spelNaam = spelNaam;
        this.borden = borden;
    }
    public Spel(String spelNaam, Spelbord[] spelborden)
    {
        this.spelNaam = spelNaam;
        this.spelborden = spelborden;
    }

    public void start()
    {
        for (Spelbord spelbord : borden)
        {
            if (!spelbord.isVoltooid())
            {
                this.setHuidigSpelbord(spelbord);
                break;
            }
        }
    }

    public String getSpelNaam()
    {
        return this.spelNaam;
    }

    public int geefAantalVoltooideBorden()
    {
        int aantal = 0;
        for (Spelbord spelbord : borden)
        {
            if (spelbord.isVoltooid())
            {
                ++aantal;
            }
        }
        return aantal;
    }

    public int geefAantalSpelborden()
    {
        return borden.size();
        //return spelborden.length;
    }

    public void setSpelborden(ArrayList<Spelbord> borden /*Spelbord[] spelborden*/)
    {
        this.borden = borden;
        //this.spelborden = spelborden;
    }

    public List<Spelbord>/*Spelbord[]*/ getSpelborden()
    {
        return this.borden;
        //return this.spelborden;
    }

    public Spelbord getHuidigSpelbord()
    {
        return this.huidigSpelbord;
    }

    private void setHuidigSpelbord(Spelbord spelbord)
    {
        this.huidigSpelbord = spelbord;
    }
}
