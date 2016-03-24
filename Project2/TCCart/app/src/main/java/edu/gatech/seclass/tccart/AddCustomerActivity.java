package edu.gatech.seclass.tccart;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.gatech.seclass.services.PrintingService;

public class AddCustomerActivity extends AppCompatActivity {

    private CustomerDBHandler db;
    private static Customer customer_to_add = null;

    private EditText editTextFirstName;
    private EditText editTextLastName;
    private EditText editTextEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        db = new CustomerDBHandler(this);

        editTextFirstName = (EditText)findViewById(R.id.textFirstName);
        editTextLastName = (EditText)findViewById(R.id.textLastName);
        editTextEmail = (EditText)findViewById(R.id.textEmail);
    }

    public void handleCancel(View view){
        Intent intent = new Intent(this, MainActivity.class);
        if (Customer.currentCustomer != null)
            intent.putExtra("current_id", Customer.currentCustomer.getID());
        startActivity(intent);
    }

    public String generateID(){
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
        return id;
    }

    public void handleAdd(View view){
        String id = this.generateID();
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

        String email_pattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(email_pattern);
        Matcher matcher = pattern.matcher(email);

        if (!matcher.matches()){
            Context context = getApplicationContext();
            CharSequence text = "Please enter a valid email address in the format aaa@bbb.ccc!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }

        customer_to_add = new Customer(id, firstName, lastName, email);

        AlertDialog.Builder alert = new AlertDialog.Builder(
                view.getContext());

        alert.setTitle("Confirm Add");
        alert.setMessage("Are you sure you want to add this customer and print a card?\n "
                + "Name: " + customer_to_add.getFullName() + "\n"
                + "Email: " + customer_to_add.getEmail() + "\n"
                + "ID: " + customer_to_add.getID());

        alert.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        db.addCustomer(customer_to_add);
                        Customer.currentCustomer = customer_to_add;
                        editTextFirstName.setText("");
                        editTextLastName.setText("");
                        editTextEmail.setText("");
                        customer_to_add = null;
                        Context context = getApplicationContext();
                        CharSequence text = "Customer is added successfully!";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();

                        Customer customer = Customer.currentCustomer;
                        String firstName = customer.getFirstName();
                        String lastName = customer.getLastName();
                        String id = customer.getID();
                        if (PrintingService.printCard(firstName, lastName, id)){
                            context = getApplicationContext();
                            text = "Print Card Success!";
                            duration = Toast.LENGTH_SHORT;
                            toast = Toast.makeText(context, text, duration);
                            toast.show();
                        }
                        else{
                            context = getApplicationContext();
                            text = "Print Card Failed!";
                            duration = Toast.LENGTH_SHORT;
                            toast = Toast.makeText(context, text, duration);
                            toast.show();
                        }
                    }
                });

        alert.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();
                    }
                });
        alert.show();
    }

    public void handleClear(View view){
        editTextFirstName.setText("");
        editTextLastName.setText("");
        editTextEmail.setText("");
    }

}
