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
public class Category {

    public static HashMap<Integer, Category> allCategories = new HashMap<>();

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int id;
    private String name;

    protected Category(int id, String name) {
        this.id = id;
        this.name = name;

        Category.allCategories.put(id, this);
    }

    public Category(String name) {
        this.name = name;
    }
    
    

    public static Category getOrCreateCategory(int id, String name) {
        if (Category.allCategories.containsKey(id)) {
            return Category.allCategories.get(id);
        } else {
            return new Category(id, name);
        }
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @Override
    public String toString(){
        return this.name;
    }

}
