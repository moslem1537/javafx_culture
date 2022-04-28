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
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author mohamed
 */
public class ListController implements Initializable {

    UtilisateurService cs = new UtilisateurService();
    public ObservableList<Utilisateur> list;
    public static Utilisateur connectedUser;

    @FXML
    private TableView<Utilisateur> tableview;
    @FXML
    private TableColumn<Utilisateur, String> email;
    @FXML
    private TableColumn<Utilisateur, String> Nom;
    @FXML
    private TableColumn<Utilisateur, Integer> tel;
    private TableColumn<Utilisateur, Float> solde;
    @FXML
    private TableColumn<Utilisateur, String> usertype;
    @FXML
    private TextField inputRech;
    @FXML
    private Button supp;
    @FXML
    private Button supp1;
    @FXML
    private Button Ajouter;
    @FXML
    private Label User;
    @FXML
    private TableColumn<Utilisateur, Boolean> isblocked;
    @FXML
    private TableColumn<Utilisateur, String> lastName;
    @FXML
    private TableColumn<Utilisateur, String> Date;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        User.setText(LoginController.connectedUser.getName());
        UtilisateurService pss = new UtilisateurService();
        ArrayList<Utilisateur> c = new ArrayList<>();
        try {
            c = (ArrayList<Utilisateur>) pss.AfficherAllUsers();
        } catch (SQLException ex) {
            System.out.println("erreurrrrrrrrrrrrrrrrrr");
        }
        
        ObservableList<Utilisateur> obs2 = FXCollections.observableArrayList(c);
        tableview.setItems(obs2);
        Nom.setCellValueFactory(new PropertyValueFactory<>("name"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        tel.setCellValueFactory(new PropertyValueFactory<>("phone"));
        usertype.setCellValueFactory(new PropertyValueFactory<>("gender"));
        isblocked.setCellValueFactory(new PropertyValueFactory<>("isBlocked"));
        Date.setCellValueFactory(new PropertyValueFactory<>("birthday"));


        try {
            list = FXCollections.observableArrayList(
                    pss.AfficherAllUsers()
            );
            FilteredList<Utilisateur> filteredData = new FilteredList<>(list, e -> true);
            inputRech.setOnKeyReleased(e -> {
                inputRech.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                    filteredData.setPredicate((Predicate<? super Utilisateur>) Utilisateurs -> {
                         /* If filter text is empty, display all data */
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lower = newValue.toLowerCase();
                        if (Utilisateurs.getName().toLowerCase().contains(lower)) {
                            return true;
                        }

                        return false;
                    });
                });
                SortedList<Utilisateur> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(tableview.comparatorProperty());
                tableview.setItems(sortedData);
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    private void supp(ActionEvent event) throws SQLException {
        if (event.getSource() == supp) {
            Utilisateur user = new Utilisateur();
            user.setId(tableview.getSelectionModel().getSelectedItem().getId());
            UtilisateurService cs = new UtilisateurService();
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Vous avez Supprimé l'utilisateur " + user.getName());
            tray.setMessage("");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));
            cs.SupprimerUser(user);
            resetTableData();

        }

    }

    public void resetTableData() throws SQLDataException, SQLException {

        List<Utilisateur> listuser = new ArrayList<>();
        listuser = cs.AfficherAllUsers();
        ObservableList<Utilisateur> data = FXCollections.observableArrayList(listuser);
        tableview.setItems(data);
    }

    @FXML
    private void Modif(ActionEvent event) throws IOException {

        UtilisateurService ps = new UtilisateurService();
        Utilisateur c = new Utilisateur(tableview.getSelectionModel().getSelectedItem().getId(),
                tableview.getSelectionModel().getSelectedItem().getName(),
                                tableview.getSelectionModel().getSelectedItem().getLastName(),

                tableview.getSelectionModel().getSelectedItem().getPhone(),
                tableview.getSelectionModel().getSelectedItem().getEmail(),
                tableview.getSelectionModel().getSelectedItem().getPassword(),
                tableview.getSelectionModel().getSelectedItem().getGender(),
                tableview.getSelectionModel().getSelectedItem().getBirthday(),
                tableview.getSelectionModel().getSelectedItem().isIsBlocked()
        );
        ListController.connectedUser = c;

        Parent page1 = FXMLLoader.load(getClass().getResource("ModifierUser.fxml"));
        Scene scene = new Scene(page1, 1144, 741);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void Ajouter(ActionEvent event) throws IOException {

        Parent page1 = FXMLLoader.load(getClass().getResource("Ajout.fxml"));
        Scene scene = new Scene(page1, 1236, 785);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Liste des Utilisateurs");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void bloquer(ActionEvent event) {
        UtilisateurService ps = new UtilisateurService();
        Utilisateur c = new Utilisateur(
                tableview.getSelectionModel().getSelectedItem().getId(),
                tableview.getSelectionModel().getSelectedItem().getName(),
                tableview.getSelectionModel().getSelectedItem().getLastName(),
                tableview.getSelectionModel().getSelectedItem().getPhone(),
                tableview.getSelectionModel().getSelectedItem().getEmail(),
                tableview.getSelectionModel().getSelectedItem().getPassword(),
                tableview.getSelectionModel().getSelectedItem().getGender(),
                tableview.getSelectionModel().getSelectedItem().getBirthday(),
                tableview.getSelectionModel().getSelectedItem().isIsBlocked()
        );
        ListController.connectedUser = c;

        try {
            if (c.isIsBlocked()) {
                ps.debloquerUtilisateur(c.getId());
            } else {
                ps.bloquerUtilisateur(c.getId());
            }
            resetTableData();
        } catch (SQLException ex) {
            Logger.getLogger(ListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
