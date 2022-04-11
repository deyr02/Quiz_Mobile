package com.example.cars;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.VolleyError;

import java.util.regex.Pattern;


public class Login extends AppCompatActivity {
    private Button btn_login ;
    private EditText txt_email, txt_password;
    private TextView txt_register, lbl_error;
    private  API api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        api = new API(this);

        btn_login= findViewById(R.id.btn_login);
        txt_email = findViewById(R.id.txt_login_email);
        txt_password = findViewById(R.id.txt_login_password);
        txt_register = findViewById(R.id.lbl_register);
        lbl_error = findViewById(R.id.lbl_login_error);
        lbl_error.setVisibility(View.GONE);


        txt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getApplicationContext(), Register.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txt_email.getText().toString().trim();
                String password = txt_password.getText().toString().trim();

                if(!isValidEmailId(email)){
                    lbl_error.setVisibility(View.VISIBLE);
                    lbl_error.setText("Invalid Email Address");
                    return;
                }
                if(password.length()<6){
                    lbl_error.setVisibility(View.VISIBLE);
                    lbl_error.setText("Password must have six characters long");
                    return;
                }
                api.login(email, password, new APIResponseListener<AccountResponseDTO>() {
                    @Override
                    public void onError(VolleyError error) {
                        lbl_error.setVisibility(View.VISIBLE);
                        lbl_error.setText(error.getMessage());

                    }

                    @Override
                    public void onResponse(AccountResponseDTO response) {
                        lbl_error.setVisibility(View.GONE);
                        if(response.isSuccess()){
                            User user = response.getUser();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.putExtra("userObject", user);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            getApplicationContext().startActivity(intent);
                        }
                        else {
                            lbl_error.setVisibility(View.VISIBLE);
                            lbl_error.setText(response.getMessage());
                        }

                    }
                });



            }
        });
    }


    private boolean isValidEmailId(String email){

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }
}