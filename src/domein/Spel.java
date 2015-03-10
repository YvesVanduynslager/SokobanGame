package domein;

public class Spel
{
    private Spelbord[] spelborden;
    private final String spelNaam;
    private Spelbord huidigSpelbord;

    public Spel(String spelNaam, Spelbord[] spelborden)
    {
        this.spelNaam = spelNaam;
        this.spelborden = spelborden;
    }

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
        return spelborden.length;
    }

    public void setSpelborden(Spelbord[] spelborden)
    {
        this.spelborden = spelborden;
    }

    public Spelbord[] getSpelborden()
    {
        return this.spelborden;
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
