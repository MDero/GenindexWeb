package kernel;


 

public class Animals {
  /**
   * The animal belongs to a specie.
   */

  private Species species;
  private String name;

    public String getName() {
        return name;
    }
  private int numberBirthday;

  public Animals(Species specie, int numberBirthday) {
    // Bouml preserved body begin 00020D83
	  this.species = specie;
	  this.numberBirthday = numberBirthday;
    // Bouml preserved body end 00020D83
  }

  public Species getSpecies() {
    // Bouml preserved body begin 00022C83
	  return this.species;
    // Bouml preserved body end 00022C83
  }

  public int getNumberBirthday() {
    // Bouml preserved body begin 00022D03
	  return this.numberBirthday;
    // Bouml preserved body end 00022D03
  }

  public void setSpecies(Species new_species) {
    // Bouml preserved body begin 00022D83
	  this.species = new_species;
    // Bouml preserved body end 00022D83
  }

  public void setNumberBirthday(int new_NumberBirthday) {
    // Bouml preserved body begin 00022E03
	  this.numberBirthday = new_NumberBirthday;
    // Bouml preserved body end 00022E03
  }

}
