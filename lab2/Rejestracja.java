package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Rejestracja extends AppCompatActivity {

    EditText login;
    EditText haslo;
    EditText powtorzone;
    Button button;
    String loginStr, hasloStr, powtorzoneStr;


    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "nameKey";
    public static final String Password = "passwordKey";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rejestracja);

        login = findViewById(R.id.editTextTextPersonName2);
        haslo = findViewById(R.id.editTextTextPassword2);
        powtorzone = findViewById(R.id.editTextTextPassword3);
        button = findViewById(R.id.button2);


        sharedpreferences = getSharedPreferences(MyPREFERENCES, this.MODE_PRIVATE);

        haslo.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                            hasloStr = haslo.getText().toString();
                            if(hasloStr.length() < 8 )
                                haslo.setTextColor(Color.RED);
                            else
                                haslo.setTextColor(Color.GREEN);

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                }
        );

        powtorzone.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        powtorzoneStr = powtorzone.getText().toString();
                        if (hasloStr.equals(powtorzoneStr))
                            powtorzone.setTextColor(Color.GREEN);
                        else
                            powtorzone.setTextColor(Color.RED);

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                }
        );


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginStr = login.getText().toString();
                hasloStr = haslo.getText().toString();
                powtorzoneStr = powtorzone.getText().toString();


                SharedPreferences.Editor editor = sharedpreferences.edit();


                if (hasloStr.equals(powtorzoneStr)) {
                    editor.putString(loginStr, powtorzoneStr);
                    editor.apply();
                    Toast.makeText(Rejestracja.this, "Thanks for registering", Toast.LENGTH_LONG).show();
                    finish();
                }else {
                    Toast.makeText(Rejestracja.this, "Passwords need to match!", Toast.LENGTH_LONG).show();
                }

            }
        });



    }


}