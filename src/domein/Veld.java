package domein;

public class Veld extends Element
{

    /**
     *
     * @param xPositie
     * @param yPositie
     */
    public Veld(int xPositie, int yPositie)
    {
        super(xPositie, yPositie);
    }

    /**
     *
     * @param xPositie
     * @param yPositie
     * @param isDoel
     */
    public Veld(int xPositie, int yPositie, boolean isDoel)
    {
        super(xPositie, yPositie, isDoel);
    }

    @Override
    public String toString()
    {
        if (this.isDoel())
        {
            return "D";
        }
        else
        {
            return "V";
        }
    }

}
