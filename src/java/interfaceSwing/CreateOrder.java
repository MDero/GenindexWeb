/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kernel;

/**
 *
 * @author Marion
 */
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class CreateOrder extends JFrame
{
    // instance variables - replace the example below with your own
    private JButton buttonValidate;
    private JPanel panelInfo, panelCSAS, panelValidate;
    private JLabel category, species, analysis, samples;
    private JLabel date, customer;
    private JComboBox boxCategory, boxSpecies, boxAnalysis, boxSamples, boxDate, boxCustomer;
    private JFrame myFrame;
    private String[] items;
    static Database database = new Database();
    
    /**
     * Constructor for objects of class CreateOrder
     */
    public CreateOrder()
    {
        // initialise instance variables
        this.myFrame = new JFrame("Create an order");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        this.panelInfo = new JPanel();
        panelInfo.setLayout(new GridLayout(2,2));        
        this.date = new JLabel("Enter date : ", JLabel.CENTER);
        this.customer = new JLabel("Choose Customer : ", JLabel.CENTER);
        
        
        this.panelCSAS = new JPanel();
        panelCSAS.setLayout(new GridLayout(4,4));
        this.category = new JLabel("Category : ", JLabel.CENTER);
        this.species = new JLabel("Species : ", JLabel.CENTER);
        this.analysis = new JLabel("Analysis : ", JLabel.CENTER);
        this.samples = new JLabel("Samples : ", JLabel.CENTER);
        
        this.boxDate = new JComboBox();
        this.boxCustomer= new JComboBox();
        this.boxCategory = new JComboBox();
        this.boxSpecies = new JComboBox();
        this.boxAnalysis = new JComboBox();
        this.boxSamples = new JComboBox();
        
        this.panelValidate = new JPanel();
        panelValidate.setLayout(new GridLayout(1,1));
        buttonValidate = new JButton("Validate");
        panelValidate.add(buttonValidate);
        
        panelInfo.add(date);
        panelInfo.add(boxDate);
        panelInfo.add(customer);
        panelInfo.add(boxCustomer);
        

        panelCSAS.add(category);
        panelCSAS.add(boxCategory);
        panelCSAS.add(species);
        panelCSAS.add(boxSpecies);
        panelCSAS.add(analysis);
        panelCSAS.add(boxAnalysis);
        panelCSAS.add(samples);
        panelCSAS.add(boxSamples);
        

        myFrame.add(panelInfo, BorderLayout.NORTH);
        myFrame.add(panelCSAS,BorderLayout.CENTER);
        myFrame.add(panelValidate,BorderLayout.SOUTH);
    
        ArrayList<String> names = new ArrayList<>();
    for (Customers customer : database.getCustomerList()){
        names.add(customer.getFirstName()+" "+customer.getLastName());
    }
    items = new String[names.size()];
    for (int i =0; i<items.length;i++)
        items[i]=names.get(i);
    JComboBox Customers = new JComboBox(items);
        
        
        myFrame.pack();
	myFrame.setVisible(true);
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param     a sample parameter for a method
     * @return     the sum of x and y 
     */
    public static void main(String[] args)
    {
        // put your code here
        CreateOrder x = new CreateOrder();
    }


}
