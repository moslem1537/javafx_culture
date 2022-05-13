package Gui.Produit;

import Entities.Cart;
import Entities.Product;
import Entities.produit;
import Gui.Commande.PanierController;
import Services.ServiceProduit;
import animatefx.animation.Bounce;
import animatefx.animation.FadeInDown;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ShopController implements Initializable {

    @FXML
    private GridPane productGrid;

    @FXML
    private Pane banner;

    @FXML
    private AnchorPane centerContent;

    private List<Product> productsList;
    ObservableList<Cart> panier = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new Bounce(banner).play();
        loadProductPane();
    }

    void loadProductPane(){
        productsList = new ArrayList<>(getShopProducts());
        int column = 0;
        int row =1;

        try {
            for (Product prod : productsList){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("myBook.fxml"));

                HBox hBoxItem = fxmlLoader.load();
                MyBookController bookController = fxmlLoader.getController();
                bookController.setData(prod,centerContent);
                if (column == 3){
                    column=0;
                    ++row;
                }

                productGrid.add(hBoxItem,column++,row);
                GridPane.setMargin(hBoxItem, new Insets(10));
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Product> getShopProducts(){
        List<Product> lp = new ArrayList<>();
        ArrayList<Product> products = new ServiceProduit().getAll();

        for (int i = 0; i < products.size(); i++){
            Product product = new Product();
            product.setNom(products.get(i).getNom());
            product.setPrix(products.get(i).getPrix());
            product.setId(products.get(i).getId());
           // product.setRef(products.get(i).getRef());
            //product.setQuantity(products.get(i).getQuantity());
            product.setImg("/Gui/Images/"+products.get(i).getImg());
            //product.setDescription(products.get(i).getDescription());
            lp.add(product);
        }

        return lp;
    }

    public void redirectionFromPayment(AnchorPane c) {
        centerContent = c;
        panier.clear();

    }

    public void redirectionFromPanier(AnchorPane c, ObservableList<Cart> p) {
        centerContent = c;
        if(panier.isEmpty())
            panier.addAll(p);
        else {
            panier.clear();
            panier.addAll(p);
        }
      //  user=u;
    }

}
