package user;

public class Client {
    int id;
    String nom,prenom;
    float montant;

    public Client(int id, String nom, String prenom, float montant) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.montant = montant;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public float getMontant() {
        return montant;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                ", montant=" + montant +
                '}';
    }
}
