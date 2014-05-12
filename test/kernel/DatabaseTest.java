package kernel;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;
import org.junit.After;

public class DatabaseTest extends TestCase {


    Database d = new Database();
    Adress aTest = new Adress(100, 3, "rue du petit four", 99999, "Poitiers", "France");
    Customers cTest = new Customers("Maxime", "Dero", aTest, "0699644317", 99, 10);


    //ADDRESS TESTS
    //MDERO
    public void testInsertAdress() {
        //public Adress(int id, int number, String street, int zip, String city, String country) {
        d.insertAdress(aTest);
    }
    
    
    //CUSTOMERS TESTS
    
    //MDERO
    public void testDelCustomer() {
        d.delCustomer(99);
    }

    //MDERO
    public void testInsertCustomer() { //VALIDATED BY MDERO
        //public Customers(String first, String last, Adress adress, String phone, int ID, int typeCusto) {
        d.insertCustomerWOID(cTest);
    }

    //MDero
    public void testGetCustomer() {
        Customers c = d.getCustomer(0);
    }

    @After
    public void after() {
        try {
            d.connexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
