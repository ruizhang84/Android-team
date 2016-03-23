package edu.gatech.seclass.tccart;

import java.util.Date;

/**
 * Created by zhang on 3/22/2016.
 */
public class Transaction {
    private long transactionID;
    private long customerID;
    private Date year;
    private Double totalAmount;
    private Double vipDiscount;
    private Double creditApplied;

    //getter and setter
    public void setTransactionID(long transactionID){
        this.transactionID = transactionID;
    }

    public void setCustomerID(long customerID){
        this.customerID = customerID;
    }

    public void setYear(Date year){
        this.year = year;
    }

    public void setTotalAmount(Double totalAmount){
        this.totalAmount = totalAmount;
    }

    public void setVipDiscount(Double vipDiscount){
        this.vipDiscount = vipDiscount;
    }

    public void setCreditApplied(Double creditApplied){
        this.creditApplied = creditApplied;
    }

    public long getTransactionID(){ return this.transactionID; }
    public long getCustomerID(){ return this.customerID; }
    public Date getYear(){ return this.year; }
    public Double getTotalAmount() { return this.totalAmount; }
    public Double getVipDiscount() { return this.vipDiscount; }
    public Double getCreditApplied() { return this.creditApplied; }

}
