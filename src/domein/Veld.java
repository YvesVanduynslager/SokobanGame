package domein;

public class Veld extends Element
{
    public Veld(int xPositie, int yPositie)
    {
        super(xPositie, yPositie);
    }

    public Veld(int xPositie, int yPositie, boolean isDoel)
    {
        super(xPositie, yPositie, isDoel);
    }

    @Override
    public String toString()
    {
        if (super.isDoel())
        {
            return "D";
        }
        else
        {
            return ".";
        }
    }
}
