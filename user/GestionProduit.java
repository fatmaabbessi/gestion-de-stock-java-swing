package user;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.sql.Date;
import java.sql.ResultSet;

public class GestionProduit extends JFrame {


        private JLabel id, nom,prix, qte,titre;
        private JTextField tid, tnom,tprix, tqte;
        private JButton ajoutprod,searchprod,modifyprod,deleteprod;
        private JPanel Apanel,Spanel,Mpanel,Dpanel,buttonpanel,psouth;
        ProduittableModel model;
        JTable jt_produit;
        public GestionProduit() {
            initializeComponents();
            createLayout();
            actions();
        }

        private void initializeComponents() {
            titre = new JLabel("Produit");
           id = new JLabel("Id :"); // id produit
           prix = new JLabel("Prix :"); // prix produit
            nom = new JLabel("Nom :"); // nom produit
            qte= new JLabel("Quantité :"); // quantite


            tid= new JTextField(20);
            tnom = new JTextField(20);
            tprix= new JTextField(20);
            tqte= new JTextField(20);




            ajoutprod = new JButton("Ajouter"); // "Add"
            searchprod = new JButton("Rechercher");
            modifyprod = new JButton("Modifier");
            deleteprod = new JButton("supprimer");
            //cancelButton = new JButton("Cancel");
        }

        private void createLayout()  {
            setTitle("gestion de produit");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setSize(1000,800);
            //pnorth

            titre.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
            titre.setForeground(new Color(166,200,224));
            //****ptitre
            JPanel ptitre=new JPanel();
            ptitre.setPreferredSize(new Dimension(0, 150));
            ptitre.setBackground(new Color(148,180,68));
            ptitre.add(titre);
            add(ptitre,BorderLayout.NORTH);
            //button
            ajoutprod.setBackground(new Color(166,200,224));
            searchprod.setBackground(new Color(166,200,224));
            deleteprod.setBackground(new Color(166,200,224));
            modifyprod.setBackground(new Color(166,200,224));
            //****button panel
            buttonpanel=new JPanel();
            buttonpanel.setLayout(new GridLayout(4,1));
            buttonpanel.setBackground(new Color(148,180,68));
            Spanel=new JPanel();
            Spanel.setBackground(new Color(148,180,68));
            Spanel.add(searchprod);
            Apanel=new JPanel();
            Apanel.setBackground(new Color(148,180,68));
            Apanel.add(ajoutprod);
           Mpanel=new JPanel();
            Mpanel.setBackground(new Color(148,180,68));
            Mpanel.add(modifyprod);
            Dpanel=new JPanel();
           Dpanel.setBackground(new Color(148,180,68));
            Dpanel.add(deleteprod);
            buttonpanel.add(Spanel);
            buttonpanel.add(Apanel);
            buttonpanel.add(Mpanel);
            buttonpanel.add(Dpanel);
            //label and textfield panel
            JPanel panel = new JPanel(new GridLayout(4, 3));
            panel.setBackground(new Color(148,180,68));
            panel.add(id);
            panel.add(tid);
            panel.add(nom);
            panel.add(tnom);
            panel.add(prix);
            panel.add(tprix);
            panel.add(qte);
            panel.add(tqte);
            //south panel
            psouth=new JPanel();
            psouth.setLayout(new GridLayout(1,2));
            psouth.setPreferredSize(new Dimension(100,150));
            psouth.setBackground(new Color(148,180,68));
            psouth.add(buttonpanel,BorderLayout.EAST);
            psouth.add(panel,BorderLayout.WEST);
            add(psouth,BorderLayout.SOUTH);
            setVisible(true);
            //liste des produit
            // Affichage des données
           DAO dao=new DAO(Config.URL,Config.USERNAME,Config.PASSWORD);
            String req = "select * from produit";
            ResultSet rs =dao.selection(req);
           jt_produit = new JTable();
            model = new ProduittableModel(rs,dao);
            jt_produit.setModel(model);
            add(new JScrollPane((jt_produit)));


        }
     private void actions(){
         ajoutprod.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 int id= Integer.parseInt(tid.getText());
                 String nom=tnom.getText();
                 float prix=Float.parseFloat(tprix.getText());
                 float qte=Float.parseFloat(tqte.getText());
                 model.insertProduit(id,nom,prix,qte);
                 tid.setText("");
                 tnom.setText("");
                 tprix.setText("");
                 tqte.setText("");
             }
         });

         searchprod.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 int id=Integer.parseInt(tid.getText());
                 int rowindex=model.getRowindex(id);
                 if (rowindex == -1)
                 {
                     JOptionPane.showMessageDialog(null, "Produit n'existe pas \n verifier l'id", "Erreur:", JOptionPane.ERROR_MESSAGE);
                 }
                 else {
                     tnom.setText((model.data.get(rowindex)[1]).toString());
                     tprix.setText((model.data.get(rowindex)[2]).toString());
                     tqte.setText((model.data.get(rowindex)[3]).toString());
                 }
             }
         });

         modifyprod.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 int id=Integer.parseInt(tid.getText());
                 String nom=tnom.getText();
                 float prix=Float.parseFloat(tprix.getText());
                 float qte= Float.parseFloat(tqte.getText());
                 model.modifierProduit(id,nom,prix,qte);
                 tid.setText("");
                 tnom.setText("");
                 tprix.setText("");
                 tqte.setText("");
             }
         });
         deleteprod.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 model.supprimerProduit(Integer.parseInt(tid.getText()));
                 tid.setText("");
                 tnom.setText("");
                 tprix.setText("");
                 tqte.setText("");
             }
         });

     }

}


