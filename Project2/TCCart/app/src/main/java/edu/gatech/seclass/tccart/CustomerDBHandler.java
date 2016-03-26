package edu.gatech.seclass.tccart;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;
import java.text.DateFormat;

/**
 * The SQLite database handler for Customer information
 */
public class CustomerDBHandler {

    DBHelper dbh;

    public CustomerDBHandler(Context context) {
        this.dbh = DBHelper.getInstance(context);
    }

    public void addCustomer(Customer customer) {
        SQLiteDatabase db = this.dbh.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(this.dbh.CUST_ID, customer.getID());
        values.put(this.dbh.CUST_FIRST_NAME, customer.getFirstName());
        values.put(this.dbh.CUST_LAST_NAME, customer.getLastName());
        values.put(this.dbh.CUST_EMAIL, customer.getEmail());
        values.put(this.dbh.CUST_REWARDS, customer.getRewards());

        Date rewardsDate = customer.getRewardDate();
        if (rewardsDate == null) {
            values.put(this.dbh.CUST_REWARD_DATE, (new Date(0)).getTime());
        }
        else{
            values.put(this.dbh.CUST_REWARD_DATE, rewardsDate.getTime());
        }

        values.put(this.dbh.CUST_SPENDING_YTD, customer.getSpendingYTD());
        values.put(this.dbh.CUST_SPENDING_YEAR, customer.getSpendingYear());
        values.put(this.dbh.CUST_VIP_YEARS, customer.getVipYearsString());

        db.insert(this.dbh.TABLE_CUSTOMER, null, values);
        db.close();
    }

    public Customer getCustomer(String id) {
        SQLiteDatabase db = this.dbh.getReadableDatabase();

        String[] projection = new String[]{this.dbh.CUST_ID,
                this.dbh.CUST_FIRST_NAME, this.dbh.CUST_LAST_NAME,
                this.dbh.CUST_EMAIL, this.dbh.CUST_REWARDS, this.dbh.CUST_REWARD_DATE,
                this.dbh.CUST_SPENDING_YTD, this.dbh.CUST_SPENDING_YEAR,
                this.dbh.CUST_VIP_YEARS};
        String selection = this.dbh.CUST_ID + "=?";
        String[] selectionArgument = new String[]{id};
        Cursor cursor = db.query(this.dbh.TABLE_CUSTOMER, projection, selection,
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

    public int updateCustomer(Customer customer) {
        SQLiteDatabase db = this.dbh.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(this.dbh.CUST_ID, customer.getID());
        values.put(this.dbh.CUST_FIRST_NAME, customer.getFirstName());
        values.put(this.dbh.CUST_LAST_NAME, customer.getLastName());
        values.put(this.dbh.CUST_EMAIL, customer.getEmail());
        values.put(this.dbh.CUST_REWARDS, customer.getRewards());

        Date rewardsDate = customer.getRewardDate();
        if (rewardsDate == null) {
            values.put(this.dbh.CUST_REWARD_DATE, (new Date(0)).getTime());
        }
        else{
            values.put(this.dbh.CUST_REWARD_DATE, rewardsDate.getTime());
        }

        values.put(this.dbh.CUST_SPENDING_YTD, customer.getSpendingYTD());
        values.put(this.dbh.CUST_SPENDING_YEAR, customer.getSpendingYear());
        values.put(this.dbh.CUST_VIP_YEARS, customer.getVipYearsString());

        return db.update(this.dbh.TABLE_CUSTOMER, values, this.dbh.CUST_ID + " = ?",
                new String[]{customer.getID()});
    }

    public void deleteCustomer(Customer customer) {
        SQLiteDatabase db = this.dbh.getWritableDatabase();
        db.delete(this.dbh.TABLE_CUSTOMER, this.dbh.CUST_ID + " = ?",
                new String[]{customer.getID()});
        db.close();
    }

}
