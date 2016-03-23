package edu.gatech.seclass.tccart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;
import java.util.Locale;

public class ViewTransactionActivity extends AppCompatActivity {

    private CustomerDBHandler db;

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
        }

    }

    public void handleBack(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
