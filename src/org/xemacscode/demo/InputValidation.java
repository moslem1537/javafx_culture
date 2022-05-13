/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xemacscode.demo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**
 *
 * @author user
 */
public class InputValidation {
        public static boolean validTextField(String test) {
        return test.equals("");
    }

    public static int validPwd(String mdp) {
        int i = 0;
        if (!mdp.equals("")) {
            i = 1;
        }
        return i;
    }

    public static boolean validEmail(String email) {
        boolean status = false;

        String emailPattern = "^[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(CharSequence.class.cast(email));
        if (matcher.matches()) {
            status = true;
        }
        return status;
    }

    public static boolean validUsername(String username) {
        boolean status = false;

        String usernamePattern = "^[a-zA-Z]+[A-Za-z0-9-]*$";
        Pattern pattern = Pattern.compile(usernamePattern);
        Matcher matcher = pattern.matcher(CharSequence.class.cast(username));
        if (matcher.matches()) {
            status = true;
        }
        return status;
    }
    
    public static int isAge(String ch) {
        int i = 0;
        if (ch.length() == 2) {
            try {
                i = Integer.parseInt(ch);
            } catch (NumberFormatException e) {
                return i;
            }
        }
        return i;
    }

    public static int isPhoneNumber(String ch) {
        int i = 0;
        if (ch.length() == 8) {
            try {
                i = Integer.parseInt(ch);
            } catch (NumberFormatException e) {
                return i;
            }
        }
        return i;
    }
    public static boolean PReduction(String ch) {
        boolean status = false;
        String usernamePattern = "([0-9]*[.])?[0-9]";
        String usernamePattern2 = "[0-9]?[0-9]";
        Pattern pattern = Pattern.compile(usernamePattern);
        Matcher matcher = pattern.matcher(CharSequence.class.cast(ch));
        Pattern pattern2 = Pattern.compile(usernamePattern2);
        Matcher matcher2 = pattern.matcher(CharSequence.class.cast(ch));
        if (matcher.matches()||matcher2.matches()) {
            status = true;
        } 
        return status;
    }

    public Alert getAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        return alert;
    }
    public  int existence (String titre){
        int f=0;
                ObservableList<blogs> blogsList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM blogs";
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            blogs blogs;
            while(rs.next()){
                blogs = new blogs(rs.getInt("id"), rs.getString("titre"), rs.getString("cnt"));
                blogsList.add(blogs);
                if(blogs.getTitre()==titre){
                    f=1;
                    
                }
            }
                
        }catch(Exception ex){
            ex.printStackTrace();
        }

           
        
        
        return f;
    
}
        public Connection getConnection(){
        Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/culture-website-db?useLegacyDatetimeCode=false&serverTimezone=CET", "root","");
            return conn;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
            private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
}
