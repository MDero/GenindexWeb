package kernel;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;
import org.junit.After;

public class DatabaseTest extends TestCase {


    Database d = new Database();
    Adress aTest = new Adress(3, "rue du petit four", 99999, "Poitiers", "France");
    Customers cTest = new Customers("Maxime", "Dero", aTest, "0699644317","maxime.dero@gmail.com", 1);
    


    //ADDRESS TESTS
    //MDERO
    public void testInsertAdress() {
        //public Adress(int id, int number, String street, int zip, String city, String country) {
        d.insertAdress(aTest);
        System.out.println("INSERT ADRESS DONE");
    }
    
     public void testDelAdress() {
        d.delAdress(2);
        System.out.println("DELETION ADRESS DONE");
    }
    
    //CUSTOMERS TESTS
    
    //MDERO
    

    //MDERO
    public void testInsertCustomer() { //VALIDATED BY MDERO
        //public Customers(String first, String last, Adress adress, String phone, int ID, int typeCusto) {
        cTest.getAdress().setID(2);
        System.out.println("testInsertCustomer : " + cTest);
        d.insertCustomer(cTest);
        System.out.println("INSERT CUSTOMER DONE");
    }
    
    
    public void testDelCustomer() {
        d.delCustomer(99);
        System.out.println("DELETION CUSTOMER DONE");
    }
    

    //MDero
    public void testGetCustomer() {
        Customers c = d.getCustomer(0);
        System.out.println("GET CUSTOMER DONE");
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


    public void testClose() {
        try {
            d.connexion.close();
            System.out.println("CONNEXION CLOSED");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
