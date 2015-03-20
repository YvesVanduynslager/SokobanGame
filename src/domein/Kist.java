package domein;

/**
 * Behandelt Kistelement. Subklasse van Element.
 *
 * @author Yves
 *
 */
public class Kist extends Element
{
    /**
     * Constructor stelt x positie, y positie in van het element, en stelt in of het element als een doel behandelt moet worden.
     * @param xPositie x-coördinaat, int.
     * @param yPositie y-coördinaat, int
     * @param isDoel true als doel, false als geen doel.
     */
    public Kist(int xPositie, int yPositie, boolean isDoel)
    {
        super(xPositie, yPositie, isDoel);
    }

    /**
     * Geeft de representatie terug van een Kist.
     * @return representatie van een Kist.
     */
    @Override
    public String toString()
    {
        return "K";
    }
}
