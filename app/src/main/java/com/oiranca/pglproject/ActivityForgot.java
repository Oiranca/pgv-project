package com.oiranca.pglproject;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityForgot extends AppCompatActivity {

    EditText forgoten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        Toolbar toolbar = findViewById(R.id.toolbarForgot);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        forgoten = (EditText)findViewById(R.id.textForgot);

        FloatingActionButton fab = findViewById(R.id.fabSendAdm);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               correct();

            }
        });


    }

    private void correct() {

        forgoten.setError(null);

        String forget = forgoten.getText().toString();

        if (TextUtils.isEmpty(forget)) {

            forgoten.setError(getString(R.string.empty));
            forgoten.requestFocus();
            return;

        } else {


            Intent fortgot = new Intent(getApplicationContext(),MainActivity.class);
            Toast.makeText(getApplicationContext(),"Se le ha enviado un password provisional",Toast.LENGTH_LONG).show();
            startActivity(fortgot);
        }


    }
    


}
