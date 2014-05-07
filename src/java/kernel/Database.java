package kernel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import oracle.jdbc.OraclePreparedStatement;

public class Database {
    /* *****************************************************************************************/

    //Constructor
    Connection connexion;
    
    //Default connection MDERO
    public Database() {
        try {
            connexion = DriverManager.getConnection("jbdc:oracle:thin:@//192.168.24.3:1521/pfpbs", "gp27", "gp27");
            System.out.println("Connection to gphy successful");
        } catch (SQLException ex) {
            Logger.getLogger("ConnectBDD").log(Level.SEVERE, null, ex);
            System.out.println("failed to connect to gphy successfully");
        }
    }
    
    public Database(String url, String user, String password) {
        //DEFINE JDBC objects for connection
        try {
            connexion = DriverManager.getConnection(url, user, password);
            System.out.println("Connection to " + url + " successful for " + user);
        } catch (SQLException ex) {
            Logger.getLogger("ConnectBDD").log(Level.SEVERE, null, ex);
            System.out.println("failed to connect to " + url + " successful for " + user);
        }
    }

    /* *****************************************************************************************/
    /* CONVERSION METHODS */
    //FORMAT CONVERTER
    private static String extractString(ResultSet results, String fieldName) {
        String field = "";
        try {
            field = results.getString(fieldName).replaceAll("[ ]*$", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return field;
    }
    private static Integer extractNumber(ResultSet results, String fieldName) {
        Integer field = null;
        try {
            field = Integer.valueOf(results.getString(fieldName).replaceAll("[^0-9]*", ""));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return field;
    }
    private static kernel.Date extractDate(ResultSet results, String datename) {
        Date date = null;
        try {
            //sql date 
            java.sql.Date odate = results.getDate(datename);
            //convert the oracle date to kernel.date
            date = new Date(odate.getDay(), odate.getMonth(), odate.getYear());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return date;
    }
    private static String convertDateToString(kernel.Date date) {
        return date.getYear() + "-" + date.getMonth() + "-" + date.getDay();
    }

    //PREPARED QUERIES
    private ResultSet getResultSetFromIdQuery(String table, int id) {
        ResultSet results = null;
        try {
            OraclePreparedStatement ps = (OraclePreparedStatement) this.connexion.prepareStatement("SELECT * FROM :table WHERE Id_:table = :id");
            ps.setStringAtName("table", table);
            ps.setIntAtName("id", id);
            results = ps.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    //FROM RESULTSET	
    private Adress getAdressFromCurrentRow(ResultSet results){
        return  new Adress(
                    extractNumber(results,"Number"),
                    extractString(results, "Street"),
                    extractNumber(results,"ZipCode"),
                    extractString(results, "City"),
                    extractString(results, "Country")
            );
    }
    private Customers getCustomerFromCurrentRow(ResultSet results){
        Adress adress = this.getAdress(extractNumber(results,"ID_adress"));
        return new Customers(
                    extractString(results, "FirstName_custo"),
                    extractString(results, "LastName_custo"),
                    adress.getNumber(),
                    adress.getStreet(),
                    extractString(results, "PhoneNumber_custo"),
                    extractNumber(results,"Id_Customers")
            );
    }
    private Orders getOrderFromCurrentRow(ResultSet results) {
        return new Orders(
                (int) extractNumber(results, "NumberSamples"),
                extractDate(results, "DateOrder"),
                extractDate(results, "DateDeadline"),
                (int) extractNumber(results, "PriorityLevel"),
                getCustomer(extractNumber(results, "Id_customers"))
        );
    }
    private Animals getAnimalsFromCurrentRow(ResultSet results){
        
    }
    private Samples getSampleFromCurrentRow(ResultSet results){
        
    }
    private Species getSpeciesFromCurrentRow(ResultSet results){
        
    }
    
    //FROM IDS
    public Adress getAdress(int id) {
        Adress adress = null;

        //request all the orders from the database
        try {
            ResultSet results = getResultSetFromIdQuery("Adress",id);
            results.next();

            adress = this.getAdressFromCurrentRow(results);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return adress;
    }
    public Customers getCustomer(int id) {
        Customers customer = null;
        try {
            Statement request = this.connexion.createStatement();
            //request all the orders from the database
            request.execute("SELECT * FROM CUSTOMERS WHERE Id_customers=" + id);

            ResultSet results = request.getResultSet();
            results.next();

            customer = this.getCustomerFromCurrentRow(results);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customer;
    }
    public Orders getOrders(int id) {
        Orders order = null;
        try {
            ResultSet results = this.getResultSetFromIdQuery("Orders", id);
            results.next();

            order = this.getOrderFromCurrentRow(results);
            order = new Orders(
                    (int) extractNumber(results, "NumberSamples"),
                    extractDate(results, "DateOrder"),
                    extractDate(results, "DateDeadline"),
                    (int) extractNumber(results, "PriorityLevel"),
                    getCustomer(extractNumber(results, "Id_customers"))
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;
    }

    //FROM RESULTSET	
    private Adress getAdressFromCurrentRow(ResultSet results){
        return  new Adress(
                    extractNumber(results,"ID_ADRESS"), //ADD by MDERO
                    extractNumber(results,"Number"),
                    extractString(results, "Street"),
                    extractNumber(results,"ZipCode"),
                    extractString(results, "City"),
                    extractString(results, "Country")
            );
    }
    private Customers getCustomerFromCurrentRow(ResultSet results){
        Adress adress = this.getAdress(extractNumber(results,"ID_adress"));
        return new Customers(
                    extractString(results, "FirstName_custo"),
                    extractString(results, "LastName_custo"),
                    adress.getNumber(),
                    adress.getStreet(),
                    extractString(results, "PhoneNumber_custo"),
                    extractNumber(results,"Id_Customers")
            );
    }
    private Orders getOrderFromCurrentRow(ResultSet results) {
        return new Orders(
                (int) extractNumber(results, "NumberSamples"),
                extractDate(results, "DateOrder"),
                extractDate(results, "DateDeadline"),
                (int) extractNumber(results, "PriorityLevel"),
                getCustomer(extractNumber(results, "Id_customers"))
        );
    }

    //LISTS ESTABLISHMENT
    public List<Orders> getOrderList() {
        ArrayList<Orders> orderList = new ArrayList<>();

        try {
            Statement request = this.connexion.createStatement();

            //request all the orders from the database
            request.execute("SELECT * FROM ORDERS");

            ResultSet results = request.getResultSet();

            //put the results in the list
           while(results.next()){
               orderList.add(this.getOrderFromCurrentRow(results));
           }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderList;
    }

    /* INSERTION METHODS */
    public void insertAdress(Adress adress) {
        try {
            Statement s = this.connexion.createStatement();
            s.executeQuery("INSERT INTO Adress values("
                    + adress.getNumber() + ","
                    + adress.getStreet() + ","
                    + adress.getZipCode() + ","
                    + adress.getCity() + ","
                    + adress.getCountry()
                    + ");");

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void insertOrder(Orders order) {
        try {
            Statement s = this.connexion.createStatement();
            s.executeQuery("INSERT INTO Orders values("
                    + order.getId() + ","
                    + order.getCustomerID() + ","
                    + order.getPriorityLevel() + ", "
                    + order.getSamples().size() + ", "
                    + convertDateToString(order.getDeadLine()) + ","
                    + convertDateToString(order.getDateOrder()) + ","
                    + order.getPaid() + ","
                    + order.getResultSend() + ","
                    + order.getReport()
                    + ");");

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    //MDERO
    public void insertCustomer(Customers c) {
        try {
            Statement s = this.connexion.createStatement();
            s.executeQuery("INSERT INTO Customers values("+
                    c.getID()+","+
                    c.getTypeCusto()+","+
                    c.getAdress().getIdAdress()+","+
                    "0"+ //ID CORPORATE TODO
                    c.getFirstName()+","+
                    c.getLastName()+","+
                    c.getPhoneNumber()+","+
                    c.getEmail()+","+
                    c.getPhone()+","+
                    ");");
            
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
