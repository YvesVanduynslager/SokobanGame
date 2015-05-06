package domein;

/**
 * Behandelt Muurelement. Subklasse van Element.
 *
 * @author Yves
 *
 */
public final class Muur extends Element
{
    /**
     * Constructor stelt kolom-positie, rij-positie in van het element, en stelt in
     * dat dit element geen doel is.
     *
     * @param kolomPositie kolom-positie, int.
     * @param rijPositie rij-positie, int
     */
    public Muur(int kolomPositie, int rijPositie)
    {
        super(kolomPositie, rijPositie, false);
    }
    
    /**
     * Constructor stelt kolom-positie, rij-positie in van het element, en stelt in
     * dat dit element geen doel is. Stelt ook in of dit element mag vervangen worden op het spelbord.
     * Deze constructor gebruiken om spelbord te maken.
     * 
     * @param kolomPositie kolom-positie, int
     * @param rijPositie rij-positie, int
     * @param staatVast stelt in of het Element vervangen mag worden op het spelbord
     */
    public Muur(int kolomPositie, int rijPositie, boolean staatVast)
    {
        super(kolomPositie, rijPositie, false, staatVast);
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
