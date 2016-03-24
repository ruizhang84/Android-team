package edu.gatech.seclass.tccart;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zhang on 3/23/2016.
 */
public class ItemDBHelper extends SQLiteOpenHelper {

    //version number to upgrade database version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    public static final String DATABASE_NAME = "Items.db";

    public static final String REWARDS_TABLE_NAME = "Items";
    public static final String REWARDS_COLUMN_ID = "ID";
    public static final String REWARDS_COLUMN_NAME = "Name";

    public static final String REWARDS_COLUMN_PURCHASE = "Price";           //purchase price
    public static final String REWARDS_COLUMN_DESCRIPTION = "Description";           //Description



    public ItemDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_STUDENT = "CREATE TABLE " + REWARDS_TABLE_NAME  + "("
                + REWARDS_COLUMN_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + REWARDS_COLUMN_NAME + " TEXT, "
                + REWARDS_COLUMN_PURCHASE + " DECIMAL(10,2), "
                + REWARDS_COLUMN_DESCRIPTION + " TEXT )";

        db.execSQL(CREATE_TABLE_STUDENT);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed, all data will be gone
        db.execSQL("DROP TABLE IF EXISTS " + REWARDS_TABLE_NAME);

        // Create tables again
        onCreate(db);

    }



}
