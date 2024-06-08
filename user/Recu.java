package user;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Recu extends JFrame {
    JLabel titre,total;
    JTextField ttotal;
    JButton imprimer;
    PaniertableModel model;
    JTable jt_panier;
    int idc;
    public Recu(int idc){
        this.idc=idc;
        initializeComponents();
        createLayout();
    }
    private void initializeComponents() {
        titre = new JLabel("Reçu ");
        total=new JLabel("Total :");
        ttotal=new JTextField(20);
        imprimer=new JButton("Imprimer");
    }
    private void createLayout() {
        setTitle("Recu");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 600);
        //titre
        titre.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        titre.setForeground(new Color(148, 180, 68));
        JPanel ptitre = new JPanel();
        ptitre.setPreferredSize(new Dimension(0, 120));
        ptitre.setBackground(new Color(204, 206, 173));
        ptitre.add(titre);
        add(ptitre, BorderLayout.NORTH);
        //label
        total.setForeground(new Color(115, 99, 89));
        total.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        //button
        imprimer.setBackground(new Color(148, 180, 68));
        JPanel pbutton = new JPanel();
        pbutton.setBackground(new Color(204, 206, 173));
        pbutton.setPreferredSize(new Dimension(60, 60));
        pbutton.add(imprimer, BorderLayout.SOUTH);
        //south
        JPanel psouth = new JPanel();
        psouth.setPreferredSize(new Dimension(600, 100));
        psouth.setLayout(new GridLayout(1, 3));
        psouth.setBackground(new Color(204, 206, 173));
        psouth.add(pbutton);
        psouth.add(total);
        psouth.add(ttotal);
        add(psouth, BorderLayout.SOUTH);
        //*********liste des paniers de client
        DAO dao = new DAO(Config.URL,Config.USERNAME,Config.PASSWORD);
        String req = "select * from panier where idClient="+idc;
        ResultSet rs =dao.selection(req);
        jt_panier = new JTable();
        add(new JScrollPane((jt_panier)));
        model = new PaniertableModel(rs,dao);
        jt_panier.setModel(model);
        setVisible(true);

        //**********event
            imprimer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    File F = new File("Recu.html");//F chemin vers un fichier
                    try {
                        FileWriter Fw = new FileWriter(F);
                        Fw.write("<h1>Reçu</h1>" +
                                "                          <p ><h2>Produit : </h2></p>" +
                                                           dao.affichePanier(rs)+
                                "                          <p ><h2>Total : " + ttotal.getText()+"</h2></p>" );  //code html
                        Fw.close();    //fermeture et sauvegarde de fichier
                        Desktop.getDesktop().open(F);

                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }

            });
    }


}
