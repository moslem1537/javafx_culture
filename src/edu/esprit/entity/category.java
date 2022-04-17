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
public class category {
     private int id;
    private String nom;
    private String descrption;

    public category(int id, String nom, String descrption) {
        this.id = id;
        this.nom = nom;
        this.descrption = descrption;
    }

    public category(String nom, String descrption) {
        this.nom = nom;
        this.descrption = descrption;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescrption() {
        return descrption;
    }

    public void setDescrption(String descrption) {
        this.descrption = descrption;
    }

    @Override
    public String toString() {
        return "category{" + "id=" + id + ", nom=" + nom + ", descrption=" + descrption + '}';
    }

    
}
