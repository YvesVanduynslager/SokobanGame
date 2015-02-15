package domein;

public class Speler {

	private String gebruikersnaam;
	private String wachtwoord;
	private boolean adminrechten = false;
	private String id;

	/**
	 * 
	 * @param gebruikersnaam
	 */
	public void setGebruikersnaam(String gebruikersnaam) {
		this.gebruikersnaam = gebruikersnaam;
	}

	/**
	 * 
	 * @param wachtwoord
	 */
	public void setWachtwoord(String wachtwoord) {
		this.wachtwoord = wachtwoord;
	}

	/**
	 * 
	 * @param adminrechten
	 */
	public void setAdminrechten(boolean adminrechten) {
		this.adminrechten = adminrechten;
	}

	public String getGebruikersnaam() {
		return this.gebruikersnaam;
	}

	public String getWachtwoord() {
		return this.wachtwoord;
	}

	public boolean heeftAdminrechten() {
            return this.adminrechten;
	}

	public String getID() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setID(String id) {
		this.id = id;
	}
}