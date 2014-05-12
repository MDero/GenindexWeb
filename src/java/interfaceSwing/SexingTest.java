package interfaceSwing;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;


/**
 *
 * @author Nathan
 */
public class SexingTest extends JFrame {

    private JButton valider,annuler;
    private JFrame myFrame;
    private JPanel topPanel, botPanel,midPanel;
    private JComboBox species;
    private JLabel presentation,info1, info2 ;
    private JTextField specificite;
    
    public SexingTest () {
        myFrame = new JFrame("Créer un test sur le sexe");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.presentation = new JLabel ("Céation d'un test sur le Sexe",JLabel.CENTER);
        this.info1 = new JLabel ("Choisir une espèce",JLabel.CENTER);
        this.info2 = new JLabel ("Entrer les informations du gène",JLabel.CENTER);
        this.valider = new JButton("Valider");
        this.annuler = new JButton("Annuler");
        this.species = new JComboBox();
        this.specificite = new JTextField();
        topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1,1));
        midPanel = new JPanel();
        midPanel.setLayout(new GridLayout(1,4));
        botPanel = new JPanel();
        botPanel.setLayout(new GridLayout(1,2));
        topPanel.add(presentation);
        midPanel.add(info1);
        midPanel.add(species);
        midPanel.add(info2);
        midPanel.add(specificite);
        botPanel.add(valider);
        botPanel.add(annuler);
        
        
        myFrame.add(topPanel, BorderLayout.NORTH);
        myFrame.add(midPanel, BorderLayout.CENTER);
        myFrame.add(botPanel, BorderLayout.SOUTH);
        
        annuler.addActionListener (new ActionListener () 
        {
            @Override
            public void actionPerformed (ActionEvent e) 
            {
             System.exit(0);
            }
        } );
        myFrame.pack();
        myFrame.setVisible(true);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SexingTest windows = new SexingTest ();
    }
}
