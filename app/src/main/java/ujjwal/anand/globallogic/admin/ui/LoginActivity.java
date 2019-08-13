package ujjwal.anand.globallogic.admin.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ujjwal.anand.globallogic.admin.db.DatabaseHelper;
import ujjwal.anand.globallogic.admin.R;

import static ujjwal.anand.globallogic.admin.constants.Constants.LOGGED_USER;
import static ujjwal.anand.globallogic.admin.constants.Constants.PREF_NAME;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText mLoginId,mPassword;
    Button mLoginBtn;
    DatabaseHelper mDatabaseHelper;
    TextView mRegister;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }

        sharedPreferences = getSharedPreferences(PREF_NAME,MODE_PRIVATE);

        initViews();

        mDatabaseHelper = new DatabaseHelper(this);

        mLoginBtn.setOnClickListener(this);

        mRegister.setOnClickListener(this);
    }

    private void initViews() {
        mLoginId = findViewById(R.id.login_id);
        mPassword = findViewById(R.id.pass);
        mLoginBtn = findViewById(R.id.login_btn);
        mRegister = findViewById(R.id.reg);
    }

    @Override
    public void onClick(View view) {
        if(view==mLoginBtn){

            String id = mLoginId.getText().toString();
            String pa = mPassword.getText().toString();

            if(TextUtils.isEmpty(id) && TextUtils.isEmpty(pa)){
                Toast.makeText(getApplicationContext(),"Enter Details",Toast.LENGTH_LONG);
            }else {
                if(!(mDatabaseHelper.getReqPassword(id)).equals(pa)){
                    Toast.makeText(getApplicationContext(),"Incorrect password",Toast.LENGTH_LONG).show();
                    mLoginId.setText(null);
                    mPassword.setText(null);
                }else{

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(LOGGED_USER,id);
                    editor.apply();

                    Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }
        if(view==mRegister){
            Intent intent = new Intent(getApplicationContext(),RegistrationActivity.class);
            startActivity(intent);

        }
    }
}
