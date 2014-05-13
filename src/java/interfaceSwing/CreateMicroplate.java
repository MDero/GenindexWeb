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
import kernel.Samples;

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
        private JLabel tanal, tsample;
        private JComboBox boxCategory;
        private String[] items;
        private String[] ech;
        private JList listEch;
        private ListSelectionModel listEchSelectionModel;
        private Vector imageList;
        private JPanel zone1, zone2,zone3;
        
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
        tanal = new JLabel("Type d'analyse:");
        tsample = new JLabel ("Liste d'échantillon:");
        
         /* creation de menu déroulant */
        String[] items;
        ArrayList<String> typeAnalNames = new ArrayList<>();

                //parcours de toutes les type d'analyses de la base de données
        for (TypeAnalysis typeanal: database.getTypeAnalysisList())
            typeAnalNames.add(typeanal.getType());

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

                //parcours de toutes les catégories de la base données
        for (Samples sample: database.getSampleList())
            sampleNames.add("Numero : "+sample.getId()+" Statut : "+sample.getStatusId());

                //initialisation du tableau utilisable par JComboBox
        ech = new String[sampleNames.size()];

                //transfert liste -> array
        for (int i = 0; i<ech.length;i++)
            ech[i]=sampleNames.get(i);
        
                //création effective 
        imageList=new Vector();
        listEch = new JList(ech);
        

        zone1= new JPanel();
        zone1.setLayout(new GridLayout (1,2));
        zone1.add(tanal);
        zone1.add(boxCategory);
        
        zone2 = new JPanel();
        zone2.setLayout(new GridLayout (1,2));
        zone2.add(tsample);
        zone2.add(listEch);


        myFrame.add(menuBar, BorderLayout.NORTH);
        myFrame.add(zone1, BorderLayout.WEST);
        myFrame.add(zone2, BorderLayout. SOUTH);

        myFrame.pack();
        myFrame.setVisible(true);
}

           public static void main (String [] args){
CreateMicroplate visuel= new CreateMicroplate();

           }
}
           

