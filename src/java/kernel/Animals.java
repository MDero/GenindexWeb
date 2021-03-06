package kernel;

import java.util.HashMap;

public class Animals {
    private static HashMap<Integer,Animals> allAnimals = new HashMap<>();
    public static Animals getAnimals(int id){
        return allAnimals.get(id);
    }
    
    
    /**
     * The animal belongs to a specie.
     */
    private int id;
    private Species species;
    private String name;

    public String getName() {
        return name;
    }
    private int numberBirthday;

    public Animals(Species specie, int numberBirthday, String name) {
        // Bouml preserved body begin 00020D83
        this.species = specie;
        this.numberBirthday = numberBirthday;
        this.name = name;
        // Bouml preserved body end 00020D83
    }

    protected Animals(int id, Species species, int numberBirthday, String name) {
        this(species, numberBirthday, name);
        this.id = id;
        if (!allAnimals.containsKey(id))
            allAnimals.put(id, this);
    }

    public int getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
        if (!allAnimals.containsKey(id))
            allAnimals.put(id, this);
    }

}
