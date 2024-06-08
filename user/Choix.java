package user;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class Choix extends JFrame {

    JLabel choisir;
    JButton client,produit;
    JPanel ptitre,pcenter,pclient,pprod;
    Choix() {
        this.setTitle("choix");
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //*******pclient
        client=new JButton("Client");
        client.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        client.setForeground(Color.white);
        client.setBackground(new Color(148,180,68));
        JPanel pc=new JPanel();
        pc.setPreferredSize(new Dimension(50,50));
        pc.setBackground(new Color(204,206,173));
        pc.add(client);


        ImageIcon originalIconC = new ImageIcon("C:\\Users\\Lenovo\\Desktop\\gestion de stock\\client.png");
        JLabel imgC = new JLabel(originalIconC);

        pclient=new JPanel();
        pclient.setLayout(new GridLayout(2,1));
        pclient.setBackground(new Color(204,206,173));
        pclient.add(imgC);
        pclient.add(pc);


        //*********pprod
        ImageIcon originalIconP = new ImageIcon("C:\\Users\\Lenovo\\Desktop\\gestion de stock\\produit.png");
        JLabel imgP = new JLabel(originalIconP);
        produit=new JButton("Produit");
        produit.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        produit.setForeground(Color.white);
        produit.setBackground(new Color(148,180,68));

        JPanel pp=new JPanel();
        pp.setPreferredSize(new Dimension(50,50));
        pp.setBackground(new Color(204,206,173));
        pp.add(produit);


        pprod=new JPanel();
        pprod.setLayout(new GridLayout(2,1));
        pprod.setBackground(new Color(204,206,173));
        pprod.add(imgP);
        pprod.add(pp);

        //****ptitre
        choisir=new JLabel("choisir");
        choisir.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
        choisir.setForeground(new Color(166,200,224));
        ptitre=new JPanel();
        ptitre.setPreferredSize(new Dimension(0, 150));
        ptitre.setBackground(new Color(148,180,68));
        ptitre.add(choisir);

        //*****pcenter
        pcenter=new JPanel();
        pcenter.setLayout(new GridLayout(0,2));
        pcenter.add(pprod);
        pcenter.add(pclient);


         this.add(ptitre,BorderLayout.NORTH);
         this.add(pcenter);

        this.setVisible(true);

        //********events
        client.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    GestionClient cl=new GestionClient();

            }
        });
        produit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    GestionProduit pro=new GestionProduit();
            }
        });
    }
}
