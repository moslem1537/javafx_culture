/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mohamed
 */
public class DefaultpageController implements Initializable {

    @FXML
    private Label User;
    @FXML
    private Button b_projets;
    @FXML
    private Button b_evenements;
    @FXML
    private Button b_forum;
    @FXML
    private Button b_transactions;
    @FXML
    private Button b_profil;
    @FXML
    private Button b_profil1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }  
   
    @FXML
    private void on_b_projets(ActionEvent event) {
    }

    @FXML
    private void on_b_evenements(ActionEvent event) {
    }

    @FXML
    private void on_b_forum(ActionEvent event) {
    }

    @FXML
    private void on_b_transactions(ActionEvent event) {
    }

    @FXML
    private void on_b_profil(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("Profile.fxml"));
        Scene scene = new Scene(page1, 1236, 785);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Profile");
        stage.setScene(scene);
        stage.show();

    }
    
}
