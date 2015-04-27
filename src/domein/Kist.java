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
	 * 
	 * @param xPositie x-coördinaat, int.
	 * @param yPositie y-coördinaat, int
	 * @param isDoel
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
