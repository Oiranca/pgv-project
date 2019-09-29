package com.oiranca.pglproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button loginButton;
    TextView sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    sign = (TextView) findViewById(R.id.text_sign);
    sign.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent signIntent = new Intent(getApplicationContext(),ActivitySignUp.class);
            startActivity(signIntent);
        }
    });

    }



}
