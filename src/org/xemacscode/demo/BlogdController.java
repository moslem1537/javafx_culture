/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xemacscode.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextFlow;

/**
 * FXML Controller class
 *
 * @author user
 */
public class BlogdController  {

   
    private String mm;
    @FXML
    private Label tt;
    @FXML
    private TextArea cnt;
    @FXML
    private TableView<cmnt> aaaa;
    @FXML
    private TableColumn<cmnt, String> aa;
    private Integer jj;
    @FXML
    private TextField tfnickname;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfcontenu;
    @FXML
    private Label tfid;
    @FXML
    private Button btnajouter;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btndelete;
    @FXML
    private TableColumn<cmnt, String> colnickname;
    @FXML
    private TableColumn<cmnt, String> colemail;
    @FXML
    private TableColumn<cmnt, Integer> colid;
    @FXML
    private Button ImgBTN;
    @FXML
    private AnchorPane pdf;
    
    @FXML
    private ImageView imageview;
   
    

    /**
     * Initializes the controller class.
     */
    
    public void initialize(URL url, ResourceBundle rb) {
       
            // TODO
         aaaa();
            ss();
        
    } 

        public void aaaa(){
      
        String ll="kjhgg";
        tfemail.setText(ll);
    }
    public void ss(){
      
        String ll=tt.getText();
        tfnickname.setText(ll);
    }
  
 public void showInformation (String mm) throws SQLException{
    ObservableList<cmnt> cmntList = FXCollections.observableArrayList();
     tt.setText(mm); 
     
            Connection conn = getConnection();
       String query = "SELECT * FROM blogs WHERE titre = '" + mm + "'";
       Statement st;
        ResultSet rs;
       
         st = conn.createStatement();
            rs = st.executeQuery(query);
            blogs blogs;
            while(rs.next()){
                //String mm=rs.getString("cnt");

                blogs = new blogs(rs.getInt("id"), rs.getString("titre"), rs.getString("cnt"),rs.getString("image"));
                String mmm=blogs.getContenu();
                
                String imagee=blogs.getImage();
                        File f = new File("G:\\JobHub-Desktop-Application\\src\\Image\\"+imagee);
        System.out.println(f);
        Image i = new Image(f.toURI().toString());
        System.out.println(i);
        imageview.setImage(i);
        imageview.setPreserveRatio(true);
        imageview.fitWidthProperty();
        imageview.fitHeightProperty();
                
                
                
                
                
                
                
                
                
                cnt.setText(mmm);
                 jj =blogs.getId();
                //System.out.println(jj);
                
                }
            //Integer ff=28;
                   String queryy = "SELECT * FROM cmnts WHERE blog_id=" + jj + "";
       Statement stt;
        ResultSet rss;
       
         stt = conn.createStatement();
            rss = stt.executeQuery(queryy);
            cmnt cmnt;
            while(rss.next()){
               
                cmnt = new cmnt(rss.getInt("id"), rss.getString("nickname"), rss.getString("email"),rss.getString("contenu"));
               // String tt=cmnt.getContenu();
               
               cmntList.add(cmnt);
                //Integer rr =cmnt.getId();
               // System.out.println(rr);
               // aa.setText(tt)
                }
                 ObservableList<cmnt> listcmnt = cmntList;
                                aa.setCellValueFactory(new PropertyValueFactory<cmnt, String>("contenu"));
                colnickname.setCellValueFactory(new PropertyValueFactory<cmnt, String>("nickname"));
                colemail.setCellValueFactory(new PropertyValueFactory<cmnt, String>("email"));
                 colid.setCellValueFactory(new PropertyValueFactory<cmnt, Integer>("id"));
               ;
                aaaa.setItems(listcmnt);
   
     
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

    @FXML
    private void v(ActionEvent event) throws FileNotFoundException, SQLException {
        insertRecord();
    }
        private void insertRecord() throws FileNotFoundException, SQLException{
                             if (InputValidation.validTextField(tfnickname.getText())) {
            Alert alertNom = new InputValidation().getAlert("Nickname", "Saisissez votre nickname ");
            alertNom.showAndWait();
        }
                     else if (InputValidation.validTextField(tfcontenu.getText())) {
                Alert alertPrenom = new InputValidation().getAlert("Contenu", "Saisissez votre commentaire");
                alertPrenom.showAndWait();
            }        
                     else if
                         (!InputValidation.validEmail(tfemail.getText())) {
                    Alert alertEmail = new InputValidation().getAlert("Email", "Verifiez si votre adresse email est valide");
                    alertEmail.showAndWait();
                }
                                                  else if (InputValidation.validTextField(tfemail.getText())) {
                Alert alertPrenom = new InputValidation().getAlert("Contenu", "Saisissez votre commentaire");
                alertPrenom.showAndWait();
            } 
                             
                                                  else{        
         Connection conn = getConnection();
       String query = "SELECT * FROM blogs WHERE titre = '" + tt.getText() + "'";
       Statement st;
        ResultSet rs;
       
         st = conn.createStatement();
            rs = st.executeQuery(query);
            blogs blogs;
            while(rs.next()){
                //String mm=rs.getString("cnt");

                blogs = new blogs(rs.getInt("id"), rs.getString("titre"), rs.getString("cnt"));
                //String mmm=blogs.getContenu();
                //cnt.setText(mmm);
                 jj =blogs.getId();
        }  
      String queryy = "INSERT INTO cmnts VALUES (default,'" + tfnickname.getText() + "','" + tfemail.getText() + "','" + tfcontenu.getText() + "'," + jj + ")";
        executeQuery(queryy);
        ObservableList<cmnt> cmntList = FXCollections.observableArrayList();
         String queryyy = "SELECT * FROM cmnts WHERE blog_id=" + jj + "";
       Statement stt;
        ResultSet rss;
       
         stt = conn.createStatement();
            rss = stt.executeQuery(queryyy);
            cmnt cmnt;
            while(rss.next()){
               
                cmnt = new cmnt(rss.getInt("id"), rss.getString("nickname"), rss.getString("email"),rss.getString("contenu"));
               // String tt=cmnt.getContenu();
               
               cmntList.add(cmnt);
                //Integer rr =cmnt.getId();
               // System.out.println(rr);
               // aa.setText(tt)
                }
                 ObservableList<cmnt> listcmnt = cmntList;
                aa.setCellValueFactory(new PropertyValueFactory<cmnt, String>("contenu"));
                colnickname.setCellValueFactory(new PropertyValueFactory<cmnt, String>("nickname"));
                colemail.setCellValueFactory(new PropertyValueFactory<cmnt, String>("email"));
                 colid.setCellValueFactory(new PropertyValueFactory<cmnt, Integer>("id"));
                aaaa.setItems(listcmnt);
                                                  }
  
        }

    @FXML
    private void u(ActionEvent event) throws SQLException {
        updateRecord();
    }

    @FXML
    private void d(ActionEvent event) throws SQLException {
        deleteButton();
    }

    @FXML
    private void handleMouseAction(MouseEvent event) {
                cmnt cmnt = aaaa.getSelectionModel().getSelectedItem();
        tfid.setText("" + cmnt.getId());
        tfnickname.setText( cmnt.getNickname());
        tfemail.setText( cmnt.getEmail());
        tfcontenu.setText( cmnt.getContenu());
    }
        private void updateRecord() throws SQLException{
                                         if (InputValidation.validTextField(tfnickname.getText())) {
            Alert alertNom = new InputValidation().getAlert("Nickname", "Saisissez votre nickname ");
            alertNom.showAndWait();
        }
                     else if (InputValidation.validTextField(tfcontenu.getText())) {
                Alert alertPrenom = new InputValidation().getAlert("Contenu", "Saisissez votre commentaire");
                alertPrenom.showAndWait();
            }        
                     else if
                         (!InputValidation.validEmail(tfemail.getText())) {
                    Alert alertEmail = new InputValidation().getAlert("Email", "Verifiez si votre adresse email est valide");
                    alertEmail.showAndWait();
                }
                                                  else if (InputValidation.validTextField(tfemail.getText())) {
                Alert alertPrenom = new InputValidation().getAlert("Contenu", "Saisissez votre commentaire");
                alertPrenom.showAndWait();
            }
                                                  else{
        String query = "UPDATE  cmnts SET nickname  = '" + tfnickname.getText() + "', email = '" + tfemail.getText() + "', contenu = '" + tfcontenu.getText() + "' WHERE id = " + tfid.getText() + "";
        executeQuery(query);
        Connection conn = getConnection();
               String queryy = "SELECT * FROM blogs WHERE titre = '" + tt.getText() + "'";
       Statement st;
        ResultSet rs;
       
         st = conn.createStatement();
            rs = st.executeQuery(queryy);
            blogs blogs;
            while(rs.next()){
                //String mm=rs.getString("cnt");

                blogs = new blogs(rs.getInt("id"), rs.getString("titre"), rs.getString("cnt"));
                //String mmm=blogs.getContenu();
                //cnt.setText(mmm);
                 jj =blogs.getId();
        
        }
        
                ObservableList<cmnt> cmntList = FXCollections.observableArrayList();
         String queryyy = "SELECT * FROM cmnts WHERE blog_id=" + jj + "";
       Statement stt;
        ResultSet rss;
       
         stt = conn.createStatement();
            rss = stt.executeQuery(queryyy);
            cmnt cmnt;
            while(rss.next()){
               
                cmnt = new cmnt(rss.getInt("id"), rss.getString("nickname"), rss.getString("email"),rss.getString("contenu"));
               // String tt=cmnt.getContenu();
               
               cmntList.add(cmnt);
                //Integer rr =cmnt.getId();
               // System.out.println(rr);
               // aa.setText(tt)
                }
                 ObservableList<cmnt> listcmnt = cmntList;
                aa.setCellValueFactory(new PropertyValueFactory<cmnt, String>("contenu"));
                colnickname.setCellValueFactory(new PropertyValueFactory<cmnt, String>("nickname"));
                colemail.setCellValueFactory(new PropertyValueFactory<cmnt, String>("email"));
                 colid.setCellValueFactory(new PropertyValueFactory<cmnt, Integer>("id"));
                aaaa.setItems(listcmnt);
            tfid.setText("" );
        tfnickname.setText( "");
        tfemail.setText( "");
        tfcontenu.setText( "");
                                                  }
    }
            private void deleteButton() throws SQLException{
        String query = "DELETE FROM cmnts WHERE id =" + tfid.getText() + "";
        executeQuery(query);
                Connection conn = getConnection();
               String queryy = "SELECT * FROM blogs WHERE titre = '" + tt.getText() + "'";
       Statement st;
        ResultSet rs;
       
         st = conn.createStatement();
            rs = st.executeQuery(queryy);
            blogs blogs;
            while(rs.next()){
                //String mm=rs.getString("cnt");

                blogs = new blogs(rs.getInt("id"), rs.getString("titre"), rs.getString("cnt"));
                //String mmm=blogs.getContenu();
                //cnt.setText(mmm);
                 jj =blogs.getId();
        
        }
        
                ObservableList<cmnt> cmntList = FXCollections.observableArrayList();
         String queryyy = "SELECT * FROM cmnts WHERE blog_id=" + jj + "";
       Statement stt;
        ResultSet rss;
       
         stt = conn.createStatement();
            rss = stt.executeQuery(queryyy);
            cmnt cmnt;
            while(rss.next()){
               
                cmnt = new cmnt(rss.getInt("id"), rss.getString("nickname"), rss.getString("email"),rss.getString("contenu"));
               // String tt=cmnt.getContenu();
               
               cmntList.add(cmnt);
                //Integer rr =cmnt.getId();
               // System.out.println(rr);
               // aa.setText(tt)
                }
                 ObservableList<cmnt> listcmnt = cmntList;
                aa.setCellValueFactory(new PropertyValueFactory<cmnt, String>("contenu"));
                colnickname.setCellValueFactory(new PropertyValueFactory<cmnt, String>("nickname"));
                colemail.setCellValueFactory(new PropertyValueFactory<cmnt, String>("email"));
                 colid.setCellValueFactory(new PropertyValueFactory<cmnt, Integer>("id"));
                aaaa.setItems(listcmnt);
            tfid.setText("" );
        tfnickname.setText( "");
        tfemail.setText( "");
        tfcontenu.setText( "");

    }

    @FXML
    private void imgaction(MouseEvent event) {
                PrinterJob job = PrinterJob.createPrinterJob();
        System.out.println("aaaa");
 if(job != null){
     System.out.println("rr");
   job.printPage(pdf);
   job.endJob();
 }
    }
            
            
 
 
 
 
 
    
}
