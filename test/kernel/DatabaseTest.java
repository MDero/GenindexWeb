package kernel;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;
import org.junit.After;

public class DatabaseTest extends TestCase {


    Database d = new Database();
    Adress aTest = new Adress(99, 3, "rue du petit four", 99999, "Poitiers", "France");
    Customers cTest = new Customers("Maxime", "Dero", aTest, "0699644317", 99, 10);


    //ADDRESS TESTS
    //MDERO
    public void testDelAdress() {
        d.delAdress(99);
        System.out.println("DELETION ADRESS DONE");
    }
    public void testInsertAdress() {
        //public Adress(int id, int number, String street, int zip, String city, String country) {
        d.insertAdress(aTest);
        System.out.println("INSERT ADRESS DONE");
    }
    
    
    //CUSTOMERS TESTS
    
    //MDERO
    public void testDelCustomer() {
        d.delCustomer(99);
        System.out.println("DELETION CUSTOMER DONE");
    }

    //MDERO
    public void testInsertCustomer() { //VALIDATED BY MDERO
        //public Customers(String first, String last, Adress adress, String phone, int ID, int typeCusto) {
        d.insertCustomerWOID(cTest);
        System.out.println("INSERT CUSTOMER DONE");
    }

    //MDero
    public void testGetCustomer() {
        Customers c = d.getCustomer(0);
        System.out.println("GET CUSTOMER DONE");
    }

    @After
    public void after() {
        try {
            d.connexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("CONNEXION CLOSED");
    }
}
