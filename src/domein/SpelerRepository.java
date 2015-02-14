package domein;

import persistentie.*;

public class SpelerRepository {

	private SpelerMapper spelerMapper;

	/**
	 * 
	 * @param gebruikersnaam
	 * @param wachtwoord
     * @return 
	 */
	public Speler geefSpeler(String gebruikersnaam, String wachtwoord) {
		spelerMapper = new SpelerMapper();
                return spelerMapper.zoek(gebruikersnaam, wachtwoord);                
	}
}