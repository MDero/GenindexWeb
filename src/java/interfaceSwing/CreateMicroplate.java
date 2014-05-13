/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceSwing;

import static interfaceSwing.CreateSpecie.database;
import java.awt.*; 

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import kernel.Category;
import kernel.Database;
import kernel.TypeAnalysis;
import kernel.TypeSample;
import java.util.Vector;

/*
 *
 * @author Caro
 */

public class CreateMicroplate extends JFrame {
    
        //database
        static Database database = new Database();
    
        // Déclaration des variables
        private JMenuBar menuBar;
        private JFrame myFrame;
        private JMenuItem exit;
        private JLabel nom;
        private JComboBox boxCategory;
        private String[] items;
        private String[] ech;
        private JList listEch;
        private ListSelectionModel listEchSelectionModel;
        private Vector imageList;
        
        public CreateMicroplate (){
        JFrame myFrame = new JFrame("Create microplate");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        nom = new JLabel("Type d'échantillon:");
        
         /* creation de menu déroulant */
        String[] items;
        ArrayList<String> typeAnalNames = new ArrayList<>();

                //parcours de toutes les type d'analyses de la base de données
        for (TypeAnalysis typeanal: database.getTypeAnalysisList())
            typeAnalNames.add(typeanal.getName());

                //initialisation du tableau utilisable par JComboBox
        items = new String[typeAnalNames.size()]; 

                //transfert liste -> array
        for (int i = 0; i<items.length;i++)
            items[i]=typeAnalNames.get(i);
        
                //création effective 
        this.boxCategory = new JComboBox(items);
        
        
        
        /* creation de la liste d'échantillon */
        String[] ech;
        ArrayList<String> sampleNames = new ArrayList<>();

                //parcours de toutes les catégories de la base de données
        for (TypeSample typesample: database.getTypeSampleList())
            sampleNames.add(typesample.getTypeName());

                //initialisation du tableau utilisable par JComboBox
        ech = new String[sampleNames.size()];

                //transfert liste -> array
        for (int i = 0; i<ech.length;i++)
            ech[i]=sampleNames.get(i);
        
                //création effective 
        this.boxCategory = new JComboBox(ech);
        
        
        
        myFrame.pack();
        myFrame.setVisible(true);
}

           public static void main (String [] args){
CreateMicroplate visuel= new CreateMicroplate();

           }
}
           

