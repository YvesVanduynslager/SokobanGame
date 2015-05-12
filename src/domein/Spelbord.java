package domein;

import exceptions.OngeldigAantalDoelenException;
import exceptions.OngeldigAantalKistenException;
import exceptions.OngeldigAantalMannetjesException;
import exceptions.OngelijkAantalDoelenKistenException;

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
    /* DECLARATIES CONSTANTEN */
    private Element[][] velden;
    private Mannetje mannetje;
	private boolean voltooid;

    /**
     * Default-constructor stelt een leeg spelbord in.
     */
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
     * Geeft het aangrenzend element terug op basis van meegegeven parameters.
     *
     * @param element Element object waarop gecontroleerd moet worden.
     * @param richting in welke richting gecontroleerd moet worden.
     * @return aangrenzend Element-object.
     */
    private Element geefAangrenzendElement(Element element, int richting)
    {
        int kolom = element.getKolomPositie();
        int rij = element.getRijPositie();

        int[] kolom1 =
        {
            kolom - 1, kolom + 1, kolom, kolom
        }; /*kolom1 bevat de kolom-waarden voor de veplaatsing van het mannetje
         in de vorm {omhoog, omlaag, links, rechts}*/

        int[] rij1 =
        {
            rij, rij, rij - 1, rij + 1
        }; /*rij1 bevat de rij-waarden voor de veplaatsing van het mannetje
         in de vorm {omhoog, omlaag, links, rechts}*/

        return velden[kolom1[richting]][rij1[richting]];
    }

    /**
     * Staat in voor het bewegen van het mannetje en kisten over het spelbord.
     *
     * @param richting Gewenste richting waar bewogen moet naar worden. 0 voor
     * omhoog, 1 voor omlaag, 2 voor links en 3 voor rechts.
     */
    public void verplaatsMannetje(int richting)
    {
        int rij0 = mannetje.getKolomPositie();
        int kolom0 = mannetje.getRijPositie();
        int rij, kolom, rijNa, kolomNa;

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

        rij = aangrenzend.getKolomPositie();
        kolom = aangrenzend.getRijPositie();
        rijNa = naAangrenzend.getKolomPositie();
        kolomNa = naAangrenzend.getRijPositie();

        if (aangrenzend instanceof Veld) //als aangrenzend element een Veld is
        {
            if (mannetje.isDoel()) //als huidige veld doel is
            {
                velden[rij0][kolom0] = new Veld(rij0, kolom0, true);
            }
            else //als huidige veld geen doel is
            {
                velden[rij0][kolom0] = new Veld(rij0, kolom0, false);
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
            mannetje.setKolomPositie(rij);
            mannetje.setRijPositie(kolom);
            velden[rij][kolom] = mannetje;
        }

        if (aangrenzend instanceof Kist && naAangrenzend instanceof Veld)
        {
            if (mannetje.isDoel()) //als huidige veld doel is
            {
                velden[rij0][kolom0] = new Veld(rij0, kolom0, true);
            }
            else //als huidige veld geen doel is
            {
                velden[rij0][kolom0] = new Veld(rij0, kolom0, false);
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
            mannetje.setKolomPositie(rij);
            mannetje.setRijPositie(kolom);
            aangrenzend.setKolomPositie(rijNa);
            aangrenzend.setRijPositie(kolomNa);

            velden[rij][kolom] = mannetje;
            velden[rijNa][kolomNa] = aangrenzend;
        }

        if (rij0 != mannetje.getKolomPositie() || kolom0 != mannetje.getRijPositie()) //Als het mannetje effectief heeft bewogen
        {
            ++aantalZetten;
        }

        System.gc(); /*Oproep als test. Na cratie van een exe file en bij uitvoering hiervan,
         stijgt het gebruikte RAM-geheugen bij iedere verplaatsing met 1-5 MB.*/

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
        return getAantalKisten() == aantalKistenOpDoel;
    }

    /**
     * Bouwt het spelbord op als String. Wordt enkel gebruikt in CUI.
     *
     * @return spelbord als String.
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
     * Bouwt het spelbord op als 2D String. Wordt enkel gebruikt in GUI.
     *
     * @return spelbord als 2D String.
     */
    public String[][] to2DString()
    {
        String[][] veldenString = new String[velden.length][velden[0].length];

        for (int rij = 0; rij < velden.length; rij++)
        {
            for (int kolom = 0; kolom < velden[rij].length; kolom++)
            {
                veldenString[rij][kolom] = velden[rij][kolom].toString();
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
	 * @param elementType Welk soort element moet toegevoegd worden (kist, muur, mannetje, veld of doel).
	 * @param kolom
	 * @param rij
	 */
    public void plaatsElement(String elementType, int rij, int kolom)
    {
        if (geldigePlaats(rij, kolom))
        {
            switch (elementType)
            {
                case "kist":
                    velden[rij][kolom] = new Kist(rij, kolom, false);
                    break;
                case "muur":
                    velden[rij][kolom] = new Muur(rij, kolom);
                    break;
                case "mannetje":
                    velden[rij][kolom] = new Mannetje(rij, kolom, false);
                    break;
                case "veld":
                    velden[rij][kolom] = new Veld(rij, kolom, false);
                    break;
                case "doel":
                    velden[rij][kolom] = new Veld(rij, kolom, true);
                    break;
                default:
                    velden[rij][kolom] = new Veld(rij, kolom, false);
                    break;
            }
        }
    }

    /**
     * Controleert of het element op gekozen positie volgens argumenten een
     * vaststaand element is.
     *
     * @param rij rij-index van het element.
     * @param kolom kolom-index van het element.
     * @return true voor niet-vaststaand element.
     */
    private boolean geldigePlaats(int rij, int kolom)
    {
        return !velden[rij][kolom].staatVast();
    }

    /**
     * Initialiseert een velden-array: alle randen zijn onveranderlijke muren.
     * Alle andere elementen zijn veranderlijke velden (zonder doel).
     */
    private void maakLeegSpelbord()
    {
        velden = new Element[10][10];

        for (int rij = 0; rij < velden.length; rij++)
        {
            for (int kolom = 0; kolom < velden[rij].length; kolom++)
            {
                /*
                 Randen opvullen met muren.
                 */
                if (rij == 0 || rij == velden.length - 1 || kolom == 0 || kolom == velden[rij].length - 1)
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

        //System.out.print(this); //Test
    }

    /**
     * Controleert of het custom spelbord geldig is opgebouwd. Kan alternatief
     * ook met boolean, maar omslachtiger.
     *
     * @throws OngeldigAantalMannetjesException
     * @throws OngelijkAantalDoelenKistenException
     * @throws OngeldigAantalKistenException
     * @throws OngeldigAantalDoelenException
     */
    public void controleerGeldigheid() throws OngeldigAantalMannetjesException, OngelijkAantalDoelenKistenException,
            OngeldigAantalKistenException, OngeldigAantalDoelenException
    {
        int aantalMannetjes = 0, aantalKisten = 0, aantalDoelen = 0;

        for (Element[] rij : velden)
        {
            for (Element element : rij)
            {
                if (element instanceof Mannetje)
                {
                    aantalMannetjes++;
                }
                else
                {
                    if (element instanceof Veld && element.isDoel())
                    {
                        aantalDoelen++;
                    }
                    if (element instanceof Kist)
                    {
                        aantalKisten++;
                    }
                }
            }
        }

        if (!(aantalMannetjes == 1))
        {
            throw new OngeldigAantalMannetjesException();
        }
        else
        {
            if (!(aantalKisten > 0))
            {
                throw new OngeldigAantalKistenException();
            }
            else
            {
                if (!(aantalDoelen > 0))
                {
                    throw new OngeldigAantalDoelenException();
                }
                else
                {
                    if (!(aantalDoelen == aantalKisten))
                    {
                        throw new OngelijkAantalDoelenKistenException();
                    }
                }
            }
        }
    }
}