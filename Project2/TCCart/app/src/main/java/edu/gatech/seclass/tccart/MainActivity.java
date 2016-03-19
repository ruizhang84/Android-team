package edu.gatech.seclass.tccart;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.printservice.PrintService;
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

import edu.gatech.seclass.services.*;

public class MainActivity extends AppCompatActivity {

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

        //Preload some customers
        Customer.preloadCustomers();

        Customer customer = Customer.currentCustomer;
        if (customer != null &&
                Customer.customerMap.containsKey(customer.getID())){
            textID.setText(customer.getID());
            textName.setText(customer.getFullName());
            textEmail.setText(customer.getEmail());
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
        if (id == null || id.length() == 0){
            Context context = getApplicationContext();
            CharSequence text = "Please select a customer!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }
        if (!Customer.customerMap.containsKey(id)){
            Context context = getApplicationContext();
            CharSequence text = "This customer is not registered!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }
        Customer customer = Customer.customerMap.get(id);
        String firstName = customer.getFirstName();
        String lastName = customer.getLastName();
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
