
import java.util.Comparator;

public class Contacts extends Object {
   
    /**
     *  Contacts fields
     */
     private String firstName;
     private String lastName;
     private String address;
     private String city;
     private String state;
     private String zip;
     private String phone;
     /** 
      *  Constructor for Contacts.
      */
     public Contacts(){
    	 
     }
     /**
      * initialize a new Contacts entry
      * @param firstName contacts' first name
      * @param lastName contacts' last name
      * @param address contacts' address
      * @param city contacts' city address
      * @param state contacts' state address
      * @param zip contacts' zip code
      * @param phone contacts' phone number
      * */
      public void initEntry( String firstName, String lastName, String address, String city, String state, String zip, String phone )
      {
          this.firstName = firstName;
          this.lastName  = lastName;
          this.address   = address;
          this.city      = city;
          this.state     = state;
          this.zip       = zip;
          this.phone     = phone ;
      }
      /** 
       *  @return the Contacts's first name
       */
       public String getFirstName() {
           return firstName;
       }
      
      /** 
       *  @return the Contacts's last name
       */
       public String getLastName() {
           return lastName;
       }
      
      /** 
       *  @return the Contacts's address
       */
       public String getAddress() {
          return address;
       }
      
      /** 
       *  @return the Contacts's city address
       */
       public String getCity() {
          return city;
       }
      
      /** 
       *  @return the Contacts's state address
       */
       public String getState() {
          return state;
       }
      
      /** 
       *  @return the Contacts's zip code
       */
       public String getZip() {
          return zip;
       }
      
      /** 
       *  @return the Contacts's phone number
       */
       public String getPhone() {
          return phone;
       }
 
      /** 
       *  Set the Contacts's first name
       */
       public void setFirstName(String firstName) {
           this.firstName = firstName;
       }
     
      /** 
       *  Set the Contacts's last name
       */
       public void setLastName(String lastName) {
          this.lastName = lastName;
       }
 
      /** 
       *  Set the Contacts's address
       */
       public void setAddress(String address) {
           this.address = address;
       }
 
      /** 
       *  Set the Contacts's city
       */
       public void setCity(String city) {
          this.city = city;
       }
 
      /** 
       *  Set the Contacts's state
       */
       public void setState(String state) {
          this.state = state;
       }
 
      /** 
       *  Set the Contacts's zip code
       */
       public void setZip(String zip) {
          this.zip = zip;
       }
 
      /**  
       *  Set the Contacts's phone number
       */
       public void setPhone(String phone) {
          this.phone = phone;
       }
       /**
        * torString method to display all info about the Contacts
        * */
       public String toString() {
    	   return  "Name: " + getLastName() + ", " + getFirstName() +
   			       "Address: " + getAddress() + ", " + getCity() + "," + getState() + ", " + getZip() +
   			       "Phone Number: " + getPhone();
       }        
        /** Comparator for comparing two contacts by name
         */
        public static class CompareByName implements Comparator<Contacts>
        {
            public int compare(Contacts contact1, Contacts contact2)
            {
                int result = contact1.lastName.compareTo(contact2.lastName);
                if (result == 0)
                    return contact1.firstName.compareTo(contact2.firstName);
                else
                   return result;
            }       
        }
        
        /** Comparator for comparing two contacts by order of zip code
         */
        public static class CompareByZip implements Comparator<Contacts>
        {
            public int compare(Contacts contact1, Contacts contact2)
            {
                int result = contact1.zip.compareTo(contact2.zip);
                if (result == 0)
                    return new CompareByName().compare(contact1, contact2);
                else
                    return result;
            }       
        }
}
