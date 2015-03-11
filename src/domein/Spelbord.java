package domein;

import java.util.*;

public class Spelbord
{
    private final Veld[][] velden;
    private final Mannetje mannetje;
    private boolean isVoltooid;
    //private int spelbordID;

    public Spelbord(Veld[][] velden, Mannetje mannetje)
    {
        this.velden = velden;
        this.mannetje = mannetje;
    }

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
                if (velden.get(y).get(x + 1) instanceof Veld && x + 1 < 10)
                {
                    mannetje.setxPositie(x + 1);
                }
                else
                {
                    if (velden.get(y).get(x + 1) instanceof Kist && x + 1 < 10)
                    {
                        if (velden.get(y).get(x + 2) instanceof Veld && x + 2 < 10)
                        {
                            boolean doel = velden.get(y).get(x + 2).isIsDoel();
                            ArrayList<Veld> tijdelijkeRij = velden.get(y);
                            Kist tijdelijkeKist = new Kist(x + 2, y);
                            tijdelijkeKist.setIsDoel(doel);
                            tijdelijkeRij.add(x + 2, tijdelijkeKist);
                            Veld tijdelijkVeld = new Veld(x + 1, y);
                            tijdelijkeRij.add(x + 1, tijdelijkVeld);
                            velden.add(y, tijdelijkeRij);

                            mannetje.setxPositie(x + 1);
                        }
                    }
                }
                break;
            case "left":
                if (velden.get(y - 1).get(x) instanceof Veld && y - 1 > 0)
                {
                    mannetje.setyPositie(y - 1);
                }
                else
                {
                    if (velden.get(y - 1).get(x) instanceof Kist && y - 1 > 0)
                    {
                        if (velden.get(y - 2).get(x) instanceof Veld && y - 2 > 0)
                        {
                            boolean doel = velden.get(y - 2).get(x).isIsDoel();
                            ArrayList<Veld> tijdelijkeRij1 = velden.get(y - 1);
                            ArrayList<Veld> tijdelijkeRij2 = velden.get(y - 2);
                            Kist tijdelijkeKist = new Kist(x, y - 2);
                            tijdelijkeKist.setIsDoel(doel);
                            tijdelijkeRij2.add(x, tijdelijkeKist);
                            Veld tijdelijkVeld = new Veld(x, y - 1);
                            tijdelijkeRij1.add(x, tijdelijkVeld);
                            velden.add(y - 1, tijdelijkeRij1);
                            velden.add(y - 2, tijdelijkeRij2);

                            mannetje.setyPositie(y - 1);
                        }
                    }
                }
                break;
            case "right":
                if (velden.get(y + 1).get(x) instanceof Veld && y - 1 < 10)
                {
                    mannetje.setyPositie(y + 1);
                }
                else
                {
                    if (velden.get(y + 1).get(x) instanceof Kist && y + 1 < 10)
                    {
                        if (velden.get(y + 2).get(x) instanceof Veld && y + 2 < 10)
                        {
                            boolean doel = velden.get(y + 2).get(x).isIsDoel();
                            ArrayList<Veld> tijdelijkeRij1 = velden.get(y + 1);
                            ArrayList<Veld> tijdelijkeRij2 = velden.get(y + 2);
                            Kist tijdelijkeKist = new Kist(x, y + 2);
                            tijdelijkeKist.setIsDoel(doel);
                            tijdelijkeRij2.add(x, tijdelijkeKist);
                            Veld tijdelijkVeld = new Veld(x, y + 1);
                            tijdelijkeRij1.add(x, tijdelijkVeld);
                            velden.add(y + 1, tijdelijkeRij1);
                            velden.add(y + 2, tijdelijkeRij2);

                            mannetje.setyPositie(y + 1);
                        }
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    public String toString()
    {
        String output = "";

        for (Veld[] rij : this.velden)
        {
            for (Veld cel : rij)
            {
                output += cel.toString();
            }
            output += "\n";
        }
        return output;
    }

    public boolean isVoltooid()
    {
        return isVoltooid;
    }

    public Veld[][] geefVelden()
    {
        return this.velden;
    }
}
