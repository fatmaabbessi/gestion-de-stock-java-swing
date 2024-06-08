package user;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Login extends JFrame  {

    JButton connexion;
    JTextField tlogin;
    JPasswordField tmdp;
    JLabel login, mdp,titre;
    JPanel pcentre,plogin,pcon,ptitre,pwest,peast;
    public Login() {
        this.setTitle("Login");
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //north
        titre = new JLabel("Login:");
        titre.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
        titre.setForeground(new Color(166,200,224));
       //****ptitre
        ptitre=new JPanel();
        ptitre.setPreferredSize(new Dimension(0, 150));
        ptitre.setBackground(new Color(148,180,68));
        ptitre.add(titre);


       //pcentre
        login = new JLabel("Login: ");
        mdp = new JLabel("Mot de passe: ");
        tlogin = new JTextField(15);
        tmdp = new JPasswordField(15);

        //peast*****button
       connexion = new JButton("connecter");
        connexion.setBackground(new Color(148,180,68));
        connexion.setForeground(Color.white);
        connexion.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        pcon = new JPanel();
        pcon.setPreferredSize(new Dimension(30, 40));
        pcon.add(connexion);
        pcon.setBackground(new Color(204,206,173));
        plogin=new JPanel();
        plogin.setLayout(new GridLayout(4,1));
        plogin.setPreferredSize(new Dimension(200,200));
        plogin.add(login);
        plogin.add(tlogin);
        plogin.add(mdp);
        plogin.add(tmdp);
        plogin.setBackground(new Color(204,206,173));
        peast=new JPanel();
        peast.setLayout(new GridLayout(2,1));
        peast.add(plogin);
        peast.add(pcon);
        //***pwest*******
        pwest=new JPanel();
        pwest.setPreferredSize(new Dimension(250,300));
        pwest.setBackground(new Color(204,206,173));
        ImageIcon originalIcon = new ImageIcon("C:\\Users\\Lenovo\\Desktop\\gestion de stock\\icon_login.png");
         JLabel img = new JLabel(originalIcon);
         pwest.add(img);

        pcentre=new JPanel();
        pcentre.add(pwest,BorderLayout.WEST);
        pcentre.add(peast,BorderLayout.EAST);
        pcentre.setBackground(new Color(204,206,173));


        this.add(pcentre);
        this.add(ptitre, BorderLayout.NORTH);
        this.setVisible(true);

//****************event
        connexion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //*********socket********
                int test = 0;
                    try {

                        Socket s = new Socket("127.0.0.1", 9000);

                        //*******ecriture
                        PrintWriter pw = new PrintWriter(s.getOutputStream());
                        pw.println(tlogin.getText() + " " + tmdp.getText());
                        pw.flush();
                        //*********lecture
                        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                        test = Integer.parseInt(br.readLine());

                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                    if (test == 0) {
                        JOptionPane.showMessageDialog(null, "vous devez remplir tout les champs", "Erreur:", JOptionPane.ERROR_MESSAGE);
                    } else if (test == 1) {
                        Choix ch = new Choix();
                    } else {
                        JOptionPane.showMessageDialog(null, "login ou mot de passe incorrect", "Erreur:", JOptionPane.ERROR_MESSAGE);
                        tlogin.setText("");
                        tmdp.setText("");
                    }
                }

        });

    }



}
