package kernel;

 

import kernel.Analysis;
import kernel.Animals;
import kernel.Date;
import kernel.Samples;
import kernel.Types_analysis;
import junit.framework.TestCase;
public class SamplesTest extends TestCase {
	// For each test, we search a sample on the database and we use this sample to do the tests.

// GET	
	// Identifier
	// The aim of this test is to see if the method returns the right identifier. 
		public void testgetId_0(){
			Database b = new Database();
			Samples s = b.searchSample("1");
			System.out.println(s);
			assertTrue(s.getId().contains("1"));
		}
	// The test is successful, the id of the sample on the database is 1.

	// Type
	// The aim of this test is to see if the method returns the right type of sample. 
		public void testgetType_0(){
			Database b = new Database();
			Samples s = b.searchSample("1");
			assertTrue(s.getType().contains("blood"));
		}
	// The test is successful, the type returning is the right. 


	// Date sampling
	// The aim of this test is to see if the method returns the good date of sampling.
		public void testgetDatesampling_0(){
			Database b = new Database();
			Samples s = b.searchSample("1");
			Date d1 = new Date(23,12,10);
			assertTrue(s.getDatesampling().compareDate(d1));
		}
	// 23/12/2010 is the date of sampling, the test is successful.
	
	// Date storage
	// The aim of this test is to see if the method returns the good date of the storage.
		public void testgetDatestorage_0(){
			Database b = new Database();
			Samples s = b.searchSample("1");
			Date d2 = new Date(23,12,11);
			assertTrue(s.getDatestorage().compareDate(d2));
		}
	// The date returning is 23/12/2011, that's why we can say that the test is successful.

	// Animal
	// The aim of this test is to see if the method returns the right animal.

		public void testgetAnimal_0(){
			Database b = new Database();
			Samples s = b.searchSample("1");
			Animals animal = new Animals("cat","2010");
			assertTrue(s.getAnimal().getSpecie()=="cat");
		}
	// The test is successful (test in green color).

	// Analysis 
	// The aim of this test is to see if the method returns the analysis on the database.
		public void testgetAnalysis_0(){
			Database b = new Database();
			Samples s = b.searchSample("1");
			Date d = new Date(12,12,2010);
			Types_analysis typana = new Types_analysis("PCR", 123);
			Analysis ana2 = new Analysis(1, typana, d);
			assertTrue(s.getAnalyses().getID()==1);
		}
	// The method returns the good analysis for the sample: the test is successful.

		
// SET
	// Type	
		// The aim of this test is to see if the method can change the type of the sample.
	public void testSetType_0(){
		Database b = new Database();
		Samples s = b.searchSample("1");
		s.setType("DNA");
		assertTrue(s.getType().contains("DNA"));
	}
	// The type of the sample has changed. This test is successful.
	
	// Date of the sampling
	// The aim of this test is to see if the method can change the date of the sampling.
	public void testSetDatesampling_0(){
		Database b = new Database();
		Samples s = b.searchSample("1");
		Date d2= new Date (12,10,2010);
		s.setDatesampling(d2);
		assertTrue(s.getDatesampling().equals(d2));
	}
	// The test is successful (green color).
	
	// Date of the storage
	// The aim of this test is to see if the method can change the date of the storage.
	public void testSetDatestorage_0(){
		Database b = new Database();
		Samples s = b.searchSample("1");
		Date d3= new Date (18,10,2010);
		s.setDatestorage(d3);
		assertTrue(s.getDatestorage().equals(d3));
	}
	// The date of the sample is new and is correct: the test is good.
	
	// Animal
	// The aim of this test is to see if the method can change the animal of a sample.
	public void testSetanimal_0(){
		Database b = new Database();
		Samples s = b.searchSample("1");
		Animals anim2 = new Animals("chien","5");
		s.setAnimal(anim2);
		assertTrue(s.getAnimal().equals(anim2));
	}
	// The test of this method is successful, we have a new animal for this sample.
	
	
	// Analyzed
	// The aim of this test is to see if the method can change the state of the sample.
	public void testSetAnalyzed_0(){
		Database b = new Database();
		Samples s = b.searchSample("1");
		s.setAnalyzed();
		assertTrue(s.analyzed == (true));
	}
	// The new state of the sample is "true", it is right: the test is successful.
	
	// Analysis
	// The aim of this test is to see if the method can change the analysis of the sample.
	public void testSetanalysis_0(){
		Database b = new Database();
		Samples s = b.searchSample("1");
		
		Date d2 = new Date(14,12,2010);
		Types_analysis typana2 = new Types_analysis("PCR", 23);
		Analysis ana3 = new Analysis(9, typana2, d2);
		
		s.addAnalysis(ana3);
		assertTrue(s.getAnalyses().getID()==9);
	}	
	//The test is successfull, the new identifier of the analysis is 9.
}
