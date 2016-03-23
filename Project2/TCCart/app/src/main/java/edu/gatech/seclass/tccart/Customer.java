package edu.gatech.seclass.tccart;

import java.util.Date;
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
    private double rewards;
    private Date rewardDate;
    private Date vipDate;

    public Customer(String id, String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.id = id;
        this.rewards = 0;
        this.rewardDate = null;
        this.vipDate = null;
    }

    public Date getRewardDate(){
        return this.rewardDate;
    }

    public double getRewards(){
        return this.rewards;
    }

    public Date getVipDate(){
        return this.vipDate;
    }

    public void setRewardDate(Date d){
        this.rewardDate = d;
    }

    public void setRewards(double r){
        this.rewards = r;
    }

    public void  setVipDate(Date d){
        this.vipDate = d;
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


/*

import java.util.Date;
import java.util.Calendar;

public class HelloWorld{

     public static void main(String []args){
       
       Date date = new Date(2121212333121L);
       System.out.println(date.toString());
       
       Calendar cal = Calendar.getInstance();
       cal.setTime(date);
       System.out.println(cal.get(Calendar.YEAR));
       System.out.println(cal.get(Calendar.MONTH));
       System.out.println(cal.get(Calendar.DAY_OF_MONTH));
       
     }
}


*/
