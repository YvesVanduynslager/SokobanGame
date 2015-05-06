package domein;

/**
 * Behandelt abstracte superklasse Element. Superklasse van Kist, Mannetje, Muur, Veld.
 * @author Yves
 */
public abstract class Element
{
    private int kolomPositie;
    private int rijPositie;
    private boolean isDoel;
    private boolean staatVast;

    /**
     * Constructor stelt kolom-positie, rij-positie in van het element, en stelt in of het element als een doel behandelt moet worden.
     * @param kolomPositie kolom-positie, int.
     * @param rijPositie rij-positie, int
     * @param isDoel true als doel, false als geen doel.
     */
    public Element(int kolomPositie, int rijPositie, boolean isDoel)
    {
        this.kolomPositie = kolomPositie;
        this.rijPositie = rijPositie;
        this.isDoel = isDoel;
    }
    
    /**
     * Constructor stelt kolom-positie, rij-positie in van het element, en stelt in of het element als een doel behandelt moet worden
     * en of het element vast staat op het spelbord.
     * @param kolomPositie kolom-positie, int.
     * @param rijPositie rij-positie, int.
     * @param isDoel true als doel, false als geen doel.
     * @param staatVast true als vaststaand, false als niet vaststaand.
     */
    public Element(int kolomPositie, int rijPositie, boolean isDoel, boolean staatVast)
    {
        this(kolomPositie, rijPositie, isDoel);
        this.staatVast = staatVast;
    }

    /**
     * Teruggeven van kolom-positie.
     * @return kolom-positie als int.
     */
    public int getKolomPositie()
    {
        return kolomPositie;
    }

    /**
     * Instellen van kolom-positie
     * @param kolomPositie kolom-positie als int.
     */
    public void setKolomPositie(int kolomPositie)
    {
        this.kolomPositie = kolomPositie;
    }

    /**
     * Teruggeven van rij-positie.
     * @return rij-positie als int.
     */
    public int getRijPositie()
    {
        return rijPositie;
    }

    /**
     * Instellen van rij-positie
     * @param rijPositie rij-positie als int.
     */
    public void setRijPositie(int rijPositie)
    {
        this.rijPositie = rijPositie;
    }

    /**
     * Teruggeven van doelinstelling.
     * @return true als doel, false als geen doel.
     */
    public boolean isDoel()
    {
        return isDoel;
    }
    
    /**
     * Teruggeven van vaststand-instelling.
     * @return true als vaststaand, false als niet vaststaand.
     */
    public boolean staatVast()
    {
        return staatVast;
    }

    /**
     * Instellen of dit element een doel is of niet.
     * @param isDoel True als doel, false als geen doel.
     */
    public void setIsDoel(boolean isDoel)
    {
        this.isDoel = isDoel;
    }
}