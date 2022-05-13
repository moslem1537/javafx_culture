/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xemacscode.demo;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class BlogminController implements Initializable {

    @FXML
    private ImageView image;
    @FXML
    private Label title;
    private blogs p;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button lire;
    private Parent root;
    private String mm;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Deprecated
    public void setData (blogs pub) {
        this.p= pub;
        title.setText(pub.getTitre());
       // mm.setText(p.getImage());
        
        File f = new File("G:\\JobHub-Desktop-Application\\src\\Image\\"+p.getImage());
        System.out.println(f);
        Image i = new Image(f.toURI().toString());
        System.out.println(i);
        image.setImage(i);
        image.setPreserveRatio(true);
        image.fitWidthProperty().bind(rootPane.widthProperty());
        image.fitHeightProperty().bind(rootPane.heightProperty());
    }

    @FXML
    private void lire(ActionEvent event) throws IOException, SQLException {
                // blogs blog = blogsview.getSelectionModel().getSelectedItem();
        mm=title.getText();
 
FXMLLoader loader =new FXMLLoader(getClass().getResource("blogd.fxml"));
root =loader.load();
BlogdController blogController=loader.getController();
blogController.showInformation(mm);
Stage stage=new Stage();
stage.setScene(new Scene(root));
stage.show();
    }
    
}
