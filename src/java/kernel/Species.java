/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kernel;

import java.util.HashMap;

/**
 *
 * @author MrCake
 */
public class Species {
    private static HashMap<Integer,Species> allSpecies = new HashMap<>();
    public static Species getSpecies(int id){
        return allSpecies.get(id);
    }
    
    private int id;
    private Category category;
    private String name;

    protected Species(int id, Category category, String name) {
        this.id = id;
        this.category = category;
        this.name = name;
        
        if (!allSpecies.containsKey(id))
            allSpecies.put(id, this);
    }

    public Species( Category category, String name) {
        this.category = category;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
        if (!allSpecies.containsKey(id))
            allSpecies.put(id, this);
    }

}
