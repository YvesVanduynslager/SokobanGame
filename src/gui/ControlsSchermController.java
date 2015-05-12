package gui;

import domein.DomeinController;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Yves
 */
public class ControlsSchermController extends GridPane implements Refreshable
{
    @FXML
    private Label lblTitel, lblZ, lblQ, lblS, lblD;

    private final DomeinController c;
    private final StartSchermController startScherm;

    /**
     * Initialisatie van het controls-scherm.
     *
     * @param startScherm parent-component.
     * @param c DomeinController-object.
     */
    public ControlsSchermController(StartSchermController startScherm, DomeinController c)
    {
        init();

        this.startScherm = startScherm;
        this.c = c;

        lblZ.getStyleClass().add("border");
        lblQ.getStyleClass().add("border");
        lblS.getStyleClass().add("border");
        lblD.getStyleClass().add("border");

        installMouseHandlers();
        refresh();
    }

    /**
     * Initialisatie van fxml-root.
     */
    @Override
    public final void init()
    {
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
    }

    /**
     * Stelt de handlers in voor mouseovers over keys.
     */
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
            startScherm.updateStatusLabel("Z = " + c.getString("controls.omhoog"));
        }
        else
        {
            if (event.getSource() == lblQ)
            {
                startScherm.updateStatusLabel("Q = " + c.getString("controls.links"));
            }
            else
            {
                if (event.getSource() == lblS)
                {
                    startScherm.updateStatusLabel("S = " + c.getString("controls.omlaag"));
                }
                else
                {
                    if (event.getSource() == lblD)
                    {
                        startScherm.updateStatusLabel("D = " + c.getString("controls.rechts"));
                    }
                }
            }
        }
    }

    /**
     * Als de muis het gebied verlaat.
     * @param event 
     */
    private void mouse_exited(MouseEvent event)
    {
        startScherm.updateStatusLabel("");
    }

    /**
     * Stelt componenten opnieuw in adhv taal-resources.
     */
    @Override
    public final void refresh()
    {
        lblTitel.setText(c.getString("controls"));
    }
}
