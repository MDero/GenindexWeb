package interfaceSwing;

import static interfaceSwing.CreateSpecie.database;
import kernel.*;
import java.awt.*; 
import javax.swing.*;
import java.awt.event.*;


/**
 *
 * @author Caro
 */
public class CreateCategory extends JFrame {
        
        //database
        static Database database = new Database();

        // Déclaration des variables
        private JMenuBar menuBar;
        private JMenuItem Exit;
        private Category category;
        private JFrame myFrame;
        private JButton valider=new JButton("Valider");
        private JButton annuler;
        // le label explicatif
        private JLabel nom, caracteristiques;
        // le label permettant l'écho
        private JLabel echo;
        // zone de saisie
        private JTextField Cnom, Ccaracteristiques;
        // zone de placement?
        private JPanel zone1, zone2, champs, bouton;
    
    public CreateCategory (){
        myFrame = new JFrame("Category");
        //Création de la barre de menu
        menuBar = new JMenuBar();
        
        //Installation dans la fenêtre
        this.setJMenuBar(menuBar);
        this.annuler = new JButton("Annuler");
        //Construction du premier menu
        JMenu menu = new JMenu("Menu");
        menu.setMnemonic(KeyEvent.VK_M);
        menuBar.add(menu);  
        Exit = new JMenuItem ("Exit",KeyEvent.VK_D);
        menu.add(Exit);
        
        // creation des widgets
        nom = new JLabel("Nom*:");
        caracteristiques = new JLabel("Caractéristiques :");
        
        Cnom = new JTextField(20);
        Ccaracteristiques = new JTextField(20);
        
        zone1 = new JPanel();
        zone1.setLayout(new GridLayout (1,2));
        zone1.add(nom);
        zone1.add(Cnom);
        
        zone2 = new JPanel();
        zone2.setLayout(new GridLayout (1,2));
        zone2.add(caracteristiques);
        zone2.add(Ccaracteristiques);
        
        champs = new JPanel(); 
        champs.setLayout(new GridLayout(3,1));
        champs.add(zone1);
        champs.add(zone2);
        
        bouton = new JPanel();
        bouton.setLayout(new GridLayout(1,2));
        bouton.add(valider);
        bouton.add(annuler);
        
        valider.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {  
                String categoryName = CreateCategory.this.Cnom.getText();
                if (categoryName.length()>0){
                    Category nCategory = new Category(categoryName);
                    database.insertCategory(nCategory);     
                }  
                PopUp popUp = new PopUp();
                myFrame.dispose(); 
            }
        });
        myFrame.setVisible(true);
        
        annuler.addActionListener (new ActionListener () 
        {
            @Override
            public void actionPerformed (ActionEvent e) 
            {
            myFrame.dispose();
            }
        } );

  
        myFrame.setLayout(new BorderLayout());
        myFrame.add(menuBar, BorderLayout.NORTH);
        myFrame.add(champs, BorderLayout.CENTER);
        myFrame.add(bouton, BorderLayout.SOUTH);;
        
        myFrame.pack();
        
    }
    
}
    

