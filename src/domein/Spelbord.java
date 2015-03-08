package domein;

import java.util.*;

public class Spelbord {

	/*
        * Over de velden.
        * De buitenste Arraylist bevat een ArrayList van velden. Zo is het mogelijk om met 
        * velden.get(y).get(x) aan de hand van coördinaten een Veld op te halen uit de "lijst"
        * dit was veel moeilijker in een Collection.
        */
        ArrayList<ArrayList<Veld>> velden;
	Mannetje mannetje;
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
                        
                        mannetje.setyPositie(y+1);
                    }
                }
                break;
            default:
                break;
        }
        
    }
}