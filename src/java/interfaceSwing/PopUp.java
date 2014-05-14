/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaceSwing;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * @author Elodie
 */
public class PopUp extends JFrame {
    
    public JFrame myFrame;
    public JPanel myPanel;
    public JLabel myLabel;
    public JButton myButton;
    
    public PopUp(){
    
        myFrame = new JFrame();
        myLabel = new JLabel("Opération effectuée", JLabel.CENTER);
        myButton = new JButton ("OK");
        myPanel = new JPanel ();
        myPanel.setLayout(new GridLayout(2,1));
        myPanel.add(myLabel);
        myPanel.add(myButton, BorderLayout.CENTER);
        myFrame.add(myPanel);      
        myFrame.pack();
        myFrame.setVisible(true);
        
    myButton.addActionListener(new ActionListener(){
    @Override
    public void actionPerformed(ActionEvent ae) {
    myFrame.dispose();
   }});
        
    }
    
}
