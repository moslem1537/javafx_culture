/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xemacscode.demo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class GyController implements Initializable {

    @FXML
    private Button btnblogb;
    @FXML
    private Button btncmnt;
    @FXML
    private Button blogf;
    @FXML
    private Button blogff;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
       
                if(event.getSource() == btnblogb){
                           Parent blog_parent = FXMLLoader.load(getClass().getResource("blog.fxml"));
        Scene blog_scene = new Scene(blog_parent);
        Stage app_stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        
        app_stage.setScene(blog_scene);
        app_stage.show();
        }else if (event.getSource() == btncmnt){
                          Parent cmnt_parent = FXMLLoader.load(getClass().getResource("cmnt.fxml"));
        Scene cmnt_scene = new Scene(cmnt_parent);
        Stage app_stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        
        app_stage.setScene(cmnt_scene);
        app_stage.show();
        }else if (event.getSource() == blogf){
                          Parent cmnt_parent = FXMLLoader.load(getClass().getResource("blogf.fxml"));
        Scene cmnt_scene = new Scene(cmnt_parent);
        Stage app_stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        
        app_stage.setScene(cmnt_scene);
        app_stage.show();
        }
        
        
        
        
    }

    @FXML
    private void blogff(ActionEvent event) throws IOException {
        Parent blog_parent = FXMLLoader.load(getClass().getResource("blogff.fxml"));
        Scene blog_scene = new Scene(blog_parent);
        Stage app_stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        app_stage.setScene(blog_scene);
        app_stage.show();
    }
    
}
