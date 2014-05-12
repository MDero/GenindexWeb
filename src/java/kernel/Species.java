/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kernel;

/**
 *
 * @author MrCake
 */
public class Species {
    private int id; 
    private Category category;
    private String name;
    
    public Species(int id,Category category, String name){
        this.id=id;
        this.category=category;
        this.name=name;
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
}
