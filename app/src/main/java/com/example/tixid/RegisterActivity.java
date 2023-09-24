package com.example.tixid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText username, password, retypePassword, phoneNumber;
    Button signup, signin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.etUsername);
        password = findViewById(R.id.etPassword);
        retypePassword = findViewById(R.id.etRetypePassword);
        phoneNumber = findViewById(R.id.etPhoneNumber);
        signup = findViewById(R.id.btnRegister);
        signin = findViewById(R.id.btnSignIn);
        DB = new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = retypePassword.getText().toString();
                String phone = phoneNumber.getText().toString();

                if(user.equals("")|| pass.equals("")||repass.equals("")||phone.equals("")){
                    Toast.makeText(RegisterActivity.this, "Please fill all the column!", Toast.LENGTH_SHORT).show();
                }else{
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);

                        if(!checkuser){
                            Boolean insert = DB.insertData(user, pass, phone);
                            if(insert){
                                Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                PreferenceData.setUserLoggedInStatus(getApplicationContext(), true);
                                PreferenceData.setLoggedInUserUsername(getApplicationContext(), user);
                                finish();
                            }else{
                                Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(RegisterActivity.this, "User already exists!", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(RegisterActivity.this, "Password does not match!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        this.finish();
        super.onBackPressed();
    }
}