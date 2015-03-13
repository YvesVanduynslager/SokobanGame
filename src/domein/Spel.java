package domein;

import java.util.ArrayList;
import java.util.List;

public class Spel
{
    private List<Spelbord> spelborden = new ArrayList<>();
    private String spelNaam;
    private Spelbord huidigSpelbord;

    public Spel()
    {
    }

    public Spel(String spelNaam, List<Spelbord> borden)
    {
        this.spelNaam = spelNaam;
        this.spelborden = borden;
    }
//    public Spel(String spelNaam, Spelbord[] spelborden)
//    {
//        this.spelNaam = spelNaam;
//        this.spelborden = spelborden;
//    }

    public void start()
    {
        for (Spelbord spelbord : spelborden)
        {
            if (!spelbord.isVoltooid())
            {
                this.setHuidigSpelbord(spelbord);
                break;
            }
        }
        System.out.println(huidigSpelbord.toString());
    }

    public String getSpelNaam()
    {
        return this.spelNaam;
    }

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

    public int geefAantalSpelborden()
    {
        return spelborden.size();
        //return spelborden.length;
    }

    public void setSpelborden(ArrayList<Spelbord> borden /*Spelbord[] spelborden*/)
    {
        this.spelborden = borden;
        //this.spelborden = spelborden;
    }

    public List<Spelbord>/*Spelbord[]*/ getSpelborden()
    {
        return this.spelborden;
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
