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
import kernel.Samples;
public class Order_Explorer extends JFrame implements ActionListener{

	static Database database = new Database();
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem;
	private String[] items;
	private Integer[] items2;
	private JPanel panelCenter;
	private JTable tableOrders, tableSamples;
            private int lastSelectedIndex,lastOrderEmptyRow=0, lastSampleEmptyRow=0;
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
        panelCenter.setLayout(new BoxLayout(panelCenter,BoxLayout.Y_AXIS));

	//TABLE D ORDERS
        tableOrders = new JTable(new DefaultTableModel(new Object[]{"id","Statut","Payé/Non Payé","Nombre d'analyses"},10));
        lastOrderEmptyRow=10;//de base on a 10 lignes pour le swag
        
        panelCenter.add(tableOrders);
//        JButton button = new JButton("Voir échantillons");
//        panelCenter.add(button);
        
	JScrollPane scrollPane = new JScrollPane(tableOrders);
        scrollPane.setPreferredSize(new Dimension(800,100));
	scrollPane.setColumnHeaderView(tableOrders.getTableHeader());
	panelCenter.add(scrollPane);

        //TABLE DE SAMPLES CORRESPONDANT A L ORDER
        tableSamples = new JTable(new DefaultTableModel(new Object[]{"Identifier","Type","Créé le","Admis le","Etat"},20));
        lastSampleEmptyRow = 20;
        
        panelCenter.add(tableSamples);
        
        JScrollPane scrollPane2 = new JScrollPane(tableSamples);
        scrollPane2.setPreferredSize(new Dimension(800,100));
	scrollPane2.setColumnHeaderView(tableOrders.getTableHeader());
	panelCenter.add(scrollPane2);
        
 // On met les différents panel a chaque endroit de la frame.
	general.setLayout(new BorderLayout());
	general.add(panelNorth,BorderLayout.NORTH);
	general.add(panelCenter,BorderLayout.CENTER);

	myFrame.add(general);
	myFrame.pack();
        //myFrame.setResizable(false);
	myFrame.setVisible(true);


}

	@Override
	public void actionPerformed(ActionEvent e) {
            JComboBox cb = (JComboBox)e.getSource();
            
            //check if customer changed
            if (cb.getSelectedIndex()!=this.lastSelectedIndex){
                lastSelectedIndex = cb.getSelectedIndex();
                
                ArrayList<Orders> orders = (ArrayList<Orders>) database.getOrdersForCustomer(cb.getSelectedIndex());
                //clear screen
                for (int i = 0 ; i<Order_Explorer.this.lastOrderEmptyRow;i++)
                     ((DefaultTableModel)Order_Explorer.this.tableOrders.getModel()).removeRow(0);
                Order_Explorer.this.lastOrderEmptyRow=0;
                for (int i = 0 ; i<lastSampleEmptyRow;i++)
                     ((DefaultTableModel)tableSamples.getModel()).removeRow(0);
                this.lastSampleEmptyRow=0;
                
                //add new rows
                if (orders.size()>0){
                    for (Orders order : orders){
                        ((DefaultTableModel)tableOrders.getModel()).addRow(new Object[]{order.getId(),order.getResultSend()? "envoyé":"en cours",order.getPaid()? "payé" : "non payé",order.getNumberSamples()});    
                        tableOrders.addMouseListener(new MouseListener(){
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                System.out.println("clicked");
                                int row = tableOrders.getSelectedRow();
                
                                if (row != -1){
                                    int orderId = (int) tableOrders.getModel().getValueAt(row, 0); //id 
                                    
                                    Orders thisOrder = database.getOrders(orderId);
                                    
                                    //clear screen
                                    for (int i = 0 ; i<lastSampleEmptyRow;i++)
                                         ((DefaultTableModel)tableSamples.getModel()).removeRow(0);
                                    lastSampleEmptyRow=0;
                                    
                                    ArrayList<Samples> samples = (ArrayList<Samples>) thisOrder.getSamples();

                                    //add new rows
                                    if (samples.size()>0){
                                        System.out.println("HYEAH");
                                        for (Samples sample : samples){
                                            ((DefaultTableModel)tableSamples.getModel()).addRow(new Object[]{sample.getId(),sample.getType().getTypeName(),sample.getDateSampling().toString(),sample.getDateStorage().toString(),sample.getAnalysisCount()>0 ? "analysé "+sample.getAnalysisCount()+" fois":"non analysé"});
                                            lastSampleEmptyRow++;
                                        }
                                    }
                                }
                            }

                            @Override
                            public void mousePressed(MouseEvent e) {
//                                System.out.println("pressed");
                            }

                            @Override
                            public void mouseReleased(MouseEvent e) {
//                                System.out.println("released");
                            }

                            @Override
                            public void mouseEntered(MouseEvent e) {
//                                System.out.println("entered");
                            }

                            @Override
                            public void mouseExited(MouseEvent e) {
//                                System.out.println("exited");
                            }
                            
                        });
                        Order_Explorer.this.lastOrderEmptyRow++;
                    }
                }
            }
            //check for order selection
            else {

            }
            
            
	}
//class MyTableModel extends AbstractTableModel {
//    private String[] columnNames = {"Num commande",
//                                    "Nom commande",
//                                    "Payé","Envoyé"
//                                    };
//    private Object[][] data = {
//    {"1", "outils","en cours"},{"2", "medoc","terminé"},{"3", "produit","en cours"}
//    };
//
//    public int getColumnCount() {
//        return columnNames.length;
//    }
//
//    public int getRowCount() {
//        return data.length;
//    }
//
//    public String getColumnName(int col) {
//        return columnNames[col];
//    }
//
//    public Object getValueAt(int row, int col) {
//        return data[row][col];
//    }
//
//    public boolean isCellEditable(int row, int col) {
//            return false;
//        }
//    public boolean isCellSelected(int row, int col) {
//        return true;
//    }
//}
	public static void main(String[] args) {
		new Order_Explorer ();
	}	
}

