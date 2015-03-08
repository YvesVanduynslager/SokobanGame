package domein;

import java.util.*;

public class Spelbord {

	Collection<Veld> velden;
	Mannetje mannetje;
	private int spelbordID;

    public Spelbord(Collection<Veld> velden, Mannetje mannetje, int spelbordID) {
        this.velden = velden;
        this.mannetje = mannetje;
        this.spelbordID = spelbordID;
    }

    public Collection<Veld> getVelden() {
        return velden;
    }

    public void setVelden(Collection<Veld> velden) {
        this.velden = velden;
    }

    public Mannetje getMannetje() {
        return mannetje;
    }

    public void setMannetje(Mannetje mannetje) {
        this.mannetje = mannetje;
    }

    public int getSpelbordID() {
        return spelbordID;
    }

    public void setSpelbordID(int spelbordID) {
        this.spelbordID = spelbordID;
    }

}