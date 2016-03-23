package edu.gatech.seclass.tccart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

public class ViewTransactionActivity extends AppCompatActivity {

    private CustomerDBHandler db;

    private static final long ms_in_year = 31536000000L;

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
            Date today = new Date();
            //customer.setVipDate(today); // for testing
            Date vipDate = customer.getVipDate();
            long dt_ms = today.getTime() - vipDate.getTime();
            if (dt_ms <= ms_in_year) {
                String vipName = customer.getFullName() + " (VIP)";
                textName.setText(vipName);
            }
            else {
                textName.setText(customer.getFullName());
            }
            textRewards.setText(Double.toString(customer.getRewards()));
        }

    }

    public void handleBack(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
