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
public class TypeSample {

    private int id;
    private String typeName;

    public TypeSample(String typeName) {
        this.typeName = typeName;
    }

    protected TypeSample(int id, String typeName) {
        this(typeName);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTypeName() {
        return typeName;
    }
}
