package com.anjon.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.anjon.manager.DatabaseManager;
import com.anjon.model.User;

/**
 * Created by anjon on 5/1/2016.
 */
public class MainActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_login) {
            EditText etName = (EditText) findViewById(R.id.edit_name);
            EditText etEmail = (EditText) findViewById(R.id.edit_email);
            EditText etPhone = (EditText) findViewById(R.id.edit_phone);
            EditText etPassword = (EditText) findViewById(R.id.edit_password);
            EditText etConfirmPassword = (EditText) findViewById(R.id.edit_confirm_password);
            String name = etName.getText().toString().isEmpty() ? "Not provided" : etName.getText().toString();
            String email = etEmail.getText().toString();
            String phone = etPhone.getText().toString();
            String password = etPassword.getText().toString();
            String confirmPassword = etConfirmPassword.getText().toString();
            boolean isValidEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches();
            boolean isValidPhone = Patterns.PHONE.matcher(phone).matches();
            if (isValidPhone && isValidEmail && password.length() >= 6 && password.equals(confirmPassword)) {
                DatabaseManager databaseManager=new DatabaseManager(this);
                User user=new User();
                user.setName(name);
                user.setEmail(email);
                user.setPhone(phone);
                user.setPassword(password);
                databaseManager.InsertUser(user);
                Bundle bundle = new Bundle();
                /*bundle.putString("name", name);*/
                bundle.putString("email", email);
                /*bundle.putString("phone", phone);
                bundle.putString("password", password);*/
                Intent intent = new Intent(this, WelcomeActivity.class);
                intent.putExtras(bundle);
                SharedPreferences sharedPref=this.getSharedPreferences("LoginPref",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor= sharedPref.edit();
                editor.putInt("isLoggedIn", 1);
                editor.commit();
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            } else {
                if (email.isEmpty())
                    etEmail.setError("Email required");
                else if (!isValidEmail)
                    etEmail.setError("Invalid email");
                if (password.isEmpty())
                    etPassword.setError("Password required");
                else if (password.length() < 6)
                    etPassword.setError("Password is less than 6 characters");
                if (!isValidPhone)
                    etPhone.setError("Not a valid phone number");
                if (!password.equals(confirmPassword))
                    etConfirmPassword.setError("Password didn't match");
            }
        }
    }
}
