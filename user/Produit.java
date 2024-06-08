package user;

public class Produit {
    int id;
    float prix,qte;
    String nom;

    public Produit(int id, float prix, float qte, String nom) {
        this.id = id;
        this.prix = prix;
        this.qte = qte;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public float getPrix() {
        return prix;
    }

    public float getQte() {
        return qte;
    }

    public String getNom() {
        return nom;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setQte(float qte) {
        this.qte = qte;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", nom='" + nom +
        ", prix=" + prix +
                ", qte=" + qte +
                 '\'' +
                '}';
    }
}
