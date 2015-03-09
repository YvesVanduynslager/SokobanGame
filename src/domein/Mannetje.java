package domein;

public class Mannetje extends Veld{

	private int xPositie;
	private int yPositie;

    /**
     *
     * @param xPositie
     * @param yPositie
     */
    public Mannetje(int xPositie, int yPositie) {
        super(xPositie, yPositie, false);
//        this.xPositie = xPositie;
//        this.yPositie = yPositie;
    }
    
    public Mannetje(int xPositie, int yPositie, boolean isDoel)
    {
        super(xPositie, yPositie, isDoel);
    }

    public int getxPositie() {
        return xPositie;
    }

    public void setxPositie(int xPositie) {
        this.xPositie = xPositie;
    }

    public int getyPositie() {
        return yPositie;
    }

    public void setyPositie(int yPositie) {
        this.yPositie = yPositie;
    }

        @Override
    public String toString()
    {
        return "O";
    }
}