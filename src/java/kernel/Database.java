package kernel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import oracle.jdbc.OraclePreparedStatement;

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
            field = results.getString(fieldName).replaceAll("[ ]*$", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return field;
    }
    private static Integer extractNumber(ResultSet results, String fieldName) {
        Integer field = null;
        try {
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
    private ArrayList<?> generateListOfAll(String table){
         ArrayList<Object> list = new ArrayList<>();
         table = table.toUpperCase();
         
        try {
            Statement request = this.connexion.createStatement();

            //request all the orders from the database
            request.execute("SELECT * FROM "+table);

            ResultSet results = request.getResultSet();

            //put the results in the list
           while(results.next()){
               list.add(
                       "ADRESS".equals(table)?
                               this.getAdressFromCurrentRow(results):
                       "ORDERS".equals(table) ?
                               this.getOrderFromCurrentRow(results) : 
                       "CUSTOMERS".equals(table)?
                               this.getCustomerFromCurrentRow(results):
                        "SAMPLES".equals(table)?
                                this.getSampleFromCurrentRow(results):
                        "ANIMALS".equals(table)?
                                this.getAnimalFromCurrentRow(results):
                        "SPECIES".equals(table)?
                                this.getSpeciesFromCurrentRow(results):
                        "CATEGORY".equals(table)?
                                this.getCategoryFromCurrentRow(results):
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
        ResultSet results = null;
        table = table.toUpperCase();
        try {
            //OraclePreparedStatement ps = (OraclePreparedStatement) this.connexion.prepareStatement("SELECT * FROM :table WHERE ID_:table = :id");
            //ps.setStringAtName("table", table);
            //ps.setIntAtName("id", id);
            
            String request = "SELECT * FROM table WHERE field = id";
            request=request.replace("table", table);
            request= request.replace("field", "ID_"+table);
            request=request.replace("id", ""+id);
            
            Statement statement = this.connexion.createStatement();
            //System.out.println(request);            
            results = statement.executeQuery(request);
            
            //results = ps.executeQuery();
        } catch (SQLException e) {
            System.out.println(table+" "+id);
            e.printStackTrace();
        }
        return results;
    }

    //FROM RESULTSET	
    private Adress getAdressFromCurrentRow(ResultSet results){
        return  new Adress(
                    extractNumber(results,"ID_ADRESS"), //ADD by MDERO
                    extractNumber(results,"Number"),
                    extractString(results, "Street"),
                    extractNumber(results,"ZipCode"),
                    extractString(results, "City"),
                    extractString(results, "Country")
            );
    }
    private Customers getCustomerFromCurrentRow(ResultSet results){
        Adress adress = this.getAdress(extractNumber(results,"ID_adress"));
        //protected Customers(int ID, String first, String last, Adress adress, String mail, String phone, int typeCusto) {
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
                extractString(results,"Name")
        );
    }
    private Samples getSampleFromCurrentRow(ResultSet results){
        return new Samples(
                ""+extractString(results,"Id_sample"),
                new TypeSample(extractString(results,"Id_TypeSample")),
                extractDate(results,"DateSampling"),
                extractDate(results,"DateStorage"),
                this.getAnimals(extractNumber(results,"Id_animals"))
        );
    }
    private Species getSpeciesFromCurrentRow(ResultSet results){
        return new Species(
                extractNumber(results,"ID_SPECIES"),
                getCategoryFromCurrentRow(results),
                extractString(results,"species")
        );
    }
    private Category getCategoryFromCurrentRow(ResultSet results){
        //avoid creating a new instance of category with the same id everytime this category is called
        return Category.getOrCreateCategory(extractNumber(results,"Id_category"), extractString(results,"Name"));
    }
    
    //FROM IDS
    public Adress getAdress(int id) {
        Adress adress = null;

        //request all the orders from the database
        try {
            ResultSet results = getResultSetFromIdQuery("Adress",id);
            results.next();

            adress = this.getAdressFromCurrentRow(results);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return adress;
    }
    public Customers getCustomer(int id) {
        Customers customer = null;
        try {            
            ResultSet results = getResultSetFromIdQuery("CUSTOMERS",id);
            results.next();

            customer = this.getCustomerFromCurrentRow(results);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customer;
    }
    public Orders getOrders(int id) {
        Orders order = null;
        try {
            ResultSet results = this.getResultSetFromIdQuery("Orders", id);
            results.next();

            order = this.getOrderFromCurrentRow(results);
        } catch (SQLException e) {
            e.printStackTrace();
        }

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
        ResultSet results = this.getResultSetFromIdQuery("Category", id);
        Category category = this.getCategoryFromCurrentRow(results);
        return category;
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
        return (List<Samples>) this.generateListOfAll("SAMPLES");
    }
    public List<Species> getSpeciesList(){
        return (List<Species>) this.generateListOfAll("SPECIES");
    }
    public List<Category> getCategoryList(){
        return (List<Category>) this.generateListOfAll("CATEGORY");
    }
    public List<Invoice> getInvoiceList(){
        return (List<Invoice>) this.generateListOfAll("INVOICE");
    }
    public List<TypeAnalysis> getTypeAnalysisList(){
        return  (List<TypeAnalysis>) this.generateListOfAll("TYPEANAL");
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
    private void insertIntoTableValuesForFields(String table, String fields, Object...values){
        try {
            Statement s = this.connexion.createStatement();
            
            //create the query
            String insert = "INSERT INTO "+table.toUpperCase()+fields+" values(";
            
            for (int i = 0; i<values.length;i++)
                insert+= (values[i] instanceof String ? "'" :"") + 
                        values[i]
                        + (i==values.length-1 ? 
                       values[i] instanceof String ? "'" :""  
                        : ",");
            
            insert += ")";
            System.out.println(insert);
            s.executeQuery(insert);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    //MDERO
    public void insertAdress(Adress adress) {
        this.insertIntoTableValuesForFields("ADRESS", 
                "(NUMBER, STREET, ZIPCODE,CITY, COUNTRY)",
                //values
                adress.getNumber(),
                adress.getStreet(), 
                adress.getZipCode(),
                adress.getCity(),
                adress.getCountry());
    }
    public void insertOrder(Orders order) {
        String[] fields = {};
        this.insertIntoTableValuesForFields("ORDERS", 
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

    }
    public void insertCustomer(Customers c) {
    this.insertIntoTableValuesForFields("CUSTOMERS", 
            "(ID_TYPECUSTOMER, ID_ADRESS, FIRSTNAME_CUSTO, LASTNAME_CUSTO, PHONENUMBER_CUSTO, MAIL_CUSTO, CELLPHONE_CUSTO)",
            c.getTypeCusto(),
            c.getAdress().getIdAdress(),
            c.getFirstName(),
            c.getLastName(),
            c.getPhoneNumber(),
            c.getEmail(),
            c.getPhone());
    }
    public void insertAnimals(Animals animal){
        this.insertIntoTableValuesForFields("ANIMALS", 
                "(ID_SPECIES,NUMBERBIRTHDAY,NAME)",
                //values
                animal.getSpecies().getId(),
                animal.getNumberBirthday(),
                animal.getName()
        );
    }
    public void insertCategory(Category category){
        this.insertIntoTableValuesForFields("CATEGORY", 
                "(NAME)",
                category.getName()
        );
    }
    public void insertSpecies(Species species){
        this.insertIntoTableValuesForFields("SPECIES", 
                "(ID_CATEGORY,NAME)",
                //values
                species.getCategory().getId(),
                species.getName()
        );
    }
    public void insertSample(Samples sample){
        this.insertIntoTableValuesForFields("SAMPLE",
                "(ID_TYPESAMPLE ,ID_ORDERS,ID_ANIMALS,ID_STATUTSAMPLE,ANALYSED,DATESAMPLING,DATESTORAGE)",
                sample.getType().getId(),
                sample.getOrder().getId(),
                sample.getAnimal().getId(),
                sample.getStatusId(),
                sample.analyzed,
                convertDateToString(sample.getDateSampling()),
                convertDateToString(sample.getDateStorage())
        );
    }
    
    /* DELETION METHODS */
    public Customers delCustomer(int id) {
        Customers customer = null;
        try {
            Statement request = this.connexion.createStatement();
            request.execute("DELETE FROM CUSTOMERS WHERE Id_customers=" + id);
            System.out.println("DEL CUSTOMER : " + request.toString());
            ResultSet results = request.getResultSet();
            results.next();

            customer = this.getCustomerFromCurrentRow(results);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customer;
    }
    
    public Customers delAdress(int id) {
        Customers customer = null;
        try {
            Statement request = this.connexion.createStatement();
            request.execute("DELETE FROM ADRESS WHERE ID_ADRESS=" + id);
            
            ResultSet results = request.getResultSet();
            results.next();

            customer = this.getCustomerFromCurrentRow(results);
        } catch (SQLException e) {
            System.out.println("delAdress");
            e.printStackTrace();
        }

        return customer;
    }
}

