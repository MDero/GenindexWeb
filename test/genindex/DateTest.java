package genindex;

 

import genindex.Date;
import junit.framework.TestCase;

public class DateTest extends TestCase {

/*
 * These functions permit to test the function check_dates
 * The function check_dates return TRUE if the date in parameter is not older, or it is the same, FALSE if not.
 */

	
	public void testCheckDates_0() { //if the dates are the same
		Date d1 = new Date(25,12,10);
		Date d2 = new Date(25, 12, 10);
		assertTrue(d1.check_dates(d2)==true); 
	}
	//Like expected, the function return true
	
	public void testCheckDates_1() { //if the year of the dates are different
		Date d1 = new Date(25,12,10);
		Date d2 = new Date(25, 12, 11);
		assertTrue(d1.check_dates(d2)==true);
	}
	//The first date is older than the second, so like expected the function return true
	
	public void testCheckDates_2() { //if the month of the dates are different
		Date d1 = new Date(25,11,10);
		Date d2 = new Date(25, 12, 10);
		assertTrue(d1.check_dates(d2)==true);
	}
	//The first date is older than the second, so like expected the function return true
	
	public void testCheckDates_6() {//if the day of the dates are different
		Date d1 = new Date(25,12,10);
		Date d2 = new Date(26, 12, 10);
		assertTrue(d1.check_dates(d2)==true);
	}
	//The first date is older than the second, so like expected the function return true
	
	
	
	public void testCheckDates_3() { //if the day of the dates are different, and the date in the parameter is older
		Date d1 = new Date(25,11,10);
		Date d2 = new Date(24, 11, 10);
		assertTrue(d1.check_dates(d2)==false);
	}
	//The second date is older than the first, so like expected the function return false
	
	public void testCheckDates_4() {//if the month of the dates are different, and the date in the parameter is older
		Date d1 = new Date(25,11,10);
		Date d2 = new Date(25, 10, 10);
		assertTrue(d1.check_dates(d2)==false);
	}
	//Like expected, the function return false
	
	public void testCheckDates_5() {//if the year of the dates are different, and the date in the parameter is older
		Date d1 = new Date(25,11,10);
		Date d2 = new Date(25, 11, 9);
		assertTrue(d1.check_dates(d2)==false);
	}
	//Like expected, the function return false
	
	
	/*
	 * These tests permit to test the function compareDate
	 * The function compareDate returns TRUE if the dates are the same
	 */
	
	public void testCompareDate_0(){ //if the dates are different
		Date d1 = new Date(25,12,10);
		Date d2 = new Date(26, 12, 10);
		assertTrue(d1.compareDate(d2)==false);
	}
	//The dates are different, so like expected, the function returns FALSE
	
	public void testCompareDate_1(){ //if the dates are the same
		Date d1 = new Date(25,12,10);
		Date d2 = new Date(25, 12, 10);
		assertTrue(d1.compareDate(d2)==true);
	}
	//The dates are the same, so like expected, the function returns TRUE
	
	
	/*
	 * This test permits to check the function printDate
	 * This function permits to print the date in the format day/month/year
	 */
	public void testPrintDate() {
		Date d1 = new Date(25,12,10);
		d1.printDate();
	}
}
