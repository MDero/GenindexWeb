package interfaceSwing;
import static interfaceSwing.Order_Explorer.database;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
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


	JPanel panelCenter=new JPanel();

	String[] columnNames = {"Id commande",
									"Statut",
"Nombre d'analyse"};
		Object[][] data = {{items2,"",""}};

	JTable table = new JTable(data,columnNames);
	JScrollPane scrollPane = new JScrollPane(table);
	scrollPane.setColumnHeaderView(table.getTableHeader());
	panelCenter.add(scrollPane);

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

				if (cb.getSelectedIndex() == 1){
				ArrayList<Integer> names2 = new ArrayList<>();
				for (Orders order : database.getOrdersForCustomer(1)){
					names2.add(order.getId());
					System.out.println(order.getId());
				}
				items2 = new Integer[names2.size()];
				for (int i =0; i<items2.length;i++)
				items2[i]=names2.get(i);
				}
	}

	public static void main(String[] args) {
		Order_Explorer window = new Order_Explorer ();
	}	
}

