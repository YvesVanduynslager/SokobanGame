/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import java.io.IOException;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Spelbord.fxml"));
        loader.setRoot(this);
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
//        List namen = c.geefSpelNamen();
//        int aantalSpellen = namen.size();

        addElements();

        this.requestFocus();

        final EventHandler<KeyEvent> keyEventHandler
                = new EventHandler<KeyEvent>()
                {
                    @Override
                    public void handle(final KeyEvent keyEvent)
                    {
                        System.out.println("Test");
                        keyEvent.consume();

                    }
                };
        this.setOnKeyPressed(keyEventHandler);
//        this.addEventHandler(KeyEvent.KEY_RELEASED, keyEventHandler);
//        this.addEventHandler(KeyEvent.KEY_PRESSED, keyEventHandler);
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

//    private void beweeg(KeyEvent event)
//    {
//        System.out.println("Key pressed test");
//        
//    }
    public void refresh()
    {
        this.getChildren().clear();
        this.addElements();
    }
}

//https://docs.oracle.com/javafx/2/events/handlers.htm
