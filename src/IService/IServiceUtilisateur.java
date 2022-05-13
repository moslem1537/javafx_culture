/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;

import Entities.Utilisateur;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author takwa
 */
public interface IServiceUtilisateur {

    public void ajouterUser(Utilisateur u) throws SQLException;

    public void modifierUser(Utilisateur u) throws SQLException, NoSuchAlgorithmException;

    public void SupprimerUser(Utilisateur u) throws SQLException;

    public void changerMotDePasse(String email, String nouveauMDP) throws SQLException, NoSuchAlgorithmException;

    public boolean verifierEmailExistant(String email) throws SQLException;

    public String hashmdp(String mdp) throws NoSuchAlgorithmException;

    public List<Utilisateur> AfficherAllUsers() throws SQLException;

    public List<Utilisateur> AfficherUsersTriéParNom() throws SQLException;

    public List<Utilisateur> RechercherUsersParNom(String Nom) throws SQLException;

    public List<Utilisateur> AfficherParRole(String Genre) throws SQLException;

    public void bloquerUtilisateur(int idUtilisateur) throws SQLException;

    public void debloquerUtilisateur(int idUtilisateur) throws SQLException;

    public int nbUsersParRole(String Genre) throws SQLException;
    // public int nbUsersParGenre2(String Genre) throws SQLException;
}
