package Gui.Backoffice;

import Entities.Commande;
import Entities.Offre_Emploi;
import Entities.Product;
import Entities.Produit;
import Gui.Acceuil.FXloader;
import Gui.Commande.ListCellCommande;
import Gui.OffreEmploi.OffreCell;
import Gui.Produit.AddProductController;
import Services.Offre_Emploi_Service;
import Services.ServiceCommande;
import Services.ServiceProduit;
import animatefx.animation.FadeInDown;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.fxml.Initializable;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BackofficeController implements Initializable {
    @FXML
    private Button btnHome;

    @FXML
    private Button btnGUser;

    @FXML
    private Button btnGProduits;

    @FXML
    private Button btnGCommande;

    @FXML
    private Button form;

    @FXML
    private Button btnGEvent;

    @FXML
    private Button btnGRating;

    @FXML
    private Button btnFrontOffice;

    @FXML
    private Button categ;

    @FXML
    private Pane pnStatus;

    @FXML
    private Label lbStatus;

    @FXML
    private Label lbStatusMin;

    @FXML
    private Button btnClose;

    @FXML
    private GridPane pnProducts;

    @FXML
    private GridPane pnCommandes;

    @FXML
    private GridPane pnUsers;

    @FXML
    private GridPane pnFormations;

    @FXML
    private GridPane gridcateg;

    @FXML
    private GridPane pnHome;

    @FXML
    private TextField tfSearch;


    @FXML
    private FontAwesomeIcon faRefresh;

    @FXML
    private FontAwesomeIcon faRefreshOrder;

    @FXML
    private TextField tfSearchOrder;

    @FXML
    private StackPane effect;

    @FXML
    private JFXButton viewProducts;


    @FXML
    private BorderPane affform;

    @FXML
    private BorderPane affcateg;

    @FXML
    private ListView<Commande> listCommande;
    @FXML
    private ListView<Product> productGrid;

    private List<Product> productsList;
    ObservableList<Produit> dataList;


    Produit productTab = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        searchCommandes();
        searchProduits();
        pnHome.toFront();
    }


    @FXML
    private void handleClicks(ActionEvent event) {
        if (event.getSource() == btnHome) {
            affform.setVisible(false);
            affcateg.setVisible(false);
            lbStatusMin.setText("/Home");
            lbStatus.setText("Home");
            pnStatus.setBackground(new Background(new BackgroundFill(Color.rgb(40, 95, 60), CornerRadii.EMPTY, Insets.EMPTY)));
            pnHome.toFront();
        } else if (event.getSource() == btnGUser) {
            affform.setVisible(false);
            affcateg.setVisible(false);
            lbStatusMin.setText("/Home/GUsers");
            lbStatus.setText("Users");
            pnStatus.setBackground(new Background(new BackgroundFill(Color.rgb(63, 43, 99), CornerRadii.EMPTY, Insets.EMPTY)));
            pnUsers.toFront();
        } else if (event.getSource() == btnGProduits) {
            affform.setVisible(false);
            affcateg.setVisible(false);
            lbStatusMin.setText("/Home/Gestion Produits");
            lbStatus.setText("Produits");
            pnStatus.setBackground(new Background(new BackgroundFill(Color.rgb(43, 99, 63), CornerRadii.EMPTY, Insets.EMPTY)));
            pnProducts.toFront();
        } else if (event.getSource() == btnGCommande) {
            affform.setVisible(false);
            affcateg.setVisible(false);
            lbStatusMin.setText("/Home/Gestion Commande");
            lbStatus.setText("Commandes");
            pnStatus.setBackground(new Background(new BackgroundFill(Color.rgb(43, 63, 99), CornerRadii.EMPTY, Insets.EMPTY)));
            pnCommandes.toFront();
            // showCommandes();

        } else if (event.getSource() == form) {
            affform.setVisible(true);
            affcateg.setVisible(false);
            lbStatusMin.setText("/Home/Gestion Formation");
            lbStatus.setText("Formation");
            pnStatus.setBackground(new Background(new BackgroundFill(Color.rgb(99, 43, 63), CornerRadii.EMPTY, Insets.EMPTY)));
            pnFormations.toFront();
            URL root_url = null;
            try {
                root_url = new File("src/Gui/Formation/Formation.fxml").toURI().toURL();
            } catch (MalformedURLException malformedURLException) {
                malformedURLException.printStackTrace();
            }
            Pane view = new FXloader().getPane(root_url);
            affform.setCenter(view);
        } else if (event.getSource() == btnGEvent) {
            affform.setVisible(false);
            affcateg.setVisible(false);
            lbStatusMin.setText("/Home/Gestion Evenements");
            lbStatus.setText("Evenements");
            pnStatus.setBackground(new Background(new BackgroundFill(Color.rgb(99, 63, 43), CornerRadii.EMPTY, Insets.EMPTY)));
        } else if (event.getSource() == btnGRating) {
            affform.setVisible(false);
            affcateg.setVisible(false);
            lbStatusMin.setText("/Home/Gestion Ratings");
            lbStatus.setText("Ratings");
            pnStatus.setBackground(new Background(new BackgroundFill(Color.rgb(63, 99, 43), CornerRadii.EMPTY, Insets.EMPTY)));
        } else if (event.getSource() == categ) {
            affform.setVisible(false);
            affcateg.setVisible(true);
            gridcateg.toFront();
            lbStatusMin.setText("/Home/Gestion Category");
            lbStatus.setText("Categories");
            pnStatus.setBackground(new Background(new BackgroundFill(Color.rgb(63, 99, 43), CornerRadii.EMPTY, Insets.EMPTY)));
            URL root_url = null;
            try {
                root_url = new File("src/Gui/Formation/Category.fxml").toURI().toURL();
            } catch (MalformedURLException malformedURLException) {
                malformedURLException.printStackTrace();
            }
            Pane view = new FXloader().getPane(root_url);
            affcateg.setCenter(view);
        }
    }

    @FXML
    void handleClose(MouseEvent event) {
        if (event.getSource() == btnClose) {
            System.exit(0);
        }
    }

    @FXML
    void addP(MouseEvent event) {
        try {

            final double[] xOffset = new double[1];
            final double[] yOffset = new double[1];
            Parent parent = FXMLLoader.load(getClass().getResource("/Gui/Produit/AddProduct.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            parent.setOnMousePressed(e -> {
                xOffset[0] = e.getSceneX();
                yOffset[0] = e.getSceneY();
            });

            parent.setOnMouseDragged(e -> {
                stage.setX(e.getScreenX() - xOffset[0]);
                stage.setY(e.getScreenY() - yOffset[0]);
            });
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();

        } catch (IOException exception) {
            exception.printStackTrace();
        }
        searchProduits();
    }

    @FXML
    void orderStats(MouseEvent event) {
        try {

            final double[] xOffset = new double[1];
            final double[] yOffset = new double[1];
            Parent parent = FXMLLoader.load(getClass().getResource("/Gui/Commande/stats.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            parent.setOnMousePressed(e -> {
                xOffset[0] = e.getSceneX();
                yOffset[0] = e.getSceneY();
            });

            parent.setOnMouseDragged(e -> {
                stage.setX(e.getScreenX() - xOffset[0]);
                stage.setY(e.getScreenY() - yOffset[0]);
            });
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }


    @FXML
    void orderProducts(MouseEvent event) {
        Commande orderTab = listCommande.getSelectionModel().getSelectedItem();
        if (orderTab != null) {
            final double[] xOffset = new double[1];
            final double[] yOffset = new double[1];
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ProductCart.fxml"));
            try {
                loader.load();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            ProductCartController pcController = loader.getController();
            pcController.setRecords(orderTab.getId());
            Parent parent = loader.getRoot();
            Stage stage = new Stage();
            parent.setOnMousePressed(e -> {
                xOffset[0] = e.getSceneX();
                yOffset[0] = e.getSceneY();
            });

            parent.setOnMouseDragged(e -> {
                stage.setX(e.getScreenX() - xOffset[0]);
                stage.setY(e.getScreenY() - yOffset[0]);
            });
            stage.setScene(new Scene(parent));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            searchCommandes();
        } else {
            effect.setDisable(false);
            BoxBlur blur = new BoxBlur(3, 3, 3);
            JFXDialogLayout content = new JFXDialogLayout();
            content.setHeading(new Text("Error!!!"));
            content.setBody(new Text("Select the Item first !!"));
            JFXDialog fialog = new JFXDialog(effect, content, JFXDialog.DialogTransition.CENTER);
            JFXButton btn = new JFXButton("OK!");
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    fialog.close();
                    effect.setEffect(null);
                }
            });
            content.setActions(btn);
            effect.setEffect(blur);
            fialog.show();
        }
    }

    @FXML
    void suppOrder(MouseEvent event) {
        Commande order = listCommande.getSelectionModel().getSelectedItem();
        if (order != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Deleting Order");
            alert.setContentText("Do you really wanna delete this application ?");
            ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(okButton, cancelButton);
            alert.showAndWait().ifPresent(type -> {
                if (type == okButton) {
                    try {
                        new ServiceCommande().delete(order.getId());
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    searchCommandes();
                }
            });
        } else {
            effect.setDisable(false);
            BoxBlur blur = new BoxBlur(3, 3, 3);
            JFXDialogLayout content = new JFXDialogLayout();
            content.setHeading(new Text("Error!!!"));
            content.setBody(new Text("Select the Item first !!"));
            JFXDialog fialog = new JFXDialog(effect, content, JFXDialog.DialogTransition.CENTER);
            JFXButton btn = new JFXButton("OK!");
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    fialog.close();
                    effect.setEffect(null);
                }
            });
            content.setActions(btn);
            effect.setEffect(blur);
            fialog.show();
        }
    }

    @FXML
    void suppProduct(MouseEvent event) {
        Product prod = productGrid.getSelectionModel().getSelectedItem();
        if (prod != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Deleting Product");
            alert.setContentText("Do you really wanna delete this ?");
            ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(okButton, cancelButton);
            alert.showAndWait().ifPresent(type -> {
                if (type == okButton) {
                    try {
                        new ServiceProduit().delete(prod.getId());
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    searchProduits();
                }
            });
        } else {
            effect.setDisable(false);
            BoxBlur blur = new BoxBlur(3, 3, 3);
            JFXDialogLayout content = new JFXDialogLayout();
            content.setHeading(new Text("Error!!!"));
            content.setBody(new Text("Select the Item first !!"));
            JFXDialog fialog = new JFXDialog(effect, content, JFXDialog.DialogTransition.CENTER);
            JFXButton btn = new JFXButton("OK!");
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    fialog.close();
                    effect.setEffect(null);
                }
            });
            content.setActions(btn);
            effect.setEffect(blur);
            fialog.show();
        }
    }

    @FXML
    void editProd(MouseEvent event) {
        Product productTab = productGrid.getSelectionModel().getSelectedItem();
        if (productTab != null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Gui/Produit/AddProduct.fxml"));
            try {
                loader.load();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            AddProductController addPController = loader.getController();
            addPController.setUpdate(true);
            //addPController.setRecords(productTab.getId(), productTab.getRef(), productTab.getName(),
            //        productTab.getPrice(), productTab.getDescription(), productTab.getImage());
            Parent parent = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
            searchProduits();
        } else {
            effect.setDisable(false);
            BoxBlur blur = new BoxBlur(3, 3, 3);
            JFXDialogLayout content = new JFXDialogLayout();
            content.setHeading(new Text("Error!!!"));
            content.setBody(new Text("Select the Item first !!"));
            JFXDialog fialog = new JFXDialog(effect, content, JFXDialog.DialogTransition.CENTER);
            JFXButton btn = new JFXButton("OK!");
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    fialog.close();
                    effect.setEffect(null);
                }
            });
            content.setActions(btn);
            effect.setEffect(blur);
            fialog.show();
        }

    }

    @FXML
    void refresh(MouseEvent event) {
        if (event.getSource() == faRefresh) {
            showProduits();
        }
    }

    @FXML
    void refreshOrder(MouseEvent event) {
        if (event.getSource() == faRefreshOrder) {
            searchCommandes();
        }
    }

    public void showCommandes() {
        ObservableList<Commande> orders = null;
        try {
            orders = new ServiceCommande().getAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        listCommande.setItems(orders);
        listCommande.setCellFactory(studentListView -> new ListCellCommande());
    }

    public void showProduits() {
        ObservableList<Product> products = null;
        try {
            products = new ServiceProduit().readAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        productGrid.setItems(products);
        productGrid.setCellFactory(studentListView -> new BookController());
    }

    public void searchCommandes() {
        ObservableList<Commande> commandes = null;
        try {
            commandes = new ServiceCommande().getAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        FilteredList<Commande> filteredData = new FilteredList(commandes, p -> true);
        tfSearchOrder.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(client -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare first name and last name of every client with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (client.getDate().toLowerCase().contains(lowerCaseFilter)) {
                    return true; //filter matches date
                } else if (String.valueOf(client.getId()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; //filter matches ref
                } else if (String.valueOf(client.isState()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; //matches state
                }
                return false; //Does not match
            });
        });

        //Wrap the FilteredList in a SortedList.
        SortedList<Commande> sortedData = new SortedList<>(filteredData);

        //put the sorted list into the listview
        listCommande.setItems(sortedData);
        listCommande.setCellFactory(studentListView -> new ListCellCommande());
    }

    public void searchProduits() {
        ObservableList<Product> produits = null;
        try {
            produits = new ServiceProduit().readAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        FilteredList<Product> filteredData = new FilteredList(produits, p -> true);
        tfSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(client -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare first name and last name of every client with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (client.getNom().toLowerCase().contains(lowerCaseFilter)) {
                    return true; //filter matches date
                }
                return false; //Does not match
            });
        });

        //Wrap the FilteredList in a SortedList.
        SortedList<Product> sortedData = new SortedList<>(filteredData);

        //put the sorted list into the listview
        productGrid.setItems(sortedData);
        productGrid.setCellFactory(studentListView -> new BookController());
    }

    private List<Product> getProducts() {
        List<Product> lp = new ArrayList<>();
        ArrayList<Product> products = new ServiceProduit().getAll();

        for (int i = 0; i < products.size(); i++) {
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

}