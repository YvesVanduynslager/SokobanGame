package resources;

import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

/**
 *
 * @author Yves
 */
// default (Engels)
public class Resources extends ResourceBundle
{
    @Override
    public Object handleGetObject(String key)
    {
        if (key.equals("okKey"))
        {
            return "Ok";
        }
        if (key.equals("cancelKey"))
        {
            return "Cancel";
        }
        return null;
    }

    @Override
    public Enumeration<String> getKeys()
    {
        return Collections.enumeration(keySet());
    }

     // Overrides handleKeySet() so that the getKeys() implementation
    // can rely on the keySet() value.
    @Override
    protected Set<String> handleKeySet()
    {
        return new HashSet<>(Arrays.asList("okKey", "cancelKey"));
    }
}