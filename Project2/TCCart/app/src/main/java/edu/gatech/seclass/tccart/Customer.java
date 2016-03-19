package edu.gatech.seclass.tccart;

import java.util.HashMap;

/**
 * A Customer class for TCCart System
 */
public class Customer {

    public static HashMap<String, Customer>
            customerMap = new HashMap<String, Customer>();
    public static final String idCharList = "0123456789abcdef";
    public static final int idLength = 8;
    public static Customer currentCustomer = null;

    private String firstName;
    private String lastName;
    private String email;
    private String id;

    public Customer(String firstName, String lastName, String email, String id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.id = id;
    }

    public String getFullName(){
        return this.firstName +
                " " +
                this.lastName;
    }

    public String getFirstName(){ return this.firstName; }

    public String getLastName() { return this.lastName; }

    public String getID(){
        return this.id;
    }

    public String getEmail(){
        return this.email;
    }

    public void setName(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public static void preloadCustomers(){
        Customer customer1 =
                new Customer("Ralph", "Hapschatt", "ralph@gmail.com", "7c86ffee");
        Customer customer2 =
                new Customer("Betty", "Monro", "betty@gmail.com", "b59441af");
        Customer customer3 =
                new Customer("Everett", "Scott", "everett@gmail.com", "cd0f0e05");
        Customer.customerMap.put(customer1.getID(), customer1);
        Customer.customerMap.put(customer2.getID(), customer2);
        Customer.customerMap.put(customer3.getID(), customer3);
    }
}
