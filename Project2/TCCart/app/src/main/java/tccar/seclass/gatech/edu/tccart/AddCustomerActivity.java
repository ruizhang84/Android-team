package tccar.seclass.gatech.edu.tccart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AddCustomerActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextEmail;
    private TextView textID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        editTextName = (EditText)findViewById(R.id.textName);
        editTextEmail = (EditText)findViewById(R.id.textEmail);
        textID = (TextView)findViewById(R.id.textID);
    }

    public void handleCancel(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void handleGenerateID(View view){
    }

    public void handleAdd(View view){
    }

    public void handleClear(View view){
    }

}
