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
 * Staat in voor het ophalen van een spel en bijhorende spelborden uit de
 * databank en deze op te slaan als een Spel-object.
 *
 * @author Yves
 */
public class SpelMapper
{
    private final int VELDEN_ARRAY_GROOTTE = 10;
    private Element[][] velden;
    private Mannetje mannetje;

    /**
     * Geeft de eerste ID terug van een spelbord binnen een spel. Adhv deze id zal er geloopt worden door de borden van een spel in de geefSpel()-methode.
     * @param naam
     * @return 
     */
    private int geefEersteSpelbordID(String naam)
    {
        int eersteID = 0;
        String sqlEerste = "SELECT MIN(spelbord.spelbordID) FROM spelbord JOIN spel ON spelbord.Spel_spelID = spel.spelID "
                + "WHERE spel.spelNaam = '" + naam + "';";

        Connectie connectie = new Connectie();
        PreparedStatement stmtEersteID;// = null;

        try
        {
            stmtEersteID = connectie.getDatabaseConnectie().prepareStatement(sqlEerste);
            ResultSet rs = stmtEersteID.executeQuery();

            while (rs.next())
            {
                eersteID = rs.getInt(1);
            }
            stmtEersteID.close();
        }
        catch (SQLException sqlEx)
        {
            System.err.println("SQL fout: " + sqlEx.getMessage() + "\n" + sqlEx.getSQLState());
        }
        finally
        {
            connectie.sluit();
        }

        return eersteID;
    }
    /**
     *
     * @param naam makkelijk, gemiddeld, of moeilijk
     * @return
     */
    public Spel geefSpel(String naam)
    {
        String spelbordNaam = "";
        final String[] ELEMENTEN =
        {
            "veld", "doel", "mannetje", "kist"
        };
        int eersteSpelbordID = this.geefEersteSpelbordID(naam);
        int aantalBorden = this.geefAantalSpelborden(naam) + eersteSpelbordID;

        List<Spelbord> borden = new ArrayList();

        for (int spelbordID = eersteSpelbordID; spelbordID < aantalBorden; spelbordID++)
        {
            velden = new Element[VELDEN_ARRAY_GROOTTE][VELDEN_ARRAY_GROOTTE];
            for (String element : ELEMENTEN)
            {
                Connectie connectie = new Connectie();
                PreparedStatement stmtBordenOphalen;

                String sqlBordenOphalen = "SELECT Element.positieX, Element.positieY, Spelbord.spelbordNaam "
                        + "FROM " + element + " Element JOIN spelbord Spelbord ON Element.Spelbord_spelbordID = Spelbord.spelbordID "
                        + "JOIN spel Spel ON Spelbord.Spel_spelID = Spel.spelID "
                        + "WHERE Spel.spelNaam = '" + naam + "' AND Spelbord.spelbordID = " + spelbordID + ";";

                try
                {
                    int x, y;

                    stmtBordenOphalen = connectie.getDatabaseConnectie().prepareStatement(sqlBordenOphalen);
                    ResultSet rs = stmtBordenOphalen.executeQuery();

                    while (rs.next())
                    {
                        x = rs.getInt(1);
                        y = rs.getInt(2);
                        spelbordNaam = rs.getString(3);

                        switch (element)
                        {
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
            //for-loop om te controleren op null-waarden in de velden array en die dan te vullen met een muur.
            //Ik doe dit bewust met een gewone for-loop zodat ik de teller kan gebruiken in de declaratie van de Muur.
            for (int x = 0; x < 10; x++)
            {
                for (int y = 0; y < 10; y++)
                {
                    if (velden[x][y] == null)
                    {
                        velden[x][y] = new Muur(x, y);
                    }
                }
            }
            borden.add(new Spelbord(spelbordNaam, velden, mannetje));
        }

        Spel spel = new Spel(naam, borden);
        return spel;
    }

    /**
     * Deze methode geeft het aantal spelborden terug die 1 enkel gekozen spel
     * bevat
     *
     * @param naam De naam van het spel waarvan het aantal spelborden geweten
     * moet zijn.
     * @return Het aantal spelborden van het gekozen spel.
     */
    private int geefAantalSpelborden(String naam)
    {
        int aantal = 0;
        String sqlCount = "SELECT COUNT(spelbord.spelbordID) FROM spelbord JOIN spel ON spelbord.Spel_spelID = spel.spelID "
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
        String sqlSpelNamen = "SELECT spelNaam FROM spel ORDER BY spelID;";

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