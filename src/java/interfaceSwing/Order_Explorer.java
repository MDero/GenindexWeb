package interfaceSwing;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import kernel.Customers;
import kernel.Database;
public class Order_Explorer extends JFrame {
	
    static Database database = new Database();
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuItem;
    private String[] items;
    
public Order_Explorer(){
		
    JFrame myFrame = new JFrame("Explore Order");
    myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    myFrame.setSize(2000, 650);
    
// Creation of the menu bar
    menuBar = new JMenuBar();
    myFrame.setJMenuBar(menuBar);
    
 // Creation of the file menu
    menu = new JMenu("Files");
    menuItem = new JMenuItem("Exit");
    menu.add(menuItem);
    menuBar.add(menu);
    
 // On créé un panel général        
    
    JPanel general = new JPanel();  
    
 // Panel north
    JPanel panelNorth=new JPanel();
    panelNorth.setLayout(new GridLayout(1,2));

    
 // Création tableau de commande
    JPanel panelCenter=new JPanel(); 
    JTable table = new JTable(new MyTableModel());
    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setColumnHeaderView(table.getTableHeader());
    panelCenter.add(scrollPane);

 // Création menu déroulant
    JLabel Select = new JLabel("Selectioner un client");
    
    ArrayList<String> names = new ArrayList<>();
    for (Customers customer : database.getCustomerList()){
        names.add(customer.getFirstName()+" "+customer.getLastName());
    }
    items = new String[names.size()];
    for (int i =0; i<items.length;i++)
        items[i]=names.get(i);
    JComboBox Customers = new JComboBox(items);
    
    panelNorth.add(Select);
    panelNorth.add(Customers);
    
 // On met les différents panel a chaque endroit de la frame.
    general.setLayout(new BorderLayout());
    general.add(panelNorth,BorderLayout.NORTH);
    general.add(panelCenter,BorderLayout.CENTER);
       
    myFrame.add(general);
    myFrame.pack();
    myFrame.setVisible(true);
    

}

}
class MyTableModel extends AbstractTableModel {
    private String[] columnNames = {"Num commande",
                                    "Nom commande",
                                    "Statut"
                                    };
    private Object[][] data = {
    {"1", "outils","en cours"},{"2", "medoc","terminé"},{"3", "produit","en cours"}
    };

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    public boolean isCellEditable(int row, int col) {
            return false;
        }
    public boolean isCellSelected(int row, int col) {
        return true;
    }
    public static void main(String[] args) {
        Order_Explorer windows = new Order_Explorer ();
    }	
}

