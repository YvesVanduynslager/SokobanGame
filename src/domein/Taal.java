package domein;

import java.util.ResourceBundle;

/**
 *
 * @author Michiel
 */
public class Taal
{
    private ResourceBundle resourceBundle;
    public Taal()
    {
        
    }
    
    public void setTaalKeuze(int locale)
    {
    switch (locale) //resourcebundle selecteren op basis van genomen keuze
        {
            case 1: this.resourceBundle = ResourceBundle.getBundle("resources.Resources_nl_BE");
            break;
            case 2: this.resourceBundle = ResourceBundle.getBundle("resources.Resources_en_UK");
            break;
            case 3: this.resourceBundle = ResourceBundle.getBundle("resources.Resources_fr_BE");
            break;
            default: this.resourceBundle = ResourceBundle.getBundle("resources.Resources_nl_BE");
            break; 
        }
    }
    
    public String getStringUitBundle(String key)
    {
        return resourceBundle.getString(key);
    }
}
