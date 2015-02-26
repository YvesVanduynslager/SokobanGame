package exceptions;

/**
 *
 * @author Yves
 */
public class GebruikerBestaatException extends Exception
{
    public GebruikerBestaatException()
    {
        
    }
    
    public GebruikerBestaatException(String message)
    {
        super(message);
    }
    
    public GebruikerBestaatException(String message, Throwable cause)
    {
        super(message, cause);
    }
    
    public GebruikerBestaatException(Throwable cause)
    {
        super(cause);
    }
}
