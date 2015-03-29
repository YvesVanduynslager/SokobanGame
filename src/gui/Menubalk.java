/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuBar;

/**
 * FXML Controller class
 *
 * @author Yves
 */

public class Menubalk extends MenuBar
{
    private StartScherm startScherm;
    
    @FXML
    private MenuBar mbMenuBalk;

    public Menubalk(StartScherm startScherm)
    {
        this.startScherm = startScherm;
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menubalk.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try
        {
            loader.load();
            
        }
        catch(IOException ex)
        {
            throw new RuntimeException(ex);
        }
    }
}
