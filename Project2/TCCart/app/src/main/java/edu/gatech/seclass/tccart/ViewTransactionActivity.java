package edu.gatech.seclass.tccart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ViewTransactionActivity extends AppCompatActivity {

    private CustomerDBHandler db;
    private TransactionDBHandler tdb;

    private TextView textName;
    private TextView textRewards;
    private TextView textTransactionRewardHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_transaction);

        textName = (TextView)findViewById(R.id.textName);
        textRewards = (TextView)findViewById(R.id.textRewards);
        textTransactionRewardHistory =
                (TextView)findViewById(R.id.textTransactionRewardHistory);

        db = new CustomerDBHandler(this);
        tdb = new TransactionDBHandler(this);
        List<Transaction> tlist = new ArrayList<>();

        Customer customer = Customer.currentCustomer;
        if (customer != null &&
                db.getCustomer(customer.getID()) != null){
            if (customer.isVIP()) {
                String vipName = customer.getFullName() + " (VIP)";
                textName.setText(vipName);
            }
            else {
                textName.setText(customer.getFullName());
            }
            String rewards_text = String.format(Locale.US, "%5.2f", customer.getRewards());
            textRewards.setText(rewards_text);

            String t = "";
            tlist = tdb.getTransaction(customer);
            for (Transaction temp : tlist) {
                t += temp.getTransactionLog();
            }
            textTransactionRewardHistory.setText(t);

        }

    }

    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        if (Customer.currentCustomer != null)
            intent.putExtra("current_id", Customer.currentCustomer.getID());
        startActivity(intent);
    }

    public void handleBack(View view){
        Intent intent = new Intent(this, MainActivity.class);
        if (Customer.currentCustomer != null)
            intent.putExtra("current_id", Customer.currentCustomer.getID());
        startActivity(intent);
    }


}
