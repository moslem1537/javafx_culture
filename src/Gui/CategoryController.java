/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.category;
import Entities.produit;
import Services.CategoryCrud;
import Services.ProduitCrud;
import Utils.MyConnection;
//import java.awt.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Locale.Category;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javax.mail.MessagingException;
import javax.swing.JFileChooser;
import nl.captcha.Captcha;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//import static jdk.nashorn.internal.runtime.Debug.id;


/**
 * FXML Controller class
 *
 * @author admin
 */
public class CategoryController implements Initializable {
    private  CategoryCrud ca=new CategoryCrud();
    private  ProduitCrud pr =new ProduitCrud();

    @FXML
    private TextField cId;
    @FXML
    private TextField cNom;
    @FXML
    private TextField cDescrption;
    @FXML
    private Button cAjouter;
    @FXML
    private Button suppr;
    @FXML
    private ListView<category> listCat;
    @FXML
    private Button modif;
    @FXML
    private Button actualiserCAT;
    @FXML
    
    private TextField find;
    @FXML
    private Button nom;
    @FXML
    private Button desc;
    @FXML
    private Button triNom;
    @FXML
    private Button triDESC;
    @FXML
    private TextField pId;
    @FXML
    private TextField pDesignation;
    @FXML
    private TextField pPrix;
    @FXML
    private TextField pQuantite;
    private TextField pCathegorie_id;
    @FXML
    private Button PAjouter;
    @FXML
    private TextField pImage;
    @FXML
    private Button upload;
    @FXML
    private ListView<produit> listeP;
    @FXML
    private Button SUPP;
    @FXML
    private Button ACTU;
    @FXML
    private Button MODIF;
    @FXML
    private TextField findP;
    @FXML
    private Button prixx;
    @FXML
    private Button desg;
    @FXML
    private Button PRIX;
    @FXML
    private Button QUAN;
    @FXML
    private Button excel;
    @FXML
    private PieChart PIEchart;
    @FXML
    private Button stat;
    Captcha captcha;
    @FXML
    private ImageView captchaIV;
    @FXML
    private TextField captchaTF;
    @FXML
    private ComboBox<String> comb;
   
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        captcha=setCaptcha();
        fillComboBox();
    }    
public Captcha setCaptcha() {
        Captcha captcha = new Captcha.Builder(250, 200)
                .addText()
                .addBackground()
                .addNoise()
                .gimp()
                .addBorder()
                .build();

        System.out.println(captcha.getImage());
        Image image = SwingFXUtils.toFXImage(captcha.getImage(), null);

        captchaIV.setImage(image);

        return captcha;
    }

    
    
    
    
        /////////////////////////////////////////////////////////////Gategories/////////////////////////////////////////////////////
    @FXML
   private void AjouterCategory(ActionEvent event) {
        if (cId.getText().isEmpty() || cNom.getText().isEmpty() || cDescrption.getText().isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("veuillez remplir tous les champs !");
            alert.showAndWait();
        }
        else if (!(Pattern.matches("[0-9]*", cId.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("L'idenentifiant du categorie doit etre un entier  !");
            alert.showAndWait();
        }
        else if (!(Pattern.matches("[a-z,A-Z]*", cNom.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Le nom de categorie doit etre string !");
            alert.showAndWait();
        }
         else if (!(Pattern.matches("[a-z,A-Z]*", cDescrption.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Le description de categorie doit etre string !");
            alert.showAndWait();
        }
        else if (!captcha.isCorrect(captchaTF.getText())) {
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Captcha inncorrect ");
            alert.show();
            captcha = setCaptcha();
            captchaTF.setText("");
          
        } 

        else {
         int id=Integer.parseInt(cId.getText());
        String nom=cNom.getText();
        String descrption=cDescrption.getText();
          category c =new category();
          int x1=Integer.parseInt(cId.getText());
          c.setId(x1);
          c.setDescrption(descrption);
          c.setNom(nom);
          
       
       ca.AjouterCategory(c);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Êtes-vous sûr de ajouter cette categorie ??");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Alert xx = new Alert(Alert.AlertType.INFORMATION);
                xx.setContentText("Categorie est ajoutéé avec succes!");
                xx.showAndWait();
            }
        
            ObservableList<category> items = FXCollections.observableArrayList();
        List<category> listC = ca.afficherCategory();
        for (category r : listC) {
            String ch = r.toString();
            items.add(r);
        }
        listCat.setItems(items);
        
        
    }}

    @FXML
    private void SupprimerCategorie(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Êtes-vous sûr de supprimer cette categorie ??");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Alert xx = new Alert(Alert.AlertType.INFORMATION);
                xx.setContentText("Categorie est supprimé avec succes!");
                xx.showAndWait();
            }
       int id = Integer.parseInt(cId.getText());
        ca.SupprimerCategorie(id);
           ObservableList<category> items = FXCollections.observableArrayList();
        List<category> listC = ca.afficherCategory();
        for (category r : listC) {
            String ch = r.toString();
            items.add(r);
        }
        listCat.setItems(items);
        }
        
        
        
         
        
        
      
    

    @FXML
    private void ModifierCategorie(ActionEvent event) {{
        if (cId.getText().isEmpty() || cNom.getText().isEmpty() || cDescrption.getText().isEmpty() ){
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("veuillez remplir tous les champs !");
            alert.showAndWait();
        }
        else if (!(Pattern.matches("[0-9]*", cId.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("L'idenentifiant du categorie doit etre un entier  !");
            alert.showAndWait();
        }
        else if (!(Pattern.matches("[a-z,A-Z]*", cNom.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Le nom de categorie doit etre string !");
            alert.showAndWait();
        }
         else if (!(Pattern.matches("[a-z,A-Z]*", cDescrption.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Le description de categorie doit etre string !");
            alert.showAndWait();
        }

        else {
         int id=Integer.parseInt(cId.getText());
        String nom=cNom.getText();
        String descrption=cDescrption.getText();
          category c =new category();
          int x1=Integer.parseInt(cId.getText());
          c.setId(x1);
          c.setDescrption(descrption);
          c.setNom(nom);
        
             
        
        
        
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Êtes-vous sûr de  modifier cette Categorie ? ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Alert xx = new Alert(Alert.AlertType.INFORMATION);
                xx.setContentText("Categorie est modifié avec succes!");
                xx.showAndWait();
        
    
       
        c.setDescrption(cDescrption.getText());
        c.setNom(cNom.getText());
        
      


        ca.ModifierCategory(c);
        
        ObservableList<category> items = FXCollections.observableArrayList();
        List<category> listc = ca.afficherCategory();
        for (category r : listc) {
            String ch = r.toString();
            items.add(r);
        }
        listCat.setItems(items);
        
    }
    }}
    }
    
    
    @FXML
    private void ActualiserCategorie(ActionEvent event) {
         ObservableList<category> items = FXCollections.observableArrayList();
        List<category> listc = ca.afficherCategory();
        for (category r : listc) {
            String ch = r.toString();
            items.add(r);
        }
        listCat.setItems(items);
        
        
    }

    @FXML
    private void RechercherNom(ActionEvent event) {
      
         CategoryCrud n = new CategoryCrud();
        List< category> R = n.ChercherNom(find.getText());
        ObservableList< category> datalist = FXCollections.observableArrayList(R);

        listCat.setItems(datalist);
        find.setText(" ");
     
        
    }

    @FXML
    private void RechercherDESC(ActionEvent event) {
        
        
        CategoryCrud n = new CategoryCrud();
        List< category> R = n.ChercherDESC(find.getText());
        ObservableList< category> datalist = FXCollections.observableArrayList(R);

        listCat.setItems(datalist);
        find.setText(" ");
    }

    @FXML
    private void TrierNom(ActionEvent event) {
        CategoryCrud n = new CategoryCrud();
        List< category> R = n.TrierNom();
        ObservableList< category> datalist = FXCollections.observableArrayList(R);

        listCat.setItems(datalist);
        find.setText(" ");
    }

    @FXML
    private void TriDesc(ActionEvent event) {
         CategoryCrud n = new CategoryCrud();
        List< category> R = n.TrierDESC();
        ObservableList< category> datalist = FXCollections.observableArrayList(R);

        listCat.setItems(datalist);
        find.setText(" ");
    }

    
    
    
    
    /////////////////////////////////////////////////////////////PRODUITS//////////////////////////////////////////////////////
    
     @FXML
    private void UploadImage(ActionEvent event) {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String Filename = f.getAbsolutePath();
        pImage.setText(Filename);
    }
    @FXML
    
    
    
    private void AjouterProduit(ActionEvent event) throws MessagingException {
        
           if (pId.getText().isEmpty() || pDesignation.getText().isEmpty() || pQuantite.getText().isEmpty()|| pPrix.getText().isEmpty() || pImage.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("veuillez remplir tous les champs !");
            alert.showAndWait();
        }
            else if (!(Pattern.matches("[0-9]*", pId.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("L'idenentifiant du categorie doit etre un entier  !");
            alert.showAndWait();}
             else if (!(Pattern.matches("[0-9]*", pQuantite.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("L'idenentifiant du categorie doit etre un entier  !");
            alert.showAndWait();
        }
             
              else if (!(Pattern.matches("[a-z,A-Z]*", pDesignation.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("La designation de categorie doit etre string !");
            alert.showAndWait();
        }
           else
           {
               produit p=new produit();
           
               int q=Integer.parseInt(pQuantite.getText());
               //int pcat=Integer.parseInt(pCathegorie_id.getText());
        String designation =pDesignation.getText();
         String image =pImage.getText();
        String prix=pPrix.getText();
        int x1=Integer.parseInt(pId.getText());
        String categorie = comb.getSelectionModel().getSelectedItem().toString();
        CategoryCrud ca = new CategoryCrud(); 
            ArrayList<category> cat = (ArrayList<category>) ca.getCategory(categorie);
            produit r = new produit( x1,q,designation,image,prix,cat.get(0));
            ProduitCrud pr = new ProduitCrud();
           
        
          pr.AjouterProduit(r);
          
          Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("l produit est ajoutee avec success!");
            alert.showAndWait();
            pr.sendMail("narjes.gouader@esprit.tn");
            List<produit> listp = pr.afficherProduit();
         ObservableList<produit> items = FXCollections.observableArrayList();
         for (produit rr : listp) {
            String ch = rr.toString();
            items.add(rr);
        }
        listeP.setItems(items);
        }
    }
    
    @FXML
    private void SupprimerProduit(ActionEvent event) {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Êtes-vous sûr de supprimer ce produit ??");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Alert xx = new Alert(Alert.AlertType.INFORMATION);
                xx.setContentText("Produit est supprimé avec succes!");
                xx.showAndWait();
            }
        
        
        int id = Integer.parseInt(pId.getText());
        pr.SupprimerProduit(id);
          List<produit> listp = pr.afficherProduit();
          ObservableList<produit> items = FXCollections.observableArrayList();
        for (produit r : listp) {
            String ch = r.toString();
            items.add(r);
        }
        listeP.setItems(items);
         
        
        
        
    }

    @FXML
    private void ActualiserProduit(ActionEvent event) {
         List<produit> listp = pr.afficherProduit();
          ObservableList<produit> items = FXCollections.observableArrayList();
        for (produit r : listp) {
            String ch = r.toString();
            items.add(r);
        }
        listeP.setItems(items);
        }
        
        
    

    @FXML
    private void ModifierProduit(ActionEvent event) {
        if (pId.getText().isEmpty() || pDesignation.getText().isEmpty() || pQuantite.getText().isEmpty()|| pPrix.getText().isEmpty() || pImage.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("veuillez remplir tous les champs !");
            alert.showAndWait();
        }
            else if (!(Pattern.matches("[0-9]*", pId.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("L'idenentifiant du categorie doit etre un entier  !");
            alert.showAndWait();}
             else if (!(Pattern.matches("[0-9]*", pQuantite.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("L'idenentifiant du categorie doit etre un entier  !");
            alert.showAndWait();
        }
              else if (!(Pattern.matches("[0-9]*", pCathegorie_id.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("L'idenentifiant du categorie doit etre un entier  !");
            alert.showAndWait();
              }
              else if (!(Pattern.matches("[a-z,A-Z]*", pDesignation.getText()))) {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Êtes-vous sûr de  modifier ce produit ? ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Alert xx = new Alert(Alert.AlertType.INFORMATION);
                xx.setContentText("Produit est modifié avec succes!");
                xx.showAndWait();
            }
        
       produit p= new produit();
       
        
     
        int q=Integer.parseInt(pQuantite.getText());
               int pcat=Integer.parseInt(pCathegorie_id.getText());
        
       
     
          int x1=Integer.parseInt(pId.getText());

         p.setId(x1);
         p.setDesignation(pDesignation.getText());
         p.setImage(pImage.getText());
         p.setPrix(pPrix.getText());
      
          p.setQuantite(q);
         p.setCathegorie_id(pcat);
        pr.ModifierProduit(p);
       
        List<produit> listp = pr.afficherProduit();
          ObservableList<produit> items = FXCollections.observableArrayList();
        for (produit r : listp) {
            String ch = r.toString();
            items.add(r);
        }
        listeP.setItems(items);
    
    
    
    }
    }
    @FXML
    private void RecherchePrix(ActionEvent event) {
        
         ProduitCrud n = new ProduitCrud();
        List< produit> R = n.ChercherPrix(findP.getText());
        ObservableList< produit> datalist = FXCollections.observableArrayList(R);

        listeP.setItems(datalist);
        findP.setText(" ");
     
    }
    
    @FXML
    private void RechercheDesig(ActionEvent event) {
        ProduitCrud n = new ProduitCrud();
        List< produit> R = n.ChercherDSG(findP.getText());
        ObservableList< produit> datalist = FXCollections.observableArrayList(R);

        listeP.setItems(datalist);
        findP.setText(" ");
    }

    @FXML
    private void TrierPrix(ActionEvent event) {
        
         ProduitCrud n = new ProduitCrud();
        List< produit> R = n.TriPrix();
        ObservableList< produit> datalist = FXCollections.observableArrayList(R);

        listeP.setItems(datalist);
       
    }

    @FXML
    private void TrierQuantite(ActionEvent event) {
        ProduitCrud n = new ProduitCrud();
        List< produit> R = n.TriQuantite();
        ObservableList< produit> datalist = FXCollections.observableArrayList(R);

        listeP.setItems(datalist);
    }

    @FXML
    private void generateExcel(ActionEvent event) throws IOException {
        ca.generateExcel();}
    

    @FXML
    private void Statistique(ActionEvent event) {
       PIEchart.setTitle("Les statistiques sur les quantites des produits ");
        PIEchart.getData().setAll(new PieChart.Data("Quantite <25", pr.recherche3()), new PieChart.Data("25< quantite <50", pr.recherche2()),
                new PieChart.Data("50<quantite<100", pr.recherche1()));
    }
    private void fillComboBox() {
        ObservableList<String> options = FXCollections.observableArrayList();
       try {
            String req1 = "SELECT nom FROM categorie";
            PreparedStatement pst =    new MyConnection().cnx.prepareStatement(req1);
            ResultSet rs = pst.executeQuery(req1);
            while(rs.next()){
            options.add(rs.getString("nom"));
             comb.setItems(options);
                      
            }
            
           
        } catch (SQLException ex) {
             System.err.println(ex.getMessage());
        }
      
      
         
    }
     
       

    }
    
       
        
    

   

    
    

   

    
    
    
        
  

   


