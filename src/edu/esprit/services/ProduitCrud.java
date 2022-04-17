/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;


import edu.esprit.entity.produit;
import edu.esprit.utils.MyConnection;
import java.sql.SQLDataException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
             String req="INSERT INTO produit(id, cathegorie_id, designation, prix, quantite, image ) values(?,?,?,?,?,?)";
             
             PreparedStatement ps1 =  cnx.prepareStatement(req);
             ps1.setInt(1,p.getId());
             ps1.setString(2, p.getDesignation());
             ps1.setString(3, p.getPrix());
             ps1.setInt(4, p.getQuantite());
             ps1.setString(5, p.getImage());
             ps1.setInt(6, p.getCathegorie_id());
                System.out.println("votre produit est ajoute avec succes");
             ps1.executeUpdate();
             //System.out.println("votre produit est ajoute avec succes");
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());
            
         }

    }
     public void ModifierProduit(produit p) {
        try {
            String req = "UPDATE produit SET type= ?, id=?, designation = ?,prix = ?,quantite = ?,image = ?,cathegorie_id WHERE id= ?";
            PreparedStatement ps1 =   new MyConnection().cnx.prepareStatement(req);
       
            ps1.setString(1, p.getDesignation());
            ps1.setString(2, p.getPrix());
        ps1.setInt(3, p.getQuantite());
        ps1.setString(4, p.getImage());
        ps1.setInt(5, p.getCathegorie_id());
         
           System.out.println("Modification...");
                
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

   

        }

    

   
        
        
        


    



