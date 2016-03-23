package edu.gatech.seclass.tccart;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by rui on 16/3/22.
 */

public class RewardsAndDiscountDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Rewards.db";

    public static final String REWARDS_TABLE_NAME = "RewardsAndDiscount";
    public static final String REWARDS_COLUMN_TRANSACTION_ID = "TransactionID";
    public static final String REWARDS_COLUMN_CUSTOMER_ID = "CustomerID";
    public static final String REWARDS_COLUMN_YEAR = "Year";
    public static final String REWARDS_COLUMN_VIP = "VipDiscount";
    public static final String REWARDS_COLUMN_CREDIT = "CreditApplied";


    public RewardsAndDiscountDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE RewardsAndDiscount" +
                        "TransactionID MEDIUMINT PRIMARY KEY NOT NULL, CustomerID MEDIUMINT NOT NULL" +
                        "Year DATE, VipDiscount DECIMAL(10,2), CreditApplied DECIMAL(10,5)" //+
                        //"FOREIGN KEY(CustomerID) REFERENCES CustomerInfo(ID)"
        );
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL("DROP TABLE IF EXISTS RewardsAndDiscount");
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


    //



}
