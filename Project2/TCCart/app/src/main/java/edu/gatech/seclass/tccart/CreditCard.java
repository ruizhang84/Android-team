package edu.gatech.seclass.tccart;

import java.util.Date;

/**
 * A CreditCard class for TCCart System
 */
public class CreditCard {

    public String firstName;
    public String lastName;
    public String ccNumber;
    public Date expirationDate;
    public String securityCode;


    public CreditCard(String f, String l, String c, Date e, String s) {
        this.firstName = f;
        this.lastName = l;
        this.ccNumber = c;
        this.expirationDate = e;
        this.securityCode = s;
    }

}
