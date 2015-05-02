package exceptions;

/**
 * Klasse die erft van Exception, gebruikt als controle of een wachtwoord geldig is.
 *
 * @author Yves
 */
public final class WachtwoordOngeldigException extends IllegalArgumentException
{
    /**
     * Default constructor
     */
    public WachtwoordOngeldigException()
    {
    }

    /**
     * Geeft een bericht mee aan de exception.
     *
     * @param message Bericht als String.
     */
    public WachtwoordOngeldigException(String message)
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
    public WachtwoordOngeldigException(String message, Throwable cause)
    {
        super(message, cause);
    }

    /**
     * Geet een object mee met info over de code die de exception heeft
     * veroorzaakt.
     *
     * @param cause object met info over de exception.
     */
    public WachtwoordOngeldigException(Throwable cause)
    {
        super(cause);
    }
}