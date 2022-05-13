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
import java.sql.SQLException;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class BlogfController implements Initializable {

    @FXML
    private TableView<blogs> blogsview;
    @FXML
    private TableColumn<blogs, String> titreview;
    private String mm;
    private String kk;
    private Parent root;
    @FXML
    private Button btnback;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showblogs();
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
                blogs = new blogs(rs.getInt("id"), rs.getString("titre"), rs.getString("cnt"));
                blogsList.add(blogs);
            }
                
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return blogsList;
    }
    
    public void showblogs(){
        ObservableList<blogs> list = getblogsList();
        
       
        titreview.setCellValueFactory(new PropertyValueFactory<blogs, String>("titre"));
        
        blogsview.setItems(list);
    }

    @FXML
    private void handleMouseAction(MouseEvent event) throws IOException, SQLException {
         blogs blog = blogsview.getSelectionModel().getSelectedItem();
        mm=blog.getTitre();
 
FXMLLoader loader =new FXMLLoader(getClass().getResource("blogd.fxml"));
root =loader.load();
BlogdController blogController=loader.getController();
blogController.showInformation(mm);
Stage stage=new Stage();
stage.setScene(new Scene(root));
stage.show();
                          /* Parent blog_parent = FXMLLoader.load(getClass().getResource("blogd.fxml"));
        Scene blog_scene = new Scene(blog_parent);
        Stage app_stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        
        app_stage.setScene(blog_scene);
        app_stage.show();*/
        
  
       
        
        

    }

    @FXML
    private void back(ActionEvent event) throws IOException {
                                   Parent blog_parent = FXMLLoader.load(getClass().getResource("gy.fxml"));
        Scene blog_scene = new Scene(blog_parent);
        Stage app_stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        
        app_stage.setScene(blog_scene);
        app_stage.show();
        
    }


    
}

