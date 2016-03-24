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

public class TransactionDBHandler extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "TCCart";

    public static final String TABLE_TRANSACTION = "TransactionInfo";
    public static final String CUSTOMER_ID = "customer_id";
    public static final String TRANSACTION_TIME = "transaction_time";
    public static final String PRICE_BEFORE = "price_before";
    public static final String DISCOUNTS = "discountS";
    public static final String REWARDS_USED = "rewards_used";
    public static final String DESCRIPTION = "description";

    public TransactionDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_TRANSACTION + "("
                + CUSTOMER_ID + " VARCHAR(8),"
                + TRANSACTION_TIME + " BIGINT,"
                + PRICE_BEFORE + " DOUBLE,"
                + DISCOUNTS + " DOUBLE,"
                + REWARDS_USED + " DOUBLE,"
                + DESCRIPTION + "TEXT"
                + ")";
        db.execSQL(CREATE_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSACTION);
        onCreate(db);
    }

    public void addTransaction(Transaction transaction) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CUSTOMER_ID, transaction.getCustomer().getID());

        Date date = transaction.getDate();
        if (date == null){
            values.put(TRANSACTION_TIME, (new Date(0)).getTime());
        }
        else{
            values.put(TRANSACTION_TIME, date.getTime());
        }

        values.put(PRICE_BEFORE, transaction.getTotalAmount());
        values.put(DISCOUNTS, transaction.getVipDiscount());
        values.put(REWARDS_USED, transaction.getRewardsApplied());
        values.put(DESCRIPTION , transaction.getDescription());

        db.insert(TABLE_TRANSACTION, null, values);
        db.close();
    }

    public Customer getCustomer(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = new String[]{CustomerDBHandler.getID(),
                CustomerDBHandler.getFIRST_NAME(),
                CustomerDBHandler.getLAST_NAME(),
                CustomerDBHandler.getEMAIL(),
                CustomerDBHandler.getREWARDS(),
                CustomerDBHandler.getREWARD_DATE(),
                CustomerDBHandler.getSPENDING_YTD(),
                CustomerDBHandler.getVIP_YEAR()};
        String selection = CustomerDBHandler.getID() + "=?";
        String[] selectionArgument = new String[]{id};
        Cursor cursor = db.query(CustomerDBHandler.getTABLE_CUSTOMER(), projection, selection,
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
        int vipYear = cursor.getInt(7);
        Customer customer = new Customer(new_id, firstName, lastName, email);
        customer.setRewards(rewards);
        customer.setRewardDate(rewardsDate);
        customer.setSpendingYTD(spendingYTD);
        customer.setVipYear(vipYear);

        cursor.close();
        return customer;
    }

    public List<Transaction> getTransaction(Customer customer){

        if (customer == null)
            return null;

        List<Transaction> list = new ArrayList<Transaction>();

        String id = customer.getID();
        if (getCustomer(id) == null)
            return null;

        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = new String[]{
                TRANSACTION_TIME, PRICE_BEFORE, DISCOUNTS, REWARDS_USED, DESCRIPTION};
        String selection = CUSTOMER_ID + "=?";
        String[] selectionArgument = new String[]{id};
        Cursor cursor = db.query(TABLE_TRANSACTION, projection, selection,
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
