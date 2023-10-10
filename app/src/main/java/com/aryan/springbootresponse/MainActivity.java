package com.aryan.springbootresponse;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.JsonArrayRequest;
import com.aryan.springbootresponse.activities.LoginActivity;
import com.aryan.springbootresponse.activities.SignUpActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    Button signupButton,loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signupButton = findViewById(R.id.signupButton);
        loginButton = findViewById(R.id.loginButton);

        signupButton.setOnClickListener(v->{
            Intent i = new Intent(MainActivity.this, SignUpActivity.class);
            MainActivity.this.startActivity(i);
        });
        loginButton.setOnClickListener(v->{
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            MainActivity.this.startActivity(i);
        });


//        String url="http://10.0.2.2:8080/users/getAll";
//        RequestQueue rq = Volley.newRequestQueue(this);
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
//                Request.Method.GET,
//                url,
//                null,
//                response -> {
//                    JSONObject obj = new JSONObject();
//                    try {
//                        obj = response.getJSONObject(0);
//                        Log.d("myRes", obj.getJSONArray("foods").get(2).toString());
//                    } catch (JSONException e) {
//                        Log.d("myRes","failed");
//                    }
//                },
//                error -> {
//                    Log.d("myRes", "Error Occured");
//                }
//        );
//        rq.add(jsonArrayRequest);
    }
}