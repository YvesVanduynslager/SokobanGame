package domein;

public class Kist extends Element
{
    public Kist(int xPositie, int yPositie, boolean doel)
    {
        super(xPositie, yPositie, doel);
    }
    
    public Kist(int xPositie, int yPositie)
    {
        super(xPositie, yPositie, false);
    }

    @Override
    public String toString()
    {
        return "K";
    }
}
