package kernel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import oracle.jdbc.OraclePreparedStatement;
import kernel.Adress;
import kernel.Customers;
import kernel.Date;
import kernel.Orders;

public class Database {
    /* *****************************************************************************************/

    //Constructor
    Connection connexion;

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
    private static kernel.Date extractDate(ResultSet results, String datename){
        Date date = null;
        try {
            //sql date 
            java.sql.Date odate = results.getDate(datename);
            //convert the oracle date to kernel.date
            date=new Date(odate.getDay(),odate.getMonth(),odate.getYear());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return date;
    }
    private static String convertDateToString(kernel.Date date ){
        return date.getYear()+"-"+date.getMonth()+"-"+date.getDay();
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

    //FROM IDS
    public Adress getAdress(int id) {
        Adress adress = null;

        //request all the orders from the database
        try {
            Statement request = this.connexion.createStatement();
            request.execute("SELECT * FROM ADRESS WHERE Id_adress=" + id);

            ResultSet results = request.getResultSet();
            results.next();

            adress = new Adress(
                    Integer.valueOf("" + results.getBigDecimal("Number")),
                    extractString(results, "Street"),
                    //(""+results.getObject("Street")).replaceAll("[ ]*$", ""),
                    Integer.valueOf("" + results.getBigDecimal("ZipCode")),
                    extractString(results, "City"),
                    //(""+results.getObject("City")).replaceAll(" ", ""),
                    extractString(results, "Country")
            //(""+results.getObject("Country")).replaceAll(" ", "")
            );

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

            Adress adress = this.getAdress(Integer.valueOf("" + results.getObject("Id_adress")));
            System.out.println(adress.getStreet() + " dans la ville de " + adress.getCity());

            //request all the orders from the database
            request.execute("SELECT * FROM CUSTOMERS WHERE Id_customers=" + id);

            results = request.getResultSet();
            results.next();

            customer = new Customers(
                    extractString(results, "FirstName_custo"),
                    extractString(results, "LastName_custo"),
                    (Integer) adress.getNumber(),
                    adress.getStreet(),
                    extractString(results, "PhoneNumber_custo"),
                    id
            );
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

            //public Orders(int num_samples, Date date_order, Date date_deadline, int priority, Customers customer) {
            order = new Orders(
                    (int) extractNumber(results, "NumberSamples"),
                    extractDate(results,"DateOrder"),
                    extractDate(results,"DateDeadline"),
                    (int) extractNumber(results, "PriorityLevel"),
                    getCustomer(extractNumber(results, "Id_customers"))
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;
    }

    //FROM RESULTSET	
    public Orders getOrderFromCurrentRow(ResultSet results) {
        Orders order = null;

        try {
            order = new Orders(
                    (Integer) results.getObject("NumberSamples"),
                    (Date) results.getObject("DateOrder"),
                    (Date) results.getObject("DateDeadline"),
                    (Integer) results.getObject("PriorityLevel"),
                    this.getCustomer((int) results.getObject("Id_customers"))
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;
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
            for (int i = 0; i < results.getFetchSize(); i++) {
                results.getObject("");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderList;
    }

    /* INSERTION METHODS */ 
    public void insertAdress(Adress adress){
          try {
            Statement s = this.connexion.createStatement();
            s.executeQuery("INSERT INTO Adress values("+
                        adress.getNumber()+","+
                        adress.getStreet()+","+
                        adress.getZipCode()+","+
                        adress.getCity()+","+
                        adress.getCountry()+
                    ");");
            
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void insertOrder(Orders order){
        try {
            Statement s = this.connexion.createStatement();
            s.executeQuery("INSERT INTO Orders values("+
                    order.getId()+","+
                    order.getCustomerID()+","+
                    order.getPriorityLevel()+", "+
                    order.getSamples().size()+", "+
                    convertDateToString(order.getDeadLine())+","+
                    convertDateToString(order.getDateOrder())+","+
                    order.getPaid()+","+
                    order.getResultSend()+","+
                   // order.
                    ");");
            
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
