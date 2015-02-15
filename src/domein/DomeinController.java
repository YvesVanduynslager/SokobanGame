package domein;

public class DomeinController {

	private SpelerRepository spelerRepository;
        private String[] speler;
        private boolean spelerAdminrechten;
	/**
	 * 
	 * @param gebruikersnaam
	 * @param wachtwoord
	 */
	public void meldAan(String gebruikersnaam, String wachtwoord)
        {
            spelerRepository = new SpelerRepository();
            Speler sp = spelerRepository.geefSpeler(gebruikersnaam, wachtwoord);
            setSpeler(sp);
	}
        
        public String[] getSpeler()
        {
            return speler;
        }
        
        public boolean getSpelerAdminrechten()
        {
            return spelerAdminrechten;
        }
        
        private void setSpeler(Speler speler)
        {
            this.speler = new String[3];
            this.speler[0] = speler.getID(); //overbodig?
            this.speler[1] = speler.getGebruikersnaam(); //ZEKER NODIG IN GUI
            this.speler[2] = speler.getWachtwoord();
            this.spelerAdminrechten = speler.heeftAdminrechten();
        }
}