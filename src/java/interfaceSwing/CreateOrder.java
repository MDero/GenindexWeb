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
import java.applet.*;
import java.sql.SQLException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CreateOrder extends JFrame{
    // instance variables - replace the example below with your own

    private JButton buttonValidate, buttonCancel;
    private JPanel panelInfo, panelCSAS, panelValidate;
    private JLabel category, species, analysis, samples, date1, date2, customer, priority,tSample,animals;
    private JComboBox boxCategory, boxSpecies, boxAnalysis, boxSamples, boxCustomer, boxPriority,boxTSample,boxAnimals;
    private JTextField dateText, dateDeadline, nbSamples;
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
        panelInfo.setLayout(new GridLayout(3, 2));
        
        java.util.Date maDate;
        SimpleDateFormat maDateLongue;
        maDate= new java.util.Date();
        maDateLongue= new SimpleDateFormat("dd/MM/yyyy");


        this.date1 = new JLabel("Enter date dd/mm/yy : ");
        this.dateText = new JTextField();
        this.dateText.setText(maDateLongue.format(maDate));
        
        
        this.date2 = new JLabel("Enter date deadline dd/mm/yy : ");
        this.dateDeadline = new JTextField();
        this.dateDeadline.setText(maDateLongue.format(maDate));
        this.customer = new JLabel("Choose Customer : ");

        this.panelCSAS = new JPanel();
        panelCSAS.setLayout(new GridLayout(7, 2));
        this.category = new JLabel("Category : ");
        this.species = new JLabel("Species : ");
        this.analysis = new JLabel("Analysis : ");
        this.samples = new JLabel("Samples Number : ");
        this.priority = new JLabel("Priority : ");
        this.animals = new JLabel("Animal : ");
        this.tSample = new JLabel("Type sample : ");
        


        this.panelValidate = new JPanel();
        panelValidate.setLayout(new GridLayout(1, 2));
        buttonValidate = new JButton("Validate");
        
            
        
        // Liste déroulante Customers
        final ArrayList<Customers> names = new ArrayList<Customers>();
        for (Customers customer : database.getCustomerList()) {
            names.add(customer);
        }
        String[] CItems = new String[names.size()];
        for (int i = 0; i < CItems.length; i++) 
            CItems[i] = names.get(i).getFirstName() + " " + names.get(i).getLastName();
        
        this.boxCustomer = new JComboBox(CItems);
        

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
        
        // Liste déroulante Type Sample
        final ArrayList<TypeSample> typeSample = new ArrayList<>();
        for (TypeSample TSample : database.getTypeSampleList()) {
             typeSample.add(TSample);
        }
        items = new String[typeSample.size()];
        for (int i = 0; i < items.length; i++) {
            items[i] = typeSample.get(i).getTypeName();
        }
        this.boxTSample = new JComboBox(items);
        
        // Liste déroulante Animals
        final ArrayList<Animals> animals = new ArrayList<>();
        for (Animals ani: database.getAnimalList()) {
             animals.add(ani);
        }
        items = new String[animals.size()];
        for (int i = 0; i < items.length; i++) {
            items[i] = animals.get(i).getName();
        }
        this.boxAnimals = new JComboBox(items);
        
       
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
       

       // Liste déroulante Priority
       
       this.boxPriority = new JComboBox();
       boxPriority.addItem("1");
       boxPriority.addItem("2");
       boxPriority.addItem("3");
       boxPriority.addItem("4");
       boxPriority.addItem("5");

       this.nbSamples = new JTextField();
       this.nbSamples.setText("1");
        
        this.buttonCancel = new JButton("Cancel");
        buttonCancel.addActionListener (new ActionListener () 
        {
            @Override
            public void actionPerformed (ActionEvent e) 
            {
             myFrame.dispose();
            }
        } );
        
        buttonValidate.addActionListener(new ActionListener ()
        {
             @Override
             public void actionPerformed(ActionEvent ae) 
             {
            // if (ae.getSource()==buttonValidate)
             
             
             CreateOrder co = CreateOrder.this;
             Orders new_orders = null;
             
//                 System.out.println(co.boxCustomer.getSelectedItem() );
//                 System.out.println(co.boxCustomer.getSelectedIndex());
//                 System.out.println(names.get(co.boxCustomer.getSelectedIndex()).getFirstName());
 //   public Orders(int num_samples, Date date_order, Date date_deadline, int priority, Customers customer) {
             new_orders = new Orders(    
                      (int)Integer.valueOf(nbSamples.getText()),
                     new kernel.Date (co.dateText.getText()),
                     new kernel.Date (co.dateDeadline.getText()),
                     (int)Integer.valueOf((String)co.boxPriority.getSelectedItem()),
                     names.get(co.boxCustomer.getSelectedIndex())
                     );
             
             database.insertOrder(new_orders);
             
             //public Samples(TypeSample Type_sample,  Orders order,Date D_sampling, Date D_storage, Animals anim) {
             for (int i = 0; i < new_orders.getNumberSamples(); i++) {
                 Samples s = new Samples(typeSample.get(co.boxTSample.getSelectedIndex()), 
                         new_orders,
                         new_orders.getDateOrder(),
                         new_orders.getDateOrder(),
                         animals.get(co.boxAnimals.getSelectedIndex()));
                 database.insertSample(s);
             }
             
             
             
//             database.getSpeciesForCategory(null);
             
        }});
        
        
        panelInfo.add(date1);
        panelInfo.add(dateText);
        panelInfo.add(date2);
        panelInfo.add(dateDeadline);
        panelInfo.add(customer);
        panelInfo.add(boxCustomer);
        
        panelCSAS.add(category);
        panelCSAS.add(boxCategory);
        panelCSAS.add(species);
        panelCSAS.add(boxSpecies);
        panelCSAS.add(this.animals);
        panelCSAS.add(boxAnimals);
        panelCSAS.add(this.tSample);
        panelCSAS.add(this.boxTSample);
        panelCSAS.add(analysis);
        panelCSAS.add(boxAnalysis);
        panelCSAS.add(priority);
        panelCSAS.add(boxPriority);
        panelCSAS.add(samples);
        panelCSAS.add(nbSamples);
        
        
        panelValidate.add(buttonCancel);
        panelValidate.add(buttonValidate);
        
        
        myFrame.add(panelInfo, BorderLayout.NORTH);
        myFrame.add(panelCSAS, BorderLayout.CENTER);
        myFrame.add(panelValidate, BorderLayout.SOUTH); 
        
        myFrame.pack();
        myFrame.setVisible(true);
//        try {
//            database.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(CreateOrder.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
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


