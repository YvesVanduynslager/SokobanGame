package exceptions;

/**
 * Klasse die erft van Exception, gebruikt bij aanmelden als een gebruiker niet gevonden werd.
 *
 * @author Yves
 */
public final class GebruikerNietGevondenException extends Exception
{
    /**
     * Default constructor
     */
    public GebruikerNietGevondenException()
    {
    }

    /**
     * Geeft een bericht mee aan de exception.
     *
     * @param message Bericht als String.
     */
    public GebruikerNietGevondenException(String message)
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
    public GebruikerNietGevondenException(String message, Throwable cause)
    {
        super(message, cause);
    }

    /**
     * Geet een object mee met info over de code die de exception heeft
     * veroorzaakt.
     *
     * @param cause object met info over de exception.
     */
    public GebruikerNietGevondenException(Throwable cause)
    {
        super(cause);
    }
}