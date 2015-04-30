package domein;

/**
 * Beheert de Spelbord-objecten. Verplaatsen van mannetje, teruggeven van
 * spelbord, bijhouden aantal zetten.
 *
 * @author Yves
 */
public final class Spelbord
{
    /* DECLARATIES VARIABELEN */
    private int aantalZetten;
    private boolean voltooid;
    /* DECLARATIES CONSTANTEN */
    private Element[][] velden;
    private Mannetje mannetje;
    //private String spelbordNaam;

    
    public Spelbord()
    {
        this.maakLeegSpelbord(); //initialiseren van een leeg spelbord;
    }
    /**
     * Initialiseert de naam, velden, het mannetje en het aantal zetten.
     *
     * @param velden De 2D-Element Array van het spelbord.
     * @param mannetje Het mannetje van dit spelbord.
     */
    public Spelbord(Element[][] velden, Mannetje mannetje)
    {
        this.velden = velden;
        this.mannetje = mannetje;
        this.aantalZetten = 0;
    }

    public Spelbord(Element[][] velden)
    {
        this.velden = velden;
    }

    /**
     * Geeft het mannetje van het huidige spelbord terug.
     *
     * @return het mannetje van het huidige spelbord.
     */
    public Mannetje getMannetje()
    {
        return this.mannetje;
    }

    /**
     * Geeft de naam van het spel terug.
     *
     * @return Naam van het spel als String.
     */
//    public String getNaam()
//    {
//        return this.spelbordNaam;
//    }

    /**
     *
     * @param element
     * @param richting
     * @return
     */
    private Element geefAangrenzendElement(Element element, int richting)
    {
        int x = element.getxPositie();
        int y = element.getyPositie();

        int[] x1 =
        {
            x - 1, x + 1, x, x
        }; /*x1 bevat de x-waarden voor de veplaatsing van het mannetje
         in de vorm {omhoog, omlaag, links, rechts}*/

        int[] y1 =
        {
            y, y, y - 1, y + 1
        }; /*y1 bevat de y-waarden voor de veplaatsing van het mannetje
         in de vorm {omhoog, omlaag, links, rechts}*/

        return velden[x1[richting]][y1[richting]];
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
        int x, y, xNa, yNa;

        Element aangrenzend = this.geefAangrenzendElement(mannetje, richting);
        Element naAangrenzend;

        if (!(aangrenzend instanceof Muur))
        {
            naAangrenzend = this.geefAangrenzendElement(aangrenzend, richting);
        }
        else
        {
            naAangrenzend = aangrenzend; //Om geen nullpointer te krijgen. Dit gaf plots een fout waar er vroeger geen was maar er wel een moest zijn.
        }

        x = aangrenzend.getxPositie();
        y = aangrenzend.getyPositie();
        xNa = naAangrenzend.getxPositie();
        yNa = naAangrenzend.getyPositie();

        if (aangrenzend instanceof Veld) //als aangrenzend element een Veld is
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
            if (aangrenzend.isDoel()) //als aangrenzend veld doel is
            {
                mannetje.setIsDoel(true);
            }
            else //als aangrenzend veld geen doel is
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

        if (x0 != mannetje.getxPositie() || y0 != mannetje.getyPositie()) //Als het mannetje effectief heeft bewogen
        {
            ++aantalZetten;
        }
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
        String output = ""; //spelbordNaam + "\n";

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
    
    public String[][] to2DString()
    {
        //Element[][] elementen = huidigSpel.getHuidigSpelbord().geefVelden();

        String[][] veldenString = new String[10][10];

        for (int i = 0; i < velden.length; i++)
        {
            for (int j = 0; j < velden[i].length; j++)
            {
                veldenString[i][j] = velden[i][j].toString();
            }
        }
        return veldenString;
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

    /**
     *
     * @param elementType
     * @param xPositie
     * @param yPositie
     */
    public void plaatsElement(String elementType, int xPositie, int yPositie)
    {
        if(geldigePlaats(xPositie, yPositie))
        {
            switch (elementType)
            {
                case "kist":
                    velden[xPositie][yPositie] = new Kist(xPositie, yPositie, false);
                    break;
                case "muur":
                    velden[xPositie][yPositie] = new Muur(xPositie, yPositie);
                    break;
                case "mannetje":
                    velden[xPositie][yPositie] = new Mannetje(xPositie, yPositie, false);
                    break;
                case "veld":
                    velden[xPositie][yPositie] = new Veld(xPositie, yPositie, false);
                    break;
                case "doel":
                    velden[xPositie][yPositie] = new Veld(xPositie, yPositie, true);
                    break;
                default:
                    velden[xPositie][yPositie] = new Veld(xPositie, yPositie, false);
                    break;
            }
        }
    }
    
    private boolean geldigePlaats(int xPositie, int yPositie)
    {
        return !velden[xPositie][yPositie].staatVast();
    }
    
    /**
     * 
     */
    private void maakLeegSpelbord()
    {        
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
    }
}