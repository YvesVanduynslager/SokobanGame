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
     * @param richting
     */
    public void verplaatsMannetje(int richting)
    {
        int x0 = mannetje.getxPositie();
        int y0 = mannetje.getyPositie();
        int[] x1 = {x0 - 1, x0 + 1, x0, x0};
        int[] y1 = {y0, y0, y0 - 1, y0 + 1};
        int[] x2 = {x0 - 2, x0 + 2, x0, x0};
        int[] y2 = {y0, y0, y0 - 2, y0 + 2};
        int[] check1 = {x0 - 1, x0 + 1, y0 - 1, y0 + 1};
        int[] check2 = {x0 - 2, x0 + 2, y0 - 2, y0 + 2};

        if (velden[x1[richting]][y1[richting]] instanceof Veld && check1[richting] >= 0) {
            if (velden[x1[richting]][y1[richting]].isDoel()) {
                mannetje.setxPositie(x1[richting]);
                mannetje.setyPositie(y1[richting]);
                mannetje.setIsDoel(true);
                velden[x1[richting]][y1[richting]] = mannetje; //volgende veld instellen
            } else {
                mannetje.setxPositie(x1[richting]);
                mannetje.setyPositie(y1[richting]);
                mannetje.setIsDoel(false);
                velden[x1[richting]][y1[richting]] = mannetje; //volgende veld instellen
            }

            if (velden[x0][y0].isDoel()) //huidige veld controleren op doel
            {
                velden[x0][y0] = new Veld(x0, y0, true);
            } else {
                velden[x0][y0] = new Veld(x0, y0, false);
            }
        } else {
            if (velden[x1[richting]][y1[richting]] instanceof Kist && check1[richting] > 0) {
                if (velden[x2[richting]][y2[richting]] instanceof Veld && check2[richting] > 0) {
                    if (velden[x1[richting]][y1[richting]].isDoel()) {
                        mannetje.setxPositie(x1[richting]);
                        mannetje.setyPositie(y1[richting]);
                        mannetje.setIsDoel(true);
                        velden[x1[richting]][y1[richting]] = mannetje; //volgende veld instellen

                        if (velden[x2[richting]][y2[richting]].isDoel()) {
                            velden[x2[richting]][y2[richting]] = new Kist(x2[richting], y2[richting], true);
                            //hier incr
                        } else {
                            velden[x2[richting]][y2[richting]] = new Kist(x2[richting], y2[richting], false);
                        }
                    } else {
                        mannetje.setxPositie(x1[richting]);
                        mannetje.setyPositie(y1[richting]);
                        mannetje.setIsDoel(false);
                        velden[x1[richting]][y1[richting]] = mannetje; //volgende veld instellen
                    }

                    if (velden[x0][y0].isDoel()) //huidige veld controleren op doel en instellen
                    {
                        velden[x0][y0] = new Veld(x0, y0, true);
                    } else {
                        velden[x0][y0] = new Veld(x0, y0, false);
                    }
                    if (velden[x2[richting]][y2[richting]].isDoel()) {
                        velden[x2[richting]][y2[richting]] = new Kist(x2[richting], y2[richting], true); //true voor doel
                    } else {
                        velden[x2[richting]][y2[richting]] = new Kist(x2[richting], y2[richting], false); //false voor geen doel
                    }
                }
            }
        }
    }
    public void verplaatsMannetje2(String richting)
    {
        int x = mannetje.getxPositie();
        int y = mannetje.getyPositie();

        switch (richting)
        {
            case "up":
                if (velden[x - 1][y] instanceof Veld && x - 1 >= 0)
                {
                    if (velden[x - 1][y].isDoel())
                    {
                        mannetje.setxPositie(x - 1);
                        mannetje.setIsDoel(true);
                        velden[x - 1][y] = mannetje; //volgende veld instellen
                    }
                    else
                    {
                        mannetje.setxPositie(x - 1);
                        mannetje.setIsDoel(false);
                        velden[x - 1][y] = mannetje; //volgende veld instellen
                    }

                    if (velden[x][y].isDoel()) //huidige veld controleren op doel
                    {
                        velden[x][y] = new Veld(x, y, true);
                    }
                    else
                    {
                        velden[x][y] = new Veld(x, y, false);
                    }
                }
                else
                {
                    if (velden[x - 1][y] instanceof Kist && x - 1 > 0)
                    {
                        if (velden[x - 2][y] instanceof Veld && x - 2 > 0)
                        {
                            if (velden[x - 1][y].isDoel())
                            {
                                mannetje.setxPositie(x - 1);
                                mannetje.setIsDoel(true);
                                velden[x - 1][y] = mannetje; //volgende veld instellen

                                if (velden[x][y - 2].isDoel())
                                {
                                    velden[x][y - 2] = new Kist(x, y - 2, true);
                                    //hier incr
                                }
                                else
                                {
                                    velden[x][y - 2] = new Kist(x, y - 2, false);
                                }
                            }
                            else
                            {
                                mannetje.setxPositie(x - 1);
                                mannetje.setIsDoel(false);
                                velden[x - 1][y] = mannetje; //volgende veld instellen
                            }

                            if (velden[x][y].isDoel()) //huidige veld controleren op doel en instellen
                            {
                                velden[x][y] = new Veld(x, y, true);
                            }
                            else
                            {
                                velden[x][y] = new Veld(x, y, false);
                            }
                            if (velden[x - 2][y].isDoel())
                            {
                                velden[x - 2][y] = new Kist(x - 2, y, true); //true voor doel
                            }
                            else
                            {
                                velden[x - 2][y] = new Kist(x - 2, y, false); //false voor geen doel
                            }
                        }
                    }
                }
                break;
            case "down":
                if (velden[x + 1][y] instanceof Veld && x + 1 >= 0)
                {
                    if (velden[x + 1][y].isDoel())
                    {
                        mannetje.setxPositie(x + 1);
                        mannetje.setIsDoel(true);
                        velden[x + 1][y] = mannetje; //volgende veld instellen
                    }
                    else
                    {
                        mannetje.setxPositie(x + 1);
                        mannetje.setIsDoel(false);
                        velden[x + 1][y] = mannetje; //volgende veld instellen
                    }

                    if (velden[x][y].isDoel()) //huidige veld controleren op doel
                    {
                        velden[x][y] = new Veld(x, y, true);
                    }
                    else
                    {
                        velden[x][y] = new Veld(x, y, false);
                    }
                }
                else
                {
                    if (velden[x + 1][y] instanceof Kist && x + 1 > 0)
                    {
                        if (velden[x + 2][y] instanceof Veld && x + 2 > 0)
                        {
                            if (velden[x + 1][y].isDoel())
                            {
                                mannetje.setxPositie(x + 1);
                                mannetje.setIsDoel(true);
                                velden[x + 1][y] = mannetje; //volgende veld instellen

                                if (velden[x][y - 2].isDoel())
                                {
                                    velden[x][y - 2] = new Kist(x, y - 2, true);
                                }
                                else
                                {
                                    velden[x][y - 2] = new Kist(x, y - 2, false);
                                }
                            }
                            else
                            {
                                mannetje.setxPositie(x + 1);
                                mannetje.setIsDoel(false);
                                velden[x + 1][y] = mannetje; //volgende veld instellen
                            }

                            if (velden[x][y].isDoel()) //huidige veld controleren op doel en instellen
                            {
                                velden[x][y] = new Veld(x, y, true);
                            }
                            else
                            {
                                velden[x][y] = new Veld(x, y, false);
                            }
                            if (velden[x + 2][y].isDoel())
                            {
                                velden[x + 2][y] = new Kist(x + 2, y, true); //true voor doel
                            }
                            else
                            {
                                velden[x + 2][y] = new Kist(x + 2, y, false); //false voor geen doel
                            }
                        }
                    }
                }
                break;
            case "left":
                if (velden[x][y - 1] instanceof Veld && y - 1 >= 0)
                {
                    if (velden[x][y - 1].isDoel())
                    {
                        mannetje.setyPositie(y - 1);
                        mannetje.setIsDoel(true);
                        velden[x][y - 1] = mannetje; //volgende veld instellen
                    }
                    else
                    {
                        mannetje.setyPositie(y - 1);
                        mannetje.setIsDoel(false);
                        velden[x][y - 1] = mannetje; //volgende veld instellen
                    }

                    if (velden[x][y].isDoel()) //huidige veld controleren op doel
                    {
                        velden[x][y] = new Veld(x, y, true);
                    }
                    else
                    {
                        velden[x][y] = new Veld(x, y, false);
                    }
                }
                else
                {
                    if (velden[x][y - 1] instanceof Kist && y - 1 > 0)
                    {
                        if (velden[x][y - 2] instanceof Veld && y - 2 > 0)
                        {
                            if (velden[x][y - 1].isDoel())
                            {
                                mannetje.setyPositie(y - 1);
                                mannetje.setIsDoel(true);
                                velden[x][y - 1] = mannetje; //volgende veld instellen

                                if (velden[x][y - 2].isDoel())
                                {
                                    velden[x][y - 2] = new Kist(x, y - 2, true);
                                }
                                else
                                {
                                    velden[x][y - 2] = new Kist(x, y - 2, false);
                                }
                            }
                            else
                            {
                                mannetje.setyPositie(y - 1);
                                mannetje.setIsDoel(false);
                                velden[x][y - 1] = mannetje; //volgende veld instellen
                            }

                            if (velden[x][y].isDoel()) //huidige veld controleren op doel en instellen
                            {
                                velden[x][y] = new Veld(x, y, true);
                            }
                            else
                            {
                                velden[x][y] = new Veld(x, y, false);
                            }
                            if (velden[x][y - 2].isDoel())
                            {
                                velden[x][y - 2] = new Kist(x, y - 2, true); //true voor doel
                            }
                            else
                            {
                                velden[x][y - 2] = new Kist(x, y - 2, false); //false voor geen doel
                            }
                        }
                    }
                }
                break;
            case "right":
                if (velden[x][y + 1] instanceof Veld && y + 1 >= 0)
                {
                    if (velden[x][y + 1].isDoel())
                    {
                        mannetje.setyPositie(y + 1);
                        mannetje.setIsDoel(true);
                        velden[x][y + 1] = mannetje; //volgende veld instellen
                    }
                    else
                    {
                        mannetje.setyPositie(y + 1);
                        mannetje.setIsDoel(false);
                        velden[x][y + 1] = mannetje; //volgende veld instellen
                    }

                    if (velden[x][y].isDoel()) //huidige veld controleren op doel
                    {
                        velden[x][y] = new Veld(x, y, true);
                    }
                    else
                    {
                        velden[x][y] = new Veld(x, y, false);
                    }
                }
                else
                {
                    if (velden[x][y + 1] instanceof Kist && y + 1 > 0)
                    {
                        if (velden[x][y + 2] instanceof Veld && y + 2 > 0)
                        {
                            if (velden[x][y + 1].isDoel())
                            {
                                mannetje.setyPositie(y + 1);
                                mannetje.setIsDoel(true);
                                velden[x][y + 1] = mannetje; //volgende veld instellen

                                if (velden[x][y - 2].isDoel())
                                {
                                    velden[x][y - 2] = new Kist(x, y - 2, true);
                                }
                                else
                                {
                                    velden[x][y - 2] = new Kist(x, y - 2, false);
                                }
                            }
                            else
                            {
                                mannetje.setyPositie(y + 1);
                                mannetje.setIsDoel(false);
                                velden[x][y + 1] = mannetje; //volgende veld instellen
                            }

                            if (velden[x][y].isDoel()) //huidige veld controleren op doel en instellen
                            {
                                velden[x][y] = new Veld(x, y, true);
                            }
                            else
                            {
                                velden[x][y] = new Veld(x, y, false);
                            }
                            if (velden[x][y + 2].isDoel())
                            {
                                velden[x][y + 2] = new Kist(x, y + 2, true); //true voor doel
                            }
                            else
                            {
                                velden[x][y + 2] = new Kist(x, y + 2, false); //false voor geen doel
                            }
                        }
                    }
                }
                break;
            default:
                break;
        }
        System.out.print(this.toString());
    }

    private int getAantalKisten()
    {
        int aantal = 0;
        for (Element[] rij : velden)
        {
            for (Element cel : rij)
            {
                if (cel instanceof Kist)
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
            for (Element cel : rij)
            {
                if (cel instanceof Kist)
                {
                    if(cel.isDoel())
                    {
                    aantal++;
                    }
                }
            }
        }
        if (this.getAantalKisten() == aantal)
        {
            return true;
        }
        else
        {
            return false;
        }
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
}
