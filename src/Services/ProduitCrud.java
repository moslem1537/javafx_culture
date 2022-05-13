/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Entities.category;
import Entities.produit;
import Utils.MyConnection;
import static java.awt.SystemColor.text;
import javax.mail.Authenticator;
import java.net.PasswordAuthentication;
import java.sql.SQLDataException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.text.html.HTML;




/**
 *
 * @author admin
 */
public class ProduitCrud {
     private Connection cnx;

    public ProduitCrud() {
       cnx=MyConnection.getInstance().getConnection();
    }
     public void AjouterProduit(produit p) {
       
         try {
            
             
             
            String req = "INSERT INTO produit(id,cathegorie_id,designation,prix,quantite,image) VALUES" + "('" + p.getId() + "','" + p.getCategory().getId() + "','" + p. getDesignation()+ "','" + p.getPrix() + "','" + p.getQuantite()+"','" + p.getImage() + "')";

             PreparedStatement ps1 =   new MyConnection().cnx.prepareStatement(req);
            ps1.executeUpdate();
                System.out.println("votre produit est ajoute avec succes");
             ps1.executeUpdate();
             //System.out.println("votre produit est ajoute avec succes");
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());
            
            
         }

    }
     public void ModifierProduit(produit p) {
        try {
            String req = "UPDATE produit SET cathegorie_id=?,designation = ?,prix = ?,quantite = ?,image =?  WHERE id= ?";
            PreparedStatement ps1 =   new MyConnection().cnx.prepareStatement(req);
             ps1.setInt(6, p.getId()); 
       ps1.setString(2, p.getDesignation());
            ps1.setString(3, p.getPrix());
        ps1.setInt(4, p.getQuantite());
        ps1.setString(5, p.getImage());
       ps1.setInt(1, p.getCathegorie_id());
         
           //System.out.println("Modification...");
                
            ps1.executeUpdate();
                System.out.println("Une produit modifiée dans la table...");
        } catch (SQLException ex) {
        }
     }
    
  public void SupprimerProduit(int id) {
        
         try {
             String req = "DELETE FROM produit WHERE id = ?";
             PreparedStatement ps1 =    new MyConnection().cnx.prepareStatement(req);
             System.out.println("Suppression...");
             ps1.setInt(1, id);
             
             ps1.executeUpdate();
             System.out.println("Une produit supprimée dans la table...");
         } catch (SQLException ex) {
            
         }
        }
  
  
   public List<produit> afficherProduit() {

        List<produit> produits = new ArrayList<>();
        String req1 = "select * from produit";
      
        try {
             PreparedStatement pst =    new MyConnection().cnx.prepareStatement(req1);
            ResultSet rs = pst.executeQuery(req1);
            while (rs.next()) {
                produit p = new produit();
                p.setId(rs.getInt("id"));
                p.setPrix(rs.getString("prix"));
                  p.setDesignation(rs.getString("designation"));
               p.setCathegorie_id(rs.getInt("cathegorie_id"));
                 p.setImage(rs.getString("image"));
                  p.setQuantite(rs.getInt("quantite"));
              
             produits.add(p);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return  produits;

    }
         public List<produit> ChercherPrix(String titreN) {
       List<produit> list = new ArrayList<>();
        try{
            String req1 = "SELECT * FROM produit where prix=?";
          PreparedStatement pst =    new MyConnection().cnx.prepareStatement(req1);
            System.out.println("RECHERCHE en cours ..");
            pst.setString(1,titreN);
            ResultSet rs = pst.executeQuery();
         
              System.out.println(titreN);
            while(rs.next()){
                produit p= new produit();
                p.setId(rs.getInt(1));
                p.setDesignation(rs.getString(3));
                 p.setPrix(rs.getString(4));
                  p.setCathegorie_id(rs.getInt(2));
                   p.setQuantite(rs.getInt(5));
                p.setImage(rs.getString(6));
            
               

               
                
                list.add(p);
            }
            
        }
        catch(SQLException e){
            
        }
        return list ;   
   

}
    public List<produit> ChercherDSG(String titreN) {
       List<produit> list = new ArrayList<>();
        try{
            String req1 = "SELECT * FROM produit where designation =?";
          PreparedStatement pst =    new MyConnection().cnx.prepareStatement(req1);
            System.out.println("RECHERCHE en cours ..");
            pst.setString(1,titreN);
            ResultSet rs = pst.executeQuery();
         
              System.out.println(titreN);
            while(rs.next()){
                produit p= new produit();
                p.setId(rs.getInt(1));
                p.setDesignation(rs.getString(3));
                 p.setPrix(rs.getString(4));
                  p.setCathegorie_id(rs.getInt(2));
                   p.setQuantite(rs.getInt(5));
                p.setImage(rs.getString(6));
            
               

               
                
                list.add(p);
            }
            
        }
        catch(SQLException e){
            
        }
        return list ;   
   

}
public List<produit> TriPrix() {

 List<produit> list = new ArrayList<>();
        try {
            String req1 = "SELECT * FROM produit order by prix desc";
             PreparedStatement pst =    new MyConnection().cnx.prepareStatement(req1);
            ResultSet rs = pst.executeQuery(req1);

            while (rs.next()) {

                produit p= new produit();
                p.setId(rs.getInt(1));
                p.setDesignation(rs.getString(3));
                 p.setPrix(rs.getString(4));
                  p.setCathegorie_id(rs.getInt(2));
                   p.setQuantite(rs.getInt(5));
                p.setImage(rs.getString(6));
                list.add(p);

            }

        } catch (SQLException e) {

        }
        return list;

}
   
           public List<produit> TriQuantite() {

 List<produit> list = new ArrayList<>();
        try {
            String req1 = "SELECT * FROM produit order by quantite desc";
             PreparedStatement pst =    new MyConnection().cnx.prepareStatement(req1);
            ResultSet rs = pst.executeQuery(req1);

            while (rs.next()) {

                produit p= new produit();
                p.setId(rs.getInt(1));
                p.setDesignation(rs.getString(3));
                 p.setPrix(rs.getString(4));
                  p.setCathegorie_id(rs.getInt(2));
                   p.setQuantite(rs.getInt(5));
                p.setImage(rs.getString(6));
                list.add(p);

            }

        } catch (SQLException e) {

        }
        return list;

}
  public long recherche1()
  {
  List<produit> prod = afficherProduit();
      return  prod.stream().filter(b -> b.getQuantite() >= 75).filter(b -> b.getQuantite() <= 100).count();
  }
          
   public long recherche2()
  {
  List<produit> prod = afficherProduit();
      return  prod.stream().filter(b -> b.getQuantite() >= 25).filter(b -> b.getQuantite() <= 50).count();
  }
    public long recherche3()
  {
  List<produit> prod = afficherProduit();
      return  prod.stream().filter(b -> b.getQuantite() <= 25).count();
  }
    
    
    
    
    
    
    public void sendMail(String recipient) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        String MyAccountEmail = "roulece090@gmail.com";
        String password = "ahmed123456789";
      Session session =Session.getDefaultInstance(properties, new Authenticator() {
       protected javax.mail.PasswordAuthentication getPasswordAuthentication(){
       return new  javax.mail.PasswordAuthentication(MyAccountEmail, password);
       }
           
       }   
              );

        

        Message message = prepareMessage(session, MyAccountEmail, recipient);
        Transport.send(message);
        System.out.println("message sent successfully");

    }

    private Message prepareMessage(Session session, String MyAccountEmail, String recipient) {

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(MyAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Notification via votre application desktop");
            message.setText("cher client un nouveau produit est ajoutée a la liste");
            return message;
        } catch (Exception ex) {
            Logger.getLogger(ProduitCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
          
           
        }

    

   
        
        
        


    



