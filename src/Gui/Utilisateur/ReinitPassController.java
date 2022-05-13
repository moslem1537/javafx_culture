/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Utilisateur;

import Utilities.AlertUtils;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mohamed
 */
public class ReinitPassController implements Initializable {

    @FXML
    private TextField codeTF;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        
    }

    @FXML
    private void suivant(ActionEvent event) {
        if (codeTF.getText().equals("")) {
            AlertUtils.information("Veuillez tapez le code");
        } else {
            if (MotDePasseOublieController.resetCode == Integer.parseInt(codeTF.getText())) {
                try {
                    Parent parent = FXMLLoader.load(getClass().getResource("NouveauMotDePasse.fxml"));
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(parent));
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(MotDePasseOublieController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                AlertUtils.erreur("Code incorrect");
            }
        }

    }

}
