package edu.gatech.seclass.tccart;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by paul on 3/26/16.
 * See the following URLs for good ways to structure SQLite architecture
 * https://github.com/codepath/android_guides/wiki/Local-Databases-with-SQLiteOpenHelper
 * http://www.vogella.com/tutorials/AndroidSQLite/article.html
 */
public class DBHelper extends SQLiteOpenHelper {
    //Singleton instance
    private static DBHelper singleInstance;

    //Database Info
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "TCCart";

    //Table Names
    public static final String TABLE_CUSTOMER = "CustomerInfo";
    public static final String TABLE_TRANSACTION = "TransactionInfo";


    //Customer Table Columns
    public static final String CUST_ID = "id";
    public static final String CUST_FIRST_NAME = "first_name";
    public static final String CUST_LAST_NAME = "last_name";
    public static final String CUST_EMAIL = "email";
    public static final String CUST_REWARDS = "rewards";
    public static final String CUST_REWARD_DATE = "rewards_date";
    public static final String CUST_SPENDING_YTD = "spending_ytd";
    public static final String CUST_SPENDING_YEAR = "spending_year";
    public static final String CUST_VIP_YEARS = "vip_years";

    //Transaction Table Columns

    public static final String TRANS_CUSTOMER_ID = "customer_id";
    public static final String TRANS_TRANSACTION_TIME = "transaction_time";
    public static final String TRANS_PRICE_BEFORE = "price_before";
    public static final String TRANS_DISCOUNTS = "discounts";
    public static final String TRANS_REWARDS_USED = "rewards_used";
    public static final String TRANS_DESCRIPTION = "description";


    private DBHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Method executed only when the database name does not already exists on disk
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CUST_TABLE = "CREATE TABLE " + TABLE_CUSTOMER + "("
                + CUST_ID + " VARCHAR(8) PRIMARY KEY,"
                + CUST_FIRST_NAME + " TEXT,"
                + CUST_LAST_NAME + " TEXT,"
                + CUST_EMAIL + " TEXT,"
                + CUST_REWARDS + " DOUBLE,"
                + CUST_REWARD_DATE + " BIGINT,"
                + CUST_SPENDING_YTD + " DOUBLE,"
                + CUST_SPENDING_YEAR + " INT,"
                + CUST_VIP_YEARS + " TEXT"
                + ")";
        String CREATE_TRANS_TABLE = "CREATE TABLE " + TABLE_TRANSACTION + "("
                + TRANS_CUSTOMER_ID + " VARCHAR(8),"
                + TRANS_TRANSACTION_TIME + " BIGINT,"
                + TRANS_PRICE_BEFORE + " DOUBLE,"
                + TRANS_DISCOUNTS + " DOUBLE,"
                + TRANS_REWARDS_USED + " DOUBLE,"
                + TRANS_DESCRIPTION + " TEXT"
                + ")";
        db.execSQL(CREATE_CUST_TABLE);
        db.execSQL(CREATE_TRANS_TABLE);
    }

    //Called when database name already exists on disk and the
    //DATABASE_VERSION above is different than whats on disk
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSACTION);
        onCreate(db);
    }

    public static synchronized DBHelper getInstance(Context context) {
        //Use application context (passed in the form of "this")
        //Details here: http://bit.ly/6LRzfx
        //
        //Purpose here is to make sure only one instance of this object is ever created
        //Singleton pattern, use as follows from any activity
        //DBHelper dbh = DBHelper.getInstance(this);
        //
        if (DBHelper.singleInstance == null) {
            singleInstance = new DBHelper(context.getApplicationContext());
        }
        return DBHelper.singleInstance;
    }
}
