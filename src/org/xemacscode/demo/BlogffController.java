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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class BlogffController implements Initializable {

    @FXML
    private TilePane tilePaneId;
     public ObservableList<blogs> afficherPublicationFront = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                try {
            // TODO
            afficherPublicationFront();
        } catch (IOException ex) {
            Logger.getLogger(hg.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO
    }
        public ObservableList<blogs> getblogsList(){
        ObservableList<blogs> blogsList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM blogs";
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            blogs blogs;
            while(rs.next()){
                blogs = new blogs(rs.getInt("id"), rs.getString("titre"), rs.getString("cnt"),rs.getString("image"));
                blogsList.add(blogs);
            }
                
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return blogsList;
    }
    
    private void afficherPublicationFront() throws IOException {
        //PublicationController pc = new PublicationController();
        //List<Publication> publications = pc.afficherPublications();
        ObservableList<blogs> blogs = getblogsList();
        if (!blogs.isEmpty()) {
            for (int i = 0; i < blogs.size(); i++) {
                afficherPublicationFront.add(blogs.get(i));

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("blogmin.fxml"));
                AnchorPane pane = fxmlLoader.load();
                BlogminController cd = fxmlLoader.getController();
                cd.setData(blogs.get(i));
                //cd.setData(blogs.get(i));
                tilePaneId.getChildren().addAll(pane);
            }
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
    
}
