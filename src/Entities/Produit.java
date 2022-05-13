/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author admin
 */
public class produit {
      private int id;
    private String designation;
    private String prix;
    private int quantite;
    private String image;
    private int cathegorie_id;
    category Category;

    public produit(int id, String designation, String prix, int quantite, String image, int cathegorie_id) {
        this.id = id;
        this.designation = designation;
        this.prix = prix;
        this.quantite = quantite;
        this.image = image;
        this.cathegorie_id = cathegorie_id;
    }

    public produit(String designation, String prix, int quantite, String image, int cathegorie_id) {
        this.designation = designation;
        this.prix = prix;
        this.quantite = quantite;
        this.image = image;
        this.cathegorie_id = cathegorie_id;
    }

    
  
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCathegorie_id() {
        return cathegorie_id;
    }

    public void setCathegorie_id(int cathegorie_id) {
        this.cathegorie_id = cathegorie_id;
    }

    public category getCategory() {
        return Category;
    }

    public void setCategory(category Category) {
        this.Category = Category;
    }

    public produit(int id,int quantite, String designation, String image,String prix, category Category) {
        this.id = id;
        this.quantite = quantite;
   
        this.designation = designation;
         this.image = image;
        this.prix = prix;
       
        this.Category = Category;
    }

    public produit() {
    }
    

    @Override
    public String toString() {
        return "produit{" + "id=" + id + "\n designation=" + designation + "\n prix=" + prix + "\n quantite=" + quantite + "\n image=" + image + "\n cathegorie_id=" + cathegorie_id + '}';
    }
     

}