package Entities;

public class Product {
    private int id;
    private int cathegorie_id;
    private int prix;
    private int quantite;
    private String nom;
    private String img;

    //contructors begin
    public Product(){

    }

    public Product(int id, int cathegorie_id, int prix, int quantite, String nom,String img) {
        this.id = id;
        this.cathegorie_id = cathegorie_id;
        this.prix = prix;
        this.quantite = quantite;
        this.nom = nom;
        this.img = img;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Product(int cathegorie_id, int prix, int quantite, String nom, String img) {
        this.cathegorie_id = cathegorie_id;
        this.prix = prix;
        this.quantite = quantite;
        this.nom = nom;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCathegorie_id() {
        return cathegorie_id;
    }

    public void setCathegorie_id(int cathegorie_id) {
        this.cathegorie_id = cathegorie_id;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", cathegorie_id=" + cathegorie_id +
                ", prix=" + prix +
                ", quantite=" + quantite +
                ", nom='" + nom + '\'' +
                ", img='" + img + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }
}
