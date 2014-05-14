package kernel;

import java.util.HashMap;


 

/**
 * This class manages the customers.
 */
public class Customers {
    /**
     * HASHMAP to avoid having too many queries
     */
    private static HashMap<Integer,Customers> customersMap = new HashMap<>();
    
    
    
  private int ID;

  /**
   * The first name of the customer.
   */
  private String firstName;

  /**
   * The last name of the customer.
   */
  private String lastName;

  /**
   * The phone number of the customer.
   */
  private String phoneNumber;

  private String email;

  private Adress adress;
  
  //Mdero
  private int typeCusto = 0;
  
  //MUST USE THIS ONE IN INTERFACE
  //WITHOUT ADRESS
    public Customers(String first, String last, String phone, String mail, int typeCusto) {
	  firstName = first;
	  lastName = last;
	  phoneNumber = phone;
          this.email = mail;
          this.typeCusto = typeCusto;
    }
    //WITH ADRESS
    public Customers(String first, String last,Adress adress, String phone, String mail, int typeCusto){
        this(first,last,phone,mail,typeCusto);
        this.adress=adress;
    }
    
  //MDERO
  //TO GET FROM DATABASE
    protected Customers(int ID, String first, String last, Adress adress, String mail, String phone, int typeCusto) {
    // Bouml preserved body begin 00040A82
	  this.adress = adress;
	  this.firstName = first;
	  this.lastName = last;
	  this.phoneNumber = phone;
          this.email = mail;
	  this.ID = ID;
          //Mdero
          this.typeCusto = typeCusto;
     // Bouml preserved body end 00040A82
          if (!customersMap.containsKey(ID))
          customersMap.put(ID, this);
  }
  

  


  /**
   * to modify attribute name
   */
  public void setName(String first, String last) {
    // Bouml preserved body begin 00040F02
	  firstName = first;
	  lastName = last;
    // Bouml preserved body end 00040F02
  }

  /**
   * return last name of customer
   */
  public String getLastName() {
    // Bouml preserved body begin 00040B82
	  System.out.println(lastName);
	  return lastName;

    // Bouml preserved body end 00040B82
  }

  /**
   * accessor to attribute name
   */
  public String getFirstName() {
    // Bouml preserved body begin 00040B02
	  System.out.println(firstName);
	  return firstName;
    // Bouml preserved body end 00040B02
  }

  /**
   * change attribute phone number
   */
  public void setPhone(String phone) {
    // Bouml preserved body begin 00041002
	  this.phoneNumber = phone;
    // Bouml preserved body end 00041002
  }

  /**
   * accessor to attribute phone
   */
  public String getPhone() {
    // Bouml preserved body begin 00040C82
	  return phoneNumber;
    // Bouml preserved body end 00040C82
  }

  /**
   * accessor to attribute ID
   */
  public int getID() {
    // Bouml preserved body begin 00040D02
	  System.out.println(ID);
	  return ID;
    // Bouml preserved body end 00040D02
  }

  /**
   * accessor to attribute Email
   */
  public String getEmail() {
    // Bouml preserved body begin 00040D82
	  System.out.println(email);
	  return email;
    // Bouml preserved body end 00040D82
  }

  /**
   * change attribute ID
   */
  public void setID(int ID) {
    // Bouml preserved body begin 00041082
	  this.ID=ID;
          if (!customersMap.containsKey(ID))
          customersMap.put(ID, this);
    // Bouml preserved body end 00041082
  }

  /**
   * change Email
   */
  public void setEmail(String mail) {
    // Bouml preserved body begin 00041102
	  email = mail;
    // Bouml preserved body end 00041102
  }

  /**
   * to modify attribute adress
   */
  public void setAdress(Adress adr) {
    // Bouml preserved body begin 00040F82
	  this.adress = adr;
    // Bouml preserved body end 00040F82
  }

  /**
   * accessor to attribute adress
   */
  public Adress getAdress() {
    // Bouml preserved body begin 00040C02
	  return adress;
    // Bouml preserved body end 00040C02
  }

  
  //MDero
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getTypeCusto() {
        return typeCusto;
    }
    
    //VIncent
    public static Customers getCustomer(int id){
        return customersMap.get(id);
    }
}
