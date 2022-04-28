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
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author mohamed
 */
public class AjoutController implements Initializable {

    public static String connectedMail;
    @FXML
    private Label welcome;
    @FXML
    private TextField email;
    @FXML
    private TextField Nom;
    @FXML
    private TextField tel;
    @FXML
    private TextField mdp;
    @FXML
    private ComboBox<String> usertype;
    @FXML
    private Button A;
    @FXML
    private Hyperlink prec;
    @FXML
    private Label User;
    @FXML
    private TextField lastName;
    @FXML
    private TextField birthdayDate;
    

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> list_ne = FXCollections.observableArrayList( "Admin", "Client");
        usertype.setItems(list_ne);
        // User.setText(LoginController.connectedUser.getNom());

    }

    @FXML
    private void insert(ActionEvent event) throws SQLException, IOException, MessagingException, ParseException {

        UtilisateurService productService = new UtilisateurService();

        if (email.getText().equals("")
                || mdp.getText().equals("")
                || Nom.getText().equals("") || usertype.getValue().equals("") || tel.getText().equals("") || lastName.getText().equals("")) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("veuillez remplir tous les champs");
            a.setHeaderText(null);
            a.showAndWait();
        }

        Utilisateur c = new Utilisateur(
                Nom.getText(),
                lastName.getText(),
                Integer.parseInt(tel.getText()),
                email.getText(), 
                mdp.getText(),
                usertype.getValue(),
                new SimpleDateFormat("dd/MM/yyyy").parse(birthdayDate.getText())); 
                
        

        productService.ajouterUser(c);
        Parent page1 = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(page1, 1236, 785);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Liste des Utilisateurs");
        stage.setScene(scene);
        stage.show();

        String mail2 = email.getText();
        System.out.println(mail2);
        connectedMail = Nom.getText();
        sendMail(mail2);

    }

    @FXML
    private void prec(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(page1, 1236, 785);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Liste des Utilisateurs");

        stage.setScene(scene);
        stage.show();
    }

    public static void sendMail(String recipient) throws MessagingException {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        String myAccountEmail = "mohamed.mezghani@esprit.tn";
        String password = "191JMT1561";
        System.out.println("1");
        Session session = Session.getInstance(properties, new Authenticator() {
           
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });
System.out.println("2");
        Message message = prepareMessage(session, myAccountEmail, recipient);
System.out.println("3");
        Transport.send(message);
        System.out.println("E-mail sent successfully");
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recipient) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Bienvenue " + connectedMail + " à Mezghani");
            message.setText("Bienvenue");
            return message;
        } catch (MessagingException ex) {
            Logger.getLogger(AjoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
