/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
 
package interfaceSwing;


import static interfaceSwing.CreateMicroplate.database;
import java.awt.*; 

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import kernel.Category;
import kernel.Database;
import kernel.TypeAnalysis;
import kernel.TypeSample;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import kernel.Customers;
import kernel.Orders;
import kernel.Samples;

/*
 *
 * @author Caro
 */

public class CreateMicroplate extends JFrame implements ActionListener {
    
        //database
        static Database database = new Database();
    
        // Déclaration des variables
        private JMenuBar menuBar;
        private JFrame myFrame;
        private JMenuItem exit;
        private JLabel tanal, tsample;
        private JComboBox boxAnalyse;
        private String[] items;
        private String[] ech;
        private JList listEch;
        private ListSelectionModel listEchSelectionModel;
        private Vector imageList;
        private JPanel zone1, zone2,zone3;
        private JTable table;
        private int lastEmptyRow=0;
        
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
        this.boxAnalyse = new JComboBox(items);
        boxAnalyse.addActionListener(this);
        
        
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
        
        //TABLE D ORDERS
        table = new JTable(new DefaultTableModel(new Object[]{"Numéro","Client","Priorité"},10));
        lastEmptyRow=10;//de base on a 10 lignes pour le swag
        
        zone2 = new JPanel();
        zone2.setLayout(new BorderLayout());
        zone2.add(table);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(800,100));
	scrollPane.setColumnHeaderView(table.getTableHeader());
	zone2.add(scrollPane);


        zone1= new JPanel();
        zone1.setLayout(new GridLayout (1,2));
        zone1.add(tanal);
        zone1.add(boxAnalyse);
        
        

        myFrame.add(menuBar, BorderLayout.NORTH);
        myFrame.add(zone1, BorderLayout.WEST);
        myFrame.add(zone2, BorderLayout. SOUTH);

        myFrame.pack();
        myFrame.setVisible(true);
        
        
}
        @Override
	public void actionPerformed(ActionEvent e) {

		// TODO Auto-generated method stub

				JComboBox cb = (JComboBox)e.getSource();
                                String[] items;
                                
                                ArrayList<Samples> samples = (ArrayList<Samples>) database.getSampleList();
                                ArrayList<Samples> samplesToAnalyze = new ArrayList<>();
                                ArrayList<Samples> samplesPriority = new ArrayList<>();
                                for (Samples sample : samples){
                                    //0 is analysed 1 is to analyse 2 is analysed but need to re analyse 
                                    if(sample.getStatusId()==2){
                                        samplesPriority.add(sample);
                                    }
                                    else if(sample.getStatusId()==1){
                                        samplesToAnalyze.add(sample);
                                    }
                                }
                                
                                //clear screen
                                for (int i = 0 ; i<CreateMicroplate.this.lastEmptyRow;i++)
                                     ((DefaultTableModel)CreateMicroplate.this.table.getModel()).removeRow(0);
                                CreateMicroplate.this.lastEmptyRow=0;
                                //add new rows
                                if (samplesPriority.size()>0){
                                    for (Samples sample : samplesPriority){
                                        //find the customer
                                        Orders order = sample.getOrder();
                                        Customers customer = order.getCustomer();
                                        
                                        ((DefaultTableModel)CreateMicroplate.this.table.getModel()).addRow(new Object[]{sample.getId(),customer.getFirstName()+" "+customer.getLastName(),"PRIORITAIRE"});
                                        lastEmptyRow++;
                                    }
                                }
                                if (samplesToAnalyze.size()>0){
                                    for (Samples sample : samplesToAnalyze){
                                        //find the customer
                                        Orders order = sample.getOrder();
                                        Customers customer = order.getCustomer();
                                        
                                        ((DefaultTableModel)CreateMicroplate.this.table.getModel()).addRow(new Object[]{sample.getId(),customer.getFirstName()+" "+customer.getLastName(),""});
                                        lastEmptyRow++;
                                    }
                                }
                                
                                
	}
           public static void main (String [] args){
CreateMicroplate visuel= new CreateMicroplate();
           }
}
           

