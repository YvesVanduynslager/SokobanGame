package domein;

/**
 * Beheert de Spelbord-objecten. Verplaatsen van mannetje, teruggeven van
 * spelbord, bijhouden aantal zetten.
 *
 * @author Yves
 */
public class Spelbord
{
    /* DECLARATIES VARIABELEN */
    private int aantalZetten;
    private boolean voltooid;
    /* DECLARATIES CONSTANTEN */
    private final Element[][] velden;
    private final Mannetje mannetje;
    private final String naam;

    /**
     * Initialiseert de naam, velden, het mannetje en het aantal zetten.
     *
     * @param naam Naam van het spelbord.
     * @param velden De 2D-Element Array van het spelbord.
     * @param mannetje Het mannetje van dit spelbord.
     */
    public Spelbord(String naam, Element[][] velden, Mannetje mannetje)
    {
        this.naam = naam;
        this.velden = velden;
        this.mannetje = mannetje;
        this.aantalZetten = 0;
    }

    /**
     * Geeft de naam van het spel terug.
     *
     * @return Naam van het spel als String.
     */
    public String getNaam()
    {
        return naam;
    }

    public Element geefAangrenzendElement(Element element, int richting)
    {
        int x0 = mannetje.getxPositie();
        int y0 = mannetje.getyPositie();
        int[] x1 =
        {
            x0 - 1, x0 + 1, x0, x0
        }; //x1 bevat de x-waarden voor de veplaatsing van het mannetje in de vorm {omhoog, omlaag, links, recht}
        int[] y1 =
        {
            y0, y0, y0 - 1, y0 + 1
        }; //y1 bevat de y-waarden voor de veplaatsing van het mannetje in de vorm {omhoog, omlaag, links, recht}

        return velden[x1[richting]][y1[richting]];
    }

    public Element geefNaAangrenzendElement(Element element, int richting)
    {
        int x0 = mannetje.getxPositie();
        int y0 = mannetje.getyPositie();
        int[] x2 =
        {
            x0 - 2, x0 + 2, x0, x0
        }; //x2 bevat de x-waarden voor de veplaatsing van de kist in de vorm {omhoog, omlaag, links, recht}
        int[] y2 =
        {
            y0, y0, y0 - 2, y0 + 2
        }; //y2 bevat de y-waarden voor de veplaatsing van de kist in de vorm {omhoog, omlaag, links, recht}

        return velden[x2[richting]][y2[richting]];

    }

    /**
     * Staat in voor het bewegen van het mannetje en kisten over het spelbord.
     *
     * @param richting Gewenste richting waar bewogen moet naar worden. 0 voor
     * omhoog, 1 voor omlaag, 2 voor links en 3 voor rechts.
     */
    public void verplaatsMannetje(int richting)
    {
        int x0 = mannetje.getxPositie();
        int y0 = mannetje.getyPositie();

        int[] x1 =
        {
            x0 - 1, x0 + 1, x0, x0
        }; //x1 bevat de x-waarden voor de veplaatsing van het mannetje in de vorm {omhoog, omlaag, links, recht}
        int[] y1 =
        {
            y0, y0, y0 - 1, y0 + 1
        }; //y1 bevat de y-waarden voor de veplaatsing van het mannetje in de vorm {omhoog, omlaag, links, recht}
        int[] x2 =
        {
            x0 - 2, x0 + 2, x0, x0
        }; //x2 bevat de x-waarden voor de veplaatsing van de kist in de vorm {omhoog, omlaag, links, recht}
        int[] y2 =
        {
            y0, y0, y0 - 2, y0 + 2
        }; //y2 bevat de y-waarden voor de veplaatsing van de kist in de vorm {omhoog, omlaag, links, recht}

        int x, y, xNa, yNa;

        Element aangrenzend = this.geefAangrenzendElement(mannetje, richting);
        Element naAangrenzend = this.geefNaAangrenzendElement(aangrenzend, richting);

        x = aangrenzend.getxPositie();
        y = aangrenzend.getyPositie();
        xNa = naAangrenzend.getxPositie();
        yNa = naAangrenzend.getyPositie();

        if (aangrenzend instanceof Veld) //als volgend element een Veld is
        {
            if (mannetje/*velden[x0][y0]*/.isDoel()) //als huidige veld doel is
            {
                velden[x0][y0] = new Veld(x0, y0, true);
            }
            else //als huidige veld geen doel is
            {
                velden[x0][y0] = new Veld(x0, y0, false);
            }
            if (aangrenzend.isDoel()) //en volgend element een doel is
            {
                mannetje.setIsDoel(true);
            }
            else //en volgend element geen doel is
            {
                mannetje.setIsDoel(false);
            }

            /* NIET VERPLAATSEN */
            mannetje.setxPositie(x);
            mannetje.setyPositie(y);
            velden[x][y] = mannetje;
        }

        if (aangrenzend instanceof Kist && naAangrenzend instanceof Veld)
        {
            if (mannetje.isDoel()) //als huidige veld doel is
            {
                velden[x0][y0] = new Veld(x0, y0, true);
            }
            else //als huidige veld geen doel is
            {
                velden[x0][y0] = new Veld(x0, y0, false);
            }
            if (aangrenzend.isDoel())
            {
                mannetje.setIsDoel(true);
            }
            else
            {
                mannetje.setIsDoel(false);
            }

            if (naAangrenzend.isDoel())
            {
                aangrenzend.setIsDoel(true);
            }
            else
            {
                aangrenzend.setIsDoel(false);
            }

            /* NIET VERPLAATSEN */
            mannetje.setxPositie(x);
            mannetje.setyPositie(y);
            aangrenzend.setxPositie(xNa);
            aangrenzend.setyPositie(yNa);

            velden[x][y] = mannetje;
            velden[xNa][yNa] = aangrenzend;
        }
        ++aantalZetten;
    }

    /**
     * Geeft het totaal aantal aanwezige kisten in het spelbord terug.
     *
     * @return Aantal kisten als int.
     */
    private int getAantalKisten()
    {
        int aantalKisten = 0;
        for (Element[] rij : velden)
        {
            for (Element element : rij)
            {
                if (element instanceof Kist)
                {
                    aantalKisten++;
                }
            }
        }
        return aantalKisten;
    }

    /**
     * Controleert of het spelbord is voltooid mbv getAantalKisten().
     *
     * @return True voor voltooid, false voor niet voltooid.
     */
    public boolean isVoltooid()
    {
        int aantalKistenOpDoel = 0;
        for (Element[] rij : velden)
        {
            for (Element element : rij)
            {
                if (element instanceof Kist && element.isDoel())
                {
                    aantalKistenOpDoel++;
                }
            }
        }
        if (getAantalKisten() == aantalKistenOpDoel)
        {
            this.voltooid = true;
        }
        else
        {
            this.voltooid = false;
        }
        return voltooid;
    }

    /**
     * Bouwt het spelbord op als String.
     *
     * @return spelbord als String.
     */
    @Override
    public String toString()
    {
        String output = naam + "\n";

        for (Element[] rij : this.velden)
        {
            output += "-----------------------------------------\n";
            for (Element cel : rij)
            {
                output += "| " + cel.toString() + " ";
            }
            output += "|\n";
        }
        output += "-----------------------------------------\n";
        return output;
    }

    /**
     * Geeft een array van alle elementen op het spelbord terug.
     *
     * @return array van elementen op spelbord.
     */
    public Element[][] geefVelden()
    {
        return this.velden;
    }

    /**
     * Geeft het aantal gemaakte zetten terug.
     *
     * @return Aantal gemaakte zetten.
     */
    public int geefAantalZetten()
    {
        return this.aantalZetten;
    }
}