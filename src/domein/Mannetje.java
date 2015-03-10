package domein;

public class Mannetje{

	private int xPositie;
	private int yPositie;

    /**
     *
     * @param xPositie
     * @param yPositie
     */
    public Mannetje(int xPositie, int yPositie) {
        this.xPositie = xPositie;
        this.yPositie = yPositie;
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