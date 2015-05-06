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
     * Constructor stelt kolom-positie, rij-positie in van het element, en stelt in of
     * het element als een doel behandelt moet worden.
     *
     * @param kolomPositie kolom-positie, int.
     * @param rijPositie rij-positie, int
     * @param isDoel true als doel, false als geen doel.
     */
    public Mannetje(int kolomPositie, int rijPositie, boolean isDoel)
    {
        super(kolomPositie, rijPositie, isDoel, false);

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
