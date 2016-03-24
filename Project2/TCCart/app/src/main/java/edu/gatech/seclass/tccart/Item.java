package edu.gatech.seclass.tccart;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class Item extends AppCompatActivity {
    private EditText itemName;
    private EditText itemPrice;
    private EditText itemDescription;

    //database
    private ItemDBHelper database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemName = (EditText) findViewById(R.id.itemName);
        itemPrice = (EditText) findViewById(R.id.itemPrice);
        itemDescription = (EditText) findViewById(R.id.Description);

    }

    public void handleClick(View view){
        String Name = itemName.getText().toString();
        String Price = itemPrice.getText().toString();
        String Description = itemDescription.getText().toString();

        //access database
        database = new ItemDBHelper( getApplicationContext() );
        SQLiteDatabase db = database.getWritableDatabase();

        //sql to insert item info
        db.execSQL("Insert into Items (Name, Price, Description) " +
                "Values  (" + Name + " " + Price + " " + Description +") ");

    }


    public void handleCancel(View view){
        itemName.setText("");
        itemPrice.setText("");
        itemDescription.setText("");
    }


}
