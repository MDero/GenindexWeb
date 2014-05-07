package kernel;

 


import junit.framework.TestCase;

public class DatabaseTest extends TestCase {
	
	//this test permits to check if the function listOrder return the orders in the database
	Database d = new Database();
        //TODO: Test ADRESS insert
        
        
        //CUSTOMERS TESTS
        //MDERO
        public void testInsertCustomer() {
            //public Customers(String first, String last, Adress adress, String phone, int ID, int typeCusto) {
            //  public Adress(int id, int number, String street, int zip, String city, String country) {
            
            Customers cTest = new Customers("Maxime", "Dero",new Adress (2,3,"rue du petit four",99999,"Poitiers","France"), "0699644317", 99,1);
            d.insertCustomer(cTest);
        }
        
        public void testGetCustomer() {
            Customers c =  d.getCustomer(99);
        }
	
        
        
	
	//There is only one order in the database, and the size of the list is 1 so the test is successful.

}


