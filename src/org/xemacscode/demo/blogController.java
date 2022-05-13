/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xemacscode.demo;

import edu.db3a4.tools.MyConnection;
import java.awt.Desktop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import utils.InputValidation;


public class blogController implements Initializable {
    
    
    @FXML
    private Label tfId;
    @FXML
    private TextField tfTitle;
    
    
    @FXML
    private TableColumn<blogs, Integer> colId;
    @FXML
    private TableColumn<blogs, String> colTitle;
    
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    private FileChooser filechooser;
    private File file;
    private Stage stage;
    @FXML
    private AnchorPane anchorPane;
    private final Desktop desktop =Desktop.getDesktop();
    @FXML
    private TableColumn<blogs, String> image;
    private FileInputStream fis;
    PreparedStatement pst=null;
    Connection conn;
    @FXML
    private TextArea tfcontenu;
    @FXML
    private TableView<blogs> tvblogs;
    @FXML
    private TableColumn<blogs, String> colcontenu;
    @FXML
    private Button btnback;
    @FXML
    private TextField search;
    @FXML
    private Button formexel;
    @FXML
    private ImageView imageP;
    @FXML
    private Button ButtonImage;
    @FXML
    private Label pathPublication;
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException, SQLException {        
        
        if(event.getSource() == btnInsert){
            insertRecord();
           
           
            
        }else if (event.getSource() == btnUpdate){
            updateRecord();
        }else if(event.getSource() == btnDelete){
            deleteButton();
        }

        
            
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showblogs();
        //filechooser =new FileChooser();
////filechooser.getExtensionFilters().addAll(
//new FileChooser.ExtensionFilter("All files"."* *"),
/////new FileChooser.ExtensionFilter("Images","*png","*jpg","*gif")
//new FileChooser.ExtensionFilter("TextFile","*txt")
/////);
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
        String query = "SELECT * FROM blogs order by titre ";
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            blogs blogs;
            while(rs.next()){
                blogs = new blogs(rs.getInt("id"), rs.getString("titre"), rs.getString("cnt"), rs.getString("image"));
                blogsList.add(blogs);
            }
                
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return blogsList;
    }
    
    public void showblogs(){
        ObservableList<blogs> list = getblogsList();
        
        colId.setCellValueFactory(new PropertyValueFactory<blogs, Integer>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<blogs, String>("titre"));
        colcontenu.setCellValueFactory(new PropertyValueFactory<blogs, String>("contenu"));
        image.setCellValueFactory(new PropertyValueFactory<blogs, String>("image"));
        tvblogs.setItems(list);
        FilteredList<blogs> filteredData= new FilteredList<>(list,b->true);
search.textProperty().addListener((observable,oldValue,newValue)->{
filteredData.setPredicate(blogs->{
if(newValue.isEmpty()||  newValue==null){
return true;
}
String searchkeyword=newValue.toLowerCase();
if(blogs.getTitre().toLowerCase().indexOf(searchkeyword)> -1){
return true;
}else if (blogs.getContenu().toLowerCase().indexOf(searchkeyword)> -1){
return true;
}else
return false;
});
});
SortedList<blogs>sortedData=new SortedList<>(filteredData);
sortedData.comparatorProperty().bind(tvblogs.comparatorProperty());
tvblogs.setItems(sortedData);

    }
    private void insertRecord() throws FileNotFoundException, SQLException{
                 if (InputValidation.validTextField(tfTitle.getText())) {
            Alert alertNom = new InputValidation().getAlert("Titre", "Saisissez un titre pour votre blog ");
            alertNom.showAndWait();
        }
  
                          else if (existence (tfTitle.getText())==1) {
                Alert alertPrenom = new InputValidation().getAlert("blog existe", "ce blog existe deja ");
                alertPrenom.showAndWait();
            } 
                          
                                                    else if (existence (tfcontenu.getText())==1) {
                Alert alertPrenom = new InputValidation().getAlert("blog existe", "ce blog existe deja");
                alertPrenom.showAndWait();
            } 
                                                    else if (InputValidation.validTextField(tfcontenu.getText())) {
                Alert alertPrenom = new InputValidation().getAlert("Contenu", "Saisissez un contenu pour votre blog");
                alertPrenom.showAndWait();
            } 
                                  else if (InputValidation.validTextField(pathPublication.getText())) {
                Alert alertPrenom = new InputValidation().getAlert("Image", "Saisissez un image pour votre blog");
                alertPrenom.showAndWait();
            } 
                                  
                          else{
        Alert alertPrenom = new InputValidation().getAlert("Ajout", "Ajout Réussi");
        alertPrenom.showAndWait();
        System.out.println(pathPublication.getText());
        String query = "INSERT INTO blogs VALUES (default,'" + tfTitle.getText() + "','" + tfcontenu.getText() + "','" + pathPublication.getText() + "')";
        executeQuery(query);
        showblogs();
        tfId.setText("" );
        tfTitle.setText( "");
        tfcontenu.setText("" );
        imageP.setImage(null);
       // Glide.with(imageP).clear(imageP);
                          }
        
    /* String query ="insert into  blogs(titre,cnt) values(?,?)";
   // String query ="INSERT INTO blogs VALUES"+"(?,?,?)";
try {
   pst = conn.prepareStatement(query);
//pst.setString(1,tfId.getText());
pst.setString(1,tfTitle.getText());
pst.setString(2,tfAuthor.getText());
//fis = new FileInputStream(file);
//pst.setBinaryStream(4,(InputStream)fis,(int)file.length());
//pst.setBinaryStream(4,fis,file.length());
pst.execute();
//pst.close();
}  catch (SQLException ex) {
           System.out.println("erreur lors de l'ajout de l'annonce " + ex.getMessage());
        }*/

    }
    private void updateRecord(){
                         if (InputValidation.validTextField(tfTitle.getText())) {
            Alert alertNom = new InputValidation().getAlert("Titre", "Saisissez un titre pour votre blog ");
            alertNom.showAndWait();
        }
                         
                          else if (InputValidation.validTextField(tfcontenu.getText())) {
                Alert alertPrenom = new InputValidation().getAlert("Contenu", "Saisissez un contenu pour votre blog");
                alertPrenom.showAndWait();
            }
                          else if (InputValidation.validTextField(pathPublication.getText())) {
                Alert alertPrenom = new InputValidation().getAlert("Image", "Saisissez un image pour votre blog");
                alertPrenom.showAndWait();
            }                 
                                      else{
        Alert alertPrenom = new InputValidation().getAlert("Update", "Update Réussi");
        alertPrenom.showAndWait();             
        String query = "UPDATE  blogs SET titre  = '" + tfTitle.getText() + "', cnt = '" + tfcontenu.getText() + "', image = '" + pathPublication.getText() + "' WHERE id = " + tfId.getText() + "";
        executeQuery(query);
        showblogs();
        tfId.setText("" );
        tfTitle.setText( "");
        tfcontenu.setText("" );
        imageP.setImage(null);                  
                          
                          }
    }
    private void deleteButton(){
                Alert alertPrenom = new InputValidation().getAlert("Delete", "Delete Réussi");
        alertPrenom.showAndWait();
                String queryy = "DELETE FROM cmnts WHERE blog_id =" + tfId.getText() + "";
        executeQuery(queryy);
        String query = "DELETE FROM blogs WHERE id =" + tfId.getText() + "";
        executeQuery(query);
        showblogs();
        tfId.setText("" );
        tfTitle.setText( "");
        tfcontenu.setText("" );
        imageP.setImage(null );
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
        blogs blog = tvblogs.getSelectionModel().getSelectedItem();
        tfId.setText("" + blog.getId());
        tfTitle.setText( blog.getTitre());
        tfcontenu.setText( blog.getContenu());
       pathPublication.setText( blog.getImage());
          File f = new File("G:\\JobHub-Desktop-Application\\src\\Image\\"+blog.getImage());
        System.out.println(f);
        Image i = new Image(f.toURI().toString());
        System.out.println(i);
       imageP.setImage(i);
        imageP.setPreserveRatio(true);
        imageP.fitWidthProperty();
       imageP.fitHeightProperty();
        
        
    }
        public  int existence (String titre){
        int f=0;
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
                
                if(blogs.getTitre().equals(titre)){
                    f=1;
  
                }
                                if(blogs.getContenu().equals(titre)){
                    return 1;

                    
                    
                }
            }
                
        }catch(Exception ex){
            ex.printStackTrace();
        }
       // System.out.println(f);
        return f;
        
    
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
    private void formexel(MouseEvent event) {
        generateExcel();
        
    }
     public void generateExcel() 
         {  
           String sql = "select * from blogs";
        
        try {
         
      
              PreparedStatement ste=MyConnection.getInstance().getCnx().prepareStatement(sql);
               ResultSet rs = ste.executeQuery(sql);
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("blogs details ");
            HSSFRow header = sheet.createRow(0);
            header.createCell(0).setCellValue("ID");
            header.createCell(1).setCellValue("titre");
            header.createCell(2).setCellValue("contenu");

            int index = 1;
            while (rs.next()) {
                HSSFRow row = sheet.createRow(index);
                row.createCell(0).setCellValue(rs.getString("id"));
                row.createCell(1).setCellValue(rs.getString("titre"));
                row.createCell(2).setCellValue(rs.getString("cnt"));

                index++;
            }
            FileOutputStream fileOut = new FileOutputStream("G:\\JobHub-Desktop-Application\\images\\blogsDetails.Xls");
            wb.write(fileOut);
            fileOut.close();
           ste.close();
           rs.close();
          Desktop.getDesktop().open(new File("G:\\JobHub-Desktop-Application\\images\\blogsDetails.Xls"));

        } catch (SQLException e) {
        } catch (IOException ex) {
            Logger.getLogger(blogController.class.getName()).log(Level.SEVERE, null, ex);
        }

       

    }

    @FXML
    private void ajouterImageBack(ActionEvent event) {
                /*FileChooser image = new FileChooser();
        //image.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images Files", ".png",".jpg"));
        File f = image.showOpenDialog(null);
        if (f != null) {
            pathPublication.setText(f.getAbsolutePath());
            String sourcePath = f.getPath();
            String destinationPath = "C:\\Users\\user\\Desktop\\blogs\\src\\Image\\";
           
            File sourceFile = new File(sourcePath);
            File destinationFile = new File(destinationPath + sourceFile.getName());
            Image img = new Image(f.toURI().toString(), imageP.getFitWidth(),imageP.getFitHeight(),true,true);
            imageP.setImage(img);
            try {
                Files.copy(sourceFile.toPath(), destinationFile.toPath(), REPLACE_EXISTING);
                // Static Methods To Copy Copy source path to destination path
            } catch (Exception e) {
                System.out.println(e.getMessage());  // printing in case of error.
            }

        }*/
               FileChooser image = new FileChooser();
        //image.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images Files", ".png",".jpg"));
        File f = image.showOpenDialog(null);
        if (f != null) {
                String sourcePath = f.getPath();
            String destinationPath = "G:\\JobHub-Desktop-Application\\src\\Image\\";
            File sourceFile = new File(sourcePath);
            //File destinationFile = new File(destinationPath + sourceFile.getName());
           File destinationFile = new File( sourceFile.getName());
                       String i=sourceFile.getName();
            Image img = new Image(f.toURI().toString(), imageP.getFitWidth(),imageP.getFitHeight(),true,true);
            imageP.setImage(img);
           pathPublication.setText(i);
            try {
               // Files.copy(sourceFile.toPath(), destinationFile.toPath(), REPLACE_EXISTING);
                // Static Methods To Copy Copy source path to destination path
            } catch (Exception e) {
                System.out.println(e.getMessage());  // printing in case of error.
            }

        }         
    
        
    }



        
    
 }
