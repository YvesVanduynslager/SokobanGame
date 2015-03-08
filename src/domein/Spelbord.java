package domein;

public class Spelbord
{

    Veld[][] velden;
    Mannetje mannetje;
    private int spelbordID;

    public Spelbord(Veld[][] bord)
    {
        this.velden = bord;
    }

    @Override
    public String toString()
    {
        String output = "";
        for (Veld rij[] : velden)
        {
            for (Veld cel : rij)
            {
                output += cel.toString();
            }
            output += "\n";
        }
        return output;
    }
}