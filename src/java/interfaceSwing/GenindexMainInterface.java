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
    
    public GenindexMainInterface () {
    
    myFrame = new JFrame("Genindex");
    myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    titre = new JPanel();    
        
    //newCategorie.addActionListener(new ActionListener(){
    //@Override
    //public void actionPerformed(ActionEvent ae) {
    //new CreateCategory();   }});
    
       
    myFrame.pack();
    myFrame.setVisible(true);
    }
    public static void main(String[] args) {
        //Database db = new Database();
        
    }
}
