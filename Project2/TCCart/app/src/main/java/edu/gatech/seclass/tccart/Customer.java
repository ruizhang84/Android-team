package edu.gatech.seclass.tccart;

import java.util.HashMap;

/**
 * A Customer class for TCCart System
 */
public class Customer {

    public static final String idCharList = "0123456789abcdef";
    public static final int idLength = 8;
    public static Customer currentCustomer = null;

    private String firstName;
    private String lastName;
    private String email;
    private String id;

    public Customer(String id, String firstName, String lastName, String email) {
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

}
