package domein;

public class Muur extends Veld
{

    public Muur(int xPositie, int yPositie)
    {
        super(xPositie, yPositie);
        this.isDoel = false;
    }

    @Override
    public String toString()
    {
        return "M";
    }
}
