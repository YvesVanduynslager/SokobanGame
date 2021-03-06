package gui;

import domein.DomeinController;
import exceptions.OngeldigAantalDoelenException;
import exceptions.OngeldigAantalKistenException;
import exceptions.OngeldigAantalMannetjesException;
import exceptions.OngelijkAantalDoelenKistenException;
import exceptions.SpelNaamBestaatException;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
    private Label lblSpelNaam;
    @FXML
    private RadioButton rdbMuur, rdbVeld, rdbDoel, rdbMannetje, rdbKist;
    @FXML
    private ToggleGroup elementGroup;

    private Label[][] gridLabels;
    private final DomeinController c;
    private final StartSchermController startScherm;

    private final Image IMG_MUUR = new Image(getClass().getResourceAsStream("/images/muur.jpg"));
    private final Image IMG_KIST = new Image(getClass().getResourceAsStream("/images/kist.jpg"));
    private final Image IMG_DOEL = new Image(getClass().getResourceAsStream("/images/doel.jpg"));
    private final Image IMG_MANNETJE = new Image(getClass().getResourceAsStream("/images/mannetje.jpg"));
    private final Image IMG_VELD = new Image(getClass().getResourceAsStream("/images/veld.jpg"));

    /**
     * Initialisatie van het configureer-nieuw-spel-scherm.
     * 
     * @param startScherm parent-component.
     * @param c DomeinController-object.
     */
    public ConfigNieuwSpelController(StartSchermController startScherm, DomeinController c)
    {
        init();

        this.c = c;
        this.startScherm = startScherm;

        refresh();

        grdSpelbord.getStyleClass().add("border");
        txtSpelnaam.getStyleClass().add("border");

        btnOK.setOnAction(this::btnOK_clicked);
        btnSpelbordKlaar.setOnAction(this::btnSpelbordKlaar_clicked);
        btnRegistreerSpel.setOnAction(this::btnRegistreerSpel_clicked);

        this.toonRadioButtons(false);
        rdbMannetje.setSelected(true);
    }

    /**
     * Initialisatie van fxml-root.
     */
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

    /**
     * Stelt componenten opnieuw in adhv taal-resources.
     */
    @Override
    public final void refresh()
    {
        lblSpelNaam.setText(c.getString("configureer.spelnaam"));
        btnSpelbordKlaar.setText(c.getString("configureer.klaar"));
        btnRegistreerSpel.setText(c.getString("configureer.registreer"));

        rdbMuur.setText(c.getString("muur"));
        rdbVeld.setText(c.getString("veld"));
        rdbDoel.setText(c.getString("doel"));
        rdbMannetje.setText(c.getString("mannetje"));
        rdbKist.setText(c.getString("kist"));
    }

    /**
     * Stelt de zichtbaarheid van de radiobuttons in.
     * @param zichtbaar true voor zichtbaar, false voor onzichtbaar.
     */
    private void toonRadioButtons(boolean zichtbaar)
    {
        rdbMuur.setVisible(zichtbaar);
        rdbVeld.setVisible(zichtbaar);
        rdbDoel.setVisible(zichtbaar);
        rdbMannetje.setVisible(zichtbaar);
        rdbKist.setVisible(zichtbaar);
    }

    /**
     * Verwijdert het bord van het scherm
     */
    public final void wisBord()
    {
        grdSpelbord.getChildren().clear();
    }

    /**
     * Tekent het bord op het scherm.
     */
    private void tekenBord()
    {
        String[][] elementen = c.spelbordTo2DString();
        gridLabels = new Label[elementen.length][elementen[0].length];

        for (int rij = 0; rij < elementen.length; rij++)
        {
            for (int kolom = 0; kolom < elementen[rij].length; kolom++)
            {
                switch (elementen[rij][kolom])
                {
                    case "#":
                        gridLabels[rij][kolom] = new Label("#");
                        gridLabels[rij][kolom].setGraphic(new ImageView(IMG_MUUR));
                        grdSpelbord.add(gridLabels[rij][kolom], kolom, rij);
                        break;
                    case "K":
                        gridLabels[rij][kolom] = new Label("K");
                        gridLabels[rij][kolom].setGraphic(new ImageView(IMG_KIST));
                        grdSpelbord.add(gridLabels[rij][kolom], kolom, rij);
                        break;
                    case ".":
                        gridLabels[rij][kolom] = new Label(".");
                        gridLabels[rij][kolom].setGraphic(new ImageView(IMG_VELD));
                        grdSpelbord.add(gridLabels[rij][kolom], kolom, rij);
                        break;
                    case "D":
                        gridLabels[rij][kolom] = new Label("D");
                        gridLabels[rij][kolom].setGraphic(new ImageView(IMG_DOEL));
                        grdSpelbord.add(gridLabels[rij][kolom], kolom, rij);
                        break;
                    case "O":
                        gridLabels[rij][kolom] = new Label("O");
                        gridLabels[rij][kolom].setGraphic(new ImageView(IMG_MANNETJE));
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

    /**
     * Als er op een vakje geklikt werd.
     * @param event
     */
    private void vakje_clicked(MouseEvent event)
    {
        String type = "mannetje";
        ImageView imgElement = null;

        if (rdbMuur.isSelected())
        {
            type = "muur";
            imgElement = new ImageView(IMG_MUUR);
        }
        else
        {
            if (rdbVeld.isSelected())
            {
                type = "veld";
                imgElement = new ImageView(IMG_VELD);
            }
            else
            {
                if (rdbDoel.isSelected())
                {
                    type = "doel";
                    imgElement = new ImageView(IMG_DOEL);
                }
                else
                {
                    if (rdbKist.isSelected())
                    {
                        type = "kist";
                        imgElement = new ImageView(IMG_KIST);
                    }
                    else
                    {
                        if (rdbMannetje.isSelected())
                        {
                            type = "mannetje";
                            imgElement = new ImageView(IMG_MANNETJE);
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
                    gridLabels[rij][kolom].setGraphic(imgElement);
                    System.out.println("label " + rij + " " + kolom + " clicked");
                    break;
                }
            }
        }

        this.wisBord();
        this.tekenBord();
    }

    /**
     * Als er op btnOK geklikt werd
     * @param event
     */
    private void btnOK_clicked(ActionEvent event)
    {
        c.configureerNieuwSpel(txtSpelnaam.getText());
        btnSpelbordKlaar.setDisable(false);

        btnOK.setDisable(true);
        this.toonRadioButtons(true);

        this.wisBord();
        this.tekenBord();
    }

    /**
     * Als er op btnSpelbordKlaar geklikt werd
     * @param event
     */
    private void btnSpelbordKlaar_clicked(ActionEvent event)
    {
        try
        {
            c.registreerCustomSpelbord();

            c.maakLeegSpelbord();
            btnRegistreerSpel.setDisable(false);
            startScherm.updateStatusLabel(c.geefAantalSpelborden() + " " + c.getString("configureer.registreer.succes2"));

            this.wisBord();
            this.tekenBord();
        }
        catch (OngeldigAantalDoelenException e)
        {
            System.err.println(e);
            startScherm.updateStatusLabel(c.getString("configureer.registreer.doel"));
        }
        catch (OngeldigAantalKistenException e)
        {
            System.err.println(e);
            startScherm.updateStatusLabel(c.getString("configureer.registreer.kist"));
        }
        catch (OngeldigAantalMannetjesException e)
        {
            System.err.println(e);
            startScherm.updateStatusLabel(c.getString("configureer.registreer.mannetje"));
        }
        catch (OngelijkAantalDoelenKistenException e)
        {
            System.err.println(e);
            startScherm.updateStatusLabel(c.getString("configureer.registreer.aantal"));
        }
    }

    /**
     * Als er op btnRegistreerSpel geklikt werd.
     * @param event
     */
    private void btnRegistreerSpel_clicked(ActionEvent event)
    {
        try
        {
            if (txtSpelnaam.getText().isEmpty())
            {
                throw new IllegalArgumentException();
            }

            c.registreerCustomSpel();
            startScherm.installSpelNaamHandlers(); //Zorgt ervoor dat het spel na het registreren direct in het menu Spel te zien is.
            startScherm.updateStatusLabel(c.geefSpelNaam() + " " + c.getString("configureer.registreer.succes"));
            this.getChildren().clear();
        }
        catch (SpelNaamBestaatException snbe)
        {
            System.err.println(snbe);
            startScherm.updateStatusLabel(c.getString("configureer.registreer.naambezet"));
            lblSpelNaam.setText(null);
            btnOK.setDisable(false);
        }
        catch (IllegalArgumentException iae)
        {
            System.err.println(iae);
            startScherm.updateStatusLabel(c.getString("configureer.registreer.naamleeg"));
        }
    }
}
