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
    private StartSchermController startScherm;

    public SpelbordController(StartSchermController startScherm, DomeinController c)
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
        this.startScherm = startScherm;
        this.c = c;
        
        

        addElements();
        installEventHandler();
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

    public void refresh()
    {
        this.getChildren().clear();
        
        this.addElements();
        this.setGridLinesVisible(true);
    }
    
    private void installEventHandler()
    {
        final EventHandler<KeyEvent> keyEventHandler
                = new EventHandler<KeyEvent>()
                {
                    @Override
                    public void handle(final KeyEvent keyEvent)
                    {
                        if(keyEvent.getCode() == KeyCode.Z)
                        {
                            c.beweeg(0);
                        }
                        else
                        {
                            if(keyEvent.getCode() == KeyCode.Q)
                            {
                                c.beweeg(2);
                            }
                            else
                            {
                                if(keyEvent.getCode() == KeyCode.S)
                                {
                                    c.beweeg(1);
                                }
                                else
                                {
                                    if(keyEvent.getCode() == KeyCode.D)
                                    {
                                        c.beweeg(3);
                                    }
                                    else
                                    {
                                        startScherm.updateStatusLabel("Ongeldige invoer");
                                    }
                                }
                            }
                        }
                        refresh();
                        keyEvent.consume();
                    }
                };
        
        this.setOnKeyPressed(keyEventHandler);
    }
}

//https://docs.oracle.com/javafx/2/events/handlers.htm
