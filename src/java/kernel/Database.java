package kernel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Database {
    /* *****************************************************************************************/

    //Constructor
    Connection connexion;
    
    //Default connection MDERO
    public Database() {
        try {
            connexion = DriverManager.getConnection("jdbc:oracle:thin:@//192.168.24.3:1521/pfpbs", "gp27", "gp27");
            System.out.println("Connection to gphy successful");
        } catch (SQLException ex) {
            Logger.getLogger("ConnectBDD").log(Level.SEVERE, null, ex);
            System.out.println("failed to connect to gphy");
        }
    }
    
    public Database(String url, String user, String password) {
        //DEFINE JDBC objects for connection
        try {
            connexion = DriverManager.getConnection(url, user, password);
            System.out.println("Connection to " + url + " successful for " + user);
        } catch (SQLException ex) {
            Logger.getLogger("ConnectBDD").log(Level.SEVERE, null, ex);
            System.out.println("failed to connect to " + url + "  for " + user);
        }
    }

    /* *****************************************************************************************/
    /* CONVERSION METHODS */
    //FORMAT CONVERTER
    private static String extractString(ResultSet results, String fieldName) {
        String field = "";
        try {
            if (results.getString(fieldName)!=null)
                field = results.getString(fieldName).replaceAll("[ ]*$", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return field;
    }
    private static Integer extractNumber(ResultSet results, String fieldName) {
        Integer field = null;
        try {
            if (results.getString(fieldName)!=null)
                field = Integer.valueOf(results.getString(fieldName).replaceAll("[^0-9]*", ""));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return field;
    }
    private static kernel.Date extractDate(ResultSet results, String datename) {
        Date date = null;
        try {
            //sql date 
            java.sql.Date odate = results.getDate(datename);
            //convert the oracle date to kernel.date
            date = new Date(odate.getDay(), odate.getMonth(), odate.getYear());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return date;
    }
    private static String convertDateToString(kernel.Date date) {
        return date.getYear() + "-" + date.getMonth() + "-" + date.getDay();
    }
    
    //GLOBAL CODE 
    private ResultSet getResultsOfQuery(String query, boolean callNext){
        ResultSet results = null;
        try {
            Statement request = this.connexion.createStatement();

            //request all the objects from the database
            results = request.executeQuery(query);
            
            //count the results if any
            int rowCount = 0;
            while(results.next())
                rowCount++;
            
            //if any : reexecute and return
            if (rowCount>0){
                System.out.println(rowCount+" RESULTS FOUND FOR "+query);
                results=request.executeQuery(query);
                if (callNext)
                    results.next();
            }
            else 
                System.out.println("NO RESULTS FOUND FOR "+query);
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        return results;
    }
    private ResultSet getResultsOfQuery(String query){
        return getResultsOfQuery(query,false);
    }
    private ArrayList<?> generateListOfAll(String table){
         ArrayList<Object> list = new ArrayList<>();
         table = table.toUpperCase();
         
        try {
            Statement request = this.connexion.createStatement();

            //request all the objects from the database
            ResultSet results = this.getResultsOfQuery("SELECT * FROM "+table);
            
            //put the results in the list
            while(results!=null && results.next()){
                list.add(
                        "ADRESS".equals(table)?
                                this.getAdressFromCurrentRow(results):
                        "ORDERS".equals(table) ?
                                this.getOrderFromCurrentRow(results) : 
                        "CUSTOMERS".equals(table)?
                                this.getCustomerFromCurrentRow(results):
                         "SAMPLE".equals(table)?
                                 this.getSampleFromCurrentRow(results):
                         "ANIMALS".equals(table)?
                                 this.getAnimalFromCurrentRow(results):
                         "SPECIES".equals(table)?
                                 this.getSpeciesFromCurrentRow(results):
                         "CATEANIMALS".equals(table)?
                                 this.getCategoryFromCurrentRow(results):
                         "TYPEANAL".equals(table)?
                                 this.getTypeAnalysisFromCurrentRow(results):
                         null
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    private ArrayList<?> generateListOfAllWhere(String table, String where_field, String where_value){
        ArrayList<Object> list = new ArrayList<>();
        table = table.toUpperCase();
         
        try {
            Statement request = this.connexion.createStatement();

            //request all the objects from the database
            ResultSet results = this.getResultsOfQuery("SELECT * FROM "+table+" WHERE "+where_field+" = "+where_value);
            
            //put the results in the list
            while(results!=null&&results.next()){
                list.add(
                        "ADRESS".equals(table)?
                                this.getAdressFromCurrentRow(results):
                        "ORDERS".equals(table) ?
                                this.getOrderFromCurrentRow(results) : 
                        "CUSTOMERS".equals(table)?
                                this.getCustomerFromCurrentRow(results):
                         "SAMPLE".equals(table)?
                                 this.getSampleFromCurrentRow(results):
                         "ANIMALS".equals(table)?
                                 this.getAnimalFromCurrentRow(results):
                         "SPECIES".equals(table)?
                                 this.getSpeciesFromCurrentRow(results):
                         "CATEANIMALS".equals(table)?
                                 this.getCategoryFromCurrentRow(results):
                         "TYPEANAL".equals(table)?
                                 this.getTypeAnalysisFromCurrentRow(results):
                         null
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    
    //PREPARED QUERIES
    private ResultSet getResultSetFromIdQuery(String table, int id) {
        ResultSet results;
        table = table.toUpperCase();
        String request = "SELECT * FROM table WHERE field = id";
        request=request.replace("table", table);
        request= request.replace("field", "ID_"+table);
        request=request.replace("id", ""+id);
        results = this.getResultsOfQuery(request,true);
        return results;
    }

    //FROM RESULTSET	
    private Adress getAdressFromCurrentRow(ResultSet results){
        if (results!=null){
            return  new Adress(
                        extractNumber(results,"ID_ADRESS"), //ADD by MDERO
                        extractNumber(results,"Adress_Number"),
                        extractString(results, "Street"),
                        extractNumber(results,"ZipCode"),
                        extractString(results, "City"),
                        extractString(results, "Country")
                );
        }
        else 
            return null;
    }
    private Customers getCustomerFromCurrentRow(ResultSet results){
        Integer idAdress = extractNumber(results,"ID_adress");
        Adress adress = null;
        if (idAdress!=null)
            adress = this.getAdress(idAdress);
        
        return new Customers(
                    extractNumber(results, "ID_CUSTOMERS"),
                    extractString(results, "FirstName_custo"),
                    extractString(results, "LastName_custo"),
                    adress,
                    extractString(results, "PhoneNumber_custo"),
                    extractString(results, "MAIL_CUSTO"),
                    extractNumber(results,"ID_TYPECUSTOMER")
        );
    }
    private Orders getOrderFromCurrentRow(ResultSet results) {
        return new Orders(
                (int) extractNumber(results, "NumberSamples"),
                extractDate(results, "DateOrder"),
                extractDate(results, "DateDeadline"),
                (int) extractNumber(results, "PriorityLevel"),
                getCustomer(extractNumber(results, "Id_customers"))
        );
    }
    private Animals getAnimalFromCurrentRow(ResultSet results){
        return new Animals(
                getSpecies(extractNumber(results,"Id_species")),
                extractNumber(results,"NumberBirthday"),
                extractString(results,"Animals_Name")
        );
    }
    private Samples getSampleFromCurrentRow(ResultSet results){
        Samples sample = new Samples(
                extractNumber(results,"Id_sample"),
                getOrders(extractNumber(results,"ID_ORDERS")),
                getTypeSample(extractNumber(results,"Id_Typesample")),
                extractDate(results,"DateSampling"),
                extractDate(results,"DateStorage"),
                this.getAnimals(extractNumber(results,"Id_animals"))
        );
        Integer statusId = extractNumber(results,"ID_STATUTSAMPLE");
        if (statusId!=null)
            sample.setStatusId(statusId);
        
        return sample;
    }
    private Species getSpeciesFromCurrentRow(ResultSet results){
        return new Species(
                extractNumber(results,"ID_SPECIES"),
                getCategory(extractNumber(results,"ID_CateAnimals")),
                extractString(results,"species_name")
        );
    }
    private Category getCategoryFromCurrentRow(ResultSet results){
        //avoid creating a new instance of category with the same id everytime this category is called
            //return Category.getOrCreateCategory(extractNumber(results,"Id_category"), extractString(results,"NAME"));
        return new Category (extractNumber(results,"Id_CateAnimals"), extractString(results,"NAME_CATE"));
    }
    private TypeAnalysis getTypeAnalysisFromCurrentRow(ResultSet results){
        return new TypeAnalysis(extractString(results,"TYPEANAL"));
    }
    private TypeSample getTypeSampleFromCurrentRow(ResultSet results){
        return new TypeSample(
                extractNumber(results,"ID_TYPESAMPLE"),
                extractString(results,"TYPESAMPLE")
        );
    }
    
    //FROM IDS
    public Adress getAdress(int id) {
        Adress adress;
        System.out.println("id adresse:"+id);

        ResultSet results = getResultSetFromIdQuery("Adress",id);
        adress = this.getAdressFromCurrentRow(results);
        return adress;
    }
    public Customers getCustomer(int id) {
        Customers customer = null;
        ResultSet results = getResultSetFromIdQuery("CUSTOMERS",id);
        customer = this.getCustomerFromCurrentRow(results);

        return customer;
    }
    public Orders getOrders(int id) {
        Orders order = null;
        ResultSet results = this.getResultSetFromIdQuery("Orders", id);
        order = this.getOrderFromCurrentRow(results);

        return order;
    }
    public Animals getAnimals(int id){
        ResultSet results = this.getResultSetFromIdQuery("Animals", id);
        Animals animal = getAnimalFromCurrentRow(results);
        return animal;
    }
    public Samples getSamples(int id){
        ResultSet results = this.getResultSetFromIdQuery("Samples", id);
        Samples sample = getSampleFromCurrentRow(results);
        return sample;
    }
    public Species getSpecies(int id){
        ResultSet results = this.getResultSetFromIdQuery("Species", id);
        Species species = getSpeciesFromCurrentRow(results);
        return species;
    }
    public Category getCategory(int id ){
        ResultSet results = this.getResultSetFromIdQuery("CateAnimals", id);
        Category category = this.getCategoryFromCurrentRow(results);
        return category;
    }
    public TypeAnalysis getTypeAnalysis(int id){
        ResultSet results = this.getResultSetFromIdQuery("TYPEANAL", id);
        TypeAnalysis ta = this.getTypeAnalysisFromCurrentRow(results);
        return ta;
    }
    public TypeSample getTypeSample(int id){
        ResultSet results = this.getResultSetFromIdQuery("TYPESAMPLE", id);
        return this.getTypeSampleFromCurrentRow(results);
    }
    
    //LISTS ESTABLISHMENT
    public List<Adress> getAdressList(){
        return (List<Adress>) this.generateListOfAll("ADRESS");
    }
    public List<Customers> getCustomerList(){
         ArrayList<Customers> customerList = (ArrayList<Customers>) this.generateListOfAll("CUSTOMERS");
        return customerList;
    }
    public List<Orders> getOrderList() {
        ArrayList<Orders> orderList = (ArrayList<Orders>) this.generateListOfAll("ORDERS");
        return orderList;
    }
    public List<Animals> getAnimalList(){
        return (List<Animals>) this.generateListOfAll("ANIMALS");
    }
    public List<Samples> getSampleList(){
        return (List<Samples>) this.generateListOfAll("SAMPLE");
    }
    public List<Species> getSpeciesList(){
        return (List<Species>) this.generateListOfAll("SPECIES");
    }
    public List<Category> getCategoryList(){
        return (List<Category>) this.generateListOfAll("CATEANIMALS");
    }
    public List<Invoice> getInvoiceList(){
        return (List<Invoice>) this.generateListOfAll("INVOICE");
    }
    public List<TypeAnalysis> getTypeAnalysisList(){
        return  (List<TypeAnalysis>) this.generateListOfAll("TYPEANAL");
    } 
    
    //QUERIES
    //TODO : move to the correct class in the end
    public List<Orders> getOrdersForCustomer(Customers customer){
        return (List<Orders>) this.generateListOfAllWhere("ORDERS", "ID_CUSTOMERS",""+customer.getID());
    }
    public List<Orders> getOrdersForCustomer(int id){
        return (List<Orders>) this.generateListOfAllWhere("ORDERS", "ID_CUSTOMERS",""+id);
    }
    public List<Species> getSpeciesForCategory(Category category){
        ArrayList<Species> species = new ArrayList<>();
        ResultSet results = this.getResultsOfQuery("SELECT * FROM SPECIES natural join CATEANIMALS WHERE ID_CATEANIMALS="+category.getId(), false);
        try {
            while(results.next()){
                species.add(getSpeciesFromCurrentRow(results));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return species;
    }
    public List<Species> getSpeciesForCategory(int idCategory){
        return getSpeciesForCategory(getCategory(idCategory));
    }
    public List<TypeAnalysis> getTypeAnalysisFromSpecies(Species species){
        ArrayList<TypeAnalysis> typesAnalysis = new ArrayList<>();
        ResultSet results = this.getResultsOfQuery("SELECT * FROM SPECIES NATURAL JOIN POSSEDETEST NATURAL JOIN TYPEANAL WHERE ID_SPECIES="+species.getId(), false);
        try {
            while(results.next()){
                typesAnalysis.add(getTypeAnalysisFromCurrentRow(results));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return typesAnalysis;
    }
    public List<Samples> getSamplesToPerformAnalysisOfType(TypeAnalysis ta){
        ArrayList<Samples> samples = new ArrayList<>();
        ResultSet results = this.getResultsOfQuery(
                "SELECT * FROM TYPEANAL "
                        + "inner join CONCERNE on TYPEANAL.ID_TYPEANAL = CONCERNE.ID_TYPEANAL "
                        + "inner join ORDERS on ORDERS.ID_ORDERS = CONCERNE.ID_ORDERS "
                        + "inner join SAMPLE on SAMPLE.ID_ORDERS=ORDERS.ID_ORDERS "
                        + "WHERE TYPEANAL.ID_TYPEANAL="+ta.getId()
                        + "AND (SAMPLE.ID_STATUTSAMPLE=1 or SAMPLE.ID_STATUTSAMPLE=2)"
                ,false);
        try {
            while(results.next()){
                samples.add(getSampleFromCurrentRow(results));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return samples;
    }
    public List<Samples> getSamplesToPerformAnalysisOfType(int idTa){
        return getSamplesToPerformAnalysisOfType(this.getTypeAnalysis(idTa));
    }
    
    /* INSERTION METHODS */
    private void insertIntoTableAllValues(String table, Object... values){
        try {
            Statement s = this.connexion.createStatement();
            
            //create the query
            String insert = "INSERT INTO "+table.toUpperCase()+" values(";
            
            for (int i = 0; i<values.length;i++)
                insert+= values[i]+ (i==values.length-1 ? "" : ",");
            
            insert += ");";
            
            s.executeQuery(insert);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }//OBSOLETE : should not be used (see rules)
    private int insertIntoTableValuesForFields(String table, String fields, Object...values){
        String insert = "";
        PreparedStatement statement = null;
        Statement getIDGenerated = null;
        ResultSet generatedID = null;
        
        int idGene = -1;
        try {
            //Statement s = this.connexion.createStatement();
            
            //create the query
            insert = "INSERT INTO "+table.toUpperCase()+fields+" values(";
            
            for (int i = 0; i<values.length;i++)
                insert+= (values[i] instanceof String ? "'" :"") + 
                        values[i]+
                        (values[i] instanceof String ? "'" :"")+
                        (i==values.length-1 ? "": ","
                        );
            
            insert += ")";
            
            System.out.println(insert);
            
            statement = this.connexion.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                System.out.println("ERROR : NO ROW AFFECTED");
            }
            
            //GET THE LAST ID GENERERATED
            getIDGenerated = this.connexion.createStatement();
            insert = "SELECT MAX(ID_" + table.toUpperCase() + ") FROM "+table.toUpperCase()+"";
            
            generatedID = getIDGenerated.executeQuery(insert);
            while(generatedID.next()) {
                idGene = generatedID.getInt(1);
            }
            return idGene;
            
            //TODO: Find a better way to get back the generated ID...
//            System.out.println("UpdateCount : " + statement.getUpdateCount());
//            generatedKeys = statement.getGeneratedKeys();
//            System.out.println("KEY GENERATED");
//            int c = 0;
//            while (generatedKeys.next()) {
//                c++;
//                //idGenerated = (int)generatedKeys.getInt(1);
//                System.out.println("ID GENERATED : " + generatedKeys.getLong(c));
//            }
////            else {
////                System.out.println("ERROR : NO ID GENERATED");
////            }
        } catch (SQLException ex) {
            System.out.println("ERROR ON : " + insert);
            System.out.println(ex);
            return -1;
        }
    }
    
    //MDERO
    public void insertAdress(Adress adress) {
        int id = this.insertIntoTableValuesForFields("ADRESS", 
                "(ADRESS_NUMBER,STREET, ZIPCODE,CITY, COUNTRY)",
                //values
                adress.getNumber(),
                adress.getStreet(), 
                adress.getZipCode(),
                adress.getCity(),
                adress.getCountry());
        adress.setID(id);//adress.setID();
    }
    public void insertOrder(Orders order) {
        int id = this.insertIntoTableValuesForFields("ORDERS", 
                "(ID_CUSTOMERS, PRIORITYLEVEL,NUMBERSAMPLES, DATEDEADLINE, DATEORDER, PAID, RESULTSEND, REPORT_ORDERS)",
                //order.getId(),
                order.getCustomerID(),
                order.getPriorityLevel(),
                order.getSamples().size(),
                convertDateToString(order.getDeadLine()),
                convertDateToString(order.getDateOrder()),
                order.getPaid(),
                order.getResultSend(),
                order.getReport()
        );
        order.setIdOrder(id);

    }
    public void insertCustomer(Customers c) {
        int id = this.insertIntoTableValuesForFields("CUSTOMERS", 
            "(ID_TYPECUSTOMER, ID_ADRESS, FIRSTNAME_CUSTO, LASTNAME_CUSTO, PHONENUMBER_CUSTO, MAIL_CUSTO, CELLPHONE_CUSTO)",
            c.getTypeCusto(),
            c.getAdress()!=null ? 
            c.getAdress().getIdAdress() : null,
            c.getFirstName(),
            c.getLastName(),
            c.getPhoneNumber(),
            c.getEmail(),
            c.getPhone());
        c.setID(id);
    }
    public void insertAnimals(Animals animal){
        int id = this.insertIntoTableValuesForFields("ANIMALS", 
                "(ID_SPECIES,NUMBERBIRTHDAY,ANIMALS_NAME)",
                //values
                animal.getSpecies().getId(),
                animal.getNumberBirthday(),
                animal.getName()
        );
        animal.setId(id);
    }
    public void insertCategory(Category category){
        int id = this.insertIntoTableValuesForFields("CATEANIMALS", 
                "(CATE_NAME)",
                category.getName()
        );
        category.setId(id);
    }
    public void insertSpecies(Species species){
        int id = this.insertIntoTableValuesForFields("SPECIES", 
                "(ID_CATEANIMALS,SPECIES_NAME)",
                //values
                species.getCategory().getId(),
                species.getName()
        );
        species.setId(id);
    }
    public void insertSample(Samples sample){
        int id = this.insertIntoTableValuesForFields("SAMPLE",
                "(ID_TYPESAMPLE ,ID_ORDERS,ID_ANIMALS,ID_STATUTSAMPLE,ANALYSED,DATESAMPLING,DATESTORAGE)",
                sample.getType().getId(),
                sample.getOrder().getId(),
                sample.getAnimal().getId(),
                sample.getStatusId(),
                sample.analyzed,
                convertDateToString(sample.getDateSampling()),
                convertDateToString(sample.getDateStorage())
        );
        sample.setId(id);
    }
    
    /* DELETION METHODS */
    public void delCustomer(int id) {
        Customers customer = null;
        try {
            Statement request = this.connexion.createStatement();
            request.execute("DELETE FROM CUSTOMERS WHERE Id_CUSTOMERS=" + id);
            System.out.println("DEL CUSTOMER : " + id);
        } catch (SQLException e) {
            System.out.println("ERROR IN delCustomer : " + e);
        }
    }
    
    public void delAdress(int id) {
        Customers customer = null;
        try {
            Statement request = this.connexion.createStatement();
            request.execute("DELETE FROM ADRESS WHERE ID_ADRESS=" + id);
            System.out.println("DEL ADRESS : " + id);
        } catch (SQLException e) {
            System.out.println("ERROR IN delAdress : " + e);

        }
    }
}

