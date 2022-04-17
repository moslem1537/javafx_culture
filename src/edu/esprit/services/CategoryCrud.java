/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;


import edu.esprit.entity.category;
import edu.esprit.utils.MyConnection;
import java.sql.SQLDataException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTML;




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
            String req1 = "UPDATE categorie SET type= ?, nom = ?,descrption = ? WHERE id= ?";
            PreparedStatement pst=   new MyConnection().cnx.prepareStatement(req1);
       
            pst.setString(1, c.getNom() );
      
            
            pst.setString(2, c.getDescrption());   
            System.out.println("Modification...");
            pst.executeUpdate();
            System.out.println("Une categorie modifiée dans la table...");
        } catch (SQLException ex) {
        }
     }
  public void SupprimerCategory(int id) {
        try {
            String req1 = "DELETE FROM categorie WHERE id = ?";
            PreparedStatement pst =    new MyConnection().cnx.prepareStatement(req1);
            System.out.println("Suppression...");
            pst.setInt(1, id);

            pst.executeUpdate();
            System.out.println("Une categorie supprime...");
        } catch (SQLException ex) {

        }

    }

    

   
        
        
        
    }

        

    
         

 



   
  



