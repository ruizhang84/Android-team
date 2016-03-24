package edu.gatech.seclass.tccart;

import edu.gatech.seclass.services.*;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.common.api.GoogleApiClient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
/*<<<<<<< HEAD
=======
import java.util.Date;
import java.util.Locale;
>>>>>>> d54322f5715d9847fbff3185e5ebfa5dbc3fb5ff*/

public class MakePurchaseActivity extends AppCompatActivity {

    private TextView textName;
    private EditText textItemDescription;
    private EditText textPrice;
    private TextView textRewardsApplied;
    private TextView textDiscountApplied;
    private TextView textAmountToBePaid;

    private TransactionDBHandler transaction_db;
    private CustomerDBHandler customer_db;
    private CreditCard currentCC = null;

    private static final double vipDiscountRate = 0.1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_purchase);

        textName = (TextView) findViewById(R.id.textName);
        textRewardsApplied = (TextView) findViewById(R.id.textRewardsApplied);
        textDiscountApplied = (TextView) findViewById(R.id.textDiscountApplied);
        textAmountToBePaid = (TextView) findViewById(R.id.textAmountToBePaid);

        textItemDescription = (EditText) findViewById(R.id.textItemDescription);
        textPrice = (EditText) findViewById(R.id.textPrice);

        transaction_db = new TransactionDBHandler(this);
        customer_db = new CustomerDBHandler(this);

        Customer customer = Customer.currentCustomer;
        if (customer != null &&
                customer_db.getCustomer(customer.getID()) != null) {
            textName.setText(customer.getFullName());
        }

    }

    public void handleCancel(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        if (Customer.currentCustomer != null)
            intent.putExtra("current_id", Customer.currentCustomer.getID());
        startActivity(intent);
    }

    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        if (Customer.currentCustomer != null)
            intent.putExtra("current_id", Customer.currentCustomer.getID());
        startActivity(intent);
    }

    public void handleScanCustomerCard(View view) {

        String id = QRCodeService.scanQRCode();

        if (id == null || id.length() == 0) {
            Context context = getApplicationContext();
            CharSequence text = "Card scan failed!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }
        Customer customer = customer_db.getCustomer(id);
        if (customer == null) {
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

        textName.setText(customer.getFullName());
        Customer.currentCustomer = customer;
        textName.setText(customer.getFullName());
    }

    public void handleScanCreditCard(View view) throws ParseException {

        String ccStr = CreditCardService.readCreditCard();

        if (ccStr.equals("ERR")) {
            Context context = getApplicationContext();
            CharSequence text = "Failed to read credit card!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

       /* String[] strArray = ccStr.split("#");
        if (strArray.length != 5) {
            Context context = getApplicationContext();
            CharSequence text = "Credit card information is not valid!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyy");
        Date ccDate = sdf.parse(strArray[3]);
        currentCC = new CreditCard(strArray[0], strArray[1], strArray[2],
                ccDate, strArray[4]);

        Context context = getApplicationContext();
        CharSequence text = "Credit card successfully scanned!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();*/
    }

    public void handleApplyRewardDiscount(View view) {

        String description = textItemDescription.getText().toString();
        String priceStr = textPrice.getText().toString();

        //get field values
        int vips = c.getInt(c.getColumnIndex("Vip"));
        double credits = c.getDouble(c.getColumnIndex("Credit")) - c.getDouble(c.getColumnIndex("CreditApplied"));

        //see if vip expired
        Calendar lastTime = Calendar.getInstance();
        Calendar rightNow = Calendar.getInstance();
        lastTime.setTimeInMillis( c.getLong(c.getColumnIndex("Date")) );
        int lastTime_year = lastTime.get(Calendar.YEAR);
        int year = rightNow.get(Calendar.YEAR);

        if (lastTime_year != year && vips == 1)
            vips = 0;               //false 0, not this year

=======/*
        if (description.length() <= 0 || priceStr.length() <= 0) {
            Context context = getApplicationContext();
            CharSequence text = "Please add an item and price to purchase!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }

        double price_original = Double.parseDouble(priceStr);
        double price_current = price_original;
>>>>>>> d54322f5715d9847fbff3185e5ebfa5dbc3fb5ff

       (Customer customer = Customer.currentCustomer;

        if (customer == null) {
            Context context = getApplicationContext();
            CharSequence text = "Please select a customer!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }

        double rewardAvailable = customer.getEffectiveRewards();
        double discountApplied = 0;
        if (customer.isVIP()) {
            discountApplied = price_original * vipDiscountRate;
            price_current -= discountApplied;
        }
        double rewardApplied = 0;
        if (rewardAvailable < price_current) {
            rewardApplied = rewardAvailable;
        } else {
            rewardApplied = price_current;
        }
        price_current -= rewardApplied;

        textRewardsApplied.setText(String.format(Locale.US, "%5.2f", rewardApplied));
        textDiscountApplied.setText(String.format(Locale.US, "%5.2f", discountApplied));
        textAmountToBePaid.setText(String.format(Locale.US, "%5.2f", price_current));*/

    }

    public void handleConfirm(View view) {

        Customer customer = Customer.currentCustomer;
        if (customer == null) {
            Context context = getApplicationContext();
            CharSequence text = "Please select a customer!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }

        String description = textItemDescription.getText().toString();
        String priceStr = textPrice.getText().toString();
        String amountStr = textAmountToBePaid.getText().toString();

        if (description.length() <= 0 || priceStr.length() <= 0) {
            Context context = getApplicationContext();
            CharSequence text = "Please add an item and price to purchase!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }

        if (amountStr.length() <= 0) {
            Context context = getApplicationContext();
            CharSequence text = "Please click Apply Reward/Discount first!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }

        if ( currentCC == null){
            Context context = getApplicationContext();
            CharSequence text = "Please scan a credit card!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }
        // PaymentService.processPayment((String firstName
        //         String lastName,
        //         String ccNumber,
        //        Date expirationDate,
        //       String securityCode,
        //double amount);
        //EmailService.sendEMail(String recipient,
        //        String subject,
        //        String body);

        /*
        PaymentService.processPayment(String firstName
                String lastName,
                String ccNumber,
                Date expirationDate,
                String securityCode,
        double amount);
        EmailService.sendEMail(String recipient,
                String subject,
                String body);
        */

    }

    public void handleClear(View view) {
        textName.setText("");
        textItemDescription.setText("");
        textPrice.setText("");
        textRewardsApplied.setText("");
        textDiscountApplied.setText("");
        textAmountToBePaid.setText("");
        currentCC = null;
    }

}