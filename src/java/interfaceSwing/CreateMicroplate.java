/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceSwing;

import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/*
 *
 * @author Caro
 */

public class CreateMicroplate {
    // Déclaration des variables
        private JMenuBar menuBar;
        private JFrame myFrame;
        
        public CreateMicroplate (){
        JFrame myFrame = new JFrame("Create microplate");
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
        nom = new JLabel("Nom*:");
}
