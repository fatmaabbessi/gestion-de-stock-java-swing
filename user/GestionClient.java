package user;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.sql.ResultSet;

public class GestionClient extends JFrame {
    private JLabel id, nom,prenom,total,titre;
    private JTextField tid, tnom,tprenom,ttotal;
    private JButton ajoutcl,searchcl,modifycl,deletecl,acheter,recu;
    private JPanel Apanel,Spanel,Mpanel,Dpanel,buttonpanel,psouth;
    ClienttableModel model;
    JTable jt_client;
    public GestionClient()  {
        initializeComponents();
        createLayout();
        actions();
    }
    private void initializeComponents() {
        titre = new JLabel("Client");
        id = new JLabel("Id :"); // id client
        prenom = new JLabel("Prenom :");
        nom = new JLabel("Nom :"); // nom
        total= new JLabel("Montant :"); // montant total credite


        tid= new JTextField(20);
        tnom = new JTextField(20);
        tprenom= new JTextField(20);
        ttotal= new JTextField(20);




        ajoutcl = new JButton("Ajouter");
        searchcl = new JButton("Rechercher");
        modifycl = new JButton("Modifier");
        deletecl = new JButton("supprimer");
        acheter=new JButton("Acheter");
        recu=new JButton("Reçu");
    }

    private void createLayout()  {
        setTitle("gestion de clients");
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
        ajoutcl.setBackground(new Color(166,200,224));
        searchcl.setBackground(new Color(166,200,224));
        deletecl.setBackground(new Color(166,200,224));
        modifycl.setBackground(new Color(166,200,224));
        acheter.setBackground(new Color(166,200,224));
        recu.setBackground(new Color(166,200,224));
        //****button panel
        buttonpanel=new JPanel();
        buttonpanel.setLayout(new GridLayout(4,1));
        buttonpanel.setBackground(new Color(148,180,68));
        Spanel=new JPanel();
        Spanel.setBackground(new Color(148,180,68));
        Spanel.add(searchcl);
        Apanel=new JPanel();
        Apanel.setBackground(new Color(148,180,68));
        Apanel.add(ajoutcl);
        Mpanel=new JPanel();
        Mpanel.setBackground(new Color(148,180,68));
        Mpanel.add(modifycl);
        Dpanel=new JPanel();
        Dpanel.setBackground(new Color(148,180,68));
        Dpanel.add(deletecl);
        buttonpanel.add(Spanel);
        buttonpanel.add(Apanel);
        buttonpanel.add(Mpanel);
        buttonpanel.add(Dpanel);
        //*****acheter panel
        JPanel pacheter=new JPanel();
        pacheter.setLayout(new GridLayout(2,1));
        pacheter.setBackground(new Color(148,180,68));
        JPanel ach=new JPanel();
        ach.setPreferredSize(new Dimension(50,50));
        ach.setBackground(new Color(148,180,68));
        ach.add(acheter);
        JPanel rec=new JPanel();
        rec.setPreferredSize(new Dimension(50,50));
        rec.setBackground(new Color(148,180,68));
        rec.add(recu);
        pacheter.add(ach);
        pacheter.add(rec);

        //label and textfield panel
        JPanel panel = new JPanel(new GridLayout(4, 3));
        panel.setBackground(new Color(148,180,68));
        panel.add(id);
        panel.add(tid);
        panel.add(nom);
        panel.add(tnom);
        panel.add(prenom);
        panel.add(tprenom);
        panel.add(total);
        panel.add(ttotal);
        //south panel
        psouth=new JPanel();
        psouth.setLayout(new GridLayout(2,2));
        psouth.setPreferredSize(new Dimension(100,300));
        psouth.setBackground(new Color(148,180,68));
        psouth.add(buttonpanel);
        psouth.add(panel);
        psouth.add(pacheter);
        add(psouth,BorderLayout.SOUTH);
        setVisible(true);
        //liste des clients
        DAO dao = new DAO(Config.URL,Config.USERNAME,Config.PASSWORD);
        String req = "select * from client";
        ResultSet rs =dao.selection(req);
        jt_client = new JTable();
        add(new JScrollPane((jt_client)));
        model = new ClienttableModel(rs,dao);
        jt_client.setModel(model);


    }
    private void actions(){
        recu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tid.getText().equals("")||tnom.getText().equals("")||tprenom.getText().equals("")||ttotal.getText().equals(""))
                    JOptionPane.showMessageDialog(null, "vous devez choisir un client", "Erreur:", JOptionPane.ERROR_MESSAGE);
                else {
                    Recu recu1=new Recu(Integer.parseInt(tid.getText()));
            }
            }
        });
        acheter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tid.getText().equals("")||tnom.getText().equals("")||tprenom.getText().equals("")||ttotal.getText().equals(""))
                    JOptionPane.showMessageDialog(null, "vous devez choisir un client", "Erreur:", JOptionPane.ERROR_MESSAGE);
                else {
                    AjouterPanier panier=new AjouterPanier(Integer.parseInt(tid.getText()));
                }
            }
        });
        ajoutcl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id= Integer.parseInt(tid.getText());
                String nom=tnom.getText();
                String prenom=tprenom.getText();
                float total=Float.parseFloat(ttotal.getText());
                model.insertClient(id,nom,prenom,total);
                tid.setText("");
                tnom.setText("");
                tprenom.setText("");
                ttotal.setText("");

            }
        });
        searchcl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(tid.getText());
                int rowindex = model.getRowindex(id);
                if (rowindex == -1)
                {
                    JOptionPane.showMessageDialog(null, "Client n'existe pas \n verifier l'id", "Erreur:", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    tnom.setText((model.data.get(rowindex)[1]).toString());
                    tprenom.setText((model.data.get(rowindex)[2]).toString());
                    ttotal.setText((model.data.get(rowindex)[3]).toString());
                }
            }
        });

        modifycl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id= Integer.parseInt(tid.getText());
                String nom=tnom.getText();
                String prenom=tprenom.getText();
                float total=Float.parseFloat(ttotal.getText());
                model.modifierClient(id,nom,prenom,total);
                tid.setText("");
                tnom.setText("");
                tprenom.setText("");
                ttotal.setText("");
            }
        });
        deletecl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.supprimerClient(Integer.parseInt(tid.getText()));
                tid.setText("");
                tnom.setText("");
                tprenom.setText("");
                ttotal.setText("");
            }
        });

    }


}




