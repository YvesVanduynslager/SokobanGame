package domein;

public class Spel {

	Spelbord[] spelbord = new Spelbord[1];
        
	private int spelID;
	private String spelNaam;
	private boolean nogSpelborden;

        public Spel()
        {
            
        }
        
        public Spel(Spelbord[] borden)
        {
            this.spelbord = borden;
        }
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
        
        public void voegSpelbordenToe(Spelbord[] spelbord)
        {
            this.spelbord = spelbord;
        }
        
        public Spelbord[] geefSpelborden()
        {
            return this.spelbord;
        }
}
