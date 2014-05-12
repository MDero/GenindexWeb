/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaceSwing;
import kernel.*;

/**
 *
 * @author Elodie
 */

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.*;

public class CreateCustomer extends JFrame {
    private final JMenuBar menuBar;
    private final JMenu menu;
    private final JMenuItem exit;
    private final JRadioButton individuel, professionel;
    private final JPanel type, type_individuel1, type_professionel1, type_professionel2, type_professionel,IP,IPT, total, validerp;
    private final JLabel type_client, nom, prenom, tel, mail, port, nomCon, PrenomCon,TelCon, mailCon, Faxe, Entre,adresse,n,CP,rue,ville,pays ;
    private final JTextField nomT, prenomT, telT, mailT, portT,nomC,prenomC,MailC,FaxeC,EntreC,adresseC,nC,rueC, telC, CPC, villeC;
    private final JComboBox paysC;
    private final JButton valider;
    private String[] item;
    private Database database;
    
    
    public CreateCustomer(){
        JFrame myFrame= new JFrame ("créer client");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        myFrame.setLayout(new BorderLayout());
        
        Database database = new Database();
        // Création du menu et du bouton quitter du menu
        
        menuBar= new JMenuBar();
        this.setJMenuBar(menuBar);
        menu= new JMenu("Menu");
        menu.setMnemonic(KeyEvent.VK_M);
        menuBar.add(menu);
        exit= new JMenuItem("Quitter", KeyEvent.VK_A);
        menu.add(exit);
        
        // partie client individuel
        type= new JPanel();
        type.setLayout(new GridLayout(1,3));
        
        type_client= new JLabel("Type : ");
        individuel= new JRadioButton("Individuel");
        professionel = new JRadioButton("Professionel");
        
        nom=new JLabel("Nom*: ");
        prenom= new JLabel("Prenom*: ");
        tel= new JLabel ("N° téléphone*: ");
        mail= new JLabel ("e-mail*: ");
        port = new JLabel ("N° portable: ");
        
        nomT = new JTextField(40);
        nomT.setEditable(false);
        
        prenomT = new JTextField(40);
        prenomT.setEditable(false);
        
        telT = new JTextField(40);
        telT.setEditable(false);
        
        mailT = new JTextField(40);
        mailT.setEditable(false);
        
        portT = new JTextField(40);
        portT.setEditable(false);
        
        type_individuel1= new JPanel();
        type_individuel1.setLayout(new GridLayout(5,2));
        
        type_individuel1.add(nom);
        type_individuel1.add(nomT);
        type_individuel1.add(prenom);
        type_individuel1.add(prenomT);
        type_individuel1.add(tel);
        type_individuel1.add(telT);
        type_individuel1.add(mail);
        type_individuel1.add(mailT);
        type_individuel1.add(port);
        type_individuel1.add(portT);
        
      
        type.add(type_client,BorderLayout.WEST);
        type.add(individuel, BorderLayout.CENTER);
        type.add(professionel, BorderLayout.EAST);
        
        
       
        
        // Partie client professionel
        nomCon=new JLabel("Nom Contact*: ");
        PrenomCon=new JLabel("Prenom Contact*: ");
        TelCon= new JLabel("N° téléphone*: ");
        mailCon= new JLabel("e-mail*: ");
        Faxe= new JLabel("N° faxe entreprise*: ");
        Entre= new JLabel("Nom entreprise*: ");
        adresse= new JLabel(" Adresse");
        n= new JLabel("N°:");
        CP= new JLabel("Code Postale: ");
        rue= new JLabel(" Nom de la rue: ");
        ville= new JLabel(" Ville: ");
        pays= new JLabel("Pays: " );
        
        nomC= new JTextField(40);
        nomC.setEditable(false);
        prenomC= new JTextField(40);
        prenomC.setEditable(false);
        MailC=new JTextField(40);
        MailC.setEditable(false);
        FaxeC=new JTextField(40);
        FaxeC.setEditable(false);
        EntreC=new JTextField(40);
        EntreC.setEditable(false);
        telC= new JTextField(40);
        telC.setEditable(false);
        adresseC=new JTextField(40);
        adresseC.setEditable(false);
        nC=new JTextField(40);
        nC.setEditable(false);
        rueC=new JTextField(40);
        rueC.setEditable(false);
        
        //création des JComboBox
        
        villeC=new JTextField(40);
        villeC.setEditable(false);
        paysC=new JComboBox();
        paysC.setEditable(false);
        CPC=new JTextField(40);
        CPC.setEditable(false);
        
   
        
        type_professionel1= new JPanel();
        type_professionel1.setLayout(new GridLayout(7,2));
        type_professionel1.add(nomCon);
        type_professionel1.add(nomC);
        type_professionel1.add(PrenomCon);
        type_professionel1.add(prenomC);
        type_professionel1.add(TelCon);
        type_professionel1.add(telC);
        type_professionel1.add(mailCon);
        type_professionel1.add(MailC);
        type_professionel1.add(Faxe);
        type_professionel1.add(FaxeC);
        type_professionel1.add(Entre);
        type_professionel1.add(EntreC);
        type_professionel1.add(adresse);
        
        // je dois créer le panel adresse
        type_professionel2= new JPanel();
        type_professionel2.setLayout(new GridLayout(3,4));
        type_professionel2.add(n);
        type_professionel2.add(nC);
        type_professionel2.add(rue);
        type_professionel2.add(rueC);
        type_professionel2.add(CP);
        type_professionel2.add(CPC);
        type_professionel2.add(ville);
        type_professionel2.add(villeC);
        type_professionel2.add(pays);
        type_professionel2.add(paysC);
        
        type_professionel=new JPanel();
        type_professionel.setLayout(new GridLayout(2,1));
        type_professionel.add(type_professionel1,BorderLayout.NORTH);
        type_professionel.add(type_professionel2, BorderLayout.SOUTH);
        
        IP= new JPanel();
        IP.setLayout(new GridLayout(1,2));
        IP.add(type_individuel1, BorderLayout.WEST);
        IP.add(type_professionel, BorderLayout.EAST);
        
        IPT= new JPanel();
        IPT.setLayout(new GridLayout(2,1));
        IPT.add(type,BorderLayout.NORTH);
        IPT.add(IP,BorderLayout.SOUTH);
        
        
        validerp= new JPanel();
        valider= new JButton("Valider");
        
        total=new JPanel();
        total.setLayout(new GridLayout(2,1));
      
        validerp.add(valider, BorderLayout.CENTER);
        
         total.add(IPT, BorderLayout.NORTH);
         total.add(validerp);
        
        
        myFrame.add(menuBar, BorderLayout.NORTH);
        //myFrame.add(type, BorderLayout.CENTER);
        //myFrame.add(type_individuel1, BorderLayout.CENTER);
       // myFrame.add(type_professionel1,BorderLayout.CENTER);
       // myFrame.add(type_professionel2, BorderLayout.CENTER);
       // myFrame.add(type_professionel, BorderLayout.CENTER);
        //myFrame.add(IP, BorderLayout.CENTER);
       // myFrame.add(IPT, BorderLayout.CENTER);
        myFrame.add(total);
        myFrame.pack();
        myFrame.setVisible(true);
                }
    
    public static void main (String [] args){
CreateCustomer visuel= new CreateCustomer();
}
}


