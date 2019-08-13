package ujjwal.anand.globallogic.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import ujjwal.anand.globallogic.admin.pojo.User;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    public final String PREF_NAME = "isLogged";
    public final String LOGGED_USER = "loggedUser";

    RecyclerView mListView;
    List<User> users;
    UserAdapter userAdapter;
    Button mSignOut,mDeleteAll;

    DatabaseHelper databaseHelper;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if(getSupportActionBar()!=null)
            getSupportActionBar().hide();

        initViews();

        sharedPreferences = getSharedPreferences(PREF_NAME,MODE_PRIVATE);

        Cursor result = databaseHelper.getAllData();

        while (result.moveToNext()){
            users.add(new User(result.getString(0)));
        }

        userAdapter = new UserAdapter(this,users,sharedPreferences.getString(LOGGED_USER,null));
        mListView.setAdapter(userAdapter);

        mSignOut.setOnClickListener(this);

        mDeleteAll.setOnClickListener(this);

    }

    public void initViews(){
        users = new ArrayList<>();
        mListView = findViewById(R.id.list_users);
        mListView.setHasFixedSize(true);
        mListView.setLayoutManager(new LinearLayoutManager(this));
        mSignOut = findViewById(R.id.sign_out);
        mDeleteAll = findViewById(R.id.del_all);

        databaseHelper = new DatabaseHelper(this);
    }

    @Override
    public void onClick(View view) {
        if(view==mSignOut){

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(LOGGED_USER,null);
            editor.apply();

            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(intent);
            finish();

        }
        if(view==mDeleteAll){

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(LOGGED_USER,null);
            editor.apply();

            databaseHelper.deleteAll();

            Intent intent = new Intent(getApplicationContext(),RegistrationActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
