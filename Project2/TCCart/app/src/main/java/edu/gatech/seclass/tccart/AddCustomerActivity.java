package edu.gatech.seclass.tccart;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddCustomerActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextEmail;
    private TextView textID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        editTextName = (EditText)findViewById(R.id.textName);
        editTextEmail = (EditText)findViewById(R.id.textEmail);
        textID = (TextView)findViewById(R.id.textID);
    }

    public void handleCancel(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void handleGenerateID(View view){
    }

    public void handleAdd(View view){
        String id = textID.getText().toString();
        if (id.length() != 8){
            Context context = getApplicationContext();
            CharSequence text = "Not a valid ID!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }
        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();
        if (name.length() <= 0 || email.length() <= 0) {
            Context context = getApplicationContext();
            CharSequence text = "Name or email cannot be empty!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }
        Customer customer = new Customer(name, email, id);
        if (Customer.customerMap.containsKey(id)){
            Context context = getApplicationContext();
            CharSequence text = "This id already exists! Please generate a new id!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }
        Customer.customerMap.put(id, customer);
        Context context = getApplicationContext();
        CharSequence text = "Customer is added successfully!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        return;
    }

    public void handleClear(View view){
    }

}
