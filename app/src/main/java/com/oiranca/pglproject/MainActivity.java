package com.oiranca.pglproject;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.FirebaseApp;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {
    static boolean firebaseInitialized = false;

    Button loginButton;
    TextView sign, forgot;
    EditText mail, pass;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private String rang;
    private String passComp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        mail = findViewById(R.id.plain_email);
        pass = findViewById(R.id.plain_password);



        FirebaseApp.initializeApp(getApplicationContext());

        if (!MainActivity.firebaseInitialized) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(false);
            MainActivity.firebaseInitialized = true;
        }
        mail.setText("oiranca@gmail.com");
        pass.setText("samuel");

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        sign = findViewById(R.id.text_sign);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent signIntent = new Intent(getApplicationContext(), DialogAdmin.class);
                startActivity(signIntent);

            }
        });

        forgot = findViewById(R.id.text_forgot);
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent forgotIntent = new Intent(getApplicationContext(), ActivityForgot.class);
                startActivity(forgotIntent);

            }
        });

        loginButton = findViewById(R.id.button_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for (DataSnapshot emails : dataSnapshot.getChildren()) {
                            String valuesKey = emails.getKey();
                            if (valuesKey != null) {
                                String emailComp;
                                String emailCompF;
                                emailComp = dataSnapshot.child(valuesKey).child(mail.getText().toString().replace(".", "-")).
                                        child("email").getValue(String.class);
                                if (emailComp != null) {
                                    if (emailComp.contains(mail.getText().toString())) {
                                        passComp = dataSnapshot.child(valuesKey).child(mail.getText().toString().replace(".", "-")).
                                                child("pass").getValue(String.class);
                                        if ((passComp != null)) {
                                            if (passComp.contains(pass.getText().toString())) {
                                                rang = dataSnapshot.child(valuesKey).child(mail.getText().toString().replace(".", "-")).
                                                        child("range").getValue(String.class);
                                                if (rang != null) {
                                                    if (rang.contains("Admin")) {

                                                        if (passComp.equals(pass.getText().toString())) {
                                                            Intent admin = new Intent(getApplicationContext(), NavigationAdmin.class);
                                                            admin.putExtra("Admin", mail.getText().toString());
                                                            startActivity(admin);
                                                        }
                                                    }

                                                }
                                            }

                                        }
                                    }


                                } else {
                                    emailCompF = dataSnapshot.child(valuesKey).child(mail.getText().toString().replace(".", "-")).
                                            child("emailF").getValue(String.class);
                                    if (emailCompF != null) {
                                        if (emailCompF.contains(mail.getText().toString())) {
                                            passComp = dataSnapshot.child(valuesKey).child(mail.getText().toString().replace(".", "-")).
                                                    child("passF").getValue(String.class);
                                            if ((passComp != null)) {
                                                if (passComp.contains(pass.getText().toString())) {
                                                    rang = dataSnapshot.child(valuesKey).child(mail.getText().toString().replace(".", "-")).
                                                            child("rangeF").getValue(String.class);
                                                    if (rang != null) {
                                                        if (rang.contains("Family")) {

                                                            if (passComp.equals(pass.getText().toString())) {
                                                                Intent fam = new Intent(getApplicationContext(), TabFamily.class);
                                                                fam.putExtra("Family", mail.getText().toString());
                                                                startActivity(fam);
                                                            }
                                                        }

                                                    }
                                                }

                                            }
                                        }


                                    }

                                }


                            } else {
                                Toast.makeText(getApplicationContext(), "No se encuentrandatos", Toast.LENGTH_LONG).show();
                            }


                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });


    }


}