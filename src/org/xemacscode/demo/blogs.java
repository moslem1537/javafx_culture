/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xemacscode.demo;

/**
 *
 * @author user
 */
public class blogs {
    private Integer id;
    private String titre;
    private String contenu;
    private String image;
    

    public blogs(Integer id, String titre, String contenu) {
        this.id = id;
        this.titre = titre;
        this.contenu = contenu;
    }

    public blogs(Integer id, String titre, String contenu, String image) {
        this.id = id;
        this.titre = titre;
        this.contenu = contenu;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public blogs(String titre) {
        this.titre = titre;
    }

    

    public Integer getId() {
        return id;
    }

    public blogs() {
    }

    public String getTitre() {
        return titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
    
}
