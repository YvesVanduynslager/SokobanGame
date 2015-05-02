/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import exceptions.SpelNaamBestaatException;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
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
    private Button btnSpelbordKlaar, btnRegistreerSpel, btnOK;
    @FXML
    private TextField txtSpelnaam;
    @FXML
    private ChoiceBox chbKeuze;

    private Label[][] gridLabels;
    private final DomeinController c;
    private final StartSchermController startscherm;

    public ConfigNieuwSpelController(StartSchermController startscherm, DomeinController c)
    {
        init();

        this.c = c;
        this.startscherm = startscherm;

        refresh();

        btnOK.setOnAction(this::btnOK_clicked);
        btnSpelbordKlaar.setOnAction(this::btnSpelbordKlaar_clicked);
        btnRegistreerSpel.setOnAction(this::btnRegistreerSpel_clicked);

        chbKeuze.setItems(FXCollections.observableArrayList("Muur", "Mannetje", "Veld", "Doel", "Kist"));
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
        gridLabels = new Label[10][10];
        String[][] elementen = c.geefHuidigSpelbord();

        for (int rij = 0; rij < elementen.length; rij++)
        {
            for (int kolom = 0; kolom < elementen[rij].length; kolom++)
            {
                switch (elementen[rij][kolom])
                {
                    case "#":
                        gridLabels[rij][kolom] = new Label("#");
                        gridLabels[rij][kolom].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/muur.jpg"))));
                        grdSpelbord.add(gridLabels[rij][kolom], kolom, rij);
                        break;
                    case "K":
                        gridLabels[rij][kolom] = new Label("K");
                        gridLabels[rij][kolom].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/kist.jpg"))));
                        grdSpelbord.add(gridLabels[rij][kolom], kolom, rij);
                        break;
                    case ".":
                        gridLabels[rij][kolom] = new Label(".");
                        gridLabels[rij][kolom].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/veld.jpg"))));
                        grdSpelbord.add(gridLabels[rij][kolom], kolom, rij);
                        break;
                    case "D":
                        gridLabels[rij][kolom] = new Label("D");
                        gridLabels[rij][kolom].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/doel.jpg"))));
                        grdSpelbord.add(gridLabels[rij][kolom], kolom, rij);
                        break;
                    case "O":
                        gridLabels[rij][kolom] = new Label("O");
                        gridLabels[rij][kolom].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/mannetje.jpg"))));
                        grdSpelbord.add(gridLabels[rij][kolom], kolom, rij);
                        break;
                    default:
                        grdSpelbord.add(new Label(elementen[rij][kolom]), kolom, rij);
                        break;
                }
                gridLabels[rij][kolom].setOnMouseClicked(this::vakje_clicked);
            }
        }
    }

    private void vakje_clicked(MouseEvent event)
    {
        //Hier controleren welk item is geselecteerd (muur of kist of..)

        for (int rij = 0; rij < gridLabels.length; rij++)
        {
            for (int kolom = 0; kolom < gridLabels[rij].length; kolom++)
            {
                if (event.getSource() == gridLabels[rij][kolom])
                {
                    //c.plaatsElement(type, rij, kolom);
                   //gridLabels[rij][kolom] = new Label(type);
//                    gridLabels[rij][kolom].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/muur.jpg"))));
//                    grdSpelbord.add(gridLabels[rij][kolom], kolom, rij);
                    System.out.println("label " + rij + " " + kolom + " clicked");
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
