package user;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;


public class DAO {  //dao d'une classe est le controleur de cette classe qui fait tout modification suppression concernant cette classe
     Connection con;
    DAO(String url, String username, String password) {
        con= MyConnection.getConnection(url,username,password);
    }

//*************Produit DAO
public int insertProduit(int id, String nom, float prix,float qte) {
    String req1 = "insert into produit values (?,?,?,?)";
    try {
        PreparedStatement ps = con.prepareStatement(req1); // Statement executer des requetes
        ps.setInt(1,id);     //en javasql les indices commence par 1
        ps.setString(2,nom);
        ps.setFloat(3,prix);
        ps.setFloat(4,qte);
        return ps.executeUpdate();    //return int 0 echec 1 succes
    } catch (SQLException e) {
        return 0;       //en cas d'echec return 0
    }
}

    public int modifierProduit(int id, String nom, float prix,float qte) {
        String req = "UPDATE produit SET nom = ?, prix = ?, qte = ? WHERE idP = ?";
        try {
            PreparedStatement ps = con.prepareStatement(req);
            ps.setInt(4,id);  //5 est la position de ?  qui concerne le idf dans req
            ps.setString(1,nom);
            ps.setFloat(2,prix);
            ps.setFloat(3,qte);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int supprimerProduit(int id) {
        String req="Delete from produit where idP = ?";
        try{
            PreparedStatement ps = con.prepareStatement(req);
            ps.setInt(1,id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }





    //********Client DAO
    public int insertClient(int id, String nom,String prenom, float total) {
        String req1 = "insert into client values (?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(req1); // Statement executer des requetes
            ps.setInt(1,id);     //en javasql les indices commence par 1
            ps.setString(2,nom);
            ps.setString(3,prenom);
            ps.setFloat(4,total);
            return ps.executeUpdate();    //return int 0 echec 1 succes
        } catch (SQLException e) {
            return 0;       //en cas d'echec return 0
        }
    }

    public int modifierClient(int id, String nom,String prenom, float total) {
        String req = "UPDATE client SET nom = ?, prenom = ?, total = ? WHERE idC = ?";
        try {
            PreparedStatement ps = con.prepareStatement(req);
            ps.setInt(4,id);  //5 est la position de ?  qui concerne le idf dans req
            ps.setString(1,nom);
            ps.setString(2,prenom);
            ps.setFloat(3,total);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int supprimerClient(int id) {
        String req="Delete from client where idC = ?";
        try{
            PreparedStatement ps = con.prepareStatement(req);
            ps.setInt(1,id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }
//************Panier DAO
    public int insertPanier(int id,float qte, float montant,Date dateP, int idc, int idp) {
        String req1 = "insert into panier values(?,?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(req1); // Statement executer des requetes
            ps.setFloat(1,id);   //en javasql les indices commence par 1
            ps.setFloat(2,qte);
            ps.setFloat(3,montant);
            ps.setDate(4,dateP);
            ps.setInt(5,idc);
            ps.setInt(6,idp);
            return ps.executeUpdate();    //return int 0 echec 1 succes
        } catch (SQLException e) {
            return 0;       //en cas d'echec return 0
        }
    }
//*********affichage de donnees
public ResultSet selection(String req)  {
    try {
        Statement st=con.createStatement();  //methode traja3li resultset=l'ensemnle des lignes de la base
        return st.executeQuery(req);
    } catch (SQLException e) {
        return null;
    }
}
//**********Recu
public String affichePanier(ResultSet rs) {
        try {
            while (rs.next()) {
                int id = rs.getInt(1);
                float qte = rs.getFloat(2);
                float total = rs.getFloat(3);
                Date date = rs.getDate(4);
                int idc=rs.getInt(5);
                int idp=rs.getInt(6);
                return("Id panier : "+id+"Qte : "+qte+"Total : "+total+"Date : "+date+"Id client : "+idc+"Id produit : "+idp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "";
    }

}
