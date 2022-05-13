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
public class cmnt {
      private Integer id;
      private String nickname;
      private String email;
    private String contenu;

    public cmnt(Integer id, String nickname, String email, String contenu) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.contenu = contenu;
    }
    
    

  

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
    
    
    
}
