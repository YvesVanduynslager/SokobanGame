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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Yves
 */
public class SpelbordController extends GridPane implements Refreshable
{
    private final DomeinController c;
    private final StartSchermController startScherm;

    @FXML
    private Button btnJa, btnNee;
    @FXML
    private TextArea txaInfo;
    @FXML
    private GridPane grdSpelbord;

    public SpelbordController(StartSchermController startScherm, DomeinController c)
    {
        init();
        this.startScherm = startScherm;
        this.c = c;

        tekenBord();
        enableKeyHandler();

        btnJa.setOnAction(this::btnJa_gekozen);
        btnNee.setOnAction(this::btnNee_gekozen);
        
        grdSpelbord.getStyleClass().add("border");
        txaInfo.getStyleClass().add("border");
    }
    
    @Override
    public final void init()
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
    }

    @Override
    public void refresh()
    {
        if (spelbordIsVoltooid())
        {
            txaInfo.setText(c.getString("spelbord.verderspelen"));
            if (spelIsVoltooid())
            {
                txaInfo.setText(c.getString("spelbord.voltooid"));
            }
        }
        btnJa.setText(c.getString("spelbord.ja"));
        btnNee.setText(c.getString("spelbord.nee"));

        this.wisBord();
        this.tekenBord();

        this.requestFocus();
    }

    private void tekenBord()
    {
        String[][] elementen = c.geefHuidigSpelbord();

        for (int rij = 0; rij < elementen.length; rij++)
        {
            for (int kolom = 0; kolom < elementen[rij].length; kolom++)
            {
                switch (elementen[rij][kolom])
                {
                    case "#":
                        grdSpelbord.add(new ImageView(new Image(getClass().getResourceAsStream("/images/muur.jpg"))), kolom, rij);
                        break;
                    case "K":
                        grdSpelbord.add(new ImageView(new Image(getClass().getResourceAsStream("/images/kist.jpg"))), kolom, rij);
                        break;
                    case ".":
                        grdSpelbord.add(new ImageView(new Image(getClass().getResourceAsStream("/images/veld.jpg"))), kolom, rij);
                        break;
                    case "D":
                        grdSpelbord.add(new ImageView(new Image(getClass().getResourceAsStream("/images/doel.jpg"))), kolom, rij);
                        break;
                    case "O":
                        grdSpelbord.add(new ImageView(new Image(getClass().getResourceAsStream("/images/mannetje.jpg"))), kolom, rij);
                        break;
                    default:
                        grdSpelbord.add(new Label(elementen[rij][kolom]), kolom, rij);
                        break;
                }
            }
        }
    }

    private void wisBord()
    {
        grdSpelbord.getChildren().clear();
    }

    private boolean spelIsVoltooid()
    {
        return c.geefAantalVoltooideBorden() == c.geefAantalSpelborden();
    }

    private boolean spelbordIsVoltooid()
    {
        return c.huidigSpelbordVoltooid();
    }

    private void updateControls()
    {
        if (spelbordIsVoltooid())
        {
            disableKeyHandler();
            startScherm.updateStatusLabel(
                    "" + c.geefAantalVoltooideBorden()
                    + " " + c.getString("spelbord.van") + " " + c.geefAantalSpelborden()
                    + " " + c.getString("spelbord.spelbordVoltooidIn") + " "
                    + c.geefAantalZetten() + " " + c.getString("spelbord.zetten"));
            txaInfo.setText(c.getString("spelbord.verderspelen"));
            txaInfo.setVisible(true);
            btnJa.setVisible(true);
            btnNee.setVisible(true);

            if (spelIsVoltooid())
            {
                txaInfo.setVisible(true);
                txaInfo.setText(c.getString("spelbord.voltooid"));
                btnJa.setVisible(false);
                btnNee.setVisible(false);
            }
            else
            {
                btnJa.setVisible(true);
                btnNee.setVisible(true);
                txaInfo.setVisible(true);
            }
        }
        else
        {
            txaInfo.setVisible(false);
            btnJa.setVisible(false);
            btnNee.setVisible(false);
            startScherm.updateStatusLabel("" + c.geefAantalZetten() + " " + c.getString("spelbord.zetten"));
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
                                startScherm.updateStatusLabel(c.getString("spelbord.ongeldigeInvoer"));
                            }
                        }
                    }
                }

                updateControls();
                refresh();

                keyEvent.consume();
            }
        };

        this.setOnKeyPressed(keyEventHandler);
    }

    private void btnJa_gekozen(ActionEvent event)
    {
        btnJa.setVisible(false);
        btnNee.setVisible(false);
        txaInfo.setVisible(false);
        startScherm.updateStatusLabel("");
        
        c.startVolgendSpelbord();
        refresh();
        enableKeyHandler();
    }

    private void btnNee_gekozen(ActionEvent event)
    {
        startScherm.updateStatusLabel("");
        this.getChildren().clear();
    }
}