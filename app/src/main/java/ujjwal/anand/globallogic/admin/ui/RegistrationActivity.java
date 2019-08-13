package ujjwal.anand.globallogic.admin.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ujjwal.anand.globallogic.admin.db.DatabaseHelper;
import ujjwal.anand.globallogic.admin.R;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    EditText mEnterID,mEnterPass;
    Button mRegisterBtn;
    DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }

        mDatabaseHelper = new DatabaseHelper(this);

        initViews();

        mRegisterBtn.setOnClickListener(this);
    }

    private void initViews() {

        mEnterID = findViewById(R.id.register_id);
        mEnterPass = findViewById(R.id.pass);
        mRegisterBtn = findViewById(R.id.register_btn);

    }

    @Override
    public void onClick(View view) {
        if(view== mRegisterBtn){
            if(!TextUtils.isEmpty(mEnterID.getText().toString()) && !TextUtils.isEmpty(mEnterPass.getText().toString())){
                if(mDatabaseHelper.insertData(mEnterID.getText().toString(),mEnterPass.getText().toString())) {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(), "User Already Exists",Toast.LENGTH_LONG).show();
                }
            }else {
                Toast.makeText(getApplicationContext(),"Enter Details",Toast.LENGTH_LONG).show();
            }
        }
    }
}