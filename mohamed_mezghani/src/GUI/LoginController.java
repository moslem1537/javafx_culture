/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DB.MyDB;
import Entities.Utilisateur;
import Utilities.AlertUtils;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mohamed
 */
public class LoginController implements Initializable {

    @FXML
    private Button insccc;

    public LoginController() throws IOException, SQLException, NoSuchAlgorithmException {

        connexion = MyDB.getInstance().getConnection();
    }
    Connection connexion;
    
    public static Utilisateur connectedUser;

    @FXML
    private TextField email;
    @FXML
    private TextField mdp;
    @FXML
    private Button login;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private String hashmdp(String mdp) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(mdp.getBytes());

        byte byteData[] = md.digest();

        //convertir le tableau de bits en une format hexadécimal - méthode 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        //convertir le tableau de bits en une format hexadécimal - méthode 2
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            String hex = Integer.toHexString(0xff & byteData[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }

    @FXML
    private void login(ActionEvent event) throws SQLException, NoSuchAlgorithmException, IOException {
        String req = "SELECT * from users WHERE email LIKE '" + email.getText() + "' and password LIKE '" + hashmdp(mdp.getText()) + "' ";

        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {

            Utilisateur p = new Utilisateur(
                    rst.getInt("id"),
                    rst.getString("name"),
                    rst.getString("lastname"),
                    rst.getInt("phone"),
                    rst.getString("email"),
                    rst.getString("password"),
                    rst.getString("gender"),
                    rst.getDate("birthday"),
                    rst.getBoolean("isBlocked") 
            );

            LoginController.connectedUser = p;

            if (p.isIsBlocked()) {
                AlertUtils.erreur("Vous etes bloqué");
            } else if(p.getGender().equals("Admin")) {
                Parent page1 = FXMLLoader.load(getClass().getResource("list.fxml"));
                Scene scene = new Scene(page1, 1236, 785);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setTitle("Liste des Utilisateurs");
                stage.setScene(scene);
                stage.show();
            }
            else{
                Parent page1 = FXMLLoader.load(getClass().getResource("defaultpage.fxml"));
                Scene scene = new Scene(page1, 1236, 785);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setTitle("Liste des Utilisateurs");
                stage.setScene(scene);
                stage.show();
                
            }

        }

    }

    @FXML
    private void insccc(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("Ajout.fxml"));
        Scene scene = new Scene(page1, 1236, 785);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Liste des Utilisateurs");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void resetPassword(MouseEvent event) {
        try {
            Parent dialogParent = FXMLLoader.load(getClass().getResource("MotDePasseOublie.fxml"));
            Stage stage = new Stage();

            stage.setTitle("Reinitialisation de mot de passe");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.getIcons().add(new Image("images/mdi/settings.png"));
            stage.setX((Screen.getPrimary().getBounds().getWidth() / 2) - (680 / 2));
            stage.setY((Screen.getPrimary().getBounds().getHeight() / 2) - (425 / 2));
            stage.setScene(new Scene(dialogParent));
            stage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(MotDePasseOublieController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
