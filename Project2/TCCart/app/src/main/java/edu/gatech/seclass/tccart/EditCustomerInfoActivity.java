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

import edu.gatech.seclass.services.QRCodeService;

public class EditCustomerInfoActivity extends AppCompatActivity {

    private CustomerDBHandler db;

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

        db = new CustomerDBHandler(this);

        textName = (TextView)findViewById(R.id.textName);
        textEmail = (TextView)findViewById(R.id.textEmail);
        textID = (TextView)findViewById(R.id.textID);
        editTextNewFirstName = (EditText)findViewById(R.id.textNewFirstName);
        editTextNewLastName = (EditText)findViewById(R.id.textNewLastName);
        editTextNewEmail = (EditText)findViewById(R.id.textNewEmail);

        Customer customer = Customer.currentCustomer;
        if (customer != null &&
                db.getCustomer(customer.getID()) != null){
            textID.setText(customer.getID());
            textName.setText(customer.getFullName());
            textEmail.setText(customer.getEmail());
        }

    }

    public void handleCancel(View view){
        Intent intent = new Intent(this, MainActivity.class);
        if (Customer.currentCustomer != null)
            intent.putExtra("current_id", Customer.currentCustomer.getID());
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
        Customer customer = db.getCustomer(id);
        if (customer == null){
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
        textName.setText(customer.getFullName());
        textEmail.setText(customer.getEmail());
        Customer.currentCustomer = customer;
    }

    public void handleConfirm(View view){
        Customer customer = Customer.currentCustomer;
        if (customer == null){
            Context context = getApplicationContext();
            CharSequence text = "Must select a customer first!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }
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
        db.updateCustomer(customer);
        Customer.currentCustomer = customer;
        Context context = getApplicationContext();
        CharSequence text = "Customer information updated!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        Intent intent = new Intent(this, MainActivity.class);
        if (Customer.currentCustomer != null)
            intent.putExtra("current_id", Customer.currentCustomer.getID());
        startActivity(intent);
    }

    public void handleDelete(View view){
        Customer customer = Customer.currentCustomer;
        if (customer == null){
            Context context = getApplicationContext();
            CharSequence text = "Please select a customer to delete.";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }

        if (db.getCustomer(customer.getID()) == null){
            Context context = getApplicationContext();
            CharSequence text = "This customer is not registered, no need to delete.";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }

        // open an alert dialog
        AlertDialog.Builder alert = new AlertDialog.Builder(
                view.getContext());

        alert.setTitle("Confirm Delete");
        alert.setMessage("Are you sure you want to delete "
                + Customer.currentCustomer.getFullName() + "?");

        alert.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        db.deleteCustomer(Customer.currentCustomer);
                        Customer.currentCustomer = null;
                        textEmail.setText("");
                        textID.setText("");
                        textName.setText("");
                        editTextNewEmail.setText("");
                        editTextNewLastName.setText("");
                        editTextNewFirstName.setText("");
                        Context context = getApplicationContext();
                        CharSequence text = "Customer Deleted!";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        dialog.cancel();
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

    public void handleClearSelected(View view){
        textEmail.setText("");
        textID.setText("");
        textName.setText("");
        Customer.currentCustomer = null;
    }

    public void handleClearField(View view){
        editTextNewEmail.setText("");
        editTextNewLastName.setText("");
        editTextNewFirstName.setText("");
    }


}
