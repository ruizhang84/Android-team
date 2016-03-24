package edu.gatech.seclass.tccart;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * A Customer class for TCCart System
 */
public class Customer {

    public static final String idCharList = "0123456789abcdef"; // customer id character space
    public static final int idLength = 8; // customer id length
    public static Customer currentCustomer = null;
    public static long reward_expiration_time = 2678000000L; // 30 days in millisecond

    private String firstName;
    private String lastName;
    private String email;
    private String id;
    private double rewards;
    private Date rewardDate;
    private double spendingYTD;
    private int vipYear;

    public Customer(String id, String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.id = id;
        this.rewards = 0;
        this.rewardDate = null;
        this.vipYear = -1;
        this.spendingYTD = 0;
    }

    public boolean isVIP(){
        Calendar today = Calendar.getInstance();
        return today.get(Calendar.YEAR) == this.vipYear;
    }

    public int getVipYear(){
        return this.vipYear;
    }

    public void setVipYear(int y){
        this.vipYear = y;
    }

    public double getSpendingYTD(){
        return this.spendingYTD;
    }

    public void setSpendingYTD(double s){
        this.spendingYTD = s;
    }

    public Date getRewardDate(){
        return this.rewardDate;
    }

    public double getRewards(){
        return this.rewards;
    }

    public double getEffectiveRewards(){
        Date today = new Date();
        long dt = today.getTime() - this.rewardDate.getTime();
        if (dt >= reward_expiration_time){
            return 0;
        }
        else {
            return this.rewards;
        }

    }

    public void setRewardDate(Date d){
        this.rewardDate = d;
    }

    public void setRewards(double r){
        this.rewards = r;
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
