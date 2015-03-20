package domein;

/**
 * Behandelt Veldelement. Subklasse van Element.
 *
 * @author Yves
 *
 */
public class Veld extends Element
{
    /**
     * Constructor stelt x positie, y positie in van het element, en stelt in of
     * het element als een doel behandelt moet worden.
     *
     * @param xPositie x-coördinaat, int.
     * @param yPositie y-coördinaat, int
     * @param isDoel true als doel, false als geen doel.
     */
    public Veld(int xPositie, int yPositie, boolean isDoel)
    {
        super(xPositie, yPositie, isDoel);
    }

    /**
     * Geeft de representatie terug van een Veld of Veld als doel.
     *
     * @return representatie van een Veld of Veld als doel.
     */
    @Override
    public String toString()
    {
        if (this.isDoel()) //als het veld een doel is
        {
            return "D";
        }
        else //als het veld geen doel is.
        {
            return ".";
        }
    }
}
