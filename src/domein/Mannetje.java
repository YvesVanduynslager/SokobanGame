package domein;

public class Mannetje extends Veld{

    /**
     *
     * @param xPositie
     * @param yPositie
     */
    public Mannetje(int xPositie, int yPositie) {
        super(xPositie, yPositie);
//        this.xPositie = xPositie;
//        this.yPositie = yPositie;
    }
    
    public Mannetje (int xPositie, int yPositie, boolean isDoel){
        super(xPositie, yPositie, isDoel);

    }

        @Override
    public String toString()
    {
        return "O";
    }
}
