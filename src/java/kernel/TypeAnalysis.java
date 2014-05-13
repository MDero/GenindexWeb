package kernel;

import java.util.HashMap;

/**
 * This class has been created to manage the different types of analysis.
 */
public class TypeAnalysis {

    private String type;

    /**
     * The price of one type of analysis for one sample.
     */
    private HashMap<TypeSample, Double> priceList = new HashMap<>();

    /**
     * constructor of types
     */
    public TypeAnalysis(String name) {
        // Bouml preserved body begin 0001FE45
        this.type = name;
        // Bouml preserved body end 0001FE45
    }

    /**
     * accessor to name
     */
    public String getType() {
        // Bouml preserved body begin 0001FEC5
        return this.type;
        // Bouml preserved body end 0001FEC5
    }

    public void setType(String name) {
        // Bouml preserved body begin 0001FF45
        this.type = name;
        // Bouml preserved body end 0001FF45
    }

    public double getPriceFor(TypeSample ts) {
        return this.priceList.get(ts);
    }
}
