/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entity;

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

    @Override
    public String toString() {
        return "produit{" + "id=" + id + ", designation=" + designation + ", prix=" + prix + ", quantite=" + quantite + ", image=" + image + ", cathegorie_id=" + cathegorie_id + '}';
    }
    

}