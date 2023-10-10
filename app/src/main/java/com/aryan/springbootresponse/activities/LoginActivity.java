package com.aryan.springbootresponse.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.aryan.springbootresponse.R;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONObject;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {
    ImageView backButton;
    TextInputEditText login_email,login_password;
    Button login_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Intent i = getIntent();
        backButton = findViewById(R.id.backButton);
        login_button = findViewById(R.id.login_button);
        login_email = findViewById(R.id.login_email);
        login_password = findViewById(R.id.login_password);


        login_button.setOnClickListener(v->{
            if (login_email.getText().toString().isEmpty() || login_password.getText().toString().isEmpty()){
                Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
            }
            else{
                RequestQueue rq = Volley.newRequestQueue(this);
                String url = "http://192.168.29.115:8080/api/v1/user/login";
                HashMap<String,String> params = new HashMap<>();
                params.put("email",login_email.getText().toString());
                params.put("password",login_password.getText().toString());
                JsonObjectRequest jor = new JsonObjectRequest(
                        Request.Method.POST,
                        url,
                        new JSONObject(params),
                        response->{
                            try {
                                if (response.toString().equalsIgnoreCase("Incorrect Password")){
                                    Toast.makeText(this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                                } else if (response.toString().equalsIgnoreCase("Email Doesnt Exist")) {
                                    Toast.makeText(this, "Email Doesnt Exist", Toast.LENGTH_SHORT).show();
                                }
                                String name = response.getString("name");
                                String email = response.getString("email");
                                String course = response.getString("course");

                                Intent intent = new Intent(LoginActivity.this, Profile.class);
                                intent.putExtra("name",name);
                                intent.putExtra("email",email);
                                intent.putExtra("course",course);
                                LoginActivity.this.startActivity(intent);
                            }catch(Exception e){
                                Toast.makeText(this, "Error while parsing data", Toast.LENGTH_SHORT).show();
                            }
                        },
                        error->{
                            Toast.makeText(this, "User Doesn't Exist", Toast.LENGTH_SHORT).show();
                        }
                );
                login_email.setText("");
                login_password.setText("");
                rq.add(jor);
            }
        });
        backButton.setOnClickListener(view->{
            onBackPressed();
        });
    }
}