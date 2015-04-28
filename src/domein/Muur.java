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
     * Constructor stelt x positie, y positie in van het element, en stelt in
     * dat dit element geen doel is. Stelt ook in of dit element mag vervangen worden op het spelbord.
     * Deze constructor gebruiken om spelbord te maken.
     * 
     * @param xPositie x-coördinaat, int
     * @param yPositie y-coördinaat, int
     * @param staatVast stelt in of het Element vervangen mag worden op het spelbord
     */
    public Muur(int xPositie, int yPositie, boolean staatVast)
    {
        super(xPositie, yPositie, false, staatVast);
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
