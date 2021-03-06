package edu.gatech.seclass.tccart;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.printservice.PrintService;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.services.*;

public class MainActivity extends AppCompatActivity {

    private CustomerDBHandler db;

    private TextView textName;
    private TextView textEmail;
    private TextView textID;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textName = (TextView) findViewById(R.id.textName);
        textEmail = (TextView) findViewById(R.id.textEmail);
        textID = (TextView) findViewById(R.id.textID);

        db = new CustomerDBHandler(this);
        preloadCustomers();

        textID.setText("");
        textName.setText("");
        textEmail.setText("");
        Customer.currentCustomer = null;

    }

    public void onResume() {
        super.onResume();

        Intent intent = getIntent();
        String current_id = intent.getStringExtra("current_id");

        Customer customer = null;
        if (current_id != null)
            customer = db.getCustomer(current_id);

        if (customer != null){
            Customer.currentCustomer = customer;
            textID.setText(customer.getID());
            textName.setText(customer.getFullName());
            textEmail.setText(customer.getEmail());
        }

    }

    public void handleBack(View view){ }

    public void preloadCustomers(){

        //Create list of initial customers to add to SQLite database
        //Using a list of objects to make it easier to work with
        List<Customer> cList = new ArrayList<>();
        cList.add(new Customer("7c86ffee", "Ralph", "Hapschatt", "ralph@gmail.com" ));
        cList.add(new Customer("b59441af", "Betty", "Monro", "betty@gmail.com" ));
        cList.add(new Customer("cd0f0e05", "Everett", "Scott", "everett@gmail.com" ));

        //test each customer ID to see if it exists, if it does not, add
        for (Customer tempc : cList) {
            if (db.getCustomer(tempc.getID()) == null) {
                db.addCustomer(tempc);
            }
        }
    }

    public void handleScanCard(View view) {
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

    public void handleAddNewCustomer(View view) {
        Intent intent = new Intent(this, AddCustomerActivity.class);
        startActivity(intent);
    }

    public void handleEditCustomerInfo(View view) {
        Intent intent = new Intent(this, EditCustomerInfoActivity.class);
        startActivity(intent);
    }

    public void handlePrintCard(View view) {

        String id = textID.getText().toString();

        // check whether id is valid
        if (id.length() == 0 || Customer.currentCustomer == null){
            Context context = getApplicationContext();
            CharSequence text = "Please select a customer!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }
        Customer customer = db.getCustomer(id);
        if (customer == null){
            Context context = getApplicationContext();
            CharSequence text = "This customer is not registered!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }

        // open an alert dialog
        AlertDialog.Builder alert = new AlertDialog.Builder(
                view.getContext());

        alert.setTitle("Confirm Print Customer Card");
        alert.setMessage("Are you sure you want to print a customer card for "
                + customer.getFullName() + "?");

        alert.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // print customer card
                        Customer customer = Customer.currentCustomer;
                        String firstName = customer.getFirstName();
                        String lastName = customer.getLastName();
                        String id = customer.getID();
                        if (PrintingService.printCard(firstName, lastName, id)){
                            Context context = getApplicationContext();
                            CharSequence text = "Print Card Success!";
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        }
                        else{
                            Context context = getApplicationContext();
                            CharSequence text = "Print Card Failed!";
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        }
                        dialog.cancel();
                    }
                });
        alert.setNegativeButton("NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alert.show();
    }

    public void handleViewTransaction(View view) {
        Intent intent = new Intent(this, ViewTransactionActivity.class);
        startActivity(intent);
    }

    public void handleMakePurchase(View view) {
        Intent intent = new Intent(this, MakePurchaseActivity.class);
        startActivity(intent);
    }

    public void handleClear(View view){
        textName.setText("");
        textEmail.setText("");
        textID.setText("");
        Customer.currentCustomer = null;
    }

}
