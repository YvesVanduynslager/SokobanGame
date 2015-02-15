package domein;

public class DomeinController
{

    private SpelerRepository spelerRepository;
    private String[] spelerString;
    private boolean spelerAdminrechten;
    private Speler speler;

    /**
     *
     * @param gebruikersnaam
     * @param wachtwoord
     */
    public void meldAan(String gebruikersnaam, String wachtwoord)
    {
        spelerRepository = new SpelerRepository();
        speler = spelerRepository.geefSpeler(gebruikersnaam, wachtwoord);
        setSpeler(speler);
    }

    public String[] getSpeler()
    {
        return spelerString;
    }

    public boolean getSpelerAdminrechten()
    {
        return spelerAdminrechten;
    }

    /**
     *
     * @param speler
     */
    public void setSpeler(Speler speler)
    {
        this.spelerString = new String[3];
        this.spelerString[0] = speler.getID(); //overbodig?
        this.spelerString[1] = speler.getGebruikersnaam(); //ZEKER NODIG IN GUI
        this.spelerString[2] = speler.getWachtwoord();
        this.spelerAdminrechten = speler.heeftAdminrechten();
    }
}
