package Interfaces;

import Entities.Product;
import Entities.produit;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Mintoua
 */
public interface IServiceProduit {
  //  void add(Produit produit) throws SQLException;
   // boolean delete(int idProduit) throws SQLException;
   // public void update(Produit entity, AtomicReference<Produit> prod);
   // public void update2(Produit entity, int id);
    public ObservableList <Product> readAll() throws SQLException;
    public ArrayList<Product> getAll();
    boolean verifierQuantite(int idProduit, int quantite) throws SQLException;
}
