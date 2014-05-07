package genindex;

 

import kernel.Analysis;
import kernel.Animals;
import genindex.Database;
import kernel.Date;
import kernel.Orders;
import kernel.Samples;
import kernel.Types_analysis;
import junit.framework.TestCase;

public class InvoiceTest extends TestCase {
	
	
	/*
	 * This test is to test the function getPrice
	 */
	public void testgetPrice() {
		Database b = new Database();
		Orders o = b.searchOrder(1);
		o.editInvoice();
		assertTrue(o.getInvoice().getPrice()==40);
	}
	//The price returned by the function is the same than the price registered in the database
	
	
	/*
	 * These tests are to check the function printInvoice
	 */
	public void testPrintInvoice_0() { //if there is only one analysis ordered
		Database b = new Database();
		Orders o = b.searchOrder(1);
		o.editInvoice();
		o.getInvoice().printInvoice(b);
	}
	//The result of the function is displayed in the console.
	
	
	public void testPrintInvoice_1() { //if there is two analysis ordered
		Database b = new Database();
		Date d1 = new Date(23,12,10);
		Date d2 = new Date(23,12,11);
		Orders o = b.searchOrder(1);
		
		
		Animals animal = new Animals("dog","2010");
		Samples sample = new Samples("1", "blood", d1, d2, animal);
		o.addSample(sample);
		Types_analysis typeAna = new Types_analysis("RNA test", 60);
		Analysis analysis = new Analysis(1, typeAna, d2);
		sample.addAnalysis(analysis);
		
		o.editInvoice();
		o.getInvoice().printInvoice(b);
	}
	//The result of the function is displayed in the console.

}
