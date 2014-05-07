package kernel;

 


import junit.framework.TestCase;

public class DatabaseTest extends TestCase {
	
	//this test permits to check if the function listOrder return the orders in the database
	
	public void testListOrder_0(){
		Database b = new Database();
		assertTrue(b.getListOrder().size()==1);
	} 
	//There is only one order in the database, and the size of the list is 1 so the test is successful.

}


