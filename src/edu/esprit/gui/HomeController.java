/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entity.produit;
import edu.esprit.services.ProduitCrud;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class HomeController implements Initializable {

    @FXML
    private TextField pId;
    @FXML
    private TextField pCathegorie_id;
    @FXML
    private TextField pPrix;
    @FXML
    private TextField pQuantite;
    @FXML
    private TextField pImage;
    @FXML
    private TextField pDesignation;
    @FXML
    private Button PAjouter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterProduit(ActionEvent event) {
      
        int id=Integer.parseInt(pId.getText());
        String designation=pDesignation.getText();
        String prix=pPrix.getText();
         int quantite=Integer.parseInt(pQuantite.getText());
         String image=pImage.getText();
          int cathegorie_id=Integer.parseInt(pCathegorie_id.getText());
          
          produit p=new produit (id,designation,prix,quantite,image,cathegorie_id);
          ProduitCrud p1=new ProduitCrud();
          p1.AjouterProduit(p);
    }}
    
    
    
