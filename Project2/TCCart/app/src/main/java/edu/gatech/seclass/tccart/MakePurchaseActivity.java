package edu.gatech.seclass.tccart;

import edu.gatech.seclass.services.*;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

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
    }

    CreditCardService.readCreditCard();

    public void handleCancel(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void handleScanCustomerCard(View view) {
    }

    public void handleScanCreditCard(View view) {
    }

    public void handleApplyRewardDiscount(View view) {
    }

    public void handleConfirm(View view) {
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
