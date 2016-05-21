package com.anjon.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.anjon.manager.DatabaseManager;
import com.anjon.model.User;

/**
 * Created by anjon on 5/6/2016.
 */
public class WelcomeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = this.getSharedPreferences("LoginPref", Context.MODE_PRIVATE);
        int pref = sharedPreferences.getInt("isLoggedIn", 0);
        Log.d("TAGNAME", pref + "");
        if (sharedPreferences.getInt("isLoggedIn", 0) != 0) {
            setContentView(R.layout.activity_welcome);
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                if (bundle.containsKey("email")) {
                    DatabaseManager databaseManager = new DatabaseManager(this);
                    User user = databaseManager.GetUserByEmail(bundle.getString("email"));
                    if (user != null) {
                        if (!user.getName().isEmpty()) {
                            TextView tvName = (TextView) findViewById(R.id.text_name);
                            tvName.setText(user.getName());
                        }
                        if (!user.getEmail().isEmpty()) {
                            TextView tvEmail = (TextView) findViewById(R.id.text_email);
                            tvEmail.setText(user.getEmail());
                        }
                        if (!user.getPhone().isEmpty()) {
                            TextView tvPhone = (TextView) findViewById(R.id.text_phone);
                            tvPhone.setText(user.getPhone());
                        }
                    }
                }
            }
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}
