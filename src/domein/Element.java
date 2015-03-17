package domein;

public abstract class Element
{
    private int xPositie;
    private int yPositie;
    private boolean isDoel;

    public Element(int xPositie, int yPositie)
    {
        this.xPositie = xPositie;
        this.yPositie = yPositie;
    }

    public Element(int xPositie, int yPositie, boolean isDoel)
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
}