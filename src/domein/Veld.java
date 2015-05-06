package domein;

/**
 * Behandelt Veldelement. Subklasse van Element.
 *
 * @author Yves
 *
 */
public final class Veld extends Element
{
    /**
     * Constructor stelt kolom-positie, rij-positie in van het element, en stelt in of
     * het element als een doel behandelt moet worden.
     *
     * @param kolomPositie kolom-positie, int.
     * @param rijPositie rij-positie, int
     * @param isDoel true als doel, false als geen doel.
     */
    public Veld(int kolomPositie, int rijPositie, boolean isDoel)
    {
        super(kolomPositie, rijPositie, isDoel, false);
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