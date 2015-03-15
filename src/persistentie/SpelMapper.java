package persistentie;

import domein.Kist;
import domein.Mannetje;
import domein.Muur;
import domein.Spel;
import domein.Spelbord;
import domein.Element;
import domein.Veld;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Staat in voor het ophalen van een spel en bijhorende spelborden uit de databank en deze op te slaan als een Spel-object.
 * @author Yves
 */
public class SpelMapper
{
    Element[][] velden = new Element[10][10];
    Mannetje mannetje;

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
        final int aantalBorden = this.geefAantalSpelborden(naam);

        List<Spelbord> borden = new ArrayList();
        
        for (int spelbordID = 1; spelbordID <= aantalBorden; spelbordID++)
        {
            for (String element : ELEMENTEN)
            {
                Connectie connectie = new Connectie();
                PreparedStatement stmtBordenOphalen;// = null;

                String sqlBordenOphalen = "SELECT Element.positieX, Element.positieY, Element.Spelbord_spelbordID, "
                        + "Spelbord.spelbordID, Spelbord.spelbordNaam, Spelbord.Spel_spelID, Spel.spelID, Spel.spelNaam "
                        + "FROM " + element + " Element JOIN spelbord Spelbord ON Element.Spelbord_spelbordID = Spelbord.spelbordID "
                        + "JOIN spel Spel ON Spelbord.Spel_spelID = Spel.spelID "
                        + "WHERE Spel.spelNaam = '" + naam + "' AND Spelbord.spelbordID = " + spelbordID + ";";

                try
                {
                    //int x = 0, y = 0;
                    int x, y;
                    stmtBordenOphalen = connectie.getDatabaseConnectie().prepareStatement(sqlBordenOphalen);
                    ResultSet rs = stmtBordenOphalen.executeQuery();

                    while (rs.next())
                    {
                        x = rs.getInt(1);
                        y = rs.getInt(2);
                        
                        switch (element)
                        {
                            case "muur":
                                velden[x][y] = new Muur(x, y);
                                break;
                            case "veld":
                                velden[x][y] = new Veld(x, y, false);
                                break;
                            case "doel":
                                velden[x][y] = new Veld(x, y, true);
                                break;
                            case "mannetje":
                                mannetje = new Mannetje(x, y, false);
                                velden[x][y] = new Mannetje(x, y, false);
                                break;
                            case "kist":
                                velden[x][y] = new Kist(x, y, false);
                                break;
                        }
                    }
                    stmtBordenOphalen.close();
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
            borden.add(new Spelbord(velden, mannetje));
        }

        Spel spel = new Spel(naam, borden);
        return spel;
    }

    /**
     * Deze methode geeft het aantal spelborden terug die 1 enkel gekozen spel bevat
     * @param naam De naam van het spel waarvan het aantal spelborden geweten moet zijn.
     * @return Het aantal spelborden van het gekozen spel.
     */
    private int geefAantalSpelborden(String naam)
    {
        int aantal = 0;
        String sqlCount = "SELECT Max(spelbord.spelbordID) FROM spelbord JOIN spel ON spelbord.Spel_spelID = spel.spelID "
                + "WHERE spel.spelNaam = '" + naam + "';";

        Connectie connectie = new Connectie();
        PreparedStatement stmtAantalBorden;// = null;

        try
        {
            stmtAantalBorden = connectie.getDatabaseConnectie().prepareStatement(sqlCount);
            ResultSet rs = stmtAantalBorden.executeQuery();

            while (rs.next())
            {
                aantal = rs.getInt(1);
            }
            stmtAantalBorden.close();
        }
        catch (SQLException sqlEx)
        {
            System.err.println("SQL fout: " + sqlEx.getMessage() + "\n" + sqlEx.getSQLState());
        }
        finally
        {
            connectie.sluit();
        }

        return aantal;
    }
    
    public List<String> geefSpelNamen()
    {
        List<String> spelNamen = new ArrayList();
        String sqlSpelNamen = "SELECT spelNaam FROM spel;";
        
        Connectie connectie = new Connectie();
        PreparedStatement stmtSpelNamen;
        
        try
        {
            stmtSpelNamen = connectie.getDatabaseConnectie().prepareStatement(sqlSpelNamen);
            ResultSet rs = stmtSpelNamen.executeQuery();
            
            while (rs.next())
            {
                spelNamen.add(rs.getString(1));
            }
            stmtSpelNamen.close();
        }
        catch (SQLException sqlEx)
        {
            System.err.println("SQL fout: " + sqlEx.getMessage() + "\n" + sqlEx.getSQLState());
        }
        finally
        {
            connectie.sluit();
        }
        return spelNamen;
    }
}
