package domein;

public class Muur extends Element
{

    public Muur(int xPositie, int yPositie)
    {
        super(xPositie, yPositie, false);
    }

    @Override
    public String toString()
    {
        return "#";
    }
}
