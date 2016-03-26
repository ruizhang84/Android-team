package edu.gatech.seclass.tccart;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *  The SQLite database handler for Transaction information
 */

//test test, planning to restructure this on a new branch...
public class TransactionDBHandler {
    DBHelper dbh;

    public TransactionDBHandler(Context context) {
        this.dbh = DBHelper.getInstance(context);
    }

    public void addTransaction(Transaction transaction) {
        SQLiteDatabase db = this.dbh.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(this.dbh.TRANS_CUSTOMER_ID, transaction.getCustomer().getID());

        Date date = transaction.getDate();
        if (date == null){
            values.put(this.dbh.TRANS_TRANSACTION_TIME, (new Date(0)).getTime());
        }
        else{
            values.put(this.dbh.TRANS_TRANSACTION_TIME, date.getTime());
        }

        values.put(this.dbh.TRANS_PRICE_BEFORE, transaction.getTotalAmount());
        values.put(this.dbh.TRANS_DISCOUNTS, transaction.getVipDiscount());
        values.put(this.dbh.TRANS_REWARDS_USED, transaction.getRewardsApplied());
        values.put(this.dbh.TRANS_DESCRIPTION , transaction.getDescription());

        db.insert(this.dbh.TABLE_TRANSACTION, null, values);
        db.close();
    }

    public Customer getCustomer(String id) {
        SQLiteDatabase db = this.dbh.getReadableDatabase();

        String[] projection = new String[]{this.dbh.CUST_ID,
                this.dbh.CUST_FIRST_NAME,
                this.dbh.CUST_LAST_NAME,
                this.dbh.CUST_EMAIL,
                this.dbh.CUST_REWARDS,
                this.dbh.CUST_REWARD_DATE,
                this.dbh.CUST_SPENDING_YTD,
                this.dbh.CUST_SPENDING_YEAR,
                this.dbh.CUST_VIP_YEARS};
        String selection = this.dbh.CUST_ID + "=?";
        String[] selectionArgument = new String[]{id};
        Cursor cursor = db.query(this.dbh.TABLE_CUSTOMER,
                projection, selection,
                selectionArgument, null, null, null);

        if (cursor.getCount() == 0){
            return null;
        }
        else {
            cursor.moveToFirst();
        }
        String new_id = cursor.getString(0);
        String firstName = cursor.getString(1);
        String lastName = cursor.getString(2);
        String email = cursor.getString(3);
        double rewards = cursor.getDouble(4);
        Date rewardsDate = new Date(cursor.getLong(5));
        double spendingYTD = cursor.getDouble(6);
        int spendingYear = cursor.getInt(7);
        String vipYears = cursor.getString(8);
        Customer customer = new Customer(new_id, firstName, lastName, email);
        customer.setRewards(rewards);
        customer.setRewardDate(rewardsDate);
        customer.setSpendingYTD(spendingYTD);
        customer.setSpendingYear(spendingYear);
        customer.setVipYears(vipYears);

        cursor.close();
        return customer;
    }

    public List<Transaction> getTransaction(Customer customer) {

        if (customer == null)
            return null;

        List<Transaction> list = new ArrayList<>();

        String id = customer.getID();
        if (getCustomer(id) == null)
            return null;

        SQLiteDatabase db = this.dbh.getReadableDatabase();

        String[] projection = new String[]{
                this.dbh.TRANS_TRANSACTION_TIME, this.dbh.TRANS_PRICE_BEFORE,
                this.dbh.TRANS_DISCOUNTS, this.dbh.TRANS_REWARDS_USED,
                this.dbh.TRANS_DESCRIPTION};
        String selection = this.dbh.TRANS_CUSTOMER_ID + "=?";
        String[] selectionArgument = new String[]{id};
        Cursor cursor = db.query(this.dbh.TABLE_TRANSACTION, projection, selection,
                selectionArgument, null, null, null);

        if (cursor.getCount() == 0){
            return null;
        }
        cursor.moveToFirst();
        do {
            Date date = new Date(cursor.getLong(0));
            double priceTotal = cursor.getDouble(1);
            double discounts = cursor.getDouble(2);
            double rewards_used = cursor.getDouble(3);
            String description = cursor.getString(4);
            Transaction transaction =
                    new Transaction(customer, date, priceTotal, discounts, rewards_used, description);
            list.add(transaction);
        }  while(cursor.moveToNext());
        cursor.close();
        db.close();
        return list;
    }


}
