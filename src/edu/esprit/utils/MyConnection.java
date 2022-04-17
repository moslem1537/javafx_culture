/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Statement;


/**
 *
 * @author admin
 */



public class MyConnection {

   
    private String url="jdbc:mysql://localhost:3306/culture-website-db";
    private String login="root";
    private String pwd=""; 
   public Connection cnx;
   static MyConnection instance;
   
 
    
    public MyConnection(){
    
        try {
          cnx =  DriverManager.getConnection(url, login, pwd);
          System.out.println("connection etablie!");
        } catch (SQLException ex) {
           System.err.println(ex.getMessage());
        
       
    
        }
    }
    
    public Connection getConnection(){
        return  cnx;
    }

    
    public static MyConnection getInstance(){
        if(instance==null){
            instance = new MyConnection();
        }
        return instance;
        }
    }

    
    



