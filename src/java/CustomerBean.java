/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MrCake
 */
public class CustomerBean {
   private String firstName = null, lastName = null;

   public CustomerBean() {
   }
   public String getFirstName(){
      return firstName;
   }       
   public String getLastName(){
       return lastName;
   }
   public void setFirstName(String firstName){
      this.firstName = firstName;
   }      
   public void setLastName(String lastName){
       this.lastName = lastName;
   }
}

