package exceptions;

/**
 *
 * @author Yves
 */
public class GebruikerNietGevondenException extends Exception
{
    public GebruikerNietGevondenException()
    {
    }
    
    public GebruikerNietGevondenException(String message)
    {
        super(message);
    }
    
    public GebruikerNietGevondenException(String message, Throwable cause)
    {
        super(message, cause);
    }
    
    public GebruikerNietGevondenException(Throwable cause)
    {
        super(cause);
    }
}
