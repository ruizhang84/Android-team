package edu.gatech.seclass.tccart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EditCustomerInfoActivity extends AppCompatActivity {

    private EditText editTextNewFirstName;
    private EditText editTextNewLastName;
    private EditText editTextNewEmail;
    private TextView textName;
    private TextView textEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_customer_info);

        textName = (TextView)findViewById(R.id.textName);
        textEmail = (TextView)findViewById(R.id.textEmail);
        editTextNewFirstName = (EditText)findViewById(R.id.textNewFirstName);
        editTextNewLastName = (EditText)findViewById(R.id.textNewLastName);
        editTextNewEmail = (EditText)findViewById(R.id.textNewEmail);

    }

    public void handleCancel(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void handleScanCard(View view){
    }

    public void handleOK(View view){
    }

    public void handleClear(View view){
    }

}
