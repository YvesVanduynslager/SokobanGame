package domein;

/**
 * Behandelt abstracte superklasse Element. Superklasse van Kist, Mannetje, Muur, Veld.
 * @author Yves
 */
public abstract class Element
{
    /* DECLARATIES VARIABELES */
    private int xPositie;
    private int yPositie;
    private boolean isDoel;

    /**
     * Constructor stelt x positie, y positie in van het element, en stelt in of het element als een doel behandelt moet worden.
     * @param xPositie x-coördinaat, int.
     * @param yPositie y-coördinaat, int
     * @param isDoel true als doel, false als geen doel.
     */
    public Element(int xPositie, int yPositie, boolean isDoel)
    {
        this.xPositie = xPositie;
        this.yPositie = yPositie;
        this.isDoel = isDoel;
    }

    /**
     * Teruggeven van x-coördinaat.
     * @return x-coördinaat als int.
     */
    public int getxPositie()
    {
        return xPositie;
    }

    /**
     * Instellen van x-coördinaat.
     * @param xPositie x-coördinaat als int.
     */
    public void setxPositie(int xPositie)
    {
        this.xPositie = xPositie;
    }

    /**
     * Teruggeven van y-coördinaat.
     * @return y-coördinaat als int.
     */
    public int getyPositie()
    {
        return yPositie;
    }

    /**
     * Instellen van y-coördinaat.
     * @param yPositie y-coördinaat als int.
     */
    public void setyPositie(int yPositie)
    {
        this.yPositie = yPositie;
    }

    /**
     * Teruggeven van doelinstelling.
     * @return True als doel, false als geen doel.
     */
    public boolean isDoel()
    {
        return isDoel;
    }

    /**
     * Instellen van doel.
     * @param isDoel True als doel, false als geen doel.
     */
    public void setIsDoel(boolean isDoel)
    {
        this.isDoel = isDoel;
    }

	/**
	 * 
	 * @param xPositie
	 * @param yPositie
	 */
	public Element(int xPositie, int yPositie) {
		// TODO - implement Element.Element
		throw new UnsupportedOperationException();
	}
}