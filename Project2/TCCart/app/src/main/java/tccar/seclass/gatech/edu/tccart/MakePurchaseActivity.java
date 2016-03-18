package tccar.seclass.gatech.edu.tccart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MakePurchaseActivity extends AppCompatActivity {

    private TextView textName;
    private EditText textItemDescription;
    private EditText textPrice;
    private TextView textRewardsApplied;
    private TextView textDiscountApplied;
    private TextView textAmountToBePaid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_purchase);

        textName = (TextView)findViewById(R.id.textName);
        textRewardsApplied = (TextView)findViewById(R.id.textRewardsApplied);
        textDiscountApplied = (TextView)findViewById(R.id.textDiscountApplied);
        textAmountToBePaid = (TextView)findViewById(R.id.textAmountToBePaid);

        textName = (EditText)findViewById(R.id.textItemDescription);
        textName = (EditText)findViewById(R.id.textPrice);

    }

    public void handleCancel(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void handleScanCustomerCard(View view){
    }

    public void handleScanCreditCard(View view){
    }

    public void handleApplyRewardDiscount(View view){
    }

    public void handleConfirm(View view){
    }

    public void handleClear(View view){
    }

}
