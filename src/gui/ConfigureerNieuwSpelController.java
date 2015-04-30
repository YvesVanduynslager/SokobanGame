/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Michiel
 */
public class ConfigureerNieuwSpelController extends GridPane implements Refreshable
{
    private final DomeinController c;
    private final StartSchermController startScherm;
    private final ObservableList<ImageView> comboOptions = FXCollections.observableArrayList(
            new ImageView(new Image(getClass().getResourceAsStream("/images/muur.jpg"))),
            new ImageView(new Image(getClass().getResourceAsStream("/images/kist.jpg"))),
            new ImageView(new Image(getClass().getResourceAsStream("/images/veld.jpg"))),
            new ImageView(new Image(getClass().getResourceAsStream("/images/doel.jpg"))),
            new ImageView(new Image(getClass().getResourceAsStream("/images/mannetje.jpg")))
    );
    
    @FXML
    private GridPane grdSpelbord;
    @FXML
    private GridPane grdButtons;
    @FXML
    private Button btnJa;
    @FXML
    private Button btnNee;
    @FXML
    private TextArea txaInfo;
    @FXML
    private ComboBox<?> comboBox1_1;
    @FXML
    private ComboBox<?> comboBox2_1;
    @FXML
    private ComboBox<?> comboBox3_1;
    @FXML
    private ComboBox<?> comboBox4_1;
    @FXML
    private ComboBox<?> comboBox5_1;
    @FXML
    private ComboBox<?> comboBox2_4;
    @FXML
    private ComboBox<?> comboBox6_1;
    @FXML
    private ComboBox<?> comboBox7_1;
    @FXML
    private ComboBox<?> comboBox8_1;
    @FXML
    private ComboBox<?> comboBox1_2;
    @FXML
    private ComboBox<?> comboBox2_2;
    @FXML
    private ComboBox<?> comboBox1_3;
    @FXML
    private ComboBox<?> comboBox1_4;
    @FXML
    private ComboBox<?> comboBox1_5;
    @FXML
    private ComboBox<?> comboBox2_3;
    @FXML
    private ComboBox<?> comboBox2_5;
    @FXML
    private ComboBox<?> comboBox3_2;
    @FXML
    private ComboBox<?> comboBox4_2;
    @FXML
    private ComboBox<?> comboBox3_3;
    @FXML
    private ComboBox<?> comboBox4_3;
    @FXML
    private ComboBox<?> comboBox3_4;
    @FXML
    private ComboBox<?> comboBox4_4;
    @FXML
    private ComboBox<?> comboBox3_5;
    @FXML
    private ComboBox<?> comboBox4_5;
    @FXML
    private ComboBox<?> comboBox5_2;
    @FXML
    private ComboBox<?> comboBox5_3;
    @FXML
    private ComboBox<?> comboBox5_4;
    @FXML
    private ComboBox<?> comboBox6_2;
    @FXML
    private ComboBox<?> comboBox2_8;
    @FXML
    private ComboBox<?> comboBox1_6;
    @FXML
    private ComboBox<?> comboBox2_6;
    @FXML
    private ComboBox<?> comboBox3_6;
    @FXML
    private ComboBox<?> comboBox8_2;
    @FXML
    private ComboBox<?> comboBox2_7;
    @FXML
    private ComboBox<?> comboBox7_2;
    @FXML
    private ComboBox<?> comboBox6_3;
    @FXML
    private ComboBox<?> comboBox1_7;
    @FXML
    private ComboBox<?> comboBox1_8;
    @FXML
    private ComboBox<?> comboBox5_5;
    @FXML
    private ComboBox<?> comboBox3_7;
    @FXML
    private ComboBox<?> comboBox3_8;
    @FXML
    private ComboBox<?> comboBox4_6;
    @FXML
    private ComboBox<?> comboBox4_7;
    @FXML
    private ComboBox<?> comboBox4_8;
    @FXML
    private ComboBox<?> comboBox5_6;
    @FXML
    private ComboBox<?> comboBox5_7;
    @FXML
    private ComboBox<?> comboBox5_8;
    @FXML
    private ComboBox<?> comboBox6_4;
    @FXML
    private ComboBox<?> comboBox6_5;
    @FXML
    private ComboBox<?> comboBox6_6;
    @FXML
    private ComboBox<?> comboBox6_7;
    @FXML
    private ComboBox<?> comboBox8_4;
    @FXML
    private ComboBox<?> comboBox6_8;
    @FXML
    private ComboBox<?> comboBox7_3;
    @FXML
    private ComboBox<?> comboBox7_5;
    @FXML
    private ComboBox<?> comboBox7_4;
    @FXML
    private ComboBox<?> comboBox7_6;
    @FXML
    private ComboBox<?> comboBox7_8;
    @FXML
    private ComboBox<?> comboBox7_7;
    @FXML
    private ComboBox<?> comboBox8_3;
    @FXML
    private ComboBox<?> comboBox8_5;
    @FXML
    private ComboBox<?> comboBox8_6;
    @FXML
    private ComboBox<?> comboBox8_7;
    @FXML
    private ComboBox<?> comboBox8_8;

    public ConfigureerNieuwSpelController(StartSchermController startScherm, DomeinController c) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ConfigureerNieuwSpel.fxml"));
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
        this.startScherm = startScherm;
        
        voegNonEditablesToe();
        
    }

    private void voegNonEditablesToe(){
        grdSpelbord.add(new ImageView(new Image(getClass().getResourceAsStream("/images/muur.jpg"))), 0, 0);
        grdSpelbord.add(new ImageView(new Image(getClass().getResourceAsStream("/images/muur.jpg"))), 0, 1);
        grdSpelbord.add(new ImageView(new Image(getClass().getResourceAsStream("/images/muur.jpg"))), 0, 2);
        grdSpelbord.add(new ImageView(new Image(getClass().getResourceAsStream("/images/muur.jpg"))), 0, 3);
        grdSpelbord.add(new ImageView(new Image(getClass().getResourceAsStream("/images/muur.jpg"))), 0, 4);
        grdSpelbord.add(new ImageView(new Image(getClass().getResourceAsStream("/images/muur.jpg"))), 0, 5);
        grdSpelbord.add(new ImageView(new Image(getClass().getResourceAsStream("/images/muur.jpg"))), 0, 6);
        grdSpelbord.add(new ImageView(new Image(getClass().getResourceAsStream("/images/muur.jpg"))), 0, 7);
        grdSpelbord.add(new ImageView(new Image(getClass().getResourceAsStream("/images/muur.jpg"))), 0, 8);
        grdSpelbord.add(new ImageView(new Image(getClass().getResourceAsStream("/images/muur.jpg"))), 0, 9);
        
        grdSpelbord.add(new ImageView(new Image(getClass().getResourceAsStream("/images/muur.jpg"))), 9, 0);
        grdSpelbord.add(new ImageView(new Image(getClass().getResourceAsStream("/images/muur.jpg"))), 9, 1);
        grdSpelbord.add(new ImageView(new Image(getClass().getResourceAsStream("/images/muur.jpg"))), 9, 2);
        grdSpelbord.add(new ImageView(new Image(getClass().getResourceAsStream("/images/muur.jpg"))), 9, 3);
        grdSpelbord.add(new ImageView(new Image(getClass().getResourceAsStream("/images/muur.jpg"))), 9, 4);
        grdSpelbord.add(new ImageView(new Image(getClass().getResourceAsStream("/images/muur.jpg"))), 9, 5);
        grdSpelbord.add(new ImageView(new Image(getClass().getResourceAsStream("/images/muur.jpg"))), 9, 6);
        grdSpelbord.add(new ImageView(new Image(getClass().getResourceAsStream("/images/muur.jpg"))), 9, 7);
        grdSpelbord.add(new ImageView(new Image(getClass().getResourceAsStream("/images/muur.jpg"))), 9, 8);
        grdSpelbord.add(new ImageView(new Image(getClass().getResourceAsStream("/images/muur.jpg"))), 9, 9);
        
        grdSpelbord.add(new ImageView(new Image(getClass().getResourceAsStream("/images/muur.jpg"))), 1, 0);
        grdSpelbord.add(new ImageView(new Image(getClass().getResourceAsStream("/images/muur.jpg"))), 2, 0);
        grdSpelbord.add(new ImageView(new Image(getClass().getResourceAsStream("/images/muur.jpg"))), 3, 0);
        grdSpelbord.add(new ImageView(new Image(getClass().getResourceAsStream("/images/muur.jpg"))), 4, 0);
        grdSpelbord.add(new ImageView(new Image(getClass().getResourceAsStream("/images/muur.jpg"))), 5, 0);
        grdSpelbord.add(new ImageView(new Image(getClass().getResourceAsStream("/images/muur.jpg"))), 6, 0);
        grdSpelbord.add(new ImageView(new Image(getClass().getResourceAsStream("/images/muur.jpg"))), 7, 0);
        grdSpelbord.add(new ImageView(new Image(getClass().getResourceAsStream("/images/muur.jpg"))), 8, 0);
        
        grdSpelbord.add(new ImageView(new Image(getClass().getResourceAsStream("/images/muur.jpg"))), 1, 9);
        grdSpelbord.add(new ImageView(new Image(getClass().getResourceAsStream("/images/muur.jpg"))), 2, 9);
        grdSpelbord.add(new ImageView(new Image(getClass().getResourceAsStream("/images/muur.jpg"))), 3, 9);
        grdSpelbord.add(new ImageView(new Image(getClass().getResourceAsStream("/images/muur.jpg"))), 4, 9);
        grdSpelbord.add(new ImageView(new Image(getClass().getResourceAsStream("/images/muur.jpg"))), 5, 9);
        grdSpelbord.add(new ImageView(new Image(getClass().getResourceAsStream("/images/muur.jpg"))), 6, 9);
        grdSpelbord.add(new ImageView(new Image(getClass().getResourceAsStream("/images/muur.jpg"))), 7, 9);
        grdSpelbord.add(new ImageView(new Image(getClass().getResourceAsStream("/images/muur.jpg"))), 8, 9);
    }
    
    
    @Override
    public void refresh() {
        
    }
}
