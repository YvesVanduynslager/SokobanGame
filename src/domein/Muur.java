package domein;

/**
 * Behandelt Muurelement. Subklasse van Element.
 *
 * @author Yves
 *
 */
public class Muur extends Element
{
    /**
     * Constructor stelt x positie, y positie in van het element, en stelt in
     * dat dit element geen doel is.
     *
     * @param xPositie x-coördinaat, int.
     * @param yPositie y-coördinaat, int
     */
    public Muur(int xPositie, int yPositie)
    {
        super(xPositie, yPositie, false);
    }

    /**
     * Geeft de representatie terug van een Muur.
     *
     * @return representatie van een Muur.
     */
    @Override
    public String toString()
    {
        return "#";
    }
}
