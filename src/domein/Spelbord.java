package domein;

/**
 *
 * @author Yves
 */
public class Spelbord
{
    private final Element[][] velden;
    private final Mannetje mannetje;
    private boolean isVoltooid = false;

    /**
     *
     * @param velden
     * @param mannetje
     */
    public Spelbord(Element[][] velden, Mannetje mannetje)
    {
        this.velden = velden;
        this.mannetje = mannetje;
    }

    /**
     *
     * @param richting Gewenste richting waar bewogen moet naar worden. 0 voor omhoog, 1 voor omlaag, 2 voor links en 3 voor rechts
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

        if (velden[x1[richting]][y1[richting]] instanceof Veld)
        {
            mannetje.setxPositie(x1[richting]);
            mannetje.setyPositie(y1[richting]);
            if (velden[x1[richting]][y1[richting]].isDoel())
            {
                mannetje.setIsDoel(true);
            }
            else
            {
                mannetje.setIsDoel(false);
            }
            velden[x1[richting]][y1[richting]] = mannetje; //volgende veld instellen

            if (velden[x0][y0].isDoel()) //huidige veld controleren op doel
            {
                velden[x0][y0] = new Veld(x0, y0, true);
            }
            else
            {
                velden[x0][y0] = new Veld(x0, y0, false);
            }
        }
        else
        {
            if (velden[x1[richting]][y1[richting]] instanceof Kist)
            {
                if (velden[x2[richting]][y2[richting]] instanceof Veld)
                {
                    mannetje.setxPositie(x1[richting]);
                    mannetje.setyPositie(y1[richting]);
                    if (velden[x1[richting]][y1[richting]].isDoel())
                    {
                        mannetje.setIsDoel(true);
                        if (velden[x2[richting]][y2[richting]].isDoel())
                        {
                            velden[x2[richting]][y2[richting]] = new Kist(x2[richting], y2[richting], true);
                            //hier incr
                        }
                        else
                        {
                            velden[x2[richting]][y2[richting]] = new Kist(x2[richting], y2[richting], false);
                        }
                    }
                    else
                    {
                        mannetje.setIsDoel(false);

                    }
                    velden[x1[richting]][y1[richting]] = mannetje; //volgende veld instellen

                    if (velden[x0][y0].isDoel()) //huidige veld controleren op doel en instellen
                    {
                        velden[x0][y0] = new Veld(x0, y0, true);
                    }
                    else
                    {
                        velden[x0][y0] = new Veld(x0, y0, false);
                    }
                    if (velden[x2[richting]][y2[richting]].isDoel())
                    {
                        velden[x2[richting]][y2[richting]] = new Kist(x2[richting], y2[richting], true); //true voor doel
                    }
                    else
                    {
                        velden[x2[richting]][y2[richting]] = new Kist(x2[richting], y2[richting], false); //false voor geen doel
                    }
                }
            }
        }
    }

    private int getAantalKisten()
    {
        int aantal = 0;
        for (Element[] rij : velden)
        {
            for (Element element : rij)
            {
                if(element instanceof Kist)
                {
                    aantal++;
                }
            }
        }
        return aantal;
    }

    /**
     *
     * @return
     */
    public boolean isVoltooid()
    {
        int aantal = 0;
        for (Element[] rij : velden)
        {
            for (Element element : rij)
            {
                if (element instanceof Kist && element.isDoel())
                {
                    aantal++;
                }
            }
        }
        if (this.getAantalKisten() == aantal)
        {
            isVoltooid = true;
        }
//        else
//        {
//            return false;
//        }
        return isVoltooid;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString()
    {
        String output = "";

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
     *
     * @return
     */
    public Element[][] geefVelden()
    {
        return this.velden;
    }
//
//    void setVoltooid(boolean isVoltooid)
//    {
//        this.isVoltooid = isVoltooid;
//    }
}
