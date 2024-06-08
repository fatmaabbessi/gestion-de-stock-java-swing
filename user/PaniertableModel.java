package user;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaniertableModel extends AbstractTableModel {
    ArrayList<Object[]> data=new ArrayList<Object[]>();
    ResultSetMetaData rsmd;
    DAO dao;

    PaniertableModel(ResultSet rs, DAO dao)
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
    //***********projet
static int i=0;
   public void insertPanier(float qte, float montant, Date dateP, int idc, int idp)  {
       PaniertableModel.i=i+1;
        int a=dao.insertPanier(i,qte,montant,dateP,idc,idp);
        if(a>0) {
            data.add(new Object[]{i,qte,montant,dateP,idc,idp});
            //refraiche (de l'affichage)
            fireTableDataChanged();
            JOptionPane.showMessageDialog(null,"done");
        }
        else
            JOptionPane.showMessageDialog(null,"error detected");
    }
}

