package domein;

public class Veld
{
    protected int xPositie;
    protected int yPositie;
    protected boolean isDoel;

    public Veld(int xPositie, int yPositie)
    {
        this.xPositie = xPositie;
        this.yPositie = yPositie;
    }

    public Veld(int xPositie, int yPositie, boolean isDoel)
    {
        this.xPositie = xPositie;
        this.yPositie = yPositie;
        this.isDoel = isDoel;
    }

    public int getxPositie()
    {
        return xPositie;
    }

    public void setxPositie(int xPositie)
    {
        this.xPositie = xPositie;
    }

    public int getyPositie()
    {
        return yPositie;
    }

    public void setyPositie(int yPositie)
    {
        this.yPositie = yPositie;
    }

    public boolean isDoel()
    {
        return isDoel;
    }

    public void setIsDoel(boolean isDoel)
    {
        this.isDoel = isDoel;
    }

    @Override
    public String toString()
    {
        if (this.isDoel)
        {
            return "D";
        }
        else
        {
            return " ";
        }
    }
}
