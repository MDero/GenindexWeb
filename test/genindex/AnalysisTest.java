package genindex;

 

import kernel.Analysis;
import genindex.Database;
import kernel.Types_analysis;
import kernel.Users;
import junit.framework.TestCase;

public class AnalysisTest extends TestCase {
	
	/*
	 * first we created an instance of analysis by using the method of database, like that we test 
	 * database in same time
	 */
	Database d = new Database();
	Types_analysis ta = d.searchTypesAnalysis("PCR");
	Analysis a = d.searchAnalysisID(1);
	Users u = d.getUser();
	  
	/*
	 * We test the generation of a report without first and second reading, and validation
	 * We should have a not complete report
	 */
	public void testGenerateReport_0() {
		a.generateReport();
	}
	/* 
	 * The report show that information is missing
	 */
//------------------------------------------------------------------------------------------------	
	/*
	 * The same test with more information
	 * We must have a complete report
	 */
	public void testGenerateReport_1() {
		a.firstReading(u);
		a.secondReading(u,true,"validé");
		a.generateReport();
	}
	/*
	 * The report is complete and show all the information
	 */
//------------------------------------------------------------------------------------------------
	/*
	 * We want test the method firstReading register correctly the user who have read analysis
	 * We should get the user form the object
	 */
	public void testFirstReading() {
		a.firstReading(u);
		assertTrue(a.getFirstReader()==u);
	}
	/* true
	 * the user registered is the same that the get back user
	 */
//------------------------------------------------------------------------------------------------
	/*
	 * We want test the method secondReading register correctly the user who have read analysis,
	 * the report and if the analysis is validate or not, if the firstReading is not done
	 * Without firstReading, secondReading can't be done
	 */
	public void testSecondReading_0() {
		a.secondReading(u,true,"validé");
		assertFalse(a.getFirstReader()==u&&a.getValidate()==true&&a.getReport().equals("validé"));
	}
	/* false
	 * The user is not registered, validate is not changed and the report not contain the good 
	 * string because the first reading is not done
	 */
//------------------------------------------------------------------------------------------------
	/*
	 * We want test the method secondReading register correctly the user who have read analysis,
	 * the report and if the analysis is validate or not with the first reading done
	 * If it work, a user was registered, the boolean validate was changed and a report was created
	 */
	public void testSecondReading_1() {
		a.firstReading(u);
		a.secondReading(u,true,"validé");
		assertTrue(a.getFirstReader()==u&&a.getValidate()==true&&a.getReport().equals("validé"));
	}
	/* true
	 * The user is registered, validate is changed and the report contain the good string
	 */
//------------------------------------------------------------------------------------------------
	/*
	 * We want to know if getID work
	 * We must get back the ID defines by the constructor
	 */
	public void testGetID() {
		assertTrue(a.getID()==1);
	}
	/* true
	 * The id got back by our method is the same
	 */
//------------------------------------------------------------------------------------------------
	/*
	 * We test if setID can change the ID var
	 * If we change ID with our method, getID must get back the new variable (2)
	 */
	public void testSetID() {
		a.setID(2);
		assertTrue(a.getID()==2);
	}
	/* true
	 * Variable got back is the news
	 */
//------------------------------------------------------------------------------------------------
	/*
	 * We will test getTypeAnalysis to know if the method get back the good types of analysis 
	 * defines in the constructor
	 * We have search in dataBase the type of analysis used in the constructor so it will be
	 * the same
	 */
	public void testGetTypeAnalysis() {
		assertTrue(a.getTypeAnalysis()==ta);
	}
	/* true
	 * The types analysis got back by our method is the same
	 */

}
