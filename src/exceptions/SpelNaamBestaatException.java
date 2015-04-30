package exceptions;

/**
 * Klasse die erft van Exception, gebruikt als controle of een spelnaam al
 * bestaat in de databank.
 *
 * @author Yves
 */
public final class SpelNaamBestaatException extends Exception
{
    /**
     * Default constructor
     */
    public SpelNaamBestaatException()
    {
    }

    /**
     * Geeft een bericht mee aan de exception.
     *
     * @param message Bericht als String.
     */
    public SpelNaamBestaatException(String message)
    {
        super(message);
    }

    /**
     * Geeft een bericht en object mee met info over de code die de exception
     * heeft veroorzaakt.
     *
     * @param message Bericht als String.
     * @param cause object met info over de exception.
     */
    public SpelNaamBestaatException(String message, Throwable cause)
    {
        super(message, cause);
    }

    /**
     * Geet een object mee met info over de code die de exception heeft
     * veroorzaakt.
     *
     * @param cause object met info over de exception.
     */
    public SpelNaamBestaatException(Throwable cause)
    {
        super(cause);
    }
}