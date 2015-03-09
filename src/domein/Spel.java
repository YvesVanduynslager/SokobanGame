package domein;

import java.util.*;

public class Spel {

	private Spelbord[] spelborden;
	private int spelID;
	private String spelNaam;
	private boolean nogSpelborden;

	public Spelbord geefVolgendSpelbord() {
		// TODO - implement Spel.geefVolgendSpelbord
		throw new UnsupportedOperationException();
	}

	public int geefAantalVoltooideBorden() {
		// TODO - implement Spel.geefAantalVoltooideBorden
		throw new UnsupportedOperationException();
	}

	public int geefAantalSpelborden() {
		// TODO - implement Spel.geefAantalSpelborden
		throw new UnsupportedOperationException();
	}

    public void voegSpelbordenToe(Spelbord[] spelborden)
    {
        this.spelborden = spelborden;
    }
    
    public Spelbord[] geefSpelborden()
    {
        return this.spelborden;
    }

}