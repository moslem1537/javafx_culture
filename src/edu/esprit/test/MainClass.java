/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.test;

import edu.esprit.entity.produit;
import edu.esprit.entity.category;
import edu.esprit.services.CategoryCrud;
import edu.esprit.services.ProduitCrud;
import edu.esprit.utils.MyConnection;
import java.sql.*;
import static javax.swing.text.html.HTML.Tag.P;


/**
 *
 * @author admin
 */
public class MainClass {


    
 public static void main (String [] args) throws SQLException{
    
      MyConnection mc= new MyConnection();
      CategoryCrud pcd=new CategoryCrud();
     category C= new category(1,"narje", "gouad");
    
     pcd.AjouterCategory(C);
      pcd.ModifierCategory(C);
      pcd.SupprimerCategory(1);
 
      ProduitCrud pcd1=new ProduitCrud();
     produit p=new produit("1","11",33,"1",10000);
    pcd1.AjouterProduit(p);
   pcd1.ModifierProduit(p);
   pcd1.SupprimerProduit(1);
      
 }

   
}
    



