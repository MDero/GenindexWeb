package genindex;

 

import genindex.Database;
import genindex.Types_analysis;
import genindex.Users;
import junit.framework.TestCase;

public class UsersTest extends TestCase {
	
	/*
	 * ***These functions permit to check the functions get and set***
	 */
	
	/*
	 * This test permits to test the function getID
	 */
	public void testGetId(){
		Database b = new Database();
		Users u = b.getUser();
		assertTrue(u.getId()==1);
	}
	/*
	 * The id of the User is defined with a static variable. So if it is the first time we call the constructor Database the Id is 1 
	 * if not, the Id is not 1
	 */
	
	
	
	
	/*
	 * This test permits to test the function getLastName
	 */
	public void testGetLastName() {
		Database b = new Database();
		Users u = b.getUser();
		assertTrue(u.getLastName()=="dupont");	
	}
	//Like expected, the function returns the lastName "dupont"
	
	
	/*
	 * This test permits to test the function getFirstName
	 */
	public void testGetFirstName() {
		Database b = new Database();
		Users u = b.getUser();
		assertTrue(u.getFirstName()=="jean");	
	}
	//Like expected, the function returns the firstName "jean"
	
	/*
	 * These tests permit to test the function getLogin
	 */
	public void testGetLogin(){
		Database b = new Database();
		Users u = b.getUser();
		assertTrue(u.getLogin().contains("jdupont"));
	}
	//Like expected, the function returns "jdupont"
	
	/*
	 * This test permits to test the function setFirstName
	 */
	public void testSetFirstName() {
		Database b = new Database();
		Users u = b.getUser();
		u.setFirstName("ali");
		assertTrue(u.getFirstName()=="ali");	
	}
	//Like expected, the firstName has been changed
	
	
	/*
	 * This test permits to test the function setLastName
	 */
	public void testSetLastName() {
		Database b = new Database();
		Users u = b.getUser();
		u.setLastName("ali");
		assertTrue(u.getLastName()=="ali");	
	}
	//Like expected, the lastName has been changed
	
	
	/*
	 * This test permits to test the function getEmail
	 */
	public void testGetEmail() {
		Database b = new Database();
		Users u = b.getUser();
		assertTrue(u.getEmail()=="@");	
	}
	//The function returns the email expected (the same that the email of the user in the database)

	
	/*
	 * This test permits to test the function setEmail
	 */
	public void testSetEmail() {
		Database b = new Database();
		Users u = b.getUser();
		u.setEmail("aaaa");
		assertTrue(u.getEmail()=="aaaa");
	}
	//Like expected, the email has been changed
	
	
	/*
	 * This test permits to test the function setAdministrativeAuth
	 */
	public void testSetAdministrativeAuth(){
		Database b = new Database();
		Users u = b.getUser();
		u.setAdministrativeAuth(true);
		assertTrue(u.getAdministrativeAuth()==true);
	}
	//Like expected, the boolean change from FALSE to TRUE
	
	
	/*
	 * This test permits to test the function getPassword
	 */
	public void testGetPassword(){
		Database b = new Database();
		Users u = b.getUser();
		assertTrue(u.getPassword().contains("jdupont"));
	}
	//Like expected, the function returns the password registered in the database
	
	/*
	 * This test permits to test the function setPassword
	 */
	public void testSetPassword(){
		Database b = new Database();
		Users u = b.getUser();
		u.setPassword("abc");
		assertTrue(u.getPassword().contains("abc"));
	}
	//Like expected, the password has been changed
	
	
	
	/*
	 * This test permits to test the function setTechnicalAuth
	 */
	public void testSetTechnicalAuth(){
		Database b = new Database();
		Users u = b.getUser();
		Types_analysis type = new Types_analysis("PCR", 40);
		u.addTechnicalAuth(1, type);
		u.setTechnicalAuth(2,type);
		assertTrue(((int)u.getTechnicalAuth().get(type))==2);
	}
	//Like expected, the technicalAuthorization has been changed
	
	
	
	/*
	 * These tests permit to test the function addTechnicalAuth
	 * This function permits to add a technical authorization (level of authorization and type of analysis) in the hashmap technicalAuthorization
	 */
	
	public void testAddTechnicalAuth_1(){
		Database b = new Database();
		Users u = b.getUser();
		Types_analysis type = new Types_analysis("PCR", 40);
		u.addTechnicalAuth(1, type);
		u.listTechnicalAuth();
		assertTrue(u.getTechnicalAuth().size()==1);	
	}
	//We added 1 technical authorization in the hashmap, so the size of the hashmap is 1
	
	public void testAddTechnicalAuth_0(){
		Database b = new Database();
		Users u = b.getUser();
		Types_analysis type = new Types_analysis("PCR", 40);
		Types_analysis type2 = new Types_analysis("DNAtest", 40);
		Types_analysis type3 = new Types_analysis("Southernblot", 40);
		u.addTechnicalAuth(1, type);
		u.addTechnicalAuth(1, type2);
		u.addTechnicalAuth(1, type3);
		u.listTechnicalAuth();
		assertTrue(u.getTechnicalAuth().size()==3);
	}
	//We added 3 technical authorizations in the hashmap, so the size of the hashmap is 3
	

	public void testAddTechnicalAuth_2(){
		Database b = new Database();
		Users u = b.getUser();
		assertTrue(u.getTechnicalAuth().size()==0);
	}
	//We did not add technical authorization in the hashmap, so the size of the hashmap is 0
	
	

	/*
	 * These tests permit to test the function removeTechnicalAuth
	 * This function permits to remove a technical authorization from the hashmap
	 */
	public void testremoveTechnicalAuth_0(){
		Database b = new Database();
		Users u = b.getUser();
		Types_analysis type = new Types_analysis("PCR", 40);
		Types_analysis type2 = new Types_analysis("DNAtest", 40);
		Types_analysis type3 = new Types_analysis("Southernblot", 40);
		u.addTechnicalAuth(1, type);
		u.addTechnicalAuth(1, type2);
		u.addTechnicalAuth(1, type3);
		u.listTechnicalAuth();
		u.removeTechnicalAuth(type);
		u.listTechnicalAuth();
		assertTrue(u.getTechnicalAuth().size()==2);
	}
	//We added 3 technical authorizations, then remove one, so the size of the hashmap is 2
	

	/*
	 * These tests permit to test the function createLogin
	 * This function permits to create automatically a login for e new user with the first letter of the firstName and the lastName
	 */
	public void testCreateLogin(){
		Database b = new Database();
		Users u = b.getUser();
		assertTrue(u.getLogin().contains("jdupont"));
	}
	//The user calls jean dupont, si like expected, the login created is "jdupont"
	
	
}
