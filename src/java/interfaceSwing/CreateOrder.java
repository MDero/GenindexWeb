/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceSwing;

/**
 *
 * @author Marion
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import kernel.Database;
import kernel.*;


public class CreateOrder extends JFrame implements ActionListener{
    // instance variables - replace the example below with your own

    private JButton buttonValidate, buttonCancel;
    private JPanel panelInfo, panelCSAS, panelValidate;
    private JLabel category, species, analysis, samples, date, customer;
    private JComboBox boxCategory, boxSpecies, boxAnalysis, boxSamples, boxCustomer;
    private JTextField dateText;
    private JFrame myFrame;
    private String[] items;
    static Database database = new Database();


    /**
     * Constructor for objects of class CreateOrder
     */
    public CreateOrder() {
        // initialise instance variables
        this.myFrame = new JFrame("CREATE AN ORDER");
        //myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        this.panelInfo = new JPanel();
        panelInfo.setLayout(new GridLayout(2, 2));
        
        this.date = new JLabel("Enter date dd/mm/yy : ");
        this.dateText = new JTextField();
        this.customer = new JLabel("Choose Customer : ");

        this.panelCSAS = new JPanel();
        panelCSAS.setLayout(new GridLayout(4, 2));
        this.category = new JLabel("Category : ");
        this.species = new JLabel("Species : ");
        this.analysis = new JLabel("Analysis : ");
        this.samples = new JLabel("Samples : ");


        this.panelValidate = new JPanel();
        panelValidate.setLayout(new GridLayout(1, 2));
        buttonValidate = new JButton("Validate");
        buttonValidate.addActionListener(this);
            
        
        // Liste déroulante Customers
        ArrayList<String> names = new ArrayList<>();
        for (Customers customer : database.getCustomerList()) {
            names.add(customer.getFirstName()+ " "+ customer.getLastName());
        }
        items = new String[names.size()];
        for (int i = 0; i < items.length; i++) 
            items[i] = names.get(i);
        
        this.boxCustomer = new JComboBox(items);
        

        //Liste déroulante Category
        ArrayList<String> categorie = new ArrayList<>();
        for (Category category : database.getCategoryList()) {
            categorie.add(category.getName());
        }
        items = new String[categorie.size()];
        for (int i = 0; i < items.length; i++) {
            items[i] = categorie.get(i);
        }
        this.boxCategory = new JComboBox(items);   
        
        
        // Liste déroulante Species
        ArrayList<String> espece = new ArrayList<>();
        for (Species species : database.getSpeciesList()) {
            espece.add(species.getName());
        }
        items = new String[espece.size()];
        for (int i = 0; i < items.length; i++) {
            items[i] = espece.get(i);
        }
        this.boxSpecies = new JComboBox(items);
        
       
        // Liste déroulante Analysis
        ArrayList<String> analyse = new ArrayList<>();
        for (TypeAnalysis analysis : database.getTypeAnalysisList()) {
            analyse.add(analysis.getType());
        }
        items = new String[analyse.size()];
        for (int i = 0; i < items.length; i++) {
            items[i] = analyse.get(i);
        }
        this.boxAnalysis = new JComboBox(items);
        
        
        // Liste déroulante Sample
        ArrayList<String> echantillon = new ArrayList<>();
        for (Samples sample : database.getSampleList()) {
            echantillon.add(String.valueOf(sample.getCount()));
        }
        items = new String[echantillon.size()];
        for (int i = 0; i < items.length; i++) {
            items[i] = echantillon.get(i);
        }
        this.boxSamples = new JComboBox(items);
       
        
        this.buttonCancel = new JButton("Cancel");
        buttonCancel.addActionListener (new ActionListener () 
        {
            @Override
            public void actionPerformed (ActionEvent e) 
            {
             myFrame.dispose();
            }
        } );
        
        
        panelInfo.add(date);
        panelInfo.add(dateText);
        panelInfo.add(customer);
        panelInfo.add(boxCustomer);
        
        panelCSAS.add(category);
        panelCSAS.add(boxCategory);
        panelCSAS.add(analysis);
        panelCSAS.add(boxAnalysis);
        panelCSAS.add(species);
        panelCSAS.add(boxSpecies);
        panelCSAS.add(samples);
        panelCSAS.add(boxSamples);
        
        panelValidate.add(buttonCancel);
        panelValidate.add(buttonValidate);
        
        
        myFrame.add(panelInfo, BorderLayout.NORTH);
        myFrame.add(panelCSAS, BorderLayout.CENTER);
        myFrame.add(panelValidate, BorderLayout.SOUTH);
        
        myFrame.pack();
        myFrame.setVisible(true);
    }
    
 
    
        @Override
         public void actionPerformed(ActionEvent ae) {
         if (ae.getSource()==buttonValidate){
             database.insertOrder(null);
           }
}
    /**
     * An example of a method - replace this comment with your own
     *
     * @return the sum of x and y
     */
    public static void main(String[] args) {
        // put your code here
        CreateOrder windows = new CreateOrder();
    }

    
}


