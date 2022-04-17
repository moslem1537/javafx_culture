/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entity.category;
import edu.esprit.entity.produit;
import edu.esprit.services.CategoryCrud;
import edu.esprit.services.ProduitCrud;
import java.net.URL;
import java.util.Locale;
import java.util.Locale.Category;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class CategoryController implements Initializable {

    @FXML
    private TextField cId;
    @FXML
    private TextField cNom;
    @FXML
    private TextField cDescrption;
    @FXML
    private Button cAjouter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterCategory(ActionEvent event) {
         int id=Integer.parseInt(cId.getText());
        String nom=cNom.getText();
        String descrption=cDescrption.getText();
         
          
        category c =new category(id,nom,descrption);
          CategoryCrud c1=new CategoryCrud();
          c1.AjouterCategory(c);
    }
    
}
