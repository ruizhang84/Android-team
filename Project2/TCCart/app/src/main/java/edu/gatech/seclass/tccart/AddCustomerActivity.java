package edu.gatech.seclass.tccart;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class AddCustomerActivity extends AppCompatActivity {

    private CustomerDBHandler db;

    private EditText editTextFirstName;
    private EditText editTextLastName;
    private EditText editTextEmail;
    private TextView textID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        db = new CustomerDBHandler(this);

        editTextFirstName = (EditText)findViewById(R.id.textFirstName);
        editTextLastName = (EditText)findViewById(R.id.textLastName);
        editTextEmail = (EditText)findViewById(R.id.textEmail);
        textID = (TextView)findViewById(R.id.textID);
    }

    public void handleCancel(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void handleGenerateID(View view){
        Random rd = new Random();
        String id = null;
        while(true){
            StringBuilder sb = new StringBuilder();
            int numChar = Customer.idCharList.length();
            for(int i = 0; i < Customer.idLength; i++){
                int index = rd.nextInt(numChar);
                sb.append(Customer.idCharList.charAt(index));
            }
            id = sb.toString();
            if (db.getCustomer(id) == null)
                break;
        }
        textID.setText(id);
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
        String firstName = editTextFirstName.getText().toString();
        String lastName = editTextLastName.getText().toString();
        String email = editTextEmail.getText().toString();
        if (firstName.length() <= 0 || lastName.length() <= 0
                || email.length() <= 0) {
            Context context = getApplicationContext();
            CharSequence text = "Name or email cannot be empty!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }
        Customer customer = new Customer(firstName, lastName, email, id);
        if (db.getCustomer(id) != null){
            Context context = getApplicationContext();
            CharSequence text = "This id already exists! Please generate a new id!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }
        db.addCustomer(customer);
        Context context = getApplicationContext();
        CharSequence text = "Customer is added successfully!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void handleClear(View view){
        editTextFirstName.setText("");
        editTextLastName.setText("");
        editTextEmail.setText("");
        textID.setText("");
    }

}
