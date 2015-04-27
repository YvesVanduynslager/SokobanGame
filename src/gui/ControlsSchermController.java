package gui;

import domein.DomeinController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Yves
 */
public class ControlsSchermController extends GridPane implements Initializable, Refreshable
{
    @FXML
    private Label lblTitel, lblZ, lblQ, lblS, lblD;

    private DomeinController c;
    private StartSchermController startScherm;

    public ControlsSchermController(StartSchermController startScherm, DomeinController c)
    {
        this.startScherm = startScherm;
        this.c = c;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ControlsScherm.fxml"));
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

        lblZ.getStyleClass().add("keys");
        lblQ.getStyleClass().add("keys");
        lblS.getStyleClass().add("keys");
        lblD.getStyleClass().add("keys");

        installMouseHandlers();
        refresh();
    }
    
    private void installMouseHandlers()
    {
        lblZ.setOnMouseEntered(this::mouse_entered);
        lblQ.setOnMouseEntered(this::mouse_entered);
        lblS.setOnMouseEntered(this::mouse_entered);
        lblD.setOnMouseEntered(this::mouse_entered);

        lblZ.setOnMouseExited(this::mouse_exited);
        lblQ.setOnMouseExited(this::mouse_exited);
        lblS.setOnMouseExited(this::mouse_exited);
        lblD.setOnMouseExited(this::mouse_exited);
    }

    private void mouse_entered(MouseEvent event)
    {
        if (event.getSource() == lblZ)
        {
            startScherm.updateStatusLabel("Z = Omhoog");
            //new ImageView(new Image(getClass().getResourceAsStream("/images/muur.jpg"))), kolom, rij)
        }
        else
        {
            if (event.getSource() == lblQ)
            {
                startScherm.updateStatusLabel("Q = Links");
            }
            else
            {
                if (event.getSource() == lblS)
                {
                    startScherm.updateStatusLabel("S = Omlaag");
                }
                else
                {
                    if (event.getSource() == lblD)
                    {
                        startScherm.updateStatusLabel("D = Rechts");
                    }
                }
            }
        }
    }

    private void mouse_exited(MouseEvent event)
    {
        startScherm.updateStatusLabel("");
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    @Override
    public final void refresh()
    {
        lblTitel.setText(c.getString("controls"));
    }
}