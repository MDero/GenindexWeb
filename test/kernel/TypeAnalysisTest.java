package kernel;

 


import kernel.Types_analysis;
import junit.framework.TestCase;

public class TypeAnalysisTest extends TestCase {

	/*
	 * first we created an instance of analysis by using the method of database, like that we test 
	 * database in same time
	 */
	Database d = new Database();
	Types_analysis ta = d.searchTypesAnalysis("PCR");
	
	/*
	 * We test if getType get back the good string
	 * We must get back the string gave by the constructor
	 */
	public void testGetType() {
		assertTrue(ta.getType().equals("PCR"));
	}
	/* true
	 * variable got back is the good
	 */
//------------------------------------------------------------------------------------------------
	/*
	 * We test if setType can change the variable Type and if getType get back the good variable
	 * We must get back the new Type we will change
	 */
	public void testSetType() {
		ta.setType("type");
		assertTrue(ta.getType().equals("type"));
	}
	/* true
	 * Variable got back is the variable that we have change
	 */
//------------------------------------------------------------------------------------------------
	/*
	 * We test if getPrice get back the price defines by the constructor
	 * We must get back the integer gave by the constructor
	 */
	public void testGetPrice() {
		assertTrue(ta.getPrice()==40);
	}
	/* true
	 * Variable got back is the variable of the constructor
	 */
//------------------------------------------------------------------------------------------------
	/*
	 * We test if setPrice can change the price var
	 * If we change the price with our method, getPrice must get back the new variable (45)
	 */
	public void testSetPrice() {
		ta.setPrice(45);
		assertTrue(ta.getPrice()==45);
	}
	/* true
	 * Variable got back is the news
	 */

}
