package domein;

public class Kist extends Element
{
    public Kist(int xPositie, int yPositie, boolean doel)
    {
        super(xPositie, yPositie, doel);
    }
    
    /**
	 * 
	 * @param yPositie
	 * @param xPositie
	 */
	public Kist(int yPositie, int xPositie)
    {
        super(xPositie, yPositie, false);
    }

    @Override
    public String toString()
    {
        return "K";
    }
}
