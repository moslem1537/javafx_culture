/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.UtilisateurService;
import Utilities.AlertUtils;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
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
public class NouveauMotDePasseController implements Initializable {

    @FXML
    private TextField passwordTF;
    @FXML
    private TextField confirmTF;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void changer(ActionEvent event) {
        if (passwordTF.getText().equals("") || confirmTF.getText().equals("")) {
            AlertUtils.information("Veuillez remplir tout le champs aaychek");
        } else {
            if (passwordTF.getText().equals(confirmTF.getText())) {

                UtilisateurService cs = new UtilisateurService();
                try {
                    cs.changerMotDePasse(MotDePasseOublieController.emailActuel, passwordTF.getText());
                } catch (SQLException | NoSuchAlgorithmException ex) {
                    Logger.getLogger(NouveauMotDePasseController.class.getName()).log(Level.SEVERE, null, ex);
                }

                AlertUtils.information("Mot de passe changé");

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
            } else {
                AlertUtils.erreur("Mot de passe et code confirmation sonts differents");
            }
        }
    }
}
