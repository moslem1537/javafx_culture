/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Utilisateur;
import Services.UtilisateurService;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mohamed
 */
public class ModifierUserController implements Initializable {

    @FXML
    private Label welcome;
    @FXML
    private TextField Nom;
    @FXML
    private Button A;
    @FXML
    private TextField email;
    @FXML
    private TextField tel;
    @FXML
    private TextField mdp;
    @FXML
    private ComboBox<String> usertype;
    @FXML
    private Label labelid;
    @FXML
    private Hyperlink prec;
    @FXML
    private Label User;
    @FXML
    private TextField lastName;
    @FXML
    private TextField birthDate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        User.setText(LoginController.connectedUser.getName());

        ObservableList<String> list_ne = FXCollections.observableArrayList( "Admin", "client");
        usertype.setItems(list_ne);

        labelid.setText(Integer.toString(ListController.connectedUser.getId()));
       // email.setText(ListController.connectedUser.getEmail());
        tel.setText(Integer.toString(ListController.connectedUser.getPhone()));

        usertype.setValue(ListController.connectedUser.getGender());

       // mdp.setText(ListController.connectedUser.getPassword());
        Nom.setText(ListController.connectedUser.getName());

        // TODO
    }

    @FXML
    private void insert(ActionEvent event) throws SQLException, NoSuchAlgorithmException, IOException, ParseException {
        UtilisateurService productService = new UtilisateurService();

        Utilisateur c = new Utilisateur(
                Integer.parseInt(labelid.getText()),
                Nom.getText(),
                lastName.getText(),
                Integer.parseInt(tel.getText()),
                usertype.getValue(),
                new SimpleDateFormat("dd/MM/yyyy").parse(birthDate.getText()),
                false
        );

        productService.modifierUser(c);
        Parent page1 = FXMLLoader.load(getClass().getResource("list.fxml"));
        Scene scene = new Scene(page1, 1236, 785);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Liste des Utilisateurs");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void prec(ActionEvent event) throws IOException {

        Parent page1 = FXMLLoader.load(getClass().getResource("list.fxml"));
        Scene scene = new Scene(page1, 1236, 785);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Liste des Utilisateurs");
        stage.setScene(scene);
        stage.show();

    }

}
