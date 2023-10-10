package com.aryan.springbootresponse.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.aryan.springbootresponse.R;

public class Profile extends AppCompatActivity {
    TextView userName,userEmail,userCourse;
    Button signout_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String email = intent.getStringExtra("email");
        String course = intent.getStringExtra("course");

        userName=findViewById(R.id.userName);
        userEmail=findViewById(R.id.userEmail);
        userCourse=findViewById(R.id.userCourse);
        signout_button=findViewById(R.id.signout_button);
        userName.setText(name);
        userEmail.setText(email);
        userCourse.setText(course);

        signout_button.setOnClickListener(v->{
            userName.setText(null);
            userEmail.setText(null);
            userCourse.setText(null);
            onBackPressed();
            finish();
        });
    }
}