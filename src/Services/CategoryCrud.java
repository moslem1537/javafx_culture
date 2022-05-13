/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Entities.category;
import Utils.MyConnection;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLDataException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale.Category;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTML;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;




/**
 *
 * @author admin
 */
public class CategoryCrud {

   // connection cnx1;

    public CategoryCrud() {
      //cnx1=  (connection) MyConnection.getInstance().getConnection();
    }
    
     
    public void AjouterCategory(category c) {
        
        try {
             String req1="INSERT INTO categorie(id, nom, descrption ) values(?,?,?)";
             
             PreparedStatement ps =  new MyConnection().cnx.prepareStatement(req1);
             ps.setInt(1,c.getId());
             ps.setString(2, c.getNom());
             ps.setString(3, c.getDescrption());
             
                System.out.println("votre category est ajoute avec succes");
             ps.executeUpdate();
             //System.out.println("votre produit est ajoute avec succes");
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());
            
         }


    }
    
    
    
    
     public void ModifierCategory(category c) {
        try {
            String req1 = "UPDATE categorie SET  nom = ?,descrption = ? WHERE id= ?";
            PreparedStatement pst=   new MyConnection().cnx.prepareStatement(req1);
            
       
            pst.setString(1, c.getNom() );
            pst.setString(2, c.getDescrption());   
            pst.setInt(3, c.getId());
            System.out.println("Modification...");
            pst.executeUpdate();
            System.out.println("Une categorie modifiée dans la table...");
        } catch (SQLException ex) {
        }
     }
     
     
     
     
     
  public void SupprimerCategorie(int id) {
        try {
            String req1 = "DELETE FROM categorie WHERE id = ?";
            PreparedStatement pst =  new MyConnection().cnx.prepareStatement(req1);
            System.out.println("Suppression...");
            pst.setInt(1, id);
 
            pst.executeUpdate();
            System.out.println("Une categorie supprime...");
        } catch (SQLException ex) {

        }
  }

        
        
        public List<category> afficherCategory() {

        List<category> categories = new ArrayList<>();
        String req1 = "select * from categorie";
      
        try {
             PreparedStatement pst =    new MyConnection().cnx.prepareStatement(req1);
            ResultSet rs = pst.executeQuery(req1);
            while (rs.next()) {
                category c = new category();
                c.setId(rs.getInt("id"));
                c.setDescrption(rs.getString("descrption"));
                c.setNom(rs.getString("nom"));
              
                categories.add(c);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return categories;

    }
        
        
        
        
        public List<category> ChercherNom(String titreN) {
       List<category> list = new ArrayList<>();
        try{
            String req1 = "SELECT * FROM categorie where nom=?";
          PreparedStatement pst =    new MyConnection().cnx.prepareStatement(req1);
            System.out.println("RECHERCHE en cours ..");
            pst.setString(1,titreN);
            ResultSet rs = pst.executeQuery();
         
              System.out.println(titreN);
            while(rs.next()){
                category c = new category();
                c.setId(rs.getInt(1));
                c.setNom(rs.getString(2));
                 c.setDescrption(rs.getString(3));
              
               

               
                
                list.add(c);
            }
            
        }
        catch(SQLException e){
            
        }
        return list ;   
   

}
    
    
      public List<category> ChercherDESC(String titreN) {
       List<category> list = new ArrayList<>();
        try{
            String req1 = "SELECT * FROM categorie where descrption=?";
          PreparedStatement pst =    new MyConnection().cnx.prepareStatement(req1);
            System.out.println("RECHERCHE en cours ..");
            pst.setString(1,titreN);
            ResultSet rs = pst.executeQuery();
         
              System.out.println(titreN);
            while(rs.next()){
                category c = new category();
                c.setId(rs.getInt(1));
                c.setNom(rs.getString(2));
                 c.setDescrption(rs.getString(3));
              
               

               
                
                list.add(c);
            }
            
        }
        catch(SQLException e){
            
        }
        return list ;   
   

}
      
      public List<category> TrierNom() {

        List<category> list = new ArrayList<>();
        try {
            String req1 = "SELECT * FROM categorie order by nom desc";
             PreparedStatement pst =    new MyConnection().cnx.prepareStatement(req1);
            ResultSet rs = pst.executeQuery(req1);

            while (rs.next()) {

                category c = new category();
               c.setId(rs.getInt(1));
                  c.setNom(rs.getString(2));
                 c.setDescrption(rs.getString(3));

                list.add(c);

            }

        } catch (SQLException e) {

        }
        return list;
    }
      
      
       public List<category> TrierDESC() {

        List<category> list = new ArrayList<>();
        try {
            String req1 = "SELECT * FROM categorie order by descrption desc";
             PreparedStatement pst =    new MyConnection().cnx.prepareStatement(req1);
            ResultSet rs = pst.executeQuery(req1);

            while (rs.next()) {

                category c = new category();
               c.setId(rs.getInt(1));
                  c.setNom(rs.getString(2));
                 c.setDescrption(rs.getString(3));

                list.add(c);

            }

        } catch (SQLException e) {

        }
        return list;
    }
    public void generateExcel() throws FileNotFoundException, IOException 
         {
           String req1 = "select * from categorie";
        
        try {
       
           PreparedStatement pst =    new MyConnection().cnx.prepareStatement(req1);
               ResultSet rs = pst.executeQuery(req1);
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("categorie ");
            HSSFRow header = sheet.createRow(0);
            header.createCell(0).setCellValue("ID");
            header.createCell(1).setCellValue("Nom");
            header.createCell(2).setCellValue("Descrption");
            
            int index = 1;
            while (rs.next()) {
                HSSFRow row = sheet.createRow(index);
                row.createCell(0).setCellValue(rs.getString("id"));
                row.createCell(1).setCellValue(rs.getString("nom"));
                row.createCell(2).setCellValue(rs.getString("descrption"));
                
                index++;
            }
            FileOutputStream fileOut = new FileOutputStream("G:\\JobHub-Desktop-Application\\images\\Categorie.xls");
            wb.write(fileOut);
            fileOut.close();
           pst.close();
           rs.close();

        } catch (SQLException e) {
        } catch (IOException ex) {
            Logger.getLogger(CategoryCrud.class.getName()).log(Level.SEVERE, null, ex);
        }

       

    }
    public List<category> getCategory(String nom){
         List<category> myList = new ArrayList<>();
        try {
           
            String req1 ="SELECT * FROM categorie where nom='"+nom+"'";
            PreparedStatement pst =    new MyConnection().cnx.prepareStatement(req1);
            ResultSet rs = pst.executeQuery(req1);
            while(rs.next()){
            category Category = new category();
            Category.setId(rs.getInt(1));
            Category.setNom(rs.getString("nom"));
       
            myList.add(Category);          
            }                                
        } catch (SQLException ex) {
             System.err.println(ex.getMessage());
        }
          return myList;
     }
        
        
        
        
    }


        
        
    

        

    
         

 



   
  



