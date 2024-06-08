package user;

import java.util.Date;

public class Panier {
    int id,idp,idcl;
    float qte,total;
    Date date;

    public Panier(int id, int idp, int idcl, float qte, float total, Date date) {
        this.id = id;
        this.idp = idp;
        this.idcl = idcl;
        this.qte = qte;
        this.total = total;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Panier{" +
                "id=" + id +
                ", idpoduit=" + idp +
                ", idclient=" + idcl +
                ", qte=" + qte +
                ", total=" + total +
                ", date=" + date +
                '}';
    }
}
