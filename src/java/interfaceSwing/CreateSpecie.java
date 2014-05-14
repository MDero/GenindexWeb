package interfaceSwing;
/**
 *
 * @author Caro
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import kernel.*;

import java.awt.*; 

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class CreateSpecie extends JFrame {
        //database
        static Database database = new Database();
    
        // Déclaration des variables
        private JMenuBar menuBar;
        private JMenuItem exit;
        private JButton valider=new JButton("Valider ");
        private JButton newCategorie=new JButton(" + ");
        private JButton annuler;
        private JComboBox boxCategory;
        private String[] items;
        private JFrame myFrame;
        private Species specie;
        
        // le label explicatif
        private JLabel nom, caracteristiques, categorie;
         JLabel label = new JLabel("Vous avez saisie: ");
        // le label permettant l'écho
        private JLabel echo;
        // zone de saisie
        private JTextField Cnom, Ccaracteristiques, Ccategorie;
        JTextField textField = new JTextField("");
        // zone de placement?
        private JPanel zone1, zone2, zone3, bouton, champs;
        
    
    public CreateSpecie (){
        myFrame = new JFrame("Species");
        //Création de la barre de menu
        menuBar = new JMenuBar();
        this.annuler = new JButton("Annuler");
        //Installation dans la fenêtre
        this.setJMenuBar(menuBar);
        
        //Construction du premier menu
        JMenu menu = new JMenu("Menu");
        menu.setMnemonic(KeyEvent.VK_M);
        menuBar.add(menu);  
        exit = new JMenuItem ("Exit",KeyEvent.VK_D);
        menu.add(exit);
        
        // creation des widgets
        nom = new JLabel("Nom*:");
        caracteristiques = new JLabel("Caractéristiques :");
        categorie = new JLabel("Catégorie*:");
        
        /* creation de menu déroulant */
        
        /** TENTATIVE DEBUG **/
        ArrayList<Category> categories = new ArrayList<>();
        for (Category category : database.getCategoryList())
            categories.add(category);
        Category[] items = new Category[categories.size()];
        for (int i =0; i<items .length;i++)
            items [i]=categories.get(i);
        
        //création effective 
        //this.boxCategory = new JComboBox(items);
        this.boxCategory = new JComboBox(items);
                
        Cnom = new JTextField(20);
        Ccaracteristiques = new JTextField(20);
        Ccategorie = new JTextField(10);
        
        zone1 = new JPanel();
        zone1.setLayout(new GridLayout (1,2));
        zone1.add(nom);
        zone1.add(Cnom);
        
        zone2 = new JPanel();
        zone2.setLayout(new GridLayout (1,2));
        zone2.add(caracteristiques);
        zone2.add(Ccaracteristiques);
        
        zone3 = new JPanel();
        zone3.setLayout(new GridLayout (1,3));
        zone3.add(categorie);
        zone3.add(boxCategory);
        zone3.add(newCategorie);
            
        newCategorie.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                new CreateCategory();   
                if (!(CreateSpecie.this.Ccategorie.getText().equals("")
                       && CreateSpecie.this.Cnom.getText().equals(""))
                        ){
                    database.insertSpecies(specie);
                /**label.setText("Vous avez saisie: " + textField.getText());
                // reinitialise le champs de texte
                textField.setText("");
                // on redonne le foscus au textField
                textField.setFocusable(true);**/
                }              
            }
        });
            
           
        champs = new JPanel(); 
        champs.setLayout(new GridLayout(3,1));
        champs.add(zone1);
        champs.add(zone2);
        champs.add(zone3);
        
        bouton = new JPanel();
        bouton.setLayout(new GridLayout(1,2));
        bouton.add(valider);
            valider.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                //if there is something
                String speciesname= CreateSpecie.this.Cnom.getText();
                if (speciesname.length()>0){
                    Category category = (Category) boxCategory.getSelectedItem();
                    if (category!=null && category.getId()>-1){
                        Species nSpecies = new Species(category,speciesname);
                        database.insertSpecies(nSpecies);
                PopUp popUp = new PopUp();
                myFrame.dispose();
                    }
                }
            }
                
            });
        bouton.add(annuler);
        myFrame.setLayout(new BorderLayout());
        myFrame.add(menuBar, BorderLayout.NORTH);
        myFrame.add(champs, BorderLayout.CENTER);
        myFrame.add(bouton, BorderLayout.SOUTH);

        myFrame.pack();
        myFrame.setVisible(true);
        
        annuler.addActionListener (new ActionListener () 
        {
            @Override
            public void actionPerformed (ActionEvent e) 
            {
             myFrame.dispose();
            }
        } );
    }

}

        


        
 






