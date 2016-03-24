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
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Calendar;

public class MakePurchaseActivity extends AppCompatActivity {

    private TextView textName;
    private EditText textItemDescription;
    private EditText textPrice;
    private TextView textRewardsApplied;
    private TextView textDiscountApplied;
    private TextView textAmountToBePaid;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    //private object and CreditCard info
    private Transaction TS;
    private String CreditCard;
    // database transaction and customer
    private RewardsAndDiscountDBHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_purchase);

        textName = (TextView) findViewById(R.id.textName);
        textRewardsApplied = (TextView) findViewById(R.id.textRewardsApplied);
        textDiscountApplied = (TextView) findViewById(R.id.textDiscountApplied);
        textAmountToBePaid = (TextView) findViewById(R.id.textAmountToBePaid);

        textName = (EditText) findViewById(R.id.textItemDescription);
        textName = (EditText) findViewById(R.id.textPrice);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        //create transaction
        TS = new Transaction();

        //access database
        database = new RewardsAndDiscountDBHelper( getApplicationContext() );
    }

    //CreditCardService.readCreditCard();

    public void handleCancel(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void handleScanCustomerCard(View view) {
         String CustomerID = QRCodeService.scanQRCode();
         if (CustomerID.equals("ERR") ){
             //report error
             Context context = getApplicationContext();
             CharSequence text = "Customer ID Not Exist!";
             int duration = Toast.LENGTH_SHORT;
             Toast toast = Toast.makeText(context, text, duration);
             toast.show();
         }else{
             //readin ID
             TS.setCustomerID(Long.parseLong(CustomerID));
             //display Customer Name

         }



    }

    public void handleScanCreditCard(View view) {
        CreditCard = CreditCardService.readCreditCard();

        if (CreditCard.equals("ERR") ){
            //report error
            Context context = getApplicationContext();
            CharSequence text = "Cannot read Card!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }


    }

    public void handleApplyRewardDiscount(View view) {
        //get vip and rewards from query
        SQLiteDatabase db = database.getReadableDatabase();

        //sql to get vip and credit order by time
        Cursor c = db.rawQuery( "select Vip, Credit, CreditApplied, Year" +
                                "from RewardsAndDiscount " +
                                "Where CustomerID = " + TS.getCustomerID() +
                                "Order Year DESC by", null);
        //last transaction
        c.moveToFirst();

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


        //display credits and vip discounts


        //TS.setCreditApplied();
        //TS.setVipDiscount();

        //recycle cursor
        c.close();
    }

    public void handleConfirm(View view) {
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
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "MakePurchase Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://edu.gatech.seclass.tccart/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "MakePurchase Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://edu.gatech.seclass.tccart/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
