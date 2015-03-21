package exceptions;

/**
 * Klasse die erft van Exception, gebruikt bij registreren als een gebruiker al
 * bestaat in de databank.
 *
 * @author Yves
 */
public class GebruikerBestaatException extends Exception
{
    /**
     * Default constructor
     */
    public GebruikerBestaatException()
    {
    }

    /**
     * Geeft een bericht mee aan de exception.
     *
     * @param message Bericht als String.
     */
    public GebruikerBestaatException(String message)
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
    public GebruikerBestaatException(String message, Throwable cause)
    {
        super(message, cause);
    }

    /**
     * Geet een object mee met info over de code die de exception heeft
     * veroorzaakt.
     *
     * @param cause object met info over de exception.
     */
    public GebruikerBestaatException(Throwable cause)
    {
        super(cause);
    }
}