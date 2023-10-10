package com.aryan.springbootresponse.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aryan.springbootresponse.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {
    ImageView backButton;
    TextInputEditText user_name,user_email,user_course,user_password,user_confirmPassword;
    Button user_signup_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Intent i = getIntent();

        backButton = findViewById(R.id.backButton);
        user_signup_button = findViewById(R.id.user_signup_button);
        user_name=findViewById(R.id.user_name);
        user_email=findViewById(R.id.user_email);
        user_course=findViewById(R.id.user_course);
        user_password=findViewById(R.id.user_password);
        user_confirmPassword=findViewById(R.id.user_confirmPassword);

        backButton.setOnClickListener(view->{
            onBackPressed();
        });

        user_signup_button.setOnClickListener(bd->{
            if (user_name.getText().toString().isEmpty()||user_email.getText().toString().isEmpty()||user_course.getText().toString().isEmpty()||user_password.getText().toString().isEmpty()||user_confirmPassword.getText().toString().isEmpty()){
                Toast.makeText(this, "Invalid Inputs", Toast.LENGTH_SHORT).show();
            } else if (!user_password.getText().toString().equals(user_confirmPassword.getText().toString())) {
                Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show();
            }
            else{
                String name = user_name.getText().toString();
                String email = user_email.getText().toString();
                String course = user_course.getText().toString();
                String password = user_password.getText().toString();
                RequestQueue requestQueue = Volley.newRequestQueue(this);
                String url ="http://192.168.29.115:8080/api/v1/user/register";
                StringRequest req = new StringRequest(
                        Request.Method.POST,
                        url,
                        response -> {
                            if (response.equalsIgnoreCase("Registered User")){
                                user_name.setText(null);
                                user_email.setText(null);
                                user_course.setText(null);
                                user_password.setText(null);
                                user_confirmPassword.setText(null);
                                Toast.makeText(this, "Registered User Successfully", Toast.LENGTH_SHORT).show();
                            }
                        },
                        error -> {
                            Toast.makeText(this, "Registeration UnSuccessfull", Toast.LENGTH_SHORT).show();
                        }
                ){ //we need to send the information collected from the textFields and set them as parameters here
                    @NonNull
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String,String> params = new HashMap<>();
                        params.put("name",name);
                        params.put("email",email);
                        params.put("course",course);
                        params.put("password",password);
                        return params;
                    }
                };
                requestQueue.add(req);
            }
        });
    }
}