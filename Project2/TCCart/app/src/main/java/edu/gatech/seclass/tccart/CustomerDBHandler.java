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
public class CustomerDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "TCCart";
    private static final String TABLE_CUSTOMER = "CustomerInfo";

    private static final String ID = "id";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String EMAIL = "email";
    private static final String REWARDS = "rewards";
    private static final String REWARD_DATE = "rewards_date";
    private static final String SPENDING_YTD = "spending_ytd";
    private static final String VIP_YEAR = "vip_year";

    public static String getID(){ return ID; }
    public static String getFIRST_NAME(){ return FIRST_NAME; }
    public static String getLAST_NAME(){ return LAST_NAME; }
    public static String getEMAIL(){ return EMAIL; }
    public static String getREWARDS(){ return REWARDS; }
    public static String getREWARD_DATE(){ return REWARD_DATE; }
    public static String getSPENDING_YTD(){ return SPENDING_YTD; }
    public static String getVIP_YEAR(){ return VIP_YEAR; }
    public static String getTABLE_CUSTOMER(){ return TABLE_CUSTOMER; }


    public CustomerDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_CUSTOMER + "("
                + ID + " VARCHAR(8) PRIMARY KEY,"
                + FIRST_NAME + " TEXT,"
                + LAST_NAME + " TEXT,"
                + EMAIL + " TEXT,"
                + REWARDS + " DOUBLE,"
                + REWARD_DATE + " BIGINT,"
                + SPENDING_YTD + " DOUBLE,"
                + VIP_YEAR + " INT"
                + ")";
        db.execSQL(CREATE_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMER);
        onCreate(db);
    }

    public void addCustomer(Customer customer) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ID, customer.getID());
        values.put(FIRST_NAME, customer.getFirstName());
        values.put(LAST_NAME, customer.getLastName());
        values.put(EMAIL, customer.getEmail());
        values.put(REWARDS, customer.getRewards());

        Date rewardsDate = customer.getRewardDate();
        if (rewardsDate == null) {
            values.put(REWARD_DATE, (new Date(0)).getTime());
        }
        else{
            values.put(REWARD_DATE, rewardsDate.getTime());
        }

        values.put(SPENDING_YTD, customer.getSpendingYTD());
        values.put(VIP_YEAR, customer.getVipYear());

        db.insert(TABLE_CUSTOMER, null, values);
        db.close();
    }

    public Customer getCustomer(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = new String[]{ID,
                FIRST_NAME, LAST_NAME, EMAIL, REWARDS, REWARD_DATE, SPENDING_YTD, VIP_YEAR};
        String selection = ID + "=?";
        String[] selectionArgument = new String[]{id};
        Cursor cursor = db.query(TABLE_CUSTOMER, projection, selection,
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

    public int updateCustomer(Customer customer) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ID, customer.getID());
        values.put(FIRST_NAME, customer.getFirstName());
        values.put(LAST_NAME, customer.getLastName());
        values.put(EMAIL, customer.getEmail());
        values.put(REWARDS, customer.getRewards());

        Date rewardsDate = customer.getRewardDate();
        if (rewardsDate == null) {
            values.put(REWARD_DATE, (new Date(0)).getTime());
        }
        else{
            values.put(REWARD_DATE, rewardsDate.getTime());
        }

        values.put(SPENDING_YTD, customer.getSpendingYTD());
        values.put(VIP_YEAR, customer.getVipYear());

        return db.update(TABLE_CUSTOMER, values, ID + " = ?",
                new String[]{customer.getID()});
    }

    public void deleteCustomer(Customer customer) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CUSTOMER, ID + " = ?",
                new String[]{customer.getID()});
        db.close();
    }

}
