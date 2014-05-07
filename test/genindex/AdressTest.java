package genindex; 

import kernel.Adress;
import kernel.Adress;
import junit.framework.TestCase;

public class AdressTest extends TestCase {
	// We create an adress to test the methods of the classes.
	  Adress ad = new Adress (12, "Rue du Centre", 22000, "Saint-Brieuc", "FRANCE");

	// GET
	// Number of the street	
	// The aim of this test is to see if the function returns the correct identifier. 
	public void testgetNumber_0(){
		assertTrue(ad.getNumber()==12);
	}
	// The test is successful, the method returns 12.
	
	// Street	
	// The aim of this test is to see if the function returns the correct street of the adress. 
	public void testgetStreet_0(){
		assertTrue(ad.getStreet().contains("Rue du Centre"));
	}
	// The test is successful the street returning is the right.
	
	// Zip code	
	// The aim of this test is to see if the function returns the correct zip code of the adress. 
	public void testgetZipCode_0(){
		assertTrue(ad.getZipCode() == (22000));
	}
	// The zip code returning is 22000 the test is good.
	
	// City	
	// The aim of this test is to see if the function returns the right city.
	public void testgetCity_0(){
		assertTrue(ad.getCity().contains("Saint-Brieuc"));
	}
	// The test is successful, the city that is returned is "Saint-Brieuc".
	
	// Country
	public void testgetCountry_0(){
		assertTrue(ad.getCountry().contains("FRANCE"));
	}
	
	
	// SET
	
	// Number of the street
	// The aim of this test is to see if the function changes the identifier. 
	public void testsetNumber_0(){
		ad.setNumber(15);
		assertTrue(ad.getNumber() == (15));
	}
	// The modification is good, the test is successful.
	
	// Street
	// The aim of this test is to see if the function changes the street of the address.
	public void testsetStreet_0(){
		ad.setStreet("Main Street");
		assertTrue(ad.getStreet().contains("Main Street"));
	}
	// The new street is "Main Street" that's why we can say that the test is successful.
	
	// ZipCode
	// The aim of this test is to see if the function changes the zip code. 
	public void testsetZipCode_0(){
		ad.setZipCode(86000);
		assertTrue(ad.getZipCode() == (86000));
	}
	// The test is successful, the new zipcode is 86000.
	
	// City
	// The aim of this test is to see if the function changes the city of the address.
	public void testsetCity_0(){
		ad.setCity("Poitiers");
		assertTrue(ad.getCity().contains("Poitiers"));
	}
	// The test is green, the test is successful.

	
	// Country
	// The aim of this test is to see if the function changes the country.
	public void testsetCountry_0(){
		ad.setCountry("Angleterre");
		assertTrue(ad.getCountry().contains("Angleterre"));
	}
	// The test is successful, the city is right (Poitiers).
	
}