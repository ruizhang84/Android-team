package edu.gatech.seclass.tccart;

import java.util.HashMap;

/**
 * Created by surface-fujiawu on 3/18/2016.
 */
public class Customer {

    public static HashMap<String, Customer>
            customerMap = new HashMap<String, Customer>();
    private String name;
    private String email;
    private String id;

    public Customer(String name, String email, String id) {
        this.name = name;
        this.email = email;
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public String getID(){
        return this.id;
    }

    public String getEmail(){
        return this.email;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setEmail(String email){
        this.email = email;
    }
}
