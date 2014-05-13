package interfaceSwing;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import kernel.*;
import java.util.ArrayList;
import java.applet.*;
/**
 *
 * @author nathan
 */
public class GenindexMainInterface extends JFrame {
    
    private JFrame myFrame;
    private JPanel titre, boutons;
    private JLabel titrePage, nomLogi;
    private JLabel info1, info2, info3 ,info4 ,info5, info6, info7;
    private JButton bou1, bou2, bou3, bou4, bou5, bou6, bou7;
    public GenindexMainInterface () {
    
    myFrame = new JFrame("Genindex");
    myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    /////////////////////////////////////////////////////////////
    titre = new JPanel();    
    titre.setLayout(new GridLayout(2,1)); 
    nomLogi = new JLabel("GENINDEX", JLabel.CENTER);
    titrePage = new JLabel("Menu principale", JLabel.CENTER);
    titre.add(nomLogi);
    titre.add(titrePage);
    myFrame.add(titre);
    /////////////////////////////////////////////////////////////
    boutons = new JPanel(); 
    // ajouter une ligne pour chaque item à ajouter
    boutons.setLayout(new GridLayout(7,2));
    info1 = new JLabel("Créer un utilisateur", JLabel.CENTER);
    info2 = new JLabel("Créer une commande", JLabel.CENTER);
    info3 = new JLabel("Visionner les commandes", JLabel.CENTER);
    // va être utilisé pour l'US 8 de création de microplaques
    info4 = new JLabel("Créer une microplaque", JLabel.CENTER);
    info5 = new JLabel("Créer une espèce", JLabel.CENTER);
    info6 = new JLabel("Créer une catégorie", JLabel.CENTER);
    info7 = new JLabel("Créer un test de sexe", JLabel.CENTER);
    /////////////////////////////////////////////////////////////
    bou1 = new JButton("Créer");
    bou2 = new JButton("Créer");
    bou3 = new JButton("Visionner");
    bou4 = new JButton("Créer");
    bou5 = new JButton("Créer");
    bou6 = new JButton("Créer");
    bou7 = new JButton("Créer");
    /////////////////////////////////////////////////////////////
    boutons.add(info1);
    boutons.add(bou1);
    boutons.add(info2);
    boutons.add(bou2);
    boutons.add(info3);
    boutons.add(bou3);
    boutons.add(info4);
    boutons.add(bou4);
    boutons.add(info5);
    boutons.add(bou5);
    boutons.add(info6);
    boutons.add(bou6);
    boutons.add(info7);
    boutons.add(bou7);
    myFrame.add(boutons,BorderLayout.SOUTH);
    /////////////////////////////////////////////////////////////
    
    bou1.addActionListener(new ActionListener(){
    @Override
    public void actionPerformed(ActionEvent ae) {
    new CreateCustomer();   }});
    
    bou2.addActionListener(new ActionListener(){
    @Override
    public void actionPerformed(ActionEvent ae) {
    new CreateOrder();   }});
    
    bou3.addActionListener(new ActionListener(){
    @Override
    public void actionPerformed(ActionEvent ae) {
    new Order_Explorer();   }});
   
    //bou4.addActionListener(new ActionListener(){
    //@Override
    //public void actionPerformed(ActionEvent ae) {
    //new CreateMicroplate();   }});
   
    bou5.addActionListener(new ActionListener(){
    @Override
    public void actionPerformed(ActionEvent ae) {
    new CreateSpecie();   }});
   
    bou6.addActionListener(new ActionListener(){
    @Override
    public void actionPerformed(ActionEvent ae) {
    new CreateCategory();   }});
    
    bou7.addActionListener(new ActionListener(){
    @Override
    public void actionPerformed(ActionEvent ae) {
    new SexingTest();   }});
       
    myFrame.pack();
    myFrame.setVisible(true);
    }
    public static void main(String[] args) {
        GenindexMainInterface windows = new GenindexMainInterface();
        
    }
}
