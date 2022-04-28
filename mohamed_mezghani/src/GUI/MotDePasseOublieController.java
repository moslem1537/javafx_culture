/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.UtilisateurService;
import Utilities.AlertUtils;
import Utilities.Mailer;
import java.io.IOException;
import java.net.URL;
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
public class MotDePasseOublieController implements Initializable {

    public static int resetCode;
    public static String emailActuel;

    @FXML
    private TextField emailTF;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void reinit(ActionEvent event) throws SQLException {
        if (!emailTF.getText().equals("")) {

            UtilisateurService cs = new UtilisateurService();
            emailActuel = emailTF.getText();

            if (cs.verifierEmailExistant(emailActuel)) {
                try {
                    resetCode = getRandomNumber(1000, 9999);
                    Mailer.sendPasswordMail(emailActuel, resetCode);

                    try {
                        Parent parent = FXMLLoader.load(getClass().getResource("ReinitPass.fxml"));
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(new Scene(parent));
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(MotDePasseOublieController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(MotDePasseOublieController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                AlertUtils.erreur("Email inexistant");
            }

        } else {
            AlertUtils.information("Veuillez tapez votre email");
        }
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

}
