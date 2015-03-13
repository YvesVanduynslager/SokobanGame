package domein;

/**
 * 
 * @author Yves
 */
public class Spelbord
{
    private final Veld[][] velden;
    private final Mannetje mannetje;
    private boolean isVoltooid;

    /**
     * 
     * @param velden
     * @param mannetje 
     */
    public Spelbord(Veld[][] velden, Mannetje mannetje)
    {
        this.velden = velden;
        this.mannetje = mannetje;
    }

    /**
     * 
     * @param richting 
     */
    public void verplaatsMannetje(String richting)
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
    }

    /**
     * 
     * @return 
     */
    @Override
    public String toString()
    {
        String output = "";

        for (Veld[] rij : this.velden)
        {
            output += "-----------------------------------------\n";
            for (Veld cel : rij)
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
    public boolean isVoltooid()
    {
        return isVoltooid;
    }

    /**
     * 
     * @return 
     */
    public Veld[][] geefVelden()
    {
        return this.velden;
    }
}
