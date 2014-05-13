package kernel;

import java.util.ArrayList;

/**
 * This class "samples" is about the different samples of the analyses. We can
 * manage the samples with the methods: modifySample(), printSample(). We have
 * different accessors and we can return many informations with the methods
 * "get...".
 */
public class Samples {

    /**
     * The identifiant of the sample. It is automatically incremented. This
     * identifiant is unique.
     */
    private int id;
    private int statusId = 0;//TODO : default value;
    /**
     * It is the type of the sample (blood, DNA...)
     */
    private TypeSample type;

    /**
     * number of analyses for this sample
     */
    private int analysisCount = 0;

    /**
     * If TRUE the sample is already analyzed, if not it is FALSE.
     */
    protected boolean analyzed;

    private Animals animal;
    private Orders order;

    public int getAnalysisCount() {
        return analysisCount;
    }

    public void setAnalysisCount(int analysisCount) {
        this.analysisCount = analysisCount;
    }

    public boolean isAnalyzed() {
        return analyzed;
    }

    public void setAnalyzed(boolean analyzed) {
        this.analyzed = analyzed;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public Date getDateSampling() {
        return dateSampling;
    }

    public void setDateSampling(Date dateSampling) {
        this.dateSampling = dateSampling;
    }

    public ArrayList<Analysis> getAnalyses() {
        return analyses;
    }

    public void setAnalyses(ArrayList<Analysis> analyses) {
        this.analyses = analyses;
    }

    public Date getDateStorage() {
        return dateStorage;
    }

    public void setDateStorage(Date dateStorage) {
        this.dateStorage = dateStorage;
    }
    private Date dateSampling;

    private ArrayList<Analysis> analyses;

    private Date dateStorage;

    /**
     * Sample is a constructor. It permits to create a new sample with the
     * different fields: - the identifier (integer). - the type of sample
     * (string). - the date of creation of this sample (date). - the date of the
     * storage of this sample (date).
     */
    protected Samples(int Identifier, TypeSample Type_sample, Date D_sampling, Date D_storage, Animals anim) {
        // Bouml preserved body begin 0002A282
        this.id = Identifier;
        this.type = Type_sample;
        this.dateSampling = D_sampling;
        this.dateStorage = D_storage;
        this.animal = anim;
        this.analyzed = false; // default: the sample is not analyzed
        this.analyses = new ArrayList<Analysis>();
        // Bouml preserved body end 0002A282
    }
    public Samples(TypeSample Type_sample, Date D_sampling, Date D_storage, Animals anim) {
        // Bouml preserved body begin 0002A282
        this.type = Type_sample;
        this.dateSampling = D_sampling;
        this.dateStorage = D_storage;
        this.animal = anim;
        this.analyzed = false; // default: the sample is not analyzed
        this.analyses = new ArrayList<Analysis>();
        // Bouml preserved body end 0002A282
    }
    

    /**
     * This method permits to display a sample on the screen.
     */
    public void printSample(Samples sam) {
        // Bouml preserved body begin 0002A382
        System.out.println("Identifier: " + sam.id);
        System.out.println("Type of sample: " + sam.type);
        System.out.println("Date of the creation of the sample: " + sam.dateSampling);
        System.out.println("Date of the storage of the sample: " + sam.dateStorage);
        System.out.println("Analyzed? : " + sam.analyzed);
        // Bouml preserved body end 0002A382
    }

    /**
     * This method has been created to modify the type of the sample.
     */
    public void setType(TypeSample type) {
        // Bouml preserved body begin 0002A402
        this.type = type;
        // Bouml preserved body end 0002A402
    }

    /**
     * This method has been created to modify the date of the creation of the
     * sample.
     */
    public void setDatesampling(Date d) {
        // Bouml preserved body begin 0002A482
        this.dateSampling = d;
        // Bouml preserved body end 0002A482
    }

    /**
     * This method has been created to modify the date when the sample was
     * storaged.
     */
    public void setDatestorage(Date d2) {
        // Bouml preserved body begin 0002A502
        this.dateStorage = d2;
        // Bouml preserved body end 0002A502
    }

    /**
     * This method has been created to modify the animal of the sample.
     */
    public void setAnimal(Animals anim) {
        // Bouml preserved body begin 0002A582
        this.animal = anim;
        // Bouml preserved body end 0002A582
    }

    /**
     * This "get" permits to return the identifier of the animal.
     */
    public int getId() {
        // Bouml preserved body begin 0002A602
        return id;
        // Bouml preserved body end 0002A602
    }

    /**
     * This method return the type of the sample.
     */
    public TypeSample getType() {
        // Bouml preserved body begin 0002A682
        return this.type;
        // Bouml preserved body end 0002A682
    }

    /**
     * The method getDatesampling return the date when the sample was created.
     */
    public Date getDatesampling() {
        // Bouml preserved body begin 0002A702
        return this.dateSampling;
        // Bouml preserved body end 0002A702
    }

    /**
     * This method return the date of the storage of the sample.
     */
    public Date getDatestorage() {
        // Bouml preserved body begin 0002A782
        return this.dateStorage;
        // Bouml preserved body end 0002A782
    }

    /**
     * This method has been created to modify the boolean: FALSE to TRUE when
     * the sample is analyzed. !! WARNING !! : TRUE -> FALSE is not possible.
     */
    public void setAnalyzed() {
        // Bouml preserved body begin 0002C202
        if (analyzed == false) {
            analyzed = true;
        }
        // Bouml preserved body end 0002C202
    }

    public Analysis getAnalysis() {
        // Bouml preserved body begin 00037982
        for (int i = 0; i < analyses.size(); i++) {
            if (i == (analyses.size() - 1)) {
                return analyses.get(i);
            } else {
                analyses.get(i).generateReport();
            }
        }
        return null;
        // Bouml preserved body end 00037982
    }

    public Animals getAnimal() {
        // Bouml preserved body begin 00037A02
        return this.animal;
        // Bouml preserved body end 00037A02
    }

    /**
     * add an analyses to the sample, 1 to 3 if the analyses is not validate
     */
    public void addAnalysis(Analysis ana) {
        // Bouml preserved body begin 000233C5
        if (analysisCount == 3) {
            System.out.println("3 analysis were done, please sent new sample. ");
        } else {
            analyses.add(analysisCount, ana);
            analysisCount = analysisCount + 1;
        }
        // Bouml preserved body end 000233C5
    }

    /**
     * get the number of analyses for this sample
     */
    public int getCount() {
        // Bouml preserved body begin 00023445
        return analysisCount;
        // Bouml preserved body end 00023445
    }

    public int getStatusId() {
        return this.statusId;
    }

    public void setId(int id) {
        this.id = id;
    }

}
