/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import exceptions.SpelNaamBestaatException;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Yves
 */
public class ConfigNieuwSpelController extends GridPane implements Refreshable
{
    @FXML
    private GridPane grdSpelbord;
    @FXML
    private GridPane grdButtons;
    @FXML
    private Button btnSpelbordKlaar;
    @FXML
    private Button btnRegistreerSpel;
    @FXML
    private Group grpKeuzes;
    @FXML
    private RadioButton rdbMannetje;
    @FXML
    private RadioButton rdbVeld;
    @FXML
    private RadioButton rdbDoel;
    @FXML
    private RadioButton rdbMuur;
    @FXML
    private RadioButton rdbKist;
    @FXML
    private TextArea txaInfo;
    @FXML
    private TextField txtSpelnaam;
    @FXML
    private Button btnOK;

    private final Label[][] gridLabels;
    private final DomeinController c;
    private final StartSchermController startscherm;

    public ConfigNieuwSpelController(StartSchermController startscherm, DomeinController c)
    {
        init();

        this.c = c;
        this.startscherm = startscherm;

        refresh();

        gridLabels = new Label[10][10];

        btnOK.setOnAction(this::btnOK_clicked);
        btnSpelbordKlaar.setOnAction(this::btnSpelbordKlaar_clicked);
        btnRegistreerSpel.setOnAction(this::btnRegistreerSpel_clicked);
    }

    @Override
    public final void init()
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ConfigNieuwSpel.fxml"));
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
    }

    @Override
    public final void refresh()
    {
        //this.wisBord();
        //this.tekenBord();
    }

    public final void wisBord()
    {
        grdSpelbord.getChildren().clear();
    }

    private void tekenBord()
    {
        String[][] elementen = c.geefHuidigSpelbord();

        for (int rij = 0; rij < elementen.length; rij++)
        {
            for (int kolom = 0; kolom < elementen[rij].length; kolom++)
            {
                gridLabels[rij][kolom].setOnMouseClicked(this::vakje_clicked);

                switch (elementen[rij][kolom])
                {
                    case "#":
                        gridLabels[rij][kolom].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/muur.jpg"))));
                        grdSpelbord.add(gridLabels[rij][kolom], kolom, rij);
                        break;
                    case "K":
                        gridLabels[rij][kolom].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/kist.jpg"))));
                        grdSpelbord.add(gridLabels[rij][kolom], kolom, rij);
                        break;
                    case ".":
                        gridLabels[rij][kolom].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/veld.jpg"))));
                        grdSpelbord.add(gridLabels[rij][kolom], kolom, rij);
                        break;
                    case "D":
                        gridLabels[rij][kolom].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/doel.jpg"))));
                        grdSpelbord.add(gridLabels[rij][kolom], kolom, rij);
                        break;
                    case "O":
                        gridLabels[rij][kolom].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/mannetje.jpg"))));
                        grdSpelbord.add(gridLabels[rij][kolom], kolom, rij);
                        break;
                    default:
                        grdSpelbord.add(new Label(elementen[rij][kolom]), kolom, rij);
                        break;
                }

            }
        }
    }

    private void vakje_clicked(MouseEvent event)
    {
        String type = "";
        if (rdbMannetje.isSelected())
        {
            type = "mannetje";
        }
        else
        {
            if (rdbVeld.isSelected())
            {
                type = "veld";
            }
            else
            {
                if (rdbDoel.isSelected())
                {
                    type = "doel";
                }
                else
                {
                    if (rdbMuur.isSelected())
                    {
                        type = "muur";
                    }
                    else
                    {
                        if (rdbKist.isSelected())
                        {
                            type = "kist";
                        }
                    }
                }
            }
        }
        for (int rij = 0; rij < gridLabels.length; rij++)
        {
            for (int kolom = 0; kolom < gridLabels[rij].length; kolom++)
            {
                if (event.getSource() == gridLabels[rij][kolom])
                {
                    c.plaatsElement(type, rij, kolom);
                }
            }
        }
    }

    private void btnOK_clicked(ActionEvent event)
    {
        c.configureerNieuwSpel(txtSpelnaam.getText());
        tekenBord();
    }

    private void btnSpelbordKlaar_clicked(ActionEvent event)
    {
        c.maakLeegSpelbord();
        tekenBord();
    }

    private void btnRegistreerSpel_clicked(ActionEvent event)
    {
        try
        {
            c.registreerCustomSpel();
        }
        catch (SpelNaamBestaatException snbe)
        {
            startscherm.updateStatusLabel("Spelnaam bestaat al! Geef een andere naam in!");
        }
    }
}
