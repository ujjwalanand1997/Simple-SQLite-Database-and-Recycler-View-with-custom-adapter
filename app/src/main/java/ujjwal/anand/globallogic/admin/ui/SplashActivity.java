package ujjwal.anand.globallogic.admin.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import ujjwal.anand.globallogic.admin.R;

import static ujjwal.anand.globallogic.admin.constants.Constants.LOGGED_USER;
import static ujjwal.anand.globallogic.admin.constants.Constants.PREF_NAME;

public class SplashActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if(getSupportActionBar()!=null)
            getSupportActionBar().hide();

        sharedPreferences = getSharedPreferences(PREF_NAME,MODE_PRIVATE);

        String user = sharedPreferences.getString(LOGGED_USER,null);

        startNextActivity(user);
    }

    private void startNextActivity(final String user) {
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                if(user == null){
                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        },2000);
    }
}
