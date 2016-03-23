package edu.gatech.seclass.tccart;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
                + ")";
        db.execSQL(CREATE_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSACTION);
        onCreate(db);
    }








}
