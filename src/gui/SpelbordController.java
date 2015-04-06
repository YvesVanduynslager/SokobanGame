/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Yves
 */
public class SpelbordController extends GridPane
{
    private DomeinController c;
    public SpelbordController(StartSchermController startscherm, DomeinController c)
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SpelScherm.fxml"));
//        loader.setRoot(this);
        loader.setController(this);
        try
        {
            loader.load();
        }
        catch (IOException ex)
        {
            throw new RuntimeException(ex);
        }
        this.c = c;
        List namen = c.geefSpelNamen();
        int aantalSpellen = namen.size();

        c.selecteerSpel("easy");
        c.startVolgendSpelbord();
        
        addElements();
    }
    
    private void addElements()
    {
        String[][] elementen = c.geefHuidigSpelbord();
        
        for (int rij = 0; rij < elementen.length; rij++)
        {
            for (int kolom = 0; kolom < elementen[rij].length; kolom++)
            {
                this.add(new Label(elementen[rij][kolom]), kolom, rij);
            }
        }
    }
}
