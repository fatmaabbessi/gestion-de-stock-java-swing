package user;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProduittableModel extends AbstractTableModel {
    ArrayList<Object[]>data=new ArrayList<Object[]>();
    ResultSetMetaData rsmd;
   DAO dao;

    ProduittableModel(ResultSet rs,DAO dao)
    {
        this.dao=dao;
        try {
            rsmd=rs.getMetaData();

            while(rs.next())
            {
                Object[] ligne=new Object[rsmd.getColumnCount()];
                for(int i=0;i<ligne.length;i++)
                {
                    ligne[i]=rs.getObject(i+1); //ResultSet de java sql ou les indices commence de 1
                    data.add(ligne);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        try {
            return rsmd.getColumnCount();
        } catch (SQLException e) {
            return 0;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex)[columnIndex];
    }
    @Override
    public String getColumnName(int col) {
        try {
            return rsmd.getColumnName(col+1);
        } catch (SQLException e) {
            return null;
        }
    }
    //********produit

    public void insertProduit(int id, String nom, float prix,float qte)  {
        int a= dao.insertProduit(id,nom,prix,qte);
        if(a>0) {
            data.add(new Object[]{id,nom,prix,qte});
            //refraiche (de l'affichage)
            fireTableDataChanged();
            JOptionPane.showMessageDialog(null,"done");
        }
        else
            JOptionPane.showMessageDialog(null,"error detected");
    }

    public int getRowindex(int id)
    { int i=0;
        while(i<data.size()) {
            if (Integer.parseInt(data.get(i)[0].toString())==id)
                return i;
            else
                i++;
        }
        return -1;
    }
   public void supprimerProduit(int id)  {

        int rowIndex=getRowindex(id);
        if(JOptionPane.showConfirmDialog(null,"voulez vous supprimer")==0)
        {
            int a=dao.supprimerProduit(id);////////////////////////////////////////////
            if(a>0){
                data.remove(rowIndex);
                fireTableDataChanged();
                JOptionPane.showMessageDialog(null,"done");
            }
            else JOptionPane.showMessageDialog(null,"error");
        }
        else
            JOptionPane.showMessageDialog(null,"cancel");
    }
    public void modifierProduit(int id, String nom, float prix,float qte)  {
        int a=dao.modifierProduit(id,nom,prix,qte);
        int rowindex=getRowindex(id);
        if(a>0) {
            data.get(rowindex)[0]=id;
            data.get(rowindex)[1]=nom;
            data.get(rowindex)[2]=prix;
            data.get(rowindex)[3]=qte;
            //refraiche (de l'affichage)
            fireTableDataChanged();
            JOptionPane.showMessageDialog(null,"done");
        }
        else
            JOptionPane.showMessageDialog(null,"error detected modification");
    }
}

