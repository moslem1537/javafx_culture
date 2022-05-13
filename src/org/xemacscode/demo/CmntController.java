/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xemacscode.demo;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class CmntController implements Initializable {

    @FXML
    private TextField tfid;
    @FXML
    private TextField tfname;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfcontenu;
    @FXML
    private TableView<cmnt> tvcmnt;
    @FXML
    private TableColumn<cmnt, Integer> colid;
    @FXML
    private TableColumn<cmnt, String> colname;
    @FXML
    private TableColumn<cmnt, String> colemail;
    @FXML
    private TableColumn<cmnt, String> colcontenu;
    @FXML
    private Button btndelete;
    @FXML
    private Button btnback;
    @FXML
    private TableColumn<?, ?> colid1;
    @FXML
    private Button btn_stat;
    private Parent root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showcmnt();
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if(event.getSource() == btndelete){
            deletecmnt();
        }
        
        
    }
        public Connection getConnection(){
        Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/culture-website-db?useLegacyDatetimeCode=false&serverTimezone=CET", "root","");
            return conn;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
            public ObservableList<cmnt> getcmntList(){
        ObservableList<cmnt> cmntList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM cmnts";
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            cmnt cmnt;
            while(rs.next()){
                cmnt = new cmnt(rs.getInt("id"), rs.getString("nickname"), rs.getString("email"),rs.getString("contenu"));
                cmntList.add(cmnt);
            }
                
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return cmntList;
    }
        public void showcmnt(){
        ObservableList<cmnt> list = getcmntList();
        
        colid.setCellValueFactory(new PropertyValueFactory<cmnt, Integer>("id"));
        colname.setCellValueFactory(new PropertyValueFactory<cmnt, String>("nickname"));
        colemail.setCellValueFactory(new PropertyValueFactory<cmnt, String>("email"));
        colcontenu.setCellValueFactory(new PropertyValueFactory<cmnt, String>("contenu"));
        tvcmnt.setItems(list);
    }
    private void deletecmnt(){
        String query = "DELETE FROM cmnts WHERE id =" + tfid.getText() + "";
        executeQuery(query);
        showcmnt();
        tfid.setText("" );
        tfname.setText( "");
        tfemail.setText("" );
        tfcontenu.setText("" );
    }
        private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @FXML
    private void handleMouseAction(MouseEvent event) {
                cmnt cmnt = tvcmnt.getSelectionModel().getSelectedItem();
        tfid.setText("" + cmnt.getId());
        tfname.setText( cmnt.getNickname());
        tfemail.setText( cmnt.getEmail());
        tfcontenu.setText( cmnt.getContenu());
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        Parent blog_parent = FXMLLoader.load(getClass().getResource("gy.fxml"));
        Scene blog_scene = new Scene(blog_parent);
        Stage app_stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        
        app_stage.setScene(blog_scene);
        app_stage.show();
    }

    @FXML
    private void StatAction(MouseEvent event) throws IOException {
                   /*  Parent blog_parent = FXMLLoader.load(getClass().getResource("Statistiques.fxml"));
        Scene blog_scene = new Scene(blog_parent);
        Stage app_stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        
        app_stage.setScene(blog_scene);
        app_stage.show();*/
          FXMLLoader loader =new FXMLLoader(getClass().getResource("Statistiques.fxml"));
root =loader.load();
Stage stage=new Stage();
stage.setScene(new Scene(root));
stage.show();
    }
    
}
