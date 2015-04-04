/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Yves
 */
public class SpeelSpelSchermController extends GridPane
{
    private DomeinController c;
    public SpeelSpelSchermController(StartSchermController startscherm, DomeinController c)
    {
        this.c = c;
        List namen = c.geefSpelNamen();
        int aantalSpellen = namen.size();

        c.selecteerSpel("easy");
        c.startVolgendSpelbord();
        
        addElements();
    }
    
    private void addElements()
    {
        for (int rij = 0; rij < c.geefHuidigSpelbord().length; rij++)
        {
            for (int celInRij = 0; celInRij < c.geefHuidigSpelbord()[0].length; celInRij++)
            {
                this.add(new Label(c.geefHuidigSpelbord()[rij][celInRij]), rij, celInRij);
            }
        }
    }
}
