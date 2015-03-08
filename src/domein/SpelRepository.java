package domein;

import persistentie.SpelMapper;

public class SpelRepository {

    private SpelMapper spelMapper = new SpelMapper();
    
	public String[] geefSpelNamen() {
		// TODO - implement SpelRepository.geefSpelNamen
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param spelnaam
         * @return
	 */
	public Spel geefSpel(String spelnaam) {
		return spelMapper.geefSpel(spelnaam);
	}
}