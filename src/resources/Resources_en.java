package resources;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Yves
 */
// Engels
public class Resources_en extends Resources
{
    @Override
    public Object handleGetObject(String key)
    {
        // don't need okKey, since parent level handles it.
        if (key.equals("cancelKey"))
        {
            return "Abbrechen";
        }
        return null;
    }

    @Override
    protected Set<String> handleKeySet()
    {
        return new HashSet<>(Arrays.asList("cancelKey"));
    }
}
