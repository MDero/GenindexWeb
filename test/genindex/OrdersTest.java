package genindex;

 

import genindex.Database;
import kernel.Orders;
import kernel.Samples;
import junit.framework.TestCase;

public class OrdersTest extends TestCase {
	
	/*
	 *  This test is to check the function printOrder
	 */
	public void testPrintOrder(){
		Database b = new Database();
		Orders o = b.searchOrder(1);
		o.printOrder();
	}
	//The result of the function is displayed in the console.
	
	
	/*
	 * This test permit to test the function getPaid
	 */
	public void testGetPaid() {
		Database b = new Database();
		Orders o = b.searchOrder(1);
		assertTrue(o.getPaid()==false);
	}
	//The function return false, like expected
	
	
	/*
	 * This test permit to test the function setPriorityLevel
	 */
	public void testSetPriorityLevel() {
		Database b = new Database();
		Orders o = b.searchOrder(1);
		o.setPriorityLevel(2);
		assertTrue(o.getPriorityLevel()==2);
	}
	//The PriorityLevel has been changed, like expected
	
	
	/*
	 * This test permit to test the function setPaid
	 */
	public void testSetPaid() {
		Database b = new Database();
		Orders o = b.searchOrder(1);
		o.setPaid(true);
		assertTrue(o.getPaid()==true);
	}
	//The variable paid has been changed, like expected
	
	
	/*
	 * This test permit to test the function setResultsSend
	 */
	public void testSetResultsSend() {
		Database b = new Database();
		Orders o = b.searchOrder(1);
		o.setResultsSend(true);
		assertTrue(o.getResultSend()==true);
	}
	//The resultsSend has been changed, like expected
	
	
	/*
	 * These tests permit to test the function addSample
	 * This function permits to add a sample in the list of sample of an order
	 */
	public void testaddSample_0() {
		Database b = new Database();
		Orders o = b.searchOrder(1);
		Samples s = b.searchSample("1");
		o.addSample(s);
		o.listSamples();
		assertTrue(o.getSamples().size()==2);
	}
	//In the order of the database, there is already a sample registered, so when we add one, the size of the list is 2
	
	public void testaddSample_1() {
		Database b = new Database();
		Orders o = b.searchOrder(1);
		Samples s = b.searchSample("1");
		o.addSample(s);
		s = b.searchSample("2");
		o.addSample(s);
		s = b.searchSample("3");
		o.addSample(s);      
		assertTrue(o.getSamples().size()==4);
	}
	//In the order of the database, there is already a sample registered, so when we add three samples, the size of the list is 4
	
	
	/*
	 * These tests permit to test the function payAnalyse
	 * This function permits to change the boolean paid from FALSE to TRUE
	 */
	public void testPayAnalyse() {
		Database b = new Database();
		Orders o = b.searchOrder(1);
		o.payAnalyse();
		assertTrue(o.getPaid()==true);
	}
	//The variable paid is now TRUE, like expected
	
	
	/*
	 * These tests permit to test the function editInvoice
	 * This function permits to create a new invoice for the order
	 */
	public void testEditInvoice() {
		Database b = new Database();
		Orders o = b.searchOrder(1);
		o.editInvoice();
		assertTrue(o.getInvoice().getPrice()==40);
	}
	/*
	 * In order to check if the invoice has been created, we check if we can get the price of the new invoice
	 * The price return is the good one, so the invoice has been created
	 */
	

}
