package edu.gatech.seclass.tccart;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

    public CustomerDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_CUSTOMER + "("
                + ID + " VARCHAR(8) PRIMARY KEY," + FIRST_NAME + " TEXT,"
                + LAST_NAME + " TEXT," + EMAIL + " TEXT" + ")";
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

        db.insert(TABLE_CUSTOMER, null, values);
        db.close();
    }

    public Customer getCustomer(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = new String[]{ID,
                FIRST_NAME, LAST_NAME, EMAIL};
        String selection = ID + "=?";
        String[] selectionArgument = new String[]{id};
        Cursor cursor = db.query(TABLE_CUSTOMER, projection, selection,
                selectionArgument, null, null, null);

        if (cursor == null){
            return null;
        }
        else {
            cursor.moveToFirst();
        }

        Customer customer = new Customer(cursor.getString(0),
                cursor.getString(1), cursor.getString(2), cursor.getString(3));

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
