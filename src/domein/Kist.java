package domein;

/**
 * Behandelt Kistelement. Subklasse van Element.
 *
 * @author Yves
 *
 */
public final class Kist extends Element
{
    /**
     * Constructor stelt kolom-positie, rij-positie in van het element, en stelt in of
     * het element als een doel behandelt moet worden.
     *
     * @param kolomPositie kolom-positie, int.
     * @param rijPositie rij-positie, int
     * @param isDoel
     */
    public Kist(int kolomPositie, int rijPositie, boolean isDoel)
    {
        super(kolomPositie, rijPositie, isDoel, false);
    }

    /**
     * Geeft de representatie terug van een Kist.
     *
     * @return representatie van een Kist.
     */
    @Override
    public String toString()
    {
        return "K";
    }
}
