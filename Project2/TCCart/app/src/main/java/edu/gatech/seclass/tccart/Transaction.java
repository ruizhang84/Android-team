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

    public Transaction(Customer c, Date d, double t, double v, double r){
        this.customer = c;
        this.date = d;
        this.totalAmount = t;
        this.vipDiscount = v;
        this.rewardsApplied = r;
    }

    public Transaction(Customer c,double t, double v, double r){
        this.customer = c;
        this.date = new Date();
        this.totalAmount = t;
        this.vipDiscount = v;
        this.rewardsApplied = r;
    }

    public Customer getCustomer(){ return this.customer; }

    public Date getDate(){ return this.date; }

    public Double getTotalAmount() { return this.totalAmount; }

    public Double getVipDiscount() { return this.vipDiscount; }

    public Double getRewardsApplied() { return this.rewardsApplied; }

}
