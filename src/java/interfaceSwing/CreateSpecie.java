/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceSwing;
import kernel.*;
/**
 *
 * @author Caro
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*; 
import javax.swing.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;
//import java.util.ArrayList;

/**
 *
 * @author Caro
 */

public class CreateSpecie extends JFrame {
        //database
        static Database database = new Database();
    
        // Déclaration des variables
        private JMenuBar menuBar;
        private JMenuItem exit;
        private JButton valider=new JButton("Valider ");
        private JButton newCategorie=new JButton(" + ");
        private JComboBox boxCategory;
        private String[] items;
        private JFrame myFrame;
        
        // le label explicatif
        private JLabel nom, caracteristiques, categorie;
        // le label permettant l'écho
        private JLabel echo;
        // zone de saisie
        private JTextField Cnom, Ccaracteristiques, Ccategorie;
        // zone de placement?
        private JPanel zone1, zone2, zone3, bouton, champs;
        
    
    public CreateSpecie (){
        JFrame myFrame = new JFrame("Species");
        //Création de la barre de menu
        menuBar = new JMenuBar();
        
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
        String[] items;
        ArrayList<String> categoryNames = new ArrayList<>();

        //parcours de toutes les catégories de la base de données
        for (Category category : database.getCategoryList())
            categoryNames.add(category.getName());

        //initialisation du tableau utilisable par JComboBox
        items = new String[categoryNames.size()];

        //transfert liste -> array
        for (int i = 0; i<items.length;i++)
            items[i]=categoryNames.get(i);
        
        //création effective 
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
            }
            
            });
        
        
        champs = new JPanel(); 
        champs.setLayout(new GridLayout(3,1));
        champs.add(zone1);
        champs.add(zone2);
        champs.add(zone3);
        
        bouton = new JPanel();
        bouton.add(valider);
        
       // PreparedStatement pstmt = con.prepareStatement("insert into Category (nom,categorie) values (1,2)");
        //pstmt.setString(1,nom);
        //pstmt.setInt(2, categorie);
        //int result = pstmt.executeUpdate();
        
       // database.insertCategory(Category category);
       /**valider.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent ae) {
         
             
         
         }
            
          //  });
            
        //}
        **/
        myFrame.setLayout(new BorderLayout());
        myFrame.add(menuBar, BorderLayout.NORTH);
        myFrame.add(champs, BorderLayout.CENTER);
        myFrame.add(bouton, BorderLayout.SOUTH);
        
       

        
        myFrame.pack();
        myFrame.setVisible(true);
    }
    
        public static void main (String [] args){
CreateSpecie visuel= new CreateSpecie();
}
}

        


        
 






