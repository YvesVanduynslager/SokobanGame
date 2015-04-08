/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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
    //private boolean spelVoltooid;
    //private EventHandler<KeyEvent> keyEventHandler;

    @FXML
    private Button btnJa, btnNee;
    @FXML
    private TextArea txtVolgendSpelbord;
    @FXML
    private GridPane grdSpelbord;

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
        enableKeyHandler();

        btnJa.setOnAction(this::ja_Pressed);
        btnNee.setOnAction(this::nee_Pressed);
    }

    private void addElements()
    {
        String[][] elementen = c.geefHuidigSpelbord();

        for (int rij = 0; rij < elementen.length; rij++)
        {
            for (int kolom = 0; kolom < elementen[rij].length; kolom++)
            {
                grdSpelbord.add(new Label(elementen[rij][kolom]), kolom, rij);
            }
        }
    }

    public void refresh()
    {
        //controleEindeSpelbord();
        grdSpelbord.getChildren().clear();
        this.addElements();

        this.requestFocus();
    }

    private void controleEindeSpelbord()
    {
        if (c.huidigSpelbordVoltooid()) //spelbord voltooid
        {
            disableKeyHandler();
            //this.removeEventHandler(KeyEvent.KEY_PRESSED, keyEventHandler);

            if (c.geefAantalVoltooideBorden() == c.geefAantalSpelborden()) //spel voltooid
            {
                txtVolgendSpelbord.setVisible(true);
                txtVolgendSpelbord.setText("Spel voltooid!");
                btnJa.setVisible(false);
                btnNee.setVisible(false);
            }
            else //spel niet voltooid
            {
                btnJa.setVisible(true);
                btnNee.setVisible(true);
                txtVolgendSpelbord.setVisible(true);
                c.verhoogAantalVoltooideBorden();
            }
            startScherm.updateStatusLabel("" + c.geefAantalVoltooideBorden() + " van " + c.geefAantalSpelborden() + " spelborden voltooid in " + c.geefAantalZetten() + " zetten !");
        }
        else //spelbord niet voltooid
        {
            txtVolgendSpelbord.setVisible(false);
            btnJa.setVisible(false);
            btnNee.setVisible(false);
            startScherm.updateStatusLabel("" + c.geefAantalZetten() + " zetten!");
        }
    }
    
    private void disableKeyHandler()
    {
        this.setOnKeyPressed(null);
    }

    private void enableKeyHandler()
    {
        final EventHandler<KeyEvent> keyEventHandler = new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(final KeyEvent keyEvent)
            {
                if (keyEvent.getCode() == KeyCode.Z)
                {
                    c.beweeg(0);
                }
                else
                {
                    if (keyEvent.getCode() == KeyCode.Q)
                    {
                        c.beweeg(2);
                    }
                    else
                    {
                        if (keyEvent.getCode() == KeyCode.S)
                        {
                            c.beweeg(1);
                        }
                        else
                        {
                            if (keyEvent.getCode() == KeyCode.D)
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

                controleEindeSpelbord();
                refresh();

                keyEvent.consume();
            }
        };

        this.setOnKeyPressed(keyEventHandler);
    }

    private void ja_Pressed(ActionEvent event)
    {
        btnJa.setVisible(false);
        btnNee.setVisible(false);
        txtVolgendSpelbord.setVisible(false);
        c.startVolgendSpelbord();
        //controleEindeSpelbord();
        refresh();
        enableKeyHandler();
    }

    private void nee_Pressed(ActionEvent event)
    {
        startScherm.updateStatusLabel("");
        this.getChildren().clear();
    }
}

//https://docs.oracle.com/javafx/2/events/handlers.htm
