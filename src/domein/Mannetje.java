package domein;

/**
 * Behandelt Mannetje-element. Subklasse van Element.
 *
 * @author Yves
 *
 */
public final class Mannetje extends Element
{
    /**
     * Constructor stelt x positie, y positie in van het element, en stelt in of
     * het element als een doel behandelt moet worden.
     *
     * @param xPositie x-coördinaat, int.
     * @param yPositie y-coördinaat, int
     * @param isDoel true als doel, false als geen doel.
     */
    public Mannetje(int xPositie, int yPositie, boolean isDoel)
    {
        super(xPositie, yPositie, isDoel, false);

    }

    /**
     * Geeft de representatie terug van een Mannetje.
     *
     * @return representatie van een Mannetje.
     */
    @Override
    public String toString()
    {
        return "O";
    }
}
