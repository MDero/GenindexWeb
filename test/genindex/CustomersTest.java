package genindex;

 

import kernel.Adress;
import kernel.Customers;
import genindex.Database;
import junit.framework.TestCase;

public class CustomersTest extends TestCase {
	
	/*
	 * first we created an instance of analysis by using the method of database, like that we test 
	 * database in same time
	 */
	Database d = new Database();
	Customers c =  d.searchCustomerID(1);
	Adress ad = new Adress(86000,"Poitiers");

	/*
	 * We test if setName can change variables firstName and SecondName
	 * We must get back the two new variables
	 */
	public void testSetName() {
		c.setName("first", "last");
		assertTrue(c.getFirstName().equals("first")&&c.getLastName().equals("last"));
	}
	/* true
	 * Variables name of our object are correctly changed
	 */
//------------------------------------------------------------------------------------------------
	/*
	 * We test if getLastName get back the good string
	 * We must get back the string gave by the constructor
	 */
	public void testGetLastName() {
		assertTrue(c.getLastName().equals("dupont"));
	}
	/* true
	 * variable got back is the good
	 */
//------------------------------------------------------------------------------------------------
	/*
	 * We test if getFirstName get back the good string
	 * We must get back the string gave by the constructor
	 */
	public void testGetFirstName() {
		assertTrue(c.getFirstName().equals("jean"));
	}
	/* true
	 * variable got back is the good
	 */
//------------------------------------------------------------------------------------------------
	/*
	 * We test if setPhone can change the number phone gave by the constructor
	 * We must get back the new variables we will give
	 */
	public void testSetPhone() {
		c.setPhone("0692226601");
		assertTrue(c.getPhone().equals("0692226601"));
	}
	/* true
	 * Variable got back is the variable give by setPhone
	 */
//------------------------------------------------------------------------------------------------
	/*
	 * We test if getPhone get back the number Phone defines by the constructor
	 * We must get back the string gave by the constructor
	 */
	public void testGetPhone() {
		assertTrue(c.getPhone().equals("090909"));
	}
	/* true
	 * Variable got back is the variable of the constructor
	 */
//------------------------------------------------------------------------------------------------
	/*
	 * We test if getID get back the ID defines by the constructor
	 * We must get back the integer gave by the constructor
	 */
	public void testGetID() {
		assertTrue(c.getID()==1);
	}
	/* true
	 * Variable got back is the variable of the constructor
	 */
//------------------------------------------------------------------------------------------------
	/*
	 * We test if setID can change the ID of our instance
	 * We must get back the new ID we will give
	 */
	public void testSetID() {
		c.setID(2);
		assertTrue(c.getID()==2);
	}
	/* true
	 * Variable got back is the variable that we have change with setID
	 */
//------------------------------------------------------------------------------------------------
	/*
	 * We test if setEmail can change the variable Email and if getEmail get back the good variable
	 * We must get back the new Email we will change
	 */
	public void testSetEmail() {
		c.setEmail("poit@hotgle.fr");
		assertTrue(c.getEmail().equals("poit@hotgle.fr"));
	}
	/* true
	 * Variable got back is the variable that we have change
	 */
//------------------------------------------------------------------------------------------------
	/*
	 * We test if setAdress can give a new object address and if getAdress get back the 
	 * good object that we have gave
	 * We must get back the address create in the test constructor
	 */
	public void testSetAdress() {
		c.setAdress(ad);
		assertTrue(c.getAdress()==ad);
	}
	/* true
	 * Variable got back is the variable that we have change
	 */
}
