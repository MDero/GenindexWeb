package interfaceSwing;
import static interfaceSwing.Order_Explorer.database;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import kernel.Customers;
import kernel.Database;
import kernel.Orders;
public class Order_Explorer extends JFrame implements ActionListener{

	static Database database = new Database();
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem;
	private String[] items;
	private Integer[] items2;
	private JPanel panelCenter;
	private JTable table;
            private int lastEmptyRow=0;
	private JScrollPane scrollPane;

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

 // Création menu déroulant
	JLabel Select = new JLabel("Selectioner un client");

	ArrayList<String> names = new ArrayList<>();
	for (Customers customer : database.getCustomerList()){
                System.out.println("CUSTOMER FOUND");
		names.add(customer.getFirstName()+" "+customer.getLastName());
	}
	items = new String[names.size()];
	for (int i =0; i<items.length;i++)
		items[i]=names.get(i);
	JComboBox Customers = new JComboBox(items);
	Customers.addActionListener(this);

	panelNorth.add(Select);
	panelNorth.add(Customers);

// création du tableau
        
	panelCenter=new JPanel();


	//TABLE D ORDERS
        table = new JTable(new DefaultTableModel(new Object[]{"id","Statut","Payé/Non Payé","Nombre d'analyses"},10));
        lastEmptyRow=10;//de base on a 10 lignes pour le swag
        
        panelCenter.add(table);
        
	JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(800,100));
	scrollPane.setColumnHeaderView(table.getTableHeader());
	panelCenter.add(scrollPane);

        //TABLE DE SAMPLES CORRESPONDANT A L ORDER
        
        
 // On met les différents panel a chaque endroit de la frame.
	general.setLayout(new BorderLayout());
	general.add(panelNorth,BorderLayout.NORTH);
	general.add(panelCenter,BorderLayout.CENTER);

	myFrame.add(general);
	myFrame.pack();
	myFrame.setVisible(true);


}

	@Override
	public void actionPerformed(ActionEvent e) {

		// TODO Auto-generated method stub

				JComboBox cb = (JComboBox)e.getSource();

                                ArrayList<Orders> orders = (ArrayList<Orders>) database.getOrdersForCustomer(cb.getSelectedIndex());
                                //clear screen
                                for (int i = 0 ; i<Order_Explorer.this.lastEmptyRow;i++)
                                     ((DefaultTableModel)Order_Explorer.this.table.getModel()).removeRow(0);
                                Order_Explorer.this.lastEmptyRow=0;
                                //add new rows
                                if (orders.size()>0){
                                    for (Orders order : orders){
                                        ((DefaultTableModel)Order_Explorer.this.table.getModel()).addRow(new Object[]{order.getId(),order.getResultSend(),order.getPaid(),order.getNumberSamples()});                                    
                                        Order_Explorer.this.lastEmptyRow++;
                                    }
                                }
	}
class MyTableModel extends AbstractTableModel {
    private String[] columnNames = {"Num commande",
                                    "Nom commande",
                                    "Payé","Envoyé"
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
}
	public static void main(String[] args) {
		new Order_Explorer ();
	}	
}

