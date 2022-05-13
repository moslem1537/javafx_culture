/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author takwa
 */
public class Utilisateur {

    private int id;
    /* a ne pas utiliser */
    private String name;
    private String lastName;
    private int phone;
    private String email;
    private String password;
    private String gender;
    private Date birthday;
    private boolean isBlocked;

    public Utilisateur() {
    }

     public Utilisateur(int id, String name, String lastName, int phone, String gender, Date birthday, boolean isBlocked) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.gender = gender;
        this.birthday = birthday;
        this.isBlocked = isBlocked;
    }
    public Utilisateur(int id, String name, String lastName, int phone, String email, String password, String gender, Date birthday, boolean isBlocked) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.birthday = birthday;
        this.isBlocked = isBlocked;
    }

    public Utilisateur(String name, String lastName, int phone, String email, String password, String gender, Date birthday) {
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public boolean isIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", name=" + name + ", lastName=" + lastName + ", phone=" + phone + ", email=" + email + ", password=" + password + ", gender=" + gender + ", birthday=" + birthday + ", isBlocked=" + isBlocked + '}';
    }
    

}
   
