/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Entities.Utilisateur;
import DB.MyDB;
import GUI.LoginController;
import java.security.NoSuchAlgorithmException;
import IService.IServiceUtilisateur;
import java.security.MessageDigest;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author takwa
 */
public class UtilisateurService implements IServiceUtilisateur {

    Connection connexion;

    public UtilisateurService() {
        connexion = MyDB.getInstance().getConnection();
    }

    public UtilisateurService(String text, int parseInt, String text0, String text1, Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouterUser(Utilisateur u) throws SQLException {
        String req = "INSERT INTO `users` (`name`,`lastname`,`email`,`password`,`phone`,`gender`,`birthday`) "
                + "VALUES (?,?,?,?,?,?,?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setString(1, u.getName());
        ps.setString(2, u.getLastName());
        ps.setString(3, u.getEmail());
       try {
            ps.setString(4, hashmdp(u.getPassword()));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        ps.setInt(5, u.getPhone());
        ps.setString(6, u.getGender());
        ps.setDate(7, java.sql.Date.valueOf(String.format("%1$tY-%1$tm-%1$td",u.getBirthday())));
        ps.executeUpdate();
    }
    ////////

    @Override
    public String hashmdp(String mdp) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(mdp.getBytes());

        byte byteData[] = md.digest();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            String hex = Integer.toHexString(0xff & byteData[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }

    //////////
    @Override
    public List<Utilisateur> AfficherAllUsers() throws SQLException {

        List<Utilisateur> users = new ArrayList<>();
        String req = "select * from users ";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            if (LoginController.connectedUser.getId() != rst.getInt("id")) {

                Utilisateur u = new Utilisateur(
                        rst.getInt("id"),
                        rst.getString("name"),
                        rst.getString("lastname"),
                        rst.getInt("phone"),
                        rst.getString("email"),
                        rst.getString("password"),
                        rst.getString("gender"),
                        rst.getDate("birthday"),
                        rst.getBoolean("isBlocked")
                );
                System.out.println(u.toString());
                        
                users.add(u);
            }
        }
        return users;
    }

    @Override
    public void SupprimerUser(Utilisateur u) throws SQLException {

        String req = "DELETE FROM users WHERE id =?";
        try {
            PreparedStatement ps = connexion.prepareStatement(req);
            ps.setInt(1, u.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
        }
    }

    @Override
    public void changerMotDePasse(String email, String nouveauMdp) throws SQLException, NoSuchAlgorithmException {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connexion.prepareStatement(
                    "UPDATE `users` "
                    + "SET `password` = ? "
                    + "WHERE `email` = ?");
            preparedStatement.setString(1, hashmdp(nouveauMdp));
            preparedStatement.setString(2, email);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("Erreur de modification user : " + e.getMessage());
        }
    }

    @Override
    public boolean verifierEmailExistant(String email) throws SQLException {

        String req = "select  * from  users u  where u.email LIKE'" + email + "'";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);
        return rst.next();
    }

    @Override
    public void modifierUser(Utilisateur u) throws SQLException, NoSuchAlgorithmException {
        String req = "UPDATE users SET "
                + " name='" + u.getName()+ "',"
                + " lastname='" + u.getLastName()+ "'"
                + ", phone=" + u.getPhone()+ ""
                + ", gender='" + u.getGender()+ "' where id  = " + u.getId() + "";
        Statement stm = connexion.createStatement();
        stm.executeUpdate(req);
    }

    @Override
    public List<Utilisateur> AfficherUsersTriéParNom() throws SQLException {

        List<Utilisateur> users = new ArrayList<>();
        String req = "select * from users order by name ";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Utilisateur u = new Utilisateur(
                    rst.getInt("id"),
                        rst.getString("name"),
                        rst.getString("lastname"),
                        rst.getInt("phone"),
                        rst.getString("email"),
                        rst.getString("password"),
                        rst.getString("gender"),
                        rst.getDate("birthday"),
                        rst.getBoolean("isBlocked")
            );
            users.add(u);
        }
        return users;
    }

    @Override
    public List<Utilisateur> RechercherUsersParNom(String Nom) throws SQLException {
        List<Utilisateur> users = new ArrayList<>();
        String req = "select  * from  users u  where u.name LIKE'" + Nom + "'";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);
        while (rst.next()) {
            Utilisateur u = new Utilisateur(
                   rst.getInt("id"),
                        rst.getString("name"),
                        rst.getString("lastname"),
                        rst.getInt("phone"),
                        rst.getString("email"),
                        rst.getString("password"),
                        rst.getString("gender"),
                        rst.getDate("birthday"),
                        rst.getBoolean("isBlocked")
            );
            users.add(u);
        }
        return users;

    }

    @Override
    public List<Utilisateur> AfficherParRole(String Genre) throws SQLException {
        List<Utilisateur> users = new ArrayList<>();
        String req = "select  * from  users u  where u.gender LIKE'" + Genre + "'";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);
        while (rst.next()) {
            Utilisateur u = new Utilisateur(
                    rst.getInt("id"),
                        rst.getString("name"),
                        rst.getString("lastname"),
                        rst.getInt("phone"),
                        rst.getString("email"),
                        rst.getString("password"),
                        rst.getString("gender"),
                        rst.getDate("birthday"),
                        rst.getBoolean("isBlocked")
            );
            users.add(u);
        }
        return users;

    }

    @Override
    public int nbUsersParRole(String Genre) throws SQLException {
        List<Utilisateur> users = new ArrayList<>();
        String req = "select  * from  users u  where u.gender LIKE'" + Genre + "'";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);
        while (rst.next()) {
            Utilisateur u = new Utilisateur(
                    rst.getInt("id"),
                        rst.getString("name"),
                        rst.getString("lastname"),
                        rst.getInt("phone"),
                        rst.getString("email"),
                        rst.getString("password"),
                        rst.getString("gender"),
                        rst.getDate("birthday"),
                        rst.getBoolean("isBlocked")
            );
            users.add(u);
        }
        return users.size();
    }

    public List afficher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void bloquerUtilisateur(int idUtilisateur) throws SQLException {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connexion.prepareStatement(
                    "UPDATE `users` "
                    + "SET `isBlocked` = ? "
                    + "WHERE `id` = ?");
            preparedStatement.setInt(1, 1);
            preparedStatement.setInt(2, idUtilisateur);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("Erreur de modification user : " + e.getMessage());
        }
    }

    @Override
    public void debloquerUtilisateur(int idUtilisateur) throws SQLException {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connexion.prepareStatement(
                    "UPDATE `users` "
                    + "SET `isBlocked` = ? "
                    + "WHERE `id` = ?");
            preparedStatement.setInt(1, 0);
            preparedStatement.setInt(2, idUtilisateur);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("Erreur de modification user : " + e.getMessage());
        }
    }

}
