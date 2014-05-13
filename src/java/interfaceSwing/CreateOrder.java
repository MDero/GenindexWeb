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

    private JButton buttonValidate;
    private JPanel panelInfo, panelCSAS, panelValidate;
    private JLabel category, species, analysis, samples;
    private JLabel date, customer;
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
        this.myFrame = new JFrame("Create an order");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        this.panelInfo = new JPanel();
        panelInfo.setLayout(new GridLayout(2, 2));
        this.dateText = new JTextField();
        this.date = new JLabel("Enter date DD/MM/YY : ", JLabel.CENTER);
        this.customer = new JLabel("Choose Customer : ", JLabel.CENTER);


        this.panelCSAS = new JPanel();
        panelCSAS.setLayout(new GridLayout(4, 4));
        this.category = new JLabel("Category : ", JLabel.CENTER);
        this.species = new JLabel("Species : ", JLabel.CENTER);
        this.analysis = new JLabel("Analysis : ", JLabel.CENTER);
        this.samples = new JLabel("Samples : ", JLabel.CENTER);

        
        this.boxCustomer = new JComboBox();
        this.boxCategory = new JComboBox();
        this.boxSpecies = new JComboBox();
        this.boxAnalysis = new JComboBox();
        this.boxSamples = new JComboBox();

        this.panelValidate = new JPanel();
        panelValidate.setLayout(new GridLayout(1, 1));
        buttonValidate = new JButton("Validate");
        buttonValidate.addActionListener(this);
        panelValidate.add(buttonValidate);

        panelInfo.add(date);
        panelInfo.add(boxCustomer);
        panelCSAS.add(boxCategory);
        panelCSAS.add(boxSpecies);
        panelCSAS.add(boxAnalysis);
        panelCSAS.add(boxSamples);

              
        
        // Liste déroulante Customers
        ArrayList<String> names = new ArrayList<>();
        for (Customers customer : database.getCustomerList()) {
            names.add(customer.getFirstName()+ " "+ customer.getLastName());
        }
        items = new String[names.size()];
        for (int i = 0; i < items.length; i++) 
            items[i] = names.get(i);
        
        JComboBox Customers = new JComboBox(items);
        panelInfo.add(Customers);


        //Liste déroulante Category
        ArrayList<String> categorie = new ArrayList<>();
        for (Category category : database.getCategoryList()) {
            names.add(category.getName());
        }
        items = new String[categorie.size()];
        for (int i = 0; i < items.length; i++) {
            items[i] = categorie.get(i);
        }
        JComboBox category = new JComboBox(items);   
        panelCSAS.add(category);
        
        // Liste déroulante Species
        ArrayList<String> espece = new ArrayList<>();
        for (Species species : database.getSpeciesList()) {
            espece.add(species.getName());
        }
        items = new String[espece.size()];
        for (int i = 0; i < items.length; i++) {
            items[i] = espece.get(i);
        }
        JComboBox species = new JComboBox(items);
        panelCSAS.add(species);

        // Liste déroulante Analysis
        ArrayList<String> analyse = new ArrayList<>();
        for (TypeAnalysis analysis : database.getTypeAnalysisList()) {
            analyse.add(analysis.getType());
        }
        items = new String[analyse.size()];
        for (int i = 0; i < items.length; i++) {
            items[i] = analyse.get(i);
        }
        JComboBox analysis = new JComboBox(items);
        panelCSAS.add(analysis);

        // Liste déroulante Sample
        ArrayList<String> echantillon = new ArrayList<>();
        for (Samples sample : database.getSampleList()) {
            echantillon.add(String.valueOf(sample.getCount()));
        }
        items = new String[echantillon.size()];
        for (int i = 0; i < items.length; i++) {
            items[i] = echantillon.get(i);
        }
        JComboBox sample = new JComboBox(items);
        panelCSAS.add(samples);


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
