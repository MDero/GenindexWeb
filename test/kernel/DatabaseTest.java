package kernel;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;
import org.junit.After;

public class DatabaseTest extends TestCase {


    Database d = new Database();
    Adress aTest = new Adress(3, "rue du petit four", 99999, "Poitiers", "France");
    Customers cTest = new Customers("Maxime", "Dero", "0699644317","maxime.dero@gmail.com", 1);
    


    //ADDRESS TESTS
    //MDERO
    public void testInsertAdress() {
        //public Adress(int id, int number, String street, int zip, String city, String country) {
        d.insertAdress(aTest);
        System.out.println("INSERT ADRESS DONE : " + aTest.getIdAdress();
        System.out.println();
    }
    
     public void testDelAdress() {
        d.insertAdress(aTest);
        d.delAdress(aTest.getIdAdress());
        System.out.println("DELETION ADRESS DONE : " + aTest.getIdAdress());
        System.out.println();
    }
    
    //CUSTOMERS TESTS
    //MDERO
    public void testInsertCustomer() { //VALIDATED BY MDERO
        d.insertCustomer(cTest);
        System.out.println("INSERT CUSTOMER DONE : " + cTest.getID());
        System.out.println();
    }
    
    
    public void testDelCustomer() {
        d.insertCustomer(cTest);
        d.delCustomer(cTest.getID());
        System.out.println("DELETION CUSTOMER DONE : " + cTest.getID());
        System.out.println();
    }
    

    //MDero
    public void testGetCustomer() {
        d.insertCustomer(cTest);
        Customers c = d.getCustomer(cTest.getID());
        System.out.println("GET CUSTOMER DONE");
        System.out.println();
    }
    
    //Vincent
    public void OLDtestInsertIntoTableFromParticularFields(){
        System.out.println("test insert ");
        int idtest = 789;
        Category c = Category.getOrCreateCategory(idtest, "CategorieTest");
//        d.insertCategory(c);
        Species s = new Species(456,c,"EspeceDeCategorieTest");
        d.insertSpecies(s);
//        d.insertAnimals(new Animals(s,9,"Fido"));
        System.out.println("test insert DONE");
    }



    public void TestTearDown(){
        try {
            d.connexion.close();
            System.out.println("CONNEXION CLOSED");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
