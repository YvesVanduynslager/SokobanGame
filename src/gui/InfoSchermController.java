package gui;

import domein.DomeinController;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Yves
 */
public class InfoSchermController extends GridPane implements Refreshable
{
    @FXML
    private Label lblTitel;

    private final DomeinController c;
    
    public InfoSchermController(DomeinController c)
    {
        init();
        this.c = c;
        refresh();
    }  

    @Override
    public final void init()
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InfoScherm.fxml"));
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
        lblTitel.setText("Sokoban " + c.getString("groep") + " 8");
    }
}
