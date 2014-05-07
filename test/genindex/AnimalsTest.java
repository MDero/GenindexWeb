package genindex;

 

import kernel.Animals;
import genindex.Database;
import junit.framework.TestCase;

public class AnimalsTest extends TestCase {
	
	// For each test, we search an animal on the database and we use this animal to do the tests.
	
	//GET
	// Specie
	// The aim of this test is to see if the function returns the right identifier.
	public void testgetSpecie_0(){
		Database b = new Database();
		Animals anim = b.searchAnimal("cat");
		assertTrue(anim.getSpecie().contains("cat"));
	}
	// The test is successful (color: green).
	
	// NumberBirthday
	// The aim of this test is to see if the function returns the number birthday. 
	public void testgetNumberBirthday_0(){
		Database b = new Database();
		Animals anim = b.searchAnimal("cat");
		assertTrue(anim.getNumberBirthday().contains("2010"));
	}
	// The number birthday returning is 2010, the test is successful.
	
	//SET
	// Specie
	// The aim of this test is to see if the method changes the specie of the animal.
	public void testsetSpecie_0(){
		Database b = new Database();
		Animals anim = b.searchAnimal("cat");
		anim.setSpecie("dog");
		assertTrue(anim.getSpecie().contains("dog"));
	}
	// The new specie of the animal is "dog", it is good.
	
	// Number Birthday
	// The aim of this test is to see if the function changes the number birthday.
	public void testsetNumberBirthday_0(){
		Database b = new Database();
		Animals anim = b.searchAnimal("dog");
		anim.setNumberBirthday("2011");
		assertTrue(anim.getNumberBirthday().contains("2011"));
	}
	// The number of the birthday is 2011, we can conclude that the test is good.
}
