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
    //Spelbord bord = new Spelbord(velden);

    /**
     * 
     * @param naam makkelijk, gemiddeld, of moeilijk
     * @return 
     */
    public Spel geefSpel(String naam)
    {
        String[] elementen =
        {
            "muur", "doel", "kist", "mannetje", "veld"
        };

        for (String element : elementen)
        {
            Connectie connectie = new Connectie();

            PreparedStatement sqlStatement;
            String sqlString = "SELECT E.positieX, E.positieY "
                    + "FROM " + element + " E JOIN spelbord SB ON E.Spelbord_spelbordID = SB.spelbordID"
                    + " JOIN spel S ON SB.Spel_spelID = S.spelID"
                    + " WHERE S.spelNaam = '" + naam + "';";
//            
//            SELECT E.positieX, E.positieY, SB.spelbordID
//FROM muur E JOIN spelbord SB ON E.Spelbord_spelbordID = SB.spelbordID
//JOIN spel S ON SB.Spel_spelID = S.spelID
//WHERE S.spelNaam = 'makkelijk';

            try
            {
                sqlStatement = connectie.getDatabaseConnectie().prepareStatement(sqlString);
                ResultSet rs = sqlStatement.executeQuery();

                while (rs.next())
                {
                    switch(element)
                    {
                        case "muur" : velden[rs.getInt(1)][rs.getInt(2)] = new Muur(rs.getInt(1),rs.getInt(2));
                            break;
                        case "doel": velden[rs.getInt(1)][rs.getInt(2)] = new Veld(true);
                            break;
                        case "kist" : velden[rs.getInt(1)][rs.getInt(2)] = new Kist();
                            break;
                        case "mannetje" : velden[rs.getInt(1)][rs.getInt(2)] = new Mannetje();
                            break;
                        case "veld" : velden[rs.getInt(1)][rs.getInt(2)] = new Veld();
                            break;
                        default : velden[rs.getInt(1)][rs.getInt(2)] = new Veld();
                            break;
                    }
                }

                sqlStatement.close();
            }
            catch (SQLException sqlEx)
            {
                System.err.println("SQL fout" + sqlEx.getMessage() + "\n" + sqlEx.getSQLState());
            }
            finally
            {
                connectie.sluit();
            }
        }
        
        Spel spel1 = new Spel();
        spelborden[0] = new Spelbord(velden);
        spel1.voegSpelbordenToe(spelborden);
        return spel1;
    }
}
