package edu.gatech.seclass.tccart;

import java.util.Date;

/**
 * A Transaction class for TCCart System
 */
public class Transaction {

    private Customer customer;
    private Date date;
    private double totalAmount;
    private double vipDiscount;
    private double rewardsApplied;
    private String description;

    public Transaction(Customer c, Date d, double t, double v, double r, String description){
        this.customer = c;
        this.date = d;
        this.totalAmount = t;
        this.vipDiscount = v;
        this.rewardsApplied = r;
        this.description = description;
    }

    public Transaction(Customer c, double t, double v, double r, String description){
        this.customer = c;
        this.date = new Date();
        this.totalAmount = t;
        this.vipDiscount = v;
        this.rewardsApplied = r;
        this.description = description;
    }

    public Customer getCustomer(){ return this.customer; }

    public Date getDate(){ return this.date; }

    public Double getTotalAmount() { return this.totalAmount; }

    public Double getVipDiscount() { return this.vipDiscount; }

    public Double getRewardsApplied() { return this.rewardsApplied; }

    public String getDescription() { return this.description; }

    public String getTransactionLog() {
        String s = "";
        s += "Date of Transaction:  " + this.getDate() + "\n";
        s += "Description:  " + this.getDescription() + "\n";
        s += "Total Amount:  " + this.getTotalAmount() + "\n";
        s += "VIP Discount:  " + this.getVipDiscount() + "\n";
        s += "Rewards Applied:  " + this.getRewardsApplied() + "\n";
        s += "\n";
        return s;
    }

}
