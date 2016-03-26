package edu.gatech.seclass.tccart;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

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
    private int spendingYear;
    private List<Integer> vipYears;

    public Customer(String id, String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.id = id;
        this.rewards = 0;
        this.rewardDate = null;
        this.vipYears = null;
        this.spendingYTD = 0;
        Calendar today = Calendar.getInstance();
        this.spendingYear = today.get(Calendar.YEAR);
    }

    public boolean isVIP(){
        updateSpending();
        Calendar today = Calendar.getInstance();
        Integer currentYear = today.get(Calendar.YEAR);
        if (this.vipYears == null)
            return false;
        return this.vipYears.contains(currentYear);
    }

    public boolean isVIPNextYear(){
        updateSpending();
        Calendar today = Calendar.getInstance();
        Integer nextYear = today.get(Calendar.YEAR)+1;
        if (this.vipYears == null)
            return false;
        return this.vipYears.contains(nextYear);
    }

    public List<Integer> getVipYearsList(){
        updateSpending();
        return this.vipYears;
    }

    public String getVipYearsString() {
        updateSpending();
        String output = "";
        if (this.vipYears == null)
            return output;
        for (int i : this.vipYears) {
            String s = String.format(Locale.US, "%1$04d", i);
            output += s + "#";
        }
        return output;
    }

    public void setVipYears(String input) {
        updateSpending();
        this.vipYears = new ArrayList<>();
        if (input == null || input.length() <= 0)
            return;
        String[] strArray = input.split("#");
        if (strArray.length <= 0)
            return;
        for (String s : strArray){
            this.vipYears.add(Integer.parseInt(s));
        }
    }

    public void addVipYear(int y){
        updateSpending();
        if (this.vipYears == null)
            this.vipYears = new ArrayList<Integer>();
        this.vipYears.add(y);
    }

    private void updateSpending(){
        Calendar today = Calendar.getInstance();
        int thisYear = today.get(Calendar.YEAR);
        if (thisYear != this.spendingYear){
            this.spendingYear = thisYear;
            this.spendingYTD = 0;
        }
    }

    public double getSpendingYTD(){
        updateSpending();
        return this.spendingYTD;
    }

    public int getSpendingYear(){
        updateSpending();
        return this.spendingYear;
    }

    public void setSpendingYTD(double s){
        this.spendingYTD = s;
        updateSpending();
    }

    public void setSpendingYear(int y) {
        this.spendingYear = y;
        updateSpending();
    }

    public Date getRewardDate(){
        updateSpending();
        return this.rewardDate;
    }

    public double getRewards(){
        updateSpending();
        return this.rewards;
    }

    public double getEffectiveRewards(){
        updateSpending();
        Date today = new Date();
        if (this.rewardDate == null) {
            this.rewards = 0;
            return 0;
        }
        long dt = today.getTime() - this.rewardDate.getTime();
        if (dt >= reward_expiration_time){
            this.rewards = 0;
            return 0;
        }
        else {
            return this.rewards;
        }
    }

    public void setRewardDate(Date d){
        updateSpending();
        this.rewardDate = d;
    }

    public void setRewards(double r){
        updateSpending();
        this.rewards = r;
    }

    public String getFullName(){
        updateSpending();
        return this.firstName +
                " " +
                this.lastName;
    }

    public String getFirstName(){
        updateSpending();
        return this.firstName;
    }

    public String getLastName() {
        updateSpending();
        return this.lastName;
    }

    public String getID(){
        updateSpending();
        return this.id;
    }

    public String getEmail(){
        updateSpending();
        return this.email;
    }

    public void setName(String firstName, String lastName){
        updateSpending();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setEmail(String email){
        updateSpending();
        this.email = email;
    }

}
