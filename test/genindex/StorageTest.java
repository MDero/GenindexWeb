package genindex;

 

import genindex.Database;
import genindex.Samples;
import junit.framework.TestCase;

public class StorageTest extends TestCase {
	
	// For each test, we search an storage on the database and we use this storage to do the different tests.

	
	// Identifier
	// The aim of this test is to see if the method returns the identifier.
	public void testgetIdentifier_0(){
		Database b = new Database();
		assertTrue(b.storage.getIdentifier() == 0);
	}
	// The test is green: the test is successful.

	// Type
	// The aim of this test is to see if the method returns the right type of the storage.
	public void testgetType_0(){
		Database b = new Database();
		assertTrue(b.storage.getType().contains("freezer"));
	}
	// The test is successful, the storage is a "freezer".
	
	// Size
	// The aim of this test is to see if the method returns the good size.
	public void testgetSize_0(){
		Database b = new Database();
		assertTrue(b.storage.getSize() == 60);
	}
	// The size returning is 60: the test is successful.
	
	// SET
	// Identifier
	// The aim of this test is to see if the method can modify the identifier of a storage.
	public void testsetIdentifier_0(){
		Database b = new Database();
		b.storage.setIdentifier(133);
		assertTrue(b.storage.getIdentifier() == 133);
	}
	// The identifier returning is 133 that's why we can see that the test is successful.
	
	// Type
	// The aim of this test is to see if the method modify the type of the storage.
	public void testsetType_0(){
		Database b = new Database();
		b.storage.setType("refrigerator");
		assertTrue(b.storage.getType().contains("refrigerator"));
	}
	// The text is successful (green color) "freezer" -> "refrigerator"
	
	// Size
	// The aim of this test is to see if the method can modify the size.
	public void testsetSize_0(){
		Database b = new Database();
		b.storage.setSize(50);
		assertTrue(b.storage.getSize() == 50);
	}
	// The size has changed. The test is on success.

	
	
	//add a sample in the list
	// The aim of this test is to see if the method can add a sample into the database.
	public void testAddSample() {
		Database b = new Database();
		Samples s= b.searchSample("1");
		b.storage.addSample(s);
		assertTrue(b.storage.listSample().size()==1);
	}
	// The sample has been added, the test is successful.
	
	//remove a sample in the list
	// The aim of this test is to see if the method can remove a sample from the list.
	public void testRemoveSample() {
		Database b = new Database();
		Samples s= b.searchSample("1");
		b.storage.addSample(s);
		b.storage.removeSample(s);
		assertTrue(b.storage.listSample().size()==0);
	}
	// The test is successful, the sample has been removed.
}
