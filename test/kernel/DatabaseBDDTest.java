/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kernel;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Maxime
 */
public class DatabaseBDDTest {
    
    public DatabaseBDDTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAdress method, of class Database.
     */
    @Test
    public void testGetAdress() {
        System.out.println("getAdress");
        int id = 0;
        Database instance = new Database();
        Adress expResult = null;
        Adress result = instance.getAdress(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCustomer method, of class Database.
     */
    @Test
    public void testGetCustomer() {
        System.out.println("getCustomer");
        int id = 0;
        Database instance = new Database();
        Customers expResult = null;
        Customers result = instance.getCustomer(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOrders method, of class Database.
     */
    @Test
    public void testGetOrders() {
        System.out.println("getOrders");
        int id = 0;
        Database instance = new Database();
        Orders expResult = null;
        Orders result = instance.getOrders(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAnimals method, of class Database.
     */
    @Test
    public void testGetAnimals() {
        System.out.println("getAnimals");
        int id = 0;
        Database instance = new Database();
        Animals expResult = null;
        Animals result = instance.getAnimals(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSamples method, of class Database.
     */
    @Test
    public void testGetSamples() {
        System.out.println("getSamples");
        int id = 0;
        Database instance = new Database();
        Samples expResult = null;
        Samples result = instance.getSamples(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSpecies method, of class Database.
     */
    @Test
    public void testGetSpecies() {
        System.out.println("getSpecies");
        int id = 0;
        Database instance = new Database();
        Species expResult = null;
        Species result = instance.getSpecies(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCategory method, of class Database.
     */
    @Test
    public void testGetCategory() {
        System.out.println("getCategory");
        int id = 0;
        Database instance = new Database();
        Category expResult = null;
        Category result = instance.getCategory(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAdressList method, of class Database.
     */
    @Test
    public void testGetAdressList() {
        System.out.println("getAdressList");
        Database instance = new Database();
        List<Adress> expResult = null;
        List<Adress> result = instance.getAdressList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCustomerList method, of class Database.
     */
    @Test
    public void testGetCustomerList() {
        System.out.println("getCustomerList");
        Database instance = new Database();
        List<Customers> expResult = null;
        List<Customers> result = instance.getCustomerList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOrderList method, of class Database.
     */
    @Test
    public void testGetOrderList() {
        System.out.println("getOrderList");
        Database instance = new Database();
        List<Orders> expResult = null;
        List<Orders> result = instance.getOrderList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAnimalList method, of class Database.
     */
    @Test
    public void testGetAnimalList() {
        System.out.println("getAnimalList");
        Database instance = new Database();
        List<Animals> expResult = null;
        List<Animals> result = instance.getAnimalList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSampleList method, of class Database.
     */
    @Test
    public void testGetSampleList() {
        System.out.println("getSampleList");
        Database instance = new Database();
        List<Samples> expResult = null;
        List<Samples> result = instance.getSampleList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSpeciesList method, of class Database.
     */
    @Test
    public void testGetSpeciesList() {
        System.out.println("getSpeciesList");
        Database instance = new Database();
        List<Species> expResult = null;
        List<Species> result = instance.getSpeciesList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCategoryList method, of class Database.
     */
    @Test
    public void testGetCategoryList() {
        System.out.println("getCategoryList");
        Database instance = new Database();
        List<Category> expResult = null;
        List<Category> result = instance.getCategoryList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInvoiceList method, of class Database.
     */
    @Test
    public void testGetInvoiceList() {
        System.out.println("getInvoiceList");
        Database instance = new Database();
        List<Invoice> expResult = null;
        List<Invoice> result = instance.getInvoiceList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTypeAnalysisList method, of class Database.
     */
    @Test
    public void testGetTypeAnalysisList() {
        System.out.println("getTypeAnalysisList");
        Database instance = new Database();
        List<TypeAnalysis> expResult = null;
        List<TypeAnalysis> result = instance.getTypeAnalysisList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOrdersForCustomer method, of class Database.
     */
    @Test
    public void testGetOrdersForCustomer_Customers() {
        System.out.println("getOrdersForCustomer");
        Customers customer = null;
        Database instance = new Database();
        List<Orders> expResult = null;
        List<Orders> result = instance.getOrdersForCustomer(customer);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOrdersForCustomer method, of class Database.
     */
    @Test
    public void testGetOrdersForCustomer_int() {
        System.out.println("getOrdersForCustomer");
        int id = 0;
        Database instance = new Database();
        List<Orders> expResult = null;
        List<Orders> result = instance.getOrdersForCustomer(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertAdress method, of class Database.
     */
    @Test
    public void testInsertAdress() {
        System.out.println("insertAdress");
        Adress adress = null;
        Database instance = new Database();
        instance.insertAdress(adress);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertOrder method, of class Database.
     */
    @Test
    public void testInsertOrder() {
        System.out.println("insertOrder");
        Orders order = null;
        Database instance = new Database();
        instance.insertOrder(order);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertCustomer method, of class Database.
     */
    @Test
    public void testInsertCustomer() {
        System.out.println("insertCustomer");
        Customers c = null;
        Database instance = new Database();
        instance.insertCustomer(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertAnimals method, of class Database.
     */
    @Test
    public void testInsertAnimals() {
        System.out.println("insertAnimals");
        Animals animal = null;
        Database instance = new Database();
        instance.insertAnimals(animal);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertCategory method, of class Database.
     */
    @Test
    public void testInsertCategory() {
        System.out.println("insertCategory");
        Category category = null;
        Database instance = new Database();
        instance.insertCategory(category);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertSpecies method, of class Database.
     */
    @Test
    public void testInsertSpecies() {
        System.out.println("insertSpecies");
        Species species = null;
        Database instance = new Database();
        instance.insertSpecies(species);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertSample method, of class Database.
     */
    @Test
    public void testInsertSample() {
        System.out.println("insertSample");
        Samples sample = null;
        Database instance = new Database();
        instance.insertSample(sample);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delCustomer method, of class Database.
     */
    @Test
    public void testDelCustomer() {
        System.out.println("delCustomer");
        int id = 0;
        Database instance = new Database();
        Customers expResult = null;
        Customers result = instance.delCustomer(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delAdress method, of class Database.
     */
    @Test
    public void testDelAdress() {
        System.out.println("delAdress");
        int id = 0;
        Database instance = new Database();
        Customers expResult = null;
        Customers result = instance.delAdress(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
