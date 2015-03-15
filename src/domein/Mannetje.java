package domein;

public class Mannetje extends Element
{
    /**
     *
     * @param xPositie
     * @param yPositie
     */
    public Mannetje(int xPositie, int yPositie)
    {
        super(xPositie, yPositie);
    }

    public Mannetje(int xPositie, int yPositie, boolean isDoel)
    {
        super(xPositie, yPositie, isDoel);

    }

    @Override
    public String toString()
    {
        return "O";
    }
}
