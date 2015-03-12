package persistentie;

import domein.Kist;
import domein.Mannetje;
import domein.Muur;
import domein.Spel;
import domein.Spelbord;
import domein.Veld;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Yves
 */
public class SpelMapper
{
    Veld[][] velden = new Veld[10][10];
    Spelbord[] spelborden = new Spelbord[1];
    Mannetje mannetje;
    //Spelbord bord = new Spelbord(velden);

    /**
     *
     * @param naam makkelijk, gemiddeld, of moeilijk
     * @return
     */
    public Spel geefSpel(String naam)
    {
        final String[] ELEMENTEN =
        {
            "muur", "veld", "doel", "mannetje", "kist"
        };

        for (String element : ELEMENTEN)
        {
            int veldVreemdeSleutel;
            int spelbordVreemdeSleutel;
            
            Connectie connectie = new Connectie();
            PreparedStatement statement = null;

            String sqlInstructie = "SELECT E.positieX, E.positieY, E.Spelbord_spelbordID FROM " + element + " E;";

            try
            {
                int x = 0, y = 0;
                statement = connectie.getDatabaseConnectie().prepareStatement(sqlInstructie);
                ResultSet rs = statement.executeQuery();
                
                while(rs.next())
                {
                    x = rs.getInt(1);
                    y = rs.getInt(2);
                    veldVreemdeSleutel = rs.getInt(3);
                    
                    switch(element)
                    {
                        case "muur" :
                            velden[x][y] = new Muur(x, y);
                            break;
                        case "veld" :
                            velden[x][y] = new Veld(x, y, false);
                            break;
                        case "doel" :
                            velden[x][y] = new Veld(x, y, true);
                            break;
                        case "mannetje" :
                            velden[x][y] = new Mannetje(x, y, false);
                            break;
                        case "kist" :
                            velden[x][y] = new Kist(x, y, false);
                            break;
                    }
                    //System.out.println(x + " " + y);
                }
                statement.close();
                
            }
            catch (SQLException sqlEx)
            {
                System.err.println("SQL fout: " + sqlEx.getMessage() + "\n" + sqlEx.getSQLState());
            }
            finally
            {
                connectie.sluit();
            }

        }

        Spel[] spellen = new Spel[1];
        spellen[0] = new Spel();
        spelborden[0] = new Spelbord(velden, mannetje);
        spellen[0].setSpelborden(spelborden);
        
        return spellen[0];
    }
}
