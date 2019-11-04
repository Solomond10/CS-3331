package bank;

public abstract class Person {
 
    String firstName, lastName, dateOfBirth, address, phoneNumber;
    
    public Person(String nFirstName, String nLastName, String nDateOfBirth,
                  String nAddress, String nPhoneNumber){
    
        firstName = nFirstName;
        lastName = nLastName;
        dateOfBirth = nDateOfBirth;
        address = nAddress; 
        phoneNumber = nPhoneNumber;
    }
    
    public String GetFirstName(){
    
        return firstName;
    }
    
    
    public String GetLastName(){
    
        return lastName;
    }
    
    
    public String GetDateOfBirth(){
    
        return dateOfBirth;
    }
    
    
    public String GetAddress(){
    
        return address;
    }
    
    
    public String GetPhoneNumber(){
    
        return phoneNumber;
    }
    
}


