package user;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.ResultSet;

public class AjouterPanier extends JFrame{
    private JLabel id,qte,date,total;
    private JTextField tid, tqte,tdate,ttotal;
    private JButton valider;
    int idclient;
    PaniertableModel model;
 public AjouterPanier(int idc){
     idclient=idc;
     initializeComponents();
     createLayout();
     actions();
 }
    private void initializeComponents() {
        id = new JLabel("Id :"); //id de produit
        qte = new JLabel("Qte :");
        date = new JLabel("Date :");
        total = new JLabel("Montant :");

        tid=new JTextField(20);
        tqte=new JTextField(20);
        tdate=new JTextField(20);
        ttotal=new JTextField(20);

        valider=new JButton("Valider");

        //***panier model
        DAO dao = new DAO(Config.URL,Config.USERNAME,Config.PASSWORD);
        String req = "select * from panier";
        ResultSet rs =dao.selection(req);
        model = new PaniertableModel(rs,dao);
    }
    private void createLayout() {

        setTitle("Panier");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600,500);
        //label
        id.setForeground(new Color(115,99,89));
        qte.setForeground(new Color(115,99,89));
        date.setForeground(new Color(115,99,89));
        total.setForeground(new Color(115,99,89));
        //button
        valider.setBackground(new Color(148,180,68));
        JPanel pbutton=new JPanel();
        pbutton.setBackground(new Color(204,206,173));
        pbutton.add(valider);
        //label textfield
        JPanel panel=new JPanel();
        panel.setBackground(new Color(204,206,173));
        panel.setLayout(new GridLayout(4,2));
        panel.add(id);
        panel.add(tid);
        panel.add(qte);
        panel.add(tqte);
        panel.add(date);
        panel.add(tdate);
        panel.add(total);
        panel.add(ttotal);
        //center panel
        JPanel pcenter=new JPanel();
        pcenter.setBackground(new Color(204,206,173));
        pcenter.setLayout(new GridLayout(2,1));
        pcenter.add(panel);
        pcenter.add(pbutton);
        add(pcenter);
        //autre panel
        JPanel north=new JPanel();
        north.setPreferredSize(new Dimension(100,100));
        north.setBackground(new Color(204,206,173));
        add(north,BorderLayout.NORTH);
        JPanel south=new JPanel();
        south.setPreferredSize(new Dimension(100,100));
        south.setBackground(new Color(204,206,173));
        add(south,BorderLayout.SOUTH);
        JPanel east=new JPanel();
        east.setPreferredSize(new Dimension(100,100));
        east.setBackground(new Color(204,206,173));
       add(east,BorderLayout.EAST);
        JPanel west=new JPanel();
        west.setPreferredSize(new Dimension(100,100));
        west.setBackground(new Color(204,206,173));
        add(west,BorderLayout.WEST);
        setVisible(true);

    }
    private void actions(){
     valider.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             if (tid.getText().equals("") || tqte.getText().equals("") || ttotal.getText().equals("") || tdate.getText().equals("")) {
                 JOptionPane.showMessageDialog(null, "vous devez remplir tous les champs", "Erreur:", JOptionPane.ERROR_MESSAGE);
             } else {
                 int idp = Integer.parseInt(tid.getText());
                 float qte = Float.parseFloat(tqte.getText());
                 float montant = Float.parseFloat(ttotal.getText());
                 Date datep = Date.valueOf(tdate.getText());
                 model.insertPanier(qte, montant, datep, idclient, idp);

             }
         }

     });
    }

}
