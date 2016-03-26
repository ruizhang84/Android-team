package edu.gatech.seclass.tccart;

import edu.gatech.seclass.services.*;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
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
import java.util.Date;
import java.util.Locale;

public class MakePurchaseActivity extends AppCompatActivity {

    private TextView textName;
    private EditText textItemDescription;
    private EditText textPrice;
    private TextView textRewardsApplied;
    private TextView textDiscountApplied;
    private TextView textAmountToBePaid;
    private TextView textCardCard;

    private TransactionDBHandler transaction_db;
    private CustomerDBHandler customer_db;
    private CreditCard currentCC = null;

    private static final double vipDiscountRate = 0.1;
    private static final double rewardThreshold = 30.0;
    private static final double rewardAmount = 3.0;
    private static final double vipThreshold = 300.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_purchase);

        textName = (TextView) findViewById(R.id.textName);
        textCardCard = (TextView) findViewById(R.id.textCardCard);

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

        if (ccStr == null || ccStr.equals("ERR")) {
            Context context = getApplicationContext();
            CharSequence text = "Failed to read credit card!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }

        String[] strArray = ccStr.split("#");
        if (strArray.length != 5) {
            Context context = getApplicationContext();
            CharSequence text = "Credit card information is not valid!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyy", Locale.US);
        Date ccDate = sdf.parse(strArray[3]);
        currentCC = new CreditCard(strArray[0], strArray[1], strArray[2],
                ccDate, strArray[4]);

        int ccLength = currentCC.ccNumber.length();
        String ccEnding = "******" + currentCC.ccNumber.substring(ccLength-5, ccLength-1);
        textCardCard.setText(ccEnding);
        Context context = getApplicationContext();
        CharSequence text = "Credit card successfully scanned!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void handleApplyRewardDiscount(View view) {

        String description = textItemDescription.getText().toString();
        String priceStr = textPrice.getText().toString();

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

        Customer customer = Customer.currentCustomer;

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
        textAmountToBePaid.setText(String.format(Locale.US, "%5.2f", price_current));

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
        String discountStr = textDiscountApplied.getText().toString();
        String rewardStr = textRewardsApplied.getText().toString();

        if (description.length() <= 0 || priceStr.length() <= 0) {
            Context context = getApplicationContext();
            CharSequence text = "Please add an item and price to purchase!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }

        if (amountStr.length() <= 0 || discountStr.length() <= 0
                || rewardStr.length() <= 0) {
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

        AlertDialog.Builder alert = new AlertDialog.Builder(
                view.getContext());

        int ccLength = currentCC.ccNumber.length();
        String ccEnding = currentCC.ccNumber.substring(ccLength-5, ccLength-1);
        alert.setTitle("Confirm Purchase");
        String message = "Are you sure to make this purchase?\n"
                + "Item: " + description + "\n"
                + "Price: $" + priceStr + "\n"
                + "Discount: $" + discountStr + "\n"
                + "Rewards Applied: $" + rewardStr + "\n"
                + "Amount to Pay: $" + amountStr + "\n"
                + "Credit Card:  *****" + ccEnding + "\n";
        alert.setMessage(message);

        final Transaction transaction = new Transaction(
                Customer.currentCustomer,
                new Date(), Double.parseDouble(priceStr),
                Double.parseDouble(discountStr),
                Double.parseDouble(rewardStr),
                description);

        final Intent intent = new Intent(this, MainActivity.class);
        final String descriptionFinal = description;
        final String priceStrFinal = priceStr;
        final String discountStrFinal = discountStr;
        final String rewardStrFinal = rewardStr;
        final String ccEndingFinal = ccEnding;
        final String amountStrFinal = amountStr;

        alert.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        Customer customer = transaction.getCustomer();

                        double paidAmount = transaction.getTotalAmount()
                                - transaction.getRewardsApplied()
                                - transaction.getVipDiscount();

                        if (paidAmount > 0) {
                            boolean result = PaymentService.processPayment(
                                    currentCC.firstName,
                                    currentCC.lastName,
                                    currentCC.ccNumber,
                                    currentCC.expirationDate,
                                    currentCC.securityCode,
                                    paidAmount);
                            if (!result) {
                                Context context = getApplicationContext();
                                CharSequence text = "Credit card payment failed!";
                                int duration = Toast.LENGTH_SHORT;
                                Toast toast = Toast.makeText(context, text, duration);
                                toast.show();
                                dialog.cancel();
                                return;
                            }
                        }

                        boolean email_res = EmailService.sendEMail(customer.getEmail(),
                                "Your just made a purchase at TCCart",
                                "Order details:\n"
                                + "Item: " + descriptionFinal + "\n"
                                + "Price: $" + priceStrFinal + "\n"
                                + "Discount: $" + discountStrFinal + "\n"
                                + "Rewards Applied: $" + rewardStrFinal + "\n"
                                + "Amount Paid: $" + amountStrFinal + "\n"
                                + "Credit Card:  *****" + ccEndingFinal + "\n" );
                        if (!email_res){
                            Context context = getApplicationContext();
                            CharSequence text = "Failed to send purchase notification email!";
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        }

                          customer.setRewards(customer.getRewards()
                                  - transaction.getRewardsApplied());

                          if (paidAmount >= rewardThreshold) {
                              customer.setRewards(customer.getRewards() + rewardAmount);
                              customer.setRewardDate(new Date());
                              String rewardAmountStr = String.format(Locale.US, "%5.2f", rewardAmount);
                              email_res = EmailService.sendEMail(customer.getEmail(),
                                      "Your just earned a $" + rewardAmountStr + " reward at TCCart",
                                      "Order details:\n"
                                              + "Item: " + descriptionFinal + "\n"
                                              + "Amount Paid: $" + amountStrFinal + "\n"
                                              + "Reward Earned: " + rewardAmountStr + "\n" );
                              if (!email_res){
                                  Context context = getApplicationContext();
                                  CharSequence text = "Failed to send reward notification email!";
                                  int duration = Toast.LENGTH_SHORT;
                                  Toast toast = Toast.makeText(context, text, duration);
                                  toast.show();
                              }
                          }

                          customer.setSpendingYTD(customer.getSpendingYTD() + paidAmount);
                          if (customer.getSpendingYTD() >= vipThreshold && !customer.isVIPNextYear()){
                              Calendar today = Calendar.getInstance();
                              int thisYear = today.get(Calendar.YEAR);
                              int nextYear = thisYear+1;
                              customer.addVipYear(nextYear);

                              Context context = getApplicationContext();
                              CharSequence text = "Customer got VIP status!";
                              int duration = Toast.LENGTH_SHORT;
                              Toast toast = Toast.makeText(context, text, duration);
                              toast.show();

                              email_res = EmailService.sendEMail(customer.getEmail(),
                                      "You've Earned TCCart VIP Customer Status for "
                                              + String.format(Locale.US, "%1$04d", nextYear),
                                      "Congratulations, " + customer.getFullName() + ",\n"
                                              + "Because you have spent over $" +
                                              String.format(Locale.US, "%4.2f", vipThreshold)
                                              + "in TCCart, " + "we award you VIP customer status in "
                                              + String.format(Locale.US, "%1$04d", nextYear)
                                              + "Your VIP status will become effective from January 1st, "
                                              + String.format(Locale.US, "%1$04d", nextYear) );
                              if (!email_res){
                                  context = getApplicationContext();
                                  text = "Failed to send VIP status notification email!";
                                  duration = Toast.LENGTH_SHORT;
                                  toast = Toast.makeText(context, text, duration);
                                  toast.show();
                              }
                          }

                        customer_db.updateCustomer(customer);
                        transaction_db.addTransaction(transaction);

                        textName.setText("");
                        textItemDescription.setText("");
                        textPrice.setText("");
                        textRewardsApplied.setText("");
                        textDiscountApplied.setText("");
                        textAmountToBePaid.setText("");
                        textCardCard.setText("");
                        currentCC = null;

                        Context context = getApplicationContext();
                        CharSequence text = "Transaction completed successfully!";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();

                        if (Customer.currentCustomer != null)
                            intent.putExtra("current_id", Customer.currentCustomer.getID());
                        startActivity(intent);

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

    public void handleClear(View view) {
        textName.setText("");
        textItemDescription.setText("");
        textPrice.setText("");
        textRewardsApplied.setText("");
        textDiscountApplied.setText("");
        textAmountToBePaid.setText("");
        textCardCard.setText("");
        currentCC = null;
    }

}