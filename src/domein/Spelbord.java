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
	/*
        * Over de velden.
        * De buitenste Arraylist bevat een ArrayList van velden. Zo is het mogelijk om met 
        * velden.get(y).get(x) aan de hand van coördinaten een Veld op te halen uit de "lijst"
        * dit was veel moeilijker in een Collection.
        */
        private ArrayList<ArrayList<Veld>> velden;
	private Mannetje mannetje;
	private int spelbordID;

    public Spelbord(ArrayList<ArrayList<Veld>> velden, Mannetje mannetje, int spelbordID) {
        this.velden = velden;
        this.mannetje = mannetje;
        this.spelbordID = spelbordID;
    }

    public ArrayList<ArrayList<Veld>> getVelden() {
        return velden;
    }

    public void setVelden(ArrayList<ArrayList<Veld>> velden) {
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
    
    public void verplaatsMannetje(String richting){
        int x = mannetje.getxPositie();
        int y = mannetje.getyPositie();
        
        switch (richting){
            case "up":
                if (velden.get(y).get(x-1) instanceof Veld && x-1>0){
                    mannetje.setxPositie(x-1);
                }
                else if (velden.get(y).get(x-1) instanceof Kist && x-1>0){
                    if (velden.get(y).get(x-2) instanceof Veld && x-2>0){
                        boolean doel = velden.get(y).get(x-2).isIsDoel();
                        ArrayList<Veld> tijdelijkeRij = velden.get(y);
                        Kist tijdelijkeKist = new Kist(x-2, y);
                        tijdelijkeKist.setIsDoel(doel);
                        tijdelijkeRij.add(x-2, tijdelijkeKist);
                        Veld tijdelijkVeld = new Veld (x-1, y);
                        tijdelijkeRij.add(x-1, tijdelijkVeld);
                        velden.add(y, tijdelijkeRij);
                        
                        mannetje.setxPositie(x-1);
                    }
                }
                break;
            case "down":
                if (velden.get(y).get(x+1) instanceof Veld && x+1<10){
                    mannetje.setxPositie(x+1);
                }
                else if (velden.get(y).get(x+1) instanceof Kist && x+1<10){
                    if (velden.get(y).get(x+2) instanceof Veld && x+2<10){
                        boolean doel = velden.get(y).get(x+2).isIsDoel();
                        ArrayList<Veld> tijdelijkeRij = velden.get(y);
                        Kist tijdelijkeKist = new Kist(x+2, y);
                        tijdelijkeKist.setIsDoel(doel);
                        tijdelijkeRij.add(x+2, tijdelijkeKist);
                        Veld tijdelijkVeld = new Veld (x+1, y);
                        tijdelijkeRij.add(x+1, tijdelijkVeld);
                        velden.add(y, tijdelijkeRij);
                        
                        mannetje.setxPositie(x+1);
                    }
                }
                break;
            case "left":
                if (velden.get(y-1).get(x) instanceof Veld && y-1>0){
                    mannetje.setyPositie(y-1);
                }
                else if (velden.get(y-1).get(x) instanceof Kist && y-1>0){
                    if (velden.get(y-2).get(x) instanceof Veld && y-2>0){
                        boolean doel = velden.get(y-2).get(x).isIsDoel();
                        ArrayList<Veld> tijdelijkeRij1 = velden.get(y-1);
                        ArrayList<Veld> tijdelijkeRij2 = velden.get(y-2);
                        Kist tijdelijkeKist = new Kist(x, y-2);
                        tijdelijkeKist.setIsDoel(doel);
                        tijdelijkeRij2.add(x, tijdelijkeKist);
                        Veld tijdelijkVeld = new Veld (x, y-1);
                        tijdelijkeRij1.add(x, tijdelijkVeld);
                        velden.add(y-1, tijdelijkeRij1);
                        velden.add(y-2, tijdelijkeRij2);
                        
                        mannetje.setyPositie(y-1);
                    }
                }
                break;
            case "right":
                if (velden.get(y+1).get(x) instanceof Veld && y-1<10){
                    mannetje.setyPositie(y+1);
                }
                else if (velden.get(y+1).get(x) instanceof Kist && y+1<10){
                    if (velden.get(y+2).get(x) instanceof Veld && y+2<10){
                        boolean doel = velden.get(y+2).get(x).isIsDoel();
                        ArrayList<Veld> tijdelijkeRij1 = velden.get(y+1);
                        ArrayList<Veld> tijdelijkeRij2 = velden.get(y+2);
                        Kist tijdelijkeKist = new Kist(x, y+2);
                        tijdelijkeKist.setIsDoel(doel);
                        tijdelijkeRij2.add(x, tijdelijkeKist);
                        Veld tijdelijkVeld = new Veld (x, y+1);
                        tijdelijkeRij1.add(x, tijdelijkVeld);
                        velden.add(y+1, tijdelijkeRij1);
                        velden.add(y+2, tijdelijkeRij2);
                        
                        mannetje.setyPositie(y+1);
                    }
                }
                break;
            default:
                break;
        }
        
    }
}
