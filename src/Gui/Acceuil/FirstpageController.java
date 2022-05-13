/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Acceuil;

import java.net.URL;
import java.util.ResourceBundle;


import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author souso
 */
public class FirstpageController implements Initializable {


    @FXML
    private BorderPane yt;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //showOffres();
        WebView webView = new WebView();
        webView.getEngine().load(
                "https://www.youtube.com/watch?v=5dE8MP0fYPk"
        );
        yt.setCenter(webView);
    }

}
