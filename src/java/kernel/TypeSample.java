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
public class TypeSample {
    private static HashMap<Integer,TypeSample> allTypeSample = new HashMap<>();
    public static TypeSample getTypeSample(int id){
        return allTypeSample.get(id);
    }
    
    private int id;
    private String typeName;

    public TypeSample(String typeName) {
        this.typeName = typeName;
    }

    protected TypeSample(int id, String typeName) {
        this(typeName);
        this.id = id;
        
        if (!allTypeSample.containsKey(id))
            allTypeSample.put(id, this);
    }

    public void setId(int id) {
        this.id = id;
        if (!allTypeSample.containsKey(id))
            allTypeSample.put(id, this);
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getId() {
        return id;
    }

    public String getTypeName() {
        return typeName;
    }
}
