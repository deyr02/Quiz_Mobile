package com.example.cars;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.VolleyError;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {
    EditText txt_firstName, txt_lastName, txt_dob, txt_phoneNumber, txt_email, txt_password, txt_Confirm_password;
    Button btn_register;
    TextView lbl_register_error;
    private  API api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        api = new API(this);
        txt_firstName = findViewById(R.id.txt_firstName);
        txt_lastName = findViewById(R.id.txt_lastName);
        txt_dob = findViewById(R.id.txt_dob);
        txt_phoneNumber = findViewById(R.id.txt_phoneNumber);
        txt_email = findViewById(R.id.txt_email);
        txt_password = findViewById(R.id.txt_password);
        txt_Confirm_password = findViewById(R.id.txt_confirm_password);
        btn_register = findViewById(R.id.btn_register);
        lbl_register_error= findViewById(R.id.lbl_register_error);
        lbl_register_error.setVisibility(View.GONE);


        txt_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new DatePickerFragment(txt_dob);
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lbl_register_error.setVisibility(View.GONE);

                if(
                    CheckValidity(txt_firstName, "first name", 2, 20, lbl_register_error) &&
                    CheckValidity(txt_lastName, "last name", 2, 20, lbl_register_error) &&
                    CheckValidityPhoneNumber(txt_phoneNumber, "phone Number", 6, 20, lbl_register_error) &&
                    validateEmail(txt_email.getText().toString().trim(), lbl_register_error) &&
                    CheckValidity(txt_password, "password", 6, 20, lbl_register_error) &&
                    matchPassword(txt_password.getText().toString().trim(), txt_Confirm_password.getText().toString().trim(), lbl_register_error)

                ){
                    User user = new User(0,
                            txt_firstName.getText().toString().trim(),
                            txt_lastName.getText().toString().trim(),
                            txt_dob.getText().toString().trim(),
                            txt_email.getText().toString().trim(),
                            txt_phoneNumber.getText().toString().trim(),
                            txt_Confirm_password.getText().toString().trim(),
                            ""
                    );
                    api.register(user, new APIResponseListener<AccountResponseDTO>() {
                        @Override
                        public void onError(VolleyError error) {
                            lbl_register_error.setText(error.getMessage());
                            lbl_register_error.setVisibility(View.VISIBLE);
                            Log.e("Register Error", error.getMessage());
                        }

                        @Override
                        public void onResponse(AccountResponseDTO response) {
                            if(response.isSuccess()){
                                Intent intent = new Intent(getApplicationContext(), RegisterSuccess.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                getApplicationContext().startActivity(intent);
                            }
                            else {
                                lbl_register_error.setText(response.getMessage());
                                lbl_register_error.setVisibility(View.VISIBLE);

                            }
                        }
                    });


                }


            }
        });

    }

    public Boolean checkDateFormat(String date, TextView error_lbl){
        if (date.equals("")) {
            error_lbl.setText("Input date");
            error_lbl.setVisibility(View.VISIBLE);
            return false;
        }

            if (date == null || !date.matches("^(1[0-9]|0[1-9]|3[0-1]|2[1-9])/(0[1-9]|1[0-2])/[0-9]{4}$")) {
                error_lbl.setText("Input date");
                error_lbl.setVisibility(View.VISIBLE);
                return false;
            }

        SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
        try {
            format.parse(date);
            return true;
        }catch (ParseException e){
            error_lbl.setText("Invalid date");
            error_lbl.setVisibility(View.VISIBLE);
            return false;
        }
    }
    private  boolean CheckValidity(EditText editText, String title, Integer minLength, Integer maxLength, TextView error_lbl){
        String text = editText.getText().toString().trim();

        if(text.equals("")) {
            error_lbl.setText("Input " +title+ ".");
            error_lbl.setVisibility(View.VISIBLE);
            return false;
        }
        if(text.length() < minLength ) {
            error_lbl.setText(title+ " should have " + minLength + " characters.");
            error_lbl.setVisibility(View.VISIBLE);
            return false;
        }

        if(text.length() > maxLength ) {
            error_lbl.setText(title+ " should not have more than " + maxLength + " characters.");
            error_lbl.setVisibility(View.VISIBLE);
            return false;
        }

        return true;
    }
    private  boolean CheckValidityPhoneNumber(EditText editText, String title, Integer minLength, Integer maxLength, TextView error_lbl){
        String text = editText.getText().toString().trim();

        if(text.equals("")) {
            error_lbl.setText("Input " +title+ ".");
            error_lbl.setVisibility(View.VISIBLE);
            return false;
        }
        if(text.length() < minLength ) {
            error_lbl.setText(title+ " should have " + minLength + " digits.");
            error_lbl.setVisibility(View.VISIBLE);
            return false;
        }

        if(text.length() > maxLength ) {
            error_lbl.setText(title+ " should not have more than " + maxLength + " digits.");
            error_lbl.setVisibility(View.VISIBLE);
            return false;
        }

        return true;
    }

    private  boolean validateEmail(String email, TextView error_lbl){
        if(email.equals("")){
            error_lbl.setText("Input email address. ");
            error_lbl.setVisibility(View.VISIBLE);
            return false;
        }
        if(!isValidEmailId(email)){
            error_lbl.setText("Invalid email address. ");
            error_lbl.setVisibility(View.VISIBLE);
            return false;
        }
        return  true;
    }

    private boolean matchPassword(String password, String confirmPassword, TextView error_lbl){
        if(!password.equals(confirmPassword)){
            error_lbl.setText("Passwords does not match.");
            error_lbl.setVisibility(View.VISIBLE);
            return false;
        }
        return true;
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