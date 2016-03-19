package edu.gatech.seclass.tccart;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import edu.gatech.seclass.services.QRCodeService;

public class EditCustomerInfoActivity extends AppCompatActivity {

    private EditText editTextNewFirstName;
    private EditText editTextNewLastName;
    private EditText editTextNewEmail;
    private TextView textName;
    private TextView textEmail;
    private TextView textID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_customer_info);

        textName = (TextView)findViewById(R.id.textName);
        textEmail = (TextView)findViewById(R.id.textEmail);
        textID = (TextView)findViewById(R.id.textID);
        editTextNewFirstName = (EditText)findViewById(R.id.textNewFirstName);
        editTextNewLastName = (EditText)findViewById(R.id.textNewLastName);
        editTextNewEmail = (EditText)findViewById(R.id.textNewEmail);

        Customer customer = Customer.currentCustomer;
        if (customer != null &&
                Customer.customerMap.containsKey(customer.getID())){
            textID.setText(customer.getID());
            textName.setText(customer.getFullName());
            textEmail.setText(customer.getEmail());
        }

    }

    public void handleCancel(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void handleScanCard(View view){
        String id = QRCodeService.scanQRCode();

        if (id == null || id.length() == 0){
            Context context = getApplicationContext();
            CharSequence text = "Card scan failed!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }
        if (!Customer.customerMap.containsKey(id)){
            Context context = getApplicationContext();
            CharSequence text = "Not a registered ID!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }
        Context context = getApplicationContext();
        CharSequence text = "Scan card successful!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        textID.setText(id);
        Customer customer = Customer.customerMap.get(id);
        textName.setText(customer.getFullName());
        textEmail.setText(customer.getEmail());
        Customer.currentCustomer = customer;
    }

    public void handleConfirm(View view){
        Customer customer = Customer.currentCustomer;
        String firstName = editTextNewFirstName.getText().toString();
        String lastName = editTextNewLastName.getText().toString();
        String email = editTextNewEmail.getText().toString();
        if (firstName.length() <= 0 || lastName.length() <= 0
                || email.length() <= 0) {
            Context context = getApplicationContext();
            CharSequence text = "Name or email cannot be empty!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }
        customer.setName(firstName, lastName);
        customer.setEmail(email);
        Context context = getApplicationContext();
        CharSequence text = "Customer information updated!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void handleDelete(View view){

    }

    public void handleClear(View view){
    }


}
